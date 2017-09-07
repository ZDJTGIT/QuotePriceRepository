package com.zhongda.quote.machine.impl;

import java.security.MessageDigest;

import com.zhongda.quote.machine.SequenceService;
import com.zhongda.quote.utils.StringUtil;

/**
 * 
 * <p>获取到机器码。</p>
 * @author rojay<1250368725@qq.com>
 * @since 2017年8月11日
 */
public abstract class AbstractSequenceService implements SequenceService {
	/**
	 * 对一段String生成MD5摘要信息
	 * 
	 * @param message
	 *            要摘要的String
	 * @return 生成的MD5摘要信息
	 */
	protected String getMD5(String message) {
		message += "{apdplat}";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(message.getBytes("utf-8"));
			String md5 = StringUtil.bytesToHexString(b);
			return md5;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
