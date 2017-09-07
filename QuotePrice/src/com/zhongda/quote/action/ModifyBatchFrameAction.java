package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.service.impl.InspectionBatchServiceImpl;

/**
 *<p>修改检验批事件处理类/p>
 * @author zmdeng
 * @date 2017年9月4日
 */
public class ModifyBatchFrameAction implements ActionListener {

	public JDialog dialog;
	private JTable jt_inspectionBatch;
	private JTextField jtf_batchName;

	public ModifyBatchFrameAction(JDialog dialog) {
		this.dialog = dialog;
	}

	public ModifyBatchFrameAction(JDialog dialog, JTable jt_inspectionBatch,
			JTextField jtf_batchName) {
		this.dialog = dialog;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jtf_batchName = jtf_batchName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commandName = e.getActionCommand();
		if ("confirm".equals(commandName)) {
			int flag = JOptionPane.showConfirmDialog(null, "确定提交?", "提交检验批",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				updateInspectionBatch();
			}
		} else if ("cancel".equals(commandName)) {
			int flag = JOptionPane.showConfirmDialog(null, "取消项目？", "取消检验批",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				dialog.dispose();
			}
		}
	}

	private void updateInspectionBatch() {
		// 获取当前选中检验批ID
		int batchId = Integer.valueOf(jtf_batchName.getName());
		String batchName = jtf_batchName.getText();
		final InspectionBatch inspectionBatch = new InspectionBatch(batchId, batchName);
		new SwingWorker<InspectionBatch, Void>(){
			@Override
			protected InspectionBatch doInBackground() throws Exception {
				return new InspectionBatchServiceImpl().updateBatch(inspectionBatch);
			}

			protected void done() {
				InspectionBatch inspectionBatch;
				try {
					inspectionBatch = get();
					int batchRow = jt_inspectionBatch.getSelectedRow();
					jt_inspectionBatch.setValueAt(inspectionBatch.getInspectionBatchName(), batchRow, 1);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();
		dialog.dispose();
	}

}
