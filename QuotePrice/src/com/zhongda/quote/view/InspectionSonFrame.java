package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class InspectionSonFrame {

	public JDialog dialog;

	public InspectionSonFrame() {
		init();
	}

	public void init() {
		dialog = new JDialog();
		dialog.setBounds(0, 0, 500, 500);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setBounds(0, 0, 550, 500);
		dialog.setModal(true);// 此窗口至于前端
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setTitle("中大检测");
		dialog.setIconImage(dialog.getToolkit().getImage("images/zdLogo1.png"));
		dialog.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		dialog.getContentPane().add(panel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InspectionSonFrame window = new InspectionSonFrame();
					window.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
