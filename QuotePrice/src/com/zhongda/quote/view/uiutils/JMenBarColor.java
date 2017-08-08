package com.zhongda.quote.view.uiutils;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JMenuBar;

public class JMenBarColor extends JMenuBar{

	private static final long serialVersionUID = -2644424271663261406L;
	   
    public JMenBarColor() {
        super();
    }
   
    @Override
    protected void paintComponent(Graphics g1) {// 重写绘制组件外观
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);// 执行超类方法
        int width = getWidth();// 获取组件大小
        int height = getHeight();
        // 创建填充模式对象
        GradientPaint paint = new GradientPaint(0, 0, new Color(251, 184, 88), width, height,
                new Color(255, 255, 255));
        g.setPaint(paint);// 设置绘图对象的填充模式
        g.fillRect(0, 0, width, height);// 绘制矩形填充控件界面
    }
}
