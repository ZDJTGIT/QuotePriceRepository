package com.zhongda.quote.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.model.Industry;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.IndustryServiceImpl;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;
import com.zhongda.quote.utils.SkinUtil;

public class NewTask {

	private JFrame tframe;
	// private JButton bone;
	private JLabel ble;
	private JLabel ble_1;
	private JLabel ble_2;
	private JLabel ble_3;
	private JLabel ble_4;
	private JLabel ble_5;
	private JLabel ble_6;
	private JLabel ble_7;
	private JTextField jtf_2;
	private JTextField jtf_3;
	private JTextField jtf_4;
	private JButton ton_1;
	private JButton ton_2;
	String fer_1 = null;
	String fer_2 = null;
	String fer_3 = null;
	Integer fer_4 = 0;
	String fer_5 = null;
	int allsel = 0;
	private JTextField textField;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewTask window = new NewTask();
					window.tframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewTask() {
		init();
	}

	public void init() {

		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		tframe = new JFrame();
		// tframe.setSize(300, 400);
		tframe.setBackground(new Color(100));
		tframe.setBounds(500, 60, 620, 450);
		tframe.setVisible(true);
		ImageIcon icon = new ImageIcon("images\\zdLogo1.png");
		tframe.setTitle("中大检测新建任务");
		tframe.setIconImage(icon.getImage());
		tframe.getContentPane().setLayout(null);

		ble = new JLabel();
		ble.setText("新建任务 ");
		ble.setFont(new Font("新宋体", 1, 16));
		ble.setForeground(new Color(0, 0, 0));
		ble.setBounds(20, 0, 400, 40);
		tframe.getContentPane().add(ble);

		ble_1 = new JLabel();
		ble_1.setText("任务名称");
		ble_1.setFont(new Font("新宋体", 1, 14));
		ble_1.setForeground(Color.BLACK);
		ble_1.setBounds(42, 63, 120, 30);
		JLabel ble_1s = new JLabel();
		ble_1s.setText("（请输入完整任务名称）");
		ble_1s.setFont(new Font("新宋体", 1, 14));
		ble_1s.setForeground(Color.BLACK);
		ble_1s.setBounds(110, 63, 200, 30);
		tframe.getContentPane().add(ble_1);
		tframe.getContentPane().add(ble_1s);

		ble_2 = new JLabel();
		ble_2.setText("创建人");
		ble_2.setFont(new Font("新宋体", Font.BOLD, 14));
		ble_2.setForeground(Color.BLACK);
		ble_2.setBounds(42, 121, 400, 30);
		JLabel ble_2s = new JLabel();
		ble_2s.setText("（请填写您的真实姓名）");
		ble_2s.setFont(new Font("新宋体", 1, 14));
		ble_2s.setForeground(Color.BLACK);
		ble_2s.setBounds(110, 121, 200, 30);
		tframe.getContentPane().add(ble_2);
		tframe.getContentPane().add(ble_2s);

		ble_3 = new JLabel();
		ble_3.setText("创建时间");
		ble_3.setFont(new Font("新宋体", Font.BOLD, 14));
		ble_3.setForeground(Color.BLACK);
		ble_3.setBounds(42, 183, 400, 30);
		JLabel ble_3s = new JLabel();
		ble_3s.setText("（当前显示系统默认时间）");
		ble_3s.setFont(new Font("新宋体", 1, 14));
		ble_3s.setForeground(Color.BLACK);
		ble_3s.setBounds(110, 183, 200, 30);
		tframe.getContentPane().add(ble_3s);
		tframe.getContentPane().add(ble_3);

		ble_4 = new JLabel();
		ble_4.setText("所属行业");
		ble_4.setFont(new Font("新宋体", Font.BOLD, 14));
		ble_4.setForeground(Color.BLACK);
		ble_4.setBounds(42, 254, 400, 30);
		JLabel ble_4s = new JLabel();
		ble_4s.setText("（请选择您新建任务涉及行业）");
		ble_4s.setFont(new Font("新宋体", 1, 14));
		ble_4s.setForeground(Color.BLACK);
		ble_4s.setBounds(111, 254, 260, 30);
		tframe.getContentPane().add(ble_4s);
		tframe.getContentPane().add(ble_4);

		ble_5 = new JLabel();
		ble_5.setText("任务描述");
		ble_5.setFont(new Font("新宋体", Font.BOLD, 14));
		ble_5.setForeground(Color.BLACK);
		ble_5.setBounds(42, 285, 400, 30);
		JLabel ble_5s = new JLabel();
		ble_5s.setText("（请对您的任务主要内容进行描述）");
		ble_5s.setFont(new Font("新宋体", 1, 14));
		ble_5s.setForeground(Color.BLACK);
		ble_5s.setBounds(110, 285, 260, 30);
		tframe.getContentPane().add(ble_5s);
		tframe.getContentPane().add(ble_5);

		ble_6 = new JLabel();
		ble_6.setText("当前任务的总报价为：");
		ble_6.setFont(new Font("新宋体", 1, 18));
		ble_6.setForeground(Color.BLACK);
		ble_6.setBounds(20, 490, 200, 30);
		tframe.getContentPane().add(ble_6);

		ble_7 = new JLabel();

		String showallsel = allsel + "";
		ble_7.setText(showallsel);
		ble_7.setFont(new Font("新宋体", 1, 18));
		ble_7.setForeground(Color.BLACK);
		ble_7.setBounds(210, 490, 80, 30);
		tframe.getContentPane().add(ble_7);

		jtf_2 = new JTextField();
		jtf_2.setBounds(42, 90, 540, 21);
		tframe.getContentPane().add(jtf_2);

		jtf_3 = new JTextField();
		jtf_3.setBounds(42, 152, 540, 21);
		tframe.getContentPane().add(jtf_3);

		jtf_4 = new JTextField();
		jtf_4.setBounds(42, 223, 540, 21);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String wuat = df.format(new Date());
		jtf_4.setText(wuat);
		jtf_4.setFont(new Font("新宋体", 1, 14));
		tframe.getContentPane().add(jtf_4);
		

		List<Industry> list = new IndustryServiceImpl().queryAllIndustry();
		Vector<String> vector = new Vector<String>();
		for (int i = 0; i < list.size(); i++) {
			vector.add(list.get(i).getIndustryName());
		}
		JComboBox comboBox = new JComboBox(vector);
		comboBox.setBounds(342, 254, 240, 21);
		tframe.getContentPane().add(comboBox);

		ton_1 = new JButton();
		ton_1.setText("确认");
		ton_1.setBounds(394, 381, 91, 21);
		ton_1.setFont(new Font("新宋体", 1, 13));
		tframe.getContentPane().add(ton_1);

		ton_2 = new JButton();
		ton_2.setText("退出");
		ton_2.setFont(new Font("新宋体", 1, 13));
		ton_2.setBounds(484, 381, 98, 21);
		tframe.getContentPane().add(ton_2);
		
		JLabel lblNewLabel = new JLabel("请输入基本项目信息");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 37, 171, 15);
		tframe.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(42, 317, 540, 40);
		tframe.getContentPane().add(textField);
		textField.setColumns(10);

		ton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fer_1 = jtf_2.getText();
				fer_2 = jtf_3.getText();
				fer_3 = jtf_4.getText();
				fer_4 = comboBox.getSelectedIndex()+1;
//              fer_4 = comboBox.getSelectedItem().toString();
//              System.out.println(fer_4);
				fer_5 = textField.getText();
				System.out.println(fer_1);
				System.out.println(fer_2);
				System.out.println(fer_3);
				System.out.println(fer_4);
				System.out.println(fer_5);
				if (fer_1.equalsIgnoreCase("") | fer_2.equalsIgnoreCase("")
						| fer_3.equalsIgnoreCase("")
						| fer_4==0
						| fer_5.equalsIgnoreCase("")) {
					Object[] options = { "确认", "取消" };
					JOptionPane.showOptionDialog(null, "请完善您的任务信息", "警告",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);
				} else {
					JOptionPane.showMessageDialog(null, "任务创建成功！", "提示信息",
							JOptionPane.PLAIN_MESSAGE);
					// 把获取的任务信息储存到数据库
					QuoteTask quoteTask = new QuoteTask("ZD201708080008",
							fer_1, fer_5, fer_4, fer_2);
//					String msg = new QuoteTaskServiceImpl()
//					.createQuoteTask(quoteTask);
//					System.out.println(msg);
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
			}
		});

		ton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int inf = JOptionPane.showConfirmDialog(null, "确定退出么？",
						"Attention", JOptionPane.OK_OPTION);
				if (inf == JOptionPane.OK_OPTION) {
					System.exit(0);
				} else {

				}
				// 退出
			}
		});

	}
}
