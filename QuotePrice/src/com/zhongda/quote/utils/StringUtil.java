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

}
