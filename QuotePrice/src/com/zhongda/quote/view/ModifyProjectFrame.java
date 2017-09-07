package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.action.ModifyProjectFrameAction;
import com.zhongda.quote.service.impl.AddressServiceImpl;
import com.zhongda.quote.utils.SkinUtil;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;

/**
 *<p>修改项目窗口</p>
 * @author zmdeng
 * @date 2017年9月4日
 */
public class ModifyProjectFrame {

	public JDialog dialog;
	private JPanel jPanel;
	private JpaneColorAndPhoto jpanel_up;
	private JLabel jlb_jpup_1;
	private JLabel jlb_jpup_2;
	private JLabel lblNewLabel;
	private JTextField jtf_taskName;
	private JTextField jtf_projectName;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JLabel label_1;
	private JTextField jtf_projectAmount;
	private JLabel label_2;
	private JTextField jtf_otherAmount;
	private JLabel label_3;
	private JSeparator separator;
	private JButton jbt_confirm;
	private JButton jbt_cancel;
	private JComboBox<String> jcb_province;
	private JComboBox<String> jcb_city;
	private JComboBox<String> jcb_county;
	private JLabel ble_4;
	private JComboBox<String> jcb_industry;
	private JTable jt_quoteTask;
	private JTable jt_quoteProject;
	private double oldProjectAmount;

	public ModifyProjectFrame(JTable jt_quoteTask, JTable jt_quoteProject) {
		this.jt_quoteProject = jt_quoteProject;
		this.jt_quoteTask = jt_quoteTask;
		init();
	}

	public void init() {
		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setBounds(0, 0, 500, 530);
		dialog.setModal(true);// 此窗口至于前端
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.getContentPane().setLayout(new BorderLayout(0, 0));
		dialog.setTitle("中大检测");
		dialog.setIconImage(dialog.getToolkit().getImage("images/zdLogo1.png"));

		jPanel = new JPanel();
		dialog.getContentPane().add(jPanel, BorderLayout.CENTER);
		jPanel.setLayout(null);

		jpanel_up = new JpaneColorAndPhoto("images/bookpen.png", 430, 2, 48, 48);
		jpanel_up.setBounds(0, 0, 494, 52);
		jPanel.add(jpanel_up);
		jpanel_up.setLayout(null);

		jlb_jpup_1 = new JLabel("新建项目");
		jlb_jpup_1.setBounds(10, 10, 77, 15);
		jlb_jpup_1.setFont(new Font("黑体", 1, 15));
		jpanel_up.add(jlb_jpup_1);

		jlb_jpup_2 = new JLabel("填写项目信息");
		jlb_jpup_2.setBounds(33, 25, 84, 15);
		jpanel_up.add(jlb_jpup_2);

		label = new JLabel("所属任务");
		label.setBounds(26, 62, 54, 15);
		jPanel.add(label);

		jtf_taskName = new JTextField();
		jtf_taskName.setBounds(26, 85, 445, 25);
		jtf_taskName.setEnabled(false);
		jPanel.add(jtf_taskName);
		jtf_taskName.setColumns(10);

		lblNewLabel = new JLabel("项目名称");
		lblNewLabel.setBounds(26, 118, 54, 15);
		jPanel.add(lblNewLabel);

		jtf_projectName = new JTextField();
		jtf_projectName.setBounds(26, 138, 445, 25);
		jPanel.add(jtf_projectName);
		jtf_projectName.setColumns(10);

		ble_4 = new JLabel("所属行业（请选择您新建任务涉及行业）");
		ble_4.setBounds(26, 168, 216, 20);
		jPanel.add(ble_4);

		jcb_industry = new JComboBox<String>();
		jcb_industry.setFont(new Font("新宋体", 0, 15));
		jcb_industry.setBounds(26, 193, 207, 25);
		jcb_industry.setEnabled(false);
		jPanel.add(jcb_industry);

		lblNewLabel_1 = new JLabel("项目所属地点");
		lblNewLabel_1.setBounds(26, 223, 89, 20);
		jPanel.add(lblNewLabel_1);

		jcb_province = new JComboBox<String>();
		jcb_province.setBounds(26, 248, 145, 21);
		jcb_province.setEnabled(false);
		jPanel.add(jcb_province);

		jcb_city = new JComboBox<String>();
		jcb_city.setBounds(177, 248, 145, 21);
		jcb_city.setEnabled(false);
		jPanel.add(jcb_city);

		jcb_county = new JComboBox<String>();
		jcb_county.setBounds(326, 248, 145, 21);
		jcb_county.setEnabled(false);
		jPanel.add(jcb_county);

		label_1 = new JLabel("项目报价");
		label_1.setBounds(26, 278, 54, 15);
		jPanel.add(label_1);

		jtf_projectAmount = new JTextField();
		jtf_projectAmount.setBounds(26, 298, 445, 25);
		jtf_projectAmount.setEnabled(false);
		jPanel.add(jtf_projectAmount);
		jtf_projectAmount.setColumns(10);

		label_2 = new JLabel("其他费用报价");
		label_2.setBounds(26, 336, 101, 15);
		jPanel.add(label_2);

		jtf_otherAmount = new JTextField();
		jtf_otherAmount.setBounds(26, 358, 445, 25);
		jPanel.add(jtf_otherAmount);
		jtf_otherAmount.setColumns(10);

		label_3 = new JLabel("中大检测");
		label_3.setBounds(4, 398, 54, 23);
		jPanel.add(label_3);

		separator = new JSeparator();
		separator.setBounds(55, 410, 435, 2);
		jPanel.add(separator);

		jbt_confirm = new JButton("确认");
		jbt_confirm.setFocusPainted(false);
		jbt_confirm.setBounds(277, 440, 93, 23);
		jPanel.add(jbt_confirm);

		jbt_cancel = new JButton("取消");
		jbt_cancel.setFocusPainted(false);
		jbt_cancel.setBounds(378, 440, 93, 23);
		jPanel.add(jbt_cancel);

		//初始化数据
		int taskRow = jt_quoteTask.getSelectedRow();
		jtf_taskName.setText((String) jt_quoteTask.getValueAt(taskRow, 2));
		int projectRow = jt_quoteProject.getSelectedRow();
		jtf_projectName.setText(String.valueOf(jt_quoteProject.getValueAt(projectRow, 1)));
		jtf_projectName.setName(String.valueOf(jt_quoteProject.getValueAt(projectRow, 0)));
		jcb_industry.addItem(String.valueOf(jt_quoteProject.getValueAt(projectRow, 2)));
		int addressId = (int)jt_quoteProject.getValueAt(projectRow, 7);
		displayAddress(addressId);
		jtf_projectAmount.setText(String.valueOf(jt_quoteProject.getValueAt(projectRow, 5)));
		oldProjectAmount = (double) jt_quoteProject.getValueAt(projectRow, 4);
		jtf_otherAmount.setText(String.valueOf(jt_quoteProject.getValueAt(projectRow, 4)));

		// 提交按钮
		jbt_confirm.setActionCommand("confirm");
		jbt_confirm.addActionListener(new ModifyProjectFrameAction(jt_quoteTask,
				jt_quoteProject, jtf_projectName, jtf_otherAmount, oldProjectAmount, dialog));
		// 取消按钮
		jbt_cancel.setActionCommand("cancel");
		jbt_cancel.addActionListener(new ModifyProjectFrameAction(dialog));

	}

	private void displayAddress(int addressId) {
		new SwingWorker<String, Void>(){
			@Override
			protected String doInBackground() throws Exception {
				return new AddressServiceImpl().queryAddressDetialById(addressId);
			}

			protected void done() {
				try {
					String address = get();
					String[] addressArray = address.split(",");
					jcb_province.addItem(addressArray[0]);
					jcb_city.addItem(addressArray[1]);
					jcb_county.addItem(addressArray[2]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			};
		}.execute();
	}
}
