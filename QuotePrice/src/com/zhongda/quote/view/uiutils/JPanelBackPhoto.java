package com.zhongda.quote.view.uiutils;

import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * com.zhongda.quote.view.LoginInterface包下 变量jp_left实现 画图
 * 
 * @author LiVerson
 *
 *         2017年8月4日
 */

public class JPanelBackPhoto extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	java.awt.Image image;
	String file;
	private int width = 614;
	private int height = 334;

	public JPanelBackPhoto() {

	}

	public JPanelBackPhoto(String filename) {
		file = filename;
	}

	public JPanelBackPhoto(String filename, int width, int height) {
		file = filename;
		this.width = width;
		this.height = height;
	}

	public void paint(Graphics g) {

		try {

			image = ImageIO.read(new File(file));

			g.drawImage(image, 0, 0, width, height, null);

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

}
