package com.zhongda.quote.utils;
/**
 *
 * <p>字符串处理。</p>
 * @author rojay<1250368725@qq.com>
 * @since 2017年8月12日
 */
public class StringUtil {

	/**
	 * 转换成十六进制的字符串并且小写转大写
	 *
	 * @param src
	 * @return 十六进制的字符串
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			hv = hv.toUpperCase();
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	/*
	 * 字符串转数字
	 */
	public static Integer stringToInteger(String string){
		return Integer.parseInt(string);
	}

	/**
	 * 将字符串以“-”分割，在转换成整数型
	 * @param range
	 * @return 整形数组
	 */
	public static int[] rangeToMinMax(String range){
		String[] string = range.split("-");
		int[] array = new int[]{Integer.parseInt(string[0]), Integer.parseInt(string[1])};
		return array;
	}

	/*
	 * 判断是不是数字
	 */
	public static boolean isNumeric(String str){
		try{
			Integer.parseInt(str);
		}catch(NumberFormatException ne){
			return false;
		}
		return true;
	}
}
