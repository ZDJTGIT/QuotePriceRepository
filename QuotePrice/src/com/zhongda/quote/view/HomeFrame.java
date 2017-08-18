package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.action.HomeFrameAction;
import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.InspectionBatchServiceImpl;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.service.impl.QuoteProjectServiceImpl;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;
import com.zhongda.quote.view.uiutils.JMenBarColor;
import com.zhongda.quote.view.uiutils.MyTable;

/**
 * <p>
 * UI主界面
 * </p>
 *
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月7日
 */

public class HomeFrame {

	public JFrame frame;
	private Dimension scrSize;
	private JMenuBar jmb_tb;
	private JMenu jm_f;
	private JMenu jm_e;
	private JMenu jm_l;
	private JMenu jm_g;
	private JMenu jm_v;
	private JMenu jm_t;
	private JMenu jm_w;
	private JMenu jm_h;
	private JMenu jm_f_1;
	private JMenuItem jm_f_2;
	private JMenu jm_f_3;
	private JMenuItem jm_f_4;
	private JMenuItem jm_f_5;
	private JMenuItem jm_e_1;
	private JMenuItem jm_e_2;
	private JMenu jm_l_1;
	private JMenuItem jm_l_2;
	private JToolBar jtb_tb;
	private JButton jb_bt_1;
	private JButton jb_bt_save;
	private JTabbedPane tabbedPane;
	private JSplitPane sp_left;
	private JSplitPane sp_center;
	private JSplitPane sp_right;
	private JPanel jp_left;
	private JPanel jp_up;
	private JPanel jp_down;
	private JPanel jp_right;
	private JToolBar jtb_south;
	private JLabel jtbs_jlb_left;
	private JLabel jtbs_jlb_2;
	private JLabel jtbs_jlb_3;
	private JLabel jtbs_jlb_right;
	private JMenBarColor jmb_left;
	private JPanel jpanel_left;
	private JToolBar jtb_jsrw;
	private JLabel jmb_left_lb;
	private JButton bt_createTask;
	private JButton bt_importTask;
	private JMenBarColor jmb_center_up;
	private JPanel jpanel_center_up;
	private JToolBar jtb_center_up;
	private JButton jbt_createProject;
	private JButton jbt_updateProject;
	private JMenBarColor jmb_center_down;
	private JPanel jpanel_center_down;
	private JToolBar jtb_center_down;
	private JMenBarColor jmb_right;
	private JPanel jpanel_right;
	private JToolBar jtb_right_jcnr;
	private JButton jbt_createInspectionBatch;
	private JButton jbt_updateInspectionBatch;
	private JButton jbt_createContent;
	private JButton jbt_updateContent;
	private JLabel jlb_quoteProject;
	private JLabel jlb_center_down;
	private JLabel jlb_right_jcnr;
	private JButton bt_exportTask;
	private JButton bt_updateTask;
	private JButton bt_deleteTask;
	private JLabel jlb_jsrw;
	private JTextField jtf_queryTaskName;
	private JButton bt_queryTask;
	private JPanel jf_jpanel;
	private JButton jbt_deleteProject;
	private JButton jbt_queryProject;
	private JButton jbt_deleteInspectionBatch;
	private JButton jbt_queryInspectionBatch;
	private JButton jbt_deleteContent;
	private JButton jbt_queryContent;
	private JLabel jlb_rwxm;
	private JTextField jtf_rwxm;
	private JButton jbt_queryProject_next;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton jbt_queryInspectionBatch_next;
	private JLabel jlb_jyp;
	private JTextField jtf_jyp;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton jbt_queryContent_next;
	private JLabel jlb_jcnr;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField jtf_jcnr;
	private JButton bt_createTask_next;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JPanel panel;
	private JLabel lblNewLabel_8;
	private int sp_left_size;
	private int sp_center_size;
	private int sp_right_size;
	private Insets screenInsets;
	private int height;
	private JScrollPane jsp_jsrw;
	private DefaultTableModel dtm;
	private JTable jt_quoteTask;

	private JPanel jp_center_up;
	private JScrollPane jsp_center_up;
	private MyTable jt_quoteProject;
	private DefaultTableModel dtmPJ;
	private JScrollPane scrollPane;
	private MyTable jt_inspectionBatch;

	private JScrollPane scrollPanemike;
	private JTable jt_inspectionContent;

	/**
	 * Create the frame.
	 */
	public HomeFrame() {

		init();

	}

	public void init() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 获取当前电脑屏幕宽高
		scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 获取当前电脑任务栏高度
		screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(
				frame.getGraphicsConfiguration());
		height = screenInsets.bottom;
		frame.setBounds(0, 0, scrSize.width, scrSize.height - height);
		frame.setLocationRelativeTo(null);// 居中
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setIconImage(frame.getToolkit().getImage("images/zdLogo1.png"));
		frame.setTitle("中大检测报价软件单机版");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);
		// frame.setResizable(false);// 不允许改变窗口大小

		jmb_tb = new JMenuBar();
		frame.setJMenuBar(jmb_tb);

		// 工具栏文件及文件下的子组件
		jm_f = new JMenu("文件(F) ");
		jmb_tb.add(jm_f);

		jm_f_1 = new JMenu("新建    Ctrl+N");
		jm_f.add(jm_f_1);

		jm_f_2 = new JMenuItem("保存    Ctrl+S");
		jm_f.add(jm_f_2);

		jm_f_3 = new JMenu("检");
		jm_f.add(jm_f_3);

		jm_f_4 = new JMenuItem("测");
		jm_f.add(jm_f_4);

		jm_f_5 = new JMenuItem("研");
		jm_f.add(jm_f_5);

		// 工具栏编辑及其子组件
		jm_e = new JMenu("编辑(E) ");
		jmb_tb.add(jm_e);

		jm_e_1 = new JMenuItem("New menu item");
		jm_e.add(jm_e_1);

		jm_e_2 = new JMenuItem("New menu item");
		jm_e.add(jm_e_2);

		// 工具栏计算及其下子组件
		jm_l = new JMenu("计算(L) ");
		jmb_tb.add(jm_l);

		jm_l_1 = new JMenu("New menu");
		jm_l.add(jm_l_1);

		jm_l_2 = new JMenuItem("New menu item");
		jm_l_1.add(jm_l_2);

		// 工具栏造价审查
		jm_g = new JMenu("造价审查(G)");
		jmb_tb.add(jm_g);

		// 工具视图
		jm_v = new JMenu("视图(V) ");
		jmb_tb.add(jm_v);

		// 工具栏工具
		jm_t = new JMenu("工具(T) ");
		jmb_tb.add(jm_t);

		// 工具栏窗口
		jm_w = new JMenu("窗口(W) ");
		jmb_tb.add(jm_w);

		// 工具栏帮助
		jm_h = new JMenu("帮助(H) ");
		jmb_tb.add(jm_h);

		jf_jpanel = new JPanel();
		frame.getContentPane().add(jf_jpanel, BorderLayout.CENTER);
		jf_jpanel.setLayout(new BorderLayout(0, 0));

		// 最下方提示信息组件
		jtb_south = new JToolBar();
		jtb_south.setFloatable(false);
		jf_jpanel.add(jtb_south, BorderLayout.SOUTH);

		jtbs_jlb_left = new JLabel("就绪                ");
		jtbs_jlb_left.setBorder(new BevelBorder(BevelBorder.RAISED));
		jtb_south.add(jtbs_jlb_left);

		jtbs_jlb_2 = new JLabel("湖南中大检测软件有限公司          ");
		jtbs_jlb_2.setBorder(new BevelBorder(BevelBorder.RAISED));
		jtb_south.add(jtbs_jlb_2);

		jtbs_jlb_3 = new JLabel("服务热线： 86-0731-88137791                 ");
		jtbs_jlb_3.setBorder(new BevelBorder(BevelBorder.RAISED));
		jtb_south.add(jtbs_jlb_3);

		jtbs_jlb_right = new JLabel(
				"                                                                                                                                                                                                                          ");
		jtbs_jlb_right.setBorder(new BevelBorder(BevelBorder.RAISED));
		jtb_south.add(jtbs_jlb_right);

		// 任务JTabbedPane面板
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		jf_jpanel.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setFont(new Font("黑体", 0, 15));

		// 调整模块大小
		sp_left_size = (int) (scrSize.getWidth() * 0.5);
		sp_center_size = (int) (scrSize.getWidth() * 0.3);
		sp_right_size = (int) (scrSize.getHeight() * 0.4);

		panel = new JPanel();
		tabbedPane.addTab("任务管理", null, panel, null);
		panel.setFont(new Font("黑体", 0, 20));
		panel.setLayout(new BorderLayout(0, 0));

		sp_left = new JSplitPane();
		panel.add(sp_left);
		sp_left.setDividerLocation(sp_left_size);

		sp_center = new JSplitPane();
		sp_center.setDividerLocation(sp_center_size);
		sp_left.setRightComponent(sp_center);

		sp_right = new JSplitPane();
		sp_right.setDividerLocation(sp_right_size);// 设置splitpane左边大小

		sp_right.setOrientation(JSplitPane.VERTICAL_SPLIT);
		sp_center.setLeftComponent(sp_right);

		// splitPane分区的JPane
		jp_up = new JPanel();
		sp_right.setLeftComponent(jp_up);
		jp_up.setLayout(new BorderLayout(0, 0));

		// 项目分段
		jmb_center_up = new JMenBarColor();
		jp_up.add(jmb_center_up, BorderLayout.NORTH);

		jlb_quoteProject = new JLabel("报价项目");
		jlb_quoteProject.setBorder(new EmptyBorder(3, 10, 3, 0));
		jlb_quoteProject.setFont(new Font("黑体", 1, 14));
		jmb_center_up.add(jlb_quoteProject);

		jpanel_center_up = new JPanel();
		jp_up.add(jpanel_center_up, BorderLayout.CENTER);
		jpanel_center_up.setLayout(new BorderLayout(0, 0));

		jtb_center_up = new JToolBar();
		jtb_center_up.setBorderPainted(false);// 工具栏不绘制边框
		jtb_center_up.setFloatable(false);// 工具栏不能拖动
		jpanel_center_up.add(jtb_center_up, BorderLayout.NORTH);

		jbt_createProject = new JButton();
		jbt_createProject.setIcon(new ImageIcon("images/add.png"));
		jbt_createProject.setToolTipText("新增项目");
		jbt_createProject.setActionCommand("creatProject");
		jbt_createProject.setFocusPainted(false);// 去除按钮边线
		jtb_center_up.add(jbt_createProject);

		jbt_updateProject = new JButton();
		jbt_updateProject.setIcon(new ImageIcon("images/updatepen.png"));
		jbt_updateProject.setToolTipText("修改项目");
		jbt_updateProject.setFocusPainted(false);// 去除按钮边线
		jtb_center_up.add(jbt_updateProject);

		jbt_deleteProject = new JButton();
		jbt_deleteProject.setIcon(new ImageIcon("images/del.png"));
		jbt_deleteProject.setToolTipText("删除项目");
		jbt_deleteProject.setFocusPainted(false);// 去除按钮边线
		jtb_center_up.add(jbt_deleteProject);

		jtb_center_up.addSeparator();// 分隔符
		jlb_rwxm = new JLabel(" 查找 ");
		jtb_center_up.add(jlb_rwxm);

		jtf_rwxm = new JTextField();
		jtb_center_up.add(jtf_rwxm);
		jtf_rwxm.setColumns(10);

		lblNewLabel = new JLabel(" ");
		jtb_center_up.add(lblNewLabel);

		jbt_queryProject = new JButton();
		jbt_queryProject.setIcon(new ImageIcon("images/find.png"));
		jbt_queryProject.setToolTipText("查找项目");
		jbt_queryProject.setFocusPainted(false);// 去除按钮边线
		jtb_center_up.add(jbt_queryProject);

		jbt_queryProject_next = new JButton();
		jbt_queryProject_next.setIcon(new ImageIcon("images/next.png"));
		jbt_queryProject_next.setToolTipText("查找下一个");
		jbt_queryProject_next.setFocusPainted(false);// 去除按钮边线
		jtb_center_up.add(jbt_queryProject_next);

		lblNewLabel_1 = new JLabel("  ");
		jtb_center_up.add(lblNewLabel_1);

		jp_center_up = new JPanel();
		jpanel_center_up.add(jp_center_up, BorderLayout.CENTER);
		jp_center_up.setLayout(new BorderLayout(0, 0));

		jsp_center_up = new JScrollPane();
		jp_center_up.add(jsp_center_up, BorderLayout.CENTER);

		// 项目表

		final String[] projectHeadSt = { "序号", "项目名称", "行业", "项目地址", "其他费用", "项目总金额" };
		jt_quoteProject = new MyTable(new int[] { 1, 2, 3, 4, 5 });
		jsp_center_up.setViewportView(jt_quoteProject);

		jp_down = new JPanel();
		sp_right.setRightComponent(jp_down);
		jp_down.setLayout(new BorderLayout(0, 0));

		// 检测批
		jmb_center_down = new JMenBarColor();
		jp_down.add(jmb_center_down, BorderLayout.NORTH);

		jlb_center_down = new JLabel("检验批");
		jlb_center_down.setBorder(new EmptyBorder(3, 10, 3, 0));
		jlb_center_down.setFont(new Font("黑体", 1, 14));
		jmb_center_down.add(jlb_center_down);

		jpanel_center_down = new JPanel();
		jp_down.add(jpanel_center_down, BorderLayout.CENTER);
		jpanel_center_down.setLayout(new BorderLayout(0, 0));

		jtb_center_down = new JToolBar();
		jtb_center_down.setBorderPainted(false);// 工具栏不绘制边框
		jtb_center_down.setFloatable(false);// 工具栏不能拖动
		jpanel_center_down.add(jtb_center_down, BorderLayout.NORTH);

		jbt_createInspectionBatch = new JButton();
		jbt_createInspectionBatch.setIcon(new ImageIcon("images/add.png"));
		jbt_createInspectionBatch.setToolTipText("新增检验批");
		jbt_createInspectionBatch.setActionCommand("addInspection");
		jbt_createInspectionBatch.setFocusPainted(false);// 去除按钮边线
		jtb_center_down.add(jbt_createInspectionBatch);

		jbt_updateInspectionBatch = new JButton();
		jbt_updateInspectionBatch
				.setIcon(new ImageIcon("images/updatepen.png"));
		jbt_updateInspectionBatch.setToolTipText("修改检验批");
		jbt_updateInspectionBatch.setFocusPainted(false);// 去除按钮边线
		jtb_center_down.add(jbt_updateInspectionBatch);

		jbt_deleteInspectionBatch = new JButton();
		jbt_deleteInspectionBatch.setIcon(new ImageIcon("images/del.png"));
		jbt_deleteInspectionBatch.setToolTipText("删除检验批");
		jbt_deleteInspectionBatch.setFocusPainted(false);// 去除按钮边线
		jtb_center_down.add(jbt_deleteInspectionBatch);

		jtb_center_down.addSeparator();// 分隔符
		jlb_jyp = new JLabel(" 查找 ");
		jtb_center_down.add(jlb_jyp);

		jtf_jyp = new JTextField();
		jtb_center_down.add(jtf_jyp);
		jtf_jyp.setColumns(10);

		lblNewLabel_2 = new JLabel(" ");
		jtb_center_down.add(lblNewLabel_2);

		jbt_queryInspectionBatch = new JButton();
		jbt_queryInspectionBatch.setIcon(new ImageIcon("images/find.png"));
		jbt_queryInspectionBatch.setToolTipText("查找");
		jbt_queryInspectionBatch.setFocusPainted(false);// 去除按钮边线
		jtb_center_down.add(jbt_queryInspectionBatch);

		jbt_queryInspectionBatch_next = new JButton();
		jbt_queryInspectionBatch_next.setIcon(new ImageIcon("images/next.png"));
		jbt_queryInspectionBatch_next.setToolTipText("查找下一个");
		jbt_queryInspectionBatch_next.setFocusPainted(false);// 去除按钮边线
		jtb_center_down.add(jbt_queryInspectionBatch_next);

		lblNewLabel_3 = new JLabel("  ");
		jtb_center_down.add(lblNewLabel_3);

		scrollPane = new JScrollPane();
		jpanel_center_down.add(scrollPane, BorderLayout.CENTER);

		final String[] inspectionHeadName = { "序号", "检验批名称", "检验批总金额" };
		int[] inspectionNumber = { 1, 2 };
		jt_inspectionBatch = new MyTable(inspectionNumber);
		scrollPane.setViewportView(jt_inspectionBatch);

		jp_right = new JPanel();
		sp_center.setRightComponent(jp_right);
		jp_right.setLayout(new BorderLayout(0, 0));

		// 检测内容
		jmb_right = new JMenBarColor();
		jp_right.add(jmb_right, BorderLayout.NORTH);

		jlb_right_jcnr = new JLabel("检测内容");
		jlb_right_jcnr.setBorder(new EmptyBorder(3, 10, 3, 0));
		jlb_right_jcnr.setFont(new Font("黑体", 1, 14));
		jmb_right.add(jlb_right_jcnr);

		jpanel_right = new JPanel();
		jp_right.add(jpanel_right, BorderLayout.CENTER);
		jpanel_right.setLayout(new BorderLayout(0, 0));

		jtb_right_jcnr = new JToolBar();
		jtb_right_jcnr.setBorderPainted(false);// 工具栏不绘制边框
		jtb_right_jcnr.setFloatable(false);// 工具栏不能拖动
		jpanel_right.add(jtb_right_jcnr, BorderLayout.NORTH);

		jbt_createContent = new JButton();
		jbt_createContent.setIcon(new ImageIcon("images/add.png"));
		jbt_createContent.setToolTipText("新增检验内容");
		jbt_createContent.setFocusPainted(false);// 去除按钮边线
		jtb_right_jcnr.add(jbt_createContent);

		jbt_updateContent = new JButton();
		jbt_updateContent.setIcon(new ImageIcon("images/updatepen.png"));
		jbt_updateContent.setToolTipText("修改检验内容");
		jbt_updateContent.setFocusPainted(false);// 去除按钮边线
		jtb_right_jcnr.add(jbt_updateContent);

		jbt_deleteContent = new JButton();
		jbt_deleteContent.setIcon(new ImageIcon("images/del.png"));
		jbt_deleteContent.setToolTipText("删除检验内容");
		jbt_deleteContent.setFocusPainted(false);// 去除按钮边线
		jtb_right_jcnr.add(jbt_deleteContent);

		jtb_right_jcnr.addSeparator();// 分隔符
		jlb_jcnr = new JLabel(" 查找  ");
		jtb_right_jcnr.add(jlb_jcnr);

		jtf_jcnr = new JTextField();
		jtb_right_jcnr.add(jtf_jcnr);
		jtf_jcnr.setColumns(10);

		lblNewLabel_4 = new JLabel(" ");
		jtb_right_jcnr.add(lblNewLabel_4);

		jbt_queryContent = new JButton();
		jbt_queryContent.setIcon(new ImageIcon("images/find.png"));
		jbt_queryContent.setToolTipText("查找");
		jbt_queryContent.setFocusPainted(false);// 去除按钮边线
		jtb_right_jcnr.add(jbt_queryContent);

		jbt_queryContent_next = new JButton();
		jbt_queryContent_next.setIcon(new ImageIcon("images/next.png"));
		jbt_queryContent_next.setToolTipText("查找下一个");
		jbt_queryContent_next.setFocusPainted(false);// 去除按钮边线
		jtb_right_jcnr.add(jbt_queryContent_next);

		lblNewLabel_5 = new JLabel("  ");
		jtb_right_jcnr.add(lblNewLabel_5);

		jp_left = new JPanel();
		sp_left.setLeftComponent(jp_left);
		jp_left.setLayout(new BorderLayout(0, 0));

		// 建设任务
		jmb_left = new JMenBarColor();
		jmb_left.setBackground(Color.cyan);
		jp_left.add(jmb_left, BorderLayout.NORTH);

		jmb_left_lb = new JLabel("报价任务");
		jmb_left_lb.setBorder(new EmptyBorder(3, 10, 3, 0));
		jmb_left_lb.setFont(new Font("黑体", 1, 14));
		jmb_left.add(jmb_left_lb);

		jpanel_left = new JPanel();
		jp_left.add(jpanel_left, BorderLayout.CENTER);
		jpanel_left.setLayout(new BorderLayout(0, 0));

		jtb_jsrw = new JToolBar();
		jtb_jsrw.setBorderPainted(false);// 工具栏不绘制边框
		jtb_jsrw.setFloatable(false);// 工具栏不能拖动
		jpanel_left.add(jtb_jsrw, BorderLayout.NORTH);

		// 建设任务工具栏
		bt_createTask = new JButton();
		bt_createTask.setIcon(new ImageIcon("images/creat_1.png"));
		bt_createTask.setToolTipText("新建建设任务");
		bt_createTask.setFocusPainted(false);// 去除按钮边线
		jtb_jsrw.add(bt_createTask);

		bt_importTask = new JButton();
		bt_importTask.setIcon(new ImageIcon("images/daoru.png"));
		bt_importTask.setToolTipText("导入建设任务");
		bt_importTask.setFocusPainted(false);// 去除按钮边线
		jtb_jsrw.add(bt_importTask);

		bt_exportTask = new JButton();
		bt_exportTask.setIcon(new ImageIcon("images/daochu.png"));
		bt_exportTask.setToolTipText("导出建设任务");
		bt_exportTask.setFocusPainted(false);// 去除按钮边线
		jtb_jsrw.add(bt_exportTask);

		bt_updateTask = new JButton();
		bt_updateTask.setIcon(new ImageIcon("images/updatepen.png"));
		bt_updateTask.setToolTipText("修改建设任务");
		bt_updateTask.setFocusPainted(false);// 去除按钮边线
		// bt_updateTask.setBorder(new BevelBorder(BevelBorder.RAISED));
		jtb_jsrw.add(bt_updateTask);

		bt_deleteTask = new JButton();
		bt_deleteTask.setIcon(new ImageIcon("images/delete.png"));
		bt_deleteTask.setFocusPainted(false);// 去除按钮边线
		bt_deleteTask.setToolTipText("删除建设任务");
		jtb_jsrw.addSeparator();// 分隔符
		jtb_jsrw.add(bt_deleteTask);
		jtb_jsrw.addSeparator();

		jlb_jsrw = new JLabel(" 查找 ");
		jtb_jsrw.add(jlb_jsrw);

		jtf_queryTaskName = new JTextField();
		jtb_jsrw.add(jtf_queryTaskName);
		jtf_queryTaskName.setColumns(10);

		lblNewLabel_7 = new JLabel(" ");
		jtb_jsrw.add(lblNewLabel_7);

		bt_queryTask = new JButton();
		bt_queryTask.setIcon(new ImageIcon("images/find.png"));
		bt_queryTask.setToolTipText("查找任务");
		bt_queryTask.setFocusPainted(false);// 去除按钮边线
		jtb_jsrw.add(bt_queryTask);

		bt_createTask_next = new JButton();
		bt_createTask_next.setIcon(new ImageIcon("images/next.png"));
		bt_createTask_next.setToolTipText("查找下一个");
		bt_createTask_next.setFocusPainted(false);// 去除按钮边线
		jtb_jsrw.add(bt_createTask_next);

		lblNewLabel_6 = new JLabel("  ");
		jtb_jsrw.add(lblNewLabel_6);

		// 建设任务表格面板
		jsp_jsrw = new JScrollPane();

		// 初始化列名
		final Object[] taskColumnsName = { "序号", "任务编号", "任务名称", "任务描述", "创建人",
				"创建时间", "最后修改时间", "任务总金额" };
		jt_quoteTask = new JTable() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 1 || column == 4 || column == 6 || column == 5)
					return false;// 不可编辑
				return true;// 可编辑
			}
		};

		jsp_jsrw.setViewportView(jt_quoteTask);
		jpanel_left.add(jsp_jsrw, BorderLayout.CENTER);

		// 添加检验内容表
		// 初始化列名
		scrollPanemike = new JScrollPane();
		final Object contentColumnsName[] = { "序号", "检验内容名称", "抽样数量", "单个检验对象实施数量",
				"收费标准" };

		jt_inspectionContent = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;// 设置不可编辑文字
			}
		};
		// 设置单行可被选中
		jt_inspectionContent.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		jt_inspectionContent.getTableHeader().setReorderingAllowed(false);// 设置表头不可移动
		scrollPanemike.setViewportView(jt_inspectionContent);
		jpanel_right.add(scrollPanemike, BorderLayout.CENTER);

		// 生成该窗口时启动任务线程从数据库加载初始化数据
		new SwingWorker<Map<String, Object>, Void>() {

			@Override
			protected Map<String, Object> doInBackground() throws Exception {
				Map<String, Object> map = new HashMap<String, Object>();
				// 从数据库获取报价任务数据
				List<QuoteTask> taskList = new QuoteTaskServiceImpl()
						.queryAllQuoteTask();
				map.put("quoteTask", taskList);
				// 从数据库获取项目数据
				List<QuoteProject> projectList = null;
				if (null != taskList && taskList.size() > 0) {
					QuoteTask quoteTask = taskList.get(0);
					projectList = new QuoteProjectServiceImpl()
							.queryAllQuoteProjectsByTaskNmber(quoteTask.getId());
					map.put("quoteProject", projectList);
				}
				// 从数据库获取检验批数据
				List<InspectionBatch> batchList = null;
				if (null != projectList && projectList.size() > 0) {
					QuoteProject quoteProject = projectList.get(0);
					batchList = new InspectionBatchServiceImpl()
							.queryAllInspectionBatchByProjectID(quoteProject
									.getId());
					map.put("inspectionBatch", batchList);
				}
				// 从数据库获取检验内容数据
				List<InspectionContent> ContentList = null;
				if (null != batchList && batchList.size() > 0) {
					InspectionBatch inspectionBatch = batchList.get(0);
					ContentList = new InspectionContentServiceImpl()
							.queryAllInspectionContentByBatchId(inspectionBatch
									.getId());
					map.put("inspectionContent", ContentList);
				}
				return map;
			}

			@Override
			protected void done() {
				Map<String, Object> map;
				try {
					map = get();
					@SuppressWarnings("unchecked")
					List<QuoteTask> taskList = (List<QuoteTask>) map
							.get("quoteTask");
					// 填充任务数据
					int length = 0;
					if (null != taskList && (length = taskList.size()) > 0) {
						Object[][] rowData = new Object[length][taskColumnsName.length];
						int index = 0;
						for (QuoteTask quoteTask : taskList) {
							rowData[index][0] = quoteTask.getId();
							rowData[index][1] = quoteTask.getTaskNumber();
							rowData[index][2] = quoteTask.getTaskName();
							rowData[index][3] = quoteTask.getTaskDescription();
							rowData[index][4] = quoteTask.getCreateUser();
							rowData[index][5] = quoteTask.getCreateDate();
							rowData[index][6] = quoteTask.getLastUpdateDate();
							rowData[index][7] = quoteTask.getTaskAmount();
							index++;
						}
						dtm = new DefaultTableModel(rowData, taskColumnsName);
						jt_quoteTask.isCellEditable(2, 1);// 1列不可编辑
						jt_quoteTask.isCellEditable(2, 4);// 8列不可编辑
						jt_quoteTask.isCellEditable(2, 6);// 6列不可编辑
						jt_quoteTask.isCellEditable(2, 5);// 7列不可编辑

						jt_quoteTask.setModel(dtm);
						jt_quoteTask.getColumnModel().getColumn(0)
								.setMinWidth(0);
						jt_quoteTask.getColumnModel().getColumn(0)
								.setMaxWidth(0);
						jt_quoteTask.getColumnModel().getColumn(1)
								.setPreferredWidth(110);
						jt_quoteTask.getColumnModel().getColumn(2)
								.setPreferredWidth(76);
						jt_quoteTask.getColumnModel().getColumn(3)
								.setPreferredWidth(137);
						jt_quoteTask.getColumnModel().getColumn(4)
								.setPreferredWidth(44);
						jt_quoteTask.getColumnModel().getColumn(5)
								.setPreferredWidth(55);
						jt_quoteTask.getColumnModel().getColumn(6)
								.setPreferredWidth(70);
						jt_quoteTask
								.addMouseMotionListener(new HomeFrameAction(
										jt_quoteTask, null, null, null));
						jt_quoteTask.setRowSelectionInterval(0, 0);// 选中第一行

						// Table表中添加日期组件
						// Chooser ser = Chooser.getInstance();
						// JTextField jtf_date = new JTextField();
						// jtf_date.setEditable(false);
						// ser.register(jtf_date);
						// jt_quoteTask.getColumnModel().getColumn(5)
						// .setCellEditor(new DefaultCellEditor(jtf_date));

						// // 项目模块代码
						// 填充项目数据
						@SuppressWarnings("unchecked")
						List<QuoteProject> projectList = (List<QuoteProject>) map
								.get("quoteProject");
						int lengthProject = 0;
						if (null != projectList
								&& (lengthProject = projectList.size()) > 0) {
							index = 0;
							Object[][] objProject = new Object[lengthProject][projectHeadSt.length];
							for (QuoteProject quoteProject : projectList) {
								objProject[index][0] = quoteProject.getId();
								objProject[index][1] = quoteProject
										.getProjectName();
								objProject[index][2] = quoteProject
										.getIndustry().getIndustryName();
								objProject[index][3] = quoteProject
										.getAddress().getMergerName();
								objProject[index][4] = quoteProject
										.getOtherAmout();
								objProject[index][5] = quoteProject
										.getProjectAmount();
								index++;

							}
							dtmPJ = new DefaultTableModel(objProject,
									projectHeadSt);
							jt_quoteProject.setModel(dtmPJ);
							jt_quoteProject.getColumnModel().getColumn(0)
									.setMinWidth(0);
							jt_quoteProject.getColumnModel().getColumn(0)
									.setMaxWidth(0);
							jt_quoteProject.getColumnModel().getColumn(1)
									.setPreferredWidth(100);
							jt_quoteProject.getColumnModel().getColumn(2)
									.setPreferredWidth(30);
							jt_quoteProject.getColumnModel().getColumn(3)
									.setPreferredWidth(120);
							jt_quoteProject.setRowSelectionInterval(0, 0);// 选中第一行

							// 填充检验批数据
							// 检验批模块代码
							@SuppressWarnings("unchecked")
							List<InspectionBatch> batchList = (List<InspectionBatch>) map
									.get("inspectionBatch");
							length = 0;
							if (null != batchList
									&& (length = batchList.size()) > 0) {
								index = 0;
								Object[][] objBatch = new Object[length][inspectionHeadName.length];
								for (InspectionBatch inspectionBatch : batchList) {
									objBatch[index][0] = inspectionBatch
											.getId();
									objBatch[index][1] = inspectionBatch
											.getInspectionBatchName();
									objBatch[index][2] = inspectionBatch
											.getInspectionBatchAmount();
									index++;
								}
								DefaultTableModel dtmInspection = new DefaultTableModel(
										objBatch, inspectionHeadName);

								jt_inspectionBatch.setModel(dtmInspection);
								jt_inspectionBatch.getColumnModel()
										.getColumn(0).setMinWidth(0);
								jt_inspectionBatch.getColumnModel()
										.getColumn(0).setMaxWidth(0);
								jt_inspectionBatch
										.setRowSelectionInterval(0, 0);

								// 填充检验内容数据
								@SuppressWarnings("unchecked")
								List<InspectionContent> contentList = (List<InspectionContent>) map
										.get("inspectionContent");
								if (null != contentList
										&& (length = contentList.size()) > 0) {
									Object contentData[][] = new Object[length][contentColumnsName.length];
									int indax = 0;
									// 2,3,4,7,10
									for (InspectionContent inspectionContent : contentList) {
										contentData[indax][0] = inspectionContent
												.getId();
										contentData[indax][1] = inspectionContent
												.getInspectionContentName();
										contentData[indax][2] = inspectionContent
												.getSampleQuantity();
										contentData[indax][3] = inspectionContent
												.getSingleObjectQuantity();
										contentData[indax][4] = inspectionContent
												.getChargeStandard();
										indax++;
									}
									DefaultTableModel model = new DefaultTableModel(
											contentData, contentColumnsName);
									jt_inspectionContent.setModel(model);
									jt_inspectionContent.getColumnModel()
											.getColumn(0).setMinWidth(0);
									jt_inspectionContent.getColumnModel()
											.getColumn(0).setMaxWidth(0);
								}
							}
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			}

		}.execute();

		// JToolBar工具栏及其下按钮
		jtb_tb = new JToolBar();
		jf_jpanel.add(jtb_tb, BorderLayout.NORTH);
		jtb_tb.setFloatable(false);

		lblNewLabel_8 = new JLabel("  ");
		jtb_tb.add(lblNewLabel_8);

		jb_bt_1 = new JButton();
		jb_bt_1.setIcon(new ImageIcon("images/creat.png"));
		jb_bt_1.setToolTipText("新建  (Ctrl+N)");
		jb_bt_1.setFocusPainted(false);// 去除按钮边线
		jtb_tb.add(jb_bt_1);

		jb_bt_save = new JButton();
		jb_bt_save.setIcon(new ImageIcon("images/save.png"));
		jb_bt_save.setToolTipText("保存  (Ctrl+S)");
		jb_bt_save.setFocusPainted(false);// 去除按钮边线
		jtb_tb.add(jb_bt_save);
		jtb_tb.setRollover(true);

		jbt_createInspectionBatch.addActionListener(new HomeFrameAction(null,
				jt_quoteProject, null, null));

		// 添加创建任务事件
		bt_createTask.setActionCommand("createTask");
		bt_createTask.addActionListener(new HomeFrameAction(jt_quoteTask, null,
				null, null));
		// 添加删除任务事件
		bt_deleteTask.setActionCommand("deleteTask");
		bt_deleteTask.addActionListener(new HomeFrameAction(jt_quoteTask, null,
				null, null));
		// 添加查询任务事件
		bt_queryTask.setActionCommand("queryTask");
		bt_queryTask.addActionListener(new HomeFrameAction(jt_quoteTask, null,
				null, null, jtf_queryTaskName));
		// 添加修改任务事件
		bt_updateTask.setActionCommand("updateTask");
		bt_updateTask.addActionListener(new HomeFrameAction(jt_quoteTask, null,
				null, null));

		// 创建项目
		jbt_createProject.addActionListener(new HomeFrameAction(jt_quoteTask,
				null, null, null));

		// 新建检验批按钮
		jbt_createInspectionBatch.addActionListener(new HomeFrameAction(null,
				jt_quoteProject, jt_inspectionBatch, null));

		// 添加创建检验内容事件
		jbt_createContent.setActionCommand("createContent");
		jbt_createContent.addActionListener(new HomeFrameAction(null, null,
				jt_inspectionBatch, jt_inspectionContent));
		// 添加修改检验内容事件
		jbt_updateContent.setActionCommand("updateContent");
		jbt_updateContent.addActionListener(new HomeFrameAction(null, null,
				null, jt_inspectionContent));
		// 添加删除检验内容事件
		jbt_deleteContent.setActionCommand("deleteContent");
		jbt_deleteContent.addActionListener(new HomeFrameAction(null, null,
				null, jt_inspectionContent));

		// 任务JTabel鼠标点击事件
		jt_quoteTask.addMouseListener(new HomeFrameAction(jt_quoteTask,
				jt_quoteProject, jt_inspectionBatch, jt_inspectionContent,
				"task_jtabel"));
		// 项目JTabel鼠标点击事件
		jt_quoteProject.addMouseListener(new HomeFrameAction(null,
				jt_quoteProject, jt_inspectionBatch, jt_inspectionContent,
				"project_jtabel"));

	}

	public Image titlePhoto(String file) {
		Image image = null;
		try {
			image = ImageIO.read(frame.getClass().getResource(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
