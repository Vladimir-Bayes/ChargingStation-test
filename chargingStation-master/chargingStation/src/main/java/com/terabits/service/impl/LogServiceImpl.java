package com.terabits.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terabits.dao.LogDAO;
import com.terabits.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired 
	private LogDAO logDAO;
	
	public int insertLog(String name, int type, String text) {
		return logDAO.insertLog(name, type, text);
	}

}
