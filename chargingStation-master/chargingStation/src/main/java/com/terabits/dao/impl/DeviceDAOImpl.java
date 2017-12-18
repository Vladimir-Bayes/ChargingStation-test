package com.terabits.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.terabits.dao.DeviceDAO;
import com.terabits.mapper.DeviceMapper;
import com.terabits.meta.bo.BoxConnectionBO;
import com.terabits.meta.bo.SiteInsertBO;
import com.terabits.meta.vo.BoxConnectionTendaysVO;
import com.terabits.meta.vo.BoxConnectionVO;
import com.terabits.meta.vo.BoxInfoVO;
import com.terabits.meta.vo.CityAreaStationVO;
import com.terabits.meta.vo.DeviceVO;
import com.terabits.utils.DBTools;

@Repository("deviceDAO")
public class DeviceDAOImpl implements DeviceDAO {

	public List<DeviceVO> selectDeviceInfo(String city, String area, String station, int type, String name) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<DeviceVO> deviceVOs = null;
        try {
        	deviceVOs = mapper.selectDeviceInfo(city, area, station, type, name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return deviceVOs;
	}

	public int insertSite(SiteInsertBO deviceInsertBO) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        try {
        	mapper.insertSite(deviceInsertBO);
        	session.commit();
        	return 200;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return 400;
        } finally {
        	session.close();
        }
	}

	public List<String> selectBoxSiteList(String station) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<String> boxIds = null;
        try {
        	boxIds = mapper.selectBoxSiteList(station);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return boxIds;
	}
	
	public List<BoxInfoVO> selectBoxInfo(BoxConnectionBO boxConnectionBO) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<BoxInfoVO> boxInfoVOs = null;
        try {
        	boxInfoVOs = mapper.selectBoxInfo(boxConnectionBO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return boxInfoVOs;
		
	}

	public List<BoxConnectionVO> selectBoxConnection(String imei, String station, String beginTime, String endTime) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<BoxConnectionVO> boxConnectionVOs = null;
        try {
        	boxConnectionVOs = mapper.selectBoxConnection(imei, station, beginTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return boxConnectionVOs;
	}

	public List<BoxConnectionTendaysVO> selectBoxConnectionTendays(BoxConnectionBO boxConnectionBO) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<BoxConnectionTendaysVO> boxConnectionTendaysVOs = null;
        try {
        	boxConnectionTendaysVOs = mapper.selectBoxConnectionTendays(boxConnectionBO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return boxConnectionTendaysVOs;
	}

	public List<String> selectAllStationByArea(String phone) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<String> stationS = null;
        try {
        	stationS = mapper.selectAllStationByArea(phone);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return stationS;
	}

	public List<String> selectAllStationNoArea() {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<String> stationS = null;
        try {
        	stationS = mapper.selectAllStationNoArea();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return stationS;
	}

	public List<CityAreaStationVO> selectCityAreaStation(int type, String name) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        List<CityAreaStationVO> cityAreaStationVOs = null;
        try {
        	cityAreaStationVOs = mapper.selectCityAreaStation(type, name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return cityAreaStationVOs;
	}

	public int selectStationCheckAdmin(String station, int type, String name) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        int count = -1;
        try {
        	count = mapper.selectStationCheckAdmin(station, type, name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return count;
	}

	public int selectStationCheckDevice(String station, int type, String name) {
		SqlSession session = DBTools.getSession();
        DeviceMapper mapper = session.getMapper(DeviceMapper.class);
        int count = -1;
        try {
        	count = mapper.selectStationCheckDevice(station, type, name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return count;
	}

	public int deleteStation(String station) {
		// TODO Auto-generated method stub
		return 0;
	}

}
