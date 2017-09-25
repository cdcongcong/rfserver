package com.tongwei.wbs;

import com.tongwei.bpo.RfInterfaceBPO;

/**
 * RF执行的webservice
 * 
 * @author xx
 * 
 */
public class RfInterface {

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
		return new RfInterfaceBPO().executeRfFunction(funname, paras);
	}
	
	public String executeRfFunctionByStringArray(String funname, String[] paras)
			throws Exception {
		return new RfInterfaceBPO().executeRfFunctionByStringArray(funname, paras);
	}
	
	public String executeRfFunctionByObjectArray(String funname, Object[] paras)
			throws Exception {
		return new RfInterfaceBPO().executeRfFunctionByObjectArray(funname, paras);
	}

	public String executeRfFunctionByJson(String funname, String paras)
			throws Exception {
		return new RfInterfaceBPO().executeRfFunctionByJson(funname, paras);
	}

}
