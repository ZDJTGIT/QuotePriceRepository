package com.zhongda.quote.view;

import java.awt.Dimension;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import com.zhongda.quote.action.CreateInspectionBatchAction;
import com.zhongda.quote.utils.SkinUtil;
import com.zhongda.quote.view.uiutils.JpaneColorAndPhoto;

/**
*
* <p>
* 创建检验批窗口
* <p>
*
* @author 研发中心-Mikepolite<1011592269@qq.com>
* @sine 2017年8月12日
*/

public class CreateInspectionBatchFrame {

	private JDialog jDialog;
	private JPanel panel_subject;
	private JTextField textField_belong, textField_name, textField_offer,
			textField_search;
	private JLabel label_jypCreate, label_prompt, lable_belong, lable_name,
			label_Testingcontent, label_offer;
	private JButton Button_search, Button_yes, Button_no;
	private JSeparator partingline;
	private JTable table;
	private JScrollPane jsp;

	public static void main(String[] args) {
		CreateInspectionBatchFrame CreateInspectionLot = new CreateInspectionBatchFrame();
		CreateInspectionLot.jDialog.setVisible(true);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public CreateInspectionBatchFrame() {
		initialize();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		SkinUtil.setSkin(BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated);
		jDialog = new JDialog();
		jDialog.setTitle("中大检测-创建检验批");
		jDialog.setBounds(20, 20, 500, 442);
		ImageIcon jDialogIcon = new ImageIcon("images\\zdLogo1.png");
		jDialog.setIconImage(jDialogIcon.getImage());
		jDialog.setLocationRelativeTo(null);// 设置界面居中
		jDialog.setResizable(false);// 锁定窗口大小
		jDialog.getContentPane().setLayout(null);// 设置绝对布局
		jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 默认关闭为当前窗口

		panel_subject = new JPanel();
		panel_subject.setBounds(0, 52, 494, 362);
		panel_subject.setLayout(null);
		jDialog.getContentPane().add(panel_subject);

		// 设置图片格式
		JpaneColorAndPhoto panel_Minor = new JpaneColorAndPhoto(
				"images\\bookpen.png", 430, 2, 48, 48);
		panel_Minor.setBounds(0, 0, 494, 51);
		panel_Minor.setLayout(null);
		jDialog.getContentPane().add(panel_Minor);

		label_jypCreate = new JLabel("创建检验批");
		label_jypCreate.setFont(new Font("黑体", Font.PLAIN, 12));
		label_jypCreate.setBounds(20, 0, 86, 35);
		panel_Minor.add(label_jypCreate);

		label_prompt = new JLabel("请按提示填写相关内容");
		label_prompt.setBounds(30, 28, 164, 15);
		panel_Minor.add(label_prompt);

		lable_belong = new JLabel("检验批所属项目：");
		lable_belong.setBounds(24, 8, 109, 15);
		panel_subject.add(lable_belong);

		lable_name = new JLabel("检验批名称：");
		lable_name.setBounds(24, 62, 185, 15);
		panel_subject.add(lable_name);

		label_Testingcontent = new JLabel("检测内容：");
		label_Testingcontent.setBounds(24, 119, 99, 15);
		panel_subject.add(label_Testingcontent);

		label_offer = new JLabel("检验批报价：");
		label_offer.setBounds(24, 268, 99, 15);
		panel_subject.add(label_offer);

		textField_belong = new JTextField();
		textField_belong.setBounds(24, 31, 460, 24);
		panel_subject.add(textField_belong);
		textField_belong.setColumns(10);
		textField_belong.setEditable(false);// 设置该文本框不可编辑
		textField_belong.setText("水泥建材检测");// 由数据库自动生成所属项目

		textField_name = new JTextField();
		textField_name.setBounds(24, 85, 460, 24);
		panel_subject.add(textField_name);
		textField_name.setColumns(10);

		textField_offer = new JTextField();
		textField_offer.setBounds(21, 292, 463, 22);
		panel_subject.add(textField_offer);
		textField_offer.setColumns(10);
		textField_offer.setEditable(false);// 设置该文本框不可编辑
		textField_offer.setText("48888");// 由相关算法计算总金额

		textField_search = new JTextField();
		textField_search.setBounds(90, 117, 114, 24);
		panel_subject.add(textField_search);
		textField_search.setColumns(10);

		partingline = new JSeparator();
		partingline.setBounds(20, 325, 464, 2);
		panel_subject.add(partingline);

		Button_search = new JButton();
		Button_search.setBounds(214, 118, 28, 22);
		ImageIcon jbuttonIcon = new ImageIcon("images\\find.png");
		Button_search.setIcon(jbuttonIcon);
		Button_search.setFocusPainted(false);
		panel_subject.add(Button_search);

		Button_yes = new JButton("确认");
		Button_yes.setBounds(282, 332, 93, 23);
		Button_yes.setFocusPainted(false);
		panel_subject.add(Button_yes);

		Button_no = new JButton("取消");
		Button_no.setBounds(385, 332, 93, 23);
		Button_no.setFocusPainted(false);
		panel_subject.add(Button_no);

		// 通过数据库传输数据
		String[] lieStr = { "内容名称", "数量" };
		Object[][] hangStr = new Object[12][2];
		hangStr[0][0] = "内容名称";
		hangStr[0][1] = 9;
		hangStr[1][0] = "水泥密度";
		hangStr[1][1] = 1;
		hangStr[2][0] = "水泥密度";
	    hangStr[2][1] = 1;
	    
		table = new JTable(hangStr, lieStr);
		table.setEnabled(false); //设置不可编辑
		table.setRowHeight(20);//设置单元表格高度
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setPreferredSize(new Dimension(1, 24)); //设置表头高度
		jsp = new JScrollPane(table);
		jsp.setBounds(24, 149, 454, 114);
		panel_subject.add(jsp);

		Button_search.setActionCommand("search");
		Button_search.addActionListener(new CreateInspectionBatchAction());
		Button_no.setActionCommand("no");
		Button_no.addActionListener(new CreateInspectionBatchAction(jDialog));
		Button_yes.setActionCommand("yes");
		Button_yes.addActionListener(new CreateInspectionBatchAction(
				textField_belong, textField_name, textField_search,
				textField_offer, jDialog));

	}
}
