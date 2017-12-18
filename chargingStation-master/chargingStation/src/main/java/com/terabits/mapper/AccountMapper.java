package com.terabits.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.terabits.meta.bo.AccountAreaInfoBO;
import com.terabits.meta.bo.AccountCityInfoBO;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.vo.AccountAreaInfoVO;
import com.terabits.meta.vo.AccountCityInfoVO;

public interface AccountMapper {
	/**
	 * 城市人员查询
	 * @param accountCityInfoBO
	 * @return
	 */
	public List<AccountCityInfoVO> selectAccountCityInfo(AccountCityInfoBO accountCityInfoBO);
	
	/**
	 * 添加城市人员
	 * @param adminPO
	 * @return
	 */
	public int insertAccountCity(AdminPO adminPO);
	
	/**
	 * 删除城市人员
	 * @param phone
	 * @return
	 */
	public int deleteAccountCity(@Param("phone") String phone);
	
	/**
	 * 修改城市人员信息
	 * @param adminPO
	 * @return
	 */
	public int updateAccountCity(AdminPO adminPO);
	
	/**
	 * 区域负责人查询
	 * @param accountAreaInfoBO
	 * @return
	 */
	public List<AccountAreaInfoVO> selectAccountAreaInfo(AccountAreaInfoBO accountAreaInfoBO);
	
	/**
	 * 修改区域负责人信息
	 * @param adminPO
	 * @return
	 */
	public int updateAccountArea(AdminPO adminPO);
	
	/**
	 * 添加区域负责人网点
	 * @param phone
	 * @param station
	 * @return
	 */
	public int insertAccountAreaSite(@Param("phone") String phone, @Param("station") String station);
	
	/**
	 * 删除负责人网点
	 * @param phone
	 * @param station
	 * @return
	 */
	public int deleteAccountAreaSite(@Param("phone") String phone, @Param("station") String station);
	
	/**
	 * 添加区域负责人
	 * @param adminPO
	 * @return
	 */
	public int insertAccountArea(AdminPO adminPO);
	
	/**
	 * 删除区域负责人
	 * @param phone
	 * @return
	 */
	public int deleteAccountArea(@Param("phone") String phone);
}
