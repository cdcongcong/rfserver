package com.tongwei.db;

import java.sql.*;

import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

/**
 * proxool 连接池
 * 
 * @author 谢昕
 * 
 */
public class ConnectionManager {
	static private ConnectionManager instance;

	static synchronized public ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	private ConnectionManager() {
		this.init();
	}

	public void freeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			//单独测试用（非web环境中）
//			JAXPConfigurator.configure("E:/workspace/cqjsrfwbs/web/WEB-INF/proxool.xml", false);
			connection = DriverManager.getConnection("proxool.tynDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (connection != null) {
			return connection;
		} else {
			return null;
		}

	}

	public void release() {
	}

	private void init() {
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
