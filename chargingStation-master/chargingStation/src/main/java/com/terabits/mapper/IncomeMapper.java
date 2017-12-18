package com.terabits.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.event.PublicInvocationEvent;

import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.ConsumerecordPO;
import com.terabits.meta.po.DevicePO;
import com.terabits.meta.po.SitePO;
import com.terabits.meta.po.TerminalstatisticPO;
import com.terabits.meta.po.TestPO;

public interface IncomeMapper {
	/*
	* 测试PO,当数据库中的字段数量和PO中的字段数量不一致时，通过select *能否自动注入到PO
	*/
	public List<TestPO> selectTestPOByName(@Param("name") String name) ; 
	/*
	* 通过城市（city）查询区域
	*/
	public List<AdminPO> selectAdminPOByCity(@Param("city") String city);
	/*
	* 通过区域查询网点
	*/
	public List<SitePO> selectSitePOByAdminphone(@Param("adminname") String adminname); 
	/*
	 * 通过城市查询网点
	 */
	public List<SitePO> selectSitePOByCity(@Param("city") String city);
	/*
	 * 通过网点查询设备
	 */
	public List<DevicePO> selectDevicePOBySite(@Param("siteid") int siteid);
	/*
	 * 通过设备和时间查询消费记录
	 */
	public List<ConsumerecordPO> selectConsumerecordPOByDeviceid(@Param("deviceid") String deviceid,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
	/*
	 * 通过imei查询DevicePO
	 */
	public DevicePO selectDevicePOByImei(@Param("imei") String imei);
	/*
	 * 通过deviceid查询DevicePO
	 */
	public DevicePO selectDevicePOByDeviceid(@Param("deviceid") String deviceid);
	/*
	 * 通过网点编号siteId查询网点
	 */
	public SitePO selectSitePOBySiteId(@Param("siteId") int siteId);
	/*
	 * 通过名称Mark查询网点
	 */
	public SitePO selectSitePOByMark(@Param("mark") String mark);
	/*
	 * 通过adminName查询admin
	 */
	public AdminPO selectAdminPOByAdminName(@Param("adminName") String adminName);
	/*
	 * 查询一个设备某一天的记录
	 */
	public List<TerminalstatisticPO> selectStatisticPOByImei(@Param("imei") String imei,
													   	     @Param("sitename") String sitename,
													   	     @Param("adminname") String adminname,
													   	     @Param("city") String city,
													   	     @Param("beginTime") String beginTime,//date是日期
															 @Param("endTime") String endTime);
	/*
	 * 查询一个设备某一天的记录
	 */
	public List<TerminalstatisticPO> selectStatisticPOBySite(@Param("sitename") String sitename,
													   	     @Param("adminname") String adminname,
													   	     @Param("city") String city,
													   	     @Param("beginTime") String beginTime,//date是日期
															 @Param("endTime") String endTime);
	/*
	 * 查询一个设备某一天的记录
	 */
	public List<TerminalstatisticPO> selectStatisticPOByAdmin(@Param("adminname") String adminname,
													   	      @Param("city") String city,
													   	      @Param("beginTime") String beginTime,//date是日期
															  @Param("endTime") String endTime);
	/*
	 * 查询一个设备某一天的记录
	 */
	public List<TerminalstatisticPO> selectStatisticPOByCity( @Param("city") String city,
													   	      @Param("beginTime") String beginTime,//date是日期
															  @Param("endTime") String endTime);
	/*
	 * 查询一个设备当天的记录是否存在
	 */
	public TerminalstatisticPO selectStatisticPOByImeiAndDate(@Param("imei") String imei,@Param("date") String date);
	/*
	 * 查询所有激活的设备
	 */
	public List<DevicePO> selectAllDevice();
	/*
	 * 在statistic表中插入一条记录
	 */
	public int insertStatisticPO(TerminalstatisticPO terminalstatisticPO);
	/*
	 * 在statistic表中更细记录
	 */
	public int updateStatisticPO(TerminalstatisticPO terminalstatisticPO);
}
