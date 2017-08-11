package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

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
public class HomeFrameAction implements ActionListener {

	// 主界面报价任务的引用
	private JTable jt_quoteTask;

	public HomeFrameAction() {
	}

	public HomeFrameAction(JTable jt_quoteTask) {
		this.jt_quoteTask = jt_quoteTask;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// 如果command为创建任务，则打开创建任务窗口
		if ("createTask".equals(command)) {
			FrameGoUtils.creatTask(jt_quoteTask);
		} else if ("deleteTask".equals(command)) {
			//获取Table中被选中的行序号
			final int row = jt_quoteTask.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(null, "没有选中需要删除的报价任务,请选中后再进行删除操作！",
						"提示信息", JOptionPane.WARNING_MESSAGE);
			}else{
				int flag = JOptionPane.showConfirmDialog(null, "点击确认按钮，将会删除所选中的报价任务，包括报价任务下的所有项目以及检验批，是否确认删除？",
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
		} else if ("creatProject".equals(command)) {
			FrameGoUtils.creatProject();
		}
	}
}
