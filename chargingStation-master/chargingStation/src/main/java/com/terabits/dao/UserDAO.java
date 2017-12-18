package com.terabits.dao;


import java.util.List;

import com.terabits.meta.po.UserPO;
import com.terabits.meta.vo.ConsumesVO;
import com.terabits.meta.vo.RechargesVO;
import com.terabits.meta.vo.UserVO;


public interface UserDAO {
	
	public long itemsCount(String tablename, String beginTime, String endTime, String phone) throws Exception;
	
	public List<RechargesVO> selectRecharge(String beginTime, String endTime, String phone, int pagesize, int offset) throws Exception;

	public List<ConsumesVO> selectConsume(String beginTime, String endTime, String phone, int pagesize, int offset) throws Exception;
	
	public int insertPresent(String openid, String phone, double present, double prebalance, double postbalance, String orderid) throws Exception;
	
	public List<UserVO> selectUserInfo(String phone) throws Exception;
	
	public List<UserPO> selectUserPOInfo(String phone) throws Exception;

	public int updateUserPresent(double present, String phone) throws Exception;

}
