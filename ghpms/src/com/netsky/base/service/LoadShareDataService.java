package com.netsky.base.service;

import com.netsky.base.dataObjects.Ta03_user;

/**
 * @author wind
 * 装载表单共用数据对象
 */
public interface LoadShareDataService {
	public java.util.Map<String, java.util.List<?>> load();
}