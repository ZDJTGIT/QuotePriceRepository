package com.zhongda.quote.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 登陆界面密码栏监听事件
 * @author LiVerson
 *
 * 2017年8月3日
 */

public class JPasswordLiser implements MouseListener {

	private JPasswordFieldUser jpUser;

	public JPasswordLiser() {

	}

	public JPasswordLiser(JPasswordFieldUser jp) {
		jpUser = jp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (jpUser.getText().equals("解码值")) {
			jpUser.setText("");
		}
		jpUser.setEchoChar('*');
		jpUser.setFont(new Font("宋体", 0, 15));

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

}
