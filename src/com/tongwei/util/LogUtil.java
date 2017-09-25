package com.tongwei.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * 对Log4j进行封装，提供常用调试方法，2009-03-25
 * 
 * @author xx
 * 
 */
public class LogUtil {
	private static String CNAME = LogUtil.class.getName();// 本类名称

	private static Logger logger;

	// 私有构造方法
	private LogUtil(Class clazz) {
		logger = LogManager.getLogger(LogUtil.class);
	}

	// 返回一个LogUtil对象
	public static LogUtil getLogger(Class clazz) {
		return new LogUtil(clazz);
	}

	// 调试
	public void logDebug(Object message) {
		logger.log(CNAME, Priority.DEBUG, message, null);
	}

	// 信息
	public void logInfo(Object message) {
		logger.log(CNAME, Priority.INFO, message, null);
	}

	// 警告
	public void logWarn(Object message) {
		logger.log(CNAME, Priority.WARN, message, null);
	}

	// 错误
	public void logError(Object message) {
		logger.log(CNAME, Priority.ERROR, message, null);
	}

	// 致命错误
	public void logFatal(Object message) {
		logger.log(CNAME, Priority.FATAL, message, null);
	}
}
