package com.zhongda.quote.view.uiutils;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * <p>
 * 配置类，配置Jpanel面板渐变色
 * </p>
 * 
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月10日
 */
public class JpaneColorAndPhoto extends JPanel {

	private static final long serialVersionUID = -2644424271663261406L;
	private String file;
	private int x;
	private int y;
	private int wide;
	private int heightP;
	java.awt.Image image;

	public JpaneColorAndPhoto() {
		super();
	}

	/**
	 * 
	 * @param file
	 *            图片文件名称
	 * @param x
	 *            图片距组件的宽
	 * @param y
	 *            图片距组件的高
	 * @param wide
	 *            图片的宽
	 * @param height
	 *            图片的高
	 */
	public JpaneColorAndPhoto(String file, int x, int y, int wide, int height) {
		super();
		this.file = file;
		this.x = x;
		this.y = y;
		this.wide = wide;
		this.heightP = height;

	}

	@Override
	protected void paintComponent(Graphics g1) {

		// 重写绘制组件外观
		Graphics2D g = (Graphics2D) g1;
		super.paintComponent(g);// 执行超类方法
		int width = getWidth();// 获取组件大小
		int height = getHeight();
		// 创建填充模式对象
		GradientPaint paint = new GradientPaint(0, 0, new Color(255, 255, 255),
				width, height, new Color(251, 184, 88));
		g.setPaint(paint);// 设置绘图对象的填充模式
		g.fillRect(0, 0, width, height);// 绘制矩形填充控件界面

		if (file != null) {
			try {
				image = ImageIO.read(new File(file));
				g1.drawImage(image, x, y, wide, heightP, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
