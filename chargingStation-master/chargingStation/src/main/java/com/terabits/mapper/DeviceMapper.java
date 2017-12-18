package com.terabits.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.terabits.meta.bo.BoxConnectionBO;
import com.terabits.meta.bo.SiteInsertBO;
import com.terabits.meta.vo.BoxConnectionTendaysVO;
import com.terabits.meta.vo.BoxConnectionVO;
import com.terabits.meta.vo.BoxInfoVO;
import com.terabits.meta.vo.CityAreaStationVO;
import com.terabits.meta.vo.DeviceVO;

public interface DeviceMapper {
	/**
	 * 获取网点设备信息：所属城市，所属区域，区域负责人手机号，网点，网点地址
	 * @param city
	 * @param area
	 * @param station
	 * @param type
	 * @param name
	 * @return
	 */
	public List<DeviceVO> selectDeviceInfo(@Param("city") String city, @Param("area") String area, @Param("station") String station, @Param("type") int type, @Param("name") String name);
	
	/**
	 * 增加网点
	 * @param deviceInsertBO
	 * @return
	 */
	public int insertSite(SiteInsertBO deviceInsertBO);
	
	/**
	 * 网点设备列表
	 * @param station
	 * @return
	 */
	public List<String> selectBoxSiteList(@Param("station") String station);
	
	/**
	 * 查询设备基本信息
	 * @param boxConnectionBO
	 * @return
	 */
	public List<BoxInfoVO> selectBoxInfo(BoxConnectionBO boxConnectionBO);
	/**
	 * 设备连接性查询
	 * @param boxConnectionBO
	 * @return
	 */
	public List<BoxConnectionVO> selectBoxConnection(@Param("imei") String imei, @Param("station") String station, @Param("beginTime") String beginTime, @Param("endTime") String endTime);	
	
	/**
	 * 近十天设备连接性查询
	 * @param boxConnectionTendaysBO
	 * @return
	 */
	public List<BoxConnectionTendaysVO> selectBoxConnectionTendays(BoxConnectionBO boxConnectionBO);
	
	
	/**
	 * 查询某一区域负责人管理的所有网点
	 * @param area
	 * @return
	 */
	public List<String> selectAllStationByArea(@Param("phone") String phone);
	
	/**
	 * 查询所有未分配网点
	 * @return
	 */
	public List<String> selectAllStationNoArea();
	
	/**
	 * 登录时查询城市，区域，网点信息
	 * @param type
	 * @param name
	 * @return
	 */
	public List<CityAreaStationVO> selectCityAreaStation(@Param("type") int type, @Param("name") String name);
	
	/**
	 * 验证网点是否属于该用户管理
	 * @param station
	 * @param type
	 * @param name
	 * @return
	 */
	public int selectStationCheckAdmin(@Param("station") String station, @Param("type") int type, @Param("name") String name);
	
	/**
	 * 验证该网点下的设备是否解绑
	 * @param station
	 * @param type
	 * @param name
	 * @return
	 */
	public int selectStationCheckDevice(@Param("station") String station, @Param("type") int type, @Param("name") String name);
	
	/**
	 * 删除网点
	 * @param station
	 * @return
	 */
	public int deleteStation(@Param("station") String station);
}
