package com.zhongda.quote.utils;
/**
 * 
 * <p>解码值。</p>
 * @author rojay<1250368725@qq.com>
 * @since 2017年8月8日
 */
public class MachineKeyUtil {
	/**
	 * 对机器码进行与机器码算法匹配
	 * @param machineSerial
	 * @return
	 */
	public static String getMachineKey(String machineSerial){
		char[] subSerialKey = machineSerial.substring(6,machineSerial.length()-1).toCharArray();
        String serialKey = "";
        for(int i = 0;i<subSerialKey.length;i++){
        	serialKey += (subSerialKey[i]^5|7>>>13);
        }
        serialKey = serialKey.substring(serialKey.length()-7, serialKey.length()-1);
		return serialKey;
	}
	
}
