package com.zhongda.quote.view.uiutils;

import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * com.zhongda.quote.view.LoginInterface包下 变量jp_left实现 画图
 * @author LiVerson
 *
 * 2017年8月4日
 */

public class JPanelBackPhoto extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	java.awt.Image image;
	String file;

	public JPanelBackPhoto() {

	}

	public JPanelBackPhoto(String filename) {
		file = filename;
	}

	public void paint(Graphics g) {

		try {

			image = ImageIO.read(new File(file));

			g.drawImage(image, 0, 0, 614, 334, null);

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

}
