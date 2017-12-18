package com.terabits.dao;

import java.util.List;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.ConsumerecordPO;
import com.terabits.meta.po.DevicePO;
import com.terabits.meta.po.SitePO;
import com.terabits.meta.po.TerminalstatisticPO;
import com.terabits.meta.po.TestPO;

 public interface IncomeDAO {
	public List<TestPO> selectTestPOByName(String name);
	public List<AdminPO> selectAdminPOByCity(String city);
	public List<SitePO> selectSitePOByAdminphone(String adminname);
	public List<SitePO> selectSitePOByCity(String city);
	public List<DevicePO> selectDevicePOBySite(int siteid);
	public List<ConsumerecordPO> selectConsumerecordPOByDeviceid(String deviceid,String beginTime,String endTime);
	public DevicePO selectDevicePOByImei(String imei);
	public DevicePO selectDevicePOByDeviceid(String deviceid);
	public SitePO selectSitePOBySiteId(int siteId);
	public SitePO selectSitePOByMark(String mark);
	public AdminPO selectAdminPOByAdminName(String adminName);
	public List<TerminalstatisticPO> selectStatisticPOByImei(String imei,String sitename ,String adminname,String city,String beginTime,String endTime);//date是日期
	public List<TerminalstatisticPO> selectStatisticPOBySite(String sitename,String adminname,String city,String beginTime,String endTime);//date是日期
	public List<TerminalstatisticPO> selectStatisticPOByAdmin(String adminname,String city,String beginTime,String endTime);//date是日期
	public List<TerminalstatisticPO> selectStatisticPOByCity(String city,String beginTime,String endTime);//date是日期
	public TerminalstatisticPO selectStatisticPOByImeiAndDate(String imei,String date);
	public List<DevicePO> selectAllDevice();
	public int insertStatisticPO(TerminalstatisticPO terminalstatisticPO);
	public int updateStatisticPO(TerminalstatisticPO terminalstatisticPO);
 }