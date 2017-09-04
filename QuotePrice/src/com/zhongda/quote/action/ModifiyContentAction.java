package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.utils.StringUtil;

public class ModifiyContentAction implements ActionListener {

	private JTable jt_inspectionContent;
	private JDialog jaDialog;
	private InspectionContent inspectionContent;
	private JTextField textField_1,textField_3, textField_4,
			textField_5;
	private String SampleQuantityRange;
	private String SingleQuantityRange;

	public ModifiyContentAction() {

	}

	public ModifiyContentAction(JDialog jaDialog) {
		this.jaDialog = jaDialog;
	}

	//检验批ID 
	public ModifiyContentAction(JDialog jaDialog, JTable jt_inspectionContent,
			      				JTextField textField_1,JTextField textField_3, 
			      				JTextField textField_4,JTextField textField_5,
			      				String SampleQuantityRange, String SingleQuantityRange) {
		this.textField_1 = textField_1;
		this.textField_3 = textField_3;
		this.textField_4 = textField_4;
		this.textField_5 = textField_5;
     	this.jt_inspectionContent = jt_inspectionContent;
		this.jaDialog = jaDialog;
		this.SampleQuantityRange = SampleQuantityRange;
		this.SingleQuantityRange = SingleQuantityRange;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// 如果command为确认，则提交当前修改的信息 
		if ("yes".equals(command)) {
			String inspectionname = textField_1.getText();
			String samp = textField_3.getText();
			String indiv = textField_4.getText();
			String charges = textField_5.getText();
			// 判断用户填写的是否是数字
			if(isNumeric(samp)&&isNumeric(indiv)&&isNumeric(charges)){
				// 判断用户填写的任务信息是否完整
				if (null != inspectionname && !"".equals(inspectionname)
						&& null != samp
						&& !"".equals(samp.trim()) && null != indiv && !"".equals(indiv)
						&& null != charges && !"".equals(charges)) {
					//判断用户填写的数值是否在取值范围之内
					if(StringUtil.stringToInteger(samp)>=StringUtil.stringToMinInteger(SampleQuantityRange)
					  &StringUtil.stringToInteger(samp)<=StringUtil.stringToMaxInteger(SampleQuantityRange)
					  &StringUtil.stringToInteger(indiv)>=StringUtil.stringToMinInteger(SingleQuantityRange)
					  &StringUtil.stringToInteger(indiv)<=StringUtil.stringToMaxInteger(SingleQuantityRange)){
					Integer samps = Integer.parseInt(textField_3.getText());
					Integer indivs = Integer.parseInt(textField_4.getText());
					Integer charge = Integer.parseInt(textField_5.getText());
					// 把获取的任务信息转换为model对象
					inspectionContent = new InspectionContent(inspectionname,
							samps, indivs, charge);
					//如果是修改，inspectionContent对象加上id
					
				    int row = jt_inspectionContent.getSelectedRow();
					int contentId = Integer.parseInt(String.valueOf(jt_inspectionContent.getValueAt(row, 0)));
					inspectionContent.setId(contentId);
					
					new SwingWorker<InspectionContent, Void>() {
						protected InspectionContent doInBackground()
								throws Exception {
								return new InspectionContentServiceImpl()
								.updateInspectionContent(inspectionContent);
						}
						protected void done() {
							try {
								inspectionContent = get();
								if (null != inspectionContent) {
									DefaultTableModel model = (DefaultTableModel) jt_inspectionContent
											.getModel();
										JOptionPane.showMessageDialog(null, "任务修改成功！",
												"提示信息", JOptionPane.PLAIN_MESSAGE);
										int row = jt_inspectionContent.getSelectedRow();
										model.setValueAt(inspectionContent.getInspectionContentName(), row, 1);
										model.setValueAt(inspectionContent.getSampleQuantity(), row, 2);
										model.setValueAt(inspectionContent.getSingleObjectQuantity(), row, 3);
										model.setValueAt(inspectionContent.getChargeStandard(), row, 4);
									jaDialog.dispose();
								} else {
										JOptionPane.showMessageDialog(null, "任务修改失败！",
												"提示信息", JOptionPane.ERROR_MESSAGE);
								}
							} catch (InterruptedException | ExecutionException e) {
								e.printStackTrace();
							}
						};
						
					}.execute();
					}else{
						JOptionPane.showMessageDialog(null, "请确保您输入的数字在取值范围之内！", "提示信息",
								JOptionPane.WARNING_MESSAGE);
					}
					} else {
					JOptionPane.showMessageDialog(null, "您的修改为空！", "提示信息",
							JOptionPane.WARNING_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "请确保您的输入为数字", "提示信息",
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
