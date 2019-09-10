package com.yueqian.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.domain.CategoryInfo;

public class CategoryDao {
	private static CategoryDao instance;

	public synchronized static CategoryDao getInstance() {
		if (instance == null) {
			instance = new CategoryDao();
		}
		return instance;
	}

	private CategoryDao() {

	}

	/**
	 * 获取所有问题类别列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<CategoryInfo> findAllCategories() throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CategoryInfo> list = new ArrayList<CategoryInfo>();
		try {
			stmt = conn.prepareStatement("SELECT c.`cate_id`,c.`cate_name` FROM category_info c");
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(convert2Info(rs));
			}
		}finally {
			DBUtils.close(rs, stmt);
		}
		return list;
	}

	private CategoryInfo convert2Info(ResultSet rs) throws SQLException {
		CategoryInfo info = new CategoryInfo();
		info.setCategoryId(rs.getInt("cate_id"));
		info.setCategoryName(rs.getString("cate_name"));
		return info;
	}
}
