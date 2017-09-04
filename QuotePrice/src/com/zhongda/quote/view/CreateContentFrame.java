package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.action.CreateContentFrameAction;
import com.zhongda.quote.model.SysInspectionContent;
import com.zhongda.quote.service.impl.SysInspectionContenServiceImpl;
import com.zhongda.quote.utils.RenderDataUtils;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;
import com.zhongda.quote.view.uiutils.MyTable;

/**
 * 添加检验内容弹框界面
 * <p>
 *
 * <p>
 *
 * @author 研发中心-Mikepolite<1011592269@qq.com>
 * @sine 2017年8月17日
 */

public class CreateContentFrame {

	public JDialog jaDialog;
	private JPanel panel;
	private JPanel panel_viw, panel_tab;

	private JScrollPane scrollPanemike;
	private JButton bt_addContent, bt_cancel, bt_searchSysContent;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField jtf_contentName;
	private JTable jt_sysInspectionContent;
	private JTable jt_quoteTask;
	private JTable jt_quoteProject;
	private JTable jt_inspectionBatch;
	private JTable jt_inspectionContent;

	/**
	 * @wbp.parser.constructor
	 */
	public CreateContentFrame() {
		init();
	}

	public CreateContentFrame(JTable jt_quoteTask, JTable jt_quoteProject,
			JTable jt_inspectionBatch, JTable jt_inspectionContent) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
		init();
	}


	public void init() {
		jaDialog = new JDialog();
		jaDialog.setBounds(0, 0, 835, 442);
		jaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ImageIcon icon = new ImageIcon("images\\zdLogo1.png");
		jaDialog.setTitle("中大检测");
		jaDialog.setResizable(false);
		jaDialog.setLocationRelativeTo(null);// 设置界面居中
		jaDialog.setIconImage(icon.getImage());
		jaDialog.setModal(true);
		jaDialog.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		jaDialog.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		bt_addContent = new JButton("添加");
		bt_addContent.setBounds(569, 384, 93, 23);
		panel.add(bt_addContent);

		bt_cancel = new JButton("取消");
		bt_cancel.setBounds(713, 384, 93, 23);
		panel.add(bt_cancel);

		panel_viw = new JpaneColorAndPhoto("images/bookpen.png", 740, 10, 48, 48);
		panel_viw.setBounds(0, 0, 829, 76);
		panel_viw.setLayout(null);
		panel.add(panel_viw);

		panel_tab = new JPanel();
		panel_tab.setBounds(0, 75, 829, 253);
		panel_tab.setLayout(new BorderLayout(0, 0));
		panel.add(panel_tab);

		label = new JLabel("添加检验内容");
		label.setFont(new Font("宋体", Font.BOLD, 12));
		label.setBounds(10, 23, 142, 15);
		panel_viw.add(label);

		label_1 = new JLabel("表中检验内容皆出自官方文件，当您选择一个检验内容之后，列表中不会重复出现");
		label_1.setBounds(30, 48, 493, 15);
		panel_viw.add(label_1);


		JSeparator separator = new JSeparator();
		separator.setBounds(52, 375, 767, 2);
		panel.add(separator);

		label_2 = new JLabel("中大检测");
		label_2.setBounds(0, 364, 54, 15);
		panel.add(label_2);

		label_3 = new JLabel("请输入检测内容名称：");
		label_3.setBounds(10, 338, 130, 23);
		panel.add(label_3);

		jtf_contentName = new JTextField();
		jtf_contentName.setBounds(135, 338, 576, 21);
		panel.add(jtf_contentName);
		jtf_contentName.setColumns(10);

		bt_searchSysContent = new JButton("查询");
		bt_searchSysContent.setBounds(721, 338, 85, 23);
		panel.add(bt_searchSysContent);

		// 报价检验内容表格面板
		scrollPanemike = new JScrollPane();
		// 初始化检验内容表列名
		String[] sysContentColumnsName = { "序号id", "检测内容", "抽样数量范围", "抽样数量",
			    "(实施数量范围)单个检测对象", "(实施数量)单个检验对象", "抽样依据", "计费单位", "收费标准",
				"收费标准单位", "报价依据" };
		jt_sysInspectionContent = new MyTable(new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		jt_sysInspectionContent.setModel(new DefaultTableModel(null, sysContentColumnsName));
		jt_sysInspectionContent.getColumnModel().getColumn(0).setMinWidth(0);
		jt_sysInspectionContent.getColumnModel().getColumn(0).setMaxWidth(0);
		jt_sysInspectionContent.getTableHeader().setFont(new Font("宋体", 0, 12));
		jt_sysInspectionContent.getColumnModel().getColumn(1).setPreferredWidth(45);
		jt_sysInspectionContent.getColumnModel().getColumn(2).setPreferredWidth(62);
		jt_sysInspectionContent.getColumnModel().getColumn(3).setPreferredWidth(38);
		jt_sysInspectionContent.getColumnModel().getColumn(5).setPreferredWidth(70);
		jt_sysInspectionContent.getColumnModel().getColumn(6).setPreferredWidth(38);
		jt_sysInspectionContent.getColumnModel().getColumn(7).setPreferredWidth(38);
		jt_sysInspectionContent.getColumnModel().getColumn(8).setPreferredWidth(38);
		jt_sysInspectionContent.getColumnModel().getColumn(9).setPreferredWidth(70);
		jt_sysInspectionContent.getColumnModel().getColumn(10).setPreferredWidth(36);

		// 设置单行可被选中
		jt_sysInspectionContent.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		// 设置表头不可移动
		jt_sysInspectionContent.getTableHeader().setReorderingAllowed(false);
		scrollPanemike.setViewportView(jt_sysInspectionContent);
		panel_tab.add(scrollPanemike, BorderLayout.CENTER);

		//获取检验批Id
		int batchRow = jt_inspectionBatch.getSelectedRow();
		final int batchId = (int) jt_inspectionBatch.getValueAt(batchRow, 0);
		// 生成该窗口时启动任务线程从数据库加载初始化数据(包括所有检验内容)
		new SwingWorker<List<SysInspectionContent>, SysInspectionContent>() {

			@Override
			protected List<SysInspectionContent> doInBackground()
					throws Exception {
				// 从数据库获取所有未选中系统检验内容
				return new SysInspectionContenServiceImpl()
						.selectSysInspectionContent(batchId);
			}

			@Override
			protected void done() {
				List<SysInspectionContent> sysContentList;
				try {
					sysContentList = get();
					RenderDataUtils.renderSysContentData(jt_sysInspectionContent, sysContentList);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();

		//添加按钮事件
		bt_addContent.setActionCommand("addContent");
		bt_addContent.addActionListener(new CreateContentFrameAction(jt_quoteTask, jt_quoteProject,
				jt_inspectionBatch, jt_inspectionContent, jaDialog, jt_sysInspectionContent));

		//取消按钮事件
		bt_cancel.setActionCommand("cancel");
		bt_cancel.addActionListener(new CreateContentFrameAction(jaDialog));

		//查询按钮事件
		bt_searchSysContent.setActionCommand("searchContent");
		bt_searchSysContent.addActionListener(new CreateContentFrameAction(jtf_contentName,jt_sysInspectionContent));

		//添加鼠标悬浮事件
		jt_sysInspectionContent.addMouseMotionListener(new CreateContentFrameAction(null,jt_sysInspectionContent));
	}
}
