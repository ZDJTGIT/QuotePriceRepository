package com.zhongda.quote.service;

import java.util.List;
import java.util.Map;

import com.zhongda.quote.model.Address;

public interface AddressService {

	/**
	 * 查询所有的省
	 * @return
	 */
	List<Address> queryAllProvince();

	/**
	 * 通过省(市)的id查询出在该省(市)下面所有的市(区或县)
	 * @param id 省(市)的id
	 * @return
	 */
	List<Address> queryAllCityOrCountyByParent(Integer id);

	/**
	 * h获取所有的省，以及默认省(第一个省)下所有的市，同时根据获取到的默认的市(第一个市)获取该市下所有的区或县
	 * @return
	 */
	Map<String, List<Address>> queryAllProvinceAndCityCountyByParent();
}
