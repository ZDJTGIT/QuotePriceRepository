package com.zhongda.quote.machine;

/**
 *
 * <p>生成机器码的通用接口，不同平台有不同实现 。</p>
 * @author rojay<1250368725@qq.com>
 * @since 2017年8月11日
 */
public interface SequenceService {
	/**
	 * 获取机器码
	 * @return 机器码
 	 */
    public String getSequence();  
}
