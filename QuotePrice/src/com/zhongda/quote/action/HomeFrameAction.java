package com.zhongda.quote.action;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.zhongda.quote.view.CreateTaskDialog;
import com.zhongda.quote.view.CreateProjectFrame;

/**
 * 
 * <p>
 * HomeFrame事件类
 * </p>
 * 
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月10日
 */
public class HomeFrameAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// 如果command为创建任务，则打开创建任务窗口
		if ("createTask".equals(command)) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						CreateTaskDialog window = new CreateTaskDialog();
						window.jDialog.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else if ("creatProject".equals(command)) {
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
}
