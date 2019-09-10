package com.yueqian.faq.service;

import java.sql.SQLException;
import java.util.List;

import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.dao.CategoryDao;
import com.yueqian.faq.domain.CategoryInfo;

/**
 * 问题类别服务类
 * 
 * @author Administrator
 *
 */
public class CategoryService {

	private static CategoryService instance;

	public synchronized static CategoryService getInstance() {
		if (instance == null) {
			instance = new CategoryService();
		}
		return instance;
	}

	private CategoryService() {

	}

	/**
	 * 获取所有问题类别
	 * 
	 * @return
	 */
	public List<CategoryInfo> findAllCategories() {
		List<CategoryInfo> list = null;
		try {
			list = CategoryDao.getInstance().findAllCategories();
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}
		return list;
	}
}
