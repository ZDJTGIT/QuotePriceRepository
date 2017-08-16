package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import sun.swing.table.DefaultTableCellHeaderRenderer;

import com.zhongda.quote.action.CreatInspectionAction;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.utils.FrameGoUtils;
import com.zhongda.quote.utils.SkinUtil;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;

/**
 * 
 * <p>
 * 创建检验批界面
 * </p>
 * 
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月12日
 */
public class CreatInspectionFrame {

	public JDialog dialog;
	private JPanel panel;
	private JpaneColorAndPhoto jpanel_up;
	private JLabel jlb_jpup_1;
	private JLabel jlb_jpup_2;
	private JLabel label;
	private JTextField jtf_task;
	private JLabel lblNewLabel;
	private JTextField jtf_pname;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JTextField textField_1;
	private JButton jbt_search;
	private JPanel panel_1;
	private JLabel lblNewLabel_2;
	private JLabel label_3;
	private JSeparator separator;
	private JButton jbt_yes;
	private JButton jbt_no;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel dtm;
	private JPanel jp_search;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private DefaultTableModel dtmSearch;
	private String project;
	private int projectName;

	/**
	 * Launch the application.
	 * 
	 * 
	 */
	public static void main(String[] args) {
		FrameGoUtils.creatInspection();
	}

	/**
	 * Create the frame.
	 * 
	 * @wbp.parser.entryPoint
	 * 
	 */
	public CreatInspectionFrame() {
		init();
	}

	public CreatInspectionFrame(QuoteProject quoteProject) {
		this.project = quoteProject.getProjectName();
		this.projectName = quoteProject.getId();
		init();
	}

	public CreatInspectionFrame(JTable table) {
		this.project = (String) table.getValueAt(table.getSelectedRow(), 1);
		this.projectName = (int) table.getValueAt(table.getSelectedRow(), 0);
		init();
	}

	public void init() {

		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setBounds(0, 0, 900, 500);
		dialog.setModal(true);// 此窗口至于前端
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setTitle("中大检测");
		dialog.setIconImage(dialog.getToolkit().getImage("images/zdLogo1.png"));
		dialog.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		jpanel_up = new JpaneColorAndPhoto("images/bookpen.png", 830, 2, 48, 48);
		jpanel_up.setBounds(0, 0, 894, 52);
		panel.add(jpanel_up);
		jpanel_up.setLayout(null);

		jlb_jpup_1 = new JLabel("新建检验批");
		jlb_jpup_1.setBounds(10, 10, 157, 15);
		jlb_jpup_1.setFont(new Font("黑体", 1, 15));
		jpanel_up.add(jlb_jpup_1);

		jlb_jpup_2 = new JLabel("填写检验批信息");
		jlb_jpup_2.setBounds(33, 25, 186, 15);
		jpanel_up.add(jlb_jpup_2);

		label = new JLabel("所属项目");
		label.setBounds(26, 62, 54, 15);
		panel.add(label);

		jtf_task = new JTextField();
		jtf_task.setBounds(26, 80, 845, 25);
		jtf_task.setEnabled(false);
		jtf_task.setText(project);
		jtf_task.setName(String.valueOf(projectName));
		panel.add(jtf_task);// 存入所选项目的id，取出时需要强转为int类型
		jtf_task.setColumns(10);

		lblNewLabel = new JLabel("检验批名称");
		lblNewLabel.setBounds(26, 110, 82, 15);
		panel.add(lblNewLabel);

		jtf_pname = new JTextField();
		jtf_pname.setBounds(26, 128, 845, 25);
		panel.add(jtf_pname);
		jtf_pname.setColumns(10);

		lblNewLabel_1 = new JLabel("检测内容");
		lblNewLabel_1.setBounds(26, 163, 54, 23);
		panel.add(lblNewLabel_1);

		textField = new JTextField("请输入检测内容");
		textField.setBounds(84, 163, 754, 23);
		textField.setForeground(new Color(155, 174, 202));
		textField.addFocusListener((new CreatInspectionAction(textField,
				"printInspec")));
		panel.add(textField);
		textField.setColumns(10);

		// 检测内容面板》》》》》》》
		jp_search = new JPanel();
		jp_search.setBounds(26, 186, 845, 184);
		jp_search.setVisible(false);
		panel.add(jp_search);
		// 检测内容面板结束《《《《《《《《《

		jbt_search = new JButton();
		jbt_search.setIcon(new ImageIcon("images/find.png"));
		jbt_search.setFocusPainted(false);
		jbt_search.setBounds(848, 163, 23, 23);
		jbt_search.setActionCommand("search");
		jp_search.setLayout(new BorderLayout(0, 0));

		scrollPane_1 = new JScrollPane();
		scrollPane_1.addFocusListener(new CreatInspectionAction());
		jp_search.add(scrollPane_1, BorderLayout.CENTER);

		String[] serchContentName = { "检测内容", "检测方法", "抽样依据", "抽样数量方位", "抽样数量",
				"报价依据", "单个检测数量范围", "单个检测对象数量", "计费单位", "收费标准" };
		Object[][] testObjects = { { "\u7F57\u6770", "1" }, { "Rojay", "2" },
				{ "\u7F57\u6770\u5973", "3" }, { "\u6770\u7F57", "4" },
				{ "Yajor", "5" }, { "dsff", "6" }, { "htjj", "7" },
				{ null, "8" }, { "jyukj", "2" }, };
		table_1 = new JTable();
		dtmSearch = new DefaultTableModel(testObjects, serchContentName);
		table_1.setModel(dtmSearch);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(110);
		table_1.getTableHeader().setFont(new Font("宋体", 0, 12));
		table_1.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(6).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(7).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(8).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(9).setPreferredWidth(50);
		scrollPane_1.setViewportView(table_1);
		panel.add(jbt_search);
		jbt_search.addActionListener(new CreatInspectionAction(textField,
				jp_search, table_1));
		table_1.addFocusListener(new CreatInspectionAction(jp_search,
				"searchPanel"));
		// 结束<<<<<<<<<<<<<<<<<<<<<<

		panel_1 = new JPanel();
		panel_1.setBounds(26, 194, 845, 156);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();

		String[] tHead = { "序号", "检测内容名称", "抽样依据", "检测方法", "单个检测实施数量的范围",
				"单个检测实施对象的数量", "抽样数量" };
		// <<<<测试数据
		Object[][] obj = {
				{ "1", "是的撒发郭德纲节节高我偶尔经过hire讲话稿我偶然间hi软件hi然后就hi女女 ", "sdsd",
						"sdsd", "sdsd", "sdsd" },
				{ "2", "sdsd", "sdsd", "sdsd", "sdsd", "sdsd" },
				{ "3", "sdsd", "sdsd", "sdsd", "sdsd", "sdsd" },
				{ "4", "sdsd", "sdsd", "sdsd", "sdsd", "sdsd" },
				{ "5", "sdsd", "sdsd", "sdsd", "sdsd", "sdsd" },
				{ "6", "sdsd", "sdsd", "sdsd", "sdsd", "sdsd" },
				{ "7", "sdsd", "sdsd", "sdsd", "sdsd", "sdsd" },
				{ "8", "sdsd", "sdsd", "sdsd", "sdsd", "sdsd" },
				{ "9", "sdsd", "sdsd", "sdsd", "sdsd", "sdsd" },
				{ "10", "sdsd", "sdsd", "sdsd", "sdsd", "sdsd" } };
		// >>>>>测试数据
		dtm = new DefaultTableModel(obj, tHead);
		// 表头字体居中
		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		table.getTableHeader().setDefaultRenderer(hr);
		table.setModel(dtm);
		table.addMouseMotionListener(new CreatInspectionAction(table));
		// 设置列宽度
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);

		scrollPane.setViewportView(table);

		lblNewLabel_2 = new JLabel("检验批报价");
		lblNewLabel_2.setBounds(26, 355, 116, 15);
		panel.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(26, 374, 845, 25);
		panel.add(textField_1);

		label_3 = new JLabel("中大检测");
		label_3.setBounds(5, 407, 54, 23);
		panel.add(label_3);

		separator = new JSeparator();
		separator.setBounds(54, 420, 835, 2);
		panel.add(separator);

		jbt_yes = new JButton("确认");
		jbt_yes.setFocusPainted(false);
		jbt_yes.setActionCommand("commit");
		jbt_yes.addActionListener(new CreatInspectionAction(dialog));
		jbt_yes.setBounds(663, 436, 93, 23);
		panel.add(jbt_yes);

		jbt_no = new JButton("取消");
		jbt_no.setFocusPainted(false);
		jbt_no.setActionCommand("calloff");
		jbt_no.addActionListener(new CreatInspectionAction(dialog));
		jbt_no.setBounds(778, 436, 93, 23);
		panel.add(jbt_no);

		jtf_task.addFocusListener(new CreatInspectionAction(jp_search));
		jtf_pname.addFocusListener(new CreatInspectionAction(jp_search));
		textField.addFocusListener(new CreatInspectionAction(jp_search));
		table.addFocusListener(new CreatInspectionAction(jp_search));
		scrollPane.addFocusListener(new CreatInspectionAction(jp_search));
		textField_1.addFocusListener(new CreatInspectionAction(jp_search));
		jbt_yes.addFocusListener(new CreatInspectionAction(jp_search));
		jbt_no.addFocusListener(new CreatInspectionAction(jp_search));
		panel_1.addFocusListener(new CreatInspectionAction(jp_search));

		// jp_search置于窗口最前端
		dialog.getLayeredPane().add(jp_search, new Integer(Integer.MAX_VALUE));

	}

	// private String projectName() {
	// String name = null;
	// name = (String) jt_quoteProjec.getValueAt(
	// jt_quoteProjec.getSelectedRow(), 1);
	// return name;
	// }
}
