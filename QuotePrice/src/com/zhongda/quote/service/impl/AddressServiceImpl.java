package com.zhongda.quote.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zhongda.quote.dao.AddressMapper;
import com.zhongda.quote.model.Address;
import com.zhongda.quote.service.AddressService;
import com.zhongda.quote.utils.MyBatisUtil;

public class AddressServiceImpl implements AddressService {

	private static Logger logger = Logger.
			getLogger(AddressServiceImpl.class);

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
	public List<Address> queryAllCityOrCountyByParent(Integer id) {
		List<Address> addressList = null;
		try{
			addressList = addressMapper.selectAllCityOrCountyByParent(id);
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			MyBatisUtil.closeSqlSession();
		}
		return addressList;
	}

	@Override
	public Map<String, List<Address>> queryAllProvinceAndCityCountyByParent() {
		Address address = null;
		Map<String, List<Address>> addressMap = null;
		try{
			addressMap = new HashMap<String, List<Address>>();
			//获取所有的省
			List<Address> provinceList = addressMapper.selectAllProvince();
			addressMap.put("provinceList", provinceList);
			//获取第一个省
			address = provinceList.get(0);
			//获取第一个省下所有的市
			List<Address> cityList = addressMapper.selectAllCityOrCountyByParent(address.getId());
			addressMap.put("cityList", cityList);
			//获取第一个市
			address = cityList.get(0);
			//获取第一个市下所有的区或县
			List<Address> countyList = addressMapper.selectAllCityOrCountyByParent(address.getId());
			addressMap.put("countyList", countyList);

		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			MyBatisUtil.closeSqlSession();
		}
		return addressMap;
	}

	@Override
	public Address selectAddressByInspectionID(Integer InspectionID) {
		Address address = null;
		try {
			address = addressMapper
					.selectAddressByInspectionID(InspectionID);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return address;
	}

}
