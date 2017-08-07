package com.zhongda.quote.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.zhongda.quote.view.uiutils.JPasswordFieldUser;

/**
 * <p>com.zhongda.quote.view.LoginInterface界面所有组件监听类。描述以句号结尾。</p>
 * @author 研发中心-罗 杰<1250368725@qq.com>
 * @since 2017年8月4日
 * 
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
			//注意：建议不使用过时的方法---将确定值改前避免空指针异常
			//if (passwordFieldUser.getText().equals("解码值")) {
			String passwordCommand = new String(passwordFieldUser.getPassword());
			if ("解码值".equals(passwordCommand)) {
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
			System.out.println("好好说话");
		} else if (name.equals("close")) {
			System.exit(0);
		}

	}

}
