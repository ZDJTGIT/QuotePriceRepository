package com.zhongda.quote.view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import com.zhongda.quote.model.Address;
import com.zhongda.quote.model.Industry;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.QuoteBasis;
import com.zhongda.quote.model.SampleBasis;
import com.zhongda.quote.service.impl.AddressServiceImpl;
import com.zhongda.quote.service.impl.IndustryServiceImpl;
import com.zhongda.quote.service.impl.InspectionContentServiceImpl;
import com.zhongda.quote.service.impl.QuoteBasisServiceImpl;
import com.zhongda.quote.service.impl.SampleBasisServiceImpl;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;
import com.zhongda.quote.view.uiutils.MyTable;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.table.DefaultTableModel;

/**
 * 查看详细检验内容界面
 *<p>
 *
 *<p>
 * @author 研发中心-Mikepolite<1011592269@qq.com>
 * @sine 2017年8月23日
 */

public class DetailsOfContentFrame {

	public JDialog jDialog;
	private JPanel panel, panel_viw, panel_Basic, panel_Sampling, panel_Quotes;
	private JScrollPane scrollPaneBasic, scrollPaneSampling, scrollPaneQuotes;
	private JTable tab_Basic, tab_Sampling, tab_Quotes;
	private JLabel label_0, label_1;
	private JLabel label_4;
	private JLabel label_2;
	private JLabel label_3;
	private DefaultTableModel basicModel, sampingModel, quotesModel;
	private Integer InspectionContentID;

	/**
	 * @wbp.parser.constructor
	 */
	public DetailsOfContentFrame() {
		initialize();
	}

	public DetailsOfContentFrame(Integer InspectionContentID){
		this.InspectionContentID = InspectionContentID;
		initialize();
	}

	public void initialize() {
		jDialog = new JDialog();
		jDialog.setBounds(0, 0, 472, 588);
		jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ImageIcon icon = new ImageIcon("images\\zdLogo1.png");
		jDialog.setTitle("中大检测");
		jDialog.setResizable(false);
		jDialog.setLocationRelativeTo(null);// 设置界面居中
		jDialog.setIconImage(icon.getImage());
		jDialog.setModal(true);
		jDialog.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		jDialog.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		panel_viw = new JpaneColorAndPhoto("images/bookpen.png", 370, 6, 48, 48);
		panel_viw.setBounds(0, 0, 466, 57);
		panel_viw.setLayout(null);
		panel.add(panel_viw);

		label_0 = new JLabel("检验内容详情");
		label_0.setFont(new Font("黑体", Font.BOLD, 14));
		label_0.setBounds(10, 3, 125, 25);
		panel_viw.add(label_0);

		label_1 = new JLabel("表中所有相关数据皆出自指定官方文件,表头宽度可手动调节");
		label_1.setBounds(20, 25, 359, 15);
		panel_viw.add(label_1);

		label_4 = new JLabel("基本信息表");
		label_4.setFont(new Font("宋体", Font.BOLD, 14));
		label_4.setBounds(10, 65, 132, 15);
		panel.add(label_4);

		label_2 = new JLabel("抽样依据表");
		label_2.setFont(new Font("宋体", Font.BOLD, 14));
		label_2.setBounds(10, 260, 92, 15);
		panel.add(label_2);

		label_3 = new JLabel("报价依据表");
		label_3.setFont(new Font("宋体", Font.BOLD, 14));
		label_3.setBounds(10, 414, 120, 15);
		panel.add(label_3);

		//基本信息
		panel_Basic = new JPanel();
		panel_Basic.setBounds(0, 90, 466, 160);
		panel.add(panel_Basic);

		//抽样依据
		panel_Sampling = new JPanel();
		panel_Sampling.setBounds(0, 284, 466, 120);
		panel.add(panel_Sampling);

		//报价依据
		panel_Quotes = new JPanel();
		panel_Quotes.setBounds(0, 440, 466, 120);
		panel.add(panel_Quotes);


		//基本信息表格，表格条例为1+9，左边内容为属性名称，自定义数据插入，右边数据从相应表格查询
		final Object contentColumnsName[] = {"属性", "值"};
		basicModel = new DefaultTableModel(null,
				contentColumnsName);
		sampingModel = new DefaultTableModel(null,
				contentColumnsName);
		quotesModel = new DefaultTableModel(null,
				contentColumnsName);

		scrollPaneBasic = new JScrollPane();
		tab_Basic = new MyTable(new int[] { 1, 2});
		//设置表头
		tab_Basic.setModel(basicModel);
		//设置表格行高
		tab_Basic.setRowHeight(24);
		tab_Basic.getColumnModel().getColumn(0).setPreferredWidth(0);
		tab_Basic.getColumnModel().getColumn(1).setPreferredWidth(280);
		// 设置表头不可移动
		tab_Basic.getTableHeader().setReorderingAllowed(false);
//		tab_Basic.setPreferredScrollableViewportSize(30);
		panel_Basic.setLayout(new BorderLayout(0, 0));
		scrollPaneBasic.setViewportView(tab_Basic);
		panel_Basic.add(scrollPaneBasic, BorderLayout.CENTER);
		DefaultTableModel modelBasic = (DefaultTableModel) tab_Basic
				.getModel();
		Object[] basic = {"检验内容","所属行业","所属地点","抽样数量",
				"抽样数量范围","单个检验对象实施数量范围","计费单位","收费标准","总金额"};
		for(int i = 0 ; i < 9 ; i ++ ){
			Vector<Object> dataBasic = new Vector<Object>();
			dataBasic.add(basic[i]);
			modelBasic.addRow(dataBasic);
		}

		//抽样依据表格，表格条例为1+3，左边内容为属性名称，自定义数据插入，右边数据从相应表格查询
		scrollPaneSampling = new JScrollPane();
		tab_Sampling = new MyTable(new int[] { 1, 2, 3});
		tab_Sampling.setModel(sampingModel);
		tab_Sampling.getColumnModel().getColumn(0).setPreferredWidth(0);
		tab_Sampling.getColumnModel().getColumn(1).setPreferredWidth(280);
		//设置表格行高
		tab_Sampling.setRowHeight(24);
		// 设置表头不可移动
		tab_Sampling.getTableHeader().setReorderingAllowed(false);
		panel_Sampling.setLayout(new BorderLayout(0, 0));
		scrollPaneSampling.setViewportView(tab_Sampling);
		panel_Sampling.add(scrollPaneSampling, BorderLayout.CENTER);
		DefaultTableModel modelSampling = (DefaultTableModel)tab_Sampling
				.getModel();
		Object[] Sampling = {"依据文件号","依据文件名","依据详情"};
		for(int i = 0 ; i < 3 ; i ++ ){
			Vector<Object> dataSampling = new Vector<Object>();
			dataSampling.add(Sampling[i]);
			modelSampling.addRow(dataSampling);
		}

		//报价依据表格，表格条例为1+3，左边内容为属性名称，自定义数据插入，右边数据从相应表格查询
		scrollPaneQuotes = new JScrollPane();
		tab_Quotes = new MyTable(new int[] { 1, 2, 3});
		tab_Quotes.setModel(quotesModel);
		//设置表格行高
		tab_Quotes.setRowHeight(24);
		// 设置表头不可移动
		tab_Quotes.setShowGrid(true);
		tab_Quotes.getColumnModel().getColumn(0).setPreferredWidth(0);
		tab_Quotes.getColumnModel().getColumn(1).setPreferredWidth(280);
		tab_Quotes.getTableHeader().setReorderingAllowed(false);
		panel_Quotes.setLayout(new BorderLayout(0, 0));
		scrollPaneQuotes.setViewportView(tab_Quotes);
		panel_Quotes.add(scrollPaneQuotes, BorderLayout.CENTER);
		DefaultTableModel modelQuotes = (DefaultTableModel) tab_Quotes
				.getModel();

		Object[] Quotes = {"依据文件号","依据文件名","依据详情"};
		for(int i = 0 ; i < 3 ; i ++ ){
			Vector<Object> dataQuotes = new Vector<Object>();
			dataQuotes.add(Quotes[i]);
			modelQuotes.addRow(dataQuotes);
		}

		new SwingWorker <QuoteBasis, QuoteBasis>(){
			@Override
			protected QuoteBasis doInBackground() throws Exception {
				return new QuoteBasisServiceImpl()
				.SelectQuoteBasisByInspectionContentID(InspectionContentID);
			}
			@Override
			protected void done() {
				QuoteBasis quoteBasis = null;
				try {
					quoteBasis = get();
					tab_Quotes.setValueAt(quoteBasis.getBasisFileNumber(), 0, 1);
					tab_Quotes.setValueAt(quoteBasis.getBasisFileName(), 1, 1);
					tab_Quotes.setValueAt(quoteBasis.getBasisFileIndex(), 2, 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();


		new SwingWorker <SampleBasis, SampleBasis>(){
			@Override
			protected SampleBasis doInBackground() throws Exception {
				return new SampleBasisServiceImpl()
				.SelectSampleBasisByInspectionContentID(InspectionContentID);
			}
			@Override
			protected void done() {
				SampleBasis sampleBasis = null;
				try {
					sampleBasis = get();
					tab_Sampling.setValueAt(sampleBasis.getBasisFileNumber(), 0, 1);
					tab_Sampling.setValueAt(sampleBasis.getBasisFileName(), 1, 1);
					tab_Sampling.setValueAt(sampleBasis.getBasisFileIndex(), 2, 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();


		new SwingWorker <Industry, Industry>(){
			@Override
			protected Industry doInBackground() throws Exception {
				return new IndustryServiceImpl()
				.selectIndustryByInspectionID(InspectionContentID);
			}
			@Override
			protected void done() {
				Industry industry = null;
				try {
					industry = get();
					tab_Basic.setValueAt(industry.getIndustryName(), 1, 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();

		new SwingWorker <Address, Address>(){
			@Override
			protected Address doInBackground() throws Exception {
				return new AddressServiceImpl()
				.selectAddressByInspectionID(InspectionContentID);
			}
			@Override
			protected void done() {
				Address address = null;
				try {
					address = get();
					tab_Basic.setValueAt(address.getMergerName(), 2, 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();

		new SwingWorker <InspectionContent, InspectionContent>(){
			@Override
			protected InspectionContent doInBackground() throws Exception {
				return new InspectionContentServiceImpl()
				.selectInspectionContentById(InspectionContentID);
			}
			@Override
			protected void done() {
				InspectionContent InspectionContent = null;
				try {
					InspectionContent = get();
					tab_Basic.setValueAt(InspectionContent.getInspectionContentName(), 0, 1);
					tab_Basic.setValueAt(InspectionContent.getSampleQuantity(), 3, 1);
					tab_Basic.setValueAt(InspectionContent.getSampleQuantityRange(), 4, 1);
					tab_Basic.setValueAt(InspectionContent.getSingleObjectQuantity(), 5, 1);
					tab_Basic.setValueAt(InspectionContent.getChargeUnit(), 6, 1);
					tab_Basic.setValueAt(InspectionContent.getChargeStandard(), 7, 1);
					tab_Basic.setValueAt(InspectionContent.getInspectionContentAmount(), 8, 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();

	}
}

