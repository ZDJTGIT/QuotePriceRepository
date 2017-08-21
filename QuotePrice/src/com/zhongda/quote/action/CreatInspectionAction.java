package com.zhongda.quote.action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.SysInspectionContent;
import com.zhongda.quote.service.impl.InspectionBatchServiceImpl;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.service.impl.SysInspectionContenServiceImpl;

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
public class CreatInspectionAction implements MouseMotionListener,
		ActionListener, FocusListener, MouseListener {

	private JTable table;
	private JTextField jField;
	private String name;
	private JPanel panel;
	private JDialog dialog;
	private JTable jt_content;
	private JTable jt_creatIns;
	private Vector<InspectionContent> creatInspectionNumber;
	private JTextField jtf_project;
	private JTextField jtf_pname;
	private JTextField jtf_money;
	private Object[] objects;
	private JTable jt_inspection;
	private JPanel jp_inspection;
	private Double icAmount = 0.00;

	public CreatInspectionAction() {

	}

	public CreatInspectionAction(JDialog jd) {
		this.dialog = jd;
	}

	public CreatInspectionAction(JPanel jPanel) {
		this.panel = jPanel;
	}

	public CreatInspectionAction(JPanel panel, String name) {
		this.panel = panel;
		this.name = name;
	}

	public CreatInspectionAction(JPanel panel, JTable jt_creatIns,
			JTable jt_content, Vector<InspectionContent> creatInspectionNumber,
			JTextField jtf_money) {
		this.panel = panel;
		this.jt_creatIns = jt_creatIns;
		this.jt_content = jt_content;
		this.creatInspectionNumber = creatInspectionNumber;
		this.jtf_money = jtf_money;
	}

	public CreatInspectionAction(JTextField jtf_project, JTextField jtf_pname,
			JTable jt_creatIns, JTextField jtf_money,
			Vector<InspectionContent> creatInspectionNumber, JDialog jd,
			Object[] obj, JTable jt_inspection, JPanel jp_inspection) {
		this.jtf_project = jtf_project;
		this.jtf_pname = jtf_pname;
		this.jt_creatIns = jt_creatIns;
		this.jtf_money = jtf_money;
		this.creatInspectionNumber = creatInspectionNumber;
		this.dialog = jd;
		this.objects = obj;
		this.jt_inspection = jt_inspection;
		this.jp_inspection = jp_inspection;
	}

	public CreatInspectionAction(JTextField jField, JPanel panel, JTable table) {
		this.jField = jField;
		this.panel = panel;
		this.table = table;
	}

	public CreatInspectionAction(JTable table) {
		this.table = table;
	}

	public CreatInspectionAction(JTextField jField, String name) {
		this.jField = jField;
		this.name = name;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int row = table.rowAtPoint(e.getPoint());
		int col = table.columnAtPoint(e.getPoint());
		if (row > -1 && col > -1) {
			Object value = table.getValueAt(row, col);
			if (null != value && !"".equals(value))
				table.setToolTipText(value.toString());// 悬浮显示单元格内容
			else
				table.setToolTipText(null);// 关闭提示
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commandName = e.getActionCommand();
		// 界面查找按钮逻辑代码
		if ("search".equals(commandName)) {
			// >>>>>>>>>面板显示代码
			table.getSelectionModel().setSelectionInterval(0, 0);
			panel.setVisible(true);
			searchSysInspectionContent();
			// <<<<<<<<<面板显示代码
		} else if ("commit".equals(commandName)) {
			int flag = JOptionPane.showConfirmDialog(null, "确定提交?", "提交项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				commitInspectio();

			}
		} else if ("calloff".equals(commandName)) {
			int flag = JOptionPane.showConfirmDialog(null, "取消项目？", "取消项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				dialog.dispose();
			}
		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		if ("printInspec".equals(name)) {
			String content = jField.getText();
			if ("请输入检测内容".equals(content)) {
				jField.setText("");
				jField.setForeground(Color.BLACK);
			}
		} else if ("searchPanel".equals(name)) {
		} else if (name == null) {
			panel.setVisible(false);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if ("searchPanel".equals(name)) {
			panel.setVisible(false);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int count = e.getClickCount();
		if (count == 2) {
			addDataTabel();
		}

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

	/**
	 * 搜索检测内容线程类
	 */
	private void searchSysInspectionContent() {
		final String filedContent = jField.getText().replace(" ", "");
		if (null == filedContent || "".equals(filedContent)
				|| "请输入检测内容".equals(filedContent)) {
			JOptionPane.showMessageDialog(null, "请输入检测内容", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		} else {

			new SwingWorker<List<SysInspectionContent>, Void>() {

				@Override
				protected List<SysInspectionContent> doInBackground()
						throws Exception {
					return new SysInspectionContenServiceImpl()
							.querySysInspectionContentByContentName(filedContent);
				}

				protected void done() {
					List<SysInspectionContent> contentList = null;
					try {
						contentList = get();
						if (null != contentList && contentList.size() > 0) {
							DefaultTableModel dtm = (DefaultTableModel) table
									.getModel();
							dtm.getDataVector().clear();
							for (SysInspectionContent sysInspectionContent : contentList) {
								Vector<Object> vector = new Vector<Object>();
								vector.add(sysInspectionContent.getId());
								vector.add(sysInspectionContent
										.getInspectionContentName());
								vector.add(sysInspectionContent
										.getSampleBasis().getBasisFileName());
								vector.add(sysInspectionContent
										.getSampleQuantityRange());
								vector.add(sysInspectionContent
										.getSampleQuantity());
								vector.add(sysInspectionContent.getQuoteBasis()
										.getBasisFileName());
								vector.add(sysInspectionContent
										.getSingleQuantityRange());
								vector.add(sysInspectionContent
										.getSingleObjectQuantity());
								vector.add(sysInspectionContent.getChargeUnit());
								vector.add(sysInspectionContent
										.getChargeStandard());
								vector.add(sysInspectionContent
										.getSampleBasis().getId());
								vector.add(sysInspectionContent.getQuoteBasis()
										.getId());
								vector.add(sysInspectionContent
										.getChargeStandardUnit());
								dtm.addRow(vector);

							}
							table.setRowSelectionInterval(0, 0);
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

	/**
	 * 把搜索数据添加到展示面板
	 */
	public void addDataTabel() {
		boolean isTrue = false;
		int jt_content_id = (int) jt_content.getValueAt(
				jt_content.getSelectedRow(), 0);
		int jt_creatIns_id = 0;
		int i = 0;
		int count = jt_creatIns.getModel().getRowCount();
		for (i = 0; i < count; i++) {
			jt_creatIns_id = (int) jt_creatIns.getValueAt(i, 0);
			if (jt_content_id == jt_creatIns_id) {
				isTrue = true;
				break;
			}
		}

		if (isTrue) {
			panel.setVisible(false);
			jt_creatIns.setRowSelectionInterval(i, i);
		} else {
			DefaultTableModel creatIns = (DefaultTableModel) jt_creatIns
					.getModel();
			int maxNumber = creatIns.getRowCount() + 1;
			Vector<Object> vector = new Vector<Object>();
			int sample_quantity = (int) jt_content.getValueAt(
					jt_content.getSelectedRow(), 4);
			int single_object_quantity = (int) jt_content.getValueAt(
					jt_content.getSelectedRow(), 7);
			int charge_standard = (int) jt_content.getValueAt(
					jt_content.getSelectedRow(), 9);
			vector.add(jt_content.getValueAt(jt_content.getSelectedRow(), 0));
			vector.add(maxNumber);
			vector.add(jt_content.getValueAt(jt_content.getSelectedRow(), 1));
			vector.add(jt_content.getValueAt(jt_content.getSelectedRow(), 2));
			vector.add(jt_content.getValueAt(jt_content.getSelectedRow(), 6));
			vector.add(single_object_quantity);
			vector.add(sample_quantity);
			vector.add(charge_standard);
			double oneICAmount = sample_quantity * single_object_quantity
					* charge_standard;
			vector.add(oneICAmount);
			panel.setVisible(false);
			icAmount += oneICAmount;
			jtf_money.setText(String.valueOf(icAmount));
			creatIns.addRow(vector);
			InspectionContent inspectionContent = new InspectionContent();
			// "序号"0, "检测内容1", "抽样依据2", "抽样数量范围3", "抽样数量4",
			// "报价依据5", "单个检测数量范围6", "单个检测对象数量7", "计费单位8",
			// "收费标准9""抽样依据id 10","报价依据id 11"
			inspectionContent.setSourceId((int) jt_content.getValueAt(
					jt_content.getSelectedRow(), 0));
			inspectionContent.setInspectionContentName((String) jt_content
					.getValueAt(jt_content.getSelectedRow(), 1));
			inspectionContent.setSampleQuantity((int) jt_content.getValueAt(
					jt_content.getSelectedRow(), 4));
			inspectionContent.setSampleQuantityRange((String) jt_content
					.getValueAt(jt_content.getSelectedRow(), 3));
			inspectionContent.setSampleBasisId((int) jt_content.getValueAt(
					jt_content.getSelectedRow(), 10));
			inspectionContent.setSingleObjectQuantity((int) jt_content
					.getValueAt(jt_content.getSelectedRow(), 7));
			inspectionContent.setSingleQuantityRange((String) jt_content
					.getValueAt(jt_content.getSelectedRow(), 6));
			inspectionContent.setChargeUnit((String) jt_content.getValueAt(
					jt_content.getSelectedRow(), 8));
			if (null != jt_content.getValueAt(jt_content.getSelectedRow(), 9)) {
				inspectionContent.setChargeStandard((int) jt_content
						.getValueAt(jt_content.getSelectedRow(), 9));
			} else {
				inspectionContent.setChargeStandard(0);
			}

			inspectionContent.setChargeStandardUnit((String) jt_content
					.getValueAt(jt_content.getSelectedRow(), 12));
			inspectionContent.setQuoteBasisId((int) jt_content.getValueAt(
					jt_content.getSelectedRow(), 11));
			creatInspectionNumber.add(inspectionContent);

			jt_creatIns.setRowSelectionInterval(count, count);

		}

	}

	/**
	 * 确认提交逻辑代码
	 */
	private void commitInspectio() {
		final InspectionBatch insBatch = new InspectionBatch();
		String inspectionBatchName = jtf_pname.getText().replace(" ", "");
		if (null == inspectionBatchName || "".equals(inspectionBatchName)) {
			JOptionPane.showMessageDialog(null, "请输入检验批名称", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		} else {
			insBatch.setInspectionBatchName(inspectionBatchName);
			insBatch.setProjectId(Integer.valueOf(jtf_project.getName()));
			// insBatch.setInspectionBatchAmount(Double.valueOf(jtf_money
			// .getText()));

			new SwingWorker<Map<String, Object>, Void>() {

				@Override
				protected Map<String, Object> doInBackground() throws Exception {
					Map<String, Object> inspectionMap = new HashMap<String, Object>();
					InspectionBatch inspectionBatch = null;
					if ((boolean) objects[0]) {
						inspectionBatch = new InspectionBatchServiceImpl()
								.queryInspectionBatchByMaxId(insBatch);
						inspectionMap.put("inspectionBatch", inspectionBatch);
					} else {
						inspectionBatch = (InspectionBatch) objects[1];
					}

					boolean isTrue = false;
					if (null != inspectionBatch) {
						for (InspectionContent inspectionContent : creatInspectionNumber) {
							inspectionContent.setBatchId(inspectionBatch
									.getId());
						}
						isTrue = new InspectionContentServiceImpl()
								.insertMultipleInspectionContent(creatInspectionNumber);
					}
					inspectionMap.put("inspectionContent", isTrue);

					return inspectionMap;
				}

				protected void done() {
					Map<String, Object> map = null;
					try {
						map = get();
						InspectionBatch inspectionBatch = (InspectionBatch) map
								.get("inspectionBatch");
						if (inspectionBatch == null) {
							JOptionPane.showMessageDialog(null, "提交失败，请重新尝试",
									"提示信息", JOptionPane.WARNING_MESSAGE);
						} else {
							boolean isTrue = (boolean) map
									.get("inspectionContent");
							if (isTrue) {
								JOptionPane.showMessageDialog(null, "提交成功",
										"提示信息", JOptionPane.WARNING_MESSAGE);
								JCheckBox checkBox = new JCheckBox();
								checkBox.setText(inspectionBatch
										.getInspectionBatchName());
								checkBox.setName(String.valueOf(inspectionBatch
										.getId()));
								jp_inspection.add(checkBox);
								jp_inspection.updateUI();
								// DefaultTableModel dtm = (DefaultTableModel)
								// jt_inspection
								// .getModel();
								//
								// Vector<Object> vector = new Vector<Object>();
								// vector.add(inspectionBatch.getId());
								// vector.add(inspectionBatch
								// .getInspectionBatchName());
								// vector.add(inspectionBatch
								// .getInspectionBatchAmount());
								// dtm.addRow(vector);
								dialog.dispose();
							} else {
								objects[0] = false;
								objects[1] = inspectionBatch;
								JOptionPane.showMessageDialog(null,
										"提交失败，请重新尝试", "提示信息",
										JOptionPane.WARNING_MESSAGE);
							}
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
}
