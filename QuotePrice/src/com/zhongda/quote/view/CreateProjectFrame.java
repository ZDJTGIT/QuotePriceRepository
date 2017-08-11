package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.utils.SkinUtil;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private Choice jc_place_2;
	private Choice jc_place_3;
	private JLabel label_1;
	private JTextField jtf_pp;
	private JLabel label_2;
	private JTextField jtf_po;
	private JLabel label_3;
	private JLabel lblNewLabel_2;
	private Choice jc_place_1;
	private Choice jc_pm;
	private JSeparator separator;
	private JButton jbt_yes;
	private JButton jbt_no;

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
		dialog.setBounds(0, 0, 500, 500);
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

		jc_place_1 = new Choice();
		jc_place_1.setBounds(26, 184, 145, 21);
		jPanel.add(jc_place_1);

		jc_place_2 = new Choice();
		jc_place_2.setBounds(177, 184, 145, 21);
		jPanel.add(jc_place_2);

		jc_place_3 = new Choice();
		jc_place_3.setBounds(326, 184, 145, 21);
		jPanel.add(jc_place_3);

		lblNewLabel_2 = new JLabel("报价方法");
		lblNewLabel_2.setBounds(26, 211, 54, 15);
		jPanel.add(lblNewLabel_2);

		jc_pm = new Choice();
		jc_pm.setBounds(26, 231, 445, 25);
		jPanel.add(jc_pm);

		label_1 = new JLabel("项目报价");
		label_1.setBounds(26, 258, 54, 15);
		jPanel.add(label_1);

		jtf_pp = new JTextField();
		jtf_pp.setBounds(26, 283, 445, 25);
		jPanel.add(jtf_pp);
		jtf_pp.setColumns(10);

		label_2 = new JLabel("其他费用报价");
		label_2.setBounds(26, 315, 101, 15);
		jPanel.add(label_2);

		jtf_po = new JTextField();
		jtf_po.setBounds(26, 336, 445, 25);
		jPanel.add(jtf_po);
		jtf_po.setColumns(10);

		label_3 = new JLabel("中大检测");
		label_3.setBounds(4, 375, 54, 23);
		jPanel.add(label_3);

		separator = new JSeparator();
		separator.setBounds(55, 387, 435, 2);
		jPanel.add(separator);

		jbt_yes = new JButton("确认");
		jbt_yes.setFocusPainted(false);
		jbt_yes.setBounds(282, 399, 93, 23);
		jPanel.add(jbt_yes);

		jbt_no = new JButton("取消");
		jbt_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int temp = JOptionPane.showConfirmDialog(null, "你高兴吗?", "标题",
						JOptionPane.YES_NO_OPTION);
				if (temp == 0) {
					dialog.dispose();
				}
			}
		});
		jbt_no.setFocusPainted(false);
		jbt_no.setBounds(378, 399, 93, 23);
		jPanel.add(jbt_no);

	}
}
