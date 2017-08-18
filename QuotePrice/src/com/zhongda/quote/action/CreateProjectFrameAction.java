package com.zhongda.quote.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.zhongda.quote.model.Address;
import com.zhongda.quote.model.Industry;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.service.impl.AddressServiceImpl;
import com.zhongda.quote.service.impl.QuoteProjectServiceImpl;
import com.zhongda.quote.utils.FrameGoUtils;

public class CreateProjectFrameAction implements ItemListener, ActionListener {

	private JComboBox<Address> jcb_province;
	private JComboBox<Address> jcb_city;
	private JComboBox<Address> jcb_county;
	private JDialog dialog;
	private JComboBox<Object> jcb_jyp;
	private JTextField projectName;
	private JComboBox<Industry> jcb_industry;
	private JTextField jtf_task;
	private Object[] objects;
	private JPanel jp_inspection;
	private JTextField jtf_pp;
	private JTextField jtf_po;

	public CreateProjectFrameAction() {
	}

	public CreateProjectFrameAction(JDialog jd) {
		this.dialog = jd;
	}

	public CreateProjectFrameAction(JDialog jd, JPanel jp_jyp,
			JTextField jtf_pp, JTextField jtf_po) {
		this.dialog = jd;
		this.jp_inspection = jp_jyp;
		this.jtf_pp = jtf_pp;
		this.jtf_po = jtf_po;
	}

	public CreateProjectFrameAction(JComboBox<Address> jcb_province,
			JComboBox<Address> jcb_city, JComboBox<Address> jcb_county) {
		this.jcb_province = jcb_province;
		this.jcb_city = jcb_city;
		this.jcb_county = jcb_county;
	}

	public CreateProjectFrameAction(JTextField jtf_task,
			JComboBox<Object> jcb_jyp, JTextField projectName,
			JComboBox<Industry> jcb_industry, JComboBox<Address> jcb_province,
			JComboBox<Address> jcb_city, JComboBox<Address> jcb_county,
			Object[] objects, JPanel jp_inspection) {
		this.jtf_task = jtf_task;
		this.jcb_jyp = jcb_jyp;
		this.projectName = projectName;
		this.jcb_industry = jcb_industry;
		this.jcb_province = jcb_province;
		this.jcb_city = jcb_city;
		this.jcb_county = jcb_county;
		this.objects = objects;
		this.jp_inspection = jp_inspection;

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
			} else if (jcb_jyp == source) {

				creatInspection();
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		if ("commit".equals(name)) {
			int flag = JOptionPane.showConfirmDialog(null, "确定提交?", "提交项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				commitProject();

			}
		} else if ("calloff".equals(name)) {
			int flag = JOptionPane.showConfirmDialog(null, "取消项目？", "取消项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				dialog.dispose();
			}
		}

	}

	private void commitProject() {
		Component[] component = jp_inspection.getComponents();
		String project_amount = jtf_pp.getText();
		String other_amount = jtf_po.getText();

		if (component.length > 0) {
			// if (null == project_amount || "".equals(project_amount)) {
			// JOptionPane.showMessageDialog(null, "请选择检验批", "提示信息",
			// JOptionPane.WARNING_MESSAGE);
			// } else {
			dialog.dispose();
			// }
		} else {
			JOptionPane.showMessageDialog(null, "请创建检验批", "提示信息",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// 对省市区三级联动选项变化事件的处理，联动查询出下级地址渲染到界面下拉列表
	private void provinceCityCountyLinkage(ItemEvent e,
			JComboBox<Address> jcb_CityOrCounty) {
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

	/**
	 * 创建检验批
	 */
	private void creatInspection() {
		if ("新建检验批".equals((String) jcb_jyp.getSelectedItem())) {
			jcb_jyp.setSelectedIndex(0);
			if ((boolean) objects[0]) {
				String content = projectName.getText();
				content = content.replace(" ", "");
				if (null == content || "".equals(content)) {
					JOptionPane.showMessageDialog(null, "请先填写项目名称", "提示信息",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int flag = JOptionPane.showConfirmDialog(null,
							"确认后将无法修改以上所有信息！", "删除报价任务", JOptionPane.OK_OPTION);
					if (flag == JOptionPane.OK_OPTION) {
						projectName.setEnabled(false);
						jcb_industry.setEnabled(false);
						jcb_province.setEnabled(false);
						jcb_city.setEnabled(false);
						jcb_county.setEnabled(false);
						int taskId = Integer.valueOf(jtf_task.getName());
						String projectName = this.projectName.getText();
						int industryId = ((Industry) jcb_industry
								.getSelectedItem()).getId();
						int addressPid = ((Address) jcb_province
								.getSelectedItem()).getId();
						int addressId = ((Address) jcb_county.getSelectedItem())
								.getId();
						QuoteProject quoteProject = new QuoteProject(
								projectName, taskId, industryId, addressPid,
								addressId);
						new SwingWorker<QuoteProject, Void>() {

							@Override
							protected QuoteProject doInBackground()
									throws Exception {
								QuoteProject quoProject = null;
								if ((boolean) objects[0]) {
									quoProject = new QuoteProjectServiceImpl()
											.createProject(quoteProject);
								} else {
									quoProject = (QuoteProject) objects[1];
								}
								return quoProject;
							}

							protected void done() {
								QuoteProject quoteProject = null;
								try {
									quoteProject = get();
									if (quoteProject != null) {
										objects[0] = false;
										objects[1] = quoteProject;
										FrameGoUtils.creatInspection(
												quoteProject, jp_inspection);
									} else {

										JOptionPane.showMessageDialog(null,
												"创建失败，请重新尝试", "提示信息",
												JOptionPane.WARNING_MESSAGE);
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
			} else {
				FrameGoUtils.creatInspection((QuoteProject) objects[1],
						jp_inspection);
			}
		}
	}
}
