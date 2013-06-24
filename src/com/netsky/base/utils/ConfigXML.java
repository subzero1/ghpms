package com.netsky.base.utils;

import java.util.List;

/**
 * @description:
 * 
 * @class name:com.ghpms.controller.mbk.ConfigXML
 * @author net Dec 12, 2012
 */
public interface ConfigXML {
	public String getConfigFilePath(String config, String webinfpath)
			throws Exception;

	/**
	 * 
	 * @param config
	 * @param webinfpath
	 * @param importXML
	 * @return
	 * @throws Exception String
	 */
	public String getConfigFilePath(String config, String webinfpath,
			String importXML) throws Exception;

	/**
	 * 
	 * @param configFilePath
	 * @param webinfpath
	 * @return
	 * @throws Exception List
	 */
	public List getTagList(String configFilePath, String webinfpath)
			throws Exception;

	/**
	 * 
	 * @param configFilePath
	 * @param webinfpath
	 * @param tagName
	 * @return
	 * @throws Exception List
	 */
	public List getTagList(String configFilePath, String webinfpath,
			String tagName) throws Exception;

	/**
	 * 
	 * @param config
	 * @param webinfpath
	 * @param tagName
	 * @return
	 * @throws Exception List
	 */
	public List getTagListByConfig(String config, String webinfpath,
			String tagName) throws Exception;

	/**
	 * 
	 * @param XMLPath XML文件的路径
	 * @param tagName 所有查找的节点名称
	 * @return 返回的是节点的集合
	 * @throws Exception List
	 */
	public List getElementsByName(String XMLPath, String tagName)
			throws Exception; 

}
