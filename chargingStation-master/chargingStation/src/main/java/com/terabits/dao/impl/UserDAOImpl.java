package com.terabits.dao.impl;


import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.terabits.dao.UserDAO;
import com.terabits.mapper.UserMapper;
import com.terabits.meta.po.UserPO;
import com.terabits.meta.vo.ConsumesVO;
import com.terabits.meta.vo.RechargesVO;
import com.terabits.meta.vo.UserVO;
import com.terabits.utils.DBTools;


@Repository("userDAO")

public class UserDAOImpl implements UserDAO {
	
	public long itemsCount(String tablename, String beginTime, String endTime, String phone) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		int status=0;
		long itemnum=0;
		if (tablename.equals("t_charge_rechargerecord")) {
			status = 11;
		} else if (tablename.equals("t_charge_consumerecord")) {
			status = 66;
		}
		try {
			itemnum = mapper.itemsCount(tablename, beginTime, endTime, phone, status);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return itemnum;
	}
	
	public List<RechargesVO> selectRecharge(String beginTime, String endTime, String phone, int pagesize, int offset) throws Exception {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<RechargesVO> rechargesVOs = new ArrayList<RechargesVO>();
		int status=11;
		String tablename = "t_charge_rechargerecord";
		try {
			rechargesVOs = mapper.selectRecharge(beginTime, endTime, phone, tablename, pagesize, offset, status);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return rechargesVOs;
	}	
	
	public List<ConsumesVO> selectConsume(String beginTime, String endTime, String phone, int pagesize, int offset) throws Exception {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<ConsumesVO> consumptionVos = new ArrayList<ConsumesVO>();
		int status=66;
		String tablename = "t_charge_consumerecord";
		try {
			consumptionVos = mapper.selectConsume(beginTime, endTime, phone, tablename, pagesize, offset, status);
			session.commit();						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return consumptionVos;
	}
	
	public int insertPresent(String openid, String phone, double present, double prebalance, double postbalance, String orderid) throws Exception {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
			mapper.insertPresent(openid, phone, present, prebalance, postbalance, orderid);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
			return 0;
		}
		return 1;
	}
	
	public List<UserVO> selectUserInfo(String phone) throws Exception{
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<UserVO> userVOs = new ArrayList<UserVO>();
		try {
			userVOs = mapper.selectUserInfo(phone);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return userVOs;
	}
	
	public List<UserPO> selectUserPOInfo(String phone) throws Exception {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<UserPO> userPOs = new ArrayList<UserPO>();
		try {
			userPOs = mapper.selectUserPOInfo(phone);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return userPOs;
	}

	public int updateUserPresent(double present, String phone) throws Exception {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
			mapper.updateUserPresent(present, phone);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	

}
