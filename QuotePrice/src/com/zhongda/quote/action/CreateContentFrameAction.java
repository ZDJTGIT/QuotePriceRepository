package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.SysInspectionContent;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.service.impl.SysInspectionContenServiceImpl;
import com.zhongda.quote.utils.RenderDataUtils;

public class CreateContentFrameAction implements ActionListener {

	private JDialog jaDialog;
	private JTable jt_sysInspectionContent;
	private JTextField jtf_contentName;
	private JTable jt_quoteTask;
	private JTable jt_quoteProject;
	private JTable jt_inspectionBatch;
	private JTable jt_inspectionContent;

	public CreateContentFrameAction() {

	}

	public CreateContentFrameAction(JDialog jaDialog) {
		this.jaDialog = jaDialog;
	}

	public CreateContentFrameAction(JTextField jtf_contentName,
			JTable jt_sysInspectionContent) {
		this.jtf_contentName = jtf_contentName;
		this.jt_sysInspectionContent = jt_sysInspectionContent;
	}

	public CreateContentFrameAction(JTable jt_quoteTask,
			JTable jt_quoteProject, JTable jt_inspectionBatch,
			JTable jt_inspectionContent, JDialog jaDialog,
			JTable jt_sysInspectionContent) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
		this.jaDialog = jaDialog;
		this.jt_sysInspectionContent = jt_sysInspectionContent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// 如果command为确认添加任务，则打开创建任务窗口
		if ("addContent".equals(command)) {
			int row = jt_sysInspectionContent.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择需要添加的检验内容！", "提示信息",
						JOptionPane.ERROR_MESSAGE);
			} else {
				int sourceId = (int) jt_sysInspectionContent.getValueAt(row, 0);
				String inspectionContentName = String.valueOf(jt_sysInspectionContent.getValueAt(row, 1));
				String sampleQuantityRange = String.valueOf(jt_sysInspectionContent.getValueAt(row, 2));
				int sampleQuantity = (int)jt_sysInspectionContent.getValueAt(row, 3);
				String singleQuantityRange = String.valueOf(jt_sysInspectionContent.getValueAt(row, 4));
				int singleObjectQuantity = (int)jt_sysInspectionContent.getValueAt(row, 5);
				int sampleBasisId = (int)jt_sysInspectionContent.getValueAt(row, 6);
				String chargeUnit = String.valueOf(jt_sysInspectionContent.getValueAt(row, 7));
				int chargeStandard = (int)jt_sysInspectionContent.getValueAt(row, 8);
				String chargeStandardUnit = String.valueOf(jt_sysInspectionContent.getValueAt(row, 9));
				int quoteBasisId = (int)jt_sysInspectionContent.getValueAt(row, 10);
				//计算检测内容总金额
				double inspectionContentAmount = sampleQuantity*singleObjectQuantity*chargeStandard;
				final InspectionContent inspectionContent = new InspectionContent(sourceId,inspectionContentName,sampleQuantity,sampleQuantityRange,sampleBasisId,singleObjectQuantity,singleQuantityRange,chargeUnit,chargeStandard,chargeStandardUnit,quoteBasisId,inspectionContentAmount);

				final int batchRow = jt_inspectionBatch.getSelectedRow();
				int batchId = (int) jt_inspectionBatch.getValueAt(batchRow, 0);
				//传入batchId
				inspectionContent.setBatchId(batchId);
				//重新计算检验批金额
				final double batchAmount = (double)jt_inspectionBatch.getValueAt(batchRow, 2) + inspectionContentAmount;
				//重新计算项目金额
				final int projectRow = jt_quoteProject.getSelectedRow();
				final double projectAmount = (double)jt_quoteProject.getValueAt(projectRow, 5) + inspectionContentAmount;
				//重新计算任务金额
				final int taskRow = jt_quoteTask.getSelectedRow();
				final double taskAmount = (double)jt_quoteTask.getValueAt(taskRow, 7) + inspectionContentAmount;

				// 启动任务线程往数据库插入数据
				new SwingWorker<InspectionContent, Void>() {
					protected InspectionContent doInBackground()
							throws Exception {
						return new InspectionContentServiceImpl()
								.createInspectionContent(inspectionContent, taskAmount, projectAmount, batchAmount);
					}

					protected void done() {
						InspectionContent inspectionContent =null;
						try {
							// 获得当前插入的检验内容并展示在新增界面
							inspectionContent = get();
							if (null != inspectionContent) {
								RenderDataUtils.renderSinglePartContentData(jt_inspectionContent, inspectionContent);
								jt_quoteTask.setValueAt(taskAmount, taskRow, 7);
								jt_quoteProject.setValueAt(projectAmount, projectRow, 5);
								jt_inspectionBatch.setValueAt(batchAmount, batchRow, 2);
							} else {
								JOptionPane.showMessageDialog(null, "任务添加失败！",
										"提示信息", JOptionPane.ERROR_MESSAGE);
							}
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
					};

				}.execute();
				jaDialog.dispose();
			}
		} else if ("cancel".equals(command)) {
			int inf = JOptionPane.showConfirmDialog(null, "确定退出么？", "取消添加检验内容",
					JOptionPane.OK_OPTION);
			if (inf == JOptionPane.OK_OPTION) {
				jaDialog.dispose();
			}// 添加模糊查询操作
		} else if ("searchContent".equals(command)) {
			// 根据传入的字符串做模糊查询，查出的数据展示到表中
			new SwingWorker<List<SysInspectionContent>, SysInspectionContent>() {
				protected List<SysInspectionContent> doInBackground()
						throws Exception {
					return new SysInspectionContenServiceImpl()
							.selectAllBlurrySysInspectionContent(jtf_contentName
									.getText());
				}

				protected void done() {
					try {
						List<SysInspectionContent> sysContentList = get();
						if (null == sysContentList
								|| sysContentList.size() <= 0) {
							JOptionPane.showMessageDialog(null, "查询结果不存在！",
									"提示信息", JOptionPane.WARNING_MESSAGE);
						} else {
							RenderDataUtils.renderSysContentData(
									jt_sysInspectionContent, sysContentList);
						}
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}
			}.execute();
		}
	}

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
