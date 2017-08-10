package com.zhongda.quote.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.action.CreateTaskDialogAction;
import com.zhongda.quote.model.Industry;
import com.zhongda.quote.service.impl.IndustryServiceImpl;
import com.zhongda.quote.utils.DateField;
import com.zhongda.quote.utils.SkinUtil;

/**
 *
 * <p>
 * 创建任务窗口
 * <p>
 *
 * @author 研发中心-Mikepolite<1011592269@qq.com>
 * @sine 2017年8月9日
 */
public class CreateTaskDialog {

	public JDialog jDialog;
	// private JButton bone;
	private JLabel ble;
	private JLabel ble_1;
	private JLabel ble_2;
	private JLabel ble_3;
	private JLabel ble_4;
	private JLabel ble_5;
	private JLabel ble_6;
	private JTextField jtf_taskName;
	private JTextField jtf_createUser;
	private JComboBox<Industry> jcb_industry;
	private JButton bt_confirm;
	private JButton bt_cancel;
	private DateField df_createDate;

	public CreateTaskDialog() {
		init();
	}

	public void init() {

		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		jDialog = new JDialog();
		System.out.println(jDialog);
		// tframe.setSize(300, 400);
		jDialog.setBackground(new Color(100));
		jDialog.setBounds(500, 60, 472, 429);
		ImageIcon icon = new ImageIcon("images\\zdLogo1.png");
		jDialog.setTitle("中大检测新建任务");
		jDialog.setResizable(false);
		jDialog.setLocationRelativeTo(null);
		jDialog.setIconImage(icon.getImage());
		jDialog.getContentPane().setLayout(null);
		jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jDialog.setModal(true);

		ble_6 = new JLabel();
		ble_6.setText("当前任务的总报价为：");
		ble_6.setFont(new Font("新宋体", 1, 18));
		ble_6.setForeground(Color.BLACK);
		ble_6.setBounds(20, 490, 200, 30);
		jDialog.getContentPane().add(ble_6);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 466, 422);
		panel.setLayout(null);
		jDialog.getContentPane().add(panel);

		df_createDate = new DateField();
		df_createDate.setBounds(243, 195, 199, 23);
		panel.add(df_createDate);

		bt_confirm = new JButton();
		bt_confirm.setBounds(248, 370, 91, 21);
		panel.add(bt_confirm);
		bt_confirm.setText("确认");
		bt_confirm.setFont(new Font("新宋体", Font.PLAIN, 13));

		bt_cancel = new JButton();
		bt_cancel.setBounds(344, 370, 98, 21);
		panel.add(bt_cancel);
		bt_cancel.setText("取消");
		
		bt_cancel.setFont(new Font("新宋体", Font.PLAIN, 13));

		JTextArea jta_taskDescription = new JTextArea();
		jta_taskDescription.setBounds(20, 280, 426, 62);
		panel.add(jta_taskDescription);

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

		jtf_createUser = new JTextField();
		jtf_createUser.setBounds(20, 157, 426, 23);
		panel.add(jtf_createUser);

		ble_2 = new JLabel();
		ble_2.setBounds(20, 126, 373, 30);
		panel.add(ble_2);
		ble_2.setText("创建人（请填写您的真实姓名）");
		ble_2.setFont(new Font("新宋体", Font.PLAIN, 14));
		ble_2.setForeground(Color.BLACK);

		jtf_taskName = new JTextField();
		jtf_taskName.setBounds(20, 100, 426, 23);
		panel.add(jtf_taskName);

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

		ble = new JLabel();
		ble.setBounds(20, 2, 91, 30);
		panel.add(ble);
		ble.setText("新建任务 ");
		ble.setFont(new Font("新宋体", 1, 16));
		ble.setForeground(new Color(0, 0, 0));

		jcb_industry = new JComboBox<Industry>();
		jcb_industry.setBounds(297, 228, 145, 21);
		panel.add(jcb_industry);

		// 生成该窗口时启动任务线程从数据库加载初始化数据
		new SwingWorker<List<Industry>, Industry>() {

			@Override
			protected List<Industry> doInBackground() throws Exception {
				// 从数据库获取行业数据
				List<Industry> industryList = new IndustryServiceImpl()
						.queryAllIndustry();
				return industryList;
			}

			@Override
			protected void done() {
				List<Industry> industryList;
				try {
					industryList = get();
					Vector<Industry> model = new Vector<Industry>();
					// 将数据添加到comboBox
					for (Industry industry : industryList) {
						model.addElement(industry);
					}
					ComboBoxModel<Industry> comboBoxModel = new DefaultComboBoxModel<Industry>(
							model);
					jcb_industry.setModel(comboBoxModel);
					// 提供自定义渲染类，实现键值绑定
					jcb_industry.setRenderer(new DefaultListCellRenderer() {

						private static final long serialVersionUID = 1L;

						public Component getListCellRendererComponent(
								JList<?> list, Object value, int index,
								boolean isSelected, boolean cellHasFocus) {
							super.getListCellRendererComponent(list, value,
									index, isSelected, cellHasFocus);
							if (value != null) {
								Industry industry = (Industry) value;
								// 将行业名称填入显示列表
								setText(industry.getIndustryName());
							}
							return this;
						};
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();

		// 添加关闭窗口事件
		jDialog.addWindowListener(new CreateTaskDialogAction(jDialog));
		// 添加取消按钮事件
		bt_cancel.setActionCommand("cancelCreateTask");
		bt_cancel.addActionListener(new CreateTaskDialogAction(jDialog));
		// 添加确认按钮事件
		bt_confirm.setActionCommand("confirmCreateTask");
		bt_confirm.addActionListener(new CreateTaskDialogAction(jtf_taskName,
				jtf_createUser, df_createDate, jcb_industry,
				jta_taskDescription));
	}

}
