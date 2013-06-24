package com.netsky.base.dataObjects.interfaces;

/**
 * 处理blob字段接口对象,所有需要操作blob字段持久化对象需实现此接口.
 * 
 * @author Chiang
 */
public interface BlobObject {

	/**
	 * 设置blob
	 * 
	 * @param Blob
	 */
	public void setBlob(byte[] b);

	/**
	 * 读取blob
	 * 
	 * @return Blob
	 */
	public byte[] getBlob();

	/**
	 * 设置文件名
	 */
	public void setFileName(String fileName);

	/**
	 * 读取文件名
	 */
	public String getFileName();

	/**
	 * 复制对象
	 */
	public BlobObject cloneAttribute();
}
