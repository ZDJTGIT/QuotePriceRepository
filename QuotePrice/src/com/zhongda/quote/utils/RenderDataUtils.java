package com.zhongda.quote.utils;

import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;

/**
 *<p>渲染数据类</p>
 * @author zmdeng
 * @date 2017年8月18日
 */
public class RenderDataUtils {

	/**
	 * 渲染数据到任务Table
	 * @param jt_quoteTask 任务Table
	 * @param taskList 任务数据
	 */
	public static void renderTaskData(JTable jt_quoteTask, List<QuoteTask> taskList) {
		if (null != taskList && taskList.size() > 0) {
			DefaultTableModel model = (DefaultTableModel) jt_quoteTask
					.getModel();
			// 清除模板数据
			model.getDataVector().clear();
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
			jt_quoteTask.setRowSelectionInterval(0, 0);// 选中第一行
		} else {
			DefaultTableModel model = (DefaultTableModel) jt_quoteTask
					.getModel();
			model.getDataVector().clear();
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(0);
			rowData.add("请新建任务");
			model.addRow(rowData);
			jt_quoteTask.setRowSelectionInterval(0, 0);// 选中第一行
		}
	}

	/**
	 * 渲染数据到项目Table
	 * @param jt_quoteProjec 项目Table
	 * @param quoteMap 项目数据
	 */
	public static void renderProjectData(JTable jt_quoteProjec, List<QuoteProject> projectList) {
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
			rowData.add(0);
			rowData.add("请新建项目");
			model.addRow(rowData);
			jt_quoteProjec.setRowSelectionInterval(0, 0);// 选中第一行
		}
	}

	/**
	 * 渲染数据到检验批Table
	 * @param jt_inspectionBatch 检验批Table
	 * @param inspectionList 检验批数据
	 */
	public static void renderBatchData(JTable jt_inspectionBatch,
			List<InspectionBatch> inspectionList) {
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
			rowData.add(0);
			rowData.add("请新建检验批");
			model.addRow(rowData);
			jt_inspectionBatch.setRowSelectionInterval(0, 0);// 选中第一行
		}
	}

	/**
	 * 渲染数据到检验内容Table
	 * @param jt_inspectionContent 检验内容Table
	 * @param inspectionList 检验内容数据
	 */
	public static void renderContentData(JTable jt_inspectionContent,
			List<InspectionContent> contentList) {
		if (null != contentList && contentList.size() > 0) {
			DefaultTableModel model = (DefaultTableModel) jt_inspectionContent
					.getModel();
			// 清除模板数据
			model.getDataVector().clear();
			for (InspectionContent inspectionContent : contentList) {
				Vector<Object> dataRow = new Vector<Object>();
				dataRow.add(inspectionContent.getId());
				dataRow.add(inspectionContent
						.getInspectionContentName());
				dataRow.add(inspectionContent
						.getSampleQuantity());
				dataRow.add(inspectionContent
						.getSingleObjectQuantity());
				dataRow.add(inspectionContent
						.getChargeStandard());
				model.addRow(dataRow);
			}
			jt_inspectionContent.setRowSelectionInterval(0, 0);// 选中第一行
		} else {
			DefaultTableModel model = (DefaultTableModel) jt_inspectionContent
					.getModel();
			model.getDataVector().clear();
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(0);
			rowData.add("请新建检验内容");
			model.addRow(rowData);
			jt_inspectionContent.setRowSelectionInterval(0, 0);// 选中第一行
		}
	}
}
