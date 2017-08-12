package com.zhongda.quote.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.zhongda.quote.dao.AddressMapper;
import com.zhongda.quote.model.Address;
import com.zhongda.quote.service.AddressService;
import com.zhongda.quote.utils.MyBatisUtil;

public class AddressServiceImpl implements AddressService {

	private static Logger logger = Logger.getLogger(AddressServiceImpl.class);

	private AddressMapper addressMapper = MyBatisUtil.getSqlSession().getMapper(AddressMapper.class);

	@Override
	public List<Address> queryAllProvince() {
		List<Address> addressList = null;
		try{
			addressList = addressMapper.selectAllProvince();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			MyBatisUtil.closeSqlSession();
		}
		return addressList;
	}

	@Override
	public List<Address> queryAllCityByProvince(Integer id) {
		List<Address> addressList = null;
		try{
			addressList = addressMapper.selectAllCityByProvince(id);
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			MyBatisUtil.closeSqlSession();
		}
		return addressList;
	}

	@Override
	public List<Address> queryAllCountyByCity(Integer id) {
		List<Address> addressList = null;
		try{
			addressList = addressMapper.selectAllCountyByCity(id);
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			MyBatisUtil.closeSqlSession();
		}
		return addressList;
	}

}
