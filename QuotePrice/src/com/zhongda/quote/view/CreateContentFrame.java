package com.zhongda.quote.view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import com.zhongda.quote.action.CreateContentFrameAction;
import com.zhongda.quote.model.SysInspectionContent;
import com.zhongda.quote.service.impl.SysInspectionContenServiceImpl;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;
import com.zhongda.quote.view.uiutils.MyTable;
import java.awt.Font;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

/**
 * 创建检验内容弹框界面
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
	private MyTable tab_viw;
	private JScrollPane scrollPanemike;
	private JButton btnNewButton_add, btnNewButton_no, btnNewButton_sertch;
	private JTable jt_inspectionContent;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private Integer inspectionid;
	private JLabel label_3;
	private JTextField textField;

	public static void main(String[] args) {
		new CreateContentFrame();
	}


	/**
	 * @wbp.parser.constructor
	 */
	public CreateContentFrame() {
		init();
	}

	// 检验批ID 检验内容主界面table 是否是删除操作
	public CreateContentFrame(Integer inspectionid, JTable jt_inspectionContent) {
		this.inspectionid = inspectionid;
		this.jt_inspectionContent = jt_inspectionContent;
		init();
	}

	public void init() {
		jaDialog = new JDialog();
		jaDialog.setBounds(0, 0, 700, 442);
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

		btnNewButton_add = new JButton("添加");
		btnNewButton_add.setBounds(446, 384, 93, 23);
		panel.add(btnNewButton_add);

		btnNewButton_no = new JButton("取消");
		btnNewButton_no.setBounds(572, 384, 93, 23);
		panel.add(btnNewButton_no);

		panel_viw = new JpaneColorAndPhoto("images/bookpen.png", 430, 2, 48, 48);
		panel_viw.setBounds(0, 0, 694, 76);
		panel_viw.setLayout(null);
		panel.add(panel_viw);

		panel_tab = new JPanel();
		panel_tab.setBounds(0, 75, 694, 253);
		panel_tab.setLayout(new BorderLayout(0, 0));
		panel.add(panel_tab);

		label = new JLabel("新建/修改检验内容");
		label.setFont(new Font("宋体", Font.BOLD, 12));
		label.setBounds(10, 23, 142, 15);
		panel_viw.add(label);

		label_1 = new JLabel("表中可选中的位置为可修改信息，不可选中的为系统默认信息");
		label_1.setBounds(30, 48, 365, 15);
		panel_viw.add(label_1);


		JSeparator separator = new JSeparator();
		separator.setBounds(52, 375, 642, 2);
		panel.add(separator);

		label_2 = new JLabel("中大检测");
		label_2.setBounds(0, 364, 54, 15);
		panel.add(label_2);

		btnNewButton_no.addActionListener(new CreateContentFrameAction(jaDialog));

		label_3 = new JLabel("查询（请输入关键字）");
		label_3.setBounds(10, 338, 130, 23);
		panel.add(label_3);

		textField = new JTextField();
		textField.setBounds(135, 338, 446, 21);
		panel.add(textField);
		textField.setColumns(10);

		btnNewButton_sertch = new JButton("确定");
		btnNewButton_sertch.setBounds(599, 338, 85, 23);
		panel.add(btnNewButton_sertch);

		// 报价检验内容表格面板
		scrollPanemike = new JScrollPane();
		// 初始化检验内容表列名
		final Object contentColumnsName[] = { "序号", "检验内容", "抽样依据", "抽样数量范围",
				"抽样数量", "报价依据", "单个检测对象数量范围", "单个检验对象实施数量", "计费单位", "收费标准" };
		tab_viw = new MyTable(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		DefaultTableModel contentTableModel = new DefaultTableModel(null,
				contentColumnsName);
		tab_viw.setModel(contentTableModel);
		tab_viw.getColumnModel().getColumn(0).setMinWidth(0);
		tab_viw.getColumnModel().getColumn(0).setMaxWidth(0);
		// 设置单行可被选中
		tab_viw.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		// 设置表头不可移动
		tab_viw.getTableHeader().setReorderingAllowed(false);
		scrollPanemike.setViewportView(tab_viw);
		panel_tab.add(scrollPanemike, BorderLayout.CENTER);
		// 生成该窗口时启动任务线程从数据库加载初始化数据(包括所有检验内容)
		new SwingWorker<List<SysInspectionContent>, SysInspectionContent>() {

			@Override
			protected List<SysInspectionContent> doInBackground()
					throws Exception {
				// 从数据库获取所有未选中系统检验内容
				return new SysInspectionContenServiceImpl()
						.selectSysInspectionContent(inspectionid);
			}

			@Override
			protected void done() {
				List<SysInspectionContent> sysInspectionContentSysInspectionContentList;
				try {
					sysInspectionContentSysInspectionContentList = get();
					DefaultTableModel model = (DefaultTableModel) tab_viw
							.getModel();
					for (SysInspectionContent sic : sysInspectionContentSysInspectionContentList) {
						Vector<Object> dataRow = new Vector<Object>();
						dataRow.add(sic.getId());
						dataRow.add(sic.getInspectionContentName());
						dataRow.add(sic.getSampleBasisId());
						dataRow.add(sic.getSampleQuantityRange());
						dataRow.add(sic.getSampleQuantity());
						dataRow.add(sic.getQuoteBasisId());
						dataRow.add(sic.getSingleQuantityRange());
						dataRow.add(sic.getSingleObjectQuantity());
						dataRow.add(sic.getChargeUnit());
						dataRow.add(sic.getChargeStandard());
						model.addRow(dataRow);
						tab_viw.setRowSelectionInterval(0, 0);// 选中第一行
					}
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();

		btnNewButton_add.setActionCommand("add");
		// 传入检验批ID-inspectionid,tab-viw
		btnNewButton_add.addActionListener(new CreateContentFrameAction(
				jt_inspectionContent, inspectionid, jaDialog, tab_viw));
		btnNewButton_no.setActionCommand("no");
		btnNewButton_sertch.setActionCommand("sertch");
		btnNewButton_sertch.addActionListener(new CreateContentFrameAction(textField,tab_viw));

	}
}
