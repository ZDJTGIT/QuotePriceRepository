package com.zhongda.quote.view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.zhongda.quote.action.CreateContentFrameAction;
import com.zhongda.quote.action.ModifiyContentAction;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;

import java.awt.Font;

import javax.swing.JSeparator;

/**
 * 修改检验内容弹框界面
 *<p>
 *
 *<p>
 * @author 研发中心-Mikepolite<1011592269@qq.com>
 * @sine 2017年8月17日
 */

public class ModifyContentFrame {

	public JDialog jaDialog;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField textField_Contentname, textField_SamplesCount, textField_ImplementationCount,
			textField_Charges;
	private JLabel lblNewLabel_1, lblNewLabel_3, lblNewLabel_4,
			lblNewLabel_5;
	private JButton btnNewButton_yes, btnNewButton_no;
	private JTable jt_inspectionContent;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;

	/**
	 * @wbp.parser.constructor
	 */
	public ModifyContentFrame() {
		init();
	}

	//检验批ID 检验内容主界面table 删除操作
	public ModifyContentFrame(JTable jt_inspectionContent) {
		this.jt_inspectionContent = jt_inspectionContent;
		init();
	}

	public void init() {
		jaDialog = new JDialog();
		jaDialog.setBounds(0, 0, 500, 442);
		jaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ImageIcon icon = new ImageIcon("images\\zdLogo1.png");
		jaDialog.setTitle("中大检测");
		jaDialog.setResizable(false);
		jaDialog.setLocationRelativeTo(null);// 设置界面居中
		jaDialog.setIconImage(icon.getImage());
		jaDialog.setModal(true);
		jaDialog.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		jaDialog.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblNewLabel_1 = new JLabel("检验内容名称");
		lblNewLabel_1.setBounds(26, 86, 85, 15);
		panel.add(lblNewLabel_1);

		lblNewLabel_3 = new JLabel("抽样数量");
		lblNewLabel_3.setBounds(26, 142, 54, 15);
		panel.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("单个检验内容实施数量");
		lblNewLabel_4.setBounds(26, 198, 156, 15);
		panel.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("收费标准（元）");
		lblNewLabel_5.setBounds(26, 258, 85, 15);
		panel.add(lblNewLabel_5);

		textField_Contentname = new JTextField();
		textField_Contentname.setBounds(26, 111, 442, 23);
		panel.add(textField_Contentname);
		textField_Contentname.setColumns(10);
		textField_Contentname.setEnabled(false);

		textField_SamplesCount = new JTextField();
		textField_SamplesCount.setBounds(26, 167, 443, 21);
		panel.add(textField_SamplesCount);
		textField_SamplesCount.setColumns(10);

		textField_ImplementationCount = new JTextField();
		textField_ImplementationCount.setBounds(26, 227, 442, 21);
		panel.add(textField_ImplementationCount);
		textField_ImplementationCount.setColumns(10);

		textField_Charges = new JTextField();
		textField_Charges.setBounds(26, 283, 442, 21);
		panel.add(textField_Charges);
		textField_Charges.setColumns(10);
		textField_Charges.setEnabled(false);

		btnNewButton_yes = new JButton("确认");
		btnNewButton_yes.setBounds(250, 370, 93, 23);
		panel.add(btnNewButton_yes);

		btnNewButton_no = new JButton("取消");
		btnNewButton_no.setBounds(375, 370, 93, 23);
		panel.add(btnNewButton_no);

		panel_1 = new JpaneColorAndPhoto("images/bookpen.png", 430, 2, 48, 48);
		panel_1.setBounds(0, 0, 494, 76);
		panel_1.setLayout(null);
		panel.add(panel_1);

		label = new JLabel("修改检验内容");
		label.setFont(new Font("宋体", Font.BOLD, 12));
		label.setBounds(10, 23, 142, 15);
		panel_1.add(label);

		label_1 = new JLabel("表中可选中的位置为可修改信息，不可选中的为系统默认信息");
		label_1.setBounds(30, 48, 365, 15);
		panel_1.add(label_1);

		label_2 = new JLabel("中大检测");
		label_2.setBounds(0, 327, 54, 15);
		panel.add(label_2);
		btnNewButton_no.addActionListener(new CreateContentFrameAction(
				jaDialog));

		JSeparator separator = new JSeparator();
		separator.setBounds(52, 338, 442, 2);
		panel.add(separator);

		int row = jt_inspectionContent.getSelectedRow();
		textField_Contentname.setText(String.valueOf(jt_inspectionContent.getValueAt(row, 1)));
		textField_SamplesCount.setText(String.valueOf(jt_inspectionContent.getValueAt(row, 2)));
		textField_ImplementationCount.setText(String.valueOf(jt_inspectionContent.getValueAt(row, 3)));
		textField_Charges.setText(String.valueOf(jt_inspectionContent.getValueAt(row, 4)));

		btnNewButton_yes.setActionCommand("yes");
		btnNewButton_yes.addActionListener(new ModifiyContentAction(jaDialog, jt_inspectionContent,
				textField_Contentname,textField_SamplesCount, textField_ImplementationCount,textField_Charges) );
		btnNewButton_no.setActionCommand("no");
	}
}
