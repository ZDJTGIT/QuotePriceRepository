package com.zhongda.quote.action;

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
import javax.swing.SwingWorker;

import com.zhongda.quote.model.Address;
import com.zhongda.quote.service.impl.AddressServiceImpl;

public class CreateProjectFrameAction implements ItemListener, ActionListener {

	private JComboBox<Address> jcb_province;
	private JComboBox<Address> jcb_city;
	private JComboBox<Address> jcb_county;
	private JDialog dialog;

	public CreateProjectFrameAction() {
	}

	public CreateProjectFrameAction(JDialog jd) {
		this.dialog = jd;
	}

	public CreateProjectFrameAction(JComboBox<Address> jcb_province,
			JComboBox<Address> jcb_city, JComboBox<Address> jcb_county) {
		this.jcb_province = jcb_province;
		this.jcb_city = jcb_city;
		this.jcb_county = jcb_county;
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
				dialog.dispose();
			}
		} else if ("calloff".equals(name)) {
			int flag = JOptionPane.showConfirmDialog(null, "取消项目？", "取消项目",
					JOptionPane.OK_OPTION);
			if (flag == JOptionPane.OK_OPTION) {
				dialog.dispose();
			}
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

}
