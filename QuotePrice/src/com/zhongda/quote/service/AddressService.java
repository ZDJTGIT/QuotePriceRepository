package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.Address;

public interface AddressService {

	/**
	 * 查询所有的省
	 * @return
	 */
	List<Address> queryAllProvince();

	/**
	 * 通过省的id查询出在该省下面所有的市
	 * @param id 省份的id
	 * @return
	 */
	List<Address> queryAllCityByProvince(Integer id);

	/**
	 * 通过市的id查询出在该市下面所有的县(区)
	 * @param id 市的id
	 * @return
	 */
	List<Address> queryAllCountyByCity(Integer id);
}
