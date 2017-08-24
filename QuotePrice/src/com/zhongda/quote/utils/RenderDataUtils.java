package com.zhongda.quote.utils;

import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.model.SysInspectionContent;

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
		DefaultTableModel model = (DefaultTableModel) jt_quoteTask
				.getModel();
		// 清除模板数据
		model.getDataVector().clear();
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
			jt_quoteTask.setRowSelectionInterval(0, 0);// 选中第一行
		}
		jt_quoteTask.updateUI();
	}

	/**
	 * 渲染数据到项目Table
	 * @param jt_quoteProjec 项目Table
	 * @param quoteMap 项目数据
	 */
	public static void renderProjectData(JTable jt_quoteProject, List<QuoteProject> projectList) {
		DefaultTableModel model = (DefaultTableModel) jt_quoteProject
				.getModel();
		// 清除模板数据
		model.getDataVector().clear();
		if (null != projectList && projectList.size() > 0) {
			for (QuoteProject quoteProject : projectList) {
				Vector<Object> rowData = new Vector<Object>();
				rowData.add(quoteProject.getId());
				rowData.add(quoteProject.getProjectName());
				rowData.add(quoteProject.getIndustry()
						.getIndustryName());
				rowData.add(quoteProject.getAddress()
						.getMergerName());
				rowData.add(quoteProject.getOtherAmount());
				rowData.add(quoteProject.getProjectAmount());
				model.addRow(rowData);
			}
			jt_quoteProject.setRowSelectionInterval(0, 0);// 选中第一行
		}
		jt_quoteProject.updateUI();
	}

	/**
	 * 渲染单个项目数据到项目Table
	 * @param jt_quoteProjec 项目Table
	 * @param quoteProject 单个项目数据
	 */
	public static void renderSingleProjectData(JTable jt_quoteProject,
			QuoteProject quoteProject) {
		DefaultTableModel model = (DefaultTableModel) jt_quoteProject
				.getModel();
		Vector<Object> rowData = new Vector<Object>();
		rowData.add(quoteProject.getId());
		rowData.add(quoteProject.getProjectName());
		rowData.add(quoteProject.getIndustry()
				.getIndustryName());
		rowData.add(quoteProject.getAddress()
				.getMergerName());
		rowData.add(quoteProject.getOtherAmount());
		rowData.add(quoteProject.getProjectAmount());
		model.addRow(rowData);
		jt_quoteProject.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
		jt_quoteProject.updateUI();
	}

	/**
	 * 渲染数据到检验批Table
	 * @param jt_inspectionBatch 检验批Table
	 * @param inspectionList 检验批数据
	 */
	public static void renderBatchData(JTable jt_inspectionBatch,
			List<InspectionBatch> inspectionList) {
		DefaultTableModel model = (DefaultTableModel) jt_inspectionBatch
				.getModel();
		// 清除模板数据
		model.getDataVector().clear();
		if (null != inspectionList && inspectionList.size() > 0) {
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
		}
		jt_inspectionBatch.updateUI();
	}

	/**
	 * 渲染单个检验批数据到检验批Table
	 * @param jt_inspectionBatch 检验批Table
	 * @param inspectionBatch 单个检验批数据
	 */
	public static void renderSingleBatchData(JTable jt_inspectionBatch,
			InspectionBatch inspectionBatch) {
		if (null != inspectionBatch) {
			DefaultTableModel model = (DefaultTableModel) jt_inspectionBatch
					.getModel();
			Vector<Object> dataRow = new Vector<Object>();
			dataRow.add(inspectionBatch.getId());
			dataRow.add(inspectionBatch
					.getInspectionBatchName());
			dataRow.add(inspectionBatch
					.getInspectionBatchAmount());
			model.addRow(dataRow);
			jt_inspectionBatch.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);// 选中新添加的一行
			jt_inspectionBatch.updateUI();
		}
	}

	/**
	 * 渲染数据到检验内容Table
	 * @param jt_inspectionContent 检验内容Table
	 * @param inspectionList 检验内容数据
	 */
	public static void renderContentData(JTable jt_inspectionContent,
			List<InspectionContent> contentList) {
		DefaultTableModel model = (DefaultTableModel) jt_inspectionContent
				.getModel();
		// 清除模板数据
		model.getDataVector().clear();
		if (null != contentList && contentList.size() > 0) {
			for (InspectionContent inspectionContent : contentList) {
				Vector<Object> dataRow = new Vector<Object>();
				dataRow.add(inspectionContent.getId());
				dataRow.add(inspectionContent.getSourceId());
				dataRow.add(inspectionContent.getInspectionContentName());
				dataRow.add(inspectionContent.getSampleQuantityRange());
				dataRow.add(inspectionContent.getSampleQuantity());
				dataRow.add(inspectionContent.getSingleQuantityRange());
				dataRow.add(inspectionContent.getSingleObjectQuantity());
				dataRow.add(inspectionContent.getSampleBasisId());
				dataRow.add(inspectionContent.getChargeUnit());
				dataRow.add(inspectionContent.getChargeStandard());
				dataRow.add(inspectionContent.getChargeStandardUnit());
				dataRow.add(inspectionContent.getQuoteBasisId());
				dataRow.add(inspectionContent.getInspectionContentAmount());
				model.addRow(dataRow);
			}
			jt_inspectionContent.setRowSelectionInterval(0, 0);// 选中第一行
		}
		jt_inspectionContent.updateUI();
	}

	/**
	 * 渲染自定义部分数据数据到检验内容Table
	 * @param jt_inspectionContent 检验内容Table
	 * @param inspectionList 自定义部分检验内容数据
	 */
	public static void renderPartContentData(JTable jt_inspectionContent,
			List<InspectionContent> contentList) {
		DefaultTableModel model = (DefaultTableModel) jt_inspectionContent
				.getModel();
		// 清除模板数据
		model.getDataVector().clear();
		if (null != contentList && contentList.size() > 0) {
			for (InspectionContent inspectionContent : contentList) {
				Vector<Object> dataRow = new Vector<Object>();
				dataRow.add(inspectionContent.getId());
				dataRow.add(inspectionContent.getInspectionContentName());
				dataRow.add(inspectionContent.getSampleQuantity());
				dataRow.add(inspectionContent.getSingleObjectQuantity());
				dataRow.add(inspectionContent.getChargeStandard());
				dataRow.add(inspectionContent.getInspectionContentAmount());
				model.addRow(dataRow);
			}
			jt_inspectionContent.setRowSelectionInterval(0, 0);// 选中第一行
		}
		jt_inspectionContent.updateUI();
	}


	/**
	 * 添加单个检测内容数据到检验内容Table
	 * @param jt_inspectionContent 检验内容Table
	 * @param inspectionContent 单个检验内容数据
	 */
	public static void renderSingleContentData(JTable jt_inspectionContent,
			InspectionContent inspectionContent) {
		if (null != inspectionContent) {
			DefaultTableModel model = (DefaultTableModel) jt_inspectionContent
					.getModel();
			int index = model.getRowCount() + 1;
			Vector<Object> dataRow = new Vector<Object>();
			dataRow.add(inspectionContent.getId());
			dataRow.add(inspectionContent.getSourceId());
			dataRow.add(index);
			dataRow.add(inspectionContent.getInspectionContentName());
			dataRow.add(inspectionContent.getSampleQuantityRange());
			dataRow.add(inspectionContent.getSampleQuantity());
			dataRow.add(inspectionContent.getSingleQuantityRange());
			dataRow.add(inspectionContent.getSingleObjectQuantity());
			dataRow.add(inspectionContent.getSampleBasisId());
			dataRow.add(inspectionContent.getChargeUnit());
			dataRow.add(inspectionContent.getChargeStandard());
			dataRow.add(inspectionContent.getChargeStandardUnit());
			dataRow.add(inspectionContent.getQuoteBasisId());
			dataRow.add(inspectionContent.getInspectionContentAmount());
			model.addRow(dataRow);
			jt_inspectionContent.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);// 选中新添加的一行
			jt_inspectionContent.updateUI();
		}
	}


	/**
	 * 渲染数据到系统检验内容Table
	 * @param jt_sysInspectionContent 系统检验内容Table
	 * @param sysInspectionList 系统检验内容数据
	 */
	public static void renderSysContentData(JTable jt_sysInspectionContent,
			List<SysInspectionContent> sysInspectionList) {
		DefaultTableModel model = (DefaultTableModel) jt_sysInspectionContent
				.getModel();
		// 清除模板数据
		model.getDataVector().clear();
		if (null != sysInspectionList && sysInspectionList.size() > 0) {
			for (SysInspectionContent sysInspectionContent : sysInspectionList) {
				Vector<Object> dataRow = new Vector<Object>();
				dataRow.add(sysInspectionContent.getId());
				dataRow.add(sysInspectionContent.getInspectionContentName());
				dataRow.add(sysInspectionContent.getSampleQuantityRange());
				dataRow.add(sysInspectionContent.getSampleQuantity());
				dataRow.add(sysInspectionContent.getSingleQuantityRange());
				dataRow.add(sysInspectionContent.getSingleObjectQuantity());
				dataRow.add(sysInspectionContent.getSampleBasisId());
				dataRow.add(sysInspectionContent.getChargeUnit());
				dataRow.add(sysInspectionContent.getChargeStandard());
				dataRow.add(sysInspectionContent.getChargeStandardUnit());
				dataRow.add(sysInspectionContent.getQuoteBasisId());
				model.addRow(dataRow);
			}
			jt_sysInspectionContent.setRowSelectionInterval(0, 0);// 选中第一行
		}
		jt_sysInspectionContent.updateUI();
	}
}
