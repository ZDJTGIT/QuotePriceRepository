package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;
import com.zhongda.quote.utils.FrameGoUtils;

/**
 *
 * <p>
 * HomeFrame事件类
 * </p>
 *
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月10日
 */
public class HomeFrameAction implements ActionListener, MouseMotionListener {

	// 主界面报价任务的引用
	private JTable jt_quoteTask;
	// 主界面查询任务搜索任务名称
	private JTextField jtf_queryTaskName;

	public HomeFrameAction() {
	}

	public HomeFrameAction(JTable jt_quoteTask) {
		this.jt_quoteTask = jt_quoteTask;
	}

	public HomeFrameAction(JTable jt_quoteTask, JTextField jtf_queryTaskName) {
		this.jt_quoteTask = jt_quoteTask;
		this.jtf_queryTaskName = jtf_queryTaskName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// 如果command为创建任务，则打开创建任务窗口
		if ("createTask".equals(command)) {
			FrameGoUtils.creatTask(jt_quoteTask);
		} else if ("deleteTask".equals(command)) {
			deleteQuoteTask(jt_quoteTask);
		}  else if ("queryTask".equals(command)) {
			queryQuotePrice(jt_quoteTask, jtf_queryTaskName);
		} else if ("creatProject".equals(command)) {
			FrameGoUtils.creatProject();
		} else if ("addInspection".equals(command)) {
			FrameGoUtils.creatInspection();
		}
	}


	/**
	 * 查询报价任务
	 * @param jt_quoteTask 显示任务的列表
	 * @param jtf_queryTaskName 存放查询条件的任务名称
	 */
	private void queryQuotePrice(JTable jt_quoteTask,
			JTextField jtf_queryTaskName) {
		final String taskName = jtf_queryTaskName.getText();
		if(null != taskName && !"".equals(taskName)){
			new SwingWorker<List<QuoteTask>, Void>(){

				@Override
				protected List<QuoteTask> doInBackground() throws Exception {
					return new QuoteTaskServiceImpl().queryQuoteTaskByName(taskName);
				}

				protected void done() {
					List<QuoteTask> taskList;
					try {
						taskList = get();
						//jt_quoteTask.updateUI();
						DefaultTableModel model = (DefaultTableModel) jt_quoteTask.getModel();
						//清除原有数据
						model.getDataVector().clear();
						// 将数据添加到table
						if (null != taskList && taskList.size() > 0) {
							for (QuoteTask quoteTask : taskList) {
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
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				};

			}.execute();
		}else{
			JOptionPane.showMessageDialog(null, "请填写任务名称！", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	/**
	 * 删除报价任务
	 * @param jt_quoteTask 显示任务的列表
	 */
	private void deleteQuoteTask(JTable jt_quoteTask) {
		// 获取Table中被选中的行序号
		final int row = jt_quoteTask.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null,
					"没有选中需要删除的报价任务,请选中后再进行删除操作！", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int flag = JOptionPane.showConfirmDialog(null,
					"点击确认按钮，将会删除所选中的报价任务，包括报价任务下的所有项目以及检验批，是否确认删除？",
					"删除报价任务", JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				Object value = jt_quoteTask.getValueAt(row, 0);
				final Integer id = Integer.valueOf(String.valueOf(value));
				// 启动任务线程删除选中报价任务
				new SwingWorker<Boolean, Void>() {
					protected Boolean doInBackground() throws Exception {
						return new QuoteTaskServiceImpl()
								.deleteQuoteTask(id);
					}

					protected void done() {
						try {
							boolean flag = get();
							if (flag) {
								JOptionPane.showMessageDialog(null,
										"报价任务删除成功！", "提示信息",
										JOptionPane.PLAIN_MESSAGE);

								DefaultTableModel model = (DefaultTableModel) jt_quoteTask
										.getModel();
								model.removeRow(row);
							} else {
								JOptionPane.showMessageDialog(null,
										"报价任务删除失败！", "提示信息",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
					};
				}.execute();
			}
		}
	}

	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		int row = jt_quoteTask.rowAtPoint(e.getPoint());
		int col = jt_quoteTask.columnAtPoint(e.getPoint());
		if (row > -1 && col > -1) {
			Object value = jt_quoteTask.getValueAt(row, col);
			if (null != value && !"".equals(value))
				jt_quoteTask.setToolTipText(value.toString());// 悬浮显示单元格内容
			else
				jt_quoteTask.setToolTipText(null);// 关闭提示
		}

	}

}
