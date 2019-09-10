package com.yueqian.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.yueqian.faq.common.Constants;
import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.common.DifficultyLevel;
import com.yueqian.faq.common.ProblemStatus;
import com.yueqian.faq.domain.ProblemInfo;
import com.yueqian.faq.domain.StatisticsInfo;
import com.yueqian.faq.domain.UserInfo;

public class ProblemDao {
	private static ProblemDao instance;

	public synchronized static ProblemDao getInstance() {
		if (instance == null) {
			instance = new ProblemDao();
		}
		return instance;
	}

	private ProblemDao() {

	}

	/**
	 * 保存问题
	 * 
	 * @param info
	 * @return
	 * @throws SQLException
	 */
	public int saveInfo(ProblemInfo info) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement(
					"INSERT INTO problem_info(prob_cate_id,prob_title,prob_level,score,content,pic_id,pic_file_name,"
							+ "pic_file_type,STATUS,submit_user_id,submit_time) " + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			if (info.getCategoryId() != null) {
				stmt.setInt(1, info.getCategoryId());
			} else {
				stmt.setNull(1, Types.INTEGER);
			}
			stmt.setString(2, info.getTitle());
			if (info.getLevel() != null) {
				stmt.setShort(3, (short) info.getLevel().getValue());
			} else {
				stmt.setNull(3, Types.SMALLINT);
			}
			if (info.getScore() != null) {
				stmt.setInt(4, info.getScore().intValue());
			} else {
				stmt.setNull(4, Types.INTEGER);
			}
			stmt.setString(5, info.getContent());
			stmt.setString(6, info.getFileId());
			stmt.setString(7, info.getFileName());
			stmt.setString(8, info.getFileType());
			stmt.setShort(9, (short) info.getStatus().getValue());
			stmt.setInt(10, info.getSubmitUserId());
			// java.sql.Date,java.sql.Time,java.sql.Timestamp
			stmt.setTimestamp(11, new java.sql.Timestamp(info.getSubmitTime().getTime()));
			count = stmt.executeUpdate();
		} finally {
			DBUtils.close(null, stmt);
		}
		return count;
	}

	/**
	 * 根据问题状态获取问题列表
	 * 
	 * @param resolved
	 *            是否为已解决的问题
	 * @return 问题列表
	 * @throws SQLException
	 */
	public List<ProblemInfo> findProblemesByStatus(boolean resolved, int pageNo) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ProblemInfo> list = new ArrayList<ProblemInfo>();
		try {
			stmt = conn.prepareStatement(
					"SELECT p.`prob_id`,p.`prob_cate_id`,p.`prob_title`,p.`prob_level`,p.`score`,p.`content`,"
							+ "p.`pic_id`,p.`pic_file_name`,p.`pic_file_type`,p.`status`,p.`submit_user_id`,p.`submit_time`,"
							+ "u.`alias` "
							+ "FROM problem_info p INNER JOIN user_info u ON p.`submit_user_id`=u.`user_id` "
							+ "WHERE p.`status`=? LIMIT ?,?");
			if (resolved) {
				stmt.setShort(1, (short) ProblemStatus.RESOLVED.getValue());
			} else {
				stmt.setShort(1, (short) ProblemStatus.UNRESOLVED.getValue());
			}
			stmt.setInt(2, (pageNo - 1) * Constants.PAGE_SIZE);
			stmt.setInt(3, Constants.PAGE_SIZE);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(convert2Info(rs));
			}
		} finally {
			DBUtils.close(rs, stmt);
		}
		return list;
	}

	private ProblemInfo convert2Info(ResultSet rs) throws SQLException {
		ProblemInfo probInfo = new ProblemInfo();
		probInfo.setProblemId(rs.getInt("prob_id"));
		if (rs.getObject("prob_cate_id") != null) {
			probInfo.setCategoryId(rs.getInt("prob_cate_id"));
		}
		probInfo.setTitle(rs.getString("prob_title"));
		if (rs.getObject("prob_level") != null) {
			probInfo.setLevel(DifficultyLevel.from(rs.getByte("prob_level")));
		}
		if (rs.getObject("score") != null) {
			probInfo.setScore(rs.getInt("score"));
		}
		probInfo.setContent(rs.getString("content"));
		probInfo.setFileId(rs.getString("pic_id"));
		probInfo.setFileName(rs.getString("pic_file_name"));
		probInfo.setFileType(rs.getString("pic_file_type"));
		probInfo.setStatus(ProblemStatus.from(rs.getByte("status")));

		probInfo.setSubmitUserId(rs.getInt("submit_user_id"));

		probInfo.setSubmitTime(new java.util.Date(rs.getTimestamp("submit_time").getTime()));

		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(probInfo.getSubmitUserId());
		userInfo.setAlias(rs.getString("alias"));
		probInfo.setSubmitUser(userInfo);
		return probInfo;
	}

	/**
	 * 统计问题条数
	 * 
	 * @param resolved
	 *            问题状态
	 * @return
	 * @throws SQLException
	 */
	public int findProblemCountByStatus(boolean resolved) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement("SELECT COUNT(p.`prob_id`) FROM problem_info p WHERE p.`status`=?");
			if (resolved) {
				stmt.setShort(1, (short) ProblemStatus.RESOLVED.getValue());
			} else {
				stmt.setShort(1, (short) ProblemStatus.UNRESOLVED.getValue());
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			DBUtils.close(rs, stmt);
		}
		return count;
	}

	/**
	 * 根据问题编号获取问题信息
	 * 
	 * @param probId
	 * @return
	 * @throws SQLException
	 */
	public ProblemInfo findInfoById(Integer probId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ProblemInfo info = null;
		try {
			stmt = conn.prepareStatement(
					"SELECT p.`prob_id`,p.`prob_cate_id`,p.`prob_title`,p.`prob_level`,p.`score`,p.`content`,"
							+ "p.`pic_id`,p.`pic_file_name`,p.`pic_file_type`,p.`status`,p.`submit_user_id`,p.`submit_time`,"
							+ "u.`alias` "
							+ "FROM problem_info p INNER JOIN user_info u ON p.`submit_user_id`=u.`user_id` "
							+ "WHERE p.`prob_id`=? ");
			stmt.setInt(1, probId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				info = convert2Info(rs);
			}
		} finally {
			DBUtils.close(rs, stmt);
		}
		return info;
	}

	/**
	 * 根据图片ID获取问题信息
	 * 
	 * @param fileId
	 * @return
	 * @throws SQLException
	 */
	public ProblemInfo findInfoByFileId(String fileId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ProblemInfo info = null;
		try {
			stmt = conn.prepareStatement(
					"SELECT p.`prob_id`,p.`prob_cate_id`,p.`prob_title`,p.`prob_level`,p.`score`,p.`content`,"
							+ "p.`pic_id`,p.`pic_file_name`,p.`pic_file_type`,p.`status`,p.`submit_user_id`,p.`submit_time`,"
							+ "u.`alias` "
							+ "FROM problem_info p INNER JOIN user_info u ON p.`submit_user_id`=u.`user_id` "
							+ "WHERE p.`pic_id`=? ");
			stmt.setString(1, fileId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				info = convert2Info(rs);
			}
		} finally {
			DBUtils.close(rs, stmt);
		}
		return info;
	}

	/**
	 * 问题数量统计
	 * 
	 * @param info
	 * @throws SQLException
	 */
	public void countQuestion(StatisticsInfo info) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(
					"SELECT (SELECT COUNT(p.`prob_id`) FROM problem_info p) t,(SELECT COUNT(p.`prob_id`) "
							+ "FROM problem_info p WHERE p.`status`=?) u,(SELECT COUNT(p.`prob_id`) FROM problem_info p WHERE p.`status`=? ) r");
			stmt.setShort(1, (short) ProblemStatus.UNRESOLVED.getValue());
			stmt.setShort(2, (short) ProblemStatus.RESOLVED.getValue());
			rs = stmt.executeQuery();
			if (rs.next()) {
				info.setQuestionCount(rs.getInt(1));
				info.setUnresolved(rs.getInt(2));
				info.setResolved(rs.getInt(3));
			}
		} finally {
			DBUtils.close(rs, stmt);
		}
	}

	/**
	 * 修改问题的状态
	 * 
	 * @param problemId
	 * @param resolved
	 * @throws SQLException
	 */
	public int changeStatus(Integer problemId, ProblemStatus status) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement("UPDATE problem_info SET STATUS=? WHERE prob_id=?");
			stmt.setShort(1, (short) status.getValue());
			stmt.setInt(2, problemId);
			count = stmt.executeUpdate();
		} finally {
			DBUtils.close(null, stmt);
		}
		return count;
	}

	/**
	 * 检索推荐问题的数量
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int findRecommendProblemCount() throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement(
					"SELECT COUNT(p.`prob_id`) FROM problem_info p WHERE p.`status`=? and p.score>20");
			stmt.setShort(1, (short) ProblemStatus.UNRESOLVED.getValue());
			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			DBUtils.close(rs, stmt);
		}
		return count;
	}

	/**
	 * 检索推荐问题
	 * 
	 * @param pageNo
	 * @return
	 * @throws SQLException
	 */
	public List<ProblemInfo> findRecommendProblem(int pageNo) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ProblemInfo> list = new ArrayList<ProblemInfo>();
		try {
			stmt = conn.prepareStatement(
					"SELECT p.`prob_id`,p.`prob_cate_id`,p.`prob_title`,p.`prob_level`,p.`score`,p.`content`,"
							+ "p.`pic_id`,p.`pic_file_name`,p.`pic_file_type`,p.`status`,p.`submit_user_id`,p.`submit_time`,"
							+ "u.`alias` "
							+ "FROM problem_info p INNER JOIN user_info u ON p.`submit_user_id`=u.`user_id` "
							+ "WHERE p.`status`=? and p.score>20 ORDER BY p.score DESC LIMIT ?,?");

			stmt.setShort(1, (short) ProblemStatus.UNRESOLVED.getValue());
			stmt.setInt(2, (pageNo - 1) * Constants.PAGE_SIZE);
			stmt.setInt(3, Constants.PAGE_SIZE);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(convert2Info(rs));
			}
		} finally {
			DBUtils.close(rs, stmt);
		}
		return list;
	}
}
