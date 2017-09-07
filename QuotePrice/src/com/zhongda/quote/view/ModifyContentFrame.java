package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.zhongda.quote.action.ModifyContentFrameAction;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;

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
	private JTextField jtf_contentName, jtf_samplesQuantity, jtf_singleObjectQuantity, jtf_chargeStandard;
	private JLabel lblNewLabel_1, lblNewLabel_3, lblNewLabel_4, lblNewLabel_5;
	private JButton jbt_confirm, jbt_cancel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel jl_sampleQuantityRange;
	private JLabel jl_singleQuantityRange;
	private JTable jt_quoteTask;
	private JTable jt_quoteProject;
	private JTable jt_inspectionBatch;
	private JTable jt_inspectionContent;

	public ModifyContentFrame(JTable jt_quoteTask, JTable jt_quoteProject,
			JTable jt_inspectionBatch, JTable jt_inspectionContent) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
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
		lblNewLabel_4.setBounds(26, 198, 133, 15);
		panel.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("收费标准（元）");
		lblNewLabel_5.setBounds(26, 258, 85, 15);
		panel.add(lblNewLabel_5);

		jtf_contentName = new JTextField();
		jtf_contentName.setBounds(26, 111, 442, 23);
		panel.add(jtf_contentName);
		jtf_contentName.setColumns(10);
		jtf_contentName.setEnabled(false);

		jtf_samplesQuantity = new JTextField();
		jtf_samplesQuantity.setBounds(26, 167, 443, 21);
		panel.add(jtf_samplesQuantity);
		jtf_samplesQuantity.setColumns(10);

		jtf_singleObjectQuantity = new JTextField();
		jtf_singleObjectQuantity.setBounds(26, 227, 442, 21);
		panel.add(jtf_singleObjectQuantity);
		jtf_singleObjectQuantity.setColumns(10);

		jtf_chargeStandard = new JTextField();
		jtf_chargeStandard.setBounds(26, 283, 442, 21);
		panel.add(jtf_chargeStandard);
		jtf_chargeStandard.setColumns(10);
		jtf_chargeStandard.setEnabled(false);

		jbt_confirm = new JButton("确认");
		jbt_confirm.setBounds(250, 370, 93, 23);
		panel.add(jbt_confirm);

		jbt_cancel = new JButton("取消");
		jbt_cancel.setBounds(375, 370, 93, 23);
		panel.add(jbt_cancel);

		panel_1 = new JpaneColorAndPhoto("images/bookpen.png", 430, 2, 48, 48);
		panel_1.setBounds(0, 0, 494, 76);
		panel_1.setLayout(null);
		panel.add(panel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(55, 336, 442, 2);
		panel.add(separator);

		label = new JLabel("修改检验内容");
		label.setFont(new Font("宋体", Font.BOLD, 12));
		label.setBounds(10, 23, 142, 15);
		panel_1.add(label);

		label_1 = new JLabel("表中可选中的位置为可修改信息，不可选中的为系统默认信息");
		label_1.setBounds(30, 48, 365, 15);
		panel_1.add(label_1);

		label_2 = new JLabel("中大检测");
		label_2.setBounds(4, 327, 54, 15);
		panel.add(label_2);

		label_3 = new JLabel("抽样数量范围：");
		label_3.setBounds(89, 142, 85, 15);
		panel.add(label_3);

		label_4 = new JLabel("单个检验内容实施数量范围：");
		label_4.setBounds(161, 198, 156, 15);
		panel.add(label_4);

		jl_sampleQuantityRange = new JLabel();
		jl_sampleQuantityRange.setBounds(184, 142, 72, 15);
		panel.add(jl_sampleQuantityRange);

		jl_singleQuantityRange = new JLabel();
		jl_singleQuantityRange.setBounds(326, 198, 72, 15);
		panel.add(jl_singleQuantityRange);

		final Integer InspectiongID = (Integer) jt_inspectionContent.
				getValueAt(jt_inspectionContent.getSelectedRow(), 0);
		new SwingWorker<InspectionContent, InspectionContent>() {
			@Override
			protected InspectionContent doInBackground() throws Exception {
				return new InspectionContentServiceImpl()
				.selectInspectionContentById(InspectiongID);
			}
			@Override
			protected void done() {
				try {
					InspectionContent inspectionContent = get();
					jtf_contentName.setText(inspectionContent.getInspectionContentName());
					jtf_samplesQuantity.setText(String.valueOf(inspectionContent.getSampleQuantity()));
					jtf_singleObjectQuantity.setText(String.valueOf(inspectionContent.getSingleObjectQuantity()));
					jtf_chargeStandard.setText(String.valueOf(inspectionContent.getChargeStandard()));
					jl_sampleQuantityRange.setText(inspectionContent.getSampleQuantityRange());
					jl_singleQuantityRange.setText(inspectionContent.getSingleQuantityRange());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();

		//确认按钮事件
		jbt_confirm.setActionCommand("confirm");
		jbt_confirm.addActionListener(new ModifyContentFrameAction(jaDialog, jt_quoteTask, jt_quoteProject, jt_inspectionBatch,
				jt_inspectionContent, jtf_samplesQuantity, jtf_singleObjectQuantity, jl_sampleQuantityRange, jl_singleQuantityRange));
		//取消按钮事件
		jbt_cancel.setActionCommand("cancel");
		jbt_cancel.addActionListener(new ModifyContentFrameAction(jaDialog));
	}
}
