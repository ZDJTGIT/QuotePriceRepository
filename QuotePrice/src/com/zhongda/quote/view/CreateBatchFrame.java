package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import sun.swing.table.DefaultTableCellHeaderRenderer;

import com.zhongda.quote.action.CreateBatchFrameAction;
import com.zhongda.quote.model.Address;
import com.zhongda.quote.model.Industry;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.utils.SkinUtil;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;
import com.zhongda.quote.view.uiutils.MyTable;

/**
 *
 * <p>
 * 创建检验批界面
 * </p>
 *
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月12日
 */
public class CreateBatchFrame {

	public JDialog dialog;
	private JPanel panel;
	private JpaneColorAndPhoto jpanel_up;
	private JLabel jlb_jpup_1;
	private JLabel jlb_jpup_2;
	private JLabel label;
	private JTextField jtf_projectName;
	private JLabel lblNewLabel;
	private JTextField jtf_batchName;
	private JLabel lblNewLabel_1;
	private JTextField jtf_contentName;
	private JTextField jtf_batchAmount;
	private JButton jbt_searchContent;
	private JPanel panel_1;
	private JLabel lblNewLabel_2;
	private JLabel label_3;
	private JSeparator separator;
	private JButton jbt_confirm;
	private JButton jbt_cancel;
	private JScrollPane scrollPane;
	private JPanel jp_search;
	private JScrollPane scrollPane_1;
	private MyTable jt_sysInspectionContent;
	private JTable jt_quoteTask;
	private JTable jt_quoteProject;
	private JTable jt_inspectionBatch;
	private JTable jt_inspectionContent;
	private JTable jt_partInspectionContent;
	private JPanel jp_inspectionBatch;
	private String projectName;
	private JTextField jtf_projectAmount;
	private Industry industry;
	private Address address;
	private Map<String, Map<String,Object>> batchMap;
	private List<InspectionContent> singleContentList = new ArrayList<InspectionContent>();


	public CreateBatchFrame() {
		init();
	}

	public CreateBatchFrame(Map<String, Map<String,Object>> batchMap, JPanel jp_inspectionBatch, String projectName, JTextField jtf_projectAmount, Industry industry, Address address) {
		this.batchMap = batchMap;
		this.jp_inspectionBatch = jp_inspectionBatch;
		this.projectName = projectName;
		this.jtf_projectAmount = jtf_projectAmount;
		this.industry = industry;
		this.address = address;
		init();
	}

	public CreateBatchFrame(JTable jt_quoteTask, JTable jt_quoteProject,
			JTable jt_inspectionBatch, JTable jt_partInspectionContent) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_partInspectionContent = jt_partInspectionContent;
		int projectRow = jt_quoteProject.getSelectedRow();
		this.projectName = (String) jt_quoteProject.getValueAt(projectRow, 1);
		int industryId = (int) jt_quoteProject.getValueAt(projectRow, 6);
		this.industry = new Industry(industryId);
		int addressId = ((int) jt_quoteProject.getValueAt(projectRow, 7)) / 10000 * 10000;
		this.address = new Address(addressId);
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

		jtf_projectName = new JTextField();
		jtf_projectName.setBounds(26, 80, 845, 25);
		jtf_projectName.setEnabled(false);
		jtf_projectName.setText(projectName);
		panel.add(jtf_projectName);// 存入所选项目的id，取出时需要强转为int类型
		jtf_projectName.setColumns(10);

		lblNewLabel = new JLabel("检验批名称");
		lblNewLabel.setBounds(26, 110, 82, 15);
		panel.add(lblNewLabel);

		jtf_batchName = new JTextField();
		jtf_batchName.setBounds(26, 128, 845, 25);
		panel.add(jtf_batchName);
		jtf_batchName.setColumns(10);

		lblNewLabel_1 = new JLabel("检测内容");
		lblNewLabel_1.setBounds(26, 163, 54, 23);
		panel.add(lblNewLabel_1);

		jtf_contentName = new JTextField("请输入检测内容");
		jtf_contentName.setBounds(84, 163, 754, 23);
		jtf_contentName.setForeground(new Color(155, 174, 202));
		panel.add(jtf_contentName);
		jtf_contentName.setColumns(10);

		// 检测内容面板》》》》》》》
		jp_search = new JPanel();
		jp_search.setBounds(26, 186, 845, 184);
		jp_search.setVisible(false);
		panel.add(jp_search);
		// 检测内容面板结束《《《《《《《《《

		jbt_searchContent = new JButton();
		jbt_searchContent.setIcon(new ImageIcon("images/find.png"));
		jbt_searchContent.setFocusPainted(false);
		jbt_searchContent.setBounds(848, 163, 23, 23);
		jp_search.setLayout(new BorderLayout(0, 0));

		scrollPane_1 = new JScrollPane();
		jp_search.add(scrollPane_1, BorderLayout.CENTER);

		String[] sysContentColumnsName = { "序号id", "检测内容", "抽样数量范围", "抽样数量",
			    "单个检测对象实施数量范围", "单个检测对象实施数量", "抽样依据", "计费单位", "收费标准",
				"收费标准单位", "报价依据" };
		int[] number = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		jt_sysInspectionContent = new MyTable(number);
		jt_sysInspectionContent.setModel(new DefaultTableModel(null, sysContentColumnsName));
		jt_sysInspectionContent.getColumnModel().getColumn(0).setMinWidth(0);
		jt_sysInspectionContent.getColumnModel().getColumn(0).setMaxWidth(0);
		jt_sysInspectionContent.getTableHeader().setFont(new Font("宋体", 0, 12));
		jt_sysInspectionContent.getColumnModel().getColumn(1).setPreferredWidth(50);
		jt_sysInspectionContent.getColumnModel().getColumn(5).setPreferredWidth(50);
		jt_sysInspectionContent.getColumnModel().getColumn(6).setPreferredWidth(50);
		jt_sysInspectionContent.getColumnModel().getColumn(6).setPreferredWidth(50);
		jt_sysInspectionContent.getColumnModel().getColumn(7).setPreferredWidth(100);
		jt_sysInspectionContent.getColumnModel().getColumn(8).setPreferredWidth(100);
		jt_sysInspectionContent.getColumnModel().getColumn(10).setPreferredWidth(50);
		jt_sysInspectionContent.getColumnModel().getColumn(10).setPreferredWidth(50);

		scrollPane_1.setViewportView(jt_sysInspectionContent);
		panel.add(jbt_searchContent);

		DefaultTableCellHeaderRenderer dtc = new DefaultTableCellHeaderRenderer();
		dtc.setHorizontalAlignment(JLabel.CENTER);
		jt_sysInspectionContent.getTableHeader().setDefaultRenderer(dtc);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jt_sysInspectionContent.setDefaultRenderer(Object.class, renderer);
		// 结束<<<<<<<<<<<<<<<<<<<<<<

		panel_1 = new JPanel();
		panel_1.setBounds(26, 194, 845, 156);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		jt_inspectionContent = new MyTable(new int[] {0,1,2,3,4,6,8,9,10,11,12,13});

		String[] contentColumnsName = { "id", "来源于系统检测内容Id", "序号", "检测内容", "抽样数量范围", "抽样数量",
			    "单个检测对象实施数量范围", "单个检测对象实施数量", "抽样依据", "计费单位", "收费标准",
				"收费标准单位", "报价依据", "检测内容总金额" };
		jt_inspectionContent.setModel(new DefaultTableModel(null, contentColumnsName));
		// 表头字体居中
		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		jt_inspectionContent.getTableHeader().setDefaultRenderer(hr);
		DefaultTableCellRenderer re = new DefaultTableCellRenderer();
		re.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jt_inspectionContent.setDefaultRenderer(Object.class, re);

		// 设置列宽度
		jt_inspectionContent.getColumnModel().getColumn(0).setMinWidth(0);
		jt_inspectionContent.getColumnModel().getColumn(0).setMaxWidth(0);
		jt_inspectionContent.getColumnModel().getColumn(1).setMinWidth(0);
		jt_inspectionContent.getColumnModel().getColumn(1).setMaxWidth(0);
		jt_inspectionContent.getColumnModel().getColumn(2).setPreferredWidth(15);
		jt_inspectionContent.getColumnModel().getColumn(3).setPreferredWidth(70);
		jt_inspectionContent.getColumnModel().getColumn(4).setPreferredWidth(40);
		jt_inspectionContent.getColumnModel().getColumn(5).setPreferredWidth(120);
		jt_inspectionContent.getColumnModel().getColumn(6).setPreferredWidth(130);
		jt_inspectionContent.getColumnModel().getColumn(7).setPreferredWidth(50);


		scrollPane.setViewportView(jt_inspectionContent);

		lblNewLabel_2 = new JLabel("检验批报价");
		lblNewLabel_2.setBounds(26, 355, 116, 15);
		panel.add(lblNewLabel_2);

		jtf_batchAmount = new JTextField();
		jtf_batchAmount.setColumns(10);
		jtf_batchAmount.setEnabled(false);
		jtf_batchAmount.setBounds(26, 374, 845, 25);
		panel.add(jtf_batchAmount);

		label_3 = new JLabel("中大检测");
		label_3.setBounds(5, 407, 54, 23);
		panel.add(label_3);

		separator = new JSeparator();
		separator.setBounds(54, 420, 835, 2);
		panel.add(separator);

		jbt_confirm = new JButton("确认");
		jbt_confirm.setFocusPainted(false);
		jbt_confirm.setBounds(663, 436, 93, 23);
		panel.add(jbt_confirm);

		jbt_cancel = new JButton("取消");
		jbt_cancel.setFocusPainted(false);
		jbt_cancel.setBounds(778, 436, 93, 23);
		panel.add(jbt_cancel);

		// jp_search置于窗口最前端
		dialog.getLayeredPane().add(jp_search, new Integer(Integer.MAX_VALUE));

		//搜索检验内容按钮点击事件
		jbt_searchContent.setActionCommand("searchSysContent");
		jbt_searchContent.addActionListener(new CreateBatchFrameAction(jt_sysInspectionContent, jtf_contentName, jp_search, industry, address));

		//搜索框添加得到焦点事件
		jtf_contentName.addFocusListener((new CreateBatchFrameAction(jtf_contentName)));

		// 搜索系统检验内容结果面板鼠标点击事件
		if(null != this.batchMap){
			jt_sysInspectionContent.addMouseListener(new CreateBatchFrameAction(jt_sysInspectionContent, jt_inspectionContent, jtf_batchAmount, jp_search, batchMap));
		}else{
			jt_sysInspectionContent.addMouseListener(new CreateBatchFrameAction(jt_sysInspectionContent, jt_inspectionContent, jtf_batchAmount, jp_search, singleContentList));
		}

		// 确认事件
		jbt_confirm.setActionCommand("confirm");
		if(null != this.batchMap){
			jbt_confirm.addActionListener(new CreateBatchFrameAction(dialog, jp_inspectionBatch, jt_inspectionContent, batchMap, jtf_batchName, jtf_batchAmount, jtf_projectAmount));
		}else{
			jbt_confirm.addActionListener(new CreateBatchFrameAction(dialog, jt_quoteTask, jt_quoteProject, jt_inspectionBatch, jt_partInspectionContent, jt_inspectionContent, singleContentList, jtf_batchName, jtf_batchAmount));
		}


		//取消事件
		jbt_cancel.setActionCommand("cancel");
		jbt_cancel.addActionListener(new CreateBatchFrameAction(dialog));

		//添加检验内容Table鼠标悬浮事件
		jt_inspectionContent.addMouseMotionListener(new CreateBatchFrameAction(jt_inspectionContent));
	}
}
