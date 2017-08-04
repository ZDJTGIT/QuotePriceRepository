package com.zhongda.quote.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.zhongda.quote.view.uiutils.JPasswordFieldUser;

/**
 * com.zhongda.quote.view.LoginInterface界面所有组件监听类
 * 
 * @author LiVerson
 *
 *         2017年8月4日
 */

public class LoginInterfaceAction implements ActionListener, MouseListener {

	private String name;
	private JPasswordFieldUser passwordFieldUser;
	private String mname;

	public LoginInterfaceAction() {

	}

	/**
	 * 
	 * @param jp
	 *            传入组件类型为JPasswordFieldUser
	 */
	public LoginInterfaceAction(JPasswordFieldUser jp) {
		passwordFieldUser = jp;
	}

	/**
	 * 
	 * @param jp
	 *            传入组件类型为JPasswordFieldUser
	 * @param mname
	 *            组件备注名称
	 */
	public LoginInterfaceAction(JPasswordFieldUser jp, String mname) {
		passwordFieldUser = jp;
		this.mname = mname;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource().equals(passwordFieldUser)) {
			if (passwordFieldUser.getText().equals("解码值")) {
				passwordFieldUser.setText("");
			}
			passwordFieldUser.setEchoChar('*');
			passwordFieldUser.setFont(new Font("宋体", 0, 15));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		name = e.getActionCommand();
		if (name.equals("login")) {
			System.out.println("登录逻辑代码还没写，罗杰是个大坑逼，看到这条信息的人请远离罗杰");
		} else if (name.equals("close")) {
			System.exit(0);
		}

	}

}
