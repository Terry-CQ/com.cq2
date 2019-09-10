package com.yueqian.faq.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.yueqian.faq.common.Constants;
import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.dao.ProblemDao;
import com.yueqian.faq.dao.ReplyDao;
import com.yueqian.faq.domain.PageInfo;
import com.yueqian.faq.domain.ProblemDetailInfo;
import com.yueqian.faq.domain.ProblemInfo;
import com.yueqian.faq.domain.ReplyInfo;

public class ProblemService {
	private static ProblemService instance;

	public synchronized static ProblemService getInstance() {
		if (instance == null) {
			instance = new ProblemService();
		}
		return instance;
	}

	private ProblemService() {

	}

	/**
	 * 发布问题
	 * 
	 * @param info
	 * @return
	 */
	public boolean saveInfo(ProblemInfo info) {
		int count = 0;
		try {
			count = ProblemDao.getInstance().saveInfo(info);
			DBUtils.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtils.rollback();
		}
		return count > 0;
	}

	/**
	 * 获取所有已处理和未处理的问题列表
	 * 
	 * @param pageNo
	 *            页码
	 * @param resolved
	 *            已解决的
	 * @return
	 */
	public PageInfo findProblemList(int pageNo, boolean resolved) {
		PageInfo pageInfo = new PageInfo();

		if (pageNo < 1) {
			pageNo = 1;
		}

		try {
			// 设置总页数
			int recordCount = ProblemDao.getInstance().findProblemCountByStatus(resolved);
			pageInfo.setPageCount((recordCount + Constants.PAGE_SIZE - 1) / Constants.PAGE_SIZE);

			if (pageNo > pageInfo.getPageCount()) {
				pageNo = pageInfo.getPageCount();
			}
			// 设置列表数据
			List<ProblemInfo> list = ProblemDao.getInstance().findProblemesByStatus(resolved, pageNo);
			pageInfo.setList(list);

			// 设置当前页码
			pageInfo.setCurrentPageNo(pageNo);
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
		}
		return pageInfo;
	}

	/**
	 * 推荐问题(悬赏分数大于20分且未解决的问题)
	 * 
	 * @param pageNo
	 * @param b
	 * @return
	 */
	public PageInfo findRecommendProblemList(int pageNo) {
		PageInfo pageInfo = new PageInfo();

		if (pageNo < 1) {
			pageNo = 1;
		}

		try {
			// 设置总页数
			int recordCount = ProblemDao.getInstance().findRecommendProblemCount();
			pageInfo.setPageCount((recordCount + Constants.PAGE_SIZE - 1) / Constants.PAGE_SIZE);

			if (pageNo > pageInfo.getPageCount()) {
				pageNo = pageInfo.getPageCount();
			}
			// 设置列表数据
			List<ProblemInfo> list = ProblemDao.getInstance().findRecommendProblem(pageNo);
			pageInfo.setList(list);

			// 设置当前页码
			pageInfo.setCurrentPageNo(pageNo);
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
		}
		return pageInfo;
	}

	/**
	 * 根据问题编号获取问题详情
	 * 
	 * @param probId
	 * @return
	 */
	public ProblemDetailInfo findDetailById(Integer probId) {

		// 属性拷贝
		ProblemDetailInfo detail = new ProblemDetailInfo();

		try {
			// Auto-generated method stub
			ProblemInfo info = ProblemDao.getInstance().findInfoById(probId);
			// 回复
			List<ReplyInfo> replies = ReplyDao.getInstance().findRepliesByProbId(probId);

			try {
				BeanUtils.copyProperties(detail, info);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 寻找最佳回复
			for (int i = 0; i < replies.size(); i++) {
				if (replies.get(i).isBestReply()) {
					detail.setBestReply(replies.remove(i));
					break;
				}
			}
			detail.setReplies(replies);
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}
		return detail;
	}

	/**
	 * 根据图片ID获取问题信息
	 * 
	 * @param fileId
	 * @return
	 */
	public ProblemInfo findInfoByFileId(String fileId) {
		ProblemInfo info = null;
		try {
			info = ProblemDao.getInstance().findInfoByFileId(fileId);
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}
		return info;
	}

}
