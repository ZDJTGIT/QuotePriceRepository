package com.zhongda.quote.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.zhongda.quote.action.CreateProjectFrameAction;
import com.zhongda.quote.model.Address;
import com.zhongda.quote.model.Industry;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.service.impl.AddressServiceImpl;
import com.zhongda.quote.service.impl.IndustryServiceImpl;
import com.zhongda.quote.utils.SkinUtil;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;

/**
 *
 * <p>
 * 新建项目类UI
 * </p>
 *
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月9日
 */
public class CreateProjectFrame {

	public JDialog dialog;
	private JPanel jPanel;
	private JpaneColorAndPhoto jpanel_up;
	private JLabel jlb_jpup_1;
	private JLabel jlb_jpup_2;
	private JLabel label;
	private JTextField jtf_task;
	private JLabel lblNewLabel;
	private JTextField jtf_pname;
	private JLabel lblNewLabel_1;
	private JLabel label_1;
	private JTextField jtf_pp;
	private JLabel label_2;
	private JTextField jtf_po;
	private JLabel label_3;
	private JSeparator separator;
	private JButton jbt_yes;
	private JButton jbt_no;
	private JLabel lblNewLabel_3;
	private JComboBox<Object> jcb_jyp;
	private JPanel jp_jyp;
	private JComboBox<Address> jcb_province;
	private JComboBox<Address> jcb_city;
	private JComboBox<Address> jcb_county;

	private JLabel ble_4;
	private JComboBox<Industry> jcb_industry;
	private JTable jtb_task;
	private QuoteProject quoteProject = null;
	private Object[] objects = { true, quoteProject };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProjectFrame createP = new CreateProjectFrame();
					createP.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateProjectFrame() {

		init();

	}

	public CreateProjectFrame(JTable jt) {
		this.jtb_task = jt;
		init();

	}

	public void init() {
		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setBounds(0, 0, 500, 550);
		dialog.setModal(true);// 此窗口至于前端
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.getContentPane().setLayout(new BorderLayout(0, 0));
		dialog.setTitle("中大检测");
		dialog.setIconImage(dialog.getToolkit().getImage("images/zdLogo1.png"));

		jPanel = new JPanel();
		dialog.getContentPane().add(jPanel, BorderLayout.CENTER);
		jPanel.setLayout(null);

		jpanel_up = new JpaneColorAndPhoto("images/bookpen.png", 430, 2, 48, 48);
		jpanel_up.setBounds(0, 0, 494, 52);
		jPanel.add(jpanel_up);
		jpanel_up.setLayout(null);

		jlb_jpup_1 = new JLabel("新建项目");
		jlb_jpup_1.setBounds(10, 10, 77, 15);
		jlb_jpup_1.setFont(new Font("黑体", 1, 15));
		jpanel_up.add(jlb_jpup_1);

		jlb_jpup_2 = new JLabel("填写项目信息");
		jlb_jpup_2.setBounds(33, 25, 84, 15);
		jpanel_up.add(jlb_jpup_2);

		label = new JLabel("所属任务");
		label.setBounds(26, 62, 54, 15);
		jPanel.add(label);

		jtf_task = new JTextField();
		jtf_task.setBounds(26, 80, 445, 25);
		jtf_task.setEnabled(false);
		int row = jtb_task.getSelectedRow();
		String string = (String) jtb_task.getValueAt(row, 2);
		jtf_task.setText(string);
		jtf_task.setName(String.valueOf((int) jtb_task.getValueAt(row, 0)));
		jPanel.add(jtf_task);
		jtf_task.setColumns(10);

		lblNewLabel = new JLabel("项目名称");
		lblNewLabel.setBounds(26, 110, 54, 15);
		jPanel.add(lblNewLabel);

		jtf_pname = new JTextField();
		jtf_pname.setBounds(26, 128, 445, 25);
		jPanel.add(jtf_pname);
		jtf_pname.setColumns(10);

		ble_4 = new JLabel("所属行业（请选择您新建任务涉及行业）");
		ble_4.setBounds(26, 155, 216, 20);
		jPanel.add(ble_4);

		jcb_industry = new JComboBox<Industry>();
		jcb_industry.setFont(new Font("新宋体", 0, 15));
		jcb_industry.setBounds(26, 175, 207, 25);
		jPanel.add(jcb_industry);

		lblNewLabel_1 = new JLabel("项目所属地点");
		lblNewLabel_1.setBounds(26, 200, 89, 20);
		jPanel.add(lblNewLabel_1);

		jcb_province = new JComboBox<Address>();
		jcb_province.setBounds(26, 220, 145, 21);
		jPanel.add(jcb_province);

		jcb_city = new JComboBox<Address>();
		jcb_city.setBounds(177, 220, 145, 21);
		jPanel.add(jcb_city);

		jcb_county = new JComboBox<Address>();
		jcb_county.setBounds(326, 220, 145, 21);
		jPanel.add(jcb_county);

		// lblNewLabel_2 = new JLabel("报价方法");
		// lblNewLabel_2.setBounds(26, 211, 54, 15);
		// jPanel.add(lblNewLabel_2);
		//
		// comboBox = new JComboBox();
		// comboBox.setBounds(26, 231, 445, 25);
		// jPanel.add(comboBox);

		label_1 = new JLabel("项目报价");
		label_1.setBounds(26, 376, 54, 15);
		jPanel.add(label_1);

		jtf_pp = new JTextField();
		jtf_pp.setBounds(26, 395, 445, 25);
		jtf_pp.setEnabled(false);
		jPanel.add(jtf_pp);
		jtf_pp.setColumns(10);

		label_2 = new JLabel("其他费用报价");
		label_2.setBounds(26, 422, 101, 15);
		jPanel.add(label_2);

		jtf_po = new JTextField();
		jtf_po.setBounds(26, 440, 445, 25);
		jtf_po.setEnabled(false);
		jPanel.add(jtf_po);
		jtf_po.setColumns(10);

		label_3 = new JLabel("中大检测");
		label_3.setBounds(4, 468, 54, 23);
		jPanel.add(label_3);

		separator = new JSeparator();
		separator.setBounds(55, 480, 435, 2);
		jPanel.add(separator);

		jbt_yes = new JButton("确认");
		jbt_yes.setFocusPainted(false);
		jbt_yes.setActionCommand("commit");
		jbt_yes.setBounds(282, 490, 93, 23);
		jPanel.add(jbt_yes);

		jbt_no = new JButton("取消");
		jbt_no.setFocusPainted(false);
		jbt_no.setActionCommand("calloff");
		jbt_no.addActionListener(new CreateProjectFrameAction(dialog));
		jbt_no.setBounds(378, 490, 93, 23);
		jPanel.add(jbt_no);

		lblNewLabel_3 = new JLabel("创建检测批");
		lblNewLabel_3.setBounds(26, 250, 74, 21);
		jPanel.add(lblNewLabel_3);

		String[] strings = { "请选择检验批或者新建检验批", "新建检验批" };
		jcb_jyp = new JComboBox<Object>(strings);
		jcb_jyp.setBounds(110, 250, 361, 21);
		jPanel.add(jcb_jyp);

		jp_jyp = new JPanel();
		jp_jyp.setBounds(26, 275, 445, 95);
		jp_jyp.setBorder(BorderFactory.createTitledBorder(null, "检测批",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("宋体", 0, 14)));// 设置边框字体
		jPanel.add(jp_jyp);
		jp_jyp.setLayout(new GridLayout(3, 3));

		// 生成该窗口时启动任务线程从数据库加载初始化数据(所有行业的数据)
		new SwingWorker<List<Industry>, Industry>() {

			@Override
			protected List<Industry> doInBackground() throws Exception {
				// 从数据库获取行业数据
				return new IndustryServiceImpl().queryAllIndustry();
			}

			@Override
			protected void done() {
				List<Industry> industryList;
				try {
					industryList = get();
					Vector<Industry> model = new Vector<Industry>();
					// 将数据添加到comboBox
					for (Industry industry : industryList) {
						model.addElement(industry);
					}
					ComboBoxModel<Industry> comboBoxModel = new DefaultComboBoxModel<Industry>(
							model);
					jcb_industry.setModel(comboBoxModel);
					// 提供自定义渲染类，实现键值绑定
					jcb_industry.setRenderer(new DefaultListCellRenderer() {

						private static final long serialVersionUID = 1L;

						public Component getListCellRendererComponent(
								JList<?> list, Object value, int index,
								boolean isSelected, boolean cellHasFocus) {
							super.getListCellRendererComponent(list, value,
									index, isSelected, cellHasFocus);
							if (value != null) {
								Industry industry = (Industry) value;
								// 将行业名称填入显示列表
								setText(industry.getIndustryName());
							}
							return this;
						};
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();

		// 生成该窗口时启动任务线程从数据库加载初始化数据(所有省的数据,以及默认选中省后的所有市的数据和默认选中市后所有区的数据)
		new SwingWorker<Map<String, List<Address>>, Void>() {

			@Override
			protected Map<String, List<Address>> doInBackground()
					throws Exception {
				// 从数据库获取所有省的数据,以及默认选中省后的所有市的数据和默认选中市后所有区的数据
				return new AddressServiceImpl()
						.queryAllProvinceAndCityCountyByParent();
			}

			@Override
			protected void done() {
				Map<String, List<Address>> addressMap;
				try {
					addressMap = get();
					List<Address> provinceList = addressMap.get("provinceList");
					// 填充省数据到省的ComboBox
					initAddressComboBoxDataDisplay(jcb_province, provinceList);
					List<Address> cityList = addressMap.get("cityList");
					// 填充市数据到市的ComboBox
					initAddressComboBoxDataDisplay(jcb_city, cityList);
					List<Address> countyList = addressMap.get("countyList");
					// 填充区数据到区的ComboBox
					initAddressComboBoxDataDisplay(jcb_county, countyList);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}

			/**
			 * 填充地址数据到地址的ComboBox
			 *
			 * @param jcb_address
			 *            地址ComboBox
			 * @param addressList
			 *            地址数据
			 */
			private void initAddressComboBoxDataDisplay(
					JComboBox<Address> jcb_address, List<Address> addressList) {
				Vector<Address> model = new Vector<Address>();
				// 将数据添加到comboBox
				for (Address address : addressList) {
					model.addElement(address);
				}
				ComboBoxModel<Address> comboBoxModel = new DefaultComboBoxModel<Address>(
						model);
				jcb_address.setModel(comboBoxModel);
				// 提供自定义渲染类，实现键值绑定
				jcb_address.setRenderer(new DefaultListCellRenderer() {

					private static final long serialVersionUID = 1L;

					public Component getListCellRendererComponent(
							JList<?> list, Object value, int index,
							boolean isSelected, boolean cellHasFocus) {
						super.getListCellRendererComponent(list, value, index,
								isSelected, cellHasFocus);
						if (value != null) {
							Address address = (Address) value;
							// 将省名称填入显示列表
							setText(address.getName());
						}
						return this;
					};
				});
			}
		}.execute();

		CreateProjectFrameAction createProjectFrameAction = new CreateProjectFrameAction(
				jcb_province, jcb_city, jcb_county);
		// 省的下拉列表选项选中后触发事件
		jcb_province.addItemListener(createProjectFrameAction);
		// 市的下拉列表选项选中后触发事件
		jcb_city.addItemListener(createProjectFrameAction);
		// 创建检验批组件
		jcb_jyp.addItemListener(new CreateProjectFrameAction(jtf_task, jcb_jyp,
				jtf_pname, jcb_industry, jcb_province, jcb_city, jcb_county,
				objects, jp_jyp));
		// 提交按钮
		jbt_yes.addActionListener(new CreateProjectFrameAction(dialog, jp_jyp,
				jtf_pp, jtf_po));

	}
}
