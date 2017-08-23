package com.zhongda.quote.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.service.impl.AddressServiceImpl;
import com.zhongda.quote.service.impl.QuoteProjectServiceImpl;
import com.zhongda.quote.utils.FrameGoUtils;
import com.zhongda.quote.utils.RenderDataUtils;

public class CreateProjectFrameAction implements ItemListener, ActionListener {

	private JComboBox<Industry> jcb_industry;
	private JComboBox<Address> jcb_province;
	private JComboBox<Address> jcb_city;
	private JComboBox<Address> jcb_county;
	private JDialog dialog;
	private JComboBox<Object> jcb_selectOrCreateBatch;
	private JTextField jtf_projectName;
	private JTextField jtf_taskName;
	private JPanel jp_batchItems;
	private JTextField jtf_projectAmount;
	private JTextField jtf_otherAmount;
	private JTable jt_quoteTask;
	private JTable jt_quoteProject;
	private JTable jt_inspectionBatch;
	private JTable jt_inspectionContent;
	private Map<String, Map<String,Object>> batchMap;

	public CreateProjectFrameAction() {
	}

	public CreateProjectFrameAction(JDialog dialog) {
		this.dialog = dialog;
	}

	public CreateProjectFrameAction(JComboBox<Address> jcb_province,
			JComboBox<Address> jcb_city, JComboBox<Address> jcb_county) {
		this.jcb_province = jcb_province;
		this.jcb_city = jcb_city;
		this.jcb_county = jcb_county;
	}

	public CreateProjectFrameAction(Map<String, Map<String,Object>> batchMap,
			JComboBox<Object> jcb_selectOrCreateBatch, JTextField jtf_projectName,
			JComboBox<Industry> jcb_industry, JComboBox<Address> jcb_province,
			JComboBox<Address> jcb_city, JComboBox<Address> jcb_county,
			JPanel jp_batchItems, JTextField jtf_projectAmount) {
		this.batchMap = batchMap;
		this.jcb_selectOrCreateBatch = jcb_selectOrCreateBatch;
		this.jtf_projectName = jtf_projectName;
		this.jcb_industry = jcb_industry;
		this.jcb_province = jcb_province;
		this.jcb_city = jcb_city;
		this.jcb_county = jcb_county;
		this.jp_batchItems = jp_batchItems;
		this.jtf_projectAmount = jtf_projectAmount;
	}

	public CreateProjectFrameAction(JTextField jtf_taskName, JTable jt_quoteTask,
			JTable jt_quoteProject, JTable jt_inspectionBatch,
			JTable jt_inspectionContent,JTextField jtf_projectName,
			Map<String, Map<String, Object>> batchMap, JPanel jp_batchItems,
			JTextField jtf_otherAmount, JTextField jtf_projectAmount,
			JComboBox<Industry> jcb_industry, JComboBox<Address> jcb_province,
			JComboBox<Address> jcb_county, JDialog dialog) {
		this.jtf_taskName =jtf_taskName;
		this.jt_quoteTask = jt_quoteTask;
		this.jt_quoteProject = jt_quoteProject;
		this.jt_inspectionBatch = jt_inspectionBatch;
		this.jt_inspectionContent = jt_inspectionContent;
		this.jtf_projectName = jtf_projectName;
		this.jp_batchItems = jp_batchItems;
		this.batchMap = batchMap;
		this.jtf_projectAmount = jtf_projectAmount;
		this.jtf_otherAmount = jtf_otherAmount;
		this.jcb_industry = jcb_industry;
		this.jcb_province = jcb_province;
		this.jcb_county = jcb_county;
		this.dialog = dialog;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// 只对监听到的选中动作进行处理
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Object source = e.getSource();
			// 如果是省的ComboBox产生事件
			if (jcb_province == source) {
				provinceCityCountyLinkage(e, jcb_city);
			} else if (jcb_city == source) { // 如果是市的ComboBox产生事件
				provinceCityCountyLinkage(e, jcb_county);
			} else if (jcb_selectOrCreateBatch == source) {
				creatInspectionBatch();
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("confirm".equals(command)) {
			int flag = JOptionPane.showConfirmDialog(null, "确定提交?", "提交项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				commitProject();

			}
		} else if ("cancel".equals(command)) {
			int flag = JOptionPane.showConfirmDialog(null, "取消项目？", "取消项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				dialog.dispose();
			}
		}

	}

	private void commitProject() {
		Component[] component = jp_batchItems.getComponents();
		if (component.length > 0) {
			// 获取当前选中任务ID
			int taskId = Integer.valueOf(jtf_taskName.getName());
			String projectName = jtf_projectName.getText();
			int industryId = ((Industry) jcb_industry
					.getSelectedItem()).getId();
			int addressPid = ((Address) jcb_province
					.getSelectedItem()).getId();
			int addressId = ((Address) jcb_county.getSelectedItem())
					.getId();
			double projectAmount = Double.parseDouble(jtf_projectAmount.getText());
			String otherAmountString = jtf_otherAmount.getText();
			double otherAmount = 0;
			if(null != otherAmountString && !"".equals(otherAmountString.trim())){
				otherAmount = Double.parseDouble(otherAmountString);
			}
			int row = jt_quoteTask.getSelectedRow();
			double taskAmountOld = (double)jt_quoteTask.getValueAt(row, 7);
			double taskAmount = taskAmountOld + projectAmount + otherAmount;
			final QuoteProject quoteProject = new QuoteProject(
					projectName, industryId, addressId, addressPid,
					taskId, projectAmount, otherAmount);
			new SwingWorker<Map<String, Object>, Void>(){

				@Override
				protected Map<String, Object> doInBackground() throws Exception {
					return new QuoteProjectServiceImpl().createProjectAndBatchAndContent(quoteProject, batchMap);
				}

				protected void done() {
					Map<String, Object> quoteMap = null;
					try {
						quoteMap = get();
						QuoteProject quoteProject = (QuoteProject) quoteMap.get("project");
						RenderDataUtils.renderSingleProjectData(jt_quoteProject, quoteProject);
						@SuppressWarnings("unchecked")
						List<InspectionBatch> batchList = (List<InspectionBatch>) quoteMap.get("batch");
						RenderDataUtils.renderBatchData(jt_inspectionBatch, batchList);
						@SuppressWarnings("unchecked")
						List<InspectionContent> contentList = (List<InspectionContent>) quoteMap.get("content");
						RenderDataUtils.renderContentData(jt_inspectionContent, contentList);

						jt_quoteTask.setValueAt(taskAmount, row, 7);
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}
			}.execute();
			dialog.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "请创建检验批", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * 打开创建检验批窗口
	 */
	private void creatInspectionBatch() {
		if ("新建检验批".equals((String) jcb_selectOrCreateBatch.getSelectedItem())) {
			jcb_selectOrCreateBatch.setSelectedIndex(0);// 点击新建检验批后选中第一个数据
			String projectName = jtf_projectName.getText();
			if (null == projectName || "".equals(projectName.trim())) {
				JOptionPane.showMessageDialog(null, "请先填写项目名称", "提示信息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				int componentCount = jp_batchItems.getComponentCount();
				if(componentCount == 0){
					int flag = JOptionPane.showConfirmDialog(null,
							"确认后将无法修改以上所有信息！", "创建检验批", JOptionPane.OK_OPTION);
					if (flag == JOptionPane.OK_OPTION) {
						jtf_projectName.setEnabled(false);
						jcb_industry.setEnabled(false);
						jcb_province.setEnabled(false);
						jcb_city.setEnabled(false);
						jcb_county.setEnabled(false);
						FrameGoUtils.createBatch(
								batchMap, jp_batchItems, projectName, jtf_projectAmount);
					}
				}else{
					FrameGoUtils.createBatch(
							batchMap, jp_batchItems, projectName, jtf_projectAmount);
				}
			}
		}
	}


	// 对省市区三级联动选项变化事件的处理，联动查询出下级地址渲染到界面下拉列表
	private void provinceCityCountyLinkage(ItemEvent e,
			final JComboBox<Address> jcb_CityOrCounty) {
		final Address address = (Address) e.getItem();
		// 选中省时加载该省(市)下的所有市(区或县)
		new SwingWorker<List<Address>, Address>() {

			@Override
			protected List<Address> doInBackground() throws Exception {
				return new AddressServiceImpl()
						.queryAllCityOrCountyByParent(address.getId());
			}

			protected void done() {
				List<Address> addressList = null;
				try {
					addressList = get();

					Vector<Address> model = new Vector<Address>();
					// 将数据添加到comboBox
					for (Address address : addressList) {
						model.addElement(address);
					}
					ComboBoxModel<Address> comboBoxModel = new DefaultComboBoxModel<Address>(
							model);
					jcb_CityOrCounty.setModel(comboBoxModel);
					Object item = jcb_CityOrCounty.getSelectedItem();
					// 如果市级地址选项超过1个，切换被选中的项，使其触发事件，加载随后的区级数据，如果是区级，无监听不作处理
					if (jcb_CityOrCounty.getItemCount() > 1) {
						jcb_CityOrCounty.setSelectedIndex(1);
						jcb_CityOrCounty.setSelectedItem(item);
					} else {
						// 如果市级地址选项只有1个，那么临时添加一个item，以来换切换选项触发事件，完成后删除该item
						jcb_CityOrCounty.addItem(address);
						jcb_CityOrCounty.setSelectedItem(address);
						jcb_CityOrCounty.setSelectedItem(item);
						jcb_CityOrCounty.removeItem(address);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			};

		}.execute();
	}
}
