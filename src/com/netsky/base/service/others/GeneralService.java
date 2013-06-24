package com.netsky.base.service.others;

import java.io.File;

/**
 * @description:
 * ͨ��Service�ӿ�
 * @class name:com.ghpms.service.other.GeneralService
 * @author Administrator Jun 14, 2011
 */
public interface GeneralService {
	public boolean deleteFtpFile(String url,String webRoot) throws Exception;
	public boolean deleteFtpFiles(String[] urls,String webRoot) throws Exception;
}
