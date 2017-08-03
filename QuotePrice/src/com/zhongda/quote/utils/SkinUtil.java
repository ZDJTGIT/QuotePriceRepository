package com.zhongda.quote.utils;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

/**
 * 
 * 界面样式工具类
 * @author LiVerson
 * @time 2017-8-3 13:20:01
 *
 */

public class SkinUtil {

	/**
	 * 
	 * @param ben
	 *        界面样式
	 */
	public static void setSkin(FrameBorderStyle ben){
		BeautyEyeLNFHelper.frameBorderStyle = ben;
		BeautyEyeLNFHelper.debug = true;
		BeautyEyeLNFHelper.translucencyAtFrameInactive =false;
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
