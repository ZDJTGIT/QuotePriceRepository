package com.zhongda.quote.action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * 
 * <p>
 * 创建检验批界面事件监听类
 * </p>
 * 
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月12日
 */
public class CreatInspectionAction implements MouseMotionListener,
		ActionListener, FocusListener {

	private JTable table;
	private JTextField jField;
	private String name;
	private JPanel panel;
	private JDialog dialog;

	public CreatInspectionAction() {

	}

	public CreatInspectionAction(JDialog jd) {
		this.dialog = jd;
	}

	public CreatInspectionAction(JPanel jPanel) {
		this.panel = jPanel;
	}

	public CreatInspectionAction(JPanel panel, String name) {
		this.panel = panel;
		this.name = name;
	}

	public CreatInspectionAction(JTextField jField, JPanel panel, JTable table) {
		this.jField = jField;
		this.panel = panel;
		this.table = table;
	}

	public CreatInspectionAction(JTable table) {
		this.table = table;
	}

	public CreatInspectionAction(JTextField jField, String name) {
		this.jField = jField;
		this.name = name;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int row = table.rowAtPoint(e.getPoint());
		int col = table.columnAtPoint(e.getPoint());
		if (row > -1 && col > -1) {
			Object value = table.getValueAt(row, col);
			if (null != value && !"".equals(value))
				table.setToolTipText(value.toString());// 悬浮显示单元格内容
			else
				table.setToolTipText(null);// 关闭提示
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commandName = e.getActionCommand();
		// 界面查找按钮逻辑代码
		if ("search".equals(commandName)) {
			// >>>>>>>>>面板显示代码
			table.getSelectionModel().setSelectionInterval(0, 0);
			panel.setVisible(true);
			// <<<<<<<<<面板显示代码
		} else if ("commit".equals(commandName)) {
			int flag = JOptionPane.showConfirmDialog(null, "确定提交?", "提交项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				dialog.dispose();
			}
		} else if ("calloff".equals(commandName)) {
			int flag = JOptionPane.showConfirmDialog(null, "取消项目？", "取消项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				dialog.dispose();
			}
		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		if ("printInspec".equals(name)) {
			String content = jField.getText();
			if ("请输入检测内容".equals(content)) {
				jField.setText("");
				jField.setForeground(Color.BLACK);
			}
		} else if ("searchPanel".equals(name)) {
		} else if (name == null) {
			panel.setVisible(false);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if ("searchPanel".equals(name)) {
			panel.setVisible(false);
		}

	}

}
