package com.terabits.dao;

public interface LogDAO {
	/**
	 * 添加日志
	 * @param name
	 * @param type
	 * @return
	 */
	public int insertLog(String name, int type, String text);
}
