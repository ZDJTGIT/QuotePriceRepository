package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.SysInspectionContent;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.service.impl.SysInspectionContenServiceImpl;

public class CreateContentAction implements ActionListener {

	private JTable jt_inspectionContent;
	private JDialog jaDialog;
	private InspectionContent inspectionContent;
	private Integer inspectionid;
	// 添加添加检验内容表
	private JTable tab_viw;
	private JTextField textField;

	public CreateContentAction() {

	}
	
	public CreateContentAction(JTextField textField, JTable tab_viw) {
		this.textField = textField;
		this.tab_viw = tab_viw;
	}

	public CreateContentAction(JDialog jaDialog) {
		this.jaDialog = jaDialog;
	}

	public CreateContentAction(JTable jt_inspectionContent, Integer inspectionid
			, JDialog jaDialog, JTable tab_viw) {
		this.jt_inspectionContent = jt_inspectionContent;
		this.jaDialog = jaDialog;
		this.inspectionid = inspectionid;
		this.tab_viw = tab_viw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// 如果command为确认添加任务，则打开创建任务窗口
		if ("add".equals(command)) {
			Object object[] = new Object[9];
			int num = tab_viw.getSelectedRow();
			if(num < 0){
				JOptionPane.showMessageDialog(null, "请选择需要添加的检验内容！",
						"提示信息", JOptionPane.ERROR_MESSAGE);
			}else{
			object[0] = tab_viw.getValueAt(num, 1);
			object[1] = tab_viw.getValueAt(num, 2);
			object[2] = tab_viw.getValueAt(num, 3);
			object[3] = tab_viw.getValueAt(num, 4);
			object[4] = tab_viw.getValueAt(num, 5);
			object[5] = tab_viw.getValueAt(num, 6);
			object[6] = tab_viw.getValueAt(num, 7);
			object[7] = tab_viw.getValueAt(num, 8);
			object[8] = tab_viw.getValueAt(num, 9);

			// "序号", "系统检验内容ID", "检验内容", "抽样依据", "抽样数量范围",
			// "抽样数量", "报价依据", "单个检测对象数量范围", "单个检验对象实施数量", "计费单位", "收费标准"
			inspectionContent = new InspectionContent(object);
		    //系统默认数据和计算生成数据
			inspectionContent.setSourceId((Integer)tab_viw.getValueAt(num, 0));
			inspectionContent.setChargeStandardUnit("元");
			inspectionContent.setBatchId(inspectionid);
			//给定算法传入总金额
			inspectionContent.setInspectionContentAmount(100000000.00);
			// 启动任务线程往数据库插入数据
			new SwingWorker<InspectionContent, Void>() {
				protected InspectionContent doInBackground() throws Exception {
						return new InspectionContentServiceImpl()
								.createInspectionContent(inspectionContent);
				}
				protected void done() {
					try {
						// 获得当前插入的检验内容并展示在新增界面
						inspectionContent = get();
						if (null != inspectionContent) {
							DefaultTableModel model = (DefaultTableModel) jt_inspectionContent.getModel();
								JOptionPane.showMessageDialog(null, "任务添加成功！",
										"提示信息", JOptionPane.PLAIN_MESSAGE);
								Vector<Object> rowData = new Vector<Object>();
								rowData.add(inspectionContent.getId());
								rowData.add(inspectionContent
										.getInspectionContentName());
								rowData.add(inspectionContent
										.getSampleQuantity());
								rowData.add(inspectionContent
										.getSingleObjectQuantity());
								rowData.add(inspectionContent
										.getChargeStandard());
								model.addRow(rowData);
								// 新增后选中最后一行
								jt_inspectionContent.setRowSelectionInterval(
										jt_inspectionContent.getRowCount() - 1,
										jt_inspectionContent.getRowCount() - 1);
							jaDialog.dispose();
						} else {
								JOptionPane.showMessageDialog(null, "任务添加失败！",
										"提示信息", JOptionPane.ERROR_MESSAGE);
						}
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				};
				
			}.execute();
		}
		} else if ("no".equals(command)) {
			int inf = JOptionPane.showConfirmDialog(null, "确定退出么？", "取消添加检验内容",
					JOptionPane.OK_OPTION);
			if (inf == JOptionPane.OK_OPTION) {
				jaDialog.dispose();
			}//添加模糊查询操作
		}else if("sertch".equals(command)){
			//根据传入的字符串做模糊查询，查出的数据展示到表中
			new SwingWorker <List<SysInspectionContent>, SysInspectionContent> () {
				@Override
				protected List<SysInspectionContent> doInBackground()
						throws Exception {
					return new SysInspectionContenServiceImpl()
					.selectAllBlurrySysInspectionContent(textField.getText());
				}
				protected void done() {
					try {
						List<SysInspectionContent> sysInspectionContent = get();
						if(null == sysInspectionContent){
							JOptionPane.showMessageDialog(null, "查询结果不存在！", "提示信息",
									JOptionPane.WARNING_MESSAGE);
						}else{
							DefaultTableModel model = (DefaultTableModel) tab_viw
									.getModel();
							model.setRowCount(0);
							for (SysInspectionContent sic : sysInspectionContent) {
								Vector<Object> dataRow = new Vector<Object>();
								dataRow.add(sic.getId());
								dataRow.add(sic.getInspectionContentName());
								dataRow.add(sic.getSampleBasicId());
								dataRow.add(sic.getSampleQuantityRange());
								dataRow.add(sic.getSampleQuantity());
								dataRow.add(sic.getQuoteBasisId());
								dataRow.add(sic.getSingleQuantityRange());
								dataRow.add(sic.getSingleObjectQuantity());
								dataRow.add(sic.getChargeUnit());
								dataRow.add(sic.getChargeStandard());
								model.addRow(dataRow);
								tab_viw.setRowSelectionInterval(0, 0);// 选中第一行
							}
						}
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
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
