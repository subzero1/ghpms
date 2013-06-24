package com.netsky.base.baseObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询结果集.
 * 
 * @author Chiang 2009-3-12
 */
public class ResultObject {

	/**
	 * 数据库记录总长度 使用分页查询时不为0
	 */
	private int totalRows = 0;

	/**
	 * 分页结果总页数,使用分页查询时不为0
	 */
	private int totalPages = 0;

	/**
	 * 结果集长度
	 */
	private int length = 0;

	/**
	 * 结果集目前位置,默认-1
	 */
	private int placeIndex = -1;

	/**
	 * 结果集map
	 */
	private Map<String, Object>[] map;

	/**
	 * 结果集关键字数组
	 */
	private String ResultArray[];

	/**
	 * 结果集是否用尽.
	 */
	private boolean bottom = false;
	
	/**
	 * 初始结果集list
	 */
	private List<?> list;


	/**
	 * 获取结果集关键字数组
	 * 
	 * @return String[]
	 */
	public String[] getResultArray() {
		return ResultArray;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows
	 *            the totalRows to set
	 */
	public void setTotalRows(int totalRows, int pageRowSize) {
		this.totalRows = totalRows;
		if (totalRows % pageRowSize == 0) {
			this.totalPages = totalRows / pageRowSize;
		} else {
			this.totalPages = totalRows / pageRowSize + 1;
		}
	}

	/**
	 * 获取结果集长度
	 * 
	 * @return int
	 */
	public int getLength() {
		return length;
	}

	/**
	 * 设置结果集长度,私有方法
	 */
	private void setLength(int length) {
		this.length = length;
	}

	/**
	 * 获取结果集位置
	 */
	public int getPlaceIndex() {
		return placeIndex;
	}

	/**
	 * 设置结果集位置
	 * 
	 * @param int
	 *            placeIndex 结果集位置
	 */
	public void setPlaceIndex(int placeIndex) {
		this.placeIndex = placeIndex;
	}

	/**
	 * 重置结果集位置
	 */
	public void reSetPlaceIndex() {
		setPlaceIndex(-1);
	}

	/**
	 * HibernateQueryBuilder查询方式对应构造方法
	 * 
	 * @param list
	 *            hibernate查询返回结果
	 * @param clazz
	 *            持久化对象Class
	 */
	@SuppressWarnings("unchecked")
	public ResultObject(List<?> list, Class<?> clazz) {
		this.list = list;
		setLength(list.size());
		map = new HashMap[list.size()];
		for (int i = 0; i < list.size(); i++) {
			map[i] = new HashMap<String, Object>();
			map[i].put(clazz.getName(), list.get(i));
		}
	}

	/**
	 * hsql查询对应构造方法
	 * 
	 * @param list
	 *            hibernate查询返回结果
	 * @param HSql
	 */
	@SuppressWarnings("unchecked")
	public ResultObject(List<?> list, String HSql) {
		this.list = list;
		/**
		 * 格式化hsql,生成keys
		 */
		if (HSql.toUpperCase().indexOf("SELECT") != -1) {
			HSql = HSql.substring(HSql.toUpperCase().indexOf("SELECT") + 6, HSql.toUpperCase().indexOf("FROM")).trim();
			ResultArray = HSql.split(",");
		} else if (HSql.toUpperCase().indexOf("WHERE") != -1) {
			HSql = HSql.substring(HSql.toUpperCase().indexOf("FROM") + 4, HSql.toUpperCase().indexOf("WHERE")).trim();
			ResultArray = HSql.split(",");
		} else {
			HSql = HSql.substring(HSql.toUpperCase().indexOf("FROM") + 4).trim();
			ResultArray = HSql.split(",");
		}
		for (int i = 0; i < ResultArray.length; i++) {
			if (ResultArray[i].indexOf(" ") != -1) {
				ResultArray[i] = ResultArray[i].split(" ")[ResultArray[i].split(" ").length - 1];
			}
		}
		/**
		 * 生成结果集
		 */
		map = new HashMap[list.size()];
		setLength(list.size());
		for (int i = 0; i < list.size(); i++) {
			map[i] = new HashMap<String,Object>();
			if (ResultArray.length > 1) {
				for (int j = 0; j < ResultArray.length; j++) {
					map[i].put(ResultArray[j], ((Object[]) list.get(i))[j]);
				}
			} else {
				map[i].put(ResultArray[0], list.get(i));
			}
		}
	}

	/**
	 * 指向下一记录
	 */
	public boolean next() {
		placeIndex++;
		if (placeIndex < length) {
			bottom = true;
			return true;
		} else {
			bottom = false;
			return false;
		}
	}

	/**
	 * 根据key获得结果
	 * 
	 * @param key
	 *            列名或对象名
	 */
	public Object get(String key) {
		if (placeIndex < length && bottom) {
			return map[placeIndex].get(key);
		} else {
			return null;
		}
	}

	/**
	 * 获取当前行记录map
	 */
	public Map<String, Object> getMap() {
		if (placeIndex < length && bottom) {
			return map[placeIndex];
		} else {
			return null;
		}
	}

	/**
	 * 重置结果集.
	 */
	public void reSet() {
		placeIndex = -1;
		bottom = false;
	}

	/**
	 * @return the bottom
	 */
	public boolean isBottom() {
		return bottom;
	}

	/**
	 * @param bottom
	 *            the bottom to set
	 */
	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}
	
	/**
	 * 获取结果集初始列表
	 * 
	 * @return the list
	 */
	public List<?> getList() {
		return list;
	}

}
