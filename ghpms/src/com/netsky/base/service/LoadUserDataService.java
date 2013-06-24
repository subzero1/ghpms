package com.netsky.base.service;

import com.netsky.base.dataObjects.Ta03_user;
import java.util.Map;

/**
 * 装载用户级的常用数据
 * @author wind
 */
public interface LoadUserDataService {
	public Map<String, Object> load(Ta03_user user);
}
