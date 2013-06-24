package com.netsky.base.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netsky.base.service.SaveFormCodeService;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.dataObjects.Ta06_module;

/**
 * @description:
 * 
 * @class name:com.netsky.base.service.impl.SaveFormCodeServiceImpl
 *
 * @author lixiangyu 2010-1-26
 */
@Service("saveFormCodeService")
public class SaveFormCodeServiceImpl implements SaveFormCodeService {

	/**
	 * 表单编号
	 */
	private String formCode;
	
	@Autowired
	private Dao dao ;
	
	/**
	 * 持久化对象
	 */
	private String formTable ;
	
	/**
	 * 表单编号前缀(表单简称)
	 */
	private String formName ;
	
	/**
	 * method:setDao
	 * @param dao void
	 */
	public void setDao(Dao dao) {
		this.dao = dao;
	}

	/**
	 * method:setFormCode
	 * @param formCode void
	 */
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	/**
	 * method:setTableInfo
	 * @param module_id
	 * @throws Exception void
	 * @desc 变量初始化
	 */
	public void setTableInfo(Long module_id) throws Exception{
		
		if(module_id == null )
			throw new Exception("no module_id in [com.netsky.base.service.impl.SaveFormCodeService.java]");
		
		Class<?> clazz = null;
		QueryBuilder queryBuilder = null;
		
		clazz = Ta06_module.class;
		queryBuilder = new HibernateQueryBuilder(clazz);
		queryBuilder.eq("id", module_id);
		List list = dao.search(queryBuilder);
		Ta06_module ta06 = (Ta06_module)list.get(0);
		formTable = ta06.getForm_table();
		formName = ta06.getForm_name();
	}
	
	/**
	 * method:setFormCode
	 * @param module_id
	 * @throws Exception void
	 */
	public void setFormCode(Long module_id,Long doc_id) throws Exception{

		StringBuffer hsql = null ;
		String y4 = null;
		String m2 = null;
		List list = null;
		
		try{
			y4 = DateGetUtil.getYear() + "";
			m2 = StringFormatUtil.getCompleteString(DateGetUtil.getMonth() + "",2);
			
			/*
			 * 表单编号能体现出地址，根据表单起草人地区取
			 */
			if(module_id == 101 || module_id == 102){
				hsql = new StringBuffer("");
				hsql.append("select ta03.area_name ");
				hsql.append("from Td11_jfpmsq td11,Ta03_user ta03 ");
				hsql.append("where trim(td11.cjr) = trim(ta03.name) ");
				hsql.append("and td11.id = ");
				hsql.append(doc_id);
				list = dao.search(hsql.toString());
				String t_area = (String)list.get(0);
				if(t_area != null && t_area.indexOf("无锡") != -1){
					formName = formName + "-WX";
				}
				else if(t_area != null && t_area.indexOf("江阴") != -1){
					formName = formName + "-JY";
				}
				else if(t_area != null && t_area.indexOf("宜兴") != -1){
					formName = formName + "-YX";
				}
				else{
					formName = formName + "-QT";
				}
			}

			hsql = new StringBuffer("");
			this.formCode = formName + "-" + y4 + m2 + "-" ;

			hsql.append("select max(bdbh) from ");
			hsql.append(formTable);
			hsql.append(" where bdbh like '%");
			//hsql.append(y4);
			//hsql.append(m2);
			hsql.append(this.formCode);
			hsql.append("%'");
			list = dao.search(hsql.toString());
			String max_bdbh = (String)list.get(0);
			
			if(max_bdbh == null){
				this.formCode = this.formCode + "0001";
			}
			else if(max_bdbh.indexOf("-") != -1){
				max_bdbh = max_bdbh.substring(max_bdbh.lastIndexOf("-") + 1,max_bdbh.length());
				Long tmpSerialCode = new Long(max_bdbh) + 1;
				this.formCode = this.formCode + StringFormatUtil.getCompleteString(tmpSerialCode.toString(),4);
			}
		}
		catch(Exception e){
			throw new Exception("error in [com.netsky.base.service.impl.SaveFormCodeService.setFormCode(xxx)]:"+e);
		}
	}

	/**
	 * method:saveFormCode
	 * @param doc_id
	 * @throws Exception void
	 */
	public void saveCode(Long doc_id) throws Exception{
		
		StringBuffer hsql = null;
		
		try {
			hsql = new StringBuffer("");
			hsql.append("update ");
			hsql.append(formTable);
			hsql.append(" set bdbh = '");
			hsql.append(this.formCode);
			hsql.append("' where bdbh is null and id = ");
			hsql.append(doc_id);
			dao.update(hsql.toString());
		} catch (Exception e) {
			throw new Exception("error in [com.netsky.base.service.impl.SaveFormCodeService.saveFormCode]:"+e);
		}
	}

	/**
	 *　重载方法：saveFormCode
	 * (non-Javadoc)
	 * @see com.netsky.base.service.SaveFormCodeService#saveFormCode(java.lang.String, java.lang.String)
	 */
	public void saveFormCode(Long module_id,Long doc_id)  throws Exception{
		
		/**
		 * 初始化
		 */
		setTableInfo(module_id);
		
		/**
		 * 设置表单编号
		 */
		setFormCode(module_id,doc_id);
		
		/**
		 * 保存表单编号
		 */
		saveCode(doc_id);
	}
}
