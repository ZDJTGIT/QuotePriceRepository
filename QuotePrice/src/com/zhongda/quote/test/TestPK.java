package com.zhongda.quote.test;

import com.zhongda.quote.utils.PrimaryGeneraterUtil;

public class TestPK {

	public static void main(String[] args) {

		PrimaryGeneraterUtil primaryGenerater = PrimaryGeneraterUtil.getInstance();
		String nextNumber = primaryGenerater.getNextNumber();
		System.out.println(nextNumber);
		//primaryGenerater.saveNextNumber(nextNumber);
	}

}
