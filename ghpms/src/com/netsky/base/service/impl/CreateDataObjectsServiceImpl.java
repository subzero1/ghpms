package com.netsky.base.service.impl;
import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.baseDao.JdbcSupport;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.utils.OperProperties;
import com.netsky.base.dataObjects.Tz07_dataobject_cfg;

/**
 * @author lee.xiangyu
 * @desc create dataObjects[java] from tz07
 * 		 create hibernate configure file[java.hbm] from dataObjects
 * 		 write [java.hbm] to applicationContext-hibernate.xml
 */
@Service("createDoService")
public class CreateDataObjectsServiceImpl {
	
	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 数据库查询操作服务
	 */
	@Autowired
	private QueryService queryService;
	
	/**
	 * 数据库保存操作服务
	 */
	@Autowired
	private SaveService saveService;
	
	@Autowired
	private JdbcSupport jdbcSupport;
	
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public void setJdbcSupport(JdbcSupport jdbcSupport) {
		this.jdbcSupport = jdbcSupport;
	}

	/**
	 * @param paramMap
	 * @desc create dataObjects[java] and hibernate *.hbm.xml from tz07
	 */
	public void createJavaAndXml(Map<String, Object> paramMap) throws Exception{
		/*
		 *获得相关参数 
		 */
		String webPath = convertUtil.toString(paramMap.get("webPath"));
		Long tz07_id =  convertUtil.toLong(paramMap.get("tz07_id"));
		paramMap.put("webPath", webPath);
		paramMap.put("tz07_id", tz07_id);
		createDoJava(paramMap);
		java2Xml(paramMap);
		saveService.updateByHSql("update Tz07_dataobject_cfg set status = '已处理' where id = "+tz07_id);
	}
	/**
	 * @param paramMap
	 * @desc create dataObjects[java] from ta06,ta07
	 */
	public void createDoJava(Map<String, Object> paramMap) throws Exception{
		
		OperProperties op = new OperProperties();
		JdbcTemplate jdbcTemplate = jdbcSupport.getJdbcTemplate();
		String appPath = (String)paramMap.get("appPath");
		String owner = (String)paramMap.get("owner");
		
		Long tz07_id = (Long)paramMap.get("tz07_id");
		if(tz07_id == null){
			throw new Exception("no tz07 params");
		}
		
		/*
		 * 获得持久化对象模板
		 */
		String classpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String classname = this.getClass().getSimpleName();
		classpath = classpath.replaceAll(classname + ".class", "createDoModel.model");
		String t_modelContent = getModelContent(classpath);
		if(t_modelContent == null){
			throw new Exception("not found DataObjects model["+classpath+"]");
		}
		
		/*
		 * 替换模板中的内容[$package,$date,$obj_name,$serializable,$sequence,$field_list]
		 */
		t_modelContent = t_modelContent.replace("$date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		Long t_random = Math.round(Math.random() * 1000000000000000000L);
		t_modelContent = t_modelContent.replace("$serializable", t_random + "L");
		
		/*
		 * 根据TA06获得类名
		 */
		Tz07_dataobject_cfg tz07 = (Tz07_dataobject_cfg)queryService.searchById(Tz07_dataobject_cfg.class, tz07_id);
		if(tz07 == null){
			throw new Exception("not found infomation in Tz07_dataobject_cfg ,tz07_id="+tz07_id);
		}
		String objDesc = null;
		String objectName = null;
		String packageName = null;
		String tableName = null;
		
		if(tz07 != null){
			objDesc = convertUtil.toString(tz07.getObj_desc());
			if(objDesc == null){
				throw new Exception("not found Object name[objDesc] in Tz07_dataobject_cfg,tz07_id="+tz07_id);
			}
			 
			op.loadFromString(objDesc,null,null);
			Properties p = op.getContainer();
			
			objectName = convertUtil.toString(p.getProperty("object"));
			packageName = objectName.substring(0,objectName.lastIndexOf("."));
			tableName = objectName.substring(objectName.lastIndexOf(".") + 1 , objectName.length());
			tableName = tableName.toUpperCase().substring(0,1) + tableName.toLowerCase().substring(1,tableName.length());
			String sequenceName = convertUtil.toString(p.getProperty("sequence"),tableName+"_num");
			
			t_modelContent = t_modelContent.replaceAll("\\$obj_name", tableName);
			t_modelContent = t_modelContent.replaceAll("\\$sequence", sequenceName);
			t_modelContent = t_modelContent.replace("$package", packageName);
		}

		/*
		 * 根据TA07获得字段名
		 */
		String sql = "select col.column_name column_name,col.data_type data_type,com.comments comments "
			       + "from dba_tab_cols col,dba_col_comments com "
			       + "where col.table_name = com.table_name "
			       + "and col.column_name = com.column_name "
			       + "and col.owner = com.owner "
			       + "and col.column_name <> 'ID' "
			       + "and col.table_name = '"+tableName.toUpperCase()+"' "
				   + "and col.owner='"+owner+"'";
		
		StringBuffer t_fieldlist = new StringBuffer("");
		List list = jdbcTemplate.queryForList(sql);
		for(int i = 0;i < list.size();i++){
			Map map = (Map)list.get(i);
			
			String t_name = convertUtil.toString(map.get("column_name")).toLowerCase();
			String tt_name = t_name.toUpperCase().substring(0,1) + t_name.toLowerCase().substring(1,t_name.length());
			String t_comment = convertUtil.toString(map.get("comments"));
			String t_datatype = convertUtil.toString(map.get("data_type"));
			if(t_datatype.toLowerCase().indexOf("date") != -1){
				t_datatype = "Date";
			}
			else if(t_datatype.toLowerCase().indexOf("number") != -1){
				t_datatype = "Long";
			}
			else{
				t_datatype = "String";
			}
			
			if(!t_name.equals("") || !t_name.toLowerCase().equals("id")){
				t_fieldlist.append(" /**\n");
				t_fieldlist.append(" * " + t_comment + "\n");
				t_fieldlist.append(" */\n");
				t_fieldlist.append(" private " + t_datatype + " " + t_name + ";\n\n");
				t_fieldlist.append(" /**\n");
				t_fieldlist.append(" * @hibernate.property column=\"" + t_name + "\"\n");
				t_fieldlist.append(" * @return Returns the " + t_name + ".\n");
				t_fieldlist.append(" */\n");
				t_fieldlist.append(" public " + t_datatype +" get" + tt_name + "() {\n");
				t_fieldlist.append("    return " + t_name + ";\n");
				t_fieldlist.append(" }\n\n");
				t_fieldlist.append(" public void set" + tt_name + "(" + t_datatype + " " + t_name + ") {\n");
				t_fieldlist.append("    this." + t_name + " = " + t_name + ";\n");
				t_fieldlist.append("  }\n\n");
			}
		}
		t_modelContent = t_modelContent.replace("$field_list", t_fieldlist.toString());
		
		/*
		 * 生成文件
		 */
		if(t_modelContent != null && !t_modelContent.trim().equals("")){
			StringBuffer t_container  = new StringBuffer("");
			t_container.delete(0, t_container.length());
			t_container.append(appPath);
			t_container.append(System.getProperty("file.separator"));
			t_container.append("src");
			t_container.append(System.getProperty("file.separator"));
			t_container.append(packageName.replaceAll("\\.", "/"));
			t_container.append(System.getProperty("file.separator"));
			t_container.append(tableName+".java");
			FileOutputStream fout = new FileOutputStream(t_container.toString());
			Writer out = new OutputStreamWriter(fout, "utf-8");
			out.write(t_modelContent);
			out.close();
		}
	}
	
	/**
	 * @desc create hibernate configure file[java.hbm] from dataObjects
	 */
	public void java2Xml(Map<String, Object> paramMap) throws Exception{
		
		OperProperties op = new OperProperties();
		JdbcTemplate jdbcTemplate = jdbcSupport.getJdbcTemplate();
		String appPath = (String)paramMap.get("appPath");
		String owner = (String)paramMap.get("owner");
		
		Long tz07_id = (Long)paramMap.get("tz07_id");
		if(tz07_id == null){
			throw new Exception("no tz07 params");
		}
		
		/*
		 * 获得持久化对象模板
		 */
		String classpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String classname = this.getClass().getSimpleName();
		classpath = classpath.replaceAll(classname + ".class", "createDoModelXml.model");
		String t_modelContent = getModelContent(classpath);
		if(t_modelContent == null){
			throw new Exception("not found DataObjects model["+classpath+"]");
		}
		
		/*
		 * 替换模板中的内容[$package,$date,$obj_name,$serializable,$sequence,$field_list]
		 */
		t_modelContent = t_modelContent.replace("$date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		Long t_random = Math.round(Math.random() * 1000000000000000000L);
		t_modelContent = t_modelContent.replace("$serializable", t_random + "L");
		
		/*
		 * 根据TA06获得类名
		 */
		Tz07_dataobject_cfg tz07 = (Tz07_dataobject_cfg)queryService.searchById(Tz07_dataobject_cfg.class, tz07_id);
		if(tz07 == null){
			throw new Exception("not found infomation in Tz07_dataobject_cfg ,tz07_id="+tz07_id);
		}
		String objDesc = null;
		String objectName = null;
		String packageName = null;
		String tableName = null;
		
		if(tz07 != null){
			objDesc = convertUtil.toString(tz07.getObj_desc());
			if(objDesc == null){
				throw new Exception("not found Object name[objDesc] in Tz07_dataobject_cfg,tz07_id="+tz07_id);
			}
			 
			op.loadFromString(objDesc,null,null);
			Properties p = op.getContainer();
			
			objectName = convertUtil.toString(p.getProperty("object"));
			packageName = objectName.substring(0,objectName.lastIndexOf("."));
			tableName = objectName.substring(objectName.lastIndexOf(".") + 1 , objectName.length());
			tableName = tableName.toUpperCase().substring(0,1) + tableName.toLowerCase().substring(1,tableName.length());
			String sequenceName = convertUtil.toString(p.getProperty("sequence"),tableName+"_num");
			
			t_modelContent = t_modelContent.replaceAll("\\$obj_name", objectName);
			t_modelContent = t_modelContent.replaceAll("\\$table_name", tableName);
			t_modelContent = t_modelContent.replaceAll("\\$sequence", sequenceName);
			t_modelContent = t_modelContent.replace("$package", packageName);
		}

		/*
		 * 根据TA07获得字段名
		 */
		String sql = "select col.column_name column_name,col.data_type data_type,com.comments comments "
			       + "from dba_tab_cols col,dba_col_comments com "
			       + "where col.table_name = com.table_name "
			       + "and col.column_name = com.column_name "
			       + "and col.owner = com.owner "
			       + "and col.column_name <> 'ID' "
			       + "and col.table_name = '"+tableName.toUpperCase()+"' "
			       + "and col.owner='"+owner+"'";
		
		StringBuffer t_fieldlist = new StringBuffer("");
		List list = jdbcTemplate.queryForList(sql);
		for(int i = 0;i < list.size();i++){
			Map map = (Map)list.get(i);
			
			String t_name = convertUtil.toString(map.get("column_name")).toLowerCase();
			String tt_name = t_name.toUpperCase().substring(0,1) + t_name.toLowerCase().substring(1,t_name.length());
			String t_comment = convertUtil.toString(map.get("comments"));
			String t_datatype = convertUtil.toString(map.get("data_type"));
			if(t_datatype.toLowerCase().indexOf("date") != -1){
				t_datatype = "java.util.Date";
			}
			else if(t_datatype.toLowerCase().indexOf("number") != -1){
				t_datatype = "java.lang.Long";
			}
			else{
				t_datatype = "java.lang.String";
			}
			
			if(!t_name.equals("") || !t_name.toLowerCase().equals("id")){
			
				t_fieldlist.append("  <property\n");
				t_fieldlist.append("  name=\"" + t_name + "\"\n");
				t_fieldlist.append("  type=\"" + t_datatype + "\"\n");
				t_fieldlist.append("  update=\"true\"\n");
				t_fieldlist.append("  insert=\"true\"\n");
				t_fieldlist.append("  column=\"" + t_name + "\"\n");
				t_fieldlist.append("  />\n\n");
			}
		}
		t_modelContent = t_modelContent.replace("$field_list", t_fieldlist.toString());
		
		/*
		 * 生成文件
		 */
		if(t_modelContent != null && !t_modelContent.trim().equals("")){
			StringBuffer t_container  = new StringBuffer("");
			t_container.delete(0, t_container.length());
			t_container.append(appPath);
			t_container.append(System.getProperty("file.separator"));
			t_container.append("src");
			t_container.append(System.getProperty("file.separator"));
			t_container.append(packageName.replaceAll("\\.", "/"));
			t_container.append(System.getProperty("file.separator"));
			t_container.append(tableName+".hbm.xml");
			FileOutputStream fout = new FileOutputStream(t_container.toString());
			Writer out = new OutputStreamWriter(fout, "utf-8");
			out.write(t_modelContent);
			out.close();
		}
	}

	public static String getModelContent(String pathname) {
		StringBuffer modelContent = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname), "UTF-8"));
			String line = "";
			try {
				while ((line = br.readLine()) != null) {
					modelContent.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return modelContent.toString();
	}
}
