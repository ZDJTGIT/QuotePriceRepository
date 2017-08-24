package com.zhongda.quote.dao;

import java.util.List;

import com.zhongda.quote.model.Address;

public interface AddressMapper {

    Address selectByPrimaryKey(Integer id);

    /**
	 * 查询所有的省
	 * @return
	 */
	List<Address> selectAllProvince();

	/**
	 * 通过省(市)的id查询出在该省(市)下面所有的市(区或县)
	 * @param id 省(市)的id
	 * @return
	 */
	List<Address> selectAllCityOrCountyByParent(Integer id);
	/**
	 * 传入检验内容ID查所在地址
	 * @param InspectionID
	 * @return
	 */
	Address selectAddressByInspectionID(Integer InspectionID);

}