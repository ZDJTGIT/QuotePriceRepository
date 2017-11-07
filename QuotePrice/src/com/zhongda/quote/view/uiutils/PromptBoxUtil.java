package com.zhongda.quote.view.uiutils;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * 
 * <p>提示框工具</p>
 * @author rojay<1250368725@qq.com>
 * @since 2017年11月6日
 */
public class PromptBoxUtil {
	
	/**
	 * 
	 * @param title 标题
	 * @param strdata 提示内容
	 */
	public static void getPromptBox(String title, String strdata){
		JOptionPane jOptionPane = new JOptionPane(strdata,JOptionPane.INFORMATION_MESSAGE);
		final JDialog jDialog = jOptionPane.createDialog(title);
		//创建一个定时器
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				jDialog.setVisible(false);
				jDialog.dispose();
			}
		}, 1000);
		jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jDialog.setAlwaysOnTop(true);
		jDialog.setModal(false);
		jDialog.setVisible(true);
	}
	


}
