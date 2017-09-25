package com.tongwei.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * ��Log4j���з�װ���ṩ���õ��Է�����2009-03-25
 * 
 * @author xx
 * 
 */
public class LogUtil {
	private static String CNAME = LogUtil.class.getName();// ��������

	private static Logger logger;

	// ˽�й��췽��
	private LogUtil(Class clazz) {
		logger = LogManager.getLogger(LogUtil.class);
	}

	// ����һ��LogUtil����
	public static LogUtil getLogger(Class clazz) {
		return new LogUtil(clazz);
	}

	// ����
	public void logDebug(Object message) {
		logger.log(CNAME, Priority.DEBUG, message, null);
	}

	// ��Ϣ
	public void logInfo(Object message) {
		logger.log(CNAME, Priority.INFO, message, null);
	}

	// ����
	public void logWarn(Object message) {
		logger.log(CNAME, Priority.WARN, message, null);
	}

	// ����
	public void logError(Object message) {
		logger.log(CNAME, Priority.ERROR, message, null);
	}

	// ��������
	public void logFatal(Object message) {
		logger.log(CNAME, Priority.FATAL, message, null);
	}
}
