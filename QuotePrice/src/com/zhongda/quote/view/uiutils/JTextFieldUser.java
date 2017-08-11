package com.zhongda.quote.view.uiutils;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 * com.zhongda.quote.view.LoginInterface包下 变量jt_user实现 画图
 * @author LiVerson
 *
 * 2017年8月4日
 */

public class JTextFieldUser extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon icon;
	
	public JTextFieldUser(){
		
	}
	  
    public JTextFieldUser(String file) {
        //获取当前路径下的图片  
        icon = new ImageIcon(file);
        Insets insets = new Insets(0, 50, 0, 0);  
        //设置文本输入距左边20  
        this.setMargin(insets);  
    }  
	
    //重写画图方法
	@Override  
    public void paintComponent(Graphics g) {  
		
		 Insets insets = getInsets();  
	        super.paintComponent(g);  
	        int iconWidth = icon.getIconWidth();  
	        int iconHeight = icon.getIconHeight();  
	        int Height = this.getHeight();  
	        //在文本框中画上之前图片  
	        icon.paintIcon(this, g, (insets.left - iconWidth)/2, (Height - iconHeight) / 2);  
		
	}

}
