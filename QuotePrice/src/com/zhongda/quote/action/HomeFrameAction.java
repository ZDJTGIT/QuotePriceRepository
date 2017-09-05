package com.zhongda.quote.action;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.model.SysInspectionContent;
import com.zhongda.quote.service.impl.InspectionBatchServiceImpl;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.service.impl.QuoteProjectServiceImpl;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;
import com.zhongda.quote.service.impl.SysInspectionContenServiceImpl;
import com.zhongda.quote.utils.ConstantUtils;
import com.zhongda.quote.utils.FrameGoUtils;
import com.zhongda.quote.utils.QuoteBasisPhotoUtils;
import com.zhongda.quote.utils.RenderDataUtils;

/**
 *
 * <p>
 * HomeFrame事件类
 * </p>
 *
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月10日
 */

public class HomeFrameAction implements ActionListener, MouseMotionListener,
		MouseListener {

	// 主界面报价任务JTable
	private JTable jt_quoteTask;
	// 主界面报价项目JTable
	private JTable jt_quoteProject;
	// 主界面检验批JTabel
	private JTable jt_inspectionBatch;
	// 主界面检验内容JTabel
	private JTable jt_inspectionContent;
	// 区别鼠标单击/悬浮事件名称
	private String clickmove_Name;
	// 主界面查询对象的名称
	private JTextField jtf_queryName;
	// 主界面对象
	private JFrame frame;

	public HomeFrameAction() {
	}

	public HomeFrameAction(JFrame frame) {
		this.frame = frame;
	}

	public HomeFrameAction(JTable jt_quoteTask, JTable jt_quoteProject,
			JTable jt_inspectionBatch, JTable jt_inspectionContent) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
	}

	public HomeFrameAction(JTable jt_quoteTask, JTable jt_quoteProject,
			JTable jt_inspectionBatch, JTable jt_inspectionContent, String name) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
		this.clickmove_Name = name;
	}

	public HomeFrameAction(JTable jt_quoteTask, JTable jt_quoteProject,
			JTable jt_inspectionBatch, JTable jt_inspectionContent,
			JTextField jtf_queryName) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
		this.jtf_queryName = jtf_queryName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// 如果command为创建任务，则打开创建任务窗口
		if ("createTask".equals(command)) {
			FrameGoUtils.creatTask(jt_quoteTask, jt_quoteProject,
					jt_inspectionBatch, jt_inspectionContent, true);
		} else if ("deleteTask".equals(command)) {
			deleteQuoteTask(jt_quoteTask, jt_quoteProject, jt_inspectionBatch,
					jt_inspectionContent);
		} else if ("queryTask".equals(command)) {
			queryQuotePrice(jt_quoteTask, jtf_queryName);
		} else if ("updateTask".equals(command)) {
			// 获取Table中被选中的行序号
			int row = jt_quoteTask.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null,
						"没有选中需要修改的报价任务,请选中后再进行修改操作！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				FrameGoUtils.creatTask(jt_quoteTask, null, null, null, false);
			}
		} else if ("createProject".equals(command)) {
			createFrame(jt_quoteTask, jt_quoteProject, jt_inspectionBatch,
					jt_inspectionContent, ConstantUtils.PROJECT);
		} else if ("deleteProject".equals(command)) {
			deleteQuoteProject(jt_quoteTask, jt_quoteProject,
					jt_inspectionBatch, jt_inspectionContent);
		} else if ("createInspectionBatch".equals(command)) {
			createFrame(jt_quoteTask, jt_quoteProject, jt_inspectionBatch,
					jt_inspectionContent, ConstantUtils.BATCH);
		} else if ("deleteInspectionBatch".equals(command)) {
			deleteInspectionBatch(jt_quoteTask, jt_quoteProject,
					jt_inspectionBatch, jt_inspectionContent);
		} else if ("createContent".equals(command)) {
			createFrame(jt_quoteTask, jt_quoteProject, jt_inspectionBatch,
					jt_inspectionContent, ConstantUtils.CONTENT);
		} else if ("deleteContent".equals(command)) {
			deleteInspectionContent(jt_quoteTask, jt_quoteProject,
					jt_inspectionBatch, jt_inspectionContent);
		} else if ("updateContent".equals(command)) {
			int row = jt_inspectionContent.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择需要修改的检验内容！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				FrameGoUtils.modifyContent(jt_inspectionContent);
			}
		} else if ("Forbidden".equals(command)) {
			// 获取当前选中的检验批ID InspectionContentID
			final int row = jt_inspectionBatch.getSelectedRow();
			// 判定用户输入是否为空
			String jjc = jtf_queryName.getText();
			if(null==jjc|"".equals(jjc)){
				JOptionPane.showMessageDialog(null, "请输入关键字查询！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			}else{
			//根据检验批ID和输入关键字查出相关内容
			new SwingWorker<List<InspectionContent>, InspectionContent>() {
				protected List<InspectionContent> doInBackground()
						throws Exception {
					Integer inspectionBatchID = (Integer) jt_inspectionBatch
							.getValueAt(row, 0);
					return new InspectionContentServiceImpl()
							.selectByBatchidAndContentName(inspectionBatchID,
									jtf_queryName.getText());
				}

				protected void done() {
					try {
						List<InspectionContent> inspectionContent = get();
						if (null == inspectionContent
								|| inspectionContent.size() <= 0) {
							JOptionPane.showMessageDialog(null, "查询结果不存在！",
									"提示信息", JOptionPane.WARNING_MESSAGE);
						} else{
							RenderDataUtils.renderPartContentData(
									jt_inspectionContent, inspectionContent);
						}
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}
			}.execute();
			}
		} else if ("selectContent".equals(command)) {
			// 获取当前选中的检验内容ID InspectionContentID
			int row = jt_inspectionContent.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择一个检验内容查看！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				Integer InspectionContentID = (Integer) jt_inspectionContent
						.getValueAt(row, 0);
				FrameGoUtils.selectContent(InspectionContentID);
			}
		} else if ("selectHome".equals(command)) {
			RenderDataUtils.openHomeWeb();
		} else if ("About".equals(command)) {
			FrameGoUtils.about();
		} else if ("jmi_quoteBasis".equals(command)) {
			showQuoteBasisPhoto();
		} else if ("bt_exportTask".equals(command)) {
			exportTask();

		}
	}

	/**
	 * 创建项目，检验批或检验内容的时候判断是否有上级存在，有则打开创建窗口，没有则给出提示信息 <<<<<<< Updated upstream
	 *
	 * @param jt_quoteTask
	 * @param jt_quoteProject
	 * @param jt_inspectionBatch
	 * @param jt_inspectionContent
	 * @param frameName
	 *            项目和检验批以及内容的标志 =======
	 *
	 * @param jt_parentTable
	 *            父级Table
	 * @param jt_childTable
	 *            子级Table >>>>>>> Stashed changes
	 */
	private void createFrame(JTable jt_quoteTask, JTable jt_quoteProject,
			JTable jt_inspectionBatch, JTable jt_inspectionContent,
			String frameName) {
		if (ConstantUtils.PROJECT.equals(frameName)) { // 创建项目
			int row = jt_quoteTask.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选中" + ConstantUtils.TASK,
						"提示信息", JOptionPane.WARNING_MESSAGE);
			} else {
				FrameGoUtils.createProject(jt_quoteTask, jt_quoteProject,
						jt_inspectionBatch, jt_inspectionContent);
			}
		} else if (ConstantUtils.BATCH.equals(frameName)) { // 创建检验批
			int row = jt_quoteProject.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选中"
						+ ConstantUtils.PROJECT, "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				FrameGoUtils.createBatch(jt_quoteTask, jt_quoteProject,
						jt_inspectionBatch, jt_inspectionContent);
			}
		} else { // 创建检验内容
			int row = jt_inspectionBatch.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null,
						"请选中" + ConstantUtils.BATCH, "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				FrameGoUtils.createContent(jt_quoteTask, jt_quoteProject,
						jt_inspectionBatch, jt_inspectionContent);
			}
		}
	}

	/**
	 * 查询报价任务
	 *
	 * @param jt_quoteTask
	 * @param jtf_queryName
	 *            存放查询条件的任务名称
	 */
	private void queryQuotePrice(final JTable jt_quoteTask,
			JTextField jtf_queryName) {
		final String taskName = jtf_queryName.getText();
		if (null != taskName && !"".equals(taskName)) {
			new SwingWorker<List<QuoteTask>, Void>() {
				@Override
				protected List<QuoteTask> doInBackground() throws Exception {
					return new QuoteTaskServiceImpl()
							.queryQuoteTaskByName(taskName);
				}

				protected void done() {
					List<QuoteTask> taskList = null;
					try {
						taskList = get();
						RenderDataUtils.renderTaskData(jt_quoteTask, taskList);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				};
			}.execute();
		} else {
			JOptionPane.showMessageDialog(null, "请填写任务名称！", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * 导出任务为Excel
	 */
	private void exportTask() {
		// 获取选中行的任务
		int[] selectedNumber = jt_quoteTask.getSelectedRows();
		for (int i : selectedNumber) {
			System.out.println(i);
		}
		String[] taskidAry = new String[selectedNumber.length];
		String[] taskName = new String[selectedNumber.length];
		String[] taskNumberName = { "一", "二", "三", "四", "五", "六", "七", "八",
				"九", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十" };
		for (int i = 0; i < selectedNumber.length; i++) {
			taskidAry[i] = (String) jt_quoteTask.getValueAt(selectedNumber[i],
					1);
			taskName[i] = (String) jt_quoteTask
					.getValueAt(selectedNumber[i], 2);
		}
		RenderDataUtils.exportTask(taskidAry, taskName, taskNumberName, frame);

	}

	/**
	 * 工具栏视图选项展示报价依据图片
	 */
	private void showQuoteBasisPhoto() {
		FileDialog fileDialog = new FileDialog(frame, "打开", FileDialog.LOAD);
		fileDialog.setVisible(true);
		String fileName = fileDialog.getDirectory() + fileDialog.getFile();
		if (null != fileDialog.getDirectory() && !"".equals(fileName)) {
			new QuoteBasisPhotoUtils(fileName);
		}

	}

	/**
	 * 删除检验内容
	 *
	 * @param jt_inspectionContent
	 */
	private void deleteInspectionContent(final JTable jt_quoteTask,
			final JTable jt_quoteProject, final JTable jt_inspectionBatch,
			final JTable jt_inspectionContent) {
		final int row = jt_inspectionContent.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "没有选中需要删除的检验内容,请选中后再进行删除操作！",
					"提示信息", JOptionPane.WARNING_MESSAGE);
		} else {
			int flag = JOptionPane.showConfirmDialog(null, "确定删除该条检验内容？",
					"删除检验内容", JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				// 获取检验内容id
				final Integer contentId = (Integer) jt_inspectionContent
						.getValueAt(row, 0);
				// 获取检验内容金额
				final double contentAmount = (double) jt_inspectionContent
						.getValueAt(row, 5);
				System.out.println("contentAmount:" + contentAmount);
				// 重新计算任务金额与项目金额及检验批金额
				final int taskRow = jt_quoteTask.getSelectedRow();
				final double taskAmount = (double) jt_quoteTask.getValueAt(
						taskRow, 7) - contentAmount;
				final int projectRow = jt_quoteProject.getSelectedRow();
				final double projectAmount = (double) jt_quoteProject
						.getValueAt(projectRow, 5) - contentAmount;
				final int batchtRow = jt_inspectionBatch.getSelectedRow();
				final double batchAmount = (double) jt_inspectionBatch
						.getValueAt(batchtRow, 2) - contentAmount;
				// 通过线程从数据库中获取该检验内容的ID
				new SwingWorker<Integer, Void>() {

					@Override
					protected Integer doInBackground() throws Exception {
						return new InspectionContentServiceImpl()
								.deleteInspectionByID(contentId, taskAmount,
										projectAmount, batchAmount);
					}

					@Override
					protected void done() {
						int flag = 0;
						try {
							flag = get();
							if (flag > 0) {
								JOptionPane.showMessageDialog(null,
										"检验内容删除成功！", "提示信息",
										JOptionPane.PLAIN_MESSAGE);

								DefaultTableModel model = (DefaultTableModel) jt_inspectionContent
										.getModel();
								model.removeRow(row);
								if (jt_inspectionContent.getRowCount() > 0) {
									jt_inspectionContent
											.setRowSelectionInterval(0, 0);
								}
								jt_quoteTask.setValueAt(taskAmount, taskRow, 7);
								jt_quoteProject.setValueAt(projectAmount,
										projectRow, 5);
								jt_inspectionBatch.setValueAt(batchAmount,
										batchtRow, 2);
							} else {
								JOptionPane.showMessageDialog(null,
										"检验内容删除失败！", "提示信息",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
					}
				}.execute();
			}
		}
	}

	/**
	 * 删除检验批
	 *
	 * @param jt_quoteProjec
	 * @param jt_inspectionBatch
	 * @param jt_inspectionContent
	 */
	private void deleteInspectionBatch(final JTable jt_quoteTask,
			final JTable jt_quoteProject, final JTable jt_inspectionBatch,
			final JTable jt_inspectionContent) {

		// 获取Table中被选中的行序号
		final int row = jt_inspectionBatch.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "没有选中需要删除的检验批,请选中后再进行删除操作！",
					"提示信息", JOptionPane.WARNING_MESSAGE);
		} else {
			int flag = JOptionPane.showConfirmDialog(null,
					"点击确认按钮，将会删除所选中的检验批，包括检验批的所有检验内容，是否确认删除？", "删除报价项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				// 获取检验批id
				Object value = jt_inspectionBatch.getValueAt(row, 0);
				final Integer id = Integer.valueOf(String.valueOf(value));
				// 获取检验批金额
				final double batchAmount = (double) jt_inspectionBatch
						.getValueAt(row, 2);
				// 重新计算任务金额与项目金额
				final int taskRow = jt_quoteTask.getSelectedRow();
				final double taskAmount = (double) jt_quoteTask.getValueAt(
						taskRow, 7) - batchAmount;
				final int projectRow = jt_quoteProject.getSelectedRow();
				final double projectAmount = (double) jt_quoteProject
						.getValueAt(projectRow, 5) - batchAmount;
				// 启动任务线程删除选中报价项目
				new SwingWorker<Boolean, Void>() {
					protected Boolean doInBackground() throws Exception {
						return new InspectionBatchServiceImpl()
								.deleteInspectionBatch(id, taskAmount,
										projectAmount);

					}

					protected void done() {
						try {
							boolean flag = get();
							if (flag) {
								JOptionPane.showMessageDialog(null, "检验批删除成功！",
										"提示信息", JOptionPane.PLAIN_MESSAGE);

								DefaultTableModel model = (DefaultTableModel) jt_inspectionBatch
										.getModel();
								model.removeRow(row);
								if (jt_inspectionBatch.getRowCount() > 0) {
									jt_inspectionBatch.setRowSelectionInterval(
											0, 0);
								}
								// 重新渲染所有数据
								inspectionBatchToContent(jt_inspectionBatch,
										jt_inspectionContent);
								jt_quoteTask.setValueAt(taskAmount, taskRow, 7);
								jt_quoteProject.setValueAt(projectAmount,
										projectRow, 5);
							} else {
								JOptionPane.showMessageDialog(null, "检验批删除失败！",
										"提示信息", JOptionPane.ERROR_MESSAGE);
							}
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
					};
				}.execute();
			}
		}
	}

	/**
	 * 删除报价项目
	 *
	 * @param jt_quoteProject
	 * @param jt_inspectionBatch
	 * @param jt_inspectionContent
	 */
	private void deleteQuoteProject(final JTable jt_quoteTask,
			final JTable jt_quoteProject,

			final JTable jt_inspectionBatch, final JTable jt_inspectionContent) {
		// 获取Table中被选中的行序号
		final int row = jt_quoteProject.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "没有选中需要删除的报价项目,请选中后再进行删除操作！",
					"提示信息", JOptionPane.WARNING_MESSAGE);
		} else {
			int flag = JOptionPane.showConfirmDialog(null,
					"点击确认按钮，将会删除所选中的报价项目，包括报价任务下的所有检验批以及检验内容，是否确认删除？",
					"删除报价项目", JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				// 获取项目id
				Object value = jt_quoteProject.getValueAt(row, 0);
				final Integer id = Integer.valueOf(String.valueOf(value));
				// 获取项目金额与其他费用之和
				double totalProjectAmount = (double) jt_quoteProject
						.getValueAt(row, 4)
						+ (double) jt_quoteProject.getValueAt(row, 5);
				// 重新计算任务金额
				final int taskRow = jt_quoteTask.getSelectedRow();
				final double taskAmount = (double) jt_quoteTask.getValueAt(
						taskRow, 7) - totalProjectAmount;
				// 启动任务线程删除选中报价项目
				new SwingWorker<Boolean, Void>() {
					protected Boolean doInBackground() throws Exception {
						return new QuoteProjectServiceImpl()
								.deleteQuoteProject(id, taskAmount);
					}

					protected void done() {
						try {
							boolean flag = get();
							if (flag) {
								JOptionPane.showMessageDialog(null,
										"报价项目删除成功！", "提示信息",
										JOptionPane.PLAIN_MESSAGE);

								DefaultTableModel model = (DefaultTableModel) jt_quoteProject
										.getModel();
								model.removeRow(row);
								if (jt_quoteProject.getRowCount() > 0) {
									jt_quoteProject.setRowSelectionInterval(0,
											0);
								}
								// 重新渲染所有数据
								projectToInspectionBatch(jt_quoteProject,
										jt_inspectionBatch,
										jt_inspectionContent);
								jt_quoteTask.setValueAt(taskAmount, taskRow, 7);
							} else {
								JOptionPane.showMessageDialog(null,
										"报价项目删除失败！", "提示信息",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
					};
				}.execute();
			}
		}
	}

	/**
	 * 删除报价任务
	 *
	 * @param jt_quoteTask
	 * @param jt_quoteProject
	 * @param jt_inspectionBatch
	 * @param jt_inspectionContent
	 */
	private void deleteQuoteTask(final JTable jt_quoteTask,
			final JTable jt_quoteProject, final JTable jt_inspectionBatch,
			final JTable jt_inspectionContent) {
		// 获取Table中被选中的行序号
		final int row = jt_quoteTask.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "没有选中需要删除的报价任务,请选中后再进行删除操作！",
					"提示信息", JOptionPane.WARNING_MESSAGE);
		} else {
			int flag = JOptionPane.showConfirmDialog(null,
					"点击确认按钮，将会删除所选中的报价任务，包括报价任务下的所有项目以及检验批，是否确认删除？", "删除报价任务",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				Object value = jt_quoteTask.getValueAt(row, 0);
				final Integer id = Integer.valueOf(String.valueOf(value));
				// 启动任务线程删除选中报价任务
				new SwingWorker<Boolean, Void>() {
					protected Boolean doInBackground() throws Exception {
						return new QuoteTaskServiceImpl().deleteQuoteTask(id);
					}

					protected void done() {
						try {
							boolean flag = get();
							if (flag) {
								JOptionPane.showMessageDialog(null,
										"报价任务删除成功！", "提示信息",
										JOptionPane.PLAIN_MESSAGE);

								DefaultTableModel model = (DefaultTableModel) jt_quoteTask
										.getModel();
								model.removeRow(row);
								if (jt_quoteTask.getRowCount() > 0) {
									jt_quoteTask.setRowSelectionInterval(0, 0);
								}
								// 重新渲染所有数据
								taskToProject(jt_quoteTask, jt_quoteProject,
										jt_inspectionBatch,
										jt_inspectionContent);
							} else {
								JOptionPane.showMessageDialog(null,
										"报价任务删除失败！", "提示信息",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
					};
				}.execute();
			}
		}
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		int row;
		int col;
		if ("task".equals(clickmove_Name)) {
			row = jt_quoteTask.rowAtPoint(e.getPoint());
			col = jt_quoteTask.columnAtPoint(e.getPoint());
			if (row > -1 && col > -1) {
				Object value = jt_quoteTask.getValueAt(row, col);
				if (null != value && !"".equals(value))
					jt_quoteTask.setToolTipText(value.toString());// 悬浮显示单元格内容
				else
					jt_quoteTask.setToolTipText(null);// 关闭提示
			}
		} else if ("project".equals(clickmove_Name)) {
			row = jt_quoteProject.rowAtPoint(e.getPoint());
			col = jt_quoteProject.columnAtPoint(e.getPoint());
			if (row > -1 && col > -1) {
				Object value = jt_quoteProject.getValueAt(row, col);
				if (null != value && !"".equals(value))
					jt_quoteProject.setToolTipText(value.toString());// 悬浮显示单元格内容
				else
					jt_quoteProject.setToolTipText(null);// 关闭提示
			}
		} else if ("batch".equals(clickmove_Name)) {
			row = jt_inspectionBatch.rowAtPoint(e.getPoint());
			col = jt_inspectionBatch.columnAtPoint(e.getPoint());
			if (row > -1 && col > -1) {
				Object value = jt_inspectionBatch.getValueAt(row, col);
				if (null != value && !"".equals(value))
					jt_inspectionBatch.setToolTipText(value.toString());// 悬浮显示单元格内容
				else
					jt_inspectionBatch.setToolTipText(null);// 关闭提示
			}
		} else if ("content".equals(clickmove_Name)) {
			row = jt_inspectionContent.rowAtPoint(e.getPoint());
			col = jt_inspectionContent.columnAtPoint(e.getPoint());
			if (row > -1 && col > -1) {
				Object value = jt_inspectionContent.getValueAt(row, col);
				if (null != value && !"".equals(value))
					jt_inspectionContent.setToolTipText(value.toString());// 悬浮显示单元格内容
				else
					jt_inspectionContent.setToolTipText(null);// 关闭提示
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ("task_jtabel".equals(clickmove_Name)) {
			taskToProject(jt_quoteTask, jt_quoteProject, jt_inspectionBatch,
					jt_inspectionContent);
		} else if ("project_jtabel".equals(clickmove_Name)) {
			projectToInspectionBatch(jt_quoteProject, jt_inspectionBatch,
					jt_inspectionContent);
		} else if ("batch_jtabel".equals(clickmove_Name)) {
			inspectionBatchToContent(jt_inspectionBatch, jt_inspectionContent);
		}
	}

	/**
	 * Task面板Jtable点击事件逻辑处理
	 *
	 * @param jt_quoteTask
	 * @param jt_quoteProjec
	 * @param jt_inspectionBatch
	 * @param jt_inspectionContent
	 */
	public void taskToProject(final JTable jt_quoteTask,
			final JTable jt_quoteProject, final JTable jt_inspectionBatch,
			final JTable jt_inspectionContent) {
		if (jt_quoteTask.getRowCount() <= 0) {
			DefaultTableModel projectModel = (DefaultTableModel) jt_quoteProject
					.getModel();
			projectModel.getDataVector().clear();
			jt_quoteProject.updateUI();
			DefaultTableModel batchModel = (DefaultTableModel) jt_inspectionBatch
					.getModel();
			batchModel.getDataVector().clear();
			jt_inspectionBatch.updateUI();
			DefaultTableModel contentModel = (DefaultTableModel) jt_inspectionContent
					.getModel();
			contentModel.getDataVector().clear();
			jt_inspectionContent.updateUI();
		} else {
			new SwingWorker<Map<String, Object>, Void>() {
				@Override
				protected Map<String, Object> doInBackground() throws Exception {
					// 获取选择任务下所有的数据数据
					Map<String, Object> quoteMap = new HashMap<String, Object>();
					// 从数据库获取项目数据
					List<QuoteProject> projectList = new QuoteProjectServiceImpl()
							.queryAllQuoteProjectsByTaskNmber((int) jt_quoteTask
									.getValueAt(jt_quoteTask.getSelectedRow(),
											0));
					quoteMap.put("quote_project", projectList);
					// 从数据库获取检验批数据
					List<InspectionBatch> inspectionBatchList = null;
					if (null != projectList && projectList.size() > 0) {
						QuoteProject quoteProject = projectList.get(0);
						inspectionBatchList = new InspectionBatchServiceImpl()
								.queryAllInspectionBatchByProjectID(quoteProject
										.getId());
						quoteMap.put("quote_batch", inspectionBatchList);

						// 从数据库获取检验内容数据
						List<InspectionContent> contentList = null;
						if (null != inspectionBatchList
								&& inspectionBatchList.size() > 0) {
							InspectionBatch inspectionBatch = inspectionBatchList
									.get(0);
							contentList = new InspectionContentServiceImpl()
									.queryAllInspectionContentByBatchId(inspectionBatch
											.getId());
							quoteMap.put("quote_content", contentList);
						}
					}
					return quoteMap;
				}

				protected void done() {
					Map<String, Object> quoteMap = null;
					try {
						quoteMap = get();
						// 切换项目面板数据
						@SuppressWarnings("unchecked")
						List<QuoteProject> projectList = (List<QuoteProject>) quoteMap
								.get("quote_project");
						RenderDataUtils.renderProjectData(jt_quoteProject,
								projectList);
						// 切换检验批数据
						@SuppressWarnings("unchecked")
						List<InspectionBatch> inspectionList = (List<InspectionBatch>) quoteMap
								.get("quote_batch");
						RenderDataUtils.renderBatchData(jt_inspectionBatch,
								inspectionList);
						// 切换检验内容数据
						@SuppressWarnings("unchecked")
						List<InspectionContent> contentList = (List<InspectionContent>) quoteMap
								.get("quote_content");
						RenderDataUtils.renderPartContentData(
								jt_inspectionContent, contentList);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
			}.execute();
		}
	}

	/**
	 * 项目面板JTable点击事件逻辑处理
	 *
	 * @param jt_quoteProjec
	 * @param jt_inspectionBatch
	 * @param jt_inspectionContent
	 */
	public void projectToInspectionBatch(final JTable jt_quoteProject,
			final JTable jt_inspectionBatch, final JTable jt_inspectionContent) {
		if (jt_quoteProject.getRowCount() <= 0) {
			DefaultTableModel batchModel = (DefaultTableModel) jt_inspectionBatch
					.getModel();
			jt_inspectionBatch.updateUI();
			batchModel.getDataVector().clear();
			DefaultTableModel contentModel = (DefaultTableModel) jt_inspectionContent
					.getModel();
			contentModel.getDataVector().clear();
			jt_inspectionContent.updateUI();
		} else {
			new SwingWorker<Map<String, Object>, Void>() {
				@Override
				protected Map<String, Object> doInBackground() throws Exception {
					// 获取选择项目下所有的数据数据
					Map<String, Object> quoteMap = new HashMap<String, Object>();
					// 从数据库获取检验批数据
					List<InspectionBatch> batchList = new InspectionBatchServiceImpl()
							.queryAllInspectionBatchByProjectID((int) jt_quoteProject
									.getValueAt(
											jt_quoteProject.getSelectedRow(), 0));
					quoteMap.put("quote_batch", batchList);
					// 从数据库获取检验内容数据
					List<InspectionContent> contentList = null;
					if (null != batchList && batchList.size() > 0) {
						InspectionBatch inspectionBatch = batchList.get(0);
						contentList = new InspectionContentServiceImpl()
								.queryAllInspectionContentByBatchId(inspectionBatch
										.getId());
						quoteMap.put("quote_content", contentList);
					}
					return quoteMap;
				}

				protected void done() {
					Map<String, Object> quoteMap = null;
					try {
						quoteMap = get();
						// 切换检验批数据
						@SuppressWarnings("unchecked")
						List<InspectionBatch> inspectionList = (List<InspectionBatch>) quoteMap
								.get("quote_batch");
						RenderDataUtils.renderBatchData(jt_inspectionBatch,
								inspectionList);
						// 切换检验内容数据
						@SuppressWarnings("unchecked")
						List<InspectionContent> contentList = (List<InspectionContent>) quoteMap
								.get("quote_content");
						RenderDataUtils.renderPartContentData(
								jt_inspectionContent, contentList);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				};
			}.execute();
		}
	}

	/**
	 * 检验批面板JTable点击事件逻辑处理
	 *
	 * @param jt_inspectionBatch
	 * @param jt_inspectionContent
	 */
	public void inspectionBatchToContent(final JTable jt_inspectionBatch,
			final JTable jt_inspectionContent) {
		if (jt_inspectionBatch.getRowCount() <= 0) {
			DefaultTableModel contentModel = (DefaultTableModel) jt_inspectionContent
					.getModel();
			contentModel.getDataVector().clear();
			jt_inspectionContent.updateUI();
		} else {
			new SwingWorker<List<InspectionContent>, Void>() {
				@Override
				protected List<InspectionContent> doInBackground()
						throws Exception {
					// 获取选择检验批下所有的数据数据
					List<InspectionContent> contentList = new InspectionContentServiceImpl()
							.queryAllInspectionContentByBatchId((int) jt_inspectionBatch.getValueAt(
									jt_inspectionBatch.getSelectedRow(), 0));
					return contentList;
				}

				protected void done() {
					List<InspectionContent> contentList = null;
					try {
						contentList = get();
						// 切换检验内容数据
						RenderDataUtils.renderPartContentData(
								jt_inspectionContent, contentList);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				};
			}.execute();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {

	}

}
