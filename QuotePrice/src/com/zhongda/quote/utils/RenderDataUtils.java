package com.zhongda.quote.utils;

import java.awt.Desktop;
import java.awt.FileDialog;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.model.SysInspectionContent;
import com.zhongda.quote.service.impl.InspectionBatchServiceImpl;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.service.impl.QuoteProjectServiceImpl;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;

/**
 * <p>
 * 渲染数据类
 * </p>
 * 
 * @author zmdeng
 * @date 2017年8月18日
 */
public class RenderDataUtils {

	private static Desktop desktop;

	/**
	 * 渲染数据到任务Table
	 * 
	 * @param jt_quoteTask
	 *            任务Table
	 * @param taskList
	 *            任务数据
	 */
	public static void renderTaskData(JTable jt_quoteTask,
			List<QuoteTask> taskList) {
		DefaultTableModel model = (DefaultTableModel) jt_quoteTask.getModel();
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
	 * 
	 * @param jt_quoteProjec
	 *            项目Table
	 * @param quoteMap
	 *            项目数据
	 */
	public static void renderProjectData(JTable jt_quoteProject,
			List<QuoteProject> projectList) {
		DefaultTableModel model = (DefaultTableModel) jt_quoteProject
				.getModel();
		// 清除模板数据
		model.getDataVector().clear();
		if (null != projectList && projectList.size() > 0) {
			for (QuoteProject quoteProject : projectList) {
				Vector<Object> rowData = new Vector<Object>();
				rowData.add(quoteProject.getId());
				rowData.add(quoteProject.getProjectName());
				rowData.add(quoteProject.getIndustry().getIndustryName());
				rowData.add(quoteProject.getAddress().getMergerName());
				rowData.add(quoteProject.getOtherAmount());
				rowData.add(quoteProject.getProjectAmount());
				rowData.add(quoteProject.getIndustryId());
				rowData.add(quoteProject.getAddressId());
				model.addRow(rowData);
			}
			jt_quoteProject.setRowSelectionInterval(0, 0);// 选中第一行
		}
		jt_quoteProject.updateUI();
	}

	/**
	 * 渲染单个项目数据到项目Table
	 * 
	 * @param jt_quoteProjec
	 *            项目Table
	 * @param quoteProject
	 *            单个项目数据
	 */
	public static void renderSingleProjectData(JTable jt_quoteProject,
			QuoteProject quoteProject) {
		DefaultTableModel model = (DefaultTableModel) jt_quoteProject
				.getModel();
		Vector<Object> rowData = new Vector<Object>();
		rowData.add(quoteProject.getId());
		rowData.add(quoteProject.getProjectName());
		rowData.add(quoteProject.getIndustry().getIndustryName());
		rowData.add(quoteProject.getAddress().getMergerName());
		rowData.add(quoteProject.getOtherAmount());
		rowData.add(quoteProject.getProjectAmount());
		rowData.add(quoteProject.getIndustryId());
		rowData.add(quoteProject.getAddressId());
		model.addRow(rowData);
		jt_quoteProject.setRowSelectionInterval(model.getRowCount() - 1,
				model.getRowCount() - 1);
		jt_quoteProject.updateUI();
	}

	/**
	 * 渲染数据到检验批Table
	 * 
	 * @param jt_inspectionBatch
	 *            检验批Table
	 * @param inspectionList
	 *            检验批数据
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
				dataRow.add(inspectionBatch.getInspectionBatchName());
				dataRow.add(inspectionBatch.getInspectionBatchAmount());
				model.addRow(dataRow);
			}
			jt_inspectionBatch.setRowSelectionInterval(0, 0);// 选中第一行
		}
		jt_inspectionBatch.updateUI();
	}

	/**
	 * 渲染单个检验批数据到检验批Table
	 * 
	 * @param jt_inspectionBatch
	 *            检验批Table
	 * @param inspectionBatch
	 *            单个检验批数据
	 */
	public static void renderSingleBatchData(JTable jt_inspectionBatch,
			InspectionBatch inspectionBatch) {
		if (null != inspectionBatch) {
			DefaultTableModel model = (DefaultTableModel) jt_inspectionBatch
					.getModel();
			Vector<Object> dataRow = new Vector<Object>();
			dataRow.add(inspectionBatch.getId());
			dataRow.add(inspectionBatch.getInspectionBatchName());
			dataRow.add(inspectionBatch.getInspectionBatchAmount());
			model.addRow(dataRow);
			jt_inspectionBatch.setRowSelectionInterval(model.getRowCount() - 1,
					model.getRowCount() - 1);// 选中新添加的一行
			jt_inspectionBatch.updateUI();
		}
	}

	/**
	 * 渲染单个自定义部分检验内容数据到检验内容Table
	 * 
	 * @param jt_inspectionContent
	 *            检验内容Table
	 * @param inspectionList
	 *            单个检验内容数据
	 */
	public static void renderSinglePartContentData(JTable jt_inspectionContent,
			InspectionContent inspectionContent) {
		if (null != inspectionContent) {
			DefaultTableModel model = (DefaultTableModel) jt_inspectionContent
					.getModel();
			Vector<Object> dataRow = new Vector<Object>();
			dataRow.add(inspectionContent.getId());
			dataRow.add(inspectionContent.getInspectionContentName());
			dataRow.add(inspectionContent.getSampleQuantity());
			dataRow.add(inspectionContent.getSingleObjectQuantity());
			dataRow.add(inspectionContent.getChargeStandard());
			dataRow.add(inspectionContent.getInspectionContentAmount());
			model.addRow(dataRow);
			jt_inspectionContent.setRowSelectionInterval(
					model.getRowCount() - 1, model.getRowCount() - 1);// 选中新添加的一行
			jt_inspectionContent.updateUI();
		}
	}

	/**
	 * 渲染自定义部分数据数据到检验内容Table
	 * 
	 * @param jt_inspectionContent
	 *            检验内容Table
	 * @param inspectionList
	 *            自定义部分检验内容数据
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
	 * 
	 * @param jt_inspectionContent
	 *            检验内容Table
	 * @param inspectionContent
	 *            单个检验内容数据
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
			jt_inspectionContent.setRowSelectionInterval(
					model.getRowCount() - 1, model.getRowCount() - 1);// 选中新添加的一行
			jt_inspectionContent.updateUI();
		}
	}

	/**
	 * 渲染数据到系统检验内容Table
	 * 
	 * @param jt_sysInspectionContent
	 *            系统检验内容Table
	 * @param sysInspectionList
	 *            系统检验内容数据
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
	
	/**
	 * 打开公司官网
	 */
	public static void openHomeWeb() {
		if (Desktop.isDesktopSupported()) {
			desktop = Desktop.getDesktop();
			try {
				// URI指定网页的地址
				desktop.browse(new URI("http://www.hnzdjc.com/"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public static void exportTask(final String[] taskidAry,
			final String[] taskName, final String[] taskNumberName,
			final JFrame frame) {
		new SwingWorker<List<QuoteTask>, Void>() {
			@Override
			protected List<QuoteTask> doInBackground() throws Exception {
				List<QuoteTask> taskList = new ArrayList<QuoteTask>();
				for (String string : taskidAry) {
					taskList.add(new QuoteTaskServiceImpl()
							.queryQuoteTaskByNumber(string));
				}
				if (null != taskList) {
					for (QuoteTask quoteTask : taskList) {
						if (null != quoteTask) {
							quoteTask
									.setProjectList(new QuoteProjectServiceImpl()
											.queryAllQuoteProjectsByTaskNmber(quoteTask
													.getId()));
						}

						if (null != quoteTask
								&& null != quoteTask.getProjectList()) {
							for (QuoteProject quoteProject : quoteTask
									.getProjectList()) {
								quoteProject
										.setBatchList(new InspectionBatchServiceImpl()
												.queryAllInspectionBatchByProjectID(quoteProject
														.getId()));
								if (null != quoteProject
										&& null != quoteProject.getBatchList()) {
									for (InspectionBatch inspectionBatch : quoteProject
											.getBatchList()) {
										inspectionBatch
												.setContentList(new InspectionContentServiceImpl()
														.queryAllContentByBatchId(inspectionBatch
																.getId()));
									}
								}
							}
						}
					}
				}

				return taskList;
			}

			protected void done() {
				List<QuoteTask> taskList = null;
				try {

					taskList = get();

					// 第一行类容
					String workSheetTitle = "项目报价表";
					// 表头字段
					String[] head = { "序号", "项目名称", "检验批", "检测内容", "单位",
							"单价(元)", "数量", "次数", "合价（元）", "抽样方法", "报价方法" };
					HSSFWorkbook workbook = new HSSFWorkbook();
					// 创建单元格样式
					HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
					// 指定单元格居中对齐
					cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					// 指定单元格垂直居中对齐
					cellStyleTitle
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					// 指定当单元格内容显示不下时自动换行
					cellStyleTitle.setWrapText(true);
					// 设置单元格字体
					HSSFFont font = workbook.createFont();
					font.setFontName("宋体");
					font.setFontHeightInPoints((short) 11);
					cellStyleTitle.setFont(font);

					cellStyleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);

					HSSFSheet sheet = workbook.createSheet();
					sheet.setColumnWidth(0, 3000);
					sheet.setColumnWidth(1, 4000);
					sheet.setColumnWidth(2, 4000);
					sheet.setColumnWidth(3, 10000);
					sheet.setColumnWidth(4, 1800);
					sheet.setColumnWidth(5, 2500);
					sheet.setColumnWidth(6, 1500);
					sheet.setColumnWidth(7, 1500);
					sheet.setColumnWidth(8, 3000);
					sheet.setColumnWidth(9, 15000);
					sheet.setColumnWidth(10, 15000);

					ExportExcelUtils excel = new ExportExcelUtils(workbook,
							sheet);
					// 报表头部
					excel.createNormalHead(workSheetTitle, 10);
					// 设置第一行表头数据
					HSSFRow row = sheet.createRow(1);
					HSSFCell cell = null;
					excel.createNormalTwoRow(head);

					int allRow = 2;
					int rowNumber = 1;
					int taskNumber = -1;
					double otherAmount = 0;
					double total = 0;
					for (QuoteTask quoteTask : taskList) {
						taskNumber++;
						excel.createNormalSpecial("任务"
								+ taskNumberName[taskNumber],
								taskName[taskNumber], allRow, allRow, allRow);
						allRow++;
						for (QuoteProject quoteProject : quoteTask
								.getProjectList()) {
							int projectBegin = 0;
							otherAmount += quoteProject.getOtherAmount();
							for (InspectionBatch inspectionBatch : quoteProject
									.getBatchList()) {
								for (InspectionContent inspectionContent : inspectionBatch
										.getContentList()) {
									total += inspectionContent
											.getInspectionContentAmount();
									row = sheet.createRow(allRow);
									String[] content = new String[11];
									content[0] = String.valueOf(rowNumber);
									content[1] = quoteProject.getProjectName();
									content[2] = inspectionBatch
											.getInspectionBatchName();
									content[3] = inspectionContent
											.getInspectionContentName();
									content[4] = inspectionContent
											.getChargeUnit();
									content[5] = String
											.valueOf(inspectionContent
													.getChargeStandard());
									content[6] = String
											.valueOf(inspectionContent
													.getSampleQuantity());
									content[7] = String
											.valueOf(inspectionContent
													.getSingleObjectQuantity());
									content[8] = String
											.valueOf(inspectionContent
													.getInspectionContentAmount());
									content[9] = String
											.valueOf(inspectionContent
													.getSampleBasis()
													.getBasisFileNumber()
													+ " "
													+ inspectionContent
															.getSampleBasis()
															.getBasisFileName()
													+ " "
													+ inspectionContent
															.getSampleBasis()
															.getBasisFileIndex());
									content[10] = String
											.valueOf(inspectionContent
													.getQuoteBasis()
													.getBasisFileNumber()
													+ " "
													+ inspectionContent
															.getQuoteBasis()
															.getBasisFileName()
													+ " "
													+ inspectionContent
															.getQuoteBasis()
															.getBasisFileIndex());
									for (int i = 0; i < content.length; i++) {
										cell = row.createCell(i);
										cell.setCellStyle(cellStyleTitle);
										cell.setCellValue(new HSSFRichTextString(
												content[i]));
									}
									allRow++;
									rowNumber++;
									projectBegin++;
								}
								int begin = allRow
										- inspectionBatch.getContentList()
												.size();
								excel.merge(begin, allRow - 1, 2, 2);
							}
							excel.merge(allRow - projectBegin, allRow - 1, 1, 1);

						}
						total += otherAmount;
						row = sheet.createRow(allRow);
						row.setHeight((short) 600);
						cell = row.createCell(0);
						cell.setCellStyle(cellStyleTitle);
						cell.setCellValue(new HSSFRichTextString(String
								.valueOf(rowNumber)));

						cell = row.createCell(1);
						cell.setCellStyle(cellStyleTitle);
						cell.setCellValue(new HSSFRichTextString("其他费用"));

						for (int i = 2; i < 8; i++) {
							cell = row.createCell(i);
							cell.setCellStyle(cellStyleTitle);
							cell.setCellValue(new HSSFRichTextString("—"));
						}
						cell = row.createCell(8);
						cell.setCellStyle(cellStyleTitle);
						cell.setCellValue(new HSSFRichTextString(String
								.valueOf(otherAmount)));

						cell = row.createCell(9);
						cell.setCellStyle(cellStyleTitle);
						cell = row.createCell(10);
						cell.setCellStyle(cellStyleTitle);

						excel.merge(allRow, allRow, 1, 3);
						allRow++;
						rowNumber++;

					}
					// 设置合计行

					HSSFCellStyle cellStyle2 = workbook.createCellStyle();

					// 指定单元格居中对齐
					cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					// 指定单元格垂直居中对齐
					cellStyle2
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					// 指定当单元格内容显示不下时自动换行
					cellStyle2.setWrapText(true);
					// 设置单元格字体
					HSSFFont font2 = workbook.createFont();
					font2.setFontName("宋体");
					font2.setFontHeightInPoints((short) 11);
					cellStyle2.setFont(font);
					cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);

					HSSFPalette palette = workbook.getCustomPalette();
					palette.setColorAtIndex(HSSFColor.PINK.index, (byte) 255,
							(byte) 242, (byte) 204);
					cellStyle2.setFillForegroundColor(HSSFColor.PINK.index);
					cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle2.setFillBackgroundColor(HSSFColor.PINK.index);

					row = sheet.createRow(allRow);
					row.setHeight((short) 600);
					cell = row.createCell(0);
					cell.setCellStyle(cellStyle2);
					cell.setCellValue(new HSSFRichTextString("合计"));

					for (int i = 1; i < 8; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(cellStyle2);
					}
					cell = row.createCell(8);
					cell.setCellStyle(cellStyle2);
					cell.setCellValue(new HSSFRichTextString(String
							.valueOf(total)));
					cell = row.createCell(9);
					cell.setCellStyle(cellStyle2);
					cell = row.createCell(10);
					cell.setCellStyle(cellStyle2);
					excel.merge(allRow, allRow, 0, 7);

					FileDialog fileDialog = new FileDialog(frame, "保存",
							FileDialog.SAVE);
					fileDialog.setVisible(true);
					String fileName = fileDialog.getDirectory()
							+ fileDialog.getFile();
					if (null != fileDialog.getDirectory()
							&& !"".equals(fileName)) {
						try {
							excel.outputExcel(fileName + ".xls");
							Runtime.getRuntime().exec(
									new String[] { "cmd.exe", "/c",
											fileName + ".xls" });
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					// excel.outputExcel("d:\\test.xls");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			};
		}.execute();
	}
}
