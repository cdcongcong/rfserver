package com.tongwei.util;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * 测试
 * 
 * @author xx
 * 
 */
public class CallTest {

	public void run() {
		String url = "http://10.109.200.51:8080/cqjsrf/services/RfInterface";
//		String url = "http://localhost:9000/cqjsrfwbs/services/RfInterface";
		Service service = null;
		Call call = null;
		try {
			service = new Service();
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName("executeRfFunction");// 要调用的方法名
			Object o = call.invoke(new Object[] { "XXMFG_RF_GET_RF_VERSION","" });
			System.out.println(o);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {

		new CallTest().run();
	}

}
