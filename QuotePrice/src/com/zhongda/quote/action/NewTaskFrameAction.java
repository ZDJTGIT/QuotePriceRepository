package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;
import com.zhongda.quote.view.DateField;

/**
 * 
 * <p>
 *com.zhongda.quote.view.NewTaskFrame 窗口中所有监听事件
 * <p>
 * 
 * @author 研发中心-Mikepolite<1011592269@qq.com>
 * @sine 2017年8月9日
 */
public class NewTaskFrameAction implements ActionListener , WindowListener{

	private JTextField jtf_2;
	private JTextField jtf_3;
	private JComboBox cjb;
	private JTextArea jtar;
	private DateField datefield;
	private String fer_1 = null;
	private String fer_2 = null;
	private String fer_3 = null;
	private Integer fer_4 = 0;
	private String fer_5 = null;
	private int allsel = 0;
	private String wuat;
	public NewTaskFrameAction() {

	}

	/**
	 * 
	 * @param jtf_2
	 * @param jtf_3
	 * @param cjb
	 * @param jtar
	 * @param datefield
	 */
	public NewTaskFrameAction(JTextField jtf_2, JTextField jtf_3,
			JComboBox cjb, JTextArea jtar, DateField datefield) {
		this.jtf_2 = jtf_2;
		this.jtf_3 = jtf_3;
		this.cjb = cjb;
		this.jtar = jtar;
		this.datefield = datefield;
	}
	
	@Override
	/**
	 * 确定提交和退出按钮监听事件
	 */
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		if (name.equalsIgnoreCase("ton1")) {
			fer_1 = jtf_2.getText();
			fer_2 = jtf_3.getText();
			JTextField jtx = (JTextField) datefield.getComponent(0);
			fer_3 = jtx.getText();
			fer_4 = cjb.getSelectedIndex() + 1;
			fer_5 = jtar.getText();
			System.out.println(fer_1);
			System.out.println(fer_2);
			System.out.println(fer_3);
			System.out.println(fer_4);
			System.out.println(fer_5);
			if (fer_1.equalsIgnoreCase("") | fer_2.equalsIgnoreCase("")
					| fer_3.equalsIgnoreCase("") | fer_4 == 0
					| fer_5.equalsIgnoreCase("")) {
				Object[] options = { "确认", "取消" };
				JOptionPane.showOptionDialog(null, "请完善您的任务信息", "警告",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else {
				JOptionPane.showMessageDialog(null, "任务创建成功！", "提示信息",
						JOptionPane.PLAIN_MESSAGE);
				// 把获取的任务信息储存到数据库
				QuoteTask quoteTask = new QuoteTask("ZD201708080008", fer_1,
						fer_5, fer_4, fer_2);
				// String msg = new QuoteTaskServiceImpl()
				// .createQuoteTask(quoteTask);
				// System.out.println(msg);
				new SwingWorker<QuoteTask, QuoteTask>() {

					protected QuoteTask doInBackground() throws Exception {
						System.out.println("把数据存入数据库");
						String msg = new QuoteTaskServiceImpl()
								.createQuoteTask(quoteTask);
						System.out.println(msg);
						return quoteTask;
					}
					protected void done() {
						System.out.println("更新UI界面");
					};
				}.execute();
			}
		} else {
			int inf = JOptionPane.showConfirmDialog(null, "确定退出么？",
					"Attention", JOptionPane.OK_OPTION);
			if (inf == JOptionPane.OK_OPTION) {
				System.exit(0);
			} else {

			}
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		int inf = JOptionPane.showConfirmDialog(null, "确定关闭窗口？",
				"Attention", JOptionPane.OK_OPTION);
		if (inf == JOptionPane.OK_OPTION) {
			System.exit(0);
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
