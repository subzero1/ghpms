package com.ghpms.controller.base;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelWrite {
	private Workbook workbook;
	private Sheet sheet;
	private Row row;
	private Cell cell;
	private CellStyle cellStyle;
	private Font font;
	private List<String> titleList;
	private List<List<Object>> rowList;
	private String title;

	public ExcelWrite() {
	}

	public ExcelWrite(Workbook workbook) {
		this.workbook = workbook;
	}

	public List<String> getTitleList() {
		return titleList;
	}

	public void setTitleList(List<String> titleList) {
		this.titleList = titleList;
	}

	public List<List<Object>> getRowList() {
		return rowList;
	}

	public void setRowList(List<List<Object>> rowList) {
		this.rowList = rowList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Workbook write() {
		sheet = workbook.createSheet();
		int i = 0;
		if (title != null && !"".equals(title)) {
			row = sheet.createRow(i++);
			row.setHeight((short)500);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titleList
					.size()));
			cell = row.createCell(0);
			font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font.setFontName("宋体");
			font.setFontHeight((short)250);
			cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(title);
		}	
		row = sheet.createRow(i);
		row.setHeight((short)400);
		titleList.add(0, "序号");
		for (int j = 0; j < titleList.size(); j++) {
			cell = row.createCell(j);
			font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font.setFontName("宋体");
			cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(titleList.get(j));
		}
		for (int rowNum = 0; rowNum < rowList.size(); rowNum++) {
			List<?> list = rowList.get(rowNum);
			row = sheet.createRow(++i);
			cell = row.createCell(0);
			cell.setCellValue(""+(rowNum + 1));
			for (int j = 0; j < list.size(); j++) {
				cell = row.createCell(j + 1);
				cell.setCellValue((String) list.get(j));
			}
		}
		return workbook;
	}
}
