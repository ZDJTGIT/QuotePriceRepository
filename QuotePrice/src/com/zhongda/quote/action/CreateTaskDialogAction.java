package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.Industry;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;

/**
 *
 * <p>
 * 创建任务窗口中所有监听事件处理类
 * <p>
 *
 * @author 研发中心-Mikepolite<1011592269@qq.com>
 * @sine 2017年8月9日
 */
public class CreateTaskDialogAction implements ActionListener, WindowListener {

	private JTextField jtf_taskName;
	private JTextField jtf_createUser;
	private JTextField df_createDate;
	private JTextArea jta_taskDescription;
	private JComboBox<Industry> jcb_industry;
	private JDialog jDialog;
	private QuoteTask quoteTask;
	// 主界面报价任务的引用
	private JTable jt_quoteTask;

	public CreateTaskDialogAction(JTextField jtf_taskName,
			JTextField jtf_createUser, JTextField df_createDate,
			JComboBox<Industry> jcb_industry, JTextArea jta_taskDescription,
			JTable jt_quoteTask) {

		this.jtf_taskName = jtf_taskName;
		this.jtf_createUser = jtf_createUser;
		this.df_createDate = df_createDate;
		this.jta_taskDescription = jta_taskDescription;
		this.jcb_industry = jcb_industry;
		this.jt_quoteTask = jt_quoteTask;
	}

	public CreateTaskDialogAction(JDialog jDialog) {

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
			Industry industry = (Industry) jcb_industry.getSelectedItem();
			String taskDescription = jta_taskDescription.getText();
			// 判断用户填写的任务信息是否完整
			if (null != taskName && !"".equals(taskName) && null != createUser
					&& !"".equals(createUser) && null != createDate
					&& !"".equals(createDate) && null != taskDescription
					&& !"".equals(taskDescription) && null != industry) {

				// 把获取的任务信息转换为model对象
				quoteTask = new QuoteTask(taskName, taskDescription,
						industry.getId(), createUser, createDate, createDate);
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
								rowData.add(quoteTask.getIndustry()
										.getIndustryName());
								rowData.add(quoteTask.getCreateUser());
								rowData.add(quoteTask.getCreateDate());
								rowData.add(quoteTask.getLastUpdateDate());
								rowData.add(quoteTask.getTaskAmount());
								model.addRow(rowData);
								;
							} else {
								JOptionPane.showMessageDialog(null, "任务创建失败！",
										"提示信息", JOptionPane.PLAIN_MESSAGE);
							}
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
					};
				}.execute();
			} else {
				Object[] options = { "确认", "取消" };
				JOptionPane.showOptionDialog(null, "请完善您的任务信息", "警告",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			}

		} else if ("cancelCreateTask".equals(command)) {
			int inf = JOptionPane.showConfirmDialog(null, "确定退出么？",
					"Attention", JOptionPane.OK_OPTION);
			if (inf == JOptionPane.OK_OPTION) {
				jDialog.dispose();

			} else {

			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		int inf = JOptionPane.showConfirmDialog(null, "确定关闭窗口？", "Attention",
				JOptionPane.OK_OPTION);
		if (inf == JOptionPane.OK_OPTION) {
			jDialog.dispose();
		} else {

		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
