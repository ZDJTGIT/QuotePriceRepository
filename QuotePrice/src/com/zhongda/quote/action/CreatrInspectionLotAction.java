package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.zhongda.quote.view.TestContentsIist;

public class CreatrInspectionLotAction implements ActionListener {

	private JDialog jDialog;
	private JFrame frameContentsIist;
	private JTextField textField_belong, textField_name, textField_search,
			textField_offer;

	public CreatrInspectionLotAction() {

	}

	public CreatrInspectionLotAction(JDialog jDialog) {

		this.jDialog = jDialog;
	}
	
	public CreatrInspectionLotAction(JFrame frameContentsIist) {

		this.frameContentsIist = frameContentsIist;
	}
	
	public CreatrInspectionLotAction(JTextField textField_belong,
			JTextField textField_name, JTextField textField_search,
			JTextField textField_offer, JDialog jDialog) {

		this.textField_belong = textField_belong;
		this.textField_name = textField_name;
		this.textField_offer = textField_offer;
		this.textField_search = textField_search;
		this.jDialog = jDialog;
	}

	@Override
	// 鼠标单击搜索按钮，弹出TestContentsIist界面。
	public void actionPerformed(ActionEvent e) {
		int i;
		String command = e.getActionCommand();
		if ("search".equals(command)) {// 鼠标单击搜索按钮，弹出TestContentsIist界面。
			TestContentsIist TestContentsIist = new TestContentsIist();
			TestContentsIist.frameContentsIist.setVisible(true);
		} else if ("yes".equals(command)) {// 鼠标单击确定按钮，判断数据完整性，提交数据
			    String inspectionlot[]= { textField_belong.getText(),
				textField_name.getText(), textField_offer.getText(),
				textField_search.getText()};
			for(i = 0;i<4;i++){
				System.out.println(inspectionlot[i]);
			if("".equals(inspectionlot[i])){
				JOptionPane.showMessageDialog(null, "请完善您的任务信息！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
				break;
			}
			if(i==3){
			System.out.println("执行储存数据操作成功！");
			//若数据都不为空，下一步执行存数据入数据库操作。
			JOptionPane.showMessageDialog(null, "执行储存数据操作成功！", "提示信息",
					JOptionPane.WARNING_MESSAGE);
			jDialog.dispose();
			}
			}
		} else if ("no".equals(command)) {// 鼠标单击取消按钮，提示是否确定，关闭窗口
			int inf = JOptionPane.showConfirmDialog(null, "确定退出么？",
					"Attention", JOptionPane.OK_OPTION);
			if (inf == JOptionPane.OK_OPTION) {
				jDialog.dispose();
			} else {
			}
		}

	}
}