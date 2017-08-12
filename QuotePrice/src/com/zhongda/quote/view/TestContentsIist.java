package com.zhongda.quote.view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.utils.SkinUtil;

import javax.swing.JTable;
import javax.swing.JButton;

public class TestContentsIist {
	
	public JFrame frameContentsIist;
	private JScrollPane jsp;
	private JTable jTable;
	private JButton btnNewButton;
	
	public TestContentsIist(){
		initialize();
	}
	
	public void initialize(){
		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		frameContentsIist = new JFrame();
		frameContentsIist.setTitle("检测内容列表");
		frameContentsIist.setBounds(0, 0, 293, 345);
		ImageIcon jDialogIcon = new ImageIcon("images\\zdLogo1.png");
		frameContentsIist.setIconImage(jDialogIcon.getImage());
		frameContentsIist.setLocationRelativeTo(null);// 设置界面居中
		frameContentsIist.setResizable(false);// 锁定窗口大小
		frameContentsIist.getContentPane().setLayout(null);
		frameContentsIist.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 默认关闭为当前窗口
		//从数据库中取出信息  
	    //rowData用来存放行数据  
	    //columnNames存放列名 
		//通过数据库传输数据
		String[] columnNames = {"内容名称","数量"};
		Object[][] rowData = new Object[12][2];
		rowData[0][0] = "内容名称";
		rowData[0][1] = 9;
		rowData[1][0] = "水泥密度";
		rowData[1][1] = 1;
		rowData[2][0] = "水泥密度";
		rowData[2][1] = 1;
      
		jTable= new JTable(rowData,columnNames);
		jTable.setRowHeight(20);//设置单元表格高度
		jTable.getTableHeader().setPreferredSize(new Dimension(1, 24)); //设置表头高度
		jsp= new JScrollPane(jTable);
		jsp.setBounds(0, 0, 287, 267);
		frameContentsIist.getContentPane().add(jsp);
		
		btnNewButton = new JButton("确定");
		btnNewButton.setBounds(184, 280, 93, 23);
		btnNewButton.setFocusPainted(false);
		frameContentsIist.getContentPane().add(btnNewButton);
	}
}
