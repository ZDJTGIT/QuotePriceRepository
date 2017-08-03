package com.zhongda.quote.controller;

import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sun.prism.Image;

public class JPanelBackPhoto extends JPanel {

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
