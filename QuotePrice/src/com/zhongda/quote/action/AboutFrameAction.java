package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class AboutFrameAction implements ActionListener{
	
	private JDialog jDialog;

	public  AboutFrameAction() {
		
	}
	
    public  AboutFrameAction(JDialog jDialog) {
		this.jDialog = jDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		jDialog.dispose();
	}

	
}
