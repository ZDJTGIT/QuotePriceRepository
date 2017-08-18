package com.zhongda.quote.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;



import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.InspectionBatchServiceImpl;
import com.zhongda.quote.service.impl.QuoteProjectServiceImpl;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;
import com.zhongda.quote.utils.FrameGoUtils;

/**
 *
 * <p>
 * HomeFrame事件类
 * </p>
 *
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月10日
 */

public class HomeFrameAction implements ActionListener, MouseMotionListener ,MouseListener{


	// 主界面报价任务JTable
	private JTable jt_quoteTask;
	// 主界面报价项目JTable
	private JTable jt_quoteProjec;
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

	public HomeFrameAction(JTable jt_quoteTask, JTable jt_quoteProjec,
			JTable jt_inspectionBatch, JTable jt_inspectionContent) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProjec = jt_quoteProjec;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
	}

	public HomeFrameAction(JTable jt_quoteTask,
			JTable jt_quoteProjec, JTable jt_inspectionBatch, JTable jt_inspectionContent, String name) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProjec = jt_quoteProjec;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
		this.clickName = name;
	}

	public HomeFrameAction(JTable jt_quoteTask, JTable jt_quoteProjec,
			JTable jt_inspectionBatch, JTable jt_inspectionContent,
			JTextField jtf_queryName) {
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProjec = jt_quoteProjec;
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
			deleteQuoteTask(jt_quoteTask);
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
			haveProject(jt_quoteTask);
		} else if("createContent".equals(command)){
			int row = jt_inspectionBatch.getSelectedRow();
			if(row < 0){
				JOptionPane.showMessageDialog(null,
						"请选择一个检验批创建检验内容！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			}else{
				//获取当前检验批ID，新建时传入检验批ID作为打开的“钥匙”
				Integer inspectionid = (Integer)jt_inspectionBatch.getValueAt(row, 0);
				FrameGoUtils.createContent(inspectionid,jt_inspectionContent, true);
			}
		} else if("deleteContent".equals(command)){
			deleteInspectionContent(jt_inspectionContent);
		}else if("updateContent".equals(command)){
			int row = jt_inspectionContent.getSelectedRow();
			if(row < 0){
				JOptionPane.showMessageDialog(null,
						"请选择需要修改的检验内容！", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			}else{
				FrameGoUtils.createContent(null,jt_inspectionContent, false);
			}
		}
	}

	/**
	 * 删除检验内容
	 */
	private void deleteInspectionContent(JTable jt_shareTable) {
		int row = jt_shareTable.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null,
					"没有选中需要删除的检验内容,请选中后再进行删除操作！", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int flag = JOptionPane.showConfirmDialog(null,
					"确定删除该条检验内容？",
					"删除检验内容", JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				Integer contentId = (Integer) jt_shareTable.getValueAt(row, 0);
				//通过线程从数据库中获取该检验内容的ID
				new SwingWorker<Integer, Void>(){
					
					@Override
					protected Integer doInBackground() throws Exception {
						return new InspectionContentServiceImpl().deleteInspectionByID(contentId);
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

								DefaultTableModel model = (DefaultTableModel) jt_shareTable
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
	 *
	 * @param table
	 */
	private void haveProject(JTable table) {
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
				FrameGoUtils.creatInspection(table);
			}
		}

	}

	/**
	 * 创建项目时判断是否有任务
	 *
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
<<<<<<< Updated upstream
	 *
	 * @param jt_quoteTask
=======
	 * 
	 * @param jt_shareTable
>>>>>>> Stashed changes
	 *            显示任务的列表
	 * @param jtf_queryName
	 *            存放查询条件的任务名称
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
					List<QuoteTask> taskList;
					try {
						taskList = get();
						// jt_shareTable.updateUI();
						DefaultTableModel model = (DefaultTableModel) jt_quoteTask
								.getModel();
						// 清除原有数据
						model.getDataVector().clear();
						// 将数据添加到table
						if (null != taskList && taskList.size() > 0) {
							for (QuoteTask quoteTask : taskList) {
								Vector<Object> rowData = new Vector<Object>();
								rowData.add(quoteTask.getId());
								rowData.add(quoteTask.getTaskNumber());
								rowData.add(quoteTask.getTaskName());
								rowData.add(quoteTask.getTaskDescription());
								rowData.add(quoteTask.getCreateUser());
								rowData.add(quoteTask.getCreateDate());
								rowData.add(quoteTask.getLastUpdateDate());
								rowData.add(quoteTask.getTaskAmount());
								model.addRow(rowData);
							}
						}
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
	private void deleteQuoteTask(JTable jt_shareTable) {
		// 获取Table中被选中的行序号
		final int row = jt_shareTable.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "没有选中需要删除的报价任务,请选中后再进行删除操作！",
					"提示信息", JOptionPane.WARNING_MESSAGE);
		} else {
			int flag = JOptionPane.showConfirmDialog(null,
					"点击确认按钮，将会删除所选中的报价任务，包括报价任务下的所有项目以及检验批，是否确认删除？", "删除报价任务",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				Object value = jt_shareTable.getValueAt(row, 0);
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

								DefaultTableModel model = (DefaultTableModel) jt_shareTable
										.getModel();
								model.removeRow(row);
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
			taskToProject();
		} else if ("project_jtabel".equals(clickName)) {
			ProjectAndInspection();
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
	 * Task面板Jtable点击事件
	 */
	private void taskToProject() {
		new SwingWorker<Map<String, Object>, Void>() {

			@Override
			protected Map<String, Object> doInBackground() throws Exception {
				// 获取项目任务数据
				Map<String, Object> quoteMap = new HashMap<String, Object>();
				// 从数据库获取项目数据
				List<QuoteProject> projectList = new QuoteProjectServiceImpl()
						.queryAllQuoteProjectsByTaskNmber((int) jt_quoteTask
								.getValueAt(jt_quoteTask.getSelectedRow(), 0));
				quoteMap.put("quote_project", projectList);
				// 从数据库获取检验批数据
				List<InspectionBatch> inspectionBatch = null;
				if (null != projectList && projectList.size() > 0) {
					QuoteProject quoteProject = projectList.get(0);
					inspectionBatch = new InspectionBatchServiceImpl()
							.queryAllInspectionBatchByProjectID(quoteProject
									.getId());
				}
				quoteMap.put("quote_inspection", inspectionBatch);
				return quoteMap;
			}

			protected void done() {
				Map<String, Object> quoteMap = null;

				try {
					quoteMap = get();
					// 修改项目面板数据
					@SuppressWarnings("unchecked")
					List<QuoteProject> projectList = (List<QuoteProject>) quoteMap
							.get("quote_project");
					if (null != projectList && projectList.size() > 0) {

						DefaultTableModel model = (DefaultTableModel) jt_quoteProjec
								.getModel();
						// 清除模板数据
						model.getDataVector().clear();
						for (QuoteProject quoteProject : projectList) {
							Vector<Object> rowData = new Vector<Object>();
							rowData.add(quoteProject.getId());
							rowData.add(quoteProject.getProjectName());
							rowData.add(quoteProject.getIndustry()
									.getIndustryName());
							rowData.add(quoteProject.getAddress()
									.getMergerName());
							rowData.add(quoteProject.getOtherAmout());
							rowData.add(quoteProject.getProjectAmount());
							model.addRow(rowData);
						}
						jt_quoteProjec.setRowSelectionInterval(0, 0);// 选中第一行

					} else {
						DefaultTableModel model = (DefaultTableModel) jt_quoteProjec
								.getModel();
						model.getDataVector().clear();
						Vector<Object> rowData = new Vector<Object>();
						rowData.add("");
						rowData.add("请新建项目");
						model.addRow(rowData);
						jt_quoteProjec.setRowSelectionInterval(0, 0);// 选中第一行
					}
					// 修改检验批数据
					@SuppressWarnings("unchecked")
					List<InspectionBatch> inspectionList = (List<InspectionBatch>) quoteMap
							.get("quote_inspection");
					if (null != inspectionList && inspectionList.size() > 0) {
						DefaultTableModel model = (DefaultTableModel) jt_inspectionBatch
								.getModel();
						// 清除模板数据
						model.getDataVector().clear();
						for (InspectionBatch inspectionBatch : inspectionList) {
							Vector<Object> dataRow = new Vector<Object>();
							dataRow.add(inspectionBatch.getId());
							dataRow.add(inspectionBatch
									.getInspectionBatchName());
							dataRow.add(inspectionBatch
									.getInspectionBatchAmount());
							model.addRow(dataRow);
						}

						jt_inspectionBatch.setRowSelectionInterval(0, 0);// 选中第一行
					} else {
						DefaultTableModel model = (DefaultTableModel) jt_inspectionBatch
								.getModel();
						model.getDataVector().clear();
						Vector<Object> rowData = new Vector<Object>();
						rowData.add("");
						rowData.add("请新建检验批");
						model.addRow(rowData);
						jt_inspectionBatch.setRowSelectionInterval(0, 0);// 选中第一行
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			};

		}.execute();
	}

	/**
	 * 项目面板JTable点击事件逻辑处理
	 */
	private void ProjectAndInspection() {
		new SwingWorker<List<InspectionBatch>, InspectionBatch>() {

			@Override
			protected List<InspectionBatch> doInBackground() throws Exception {
				// 从数据库获取检验批数据
				return new InspectionBatchServiceImpl()
						.queryAllInspectionBatchByProjectID((int) jt_quoteProjec
								.getValueAt(jt_quoteProjec.getSelectedRow(), 0));
			}

			protected void done() {
				List<InspectionBatch> inspectionList = null;
				try {
					inspectionList = get();

					if (null != inspectionList && inspectionList.size() > 0) {
						DefaultTableModel dtm = (DefaultTableModel) jt_inspectionBatch
								.getModel();
						dtm.getDataVector().clear();
						for (InspectionBatch inspectionBatch : inspectionList) {
							Vector<Object> dataRow = new Vector<Object>();
							dataRow.add(inspectionBatch.getId());
							dataRow.add(inspectionBatch
									.getInspectionBatchName());
							dataRow.add(inspectionBatch
									.getInspectionBatchAmount());
							dtm.addRow(dataRow);
						}

						jt_inspectionBatch.setRowSelectionInterval(0, 0);// 选中第一行
					} else {
						DefaultTableModel model = (DefaultTableModel) jt_inspectionBatch
								.getModel();
						model.getDataVector().clear();
						Vector<Object> rowData = new Vector<Object>();
						rowData.add("");
						rowData.add("请新建检验批");
						model.addRow(rowData);
						jt_inspectionBatch.setRowSelectionInterval(0, 0);// 选中第一行
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};

		}.execute();
	}

}
