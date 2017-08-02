package com.zhongda.quote.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.User;
import com.zhongda.quote.service.UserService;
import com.zhongda.quote.service.impl.UserServiceImpl;

public class JButtController implements ActionListener{

	private JTextField jtf_string;
	private JFrame frame;

	private UserService userServiceImpl = new UserServiceImpl();

	public JButtController() {
	}

	public JButtController(JTextField jtf_string, JFrame frame) {
		this.jtf_string = jtf_string;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String string = jtf_string.getText();
		List<User> userList = userServiceImpl.selectUserListByName(string);
		int size = userList.size();
		Object[][] userArray = new Object[size][4];
		for (int i = 0; i < size; i++) {
			userArray[i][0] = userList.get(i).getRowid();
			userArray[i][1] = userList.get(i).getName();
			userArray[i][2] = userList.get(i).getSalary();
			userArray[i][3] = userList.get(i).getAddress();
		}

		JTable userTable = new JTable();
		userTable.setModel(new DefaultTableModel(
			userArray,
			new String[] {
				"编号", "姓名", "薪水", "地址"
			}
		));
		userTable.setBounds(15, 75, 383, 134);

		JScrollPane scrollPane = new JScrollPane(userTable);
		scrollPane.setBounds(10, 70, 414, 157);
		frame.getContentPane().add(scrollPane);

	}

}
