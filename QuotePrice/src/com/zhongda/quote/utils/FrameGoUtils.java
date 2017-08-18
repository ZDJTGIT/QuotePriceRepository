package com.zhongda.quote.utils;

import java.awt.EventQueue;

import javax.swing.JTable;

import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.view.CreatInspectionFrame;
import com.zhongda.quote.view.CreateContentFrame;
import com.zhongda.quote.view.CreateProjectFrame;
import com.zhongda.quote.view.CreateTaskFrame;

/**
 *
 * <p>
 * 窗口启动类
 * </p>
 *
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月11日
 */
public class FrameGoUtils {

	/**
	 * 创建任务窗口
	 *
	 * @param jt_quoteTask
	 *            任务面板JTable对象
	 */
	public static void creatTask(final JTable jt_quoteTask,
			final boolean isCreate) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTaskFrame window = new CreateTaskFrame(jt_quoteTask,
							isCreate);
					window.jDialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 创建新增检验内容窗口
	 * @param jTable
	 */
	public static void createContent(Integer inspectionid,final JTable jt_inspectionContent, boolean isCreate){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//传入一个检验批ID作为显示的内容的依据
					CreateContentFrame window = new CreateContentFrame(inspectionid,jt_inspectionContent, isCreate);
					window.jaDialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 创建项目启动窗口
	 */
	public static void creatProject(JTable jt) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProjectFrame createP = new CreateProjectFrame(jt);
					createP.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 创建检验批窗口
	 */
	public static void creatInspection() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatInspectionFrame cif = new CreatInspectionFrame();
					cif.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * 创建检验批窗口
	 */
	public static void creatInspection(QuoteProject quoteProject) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatInspectionFrame cif = new CreatInspectionFrame(
							quoteProject);
					cif.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 创建检验批窗口
	 */
	public static void creatInspection(JTable jt) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatInspectionFrame cif = new CreatInspectionFrame(jt);
					cif.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public static void updateContent(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateContentFrame window = new CreateContentFrame();
					window.jaDialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
