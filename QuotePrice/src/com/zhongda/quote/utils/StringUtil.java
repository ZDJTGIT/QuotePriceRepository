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
	/*
	 * 将数据库取得的取值范围转换为int类型
	 * @return 取值范围最小值
	 */
	public static Integer stringToMinInteger(String string){
		String minString = null;
		if(string.length()==3){
			minString = string.substring(0, 1);
		}else if(string.length()==4){
			minString = string.substring(0, 1);
		}else if (string.length()==5) {
			minString = string.substring(0, 2);
		}
		return Integer.parseInt(minString);
	}

	/*
	 * 将数据库取得的取值范围转换为int类型
	 * @return 取值范围最大值
	 */
	public static Integer stringToMaxInteger(String string){
		String maxString = null;
		if(string.length()==3){
			maxString = string.substring(2, string.length());
		}else if(string.length()==4){
			maxString = string.substring(2, string.length());
		}else if (string.length()>=5) {
			maxString = string.substring(3, string.length());
		}
		return Integer.parseInt(maxString);
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
