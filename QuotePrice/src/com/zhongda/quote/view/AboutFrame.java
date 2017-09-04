package com.zhongda.quote.view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JButton;

import com.zhongda.quote.action.AboutFrameAction;
import com.zhongda.quote.view.uiutils.JPanelBackPhoto;

/**
 * 关于界面
 *<p>
 *
 *<p>
 * @author 研发中心-Mikepolite<1011592269@qq.com>
 * @sine 2017年9月4日
 */
public class AboutFrame {
	
	public JDialog jaDialog;
	private JPanel panel, panel_1;
	private JLabel label, lblQuoteprice, lblquoteprice, lblNewLabel, 
	               lblc, label_1, lblNewLabel_1, label_2, lblNewLabel_2, 
	               lblqqcom, label_4, label_3, lblNewLabel_3;
	private JSeparator separator, separator_1;
	private JButton button;
	private JPanelBackPhoto jp_left;
	
	/**
	 * @wbp.parser.constructor
	 */
	
	public AboutFrame(){
		init();
	}
	
	public void init(){
		jaDialog = new JDialog();
		jaDialog.setBounds(0, 0, 476, 360);
		jaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ImageIcon icon = new ImageIcon("images\\zdLogo1.png");
		jaDialog.setTitle("中大检测");
		jaDialog.setResizable(false);
		jaDialog.setLocationRelativeTo(null);// 设置界面居中
		jaDialog.setIconImage(icon.getImage());
		jaDialog.setModal(true);
		jaDialog.getContentPane().setLayout(null);
		
		jp_left = new JPanelBackPhoto("images/zhongda.png");
		jp_left.setBounds(8, 8,135 ,260);
		jp_left.setFocusable(true);
		jaDialog.getContentPane().add(jp_left);
		
		panel= new JPanel();
		panel.setLocation(144, 0);
		jaDialog.getContentPane().add(panel);
		panel.setSize(326, 269);;
		panel.setLayout(null);
		
		label = new JLabel("中大检测系统监测平台系列软件");
		label.setFont(new Font("黑体", Font.BOLD, 12));
		label.setBounds(10, 12, 223, 15);
		panel.add(label);
		
		lblQuoteprice = new JLabel("QuotePrice");
		lblQuoteprice.setBounds(10, 34, 83, 15);
		panel.add(lblQuoteprice);
		
		lblquoteprice = new JLabel("中大QuotePrice单击版");
		lblquoteprice.setBounds(10, 53, 139, 15);
		panel.add(lblquoteprice);
		
		lblNewLabel = new JLabel("版本号：17.8.001");
		lblNewLabel.setBounds(10, 87, 156, 15);
		panel.add(lblNewLabel);
		
		lblc = new JLabel("版权所有：(C) 2017-2021");
		lblc.setBounds(10, 112, 169, 15);
		panel.add(lblc);
		
		label_1 = new JLabel("湖南中大检测有限公司");
		label_1.setBounds(68, 135, 139, 15);
		panel.add(label_1);
		
		separator = new JSeparator();
		separator.setBounds(10, 173, 312, 2);
		panel.add(separator);
		
		lblNewLabel_1 = new JLabel("客服中心");
		lblNewLabel_1.setFont(new Font("黑体", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 185, 83, 15);
		panel.add(lblNewLabel_1);
		
		label_2 = new JLabel("电话：18670053775");
		label_2.setBounds(10, 210, 139, 15);
		panel.add(label_2);
		
		lblNewLabel_2 = new JLabel("18616540312");
		lblNewLabel_2.setBounds(47, 226, 102, 15);
		panel.add(lblNewLabel_2);
		
		lblqqcom = new JLabel("邮箱：1011592269@qq.com");
		lblqqcom.setBounds(10, 246, 207, 15);
		panel.add(lblqqcom);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 268, 470, 64);
		panel_1.setLayout(null);
		jaDialog.getContentPane().add(panel_1);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 10, 470, 2);
		panel_1.add(separator_1);
		
		label_3 = new JLabel("警告：");
		label_3.setFont(new Font("黑体", Font.BOLD, 12));
		label_3.setBounds(10, 22, 54, 15);
		panel_1.add(label_3);
		
		lblNewLabel_3 = new JLabel("本软件受著作权法保护，任何侵犯版权的行为，");
		lblNewLabel_3.setBounds(59, 22, 252, 15);
		panel_1.add(lblNewLabel_3);
		
		button = new JButton("确定");
		button.setBounds(367, 28, 77, 23);
		panel_1.add(button);
		
		label_4 = new JLabel("都将承担法律责任。");
		label_4.setBounds(59, 38, 184, 15);
		panel_1.add(label_4);
		
		button.addActionListener(new AboutFrameAction(jaDialog));
	
	}
	
}
