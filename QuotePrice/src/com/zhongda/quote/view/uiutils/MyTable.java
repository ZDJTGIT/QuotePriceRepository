package com.zhongda.quote.view.uiutils;

import javax.swing.JTable;

public class MyTable extends JTable {

	private int[] rows;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyTable() {

	}

	public MyTable(int[] str) {
		this.rows = str;
	}

	public boolean isCellEditable(int row, int column) {

		for (int str : rows) {
			if (str == column) {
				return false;// 不可编辑
			}
		}
		return true;// 可编辑
	}

}
