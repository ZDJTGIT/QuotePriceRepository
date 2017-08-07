package com.zhongda.quote.action;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;

import com.zhongda.quote.view.HomeFrame;
import com.zhongda.quote.view.uiutils.JPasswordFieldUser;

/**
 * 
 * <p>
 * com.zhongda.quote.view.LoginFrame 窗口中所有监听事件
 * </p>
 * 
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月7日
 */
public class LoginFrameAction implements ActionListener, FocusListener {

	private String name;
	private JPasswordFieldUser passwordFieldUser;
	private String compoName;
	private JFrame jFrame;

	public LoginFrameAction() {

	}

	/**
	 * 
	 * @param frame
	 *            组件所在的窗口对象
	 */
	public LoginFrameAction(JFrame frame) {
		jFrame = frame;
	}

	/**
	 * 
	 * @param jp
	 *            传入组件类型为JPasswordFieldUser
	 */
	public LoginFrameAction(JPasswordFieldUser jp) {
		passwordFieldUser = jp;
	}

	/**
	 * 
	 * @param jp
	 *            传入组件类型为JPasswordFieldUser
	 * @param name
	 *            组件备注名称
	 */
	public LoginFrameAction(JPasswordFieldUser jpField, String name) {
		passwordFieldUser = jpField;
		this.compoName = name;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		name = e.getActionCommand();
		if ("close".equals(name)) {
			System.exit(0);
		} else if ("login".equals(name)) {
			jFrame.dispose();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						HomeFrame window = new HomeFrame();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		if (("password").equals(compoName)) {
			String passwordCommand = new String(passwordFieldUser.getPassword());
			if ("解码值".equals(passwordCommand)) {
				passwordFieldUser.setText("");
			}
			passwordFieldUser.setEchoChar('*');
			passwordFieldUser.setFont(new Font("宋体", 0, 15));
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

}
