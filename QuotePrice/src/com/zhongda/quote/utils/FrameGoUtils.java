package com.zhongda.quote.utils;

import java.awt.EventQueue;

import javax.swing.JTable;

import com.zhongda.quote.view.CreateProjectFrame;
import com.zhongda.quote.view.CreateTaskDialog;

/**
 * 
 * <p>
 * 窗口启动类
 * </p>
 * 
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月11日
 */
public class FrameGoUtils {

	/**
	 * 创建任务窗口
	 * 
	 * @param jt_quoteTask
	 *            任务面板JTable对象
	 */
	public static void creatTask(JTable jt_quoteTask) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTaskDialog window = new CreateTaskDialog(jt_quoteTask);
					window.jDialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 创建项目启动窗口
	 */
	public static void creatProject() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProjectFrame createP = new CreateProjectFrame();
					createP.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}