package com.zhongda.quote.action;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
public class LoginFrameAction implements ActionListener, FocusListener,
		KeyListener {

	private String name;
	private JPasswordFieldUser passwordFieldUser;
	private String compoName;
	private String passwordCommand;
	private JFrame jFrame;

	public LoginFrameAction() {
	}

	/**
	 *
	 * @param frame
	 *            组件所在的窗口对象
	 */
	public LoginFrameAction(JFrame frame) {
		this.jFrame = frame;
	}

	public LoginFrameAction(JFrame frame, JPasswordFieldUser passwordFieldUser) {
		this.jFrame = frame;
		this.passwordFieldUser = passwordFieldUser;
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
			login();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (("password").equals(compoName)) {
			passwordCommand = new String(passwordFieldUser.getPassword());
			if ("解码值".equals(passwordCommand)) {
				passwordFieldUser.setText("解码值:");
			}
			passwordFieldUser.setEchoChar('*');
			passwordFieldUser.setFont(new Font("宋体", 0, 15));
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			login();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void login() {
		// ***********为方便测试，机器码功能暂时注释
		// String machineSerial = SequenceServiceImpl.getMachineCode();
		// String machineKey = MachineKeyUtil.getMachineKey(machineSerial);
		// String pwd = new String(passwordFieldUser.getPassword());
		// if (machineKey.equals(pwd)) {
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
		// } else {
		// // 机器码与解码值不匹配
		// JOptionPane.showMessageDialog(null, "解码值不正确或为授权!", "操作提醒",
		// JOptionPane.PLAIN_MESSAGE);
		// }
		// // ***********为方便测试，机器码功能暂时注释

	}
}
