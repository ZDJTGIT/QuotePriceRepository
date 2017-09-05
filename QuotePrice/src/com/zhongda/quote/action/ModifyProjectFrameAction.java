package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.QuoteProjectServiceImpl;
import com.zhongda.quote.utils.StringUtil;

/**
 *
 *<p>修改项目窗口事件处理类</p>
 * @author zmdeng
 * @date 2017年9月4日
 */
public class ModifyProjectFrameAction implements ActionListener {

	private JDialog dialog;
	private JTable jt_quoteTask;
	private JTable jt_quoteProject;
	private JTextField jtf_projectName;
	private JTextField jtf_otherAmount;
	private double oldProjectAmount;

	public ModifyProjectFrameAction(){
	}

	public ModifyProjectFrameAction(JDialog dialog) {
		this.dialog = dialog;
	}

	public ModifyProjectFrameAction(JTable jt_quoteTask, JTable jt_quoteProject,
			JTextField jtf_projectName, JTextField jtf_otherAmount, double oldProjectAmount,
			JDialog dialog) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jtf_projectName = jtf_projectName;
		this.jtf_otherAmount = jtf_otherAmount;
		this.oldProjectAmount = oldProjectAmount;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("confirm".equals(command)) {
			String otherAmountString = jtf_otherAmount.getText();
			if(null != otherAmountString && !"".equals(otherAmountString.trim())){
				if(StringUtil.isNumeric(otherAmountString)){
					int flag = JOptionPane.showConfirmDialog(null, "确定提交?", "提交项目",
							JOptionPane.OK_OPTION);
					if (flag == JOptionPane.OK_OPTION) {
						updateProject(otherAmountString);
					}
				}else{
					JOptionPane.showMessageDialog(null, "其它费用项请输入大于0的整形数字", "提示信息", JOptionPane.WARNING_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "其它费用项修改后的值不能是空值！", "提示信息", JOptionPane.WARNING_MESSAGE);
			}
		} else if ("cancel".equals(command)) {
			int flag = JOptionPane.showConfirmDialog(null, "取消项目？", "取消项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				dialog.dispose();
			}
		}
	}

	private void updateProject(String otherAmountString) {
		// 获取当前选中任务ID
		int projectId = Integer.valueOf(jtf_projectName.getName());
		String projectName = jtf_projectName.getText();
		double otherAmount = Double.parseDouble(otherAmountString);
		final QuoteProject quoteProject = new QuoteProject(projectId,
				projectName,  otherAmount);
		final int taskRow = jt_quoteTask.getSelectedRow();
		int taskId = (int) jt_quoteTask.getValueAt(taskRow, 0);
		final double taskAmount = (double)jt_quoteTask.getValueAt(taskRow, 7) - oldProjectAmount + otherAmount;
		final QuoteTask quoteTask = new QuoteTask(taskId, taskAmount);
		new SwingWorker<QuoteProject, Void>(){

			@Override
			protected QuoteProject doInBackground() throws Exception {
				return new QuoteProjectServiceImpl().updateProject(quoteProject, quoteTask);
			}

			protected void done() {
				QuoteProject quoteProject;
				try {
					quoteProject = get();
					int projectRow = jt_quoteProject.getSelectedRow();
					jt_quoteProject.setValueAt(quoteProject.getProjectName(), projectRow, 1);
					jt_quoteProject.setValueAt(quoteProject.getOtherAmount(), projectRow, 4);
					jt_quoteTask.setValueAt(taskAmount, taskRow, 7);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();
		dialog.dispose();
	}
}
