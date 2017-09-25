package com.tongwei.wbs;

import com.tongwei.bpo.RfInterfaceBPO;

/**
 * RFִ�е�webservice
 * 
 * @author xx
 * 
 */
public class RfInterface {

	/**
	 * �������ݿ⺯��
	 * 
	 * @param funname
	 *            ������
	 * @param paras
	 *            ������Ҫ�Ĳ���
	 * @return ���غ���ִ�н��
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
