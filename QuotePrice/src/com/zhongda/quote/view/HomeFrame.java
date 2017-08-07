package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.utils.SkinUtil;

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
	private JButton jb_bt_2;


	/**
	 * Create the frame.
	 */
	public HomeFrame() {

		init();

	}

	public void init() {
		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 0, 1440, 760);
		frame.setLocationRelativeTo(null);// 居中
		frame.setIconImage(frame.getToolkit().getImage("images/zdLogo1.png"));
		frame.setTitle("中大检测报价软件单机版");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		

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

		// JToolBar工具栏及其下按钮
		jtb_tb = new JToolBar();
		frame.getContentPane().add(jtb_tb, BorderLayout.NORTH);

		jb_bt_1 = new JButton("按钮1");
		jb_bt_1.setFocusPainted(false);// 去除按钮边线
		jtb_tb.add(jb_bt_1);

		jb_bt_2 = new JButton("按钮2");
		jb_bt_2.setFocusPainted(false);// 去除按钮边线
		jtb_tb.add(jb_bt_2);
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

}
