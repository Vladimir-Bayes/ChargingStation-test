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
	* ����PO,�����ݿ��е��ֶ�������PO�е��ֶ�������һ��ʱ��ͨ��select *�ܷ��Զ�ע�뵽PO
	*/
	public List<TestPO> selectTestPOByName(@Param("name") String name) ; 
	/*
	* ͨ�����У�city����ѯ����
	*/
	public List<AdminPO> selectAdminPOByCity(@Param("city") String city);
	/*
	* ͨ�������ѯ����
	*/
	public List<SitePO> selectSitePOByAdminphone(@Param("adminname") String adminname); 
	/*
	 * ͨ�����в�ѯ����
	 */
	public List<SitePO> selectSitePOByCity(@Param("city") String city);
	/*
	 * ͨ�������ѯ�豸
	 */
	public List<DevicePO> selectDevicePOBySite(@Param("siteid") int siteid);
	/*
	 * ͨ���豸��ʱ���ѯ���Ѽ�¼
	 */
	public List<ConsumerecordPO> selectConsumerecordPOByDeviceid(@Param("deviceid") String deviceid,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
	/*
	 * ͨ��imei��ѯDevicePO
	 */
	public DevicePO selectDevicePOByImei(@Param("imei") String imei);
	/*
	 * ͨ��deviceid��ѯDevicePO
	 */
	public DevicePO selectDevicePOByDeviceid(@Param("deviceid") String deviceid);
	/*
	 * ͨ��������siteId��ѯ����
	 */
	public SitePO selectSitePOBySiteId(@Param("siteId") int siteId);
	/*
	 * ͨ������Mark��ѯ����
	 */
	public SitePO selectSitePOByMark(@Param("mark") String mark);
	/*
	 * ͨ��adminName��ѯadmin
	 */
	public AdminPO selectAdminPOByAdminName(@Param("adminName") String adminName);
	/*
	 * ��ѯһ���豸ĳһ��ļ�¼
	 */
	public List<TerminalstatisticPO> selectStatisticPOByImei(@Param("imei") String imei,
													   	     @Param("sitename") String sitename,
													   	     @Param("adminname") String adminname,
													   	     @Param("city") String city,
													   	     @Param("beginTime") String beginTime,//date������
															 @Param("endTime") String endTime);
	/*
	 * ��ѯһ���豸ĳһ��ļ�¼
	 */
	public List<TerminalstatisticPO> selectStatisticPOBySite(@Param("sitename") String sitename,
													   	     @Param("adminname") String adminname,
													   	     @Param("city") String city,
													   	     @Param("beginTime") String beginTime,//date������
															 @Param("endTime") String endTime);
	/*
	 * ��ѯһ���豸ĳһ��ļ�¼
	 */
	public List<TerminalstatisticPO> selectStatisticPOByAdmin(@Param("adminname") String adminname,
													   	      @Param("city") String city,
													   	      @Param("beginTime") String beginTime,//date������
															  @Param("endTime") String endTime);
	/*
	 * ��ѯһ���豸ĳһ��ļ�¼
	 */
	public List<TerminalstatisticPO> selectStatisticPOByCity( @Param("city") String city,
													   	      @Param("beginTime") String beginTime,//date������
															  @Param("endTime") String endTime);
	/*
	 * ��ѯһ���豸����ļ�¼�Ƿ����
	 */
	public TerminalstatisticPO selectStatisticPOByImeiAndDate(@Param("imei") String imei,@Param("date") String date);
	/*
	 * ��ѯ���м�����豸
	 */
	public List<DevicePO> selectAllDevice();
	/*
	 * ��statistic���в���һ����¼
	 */
	public int insertStatisticPO(TerminalstatisticPO terminalstatisticPO);
	/*
	 * ��statistic���и�ϸ��¼
	 */
	public int updateStatisticPO(TerminalstatisticPO terminalstatisticPO);
}
