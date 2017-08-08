package com.zhongda.quote.utils;

import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * <p>获取本机的机器码。</p>
 * @author rojay<1250368725@qq.com>
 * @since 2017年8月8日
 */
public class GetMachineUtil {
	/**
	 * 获取到机器码的值
	 * @return 机器码
	 */
	public static String getMachineLanguage(){
		 String serial = null;
		 String property =null;
		 Scanner sc = null;
		 try {
			Process process = Runtime.getRuntime().exec(  
				        new String[] { "wmic", "cpu", "get", "ProcessorId" });
			process.getOutputStream().close();
			sc = new Scanner(process.getInputStream());  
			property = sc.next(); 
	        serial = sc.next();  
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			sc.close();
		}	
		return serial;	
	}

}
