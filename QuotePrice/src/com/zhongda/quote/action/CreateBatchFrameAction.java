package com.zhongda.quote.action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.zhongda.quote.model.Address;
import com.zhongda.quote.model.Industry;
import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.SysInspectionContent;
import com.zhongda.quote.service.impl.InspectionBatchServiceImpl;
import com.zhongda.quote.service.impl.SysInspectionContenServiceImpl;
import com.zhongda.quote.utils.ConstantUtils;
import com.zhongda.quote.utils.RenderDataUtils;

/**
 *
 * <p>
 * 创建检验批界面事件监听类
 * </p>
 *
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月12日
 *
 *       检验批报价逻辑未实现
 */
public class CreateBatchFrameAction implements MouseMotionListener,
		ItemListener, ActionListener, FocusListener, MouseListener {

	private JTextField jtf_contentName;
	private JDialog dialog;
	private JTextField jtf_batchName;
	private JTextField jtf_batchAmount;
	private JTextField jtf_projectAmount;
	private JTable jt_sysInspectionContent;
	private JTable jt_inspectionContent;
	private JTable jt_quoteTask;
	private JTable jt_quoteProject;
	private JTable jt_inspectionBatch;
	private JTable jt_partInspectionContent;
	private JPanel jp_inspectionBatch;
	private JPanel jp_search;
	private Industry industry;
	private Address address;
	private Map<String, Map<String, Object>> batchMap;
	private Map<String, Map<String, Object>> tmpBatchMap = new HashMap<String, Map<String, Object>>();
	private static List<InspectionContent> contentList = new ArrayList<InspectionContent>();
	private List<InspectionContent> singleContentList;

	public CreateBatchFrameAction() {
	}

	public CreateBatchFrameAction(JDialog dialog) {
		this.dialog = dialog;
	}

	public CreateBatchFrameAction(JTable jt_inspectionContent) {
		this.jt_inspectionContent = jt_inspectionContent;
	}

	public CreateBatchFrameAction(JTextField jtf_contentName) {
		this.jtf_contentName = jtf_contentName;
	}

	public CreateBatchFrameAction(JTable jt_sysInspectionContent,
			JTextField jtf_contentName, JPanel jp_search, Industry industry,
			Address address) {
		this.jt_sysInspectionContent = jt_sysInspectionContent;
		this.jtf_contentName = jtf_contentName;
		this.jp_search = jp_search;
		this.industry = industry;
		this.address = address;
	}

	// 联动检验批点击事件构造函数
	public CreateBatchFrameAction(JTable jt_sysInspectionContent,
			JTable jt_inspectionContent, JTextField jtf_batchAmount,
			JPanel jp_search, Map<String, Map<String, Object>> batchMap) {
		this.jt_sysInspectionContent = jt_sysInspectionContent;
		this.jt_inspectionContent = jt_inspectionContent;
		this.jtf_batchAmount = jtf_batchAmount;
		this.jp_search = jp_search;
		this.batchMap = batchMap;
	}

	// 单独检验批点击事件构造函数
	public CreateBatchFrameAction(JTable jt_sysInspectionContent,
			JTable jt_inspectionContent, JTextField jtf_batchAmount,
			JPanel jp_search, List<InspectionContent> singleContentList) {
		this.jt_sysInspectionContent = jt_sysInspectionContent;
		this.jt_inspectionContent = jt_inspectionContent;
		this.jtf_batchAmount = jtf_batchAmount;
		this.jp_search = jp_search;
		this.singleContentList = singleContentList;
	}

	// 联动检验批确认事件构造函数
	public CreateBatchFrameAction(JDialog dialog, JPanel jp_inspectionBatch,
			JTable jt_inspectionContent,
			Map<String, Map<String, Object>> batchMap,
			JTextField jtf_batchName, JTextField jtf_batchAmount,
			JTextField jtf_projectAmount) {
		this.dialog = dialog;
		this.jp_inspectionBatch = jp_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
		this.batchMap = batchMap;
		this.jtf_batchName = jtf_batchName;
		this.jtf_batchAmount = jtf_batchAmount;
		this.jtf_projectAmount = jtf_projectAmount;
	}

	// 单独检验批确认事件构造函数
	public CreateBatchFrameAction(JDialog dialog, JTable jt_quoteTask,
			JTable jt_quoteProject, JTable jt_inspectionBatch,
			JTable jt_partInspectionContent, JTable jt_inspectionContent,
			List<InspectionContent> singleContentList,
			JTextField jtf_batchName, JTextField jtf_batchAmount) {
		this.dialog = dialog;
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_partInspectionContent = jt_partInspectionContent;
		this.jt_inspectionContent = jt_inspectionContent;
		this.singleContentList = singleContentList;
		this.jtf_batchName = jtf_batchName;
		this.jtf_batchAmount = jtf_batchAmount;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int row = jt_inspectionContent.rowAtPoint(e.getPoint());
		int col = jt_inspectionContent.columnAtPoint(e.getPoint());
		if (row > -1 && col > -1) {
			Object value = jt_inspectionContent.getValueAt(row, col);
			if (null != value && !"".equals(value))
				jt_inspectionContent.setToolTipText(value.toString());// 悬浮显示单元格内容
			else
				jt_inspectionContent.setToolTipText(null);// 关闭提示
		}
	}

	// 鼠标点击事件
	@Override
	public void mouseClicked(MouseEvent e) {
		int count = e.getClickCount();
		if (count == 2) {
			jp_search.setVisible(false);
			double contentAmount = addSysDataToTabel(jt_sysInspectionContent,
					jt_inspectionContent);
			if (contentAmount != -1) {
				// 获取原来的检验批金额
				String amount = jtf_batchAmount.getText();
				// 如果有值则加上当前获得的检验内容金额
				if (null != amount && !"".equals(amount.trim())) {
					contentAmount = contentAmount + Double.parseDouble(amount);
				}
				jtf_batchAmount.setText(String.valueOf(contentAmount));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commandName = e.getActionCommand();
		// 界面查找按钮逻辑代码
		if ("searchSysContent".equals(commandName)) {
			String contentName = jtf_contentName.getText();
			if (null != contentName) {
				contentName = contentName.trim();
			}
			if (null == contentName || "".equals(contentName)
					|| "请输入检测内容".equals(contentName)) {
				JOptionPane.showMessageDialog(null, "请输入检测内容", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				jp_search.setVisible(true);
				searchSysInspectionContent(jt_sysInspectionContent,
						contentName, industry, address);
			}
		} else if ("confirm".equals(commandName)) {
			// int flag = JOptionPane.showConfirmDialog(null, "确定提交?", "提交检验批",
			// JOptionPane.OK_OPTION);
			// if (flag == JOptionPane.OK_OPTION) {
			if (null != this.batchMap) {
				commitInspectionBatch(dialog, jp_inspectionBatch, batchMap,
						jtf_batchName, jtf_batchAmount);
			} else {
				commitSingleInspectionBatch(dialog, jt_quoteTask,
						jt_quoteProject, jt_inspectionBatch,
						jt_partInspectionContent, singleContentList,
						jtf_batchName, jtf_batchAmount);
			}
			// }
		} else if ("cancel".equals(commandName)) {
			// int flag = JOptionPane.showConfirmDialog(null, "取消项目？", "取消检验批",
			// JOptionPane.OK_OPTION);
			// if (flag == JOptionPane.OK_OPTION) {
			dialog.dispose();
			// }
		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		String content = jtf_contentName.getText();
		if ("请输入检测内容".equals(content.trim())) {
			jtf_contentName.setText("");
			jtf_contentName.setForeground(Color.BLACK);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox checkbox = (JCheckBox) e.getItem();
		double projectAmount = 0;
		String amount = jtf_projectAmount.getText();
		// 如果有值则加上当前获得的报价项目的金额
		if (null != amount && !"".equals(amount.trim())) {
			projectAmount = Double.parseDouble(amount);
		}
		String name = checkbox.getName();
		if (checkbox.isSelected()) {
			if (!batchMap.containsKey(name)) {
				batchMap.put(name, tmpBatchMap.get(name));
				InspectionBatch inspectionBatch = (InspectionBatch) ((Map<String, Object>) tmpBatchMap
						.get(name)).get("batch");
				projectAmount += inspectionBatch.getInspectionBatchAmount();
			}
		} else {
			if (batchMap.containsKey(name)) {
				batchMap.remove(name);
				InspectionBatch inspectionBatch = (InspectionBatch) ((Map<String, Object>) tmpBatchMap
						.get(name)).get("batch");
				projectAmount -= inspectionBatch.getInspectionBatchAmount();
			}
		}
		jtf_projectAmount.setText(String.valueOf(projectAmount));
	}

	/**
	 * 联动将选好的检验批以及检验内容暂存到集合中
	 * 
	 * @param dialog
	 * @param jp_inspectionBatch
	 * @param batchMap
	 * @param jtf_batchName
	 * @param jtf_batchAmount
	 */
	private void commitInspectionBatch(JDialog dialog,
			JPanel jp_inspectionBatch,
			Map<String, Map<String, Object>> batchMap,
			JTextField jtf_batchName, JTextField jtf_batchAmount) {
		String batchName = jtf_batchName.getText();
		if (null == batchName || "".equals(batchName.trim())) {
			JOptionPane.showMessageDialog(null, "请输入检验批名称", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		} else if (jt_inspectionContent.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "请选择检验内容", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		} else {
			final InspectionBatch inspectionBatch = new InspectionBatch();
			inspectionBatch.setInspectionBatchName(batchName);
			inspectionBatch.setInspectionBatchAmount(Double
					.parseDouble(jtf_batchAmount.getText()));
			// JOptionPane.showMessageDialog(null, "提交成功",
			// "提示信息", JOptionPane.WARNING_MESSAGE);
			JCheckBox checkBox = new JCheckBox(batchName);
			// 作为检验批的关联标志
			checkBox.setName(String.valueOf(ConstantUtils.index));
			checkBox.addItemListener(this);
			jp_inspectionBatch.add(checkBox);
			jp_inspectionBatch.updateUI();
			Map<String, Object> batchAndContentMap = new HashMap<String, Object>();
			// 将检验批添加到Map集合中
			batchAndContentMap.put("batch", inspectionBatch);
			// 将检验批下所有的检验内容添加到集合
			List<InspectionContent> tmpContentList = new ArrayList<InspectionContent>();
			tmpContentList.addAll(contentList);
			batchAndContentMap.put("content", tmpContentList);
			contentList.clear();
			tmpBatchMap.put(ConstantUtils.index + "", batchAndContentMap);
			ConstantUtils.index++;
			dialog.dispose();
		}
	}

	/**
	 * 单独将选好的检验批以及检验内容插入数据库中
	 * 
	 * @param dialog
	 * @param jt_quoteTask
	 * @param jt_quoteProject
	 * @param jt_inspectionBatch
	 * @param jt_partInspectionContent
	 * @param singleContentList
	 * @param jtf_batchName
	 * @param jtf_batchAmount
	 */
	private void commitSingleInspectionBatch(JDialog dialog,
			final JTable jt_quoteTask, final JTable jt_quoteProject,
			final JTable jt_inspectionBatch,
			final JTable jt_partInspectionContent,
			final List<InspectionContent> singleContentList,
			JTextField jtf_batchName, JTextField jtf_batchAmount) {
		String batchName = jtf_batchName.getText();
		if (null == batchName || "".equals(batchName.trim())) {
			JOptionPane.showMessageDialog(null, "请输入检验批名称", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		} else if (jt_inspectionContent.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "请选择检验内容", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		} else {
			final InspectionBatch inspectionBatch = new InspectionBatch();
			inspectionBatch.setInspectionBatchName(batchName);
			double batchAmount = Double.parseDouble(jtf_batchAmount.getText());
			inspectionBatch.setInspectionBatchAmount(batchAmount);
			final int projectRow = jt_quoteProject.getSelectedRow();
			inspectionBatch.setProjectId((int) jt_quoteProject.getValueAt(
					projectRow, 0));
			// 重新计算项目金额
			double projectAmountOld = (double) jt_quoteProject.getValueAt(
					projectRow, 5);
			final double projectAmount = projectAmountOld + batchAmount;
			// 重新计算任务金额
			final int taskRow = jt_quoteTask.getSelectedRow();
			double taskAmountOld = (double) jt_quoteTask.getValueAt(taskRow, 7);
			final double taskAmount = taskAmountOld + batchAmount;

			new SwingWorker<Map<String, Object>, Void>() {

				@Override
				protected Map<String, Object> doInBackground() throws Exception {
					return new InspectionBatchServiceImpl()
							.createBatchAndContent(inspectionBatch,
									singleContentList, taskAmount,
									projectAmount);
				}

				protected void done() {
					Map<String, Object> quoteMap = null;
					try {
						quoteMap = get();
						InspectionBatch inspectionBatch = (InspectionBatch) quoteMap
								.get("batch");
						RenderDataUtils.renderSingleBatchData(
								jt_inspectionBatch, inspectionBatch);
						@SuppressWarnings("unchecked")
						List<InspectionContent> contentList = (List<InspectionContent>) quoteMap
								.get("content");
						RenderDataUtils.renderPartContentData(
								jt_partInspectionContent, contentList);
						jt_quoteTask.setValueAt(taskAmount, taskRow, 7);
						jt_quoteProject
								.setValueAt(projectAmount, projectRow, 5);
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}
			}.execute();
			dialog.dispose();
		}
	}

	/**
	 * 按检验内容名称搜索系统检验内容，并渲染到Table上
	 * 
	 * @param jt_sysInspectionContent
	 *            系统检验内容Table
	 * @param contentName
	 *            检验内容名称
	 * @param address
	 *            所选地址
	 * @param industry
	 *            所选行业
	 */
	private void searchSysInspectionContent(
			final JTable jt_sysInspectionContent, final String contentName,
			final Industry industry, final Address address) {
		new SwingWorker<List<SysInspectionContent>, Void>() {
			@Override
			protected List<SysInspectionContent> doInBackground()
					throws Exception {
				return new SysInspectionContenServiceImpl()
						.querySysInspectionContentByContentName(contentName,
								industry.getId(), address.getId());
			}

			protected void done() {
				List<SysInspectionContent> sysContentList = null;
				try {
					sysContentList = get();
					RenderDataUtils.renderSysContentData(
							jt_sysInspectionContent, sysContentList);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			};
		}.execute();
	}

	/**
	 * 把搜索到的系统数据添加到展示面板
	 * 
	 * @param jt_sysInspectionContent
	 * @param jt_inspectionContent
	 * @return 点击添加的检验内容的金额
	 */
	public double addSysDataToTabel(JTable jt_sysInspectionContent,
			JTable jt_inspectionContent) {

		int row = jt_sysInspectionContent.getSelectedRow();
		int sourceId = (int) jt_sysInspectionContent.getValueAt(row, 0);
		if (null != this.batchMap) {
			for (InspectionContent inspectionContent : contentList) {
				if (sourceId == inspectionContent.getSourceId()) {
					JOptionPane.showMessageDialog(null, "该检验内容已被选中，请不要重复选择",
							"提示信息", JOptionPane.WARNING_MESSAGE);
					return -1;
				}
			}
		} else {
			for (InspectionContent inspectionContent : singleContentList) {
				if (sourceId == inspectionContent.getSourceId()) {
					JOptionPane.showMessageDialog(null, "该检验内容已被选中，请不要重复选择",
							"提示信息", JOptionPane.WARNING_MESSAGE);
					return -1;
				}
			}
		}
		String inspectionContentName = String.valueOf(jt_sysInspectionContent
				.getValueAt(row, 1));
		String sampleQuantityRange = String.valueOf(jt_sysInspectionContent
				.getValueAt(row, 2));
		int sampleQuantity = (int) jt_sysInspectionContent.getValueAt(row, 3);
		String singleQuantityRange = String.valueOf(jt_sysInspectionContent
				.getValueAt(row, 4));
		int singleObjectQuantity = (int) jt_sysInspectionContent.getValueAt(
				row, 5);
		int sampleBasisId = (int) jt_sysInspectionContent.getValueAt(row, 6);
		String chargeUnit = String.valueOf(jt_sysInspectionContent.getValueAt(
				row, 7));
		int chargeStandard = (int) jt_sysInspectionContent.getValueAt(row, 8);
		String chargeStandardUnit = String.valueOf(jt_sysInspectionContent
				.getValueAt(row, 9));
		int quoteBasisId = (int) jt_sysInspectionContent.getValueAt(row, 10);
		// 计算检测内容总金额
		double inspectionContentAmount = sampleQuantity * singleObjectQuantity
				* chargeStandard;
		InspectionContent inspectionContent = new InspectionContent(sourceId,
				inspectionContentName, sampleQuantity, sampleQuantityRange,
				sampleBasisId, singleObjectQuantity, singleQuantityRange,
				chargeUnit, chargeStandard, chargeStandardUnit, quoteBasisId,
				inspectionContentAmount);
		// 添加到集合中
		if (null != this.batchMap) {
			contentList.add(inspectionContent);
		} else {
			singleContentList.add(inspectionContent);
		}
		RenderDataUtils.renderSingleContentData(jt_inspectionContent,
				inspectionContent);
		return inspectionContentAmount;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
	}
}
