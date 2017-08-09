package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.utils.SkinUtil;
import com.zhongda.quote.view.uiutils.JMenBarColor;

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
	private JButton bt_jsrw;
	private JButton bt_jsrw2;
	private JMenBarColor jmb_center_up;
	private JPanel jpanel_center_up;
	private JToolBar jtb_center_up;
	private JButton jb_center_up_1;
	private JButton jb_center_up_2;
	private JMenBarColor jmb_center_down;
	private JPanel jpanel_center_down;
	private JToolBar jtb_center_down;
	private JMenBarColor jmb_right;
	private JPanel jpanel_right;
	private JToolBar jtb_right_jcnr;
	private JButton jtb_center_down_1;
	private JButton jtb_center_down_2;
	private JButton jtb_right_1;
	private JButton jtb_right_2;
	private JLabel jlb_center_ip;
	private JLabel jlb_center_down;
	private JLabel jlb_right_jcnr;
	private JButton bt_jsrw3;
	private JButton bt_jsrw4;
	private JButton bt_jsrw5;
	private JLabel jlb_jsrw;
	private JTextField jtf_jsrw;
	private JButton bt_jsrw_find;
	private JPanel jf_jpanel;
	private JButton jb_center_up_3;
	private JButton jb_center_up_4;
	private JButton jtb_center_down_3;
	private JButton jtb_center_down_4;
	private JButton jtb_right_3;
	private JButton jtb_right_4;
	private JLabel jlb_rwxm;
	private JTextField jtf_rwxm;
	private JButton jb_center_up_5;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton jtb_center_down_5;
	private JLabel jlb_jyp;
	private JTextField jtf_jyp;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton jtb_right_5;
	private JLabel jlb_jcnr;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField jtf_jcnr;
	private JButton bt_jsrw_next;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;

	/**
	 * Create the frame.
	 */
	public HomeFrame() {

		init();

	}

	public void init() {

		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(100, 0, scrSize.width, scrSize.height);
		// frame.setLocationRelativeTo(null);// 居中
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setIconImage(frame.getToolkit().getImage("images/zdLogo1.png"));
		frame.setTitle("中大检测报价软件单机版");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);
		frame.setResizable(false);// 不允许改变窗口大小

		jmb_tb = new JMenuBar();
		frame.setJMenuBar(jmb_tb);

		// 工具栏文件及文件下的子组件
		jm_f = new JMenu("文件(F) ");
		jmb_tb.add(jm_f);

		jm_f_1 = new JMenu("罗");
		jm_f.add(jm_f_1);

		jm_f_2 = new JMenuItem("杰");
		jm_f.add(jm_f_2);

		jm_f_3 = new JMenu("是");
		jm_f.add(jm_f_3);

		jm_f_4 = new JMenuItem("坑");
		jm_f.add(jm_f_4);

		jm_f_5 = new JMenuItem("B");
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
		frame.getContentPane().add(jf_jpanel, BorderLayout.WEST);
		jf_jpanel.setLayout(new BorderLayout(0, 0));

		// 最下方提示信息组件
		jtb_south = new JToolBar();
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

		sp_left = new JSplitPane();
		int we = (int) (scrSize.getWidth() * 0.5);
		sp_left.setDividerLocation(we);
		tabbedPane.addTab("任务管理", null, sp_left, null);

		sp_center = new JSplitPane();
		int wi2 = (int) (scrSize.getWidth() * 0.3);
		sp_center.setDividerLocation(wi2);
		sp_left.setRightComponent(sp_center);

		sp_right = new JSplitPane();
		int wi3 = (int) (scrSize.getHeight() * 0.4);
		sp_right.setDividerLocation(wi3);// 设置splitpane左边大小

		sp_right.setOrientation(JSplitPane.VERTICAL_SPLIT);
		sp_center.setLeftComponent(sp_right);

		// splitPane分区的JPane
		jp_up = new JPanel();
		sp_right.setLeftComponent(jp_up);
		jp_up.setLayout(new BorderLayout(0, 0));

		// 项目分段
		jmb_center_up = new JMenBarColor();
		jp_up.add(jmb_center_up, BorderLayout.NORTH);

		jlb_center_ip = new JLabel("任务项目");
		jlb_center_ip.setBorder(new EmptyBorder(3, 10, 3, 0));
		jlb_center_ip.setFont(new Font("黑体", 1, 14));
		jmb_center_up.add(jlb_center_ip);

		jpanel_center_up = new JPanel();
		jp_up.add(jpanel_center_up, BorderLayout.CENTER);
		jpanel_center_up.setLayout(new BorderLayout(0, 0));

		jtb_center_up = new JToolBar();
		jtb_center_up.setBorderPainted(false);// 工具栏不绘制边框
		jtb_center_up.setFloatable(false);// 工具栏不能拖动
		jpanel_center_up.add(jtb_center_up, BorderLayout.NORTH);

		jb_center_up_1 = new JButton();
		jb_center_up_1.setIcon(new ImageIcon("images/add.png"));
		jb_center_up_1.setToolTipText("新增任务");
		jb_center_up_1.setFocusPainted(false);// 去除按钮边线
		jtb_center_up.add(jb_center_up_1);

		jb_center_up_2 = new JButton();
		jb_center_up_2.setIcon(new ImageIcon("images/updatepen.png"));
		jb_center_up_2.setToolTipText("修改任务");
		jb_center_up_2.setFocusPainted(false);// 去除按钮边线
		jtb_center_up.add(jb_center_up_2);

		jb_center_up_3 = new JButton();
		jb_center_up_3.setIcon(new ImageIcon("images/del.png"));
		jb_center_up_3.setToolTipText("删除任务");
		jb_center_up_3.setFocusPainted(false);// 去除按钮边线
		jtb_center_up.add(jb_center_up_3);

		jtb_center_up.addSeparator();// 分隔符
		jlb_rwxm = new JLabel(" 查找 ");
		jtb_center_up.add(jlb_rwxm);

		jtf_rwxm = new JTextField();
		jtb_center_up.add(jtf_rwxm);
		jtf_rwxm.setColumns(10);

		lblNewLabel = new JLabel(" ");
		jtb_center_up.add(lblNewLabel);

		jb_center_up_4 = new JButton();
		jb_center_up_4.setIcon(new ImageIcon("images/find.png"));
		jb_center_up_4.setToolTipText("查找项目");
		jb_center_up_4.setFocusPainted(false);// 去除按钮边线
		jtb_center_up.add(jb_center_up_4);

		jb_center_up_5 = new JButton();
		jb_center_up_5.setIcon(new ImageIcon("images/next.png"));
		jb_center_up_5.setToolTipText("查找下一个");
		jb_center_up_5.setFocusPainted(false);// 去除按钮边线
		jtb_center_up.add(jb_center_up_5);

		lblNewLabel_1 = new JLabel("  ");
		jtb_center_up.add(lblNewLabel_1);

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

		jtb_center_down_1 = new JButton();
		jtb_center_down_1.setIcon(new ImageIcon("images/add.png"));
		jtb_center_down_1.setToolTipText("新增");
		jtb_center_down_1.setFocusPainted(false);// 去除按钮边线
		jtb_center_down.add(jtb_center_down_1);

		jtb_center_down_2 = new JButton();
		jtb_center_down_2.setIcon(new ImageIcon("images/updatepen.png"));
		jtb_center_down_2.setToolTipText("修改");
		jtb_center_down_2.setFocusPainted(false);// 去除按钮边线
		jtb_center_down.add(jtb_center_down_2);

		jtb_center_down_3 = new JButton();
		jtb_center_down_3.setIcon(new ImageIcon("images/del.png"));
		jtb_center_down_3.setToolTipText("删除");
		jtb_center_down_3.setFocusPainted(false);// 去除按钮边线
		jtb_center_down.add(jtb_center_down_3);

		jtb_center_down.addSeparator();// 分隔符
		jlb_jyp = new JLabel(" 查找 ");
		jtb_center_down.add(jlb_jyp);

		jtf_jyp = new JTextField();
		jtb_center_down.add(jtf_jyp);
		jtf_jyp.setColumns(10);

		lblNewLabel_2 = new JLabel(" ");
		jtb_center_down.add(lblNewLabel_2);

		jtb_center_down_4 = new JButton();
		jtb_center_down_4.setIcon(new ImageIcon("images/find.png"));
		jtb_center_down_4.setToolTipText("查找");
		jtb_center_down_4.setFocusPainted(false);// 去除按钮边线
		jtb_center_down.add(jtb_center_down_4);

		jtb_center_down_5 = new JButton();
		jtb_center_down_5.setIcon(new ImageIcon("images/next.png"));
		jtb_center_down_5.setToolTipText("查找下一个");
		jtb_center_down_5.setFocusPainted(false);// 去除按钮边线
		jtb_center_down.add(jtb_center_down_5);

		lblNewLabel_3 = new JLabel("  ");
		jtb_center_down.add(lblNewLabel_3);

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

		jtb_right_1 = new JButton();
		jtb_right_1.setIcon(new ImageIcon("images/add.png"));
		jtb_right_1.setToolTipText("新增");
		jtb_right_1.setFocusPainted(false);// 去除按钮边线
		jtb_right_jcnr.add(jtb_right_1);

		jtb_right_2 = new JButton();
		jtb_right_2.setIcon(new ImageIcon("images/updatepen.png"));
		jtb_right_2.setToolTipText("修改");
		jtb_right_2.setFocusPainted(false);// 去除按钮边线
		jtb_right_jcnr.add(jtb_right_2);

		jtb_right_3 = new JButton();
		jtb_right_3.setIcon(new ImageIcon("images/del.png"));
		jtb_right_3.setToolTipText("删除");
		jtb_right_3.setFocusPainted(false);// 去除按钮边线
		jtb_right_jcnr.add(jtb_right_3);

		jtb_right_jcnr.addSeparator();// 分隔符
		jlb_jcnr = new JLabel(" 查找  ");
		jtb_right_jcnr.add(jlb_jcnr);

		jtf_jcnr = new JTextField();
		jtb_right_jcnr.add(jtf_jcnr);
		jtf_jcnr.setColumns(10);

		lblNewLabel_4 = new JLabel(" ");
		jtb_right_jcnr.add(lblNewLabel_4);

		jtb_right_4 = new JButton();
		jtb_right_4.setIcon(new ImageIcon("images/find.png"));
		jtb_right_4.setToolTipText("查找");
		jtb_right_4.setFocusPainted(false);// 去除按钮边线
		jtb_right_jcnr.add(jtb_right_4);

		jtb_right_5 = new JButton();
		jtb_right_5.setIcon(new ImageIcon("images/next.png"));
		jtb_right_5.setToolTipText("查找下一个");
		jtb_right_5.setFocusPainted(false);// 去除按钮边线
		jtb_right_jcnr.add(jtb_right_5);

		lblNewLabel_5 = new JLabel("  ");
		jtb_right_jcnr.add(lblNewLabel_5);

		jp_left = new JPanel();
		sp_left.setLeftComponent(jp_left);
		jp_left.setLayout(new BorderLayout(0, 0));

		// 建设任务
		jmb_left = new JMenBarColor();
		jmb_left.setBackground(Color.cyan);
		jp_left.add(jmb_left, BorderLayout.NORTH);

		jmb_left_lb = new JLabel("建设任务");
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
		bt_jsrw = new JButton();
		bt_jsrw.setIcon(new ImageIcon("images/creat_1.png"));
		bt_jsrw.setToolTipText("新建建设任务");
		bt_jsrw.setFocusPainted(false);// 去除按钮边线
		jtb_jsrw.add(bt_jsrw);

		bt_jsrw2 = new JButton();
		bt_jsrw2.setIcon(new ImageIcon("images/daoru.png"));
		bt_jsrw2.setToolTipText("导入建设任务");
		bt_jsrw2.setFocusPainted(false);// 去除按钮边线
		jtb_jsrw.add(bt_jsrw2);

		bt_jsrw3 = new JButton();
		bt_jsrw3.setIcon(new ImageIcon("images/daochu.png"));
		bt_jsrw3.setToolTipText("导出建设任务");
		bt_jsrw3.setFocusPainted(false);// 去除按钮边线
		jtb_jsrw.add(bt_jsrw3);

		bt_jsrw4 = new JButton();
		bt_jsrw4.setIcon(new ImageIcon("images/updatepen.png"));
		bt_jsrw4.setToolTipText("修改建设任务");
		bt_jsrw4.setFocusPainted(false);// 去除按钮边线
		// bt_jsrw4.setBorder(new BevelBorder(BevelBorder.RAISED));
		jtb_jsrw.add(bt_jsrw4);

		bt_jsrw5 = new JButton();
		bt_jsrw5.setIcon(new ImageIcon("images/delete.png"));
		bt_jsrw5.setFocusPainted(false);// 去除按钮边线
		bt_jsrw5.setToolTipText("删除建设任务");
		jtb_jsrw.addSeparator();// 分隔符
		jtb_jsrw.add(bt_jsrw5);
		jtb_jsrw.addSeparator();

		jlb_jsrw = new JLabel(" 查找 ");
		jtb_jsrw.add(jlb_jsrw);

		jtf_jsrw = new JTextField();
		jtb_jsrw.add(jtf_jsrw);
		jtf_jsrw.setColumns(10);

		lblNewLabel_7 = new JLabel(" ");
		jtb_jsrw.add(lblNewLabel_7);

		bt_jsrw_find = new JButton();
		bt_jsrw_find.setIcon(new ImageIcon("images/find.png"));
		bt_jsrw_find.setToolTipText("查找任务");
		bt_jsrw_find.setFocusPainted(false);// 去除按钮边线
		jtb_jsrw.add(bt_jsrw_find);

		bt_jsrw_next = new JButton();
		bt_jsrw_next.setIcon(new ImageIcon("images/next.png"));
		bt_jsrw_next.setToolTipText("查找下一个");
		bt_jsrw_next.setFocusPainted(false);// 去除按钮边线
		jtb_jsrw.add(bt_jsrw_next);

		lblNewLabel_6 = new JLabel("  ");
		jtb_jsrw.add(lblNewLabel_6);

		// JToolBar工具栏及其下按钮
		jtb_tb = new JToolBar();
		jf_jpanel.add(jtb_tb, BorderLayout.NORTH);
		jtb_tb.setFloatable(false);

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

		// Object[][] rowData = { { "1001", "李汉", "软件部", new Double(3000) },
		// { "1002", "朱泽", "软件部", new Double(3100) },
		// { "1003", "刘宇", "经理部", new Double(3000) } };
		// // 初始化列名
		// Object[] columnsName = { "编号", "姓名", "部门", "月薪" };
		// DefaultTableModel dtm = new DefaultTableModel(rowData, columnsName);
		// jp_left.setLayout(new BorderLayout(0, 0));
		// jp_up.setLayout(new BorderLayout(0, 0));
		// jp_down.setLayout(new BorderLayout(0, 0));
		// jp_right.setLayout(new BorderLayout(0, 0));

	}

	public Image titlePhoto(String file) {
		Image image = null;
		try {
			image = ImageIO.read(frame.getClass().getResource(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame window = new HomeFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
