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
import java.util.Date;
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

public class DetailsOfContentFramess {

	public JDialog jDialog;
	private JPanel panel, panel_viw, panel_Basic;
	private JScrollPane scrollPaneBasic;
	private JTable tab_Basic;
	private JLabel label_0, label_1;
	private DefaultTableModel basicModel; 
	private Integer InspectionContentID;

	/**
	 * @wbp.parser.constructor
	 */
	public DetailsOfContentFramess() {
		initialize();
	}
	
	public DetailsOfContentFramess(Integer InspectionContentID){
		this.InspectionContentID = InspectionContentID;
		initialize();
	}

	public void initialize() {
		jDialog = new JDialog();
		jDialog.setBounds(0, 0, 472, 590);
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
		panel_viw.setBounds(0, 0, 466, 65);
		panel_viw.setLayout(null);
		panel.add(panel_viw);

		label_0 = new JLabel("检验内容详情");
		label_0.setFont(new Font("黑体", Font.BOLD, 14));
		label_0.setBounds(10, 10, 125, 23);
		panel_viw.add(label_0);

		label_1 = new JLabel("表中所有相关数据皆出自指定官方文件,表头宽度可手动调节");
		label_1.setBounds(20, 33, 359, 15);
		panel_viw.add(label_1);
		
		//基本信息
		panel_Basic = new JPanel();
		panel_Basic.setBounds(0, 93, 466, 469);
		panel.add(panel_Basic);
		
		
		//基本信息表格，左边内容为属性名称，自定义数据插入，右边数据从相应表格查询
		final Object contentColumnsName[] = {"属性", "值"};
		basicModel = new DefaultTableModel(null,
				contentColumnsName);
		panel_Basic.setLayout(new BorderLayout(0, 0));
				
				scrollPaneBasic = new JScrollPane();
				scrollPaneBasic.setBounds(0, 66, 466, 496);
				panel.add(scrollPaneBasic);
				tab_Basic = new MyTable(new int[] { 1, 2});
				//设置表头
				tab_Basic.setModel(basicModel);
				//设置表格行高
				tab_Basic.setRowHeight(25);
				tab_Basic.getColumnModel().getColumn(0).setPreferredWidth(0);
				tab_Basic.getColumnModel().getColumn(1).setPreferredWidth(280);
				// 设置表头不可移动
				tab_Basic.getTableHeader().setReorderingAllowed(false);
				scrollPaneBasic.setViewportView(tab_Basic);
//				tab_Basic.setEnabled(false);
				tab_Basic.setShowGrid(true);
		DefaultTableModel modelBasic = (DefaultTableModel) tab_Basic
				.getModel();
		final Object[] basic = {"基本信息","检验内容","所属行业","所属地点","抽样数量",
				"抽样数量范围","单个检验对象实施数量范围","计费单位","收费标准","总金额",
				"抽样依据","抽样依据文件号","抽样依据文件名","抽样依据具体内容","报价依据",
				"报价依据文件号","报价依据文件名","报价依据具体内容"};
		for(int i = 0 ; i < 18 ; i ++ ){
			Vector<Object> dataBasic = new Vector<Object>();
			dataBasic.add(basic[i]);
			modelBasic.addRow(dataBasic);
		}
		long start = 0;
		System.out.println(start = new Date().getTime());
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
					tab_Basic.setValueAt(quoteBasis.getBasisFileNumber(), 11, 1);
					tab_Basic.setValueAt(quoteBasis.getBasisFileName(), 12, 1);
					tab_Basic.setValueAt(quoteBasis.getBasisFileIndex(), 13, 1);
					tab_Basic.updateUI();
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
					tab_Basic.setValueAt(sampleBasis.getBasisFileNumber(), 15, 1);
					tab_Basic.setValueAt(sampleBasis.getBasisFileName(), 16, 1);
					tab_Basic.setValueAt(sampleBasis.getBasisFileIndex(), 17, 1);
					tab_Basic.updateUI();
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
					tab_Basic.setValueAt(industry.getIndustryName(), 2, 1);
					tab_Basic.updateUI();
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
					tab_Basic.setValueAt(address.getMergerName(), 3, 1);
					tab_Basic.updateUI();
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
				.selectInspectionContentByInspectionContentID(InspectionContentID);
			}
			@Override
			protected void done() {
				InspectionContent InspectionContent = null;
				try {
					InspectionContent = get();
					tab_Basic.setValueAt(InspectionContent.getInspectionContentName(), 1, 1);
					tab_Basic.setValueAt(InspectionContent.getSampleQuantity(), 4, 1);
					tab_Basic.setValueAt(InspectionContent.getSampleQuantityRange(), 5, 1);
					tab_Basic.setValueAt(InspectionContent.getSingleObjectQuantity(), 6, 1);
					tab_Basic.setValueAt(InspectionContent.getChargeUnit(), 7, 1);
					tab_Basic.setValueAt(InspectionContent.getChargeStandard(), 8, 1);
					tab_Basic.setValueAt(InspectionContent.getInspectionContentAmount(), 9, 1);
					tab_Basic.updateUI();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();
		
		long end = 0;
		System.out.println(end = new Date().getTime());
		
		System.out.println("耗时："+ (end-start) +"ms");
	}
}
