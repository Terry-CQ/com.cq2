package com.yueqian.faq.service;

import java.sql.SQLException;

import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.common.ProblemStatus;
import com.yueqian.faq.dao.ProblemDao;
import com.yueqian.faq.dao.ReplyDao;
import com.yueqian.faq.dao.UserDao;
import com.yueqian.faq.domain.ReplyInfo;

public class ReplyService {
	private static ReplyService instance;

	public synchronized static ReplyService getInstance() {
		if (instance == null) {
			instance = new ReplyService();
		}
		return instance;
	}

	private ReplyService() {

	}

	/**
	 * 保存回复信息
	 * 
	 * @param info
	 * @return
	 */
	public boolean saveReplyInfo(ReplyInfo info) {
		int count = 0;
		try {
			count = ReplyDao.getInstance().saveReplyInfo(info);
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}
		return count > 0;
	}

	public ReplyInfo findInfoByFileId(String fileId) {
		ReplyInfo info = null;
		try {
			info = ReplyDao.getInstance().findInfoByFileId(fileId);
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 采纳回复为最佳回复
	 * 
	 * @param replyId
	 * @return 问题编号
	 */
	public Integer acceptReply(Integer replyId) {

		ReplyInfo replyInfo = null;
		try {
			replyInfo = ReplyDao.getInstance().findInfoByReplytId(replyId);
			// 将replyId对应的回复设为最佳回复
			int count = ReplyDao.getInstance().acceptReply(replyId);
			// 给回复者加上问题的悬赏分数，需要知道回复者的id，问题的悬赏分
			if (count > 0) {
				count = UserDao.getInstance().addScore(replyId);
			}
			// 修改问题的状态为【已解决】
			ProblemDao.getInstance().changeStatus(replyInfo.getProblemId(), ProblemStatus.RESOLVED);
			DBUtils.commit();
		} catch (Exception e) {
			e.printStackTrace();
			DBUtils.rollback();
		}
		return replyInfo.getProblemId();
	}
}
