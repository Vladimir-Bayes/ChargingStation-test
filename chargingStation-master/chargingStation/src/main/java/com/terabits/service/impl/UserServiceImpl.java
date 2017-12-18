package com.terabits.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terabits.dao.LogDAO;
import com.terabits.dao.UserDAO;
import com.terabits.meta.po.UserPO;
import com.terabits.meta.vo.ConsumeVO;
import com.terabits.meta.vo.ConsumesVO;
import com.terabits.meta.vo.RechargeVO;
import com.terabits.meta.vo.RechargesVO;
import com.terabits.service.UserService;
import com.terabits.utils.RandomTools;

import net.sf.json.JSONArray;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LogDAO logDAO;
	
	
	public long getItemsCount(String tablename, String beginTime, String endTime, String phone) {
		// TODO Auto-generated method stub
		try {
			return userDAO.itemsCount(tablename, beginTime, endTime, phone);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	public JSONArray getRecharges(String beginTime, String endTime, String phone, int pagesize, int offset){
		List<RechargesVO> rechargesVOs = new ArrayList<RechargesVO>();
		List<RechargeVO> rechargeVOs = new ArrayList<RechargeVO>();
		double money = 0;

		try {
			rechargesVOs = userDAO.selectRecharge(beginTime, endTime, phone, pagesize, offset);
			for (int i = 0; i < rechargesVOs.size(); i++) {
				RechargeVO rechargeVO = new RechargeVO();
				rechargeVO.setPhone(rechargesVOs.get(i).getPhone());
				rechargeVO.setTime(rechargesVOs.get(i).getTime());
				money = rechargesVOs.get(i).getPayment()+rechargesVOs.get(i).getPresent();
				rechargeVO.setMoney((int) money);
				rechargeVOs.add(rechargeVO);
			}		
			
			JSONArray jsonArray = JSONArray.fromObject(rechargeVOs);
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONArray getConsumes(String beginTime, String endTime, String phone, int pagesize, int offset){
		List<ConsumesVO> consumesVOs = new ArrayList<ConsumesVO>();
		List<ConsumeVO> consumeVOs = new ArrayList<ConsumeVO>();
		double money = 0;
		try {
			consumesVOs = userDAO.selectConsume(beginTime, endTime, phone, pagesize, offset);
			for (int i = 0; i < consumesVOs.size(); i++) {
				ConsumeVO consumeVO = new ConsumeVO();
				consumeVO.setBoxId(consumesVOs.get(i).getBoxId());
				consumeVO.setPhone(consumesVOs.get(i).getPhone());
				consumeVO.setTime(consumesVOs.get(i).getTime());
				consumeVO.setStation(consumesVOs.get(i).getStation());
				money = consumesVOs.get(i).getMoney();
				consumeVO.setMoney((int) money);
				consumeVOs.add(consumeVO);
			}			
			JSONArray jsonArray = JSONArray.fromObject(consumeVOs);
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public int insertPresent(String phone, int present, String comments, String adminname){
		List<UserPO> userPOs = new ArrayList<UserPO>();
		String user_openid = "";
		double user_recharge = 0;
		double user_present = 0;
		double prebalance = 0;
		double postbalance = 0;
		String nowtime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String orderid = "CPCZ";
		try {
			userPOs = userDAO.selectUserPOInfo(phone);
			user_openid = userPOs.get(0).getOpenid();
			user_recharge = userPOs.get(0).getRecharge();
			user_present = userPOs.get(0).getPresent();
			
			orderid = orderid+nowtime+RandomTools.getFixLenthString(3);			
			prebalance = user_present+user_recharge;
			postbalance = prebalance+present;
			userDAO.insertPresent(user_openid, phone, present, prebalance, postbalance, orderid);
			userDAO.updateUserPresent(present+user_present, phone);
			logDAO.insertLog(adminname, 4, comments);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 2;		
	}

	public JSONArray getUserInfo(String phone) {
		List<UserPO> userPOs = new ArrayList<UserPO>();
		try {
			userPOs = userDAO.selectUserPOInfo(phone);
			JSONArray jsonArray = JSONArray.fromObject(userPOs);
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	
	
}
