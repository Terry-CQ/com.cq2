package com.yueqian.faq.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.dao.CategoryDao;
import com.yueqian.faq.dao.ProblemDao;
import com.yueqian.faq.dao.UserDao;
import com.yueqian.faq.domain.StatisticsInfo;

public class CommonService {

	// com.yueqian.faq.service.CommonService
	private Logger logger = Logger.getLogger(CommonService.class.getName());

	private static CommonService instance;

	public synchronized static CommonService getInstance() {
		if (instance == null) {
			instance = new CommonService();
		}
		return instance;
	}

	private CommonService() {

	}

	/**
	 * ��ȡҳ���ͨ�õ�ͳ����Ϣ
	 * 
	 * @return
	 */
	public StatisticsInfo findStatisticsInfo() {
		StatisticsInfo info = new StatisticsInfo();
		try {
			// �������
			info.setCategoryList(CategoryDao.getInstance().findAllCategories());
			// ��������
			info.setUserScores(UserDao.getInstance().findUserByScoresDesc());
			// ����ͳ��
			ProblemDao.getInstance().countQuestion(info);
			// �û�ͳ��
			UserDao.getInstance().countUserByQuestion(info);
			DBUtils.commit();
		} catch (SQLException e) {
			logger.error("��������ͳ����Ϣʱ�쳣:" + e.getMessage());
			DBUtils.rollback();
		}
		return info;
	}
}