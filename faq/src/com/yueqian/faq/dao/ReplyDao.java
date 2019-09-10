package com.yueqian.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.domain.ReplyInfo;
import com.yueqian.faq.domain.UserInfo;

public class ReplyDao {
	private static ReplyDao instance;

	public synchronized static ReplyDao getInstance() {
		if (instance == null) {
			instance = new ReplyDao();
		}
		return instance;
	}

	private ReplyDao() {

	}

	/**
	 * 根据问题编号获取该问题的所有回复
	 * 
	 * @param probId
	 * @return
	 * @throws SQLException
	 */
	public List<ReplyInfo> findRepliesByProbId(Integer probId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		List<ReplyInfo> list = new ArrayList<ReplyInfo>();
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT r.`reply_id`,r.`reply_user_id`,r.`prob_id`, "
					+ " r.`content`,r.`pic_id`,r.`file_type`,r.`reply_time`,r.`is_best_reply`,"
					+ " u.`alias` FROM reply_info r INNER JOIN user_info u ON r.`reply_user_id`=u.`user_id` "
					+ "WHERE r.`prob_id`=? " + "ORDER BY r.`is_best_reply` DESC,r.`reply_time` DESC");
			stmt.setInt(1, probId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(convert2Info(rs));
			}
		} finally {
			DBUtils.close(rs, stmt);
		}
		return list;
	}

	private ReplyInfo convert2Info(ResultSet rs) throws SQLException {
		ReplyInfo info = new ReplyInfo();
		info.setReplyId(rs.getInt("reply_id"));
		info.setReplyUserId(rs.getInt("reply_user_id"));
		info.setProblemId(rs.getInt("prob_id"));
		info.setContent(rs.getString("content"));
		info.setPicId(rs.getString("pic_id"));
		info.setFileType(rs.getString("file_type"));
		info.setReplyTime(new java.util.Date(rs.getTimestamp("reply_time").getTime()));
		if (rs.getInt("is_best_reply") == 1) {
			info.setBestReply(true);
		} else {
			info.setBestReply(false);
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(info.getReplyId());
		userInfo.setAlias(rs.getString("alias"));
		info.setReplyUser(userInfo);
		return info;
	}

	/**
	 * 保存回复
	 * 
	 * @param info
	 * @return
	 * @throws SQLException
	 */
	public int saveReplyInfo(ReplyInfo info) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement(
					"INSERT INTO reply_info(prob_id,reply_user_id,content,reply_time,pic_id,file_type,is_best_reply) "
							+ "VALUES(?,?,?,?,?,?,?)");
			stmt.setInt(1, info.getProblemId());
			stmt.setInt(2, info.getReplyUserId());
			stmt.setString(3, info.getContent());
			stmt.setTimestamp(4, new java.sql.Timestamp(info.getReplyTime().getTime()));
			if (info.getPicId() != null) {
				stmt.setString(5, info.getPicId());
			} else {
				stmt.setNull(5, Types.VARCHAR);
			}
			if (info.getFileType() != null) {
				stmt.setString(6, info.getFileType());
			} else {
				stmt.setNull(6, Types.VARCHAR);
			}
			if (info.isBestReply()) {
				stmt.setShort(7, (short) 1);
			} else {
				stmt.setShort(7, (short) 0);
			}
			count = stmt.executeUpdate();
		} finally {
			DBUtils.close(null, stmt);
		}
		return count;
	}

	/**
	 * 根据图片ID获取回复信息
	 * 
	 * @param fileId
	 * @return
	 * @throws SQLException
	 */
	public ReplyInfo findInfoByFileId(String fileId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ReplyInfo info = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT r.`reply_id`,r.`reply_user_id`,r.`prob_id`, "
					+ " r.`content`,r.`pic_id`,r.`file_type`,r.`reply_time`,r.`is_best_reply`,"
					+ " u.`alias` FROM reply_info r INNER JOIN user_info u ON r.`reply_user_id`=u.`user_id` "
					+ "WHERE r.pic_id=?");
			stmt.setString(1, fileId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				info = this.convert2Info(rs);
			}
		} finally {
			DBUtils.close(rs, stmt);
		}
		return info;
	}

	/**
	 * 将回复设置为最佳回复
	 * 
	 * @param replyId
	 * @return
	 * @throws SQLException
	 */
	public int acceptReply(Integer replyId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement(
					"UPDATE reply_info SET is_best_reply=1 " + "WHERE reply_id=? AND is_best_reply=0");
			stmt.setInt(1, replyId);
			count = stmt.executeUpdate();
		} finally {
			DBUtils.close(null, stmt);
		}
		return count;
	}

	/**
	 * 根据回复编号获取回复信息
	 * 
	 * @param replyId
	 * @return
	 * @throws SQLException
	 */
	public ReplyInfo findInfoByReplytId(Integer replyId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ReplyInfo info = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT r.`reply_id`,r.`reply_user_id`,r.`prob_id`, "
					+ " r.`content`,r.`pic_id`,r.`file_type`,r.`reply_time`,r.`is_best_reply`,"
					+ " u.`alias` FROM reply_info r INNER JOIN user_info u ON r.`reply_user_id`=u.`user_id` "
					+ "WHERE r.reply_id=?");
			stmt.setInt(1, replyId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				info = this.convert2Info(rs);
			}
		} finally {
			DBUtils.close(rs, stmt);
		}
		return info;
	}
}
