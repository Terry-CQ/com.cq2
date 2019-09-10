package com.yueqian.faq.service;

import java.sql.SQLException;

import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.dao.AdminDao;
import com.yueqian.faq.dao.UserDao;
import com.yueqian.faq.domain.UserInfo;

public class UserService {

	private static UserService instance;

	public synchronized static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	private UserService() {

	}

	/**
	 * 根据账号查询用户信息
	 * 
	 * @param account
	 *            账号
	 * @param admin
	 *            是否为管理员
	 * @return 用户信息
	 */
	public UserInfo findInfoByAccount(String account, boolean admin) {
		UserInfo info = null;
		try {
			if (admin) {
				info = AdminDao.getInstance().findInfoByAccount(account);
			} else {
				info = UserDao.getInstance().findInfoByAccount(account);
			}
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 保存新注册的用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean saveInfo(UserInfo userInfo) {
		int count = 0;
		try {
			if (userInfo.isAdmin()) {
				// TODO 保存新管理员
			} else {
				count = UserDao.getInstance().saveInfo(userInfo);
			}
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}

		return count > 0;
	}
}
