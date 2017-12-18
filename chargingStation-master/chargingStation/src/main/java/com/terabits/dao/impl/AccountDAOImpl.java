package com.terabits.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.terabits.dao.AccountDAO;
import com.terabits.mapper.AccountMapper;
import com.terabits.meta.bo.AccountAreaInfoBO;
import com.terabits.meta.bo.AccountCityInfoBO;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.vo.AccountAreaInfoVO;
import com.terabits.meta.vo.AccountCityInfoVO;
import com.terabits.utils.DBTools;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO{

	public List<AccountCityInfoVO> selectAccountCityInfo(AccountCityInfoBO accountCityInfoBO) {
		SqlSession session = DBTools.getSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        List<AccountCityInfoVO> accountCityInfoVOs = null;
        try {
        	accountCityInfoVOs = mapper.selectAccountCityInfo(accountCityInfoBO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return accountCityInfoVOs;
	}

	public int insertAccountCityInfo(AdminPO adminPO) {
		SqlSession session = DBTools.getSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        try {
        	mapper.insertAccountCity(adminPO);
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

	public int deleteAccountCityInfo(String phone) {
		SqlSession session = DBTools.getSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        try {
        	mapper.deleteAccountCity(phone);
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

	public int updateAccountCityInfo(AdminPO adminPO) {
		SqlSession session = DBTools.getSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        try {
        	mapper.updateAccountCity(adminPO);
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

	public List<AccountAreaInfoVO> selectAccountAreaInfo(AccountAreaInfoBO accountAreaInfoBO) {
		SqlSession session = DBTools.getSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        List<AccountAreaInfoVO> accountAreaInfoVOs = null;
        try {
        	accountAreaInfoVOs = mapper.selectAccountAreaInfo(accountAreaInfoBO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return accountAreaInfoVOs;
	}

	public int updateAccountArea(AdminPO adminPO) {
		SqlSession session = DBTools.getSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        try {
        	mapper.updateAccountArea(adminPO);
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

	public int insertAccountAreaSite(String phone, String station) {
		SqlSession session = DBTools.getSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        try {
        	mapper.insertAccountAreaSite(phone, station);
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

	public int deleteAccountAreaSite(String phone, String station) {
		SqlSession session = DBTools.getSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        try {
        	mapper.deleteAccountAreaSite(phone, station);
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

	public int insertAccountArea(AdminPO adminPO) {
		SqlSession session = DBTools.getSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        try {
        	mapper.insertAccountArea(adminPO);
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

	public int deleteAccountArea(String phone) {
		SqlSession session = DBTools.getSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        try {
        	mapper.deleteAccountArea(phone);
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

}
