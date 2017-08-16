package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.zhongda.quote.action.CreateTaskFrameAction;
import com.zhongda.quote.view.uiutils.Chooser;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;

/**
 *
 * <p>
 * 创建任务窗口
 * <p>
 *
 * @author 研发中心-Mikepolite<1011592269@qq.com>
 * @sine 2017年8月9日
 */
public class CreateTaskFrame {

	public JDialog jDialog;

	private JLabel ble_1;
	private JLabel ble_2;
	private JLabel ble_3;

	private JLabel ble_5;
	private JTextField jtf_taskName;
	private JTextField jtf_createUser;
	private JButton bt_confirm;
	private JButton bt_cancel;
	// 主界面报价任务的引用
	private JTable jt_quoteTask;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPanel panel_2;
	private JScrollPane scrollPane_1;
	private JTextArea jta_taskDescription;
	private JSeparator separator_1;
	private JTextField jtf_date;
	private boolean isCreate;

	public CreateTaskFrame() {
		init();

	}

	public CreateTaskFrame(JTable jt_quoteTask, boolean isCreate) {
		this.jt_quoteTask = jt_quoteTask;
		this.isCreate = isCreate;
		init();
	}

	public void init() {

		// SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		jDialog = new JDialog();
		jDialog.setBounds(0, 0, 500, 442);
		jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ImageIcon icon = new ImageIcon("images\\zdLogo1.png");
		jDialog.setTitle("中大检测");
		jDialog.setResizable(false);
		jDialog.setLocationRelativeTo(null);// 设置界面居中
		jDialog.setIconImage(icon.getImage());
		jDialog.setModal(true);
		jDialog.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		jDialog.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		panel_1 = new JpaneColorAndPhoto("images/bookpen.png", 430, 2, 48, 48);
		panel_1.setBounds(0, 0, 494, 52);
		panel.add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel = new JLabel("新建任务");
		lblNewLabel.setBounds(10, 10, 77, 15);
		lblNewLabel.setFont(new Font("黑体", 1, 15));
		panel_1.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("填写任务信息");
		lblNewLabel_1.setBounds(33, 25, 84, 15);
		panel_1.add(lblNewLabel_1);

		Chooser ser = Chooser.getInstance();
		jtf_date = new JTextField();
		jtf_date.setText(getTodayDate());
		jtf_date.setFont(new Font("宋体", 1, 15));
		jtf_date.setEditable(false);
		ser.register(jtf_date);
		jtf_date.setBounds(26, 193, 107, 20);
		panel.add(jtf_date);

		bt_confirm = new JButton();
		bt_confirm.setBounds(282, 375, 93, 23);
		panel.add(bt_confirm);
		bt_confirm.setText("确认");
		bt_confirm.setFont(new Font("新宋体", Font.PLAIN, 13));

		bt_cancel = new JButton();
		bt_cancel.setBounds(378, 375, 93, 23);
		panel.add(bt_cancel);
		bt_cancel.setText("取消");

		bt_cancel.setFont(new Font("新宋体", Font.PLAIN, 13));

		panel_2 = new JPanel();
		panel_2.setBounds(26, 250, 445, 92);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		jta_taskDescription = new JTextArea();
		jta_taskDescription.setFont(new Font("宋体", 0, 12));
		jta_taskDescription.setLineWrap(true);// 换行
		jta_taskDescription.setWrapStyleWord(true);// 断行不断字
		scrollPane_1.setViewportView(jta_taskDescription);

		separator_1 = new JSeparator();
		separator_1.setBounds(55, 360, 435, 2);
		panel.add(separator_1);

		ble_5 = new JLabel("任务描述（请对您的任务主要内容进行描述）");
		ble_5.setBounds(26, 220, 260, 20);
		panel.add(ble_5);

		ble_3 = new JLabel("创建时间（当前显示系统默认时间）");
		ble_3.setBounds(26, 163, 260, 20);
		panel.add(ble_3);

		jtf_createUser = new JTextField();
		jtf_createUser.setBounds(26, 128, 445, 25);
		panel.add(jtf_createUser);

		ble_2 = new JLabel("创建人（请填写您的真实姓名）");
		ble_2.setBounds(26, 110, 260, 15);
		panel.add(ble_2);

		jtf_taskName = new JTextField();
		jtf_taskName.setBounds(26, 80, 445, 25);
		panel.add(jtf_taskName);

		ble_1 = new JLabel();
		ble_1.setBounds(26, 62, 260, 15);
		ble_1.setText("任务名称（请输入完整任务名称）");
		panel.add(ble_1);

		lblNewLabel_2 = new JLabel("中大检测");
		lblNewLabel_2.setBounds(4, 347, 54, 23);
		panel.add(lblNewLabel_2);

		// 添加取消按钮事件
		bt_cancel.setActionCommand("cancelCreateTask");
		bt_cancel.addActionListener(new CreateTaskFrameAction(jDialog));
		// 添加确认按钮事件
		bt_confirm.setActionCommand("confirmCreateTask");
		bt_confirm.addActionListener(new CreateTaskFrameAction(jtf_taskName,
				jtf_createUser, jtf_date, jta_taskDescription, jt_quoteTask, jDialog, isCreate));
		//如果是修改报价任务，则填充需修改的值
		if(!isCreate){
			int row = jt_quoteTask.getSelectedRow();
			jtf_taskName.setText(String.valueOf(jt_quoteTask.getValueAt(row, 2)));
			jtf_createUser.setText(String.valueOf(jt_quoteTask.getValueAt(row, 4)));
			jtf_date.setText(String.valueOf(jt_quoteTask.getValueAt(row, 5)));
			jta_taskDescription.setText(String.valueOf(jt_quoteTask.getValueAt(row, 3)));
		}

	}

	public String getTodayDate() {

		Date now = new Date(); // 获取当前时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 格式化当前日期
		String date = dateFormat.format(now);
		return date;

	}

}
