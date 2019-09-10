package com.yueqian.faq.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static BasicDataSource ds;
	private static ThreadLocal<Connection> tl = new ThreadLocal<>();
	static {
		
		 ds = new BasicDataSource();
		 ds.setDriverClassName("com.mysql.jdbc.Driver");
		 ds.setUrl("jdbc:mysql:///faq?useUnicode=true&characterEncoding=utf8&useSSL=false"); 
		 ds.setUsername("chen"); 
		 ds.setPassword("123456");
		 ds.setMaxActive(20); ds.setMinIdle(1); ds.setMaxIdle(4);
		 ds.setMaxWait(4000); ds.setDefaultAutoCommit(false);
	}

	/**
	 * ��ȡ���ݿ�����
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		// ���ȴ�ThreadLocal�����л�ȡ��ǰ�̰߳󶨵����Ӷ���
		Connection conn = tl.get();
		if (conn == null) {
			try {
				conn = ds.getConnection();
				tl.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static void close(ResultSet rs, Statement stmt) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeDataSource() {
		System.out.println("�ر�����Դ.");
		/*
		 * if (ds != null) { try { ds.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 */
	}

	public static void commit() {
		Connection conn = tl.get();
		if (conn != null) {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			tl.remove();
		}
	}

	public static void rollback() {
		Connection conn = tl.get();
		if (conn != null) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			tl.remove();
		}
	}
}