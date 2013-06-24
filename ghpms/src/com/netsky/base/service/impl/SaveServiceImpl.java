package com.netsky.base.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.dataObjects.interfaces.BlobObject;
import com.netsky.base.service.SaveService;

/**
 * 保存操作服务实现
 * 
 * @author Chiang
 */
@Service("saveService")
public class SaveServiceImpl implements SaveService {

	@Autowired
	private Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public Object saveBlob(BlobObject blobObject,
			MultipartHttpServletRequest multipartRequest) throws Exception {
		Iterator<?> it = multipartRequest.getFileNames();
		while (it.hasNext()) {
			String fileDispath = (String) it.next();
			MultipartFile file = multipartRequest.getFile(fileDispath);
			blobObject.setFileName(file.getName());
			byte[] b = new byte[file.getInputStream().available()];
			file.getInputStream().read(b);
			blobObject.setBlob(b);
		}
		return dao.saveObject(blobObject);
	}

	public Object[] saveBlobs(BlobObject[] blobObject,
			MultipartHttpServletRequest multipartRequest) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Iterator<?> it = multipartRequest.getFileNames();
		int i = 0;
		while (it.hasNext() && i < blobObject.length) {
			String fileDispath = (String) it.next();
			MultipartFile file = multipartRequest.getFile(fileDispath);
			if (file != null) {
				byte[] b = new byte[file.getInputStream().available()];
				file.getInputStream().read(b);
				if (b.length != 0) {
					blobObject[i].setFileName(file.getOriginalFilename());
					blobObject[i].setBlob(b);
					list.add(blobObject[i]);
				}
			}
			i++;
		}
		return dao.saveObject(list.toArray(new Object[list.size()]));
	}

	public Object[] saveBlobs(BlobObject blobObject,
			MultipartHttpServletRequest multipartRequest) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Iterator<?> it = multipartRequest.getFileNames();
		while (it.hasNext()) {
			String fileDispath = (String) it.next();
			MultipartFile file = multipartRequest.getFile(fileDispath);
			if (file != null) {
				byte[] b = new byte[file.getInputStream().available()];
				file.getInputStream().read(b);
				if (b.length != 0) {
					BlobObject newObject = blobObject.cloneAttribute();
					newObject.setFileName(file.getOriginalFilename());
					newObject.setBlob(b);
					list.add(newObject);
				}
			}
		}
		return dao.saveObject(list.toArray(new Object[list.size()]));
	}

	public Object save(Object object) {
		return dao.saveObject(object);
	}

	public Object[] save(Object[] object) throws Exception {
		return dao.saveObject(object);
	}

	public void updateByHSql(String HSql) {
		dao.update(HSql);
	}

	public void removeObject(Class<?> clazz, Serializable id) {
		dao.removeObject(clazz, id);
	}

	public void removeObject(Object o) {
		dao.removeObject(o);
	}

	public Session getHiberbateSession() {
		return dao.getHibernateSession();
	}

	public Object excute(HibernateCallback action) {
		// TODO Auto-generated method stub
		return dao.getHibernateTemplate().execute(action);
	}

	public int updateByHSql(String HSql, Object[] objs) {
		return dao.update(HSql, objs);
	}

}
