package com.tongwei.db;

/**
 *
 * @author xx
 * @version 1.0
 */
import java.sql.*;

public class DbConn {
	private Connection conn = null;

	private ConnectionManager dcm = null;

	void init() {
		dcm = ConnectionManager.getInstance();
		conn = dcm.getConnection();
	}

	/**
	 * 构造数据库的连接和访问类
	 */
	public DbConn() throws Exception {
		init();
	}

	/**
	 * 返回连接
	 * 
	 * @return Connection 连接
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			if (conn != null) {
				dcm.freeConnection(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
