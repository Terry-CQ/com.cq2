package com.yueqian.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.common.EnglishLevel;
import com.yueqian.faq.common.JobStatus;
import com.yueqian.faq.domain.StatisticsInfo;
import com.yueqian.faq.domain.UserInfo;

public class UserDao {

	private static UserDao instance;

	public synchronized static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	private UserDao() {

	}

	/**
	 * 根据账号查询用户信息
	 * 
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public UserInfo findInfoByAccount(String account) throws SQLException {
		Connection conn = DBUtils.getConnection();
		UserInfo info = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT u.`user_id`,u.`user_account`,u.`alias`,"
					+ "u.`user_pwd`,u.`college`,u.`company`,u.`dob`,u.`email`,"
					+ "u.`idcard`,u.`jobStatus`,u.`level`,u.`major`,u.`mobile`," + "u.`qq`,u.`score` "
					+ "FROM user_info u " + "WHERE u.`user_account`=?");
			stmt.setString(1, account);
			rs = stmt.executeQuery();

			if (rs.next()) {
				info = convert2Info(rs);
			}

		} finally {
			DBUtils.close(rs, stmt);
		}
		return info;
	}

	private UserInfo convert2Info2(ResultSet rs) throws SQLException {
		UserInfo info = new UserInfo();
		info.setUserId(rs.getInt("user_id"));
		info.setAlias(rs.getString("alias"));
		if (rs.getObject("score") != null)
			info.setScore(rs.getInt("score"));
		return info;
	}

	private UserInfo convert2Info(ResultSet rs) throws SQLException {
		UserInfo info = new UserInfo();
		info.setUserId(rs.getInt("user_id"));
		info.setAccount(rs.getString("user_account"));
		info.setAlias(rs.getString("alias"));
		info.setPwd(rs.getString("user_pwd"));
		info.setCollege(rs.getString("college"));
		info.setCpmpany(rs.getString("company"));
		info.setDob(rs.getDate("dob"));
		info.setEmial(rs.getString("email"));
		info.setIdcard(rs.getString("idcard"));

		if (rs.getObject("jobStatus") != null) {
			byte job = rs.getByte("jobStatus");
			info.setJobStatus(JobStatus.from(job));
		}

		if (rs.getObject("level") != null) {
			byte level = rs.getByte("level");
			info.setEnglishLevel(EnglishLevel.from(level));
		}
		info.setMajor(rs.getString("major"));
		info.setMobile(rs.getString("mobile"));
		info.setQq(rs.getString("qq"));

		if (rs.getObject("score") != null)
			info.setScore(rs.getInt("score"));
		return info;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param userInfo
	 * @return
	 * @throws SQLException
	 */
	public int saveInfo(UserInfo userInfo) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement("INSERT INTO user_info(user_account,alias,user_pwd,"
					+ "qq,email,mobile,college,major,idcard,dob,LEVEL,jobStatus,company,score) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,0)");
			stmt.setString(1, userInfo.getAccount());
			stmt.setString(2, userInfo.getAlias());
			stmt.setString(3, userInfo.getPwd());
			stmt.setString(4, userInfo.getQq());
			stmt.setString(5, userInfo.getEmial());
			stmt.setString(6, userInfo.getMobile());
			stmt.setString(7, userInfo.getCollege());
			stmt.setString(8, userInfo.getMajor());
			stmt.setString(9, userInfo.getIdcard());

			if (userInfo.getDob() != null) {
				stmt.setDate(10, new java.sql.Date(userInfo.getDob().getTime()));
			} else {
				stmt.setNull(10, Types.DATE);
			}
			if (userInfo.getEnglishLevel() != null) {
				stmt.setInt(11, userInfo.getEnglishLevel().getValue());
			} else {
				stmt.setNull(11, Types.SMALLINT);
			}
			if (userInfo.getJobStatus() != null) {
				stmt.setInt(12, userInfo.getJobStatus().getValue());
			} else {
				stmt.setNull(12, Types.SMALLINT);
			}
			stmt.setString(13, userInfo.getCpmpany());
			count = stmt.executeUpdate();
		} finally {
			DBUtils.close(null, stmt);
		}
		return count;
	}

	/**
	 * 根据回复编号，给回复者加上问题的悬赏分
	 * 
	 * @param replyId
	 * @return 影响的记录条数
	 * @throws SQLException
	 */
	public int addScore(Integer replyId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement("UPDATE user_info " + "SET score=IFNULL(score,0)+(SELECT p.`score` "
					+ "FROM problem_info p    " + " WHERE p.`prob_id`=( " + "SELECT r.`prob_id` " + "FROM reply_info r "
					+ "WHERE r.`reply_id`=? " + "        )         " + " ) " + "WHERE user_id=( "
					+ "SELECT r.`reply_user_id` " + "FROM reply_info r " + "WHERE r.`reply_id`=? " + " )");
			stmt.setInt(1, replyId);
			stmt.setInt(2, replyId);
			count = stmt.executeUpdate();
		} finally {
			DBUtils.close(null, stmt);
		}
		return count;
	}

	/**
	 * 根据积分查找前几名用户的信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<UserInfo> findUserByScoresDesc() throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UserInfo> list = new ArrayList<UserInfo>();
		try {
			stmt = conn.prepareStatement("SELECT u.`user_id`,u.`alias`,u.`score` " + "FROM user_info u "
					+ "ORDER BY u.`score` DESC " + "LIMIT 0,4");
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(convert2Info2(rs));
			}

		} finally {
			DBUtils.close(rs, stmt);
		}
		return list;
	}

	public void countUserByQuestion(StatisticsInfo info) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 设置提问最多的用户
			stmt = conn.prepareStatement(
					"SELECT temp.submit_user_id,u.alias FROM   (SELECT p.`submit_user_id`,COUNT(p.`prob_id`) c "
							+ "FROM problem_info p    GROUP BY p.`submit_user_id`) temp    INNER JOIN user_info u ON temp.submit_user_id=u.user_id "
							+ "WHERE temp.c=( 	SELECT MAX(t.c)	FROM (SELECT COUNT(p.`prob_id`) c         FROM problem_info p         GROUP BY p.`submit_user_id`         ) t)");
			rs = stmt.executeQuery();
			if (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt("submit_user_id"));
				userInfo.setAlias(rs.getString("alias"));
				info.setMostQuestionUser(userInfo);
			}
			DBUtils.close(rs, stmt);
			// 回复被采纳最多的用户
			stmt = conn.prepareStatement(
					"SELECT t.reply_user_id,u.`alias` FROM (   SELECT r.`reply_user_id`,COUNT(r.`reply_id`) c "
							+ "FROM reply_info r   WHERE r.`is_best_reply`=1   GROUP BY r.`reply_user_id`) t   INNER JOIN user_info u ON t.reply_user_id=u.`user_id` "
							+ "WHERE t.c=( 	SELECT MAX(t1.c) 	FROM (           SELECT r.`reply_user_id`,COUNT(r.`reply_id`) c           FROM reply_info r "
							+ "WHERE r.`is_best_reply`=1           GROUP BY r.`reply_user_id`		) t1 )");
			rs = stmt.executeQuery();
			if (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt("reply_user_id"));
				userInfo.setAlias(rs.getString("alias"));
				info.setMostReplyUser(userInfo);
			}
		} finally {
			DBUtils.close(rs, stmt);
		}

	}

}
