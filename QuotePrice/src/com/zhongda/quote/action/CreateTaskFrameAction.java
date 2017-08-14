package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	public CreateTaskFrameAction(JTextField jtf_taskName,
			JTextField jtf_createUser, JTextField df_createDate,
			JTextArea jta_taskDescription, JTable jt_quoteTask, JDialog dialog) {

		this.jtf_taskName = jtf_taskName;
		this.jtf_createUser = jtf_createUser;
		this.df_createDate = df_createDate;
		this.jta_taskDescription = jta_taskDescription;
		this.jt_quoteTask = jt_quoteTask;
		this.jDialog = dialog;
	}

	public CreateTaskFrameAction(JDialog jDialog) {

		this.jDialog = jDialog;
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
			// 判断用户填写的任务信息是否完整
			if (null != taskName && !"".equals(taskName) && null != createUser
					&& !"".equals(createUser) && null != createDate
					&& !"".equals(createDate) && null != taskDescription
					&& !"".equals(taskDescription)) {

				// 把获取的任务信息转换为model对象
				quoteTask = new QuoteTask(taskName, taskDescription,
						createUser, createDate, createDate);
				// 启动任务线程往数据库插入数据
				new SwingWorker<QuoteTask, Void>() {
					protected QuoteTask doInBackground() throws Exception {
						return new QuoteTaskServiceImpl()
								.createQuoteTask(quoteTask);
					}

					protected void done() {
						try {
							quoteTask = get();
							if (null != quoteTask) {
								JOptionPane.showMessageDialog(null, "任务创建成功！",
										"提示信息", JOptionPane.PLAIN_MESSAGE);

								DefaultTableModel model = (DefaultTableModel) jt_quoteTask
										.getModel();
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
								FrameGoUtils.creatProject();
							} else {
								JOptionPane.showMessageDialog(null, "任务创建失败！",
										"提示信息", JOptionPane.ERROR_MESSAGE);
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
