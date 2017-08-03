package com.zhongda.quote.controller;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 * 
 * @author LiVerson
 *
 * 2017年8月3日
 */

public class JTextFieldUser extends JTextField{
	
	private ImageIcon icon;
	
	
	  
    public JTextFieldUser(String file) {
        //获取当前路径下的图片  
        icon = new ImageIcon(file);
        Insets insets = new Insets(0, 50, 0, 0);  
        //设置文本输入距左边20  
        this.setMargin(insets);  
    }  
	
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
