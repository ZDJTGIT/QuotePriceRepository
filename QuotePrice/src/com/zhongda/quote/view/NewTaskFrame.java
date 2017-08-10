package com.zhongda.quote.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.action.NewTaskFrameAction;
import com.zhongda.quote.model.Industry;
import com.zhongda.quote.service.impl.IndustryServiceImpl;
import com.zhongda.quote.utils.SkinUtil;

import javax.swing.JSeparator;
import javax.swing.JPanel;

public class NewTaskFrame {

	private JDialog tframe;
	// private JButton bone;
	private JLabel ble;
	private JLabel ble_1;
	private JLabel ble_2;
	private JLabel ble_3;
	private JLabel ble_4;
	private JLabel ble_5;
	private JLabel ble_6;
	private JTextField jtf_2;
	private JTextField jtf_3;
	private JButton ton_1;
	private JButton ton_2;
	String fer_1 = null;
	String fer_2 = null;
	String fer_3 = null;
	Integer fer_4 = 0;
	String fer_5 = null;
	int allsel = 0;
	String wuat;
	DateField datefield;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewTaskFrame window = new NewTaskFrame();
					window.tframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewTaskFrame() {
		init();
	}

	public void init() {

		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		tframe = new JDialog();
		// tframe.setSize(300, 400);
		tframe.setBackground(new Color(100));
		tframe.setBounds(500, 60, 472, 429);
		tframe.setVisible(true);
		ImageIcon icon = new ImageIcon("images\\zdLogo1.png");
		tframe.setTitle("中大检测新建任务");
		tframe.setResizable(false);
		tframe.setLocationRelativeTo(null);
		tframe.setIconImage(icon.getImage());
		tframe.getContentPane().setLayout(null);
		tframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		ble_6 = new JLabel();
		ble_6.setText("当前任务的总报价为：");
		ble_6.setFont(new Font("新宋体", 1, 18));
		ble_6.setForeground(Color.BLACK);
		ble_6.setBounds(20, 490, 200, 30);
		tframe.getContentPane().add(ble_6);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 466, 422);
		panel.setLayout(null);
		tframe.getContentPane().add(panel);

		
		datefield = new DateField();
		datefield.setBounds(243, 195, 199, 23);
		panel.add(datefield);

		ton_1 = new JButton();
		ton_1.setBounds(248, 370, 91, 21);
		panel.add(ton_1);
		ton_1.setText("确认");
		ton_1.setFont(new Font("新宋体", Font.PLAIN, 13));

		ton_2 = new JButton();
		ton_2.setBounds(344, 370, 98, 21);
		panel.add(ton_2);
		ton_2.setText("退出");
		ton_2.setFont(new Font("新宋体", Font.PLAIN, 13));

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(20, 280, 426, 62);
		panel.add(textArea_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 352, 466, 2);
		panel.add(separator_1);

		ble_5 = new JLabel();
		ble_5.setBounds(20, 251, 400, 30);
		panel.add(ble_5);
		ble_5.setText("任务描述（请对您的任务主要内容进行描述）");
		ble_5.setFont(new Font("新宋体", Font.PLAIN, 14));
		ble_5.setForeground(Color.BLACK);

		ble_4 = new JLabel();
		ble_4.setBounds(20, 223, 266, 30);
		panel.add(ble_4);
		ble_4.setText("所属行业（请选择您新建任务涉及行业）");
		ble_4.setFont(new Font("新宋体", Font.PLAIN, 14));
		ble_4.setForeground(Color.BLACK);

		ble_3 = new JLabel();
		ble_3.setBounds(20, 190, 400, 30);
		panel.add(ble_3);
		ble_3.setText("创建时间（当前显示系统默认时间）");
		ble_3.setFont(new Font("新宋体", Font.PLAIN, 14));
		ble_3.setForeground(Color.BLACK);

		jtf_3 = new JTextField();
		jtf_3.setBounds(20, 157, 426, 23);
		panel.add(jtf_3);

		ble_2 = new JLabel();
		ble_2.setBounds(20, 126, 373, 30);
		panel.add(ble_2);
		ble_2.setText("创建人（请填写您的真实姓名）");
		ble_2.setFont(new Font("新宋体", Font.PLAIN, 14));
		ble_2.setForeground(Color.BLACK);

		jtf_2 = new JTextField();
		jtf_2.setBounds(20, 100, 426, 23);
		panel.add(jtf_2);

		ble_1 = new JLabel();
		ble_1.setBounds(20, 69, 257, 30);
		panel.add(ble_1);
		ble_1.setText("任务名称（请输入完整任务名称）");
		ble_1.setFont(new Font("新宋体", Font.PLAIN, 14));
		ble_1.setForeground(Color.BLACK);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 466, 2);
		panel.add(separator);

		JLabel lblNewLabel = new JLabel("请输入基本项目信息");
		lblNewLabel.setBounds(20, 35, 171, 15);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));

		ton_2.setActionCommand("ton2");
		ton_1.setActionCommand("ton1");

		ble = new JLabel();
		ble.setBounds(20, 2, 91, 30);
		panel.add(ble);
		ble.setText("新建任务 ");
		ble.setFont(new Font("新宋体", 1, 16));
		ble.setForeground(new Color(0, 0, 0));
		JComboBox comboBox = new JComboBox(leanmesage());
		comboBox.setBounds(297, 228, 145, 21);
		panel.add(comboBox);

		
		tframe.addWindowListener(new NewTaskFrameAction());
		ton_2.addActionListener(new NewTaskFrameAction());
		ton_1.addActionListener(new NewTaskFrameAction(jtf_2, jtf_3, comboBox,
				textArea_1, datefield));
	}
/*
 * 从数据库提取所属行业信息
 */
	public Vector<String> leanmesage() {
		List<Industry> list = new IndustryServiceImpl().queryAllIndustry();
		Vector<String> vector = new Vector<String>();
		for (int i = 0; i < list.size(); i++) {
			vector.add(list.get(i).getIndustryName());
		}
		return vector;
	}
}
