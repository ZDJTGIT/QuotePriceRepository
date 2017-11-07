package com.zhongda.quote.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *<p>生成流水号的工具类</p>
 * @author zmdeng
 * @date 2017年8月9日
 */
public class PrimaryGeneraterUtil {

    /**
     * 生成下一个编号
     * @return
     */
    public static String getNextNumber(String primaryNumber) {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        
       if (null == primaryNumber || "".equals(primaryNumber)) {
           id = "ZD" + formatter.format(date) + "0001";
       } else {
           DecimalFormat df = new DecimalFormat("0000");
           id = "ZD" + formatter.format(date)
                   + df.format(1 + Integer.parseInt(primaryNumber.substring(10, 14)));
       }
        return id;
    }
}