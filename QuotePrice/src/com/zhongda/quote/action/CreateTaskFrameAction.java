package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;
import com.zhongda.quote.utils.FrameGoUtils;

/**
 *
 * <p>
 * 创建任务窗口中所有监听事件处理类
 * <p>
 *
 * @author 研发中心-Mikepolite<1011592269@qq.com>
 * @sine 2017年8月9日
 */
public class CreateTaskFrameAction implements ActionListener {

	private JTextField jtf_taskName;
	private JTextField jtf_createUser;
	private JTextField df_createDate;
	private JTextArea jta_taskDescription;
	private JDialog jDialog;
	private QuoteTask quoteTask;
	// 主界面报价任务的引用
	private JTable jt_quoteTask;
	private JTable jt_quoteProject;
	private JTable jt_inspectionBatch;
	private JTable jt_inspectionContent;
	private boolean isCreate;

	public CreateTaskFrameAction(JTextField jtf_taskName,
			JTextField jtf_createUser, JTextField df_createDate,
			JTextArea jta_taskDescription, JTable jt_quoteTask, JTable jt_quoteProject,
			JTable jt_inspectionBatch, JTable jt_inspectionContent,
			JDialog dialog, boolean isCreate) {

		this.jtf_taskName = jtf_taskName;
		this.jtf_createUser = jtf_createUser;
		this.df_createDate = df_createDate;
		this.jta_taskDescription = jta_taskDescription;
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
		this.jDialog = dialog;
		this.isCreate = isCreate;
	}

	public CreateTaskFrameAction(JDialog jDialog) {

		this.jDialog = jDialog;
	}

	public CreateTaskFrameAction(){

	}

	/**
	 * 确定提交和退出按钮监听事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// 如果command为确认创建任务，则打开创建任务窗口
		if ("confirmCreateTask".equals(command)) {
			String taskName = jtf_taskName.getText();
			String createUser = jtf_createUser.getText();
			String createDate = df_createDate.getText();
			String taskDescription = jta_taskDescription.getText();

			final DefaultTableModel model = (DefaultTableModel) jt_quoteTask.getModel();
			final int row = jt_quoteTask.getSelectedRow();

			// 判断用户填写的任务信息是否完整
			if (null != taskName && !"".equals(taskName) && null != createUser
					&& !"".equals(createUser) && null != createDate
					&& !"".equals(createDate) && null != taskDescription
					&& !"".equals(taskDescription)) {

				// 把获取的任务信息转换为model对象
				quoteTask = new QuoteTask(taskName, taskDescription,
						createUser, createDate, new SimpleDateFormat(
								"yyyy-MM-dd").format(new Date()));
				// 如果是修改报价任务，则添加需修改报价任务的id
				if (!isCreate) {
					quoteTask.setId(Integer.valueOf(String.valueOf(model
							.getValueAt(row, 0))));
				}

				// 启动任务线程往数据库插入数据
				new SwingWorker<QuoteTask, Void>() {
					protected QuoteTask doInBackground() throws Exception {
						if (isCreate) {
							return new QuoteTaskServiceImpl()
									.createQuoteTask(quoteTask);
						} else {
							return new QuoteTaskServiceImpl()
									.updateQuoteTask(quoteTask);
						}

					}

					protected void done() {
						try {
							quoteTask = get();

							if (isCreate) { // 如果是创建报价任务，则添加
								if (null != quoteTask) {
									JOptionPane.showMessageDialog(null,
											"任务创建成功！", "提示信息",
											JOptionPane.PLAIN_MESSAGE);

									Vector<Object> rowData = new Vector<Object>();
									rowData.add(quoteTask.getId());
									rowData.add(quoteTask.getTaskNumber());
									rowData.add(quoteTask.getTaskName());
									rowData.add(quoteTask.getTaskDescription());
									rowData.add(quoteTask.getCreateUser());
									rowData.add(quoteTask.getCreateDate());
									rowData.add(quoteTask.getLastUpdateDate());
									rowData.add(quoteTask.getTaskAmount());
									model.addRow(rowData);

									// 新增后选中最后一行
									jt_quoteTask.setRowSelectionInterval(
											jt_quoteTask.getRowCount() - 1,
											jt_quoteTask.getRowCount() - 1);

									jDialog.dispose();
									FrameGoUtils.createProject(jt_quoteTask, jt_quoteProject, jt_inspectionBatch, jt_inspectionContent);
								} else {
									JOptionPane.showMessageDialog(null,
											"任务创建失败！", "提示信息",
											JOptionPane.ERROR_MESSAGE);
								}
							} else { // 如果是修改报价任务，则更新
								if (null != quoteTask) {
									JOptionPane.showMessageDialog(null,
											"任务修改成功！", "提示信息",
											JOptionPane.PLAIN_MESSAGE);
									model.setValueAt(quoteTask.getTaskName(),
											row, 2);
									model.setValueAt(
											quoteTask.getTaskDescription(),
											row, 3);
									model.setValueAt(quoteTask.getCreateUser(),
											row, 4);
									model.setValueAt(quoteTask.getCreateDate(),
											row, 5);
									model.setValueAt(
											quoteTask.getLastUpdateDate(), row,
											6);
									jt_quoteTask.setModel(model);
									jDialog.dispose();
								} else {
									JOptionPane.showMessageDialog(null,
											"任务修改失败！", "提示信息",
											JOptionPane.ERROR_MESSAGE);
								}

							}
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
					};
				}.execute();
			} else {
				JOptionPane.showMessageDialog(null, "请完善您的任务信息！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			}

		} else if ("cancelCreateTask".equals(command)) {
			int inf = JOptionPane.showConfirmDialog(null, "确定退出么？", "取消创建报价任务",
					JOptionPane.OK_OPTION);
			if (inf == JOptionPane.OK_OPTION) {
				jDialog.dispose();
			}
		}
	}

}
