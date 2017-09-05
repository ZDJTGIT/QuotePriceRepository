package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.action.ModifyBatchFrameAction;
import com.zhongda.quote.utils.SkinUtil;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;

/**
 *<p>修改检验批窗口</p>
 * @author zmdeng
 * @date 2017年9月4日
 */
public class ModifyBatchFrame {

	public JDialog dialog;
	private JPanel panel;
	private JpaneColorAndPhoto jpanel_up;
	private JLabel jlb_jpup_1;
	private JLabel jlb_jpup_2;
	private JLabel label;
	private JTextField jtf_projectName;
	private JLabel lblNewLabel;
	private JTextField jtf_batchName;
	private JTextField jtf_batchAmount;
	private JLabel lblNewLabel_2;
	private JLabel label_3;
	private JSeparator separator;
	private JButton jbt_confirm;
	private JButton jbt_cancel;
	private String projectName;
	private JTable jt_quoteProject;
	private JTable jt_inspectionBatch;

	public ModifyBatchFrame(JTable jt_quoteProject, JTable jt_inspectionBatch) {
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		init();
	}

	public void init() {

		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setBounds(0, 0, 500, 350);
		dialog.setModal(true);// 此窗口至于前端
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setTitle("中大检测");
		dialog.setIconImage(dialog.getToolkit().getImage("images/zdLogo1.png"));
		dialog.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		jpanel_up = new JpaneColorAndPhoto("images/bookpen.png", 430, 2, 48, 48);
		jpanel_up.setBounds(0, 0, 494, 52);
		panel.add(jpanel_up);
		jpanel_up.setLayout(null);

		jlb_jpup_1 = new JLabel("新建检验批");
		jlb_jpup_1.setBounds(10, 10, 157, 15);
		jlb_jpup_1.setFont(new Font("黑体", 1, 15));
		jpanel_up.add(jlb_jpup_1);

		jlb_jpup_2 = new JLabel("填写检验批信息");
		jlb_jpup_2.setBounds(33, 25, 186, 15);
		jpanel_up.add(jlb_jpup_2);

		label = new JLabel("所属项目");
		label.setBounds(26, 62, 54, 15);
		panel.add(label);

		jtf_projectName = new JTextField();
		jtf_projectName.setBounds(26, 80, 445, 25);
		jtf_projectName.setEnabled(false);
		jtf_projectName.setText(projectName);
		panel.add(jtf_projectName);// 存入所选项目的id，取出时需要强转为int类型
		jtf_projectName.setColumns(10);

		lblNewLabel = new JLabel("检验批名称");
		lblNewLabel.setBounds(26, 110, 82, 15);
		panel.add(lblNewLabel);

		jtf_batchName = new JTextField();
		jtf_batchName.setBounds(26, 128, 445, 25);
		panel.add(jtf_batchName);
		jtf_batchName.setColumns(10);

		lblNewLabel_2 = new JLabel("检验批报价");
		lblNewLabel_2.setBounds(26, 160, 116, 15);
		panel.add(lblNewLabel_2);

		jtf_batchAmount = new JTextField();
		jtf_batchAmount.setColumns(10);
		jtf_batchAmount.setEnabled(false);
		jtf_batchAmount.setBounds(26, 182, 445, 25);
		panel.add(jtf_batchAmount);

		label_3 = new JLabel("中大检测");
		label_3.setBounds(5, 225, 54, 23);
		panel.add(label_3);

		separator = new JSeparator();
		separator.setBounds(54, 238, 435, 2);
		panel.add(separator);

		jbt_confirm = new JButton("确认");
		jbt_confirm.setFocusPainted(false);
		jbt_confirm.setBounds(263, 260, 93, 23);
		panel.add(jbt_confirm);

		jbt_cancel = new JButton("取消");
		jbt_cancel.setFocusPainted(false);
		jbt_cancel.setBounds(378, 260, 93, 23);
		panel.add(jbt_cancel);

		//初始化数据
		int projectRow = jt_quoteProject.getSelectedRow();
		jtf_projectName.setText(String.valueOf(jt_quoteProject.getValueAt(projectRow, 1)));
		int batchRow = jt_inspectionBatch.getSelectedRow();
		jtf_batchName.setText(String.valueOf(jt_inspectionBatch.getValueAt(batchRow, 1)));
		jtf_batchName.setName(String.valueOf(jt_inspectionBatch.getValueAt(batchRow, 0)));
		jtf_batchAmount.setText(String.valueOf(jt_inspectionBatch.getValueAt(batchRow, 2)));

		// 确认事件
		jbt_confirm.setActionCommand("confirm");
		jbt_confirm.addActionListener(new ModifyBatchFrameAction(dialog, jt_inspectionBatch, jtf_batchName));

		//取消事件
		jbt_cancel.setActionCommand("cancel");
		jbt_cancel.addActionListener(new ModifyBatchFrameAction(dialog));
	}
}
