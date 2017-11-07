package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.utils.StringUtil;

public class ModifyContentFrameAction implements ActionListener {

	private JTable jt_quoteTask;
	private JTable jt_quoteProject;
	private JTable jt_inspectionBatch;
	private JTable jt_inspectionContent;
	private JDialog jaDialog;
	private JTextField jtf_samplesQuantity;
	private JTextField jtf_singleObjectQuantity;
	private JLabel jl_sampleQuantityRange;
	private JLabel jl_singleQuantityRange;

	public ModifyContentFrameAction(JDialog jaDialog) {
		this.jaDialog = jaDialog;
	}

	public ModifyContentFrameAction(JDialog jaDialog, JTable jt_quoteTask,
			JTable jt_quoteProject, JTable jt_inspectionBatch,
			JTable jt_inspectionContent, JTextField jtf_samplesQuantity,
			JTextField jtf_singleObjectQuantity, JLabel jl_sampleQuantityRange,
			JLabel jl_singleQuantityRange) {
		this.jaDialog = jaDialog;
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
		this.jtf_samplesQuantity = jtf_samplesQuantity;
		this.jtf_singleObjectQuantity = jtf_singleObjectQuantity;
		this.jl_sampleQuantityRange = jl_sampleQuantityRange;
		this.jl_singleQuantityRange = jl_singleQuantityRange;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// 如果command为确认，则提交当前修改的信息
		if ("confirm".equals(command)) {
			String samplesQuantity = jtf_samplesQuantity.getText();
			String singleObjectQuantity = jtf_singleObjectQuantity.getText();
			// String charges = textField_5.getText();
			// 判断用户填写的任务信息是否完整
			if (null != samplesQuantity && !"".equals(samplesQuantity.trim())
					&& null != singleObjectQuantity
					&& !"".equals(singleObjectQuantity.trim())) {
				// 判断用户填写的是否是数字
				if (StringUtil.isNumeric(samplesQuantity)
						&& StringUtil.isNumeric(singleObjectQuantity)) {
					// 判断用户填写的数值是否在取值范围之内
					int[] samplesRange = StringUtil
							.rangeToMinMax(jl_sampleQuantityRange.getText());
					int[] singleRange = StringUtil
							.rangeToMinMax(jl_singleQuantityRange.getText());
					int samplesQuantityInt = Integer.valueOf(samplesQuantity);
					int singleObjectQuantityInt = Integer
							.valueOf(singleObjectQuantity);
					if (samplesQuantityInt >= samplesRange[0]
							&& samplesQuantityInt <= samplesRange[1]
							&& singleObjectQuantityInt >= singleRange[0]
							&& singleObjectQuantityInt <= singleRange[1]) {
						final int contentRow = jt_inspectionContent
								.getSelectedRow();
						int contentId = (int) jt_inspectionContent.getValueAt(
								contentRow, 0);
						// 计算检验内容金额
						double oldContentAmount = (double) jt_inspectionContent
								.getValueAt(contentRow, 5);
						int chargeStandard = (int) jt_inspectionContent
								.getValueAt(contentRow, 4);
						double contentAmount = samplesQuantityInt
								* singleObjectQuantityInt * chargeStandard;
						final InspectionContent inspectionContent = new InspectionContent(
								contentId, samplesQuantityInt,
								singleObjectQuantityInt, contentAmount);
						// 计算检验批金额
						final int batchRow = jt_inspectionBatch
								.getSelectedRow();
						int batchId = (int) jt_inspectionBatch.getValueAt(
								batchRow, 0);
						final double batchAmount = (double) jt_inspectionBatch
								.getValueAt(batchRow, 2)
								- oldContentAmount
								+ contentAmount;
						final InspectionBatch inspectionBatch = new InspectionBatch(
								batchId, batchAmount);
						// 计算报价项目金额
						final int projectRow = jt_quoteProject.getSelectedRow();
						int projectId = (int) jt_quoteProject.getValueAt(
								projectRow, 0);
						final double projectAmount = (double) jt_quoteProject
								.getValueAt(projectRow, 5)
								- oldContentAmount
								+ contentAmount;
						final QuoteProject quoteProject = new QuoteProject(
								projectId, projectAmount);
						// 计算报价任务金额
						final int taskRow = jt_quoteTask.getSelectedRow();
						int taskId = (int) jt_quoteTask.getValueAt(taskRow, 0);
						final double taskAmount = (double) jt_quoteTask
								.getValueAt(taskRow, 7)
								- oldContentAmount
								+ contentAmount;
						final QuoteTask quoteTask = new QuoteTask(taskId,
								taskAmount);

						new SwingWorker<InspectionContent, Void>() {
							protected InspectionContent doInBackground()
									throws Exception {
								return new InspectionContentServiceImpl()
										.updateInspectionContent(quoteTask,
												quoteProject, inspectionBatch,
												inspectionContent);
							}

							protected void done() {
								try {
									InspectionContent inspectionContent = get();
									if (null != inspectionContent) {
										// JOptionPane.showMessageDialog(null,
										// "任务修改成功！", "提示信息",
										// JOptionPane.PLAIN_MESSAGE);
										// 更新检验内容UI组件
										jt_inspectionContent.setValueAt(
												inspectionContent
														.getSampleQuantity(),
												contentRow, 2);
										jt_inspectionContent
												.setValueAt(
														inspectionContent
																.getSingleObjectQuantity(),
														contentRow, 3);
										jt_inspectionContent
												.setValueAt(
														inspectionContent
																.getInspectionContentAmount(),
														contentRow, 5);
										// 更新检验批UI组件
										jt_inspectionBatch.setValueAt(
												batchAmount, batchRow, 2);
										// 更新报价项目UI组件
										jt_quoteProject.setValueAt(
												projectAmount, projectRow, 5);
										// 更新报价任务UI组件
										jt_quoteTask.setValueAt(taskAmount,
												taskRow, 7);
										jaDialog.dispose();
									} else {
										JOptionPane.showMessageDialog(null,
												"任务修改失败！", "提示信息",
												JOptionPane.ERROR_MESSAGE);
									}
								} catch (InterruptedException
										| ExecutionException e) {
									e.printStackTrace();
								}
							};
						}.execute();
					} else {
						JOptionPane.showMessageDialog(null,
								"请确保您输入的数字在对应的取值范围之内！", "提示信息",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "请输入大于0的整形数字", "提示信息",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "您修改后的值不能是空值！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if ("cancel".equals(command)) {
			// int flag = JOptionPane.showConfirmDialog(null, "确定退出么？",
			// "取消修改检验内容", JOptionPane.OK_OPTION);
			// if (flag == JOptionPane.OK_OPTION) {
			jaDialog.dispose();
			// }
		}
	}
}
