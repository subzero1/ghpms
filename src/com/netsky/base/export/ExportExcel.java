package com.netsky.base.export;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.utils.DateFormatUtil;
import com.netsky.base.utils.NumberFormatUtil;

/**
 * @author Chiang
 * 
 * 导出excel处理类
 */
public class ExportExcel {

	/**
	 * 将结果集中数据导出到可写工作簿中,标题行为结果集索引值
	 * 
	 * @param ro
	 * @param ws
	 * @throws WriteException
	 * @throws UnsupportedEncodingException
	 */
	public static void Ro2Excel(ResultObject ro, WritableSheet ws) throws WriteException,Exception {
		ro.reSet();
		String titles[] = ro.getResultArray();
		Label label;
		Number number;

		/**
		 * 设置标题行
		 */
		for (int i = 0; i < titles.length; i++) {
			label = new Label(i, 0, titles[i], getTextCellAlignCenterFormat());
			ws.addCell(label);
			ws.setColumnView(i, titles[i].getBytes().length);
		}
		while (ro.next()) {
			for (int i = 0; i < titles.length; i++) {
				String value = null;
				Object o = ro.get(titles[i]);
				if (o == null) {
					value = "";
				} else if (o instanceof java.util.Date) {
					value = DateFormatUtil.FormatDate((java.util.Date) o);
				} else if (o instanceof Double) {
					value = NumberFormatUtil.roundToString((Double) o);
				} else {
					value = o.toString();
				}
				if (value.getBytes("GBK").length > 70) {
					ws.setColumnView(i, 70);
				} 
				else if (value.getBytes("GBK").length < 8 && ws.getColumnWidth(i) < 8) {
					ws.setColumnView(i, 8);
				} else {
					if (ws.getColumnWidth(i) < value.getBytes("GBK").length) {
						ws.setColumnView(i, value.getBytes("GBK").length );
					}
				}
				
				if (o instanceof Double) {
					number = new Number(i, ro.getPlaceIndex() + 1, ((Double) o).doubleValue(),
							getTextCellAlignRightFormat());
					ws.addCell(number);
				} else if (o instanceof Integer) {
					number = new Number(i, ro.getPlaceIndex() + 1, ((Integer) o).intValue(),
							getTextCellAlignRightFormat());
					ws.addCell(number);
				} else if (o instanceof Float) {
					number = new Number(i, ro.getPlaceIndex() + 1, ((Float) o).floatValue(),
							getTextCellAlignRightFormat());
					ws.addCell(number);
				} else if (o instanceof Long) {
					number = new Number(i, ro.getPlaceIndex() + 1, ((Long) o).longValue(),
							getTextCellAlignRightFormat());
					ws.addCell(number);
				} else {
					label = new Label(i, ro.getPlaceIndex() + 1, value, getTextCellAlignLeftFormat());
					ws.addCell(label);
				}
			}
		}
	}

	/**
	 * 指定列标题 titles，各行数据result。构建Excel sheet 页 result 中每一个对像数据数据类型为"数组" ,或
	 * "List" 其它不能显示
	 * 
	 * @param titles
	 * @param result
	 * @param ws
	 * @throws WriteException
	 *             void
	 */
	public static void List2Excel(List titles, List result, WritableSheet ws) throws WriteException,Exception {

		Label label;
		Number number;

		/**
		 * 设置标题行
		 */
		for (int i = 0; i < titles.size(); i++) {
			String titleStr = (String) titles.get(i);
			label = new Label(i, 0, titleStr, getTitleCellFormat());
			ws.addCell(label);
			ws.setColumnView(i, titleStr.getBytes().length);
		}

		for (int i = 0; result != null && i < result.size(); i++) {
			Object[] rowArr = null;
			Object row = result.get(i);
			if (row instanceof List) {
				rowArr = ((List) row).toArray();
			} else if (row instanceof Object[]) {
				rowArr = (Object[]) row;
			} else {
				continue;
			}

			for (int j = 0; j < rowArr.length; j++) {
				String value = null;
				Object o = rowArr[j];
				if (o == null) {
					value = "";
				} else if (o instanceof java.util.Date) {
					value = DateFormatUtil.FormatDate((java.util.Date) o);
				} else if (o instanceof Double) {
					value = NumberFormatUtil.roundToString((Double) o);
				} else {
					value = o.toString();
				}

				if (value.getBytes("GBK").length > 70) {
					ws.setColumnView(j, 70);
				} 
				else if (value.getBytes("GBK").length < 8 && ws.getColumnWidth(j) < 8) {
					ws.setColumnView(j, 8);
				} else {
					if (ws.getColumnWidth(j) < value.getBytes("GBK").length) {
						ws.setColumnView(j, value.getBytes("GBK").length );
					}
				}

				if (o instanceof Double) {
					number = new Number(j, i + 1, ((Double) o).doubleValue(), getTextCellAlignRightFormat());
					ws.addCell(number);
				} else if (o instanceof Integer) {
					number = new Number(j, i + 1, ((Integer) o).intValue(), getTextCellAlignRightFormat());
					ws.addCell(number);
				} else if (o instanceof Float) {
					number = new Number(j, i + 1, ((Float) o).floatValue(), getTextCellAlignRightFormat());
					ws.addCell(number);
				} else if (o instanceof Long) {
					number = new Number(j, i + 1, ((Long) o).longValue(), getTextCellAlignRightFormat());
					ws.addCell(number);
				} else {
					label = new Label(j, i + 1, value, getTextCellAlignLeftFormat());
					ws.addCell(label);
				}

			}
		}
	}

	/**
	 * 设定标题格式wcf_Title 黑体，15号，不加粗，无边框
	 */
	private static WritableCellFormat getTitleCellFormat1() throws WriteException {
		/**
		 * WritableFont参数说明：字体，字号，是否加粗，是否斜体
		 */
		WritableFont wf = new WritableFont(WritableFont.createFont("黑体"), 11, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf_Title = new WritableCellFormat(wf);
		wcf_Title.setAlignment(Alignment.CENTRE);
		wcf_Title.setVerticalAlignment(VerticalAlignment.CENTRE);
		return wcf_Title;
	}

	/**
	 * 设定标题格式wcf_Title 黑体，15号，不加粗，无边框
	 */
	private static WritableCellFormat getTitleCellFormat() throws WriteException {
		/**
		 * WritableFont参数说明：字体，字号，是否加粗，是否斜体
		 */
		WritableFont wf = new WritableFont(WritableFont.createFont("黑体"), 10, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf_Title = new WritableCellFormat(wf);
		wcf_Title.setAlignment(Alignment.CENTRE);
		wcf_Title.setVerticalAlignment(VerticalAlignment.CENTRE);
		return wcf_Title;
	}

	/**
	 * 设定文本域格式wcf_Text，有边框，居中对齐
	 */
	private static WritableCellFormat getTextCellAlignCenterFormat() throws WriteException {
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 9, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf_Text = new WritableCellFormat(wf);
		wcf_Text.setAlignment(Alignment.CENTRE);
		wcf_Text.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf_Text.setBorder(Border.ALL, BorderLineStyle.THIN);
		return wcf_Text;
	}

	/**
	 * 设定文本域格式wcf_Text，有边框，居左对齐
	 */
	private static WritableCellFormat getTextCellAlignLeftFormat() throws WriteException {
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 9, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf_Text = new WritableCellFormat(wf);
		wcf_Text.setAlignment(Alignment.LEFT);
		wcf_Text.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf_Text.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf_Text.setWrap(true);
		return wcf_Text;
	}

	/**
	 * 设定文本域格式wcf_Number，有边框，右对齐
	 */
	private static WritableCellFormat getTextCellAlignRightFormat() throws WriteException {
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 9, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf_Text = new WritableCellFormat(wf);
		wcf_Text.setAlignment(Alignment.RIGHT);
		wcf_Text.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf_Text.setBorder(Border.ALL, BorderLineStyle.THIN);
		return wcf_Text;
	}

	/**
	 * 设定文本域格式wcf_Info，无边框，左对齐
	 */
	private static WritableCellFormat getInfoCellAlignLeftFormat() throws WriteException {
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 9, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf_Info = new WritableCellFormat(wf);
		wcf_Info.setAlignment(Alignment.LEFT);
		wcf_Info.setVerticalAlignment(VerticalAlignment.CENTRE);
		return wcf_Info;
	}

	/**
	 * 设定文本域格式wcf_Info，无边框，右对齐
	 */
	private static WritableCellFormat getInfoCellAlignRightFormat() throws WriteException {
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 9, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf_Info = new WritableCellFormat(wf);
		wcf_Info.setAlignment(Alignment.RIGHT);
		wcf_Info.setVerticalAlignment(VerticalAlignment.CENTRE);
		return wcf_Info;
	}

	/**
	 * 设定文本域格式wcf_Info，无边框，居中对齐
	 */
	private static WritableCellFormat getInfoCellAlignCenterFormat() throws WriteException {
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 9, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf_Info = new WritableCellFormat(wf);
		wcf_Info.setAlignment(Alignment.CENTRE);
		wcf_Info.setVerticalAlignment(VerticalAlignment.CENTRE);
		return wcf_Info;
	}

	/**
	 * 设定文本域格式wcf_Info，无边框，居中对齐,11号字体
	 */
	private static WritableCellFormat getInfoCellAlignCenterFormatForTitle() throws WriteException {
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf_Info = new WritableCellFormat(wf);
		wcf_Info.setAlignment(Alignment.CENTRE);
		wcf_Info.setVerticalAlignment(VerticalAlignment.CENTRE);
		return wcf_Info;
	}
}
