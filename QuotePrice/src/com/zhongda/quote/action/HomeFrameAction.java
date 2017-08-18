package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.InspectionBatchServiceImpl;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.service.impl.QuoteProjectServiceImpl;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;
import com.zhongda.quote.utils.FrameGoUtils;
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
	// 区别鼠标单击事件名称
	private String clickName;
	// 主界面查询对象的名称
	private JTextField jtf_queryName;

	public HomeFrameAction() {
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
		this.clickName = name;
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
			FrameGoUtils.creatTask(jt_quoteTask, true);
		} else if ("deleteTask".equals(command)) {
			deleteQuoteTask(jt_quoteTask, jt_quoteProject, jt_inspectionBatch, jt_inspectionContent);
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
				FrameGoUtils.creatTask(jt_quoteTask, false);
			}
		} else if ("creatProject".equals(command)) {
			haveTask(jt_quoteTask);
		} else if ("addInspection".equals(command)) {
			haveProject(jt_quoteProject, jt_inspectionBatch);
		} else if ("createContent".equals(command)) {
			int row = jt_inspectionBatch.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择一个检验批创建检验内容！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				// 获取当前检验批ID，新建时传入检验批ID作为打开的“钥匙”
				Integer inspectionid = (Integer) jt_inspectionBatch.getValueAt(
						row, 0);
				FrameGoUtils.createContent(inspectionid, jt_inspectionContent,
						true);
			}
		} else if ("deleteContent".equals(command)) {
			deleteInspectionContent(jt_inspectionContent);
		} else if ("updateContent".equals(command)) {
			int row = jt_inspectionContent.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择需要修改的检验内容！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				FrameGoUtils.createContent(null, jt_inspectionContent, false);
			}
		}
	}

	/**
	 * 删除检验内容
	 * @param jt_inspectionContent
	 */
	private void deleteInspectionContent(JTable jt_inspectionContent) {
		int row = jt_inspectionContent.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "没有选中需要删除的检验内容,请选中后再进行删除操作！",
					"提示信息", JOptionPane.WARNING_MESSAGE);
		} else {
			int flag = JOptionPane.showConfirmDialog(null, "确定删除该条检验内容？",
					"删除检验内容", JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				Integer contentId = (Integer) jt_inspectionContent.getValueAt(row, 0);

				//通过线程从数据库中获取该检验内容的ID
				new SwingWorker<Integer, Void>(){

					@Override
					protected Integer doInBackground() throws Exception {
						return new InspectionContentServiceImpl()
								.deleteInspectionByID(contentId);
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
	 * 创建检验批的时候判断是否有项目
	 * @param jt_quoteProjec
	 * @param jt_inspectionBatch
	 */
	private void haveProject(JTable jt_quoteProjec, JTable jt_inspectionBatch) {
		String name = null;
		int row = -1;
		row = jt_quoteProjec.getSelectedRow();
		if (row <= -1) {
			JOptionPane.showMessageDialog(null, "请选中项目", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		} else if (row > -1) {
			name = (String) jt_quoteProjec.getValueAt(row, 1);
			if ("请新建项目".equals(name) || name == null) {
				JOptionPane.showMessageDialog(null, "请先创建项目", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				FrameGoUtils
						.creatInspection(jt_quoteProjec, jt_inspectionBatch);
			}
		}

	}

	/**
	 * 创建项目时判断是否有任务
	 * @param table
	 */
	private void haveTask(JTable table) {
		String name = null;
		int row = -1;
		row = table.getSelectedRow();
		if (row <= -1) {
			JOptionPane.showMessageDialog(null, "请选中项目", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		} else if (row > -1) {
			name = (String) jt_quoteTask.getValueAt(row, 1);
			if ("请新建项目".equals(name) || name == null) {
				JOptionPane.showMessageDialog(null, "请先创建项目", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				FrameGoUtils.creatProject(table);
			}
		}

	}

	/**
	 * 查询报价任务
	 * @param jt_quoteTask
	 * @param jtf_queryName 存放查询条件的任务名称
	 */
	private void queryQuotePrice(JTable jt_quoteTask, JTextField jtf_queryName) {
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
	 * 删除报价任务
	 * @param jt_quoteTask
	 *            显示任务的列表
	 */
	private void deleteQuoteTask(JTable jt_quoteTask, JTable jt_quoteProject, JTable jt_inspectionBatch, JTable jt_inspectionContent) {
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
								jt_quoteTask.setRowSelectionInterval(0, 0);
								taskToProject(jt_quoteTask, jt_quoteProject, jt_inspectionBatch, jt_inspectionContent);
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
	public void mouseDragged(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		int row = jt_quoteTask.rowAtPoint(e.getPoint());
		int col = jt_quoteTask.columnAtPoint(e.getPoint());
		if (row > -1 && col > -1) {
			Object value = jt_quoteTask.getValueAt(row, col);
			if (null != value && !"".equals(value))
				jt_quoteTask.setToolTipText(value.toString());// 悬浮显示单元格内容
			else
				jt_quoteTask.setToolTipText(null);// 关闭提示
		}


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ("task_jtabel".equals(clickName)) {
			taskToProject(jt_quoteTask, jt_quoteProject, jt_inspectionBatch, jt_inspectionContent);
		} else if ("project_jtabel".equals(clickName)) {
			projectToInspectionBatch(jt_quoteProject, jt_inspectionBatch, jt_inspectionContent);
		} else if ("batch_jtabel".equals(clickName)) {
			inspectionBatchToContent(jt_inspectionBatch, jt_inspectionContent);
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

	/**
	 * Task面板Jtable点击事件逻辑处理
	 * @param jt_quoteTask
	 * @param jt_quoteProjec
	 * @param jt_inspectionBatch
	 * @param jt_inspectionContent
	 */
	public void taskToProject(JTable jt_quoteTask, JTable jt_quoteProject, JTable jt_inspectionBatch, JTable jt_inspectionContent) {
		new SwingWorker<Map<String, Object>, Void>() {

			@Override
			protected Map<String, Object> doInBackground() throws Exception {
				// 获取选择任务下所有的数据数据
				Map<String, Object> quoteMap = new HashMap<String, Object>();
				// 从数据库获取项目数据
				List<QuoteProject> projectList = new QuoteProjectServiceImpl()
						.queryAllQuoteProjectsByTaskNmber((int) jt_quoteTask
								.getValueAt(jt_quoteTask.getSelectedRow(), 0));
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
					if (null != inspectionBatchList && inspectionBatchList.size() > 0) {
						InspectionBatch inspectionBatch = inspectionBatchList.get(0);
						contentList = new InspectionContentServiceImpl()
								.queryAllInspectionContentByBatchId(inspectionBatch.getId());
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
					List<QuoteProject> projectList = (List<QuoteProject>) quoteMap.get("quote_project");
					RenderDataUtils.renderProjectData(jt_quoteProject, projectList);
					// 切换检验批数据
					@SuppressWarnings("unchecked")
					List<InspectionBatch> inspectionList = (List<InspectionBatch>) quoteMap
							.get("quote_batch");
					RenderDataUtils.renderBatchData(jt_inspectionBatch, inspectionList);
					// 切换检验内容数据
					@SuppressWarnings("unchecked")
					List<InspectionContent> contentList = (List<InspectionContent>) quoteMap
							.get("quote_content");
					RenderDataUtils.renderContentData(jt_inspectionContent, contentList);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();
	}

	/**
	 * 项目面板JTable点击事件逻辑处理
	 * @param jt_quoteProjec
	 * @param jt_inspectionBatch
	 * @param jt_inspectionContent
	 */
	public void projectToInspectionBatch(JTable jt_quoteProject, JTable jt_inspectionBatch, JTable jt_inspectionContent) {
		new SwingWorker<Map<String, Object>, Void>() {
			@Override
			protected Map<String, Object> doInBackground()
					throws Exception {
				// 获取选择项目下所有的数据数据
				Map<String, Object> quoteMap = new HashMap<String, Object>();
				// 从数据库获取检验批数据
				List<InspectionBatch> batchList = new InspectionBatchServiceImpl()
					.queryAllInspectionBatchByProjectID((int) jt_quoteProject
						.getValueAt(jt_quoteProject.getSelectedRow(), 0));
				quoteMap.put("quote_batch", batchList);
				// 从数据库获取检验内容数据
				List<InspectionContent> contentList = null;
				if (null != batchList && batchList.size() > 0) {
					InspectionBatch inspectionBatch = batchList.get(0);
					contentList = new InspectionContentServiceImpl()
							.queryAllInspectionContentByBatchId(inspectionBatch.getId());
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
					RenderDataUtils.renderBatchData(jt_inspectionBatch, inspectionList);
					// 切换检验内容数据
					@SuppressWarnings("unchecked")
					List<InspectionContent> contentList = (List<InspectionContent>) quoteMap
							.get("quote_content");
					RenderDataUtils.renderContentData(jt_inspectionContent, contentList);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			};
		}.execute();
	}

	/**
	 * 检验批面板JTable点击事件逻辑处理
	 * @param jt_inspectionBatch
	 * @param jt_inspectionContent
	 */
	public void inspectionBatchToContent(JTable jt_inspectionBatch, JTable jt_inspectionContent) {
		new SwingWorker<List<InspectionContent>, Void>() {
			@Override
			protected List<InspectionContent> doInBackground()
					throws Exception {
				// 获取选择检验批下所有的数据数据
				List<InspectionContent> contentList = new InspectionContentServiceImpl()
				.queryAllInspectionContentByBatchId((int) jt_inspectionBatch
						.getValueAt(jt_inspectionBatch.getSelectedRow(), 0));
				return contentList;
			}

			protected void done() {
				List<InspectionContent> contentList = null;
				try {
					contentList = get();
					// 切换检验内容数据
					RenderDataUtils.renderContentData(jt_inspectionContent, contentList);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			};
		}.execute();
	}
}
