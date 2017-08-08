package com.zhongda.quote.view.uiutils;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

/**
 * 
 * com.zhongda.quote.view.LoginInterface包下 变量jp_password实现 画图
 * @author LiVerson
 *
 * 2017年8月4日
 */

public class JPasswordFieldUser extends JPasswordField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon icon;
	
	public JPasswordFieldUser(){
		
	}

	public JPasswordFieldUser(String file) {
		// 获取当前路径下的图片
		icon = new ImageIcon(file);
		Insets insets = new Insets(0, 120, 0, 0);
		// 设置文本输入距左边50
		this.setMargin(insets);
	}

	@Override
	public void paintComponent(Graphics g) {
		Insets insets = getInsets();
		super.paintComponent(g);
		int iconWidth = icon.getIconWidth();
		int iconHeight = icon.getIconHeight();
		int Height = this.getHeight();
		// 在文本框中画上之前图片
		icon.paintIcon(this, g, (insets.left - iconWidth) / 2,
				(Height - iconHeight) / 2);
	}

}
