package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.utils.SkinUtil;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;

/**
 * 
 * <p>
 * 新建项目类UI
 * </p>
 * 
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月9日
 */
public class CreateProjectFrame {

	public JDialog dialog;
	private JPanel jPanel;
	private JpaneColorAndPhoto jpanel_up;
	private JLabel jlb_jpup_1;
	private JLabel jlb_jpup_2;
	private JLabel label;
	private JTextField jtf_task;
	private JLabel lblNewLabel;
	private JTextField jtf_pname;
	private JLabel lblNewLabel_1;
	private JLabel label_1;
	private JTextField jtf_pp;
	private JLabel label_2;
	private JTextField jtf_po;
	private JLabel label_3;
	private JLabel lblNewLabel_2;
	private Choice jc_pm;
	private JSeparator separator;
	private JButton jbt_yes;
	private JButton jbt_no;
	private JLabel lblNewLabel_3;
	private JComboBox jcb_jyp;
	private JPanel jp_jyp;
	private JComboBox jcb_xjxm_1;
	private JComboBox jcb_xjxm_2;
	private JComboBox jcb_xjxm_3;
	private JComboBox comboBox;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProjectFrame windows = new CreateProjectFrame();
					windows.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateProjectFrame() {

		init();

	}

	public void init() {
		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setBounds(0, 0, 500, 550);
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

		jtf_task = new JTextField();
		jtf_task.setBounds(26, 80, 445, 25);
		jPanel.add(jtf_task);
		jtf_task.setColumns(10);

		lblNewLabel = new JLabel("项目名称");
		lblNewLabel.setBounds(26, 110, 54, 15);
		jPanel.add(lblNewLabel);

		jtf_pname = new JTextField();
		jtf_pname.setBounds(26, 128, 445, 25);
		jPanel.add(jtf_pname);
		jtf_pname.setColumns(10);

		lblNewLabel_1 = new JLabel("项目所属地点");
		lblNewLabel_1.setBounds(26, 163, 89, 15);
		jPanel.add(lblNewLabel_1);

		jcb_xjxm_1 = new JComboBox();
		jcb_xjxm_1.setBounds(26, 184, 145, 21);
		jPanel.add(jcb_xjxm_1);

		jcb_xjxm_2 = new JComboBox();
		jcb_xjxm_2.setBounds(177, 184, 145, 21);
		jPanel.add(jcb_xjxm_2);

		jcb_xjxm_3 = new JComboBox();
		jcb_xjxm_3.setBounds(326, 184, 145, 21);
		jPanel.add(jcb_xjxm_3);

		lblNewLabel_2 = new JLabel("报价方法");
		lblNewLabel_2.setBounds(26, 211, 54, 15);
		jPanel.add(lblNewLabel_2);

		comboBox = new JComboBox();
		comboBox.setBounds(26, 231, 445, 25);
		jPanel.add(comboBox);

		label_1 = new JLabel("项目报价");
		label_1.setBounds(26, 376, 54, 15);
		jPanel.add(label_1);

		jtf_pp = new JTextField();
		jtf_pp.setBounds(26, 395, 445, 25);
		jPanel.add(jtf_pp);
		jtf_pp.setColumns(10);

		label_2 = new JLabel("其他费用报价");
		label_2.setBounds(26, 422, 101, 15);
		jPanel.add(label_2);

		jtf_po = new JTextField();
		jtf_po.setBounds(26, 440, 445, 25);
		jPanel.add(jtf_po);
		jtf_po.setColumns(10);

		label_3 = new JLabel("中大检测");
		label_3.setBounds(4, 468, 54, 23);
		jPanel.add(label_3);

		separator = new JSeparator();
		separator.setBounds(55, 480, 435, 2);
		jPanel.add(separator);

		jbt_yes = new JButton("确认");
		jbt_yes.setFocusPainted(false);
		jbt_yes.setBounds(282, 490, 93, 23);
		jPanel.add(jbt_yes);

		jbt_no = new JButton("取消");
		jbt_no.setFocusPainted(false);
		jbt_no.setBounds(378, 490, 93, 23);
		jPanel.add(jbt_no);

		lblNewLabel_3 = new JLabel("创建检测批");
		lblNewLabel_3.setBounds(26, 265, 74, 21);
		jPanel.add(lblNewLabel_3);

		jcb_jyp = new JComboBox();
		jcb_jyp.setBounds(110, 265, 361, 21);
		jPanel.add(jcb_jyp);

		jp_jyp = new JPanel();
		jp_jyp.setBounds(26, 295, 445, 75);
		jp_jyp.setBorder(BorderFactory.createTitledBorder(null, "检测批",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("宋体", 0, 14)));// 设置边框字体
		jPanel.add(jp_jyp);
		jp_jyp.setLayout(null);

		chckbxNewCheckBox = new JCheckBox("社会");
		chckbxNewCheckBox.setBounds(6, 17, 103, 23);
		jp_jyp.add(chckbxNewCheckBox);

		chckbxNewCheckBox_1 = new JCheckBox("我");
		chckbxNewCheckBox_1.setBounds(174, 17, 103, 23);
		jp_jyp.add(chckbxNewCheckBox_1);

		chckbxNewCheckBox_2 = new JCheckBox("鑫哥");
		chckbxNewCheckBox_2.setBounds(306, 17, 103, 23);
		jp_jyp.add(chckbxNewCheckBox_2);

		chckbxNewCheckBox_3 = new JCheckBox("人");
		chckbxNewCheckBox_3.setBounds(6, 46, 103, 23);
		jp_jyp.add(chckbxNewCheckBox_3);

		chckbxNewCheckBox_4 = new JCheckBox("狠");
		chckbxNewCheckBox_4.setBounds(174, 46, 103, 23);
		jp_jyp.add(chckbxNewCheckBox_4);

		chckbxNewCheckBox_5 = new JCheckBox("话不多");
		chckbxNewCheckBox_5.setBounds(306, 46, 103, 23);
		jp_jyp.add(chckbxNewCheckBox_5);

	}
}
