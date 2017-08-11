package com.zhongda.quote.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 *<p>生成流水号的工具类</p>
 * @author zmdeng
 * @date 2017年8月9日
 */
public class PrimaryGeneraterUtil {

	// 流水号格式
    private static String primaryNumber = "ZD201708090001";
    private static PrimaryGeneraterUtil primaryGenerater = null;
    private static  Properties prop = new Properties();

    private PrimaryGeneraterUtil() {
    }

   /**
    * 取得PrimaryGenerater的单例实现
    * @return
    */
    public static PrimaryGeneraterUtil getInstance() {
        if (primaryGenerater == null) {
            synchronized (PrimaryGeneraterUtil.class) {
                if (primaryGenerater == null) {
                    primaryGenerater = new PrimaryGeneraterUtil();
                }
            }
        }
        return primaryGenerater;
    }

    /**
     * 生成下一个编号
     * @return
     */
    public synchronized String getNextNumber() {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        InputStream is = null;
    	try {
    		//加载properties文件
    		is = new FileInputStream("src/config.properties");
			prop.load(is);
			primaryNumber = prop.getProperty("primaryNumber");
	        if (null == primaryNumber || "".equals(primaryNumber)) {
	            id = formatter.format(date) + "0001";
	        } else {
	            DecimalFormat df = new DecimalFormat("0000");
	            id = "ZD" + formatter.format(date)
	                    + df.format(1 + Integer.parseInt(primaryNumber.substring(10, 14)));
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null != is){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return id;
    }

    /**
     *	保存下一个编号
     * @param id
     * @param prop
     */
    public synchronized void saveNextNumber(String id){
    	OutputStream os = null;
		try {
			os = new FileOutputStream("src/config.properties");
			prop.setProperty("primaryNumber", id);
			prop.store(os, "流水号");
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null != os){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}