package com.netsky.base.utils;

import java.util.Properties;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream; 
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author 李翔宇
 * @desctiption :operate the Properties file ,
 * such as reading,writing there are
 * four constructor.
 */
public class OperProperties {

	/**
	 * put the key-value list in it.
	 */
	private Properties  container;

	
	public Properties getContainer() {
		return container;
	}

	public void setContainer(Properties container) {
		this.container = container;
	}

	/**
	 * @param: load the pfile 
	 * content into the container;
	 */
	public void load(String pfile) throws FileNotFoundException,IOException{
		java.io.InputStream in = new BufferedInputStream(new FileInputStream(pfile));
		container = new Properties();
		container.load(in);
		in.close();
	}
	
	/**
	 * @param: load the [key1=value1&key2=value2...] 
	 * content into the container;
	 */
	public void loadFromString(String src,String firstSplitor,String secondSplitor){
		
		if(firstSplitor == null)
			firstSplitor = "&";
		
		if(secondSplitor == null)
			secondSplitor = "=";
		
		if(firstSplitor.equals(secondSplitor)){
			firstSplitor = "&";
			secondSplitor = "=";
		}
		
		if(src == null || src.length() == 0){
			container = new Properties();
		}
		else{
			container = new Properties();
			String [] t_var = src.split(firstSplitor);
			if(t_var != null && t_var.length > 0){
				for(int i = 0;i < t_var.length ;i++){
					String[] tt_var = t_var[i].split(secondSplitor);
					if(tt_var != null && tt_var.length == 2){
						container.setProperty(tt_var[0], tt_var[1]);
					}
				}
			}
		}
	}

	public String getValue(String key) {
		return container.getProperty(key);
	}
	
	/**
	 * @param: store the content to pfile 
	 */
	public static void write(String pfile,String key,String value) throws FileNotFoundException,IOException{
		java.io.InputStream in = new BufferedInputStream(new FileInputStream(pfile));
		Properties t_pro = new Properties();
		t_pro.load(in);
		t_pro.setProperty(key, value);
		
		java.io.OutputStream out = new BufferedOutputStream(new FileOutputStream(pfile));
		t_pro.store(out,"set");
		out.close();
		in.close();
	}
	
	public static void main(String[] args){
//		String a = "a=b";
//		loadFromString("a=b",null,null);
//		System.out.println(container.get("a"));
//		System.out.println(container.getProperty("a"));
	}
}
