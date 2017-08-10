package com.zhongda.quote.action;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.zhongda.quote.view.CreateTaskDialog;

/**
 *
 *<p>主界面相关监听处理类</p>
 * @author zmdeng
 * @date 2017年8月9日
 */
public class HomeFrameAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		//如果command为创建任务，则打开创建任务窗口
		if("createTask".equals(command)){
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
		}
	}

}
