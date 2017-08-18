package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;

public class CreateContentAction implements ActionListener {

	private JTable jt_inspectionContent;
	private JDialog jaDialog;
	private InspectionContent inspectionContent;
	private JTextField textField_1, textField_3, textField_4,
			textField_5;
	private Integer inspectionid;
	private boolean isCreate;

	public CreateContentAction() {

	}

	public CreateContentAction(JDialog jaDialog) {
		this.jaDialog = jaDialog;
	}

	public CreateContentAction(Integer inspectionid,JDialog jaDialog, JTable jt_inspectionContent,
			JTextField textField_1,
			JTextField textField_3, JTextField textField_4,
			JTextField textField_5, boolean isCreate) {
		this.inspectionid = inspectionid;
		this.textField_1 = textField_1;
		this.textField_3 = textField_3;
		this.textField_4 = textField_4;
		this.textField_5 = textField_5;
		this.jt_inspectionContent = jt_inspectionContent;
		this.jaDialog = jaDialog;
		this.isCreate = isCreate;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// 如果command为确认创建任务，则打开创建任务窗口
		if ("yes".equals(command)) {
			String inspectionname = textField_1.getText();
			String samp = textField_3.getText();
			String indiv = textField_4.getText();
			String charges = textField_5.getText();
			if(isNumeric(samp)&&isNumeric(indiv)&&isNumeric(charges)){
				// 判断用户填写的任务信息是否完整
				if (null != inspectionname && !"".equals(inspectionname)
						&& null != samp
						&& !"".equals(samp.trim()) && null != indiv && !"".equals(indiv)
						&& null != charges && !"".equals(charges)) {
					Integer samps = Integer.parseInt(textField_3.getText());
					Integer indivs = Integer.parseInt(textField_4.getText());
					Integer charge = Integer.parseInt(textField_5.getText());
					// 把获取的任务信息转换为model对象
					inspectionContent = new InspectionContent(inspectionname,
							samps, indivs, charge);
					//如果是修改，inspectionContent对象加上id
					if(!isCreate){
						int row = jt_inspectionContent.getSelectedRow();
						int contentId = Integer.parseInt(String.valueOf(jt_inspectionContent.getValueAt(row, 0)));
						inspectionContent.setId(contentId);
					}
					//从数据库提取数据，占位
					inspectionContent.setSampleQuantityRange("1-8");
					inspectionContent.setSampleBasisId(456);
					inspectionContent.setSingleQuantityRange("1-9");
					inspectionContent.setChargeUnit("光年");
					inspectionContent.setChargeStandardUnit("亿");
					inspectionContent.setQuoteBasisId(123);
					//将检验批ID写入数据库与检验批关联
					inspectionContent.setBatchId(inspectionid);
					inspectionContent.setSourceId(1);
					inspectionContent.setInspectionContentAmount(100000000.00);
					// 启动任务线程往数据库插入数据
					new SwingWorker<InspectionContent, Void>() {
						protected InspectionContent doInBackground()
								throws Exception {
							if(isCreate){
								return new InspectionContentServiceImpl()
								.createInspectionContent(inspectionContent);
							}else{
								return new InspectionContentServiceImpl()
								.updateInspectionContent(inspectionContent);
							}
							
						}
	
						protected void done() {
							try {
								inspectionContent = get();
								if (null != inspectionContent) {
									DefaultTableModel model = (DefaultTableModel) jt_inspectionContent
											.getModel();
									if(isCreate){
										JOptionPane.showMessageDialog(null, "任务创建成功！",
												"提示信息", JOptionPane.PLAIN_MESSAGE);
		
										Vector<Object> rowData = new Vector<Object>();
										rowData.add(inspectionContent
												.getId());
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
									}else{
										JOptionPane.showMessageDialog(null, "任务修改成功！",
												"提示信息", JOptionPane.PLAIN_MESSAGE);
										int row = jt_inspectionContent.getSelectedRow();
										model.setValueAt(inspectionContent.getInspectionContentName(), row, 1);
										model.setValueAt(inspectionContent.getSampleQuantity(), row, 2);
										model.setValueAt(inspectionContent.getSingleObjectQuantity(), row, 3);
										model.setValueAt(inspectionContent.getChargeStandard(), row, 4);
									}
									jaDialog.dispose();
								} else {
									if(isCreate){
										JOptionPane.showMessageDialog(null, "任务创建失败！",
												"提示信息", JOptionPane.ERROR_MESSAGE);
									}else{
										JOptionPane.showMessageDialog(null, "任务修改失败！",
												"提示信息", JOptionPane.ERROR_MESSAGE);
									}
								}
							} catch (InterruptedException | ExecutionException e) {
								e.printStackTrace();
							}
						};
						
					}.execute();
				} else {
					JOptionPane.showMessageDialog(null, "请完善您的检验内容信息！", "提示信息",
							JOptionPane.WARNING_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "请按正确的格式填写！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} 	
		} else if ("no".equals(command)) {
			int inf = JOptionPane.showConfirmDialog(null, "确定退出么？", "取消创建检验内容",
					JOptionPane.OK_OPTION);
			if (inf == JOptionPane.OK_OPTION) {
				jaDialog.dispose();
			}
		}
	}
	
	public static boolean isNumeric(String str){
		for (int i = str.length() ; --i>=0 ; ){ 
			if (!Character.isDigit(str.charAt ( i ) ) ){
			return false;
			}
		}
		return true;
	}

}
