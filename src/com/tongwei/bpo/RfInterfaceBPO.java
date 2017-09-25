package com.tongwei.bpo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tongwei.db.DbConn;

/**
 * RF 程序通过webservice 调用数据库方法
 * 
 * @author xx
 * 
 */
public class RfInterfaceBPO {
	private static Logger logger = Logger.getLogger(RfInterfaceBPO.class);

	/**
	 * 调用数据库函数
	 * 
	 * @param funname
	 *            函数名
	 * @param paras
	 *            过程需要的参数
	 * @return 返回函数执行结果
	 * @throws Exception
	 */
	public String executeRfFunction(String funname, String paras)
			throws Exception {
		// 跟踪
		logger.debug(paras);
		String[] ps = paras.equals("") ? new String[0] : paras.split(",", -1);
		return executeRfFunctionByStringArray(funname, ps);
	}

	public String executeRfFunctionByStringArray(String funname, String[] paras)
			throws Exception {
		return executeRfFunctionByObjectArray(funname, paras);
		// DbConn db = null;
		// Connection conn = null;
		// CallableStatement cst = null;
		// // String ps[] =paras.equals("")?new String[0]: paras.split(",");
		// //跟踪
		// logger.debug("paras:");
		// for (int i = 0; i < paras.length; i++) {
		// logger.debug(paras[i]);
		// }
		//
		// String msg = "0";
		// try {
		// db = new DbConn();
		// conn = db.getConnection();
		// conn.setAutoCommit(false);
		// int l = paras.length;
		// // 组合过程参数
		// String sql = "{?=call " + funname + "(";
		// for (int i = 0; i < l; i++) {
		// sql += "?,";
		// }
		// if (sql.endsWith(","))
		// sql = sql.substring(0, sql.length() - 1);
		// sql += ")}";
		// cst = conn.prepareCall(sql);
		// // 设置过程参数
		// for (int i = 0; i < l; i++) {
		// if (paras[i].equals("")) {
		// cst.setNull((i + 2), Types.VARCHAR);
		// } else {
		// cst.setString((i + 2), paras[i]);
		// }
		// }
		// cst.registerOutParameter(1, Types.VARCHAR);
		//
		// cst.execute();
		// msg = cst.getString(1);
		// conn.commit();
		// return msg;
		// } catch (Exception e) {
		// conn.rollback();
		// e.printStackTrace();
		//
		// return "{\"success\":false,\"message\":\""+e.toString()+"!\"}";
		// } finally {
		// try {
		// if (cst != null)
		// cst.close();
		// } catch (Exception e) {
		// }
		// try {
		// if (conn != null) {
		// conn.close();
		// }
		// if (db != null)
		// db.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
	}

	public String executeRfFunctionByJson(String funname, String paras)
			throws Exception {
		// 跟踪
		logger.debug(paras);
		throw new Exception("未实现");
		// String ps[] =paras.equals("")?new String[0]: paras.split(",");
		// return executeRfFunctionByStringArray(funname, ps);
	}

	public String executeRfFunctionByObjectArray(String funname, Object[] paras)
			throws Exception {
		DbConn db = null;
		Connection conn = null;
		CallableStatement cst = null;
		// String ps[] =paras.equals("")?new String[0]: paras.split(",");
		// 跟踪
		logger.debug("paras:" + paras.length);
		for (int i = 0; i < paras.length; i++) {
			logger.debug(paras[i]);
		}

		String msg = "0";
		try {
			db = new DbConn();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			int l = paras.length;
			// 组合过程参数
			String sql = "{?=call " + funname + "(";
			for (int i = 0; i < l; i++) {
				sql += "?,";
			}
			if (sql.endsWith(","))
				sql = sql.substring(0, sql.length() - 1);
			sql += ")}";
			cst = conn.prepareCall(sql);
			// 设置过程参数
			for (int i = 0; i < l; i++) {
				if (paras[i].equals("")) {
					cst.setNull((i + 2), Types.VARCHAR);
				} else {
					cst.setObject((i + 2), paras[i]);
				}
			}
			cst.registerOutParameter(1, Types.VARCHAR);

			cst.execute();
			msg = cst.getString(1);
			conn.commit();
			return msg;
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

			return "{\"success\":false,\"message\":\"" + e.toString() + "!\"}";
		} finally {
			try {
				if (cst != null)
					cst.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
				if (db != null)
					db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	public String executeRfFunctionByMap(String funname, Map<String, Object> paras)
			throws Exception {
		DbConn db = null;
		Connection conn = null;
		CallableStatement cst = null;
		// 跟踪
		logger.debug("paras:");
        for(Map.Entry<String,Object> entry : paras.entrySet()){  
            logger.debug(entry.getKey() + "=" + entry.getValue());  
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }  		

		String msg = "0";
		try {
			db = new DbConn();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			int l = paras.size();
			// 组合过程参数
			String sql = "{?=call " + funname + "(";
	        for(Map.Entry<String,Object> entry : paras.entrySet()){
	        	sql += ":" + entry.getKey() + ",";
	        }
			if (sql.endsWith(","))
				sql = sql.substring(0, sql.length() - 1);
	        
//			for (int i = 0; i < l; i++) {
//				if (i!=0){sql +=",";}
//				sql += "?";
//			}
			sql += ")}";

			System.out.println(sql);

			sql = "{?=call cux_apps_mobile_inv.get_app_version(?)}";
			cst = conn.prepareCall(sql);
//			System.out.println(cst.getParameterMetaData());
			
			// 设置过程参数
			System.out.println(2);
			
	        for(Map.Entry<String,Object> entry : paras.entrySet()){  
				if (entry.getValue().equals("")) {
					System.out.println(3);
						cst.setNull(entry.getKey(), Types.VARCHAR);
			} else {
				System.out.println(4);
	            System.out.println(entry.getKey() + "=" + entry.getValue());
//				cst.setObject(entry.getKey(), entry.getValue());
				System.out.println(5);
				
				cst.setString("p_app_code", "MobileEBS");
			}
	        }  		
	        System.out.println(5);
			
			cst.registerOutParameter(1, Types.VARCHAR);

			cst.execute();
			msg = cst.getString(1);
			conn.commit();
			return msg;
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

			return "{\"success\":false,\"message\":\"" + e.toString() + "!\"}";
		} finally {
			try {
				if (cst != null)
					cst.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
				if (db != null)
					db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {

//		Object[] o = new Object[1];
//		o[0] = "MobileEBS";
//		String string;
//		RfInterfaceBPO bpo = new RfInterfaceBPO();
//		string = bpo.executeRfFunctionByObjectArray(
//				"cux_apps_mobile_inv.get_app_version", o);
//		System.out.println(string);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("p_app_code", "MobileEBS");
//		string = bpo.executeRfFunctionByMap("cux_apps_mobile_inv.get_app_version", map);
//		System.out.println(string);
		String s = "a,b,c,,,";
		String[] ps = s.equals("") ? new String[0] : s.split(",",-1);
		System.out.println(ps.length);
	}
}
