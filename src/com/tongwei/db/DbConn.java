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
	 * �������ݿ�����Ӻͷ�����
	 */
	public DbConn() throws Exception {
		init();
	}

	/**
	 * ��������
	 * 
	 * @return Connection ����
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * �ر�����
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