package com.terabits.service;

public interface LogService {
	/**
	 * 添加日志
	 * @param name
	 * @param type
	 * @return
	 */
	public int insertLog(String name, int type, String text);
}
