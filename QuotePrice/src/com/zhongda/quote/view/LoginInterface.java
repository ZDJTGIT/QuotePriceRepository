package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.controller.JButtonLoginClose;
import com.zhongda.quote.controller.JBuuttonLogin;
import com.zhongda.quote.controller.JPanelBackPhoto;
import com.zhongda.quote.controller.JPasswordFieldUser;
import com.zhongda.quote.controller.JPasswordLiser;
import com.zhongda.quote.controller.JTextFieldUser;
import com.zhongda.quote.utils.SkinUtil;

/**
 * 登录界面
 * @author liVerson
 * @time 2017-8-3 13:27:01
 *
 */

public class LoginInterface {

	private JFrame frame;
	private JPanelBackPhoto jp_left;
	private JPanel jp_right;
	private JPanel jpr_down;
	private JLabel jl_up;
	private JButton jb_close;
	private JTextFieldUser jt_user;
	private JPasswordFieldUser jp_password;
	private JButton jb_login;
	private JLabel jl_number;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginInterface window = new LoginInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginInterface() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		
		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		frame = new JFrame();
		frame.setBounds(0, 0, 995, 334);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		jp_left = new JPanelBackPhoto("images/zhongda.png");
		jp_left.setBounds(0, 0, 614, 334);
		frame.add(jp_left);
		
		
		jp_right = new JPanel();
		jp_right.setBounds(614, 0, 380, 334);
		jp_right.setLayout(null);
		frame.add(jp_right);
		
		//右側JPanel組件
		//註冊通行证
		jpr_down = new JPanel();
		jpr_down.setBounds(1, 274, 381, 60);
		jpr_down.setBackground(new Color(0, 159, 134));
		jpr_down.setLayout(null);
		jp_right.add(jpr_down);
		
		//底层电话
		jl_number = new JLabel();
		jl_number.setBounds(50, 0, 300, 60);
		jl_number.setFont(new Font("宋体", 1, 20));
		jl_number.setIcon(new ImageIcon("images/phone2.png"));
		jl_number.setText(" 86-0731-88137791");
		jpr_down.add(jl_number);
		
		//关闭按钮
		jb_close = new JButton();
		jb_close.setBounds(360, 5, 16, 16);
		jb_close.setOpaque(false);
		jb_close.setFocusPainted(false);
		jb_close.setIcon(new ImageIcon("images/loginClose.png"));
		jb_close.addActionListener(new JButtonLoginClose());
		jp_right.add(jb_close);
		
		
		//中大检测
		jl_up = new JLabel();
		jl_up.setText("中大检测报价软件 Ver 1.0");
		jl_up.setFont(new Font("新宋体", 1, 14));
		jl_up.setForeground(Color.gray);
		jl_up.setBounds(30, 10, 300, 50);
		jp_right.add(jl_up);
		
		//账号
		jt_user = new JTextFieldUser("images/user16.png");
		jt_user.setBounds(30, 65, 320, 48);
		jt_user.setText("机器码");
		jt_user.setFont(new Font("宋体", 0, 19));
		jp_right.add(jt_user);
		
		//密码
		jp_password = new JPasswordFieldUser("images/password16.png");
		jp_password.setBounds(30, 133, 320, 48);
		jp_password.setFont(new Font("宋体", 0, 19));
		jp_password.setText("解码值");
		jp_password.addMouseListener((new JPasswordLiser(jp_password)));
		jp_password.setEchoChar('\0');
		jp_right.add(jp_password);
		
		//登录
		jb_login = new JButton("登 录");
		jb_login.setFont(new Font("宋体", 1, 20));
		jb_login.setBounds(30, 196, 320, 48);
		jb_login.setBackground(new Color(255, 101, 1));
		jb_login.addActionListener(new JBuuttonLogin());
		jp_right.add(jb_login);
		
		
		
		
	}

}
