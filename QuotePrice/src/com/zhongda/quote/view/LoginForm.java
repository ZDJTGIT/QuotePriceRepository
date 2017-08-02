package com.zhongda.quote.view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.zhongda.quote.controller.JButtController;

public class LoginForm {

	private JFrame frame;
	private JTextField jtf_string;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("请输入用户名");
		label.setBounds(10, 10, 98, 15);
		frame.getContentPane().add(label);

		jtf_string = new JTextField();
		jtf_string.setBounds(104, 7, 236, 21);
		frame.getContentPane().add(jtf_string);
		jtf_string.setColumns(10);

		JButton jbtn_change = new JButton("查询");
		jbtn_change.addActionListener(new JButtController(jtf_string, frame));
		jbtn_change.setBounds(0, 35, 93, 23);
		frame.getContentPane().add(jbtn_change);

	}
}
