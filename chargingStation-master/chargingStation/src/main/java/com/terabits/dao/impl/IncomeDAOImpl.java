package com.terabits.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.terabits.dao.IncomeDAO;
import com.terabits.mapper.IncomeMapper;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.ConsumerecordPO;
import com.terabits.meta.po.DevicePO;
import com.terabits.meta.po.SitePO;
import com.terabits.meta.po.TerminalstatisticPO;
import com.terabits.meta.po.TestPO;
import com.terabits.utils.DBTools;
 
 @Repository("incomeDAO")
 public class IncomeDAOImpl implements IncomeDAO {
 
	public List<TestPO> selectTestPOByName(String name){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        List<TestPO> testPOs=null;
        try {
			testPOs=mapper.selectTestPOByName(name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
        return testPOs;
	}
	
	public List<AdminPO> selectAdminPOByCity(String city){
		SqlSession session = DBTools.getSession();
		IncomeMapper mapper = session.getMapper(IncomeMapper.class);
		List<AdminPO> list=null;
		try {
			list= mapper.selectAdminPOByCity(city);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	public List<SitePO> selectSitePOByAdminphone(String adminname){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        List<SitePO> sitePOs=null;
        try {
			sitePOs=mapper.selectSitePOByAdminphone(adminname);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return sitePOs;
	}
	
	public List<SitePO> selectSitePOByCity(String city){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        List<SitePO> sitePOs=null;
        try {
			sitePOs=mapper.selectSitePOByCity(city);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return sitePOs;
	}
	
	public List<DevicePO> selectDevicePOBySite(int siteid){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        List<DevicePO> devicePOs=null;
        try {
			devicePOs=mapper.selectDevicePOBySite(siteid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
        return devicePOs;
	}
	
	public List<ConsumerecordPO> selectConsumerecordPOByDeviceid(String deviceid,String beginTime,String endTime){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        List<ConsumerecordPO> consumerecordPOs=null;
        try {
			consumerecordPOs=mapper.selectConsumerecordPOByDeviceid(deviceid, beginTime, endTime);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return consumerecordPOs;
	}
	
	public DevicePO selectDevicePOByImei(String imei){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        DevicePO devicePO=new DevicePO();
        try {
			devicePO= mapper.selectDevicePOByImei(imei);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
        return devicePO;
	}
	
	public DevicePO selectDevicePOByDeviceid(String deviceid){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        DevicePO devicePO=new DevicePO();
        try {
			devicePO= mapper.selectDevicePOByDeviceid(deviceid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
        return devicePO;
	}
	
	public SitePO selectSitePOBySiteId(int siteId){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        SitePO sitePO=new SitePO();
        try {
			sitePO=mapper.selectSitePOBySiteId(siteId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
        return sitePO;
	}
	
	public SitePO selectSitePOByMark(String mark){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        SitePO sitePO=new SitePO();
        try {
			sitePO=mapper.selectSitePOByMark(mark);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
        return sitePO;
	}
	
	public AdminPO selectAdminPOByAdminName(String adminName){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        AdminPO adminPO=new AdminPO();
        try {
			adminPO=mapper.selectAdminPOByAdminName(adminName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
        return adminPO;
	}
	
	public List<TerminalstatisticPO> selectStatisticPOByImei(String imei,String sitename,String adminname,String city,String beginTime,String endTime){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        List<TerminalstatisticPO> terminalstatisticPOs=null;
        try {
        	terminalstatisticPOs=mapper.selectStatisticPOByImei(imei, sitename, adminname, city, beginTime,endTime);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return terminalstatisticPOs;
	}
	
	public List<TerminalstatisticPO> selectStatisticPOBySite(String sitename,String adminname,String city,String beginTime,String endTime){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        List<TerminalstatisticPO> terminalstatisticPOs=null;
        try {
        	terminalstatisticPOs=mapper.selectStatisticPOBySite(sitename, adminname, city, beginTime, endTime);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return terminalstatisticPOs;
	}
	
	public List<TerminalstatisticPO> selectStatisticPOByAdmin(String adminname,String city,String beginTime,String endTime){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        List<TerminalstatisticPO> terminalstatisticPOs=null;
        try {
        	terminalstatisticPOs=mapper.selectStatisticPOByAdmin(adminname, city, beginTime, endTime);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return terminalstatisticPOs;
	}
	
	public List<TerminalstatisticPO> selectStatisticPOByCity(String city,String beginTime,String endTime){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        List<TerminalstatisticPO> terminalstatisticPOs=null;
        try {
        	terminalstatisticPOs=mapper.selectStatisticPOByCity(city, beginTime, endTime);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return terminalstatisticPOs;
	}
	
	public TerminalstatisticPO selectStatisticPOByImeiAndDate(String imei,String date){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        TerminalstatisticPO terminalstatisticPO=new TerminalstatisticPO();
        try {
			terminalstatisticPO=mapper.selectStatisticPOByImeiAndDate(imei, date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return terminalstatisticPO;
	}
	
	public List<DevicePO> selectAllDevice(){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        List<DevicePO> devicePOs=null;
        try {
			devicePOs=mapper.selectAllDevice();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return devicePOs;
	}
	
	public int insertStatisticPO(TerminalstatisticPO terminalstatisticPO){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        int result=0;
        try {
			result=mapper.insertStatisticPO(terminalstatisticPO);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return result;
	}
	
	public int updateStatisticPO(TerminalstatisticPO terminalstatisticPO){
		SqlSession session = DBTools.getSession();
        IncomeMapper mapper = session.getMapper(IncomeMapper.class);
        int result=0;
        try {
			result=mapper.updateStatisticPO(terminalstatisticPO);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
        return result;
	}
 }