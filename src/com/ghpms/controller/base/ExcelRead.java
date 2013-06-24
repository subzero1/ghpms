package com.ghpms.controller.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 读 Excel 表类 2009-5-21
 * 
 * @author richie.he
 * 
 * 
 * 
 */

public class ExcelRead {

	public static void main(String[] args) {

		try {
			File file = new File("d:\\test.xlsx");
			InputStream instream = new FileInputStream(file);
			// byte[] bs = new byte[1024];
			// while(instream.read(bs) != -1){
			// //System.out.println(new String(bs));
			// }
			// Workbook book = new XSSFWorkbook(instream);
			// Workbook book = new XSSFWorkbook("d:\\test.xlsx");

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * 储存到对应的信息中。
	 * 
	 * @param sheet
	 *            org.apache.poi.ss.usermodel.Sheet;工作薄。
	 * 
	 * @return
	 * 
	 */

	private static List<Map<String, String>> getData(Sheet sheet) {

		List<Map<String, String>> ls = new ArrayList<Map<String, String>>();

		Iterator<Row> rows = sheet.iterator();// 行集合

		Iterator<Cell> cell; // 得到每行的列集合.

		Cell c; // 接收每一列。

		int type; // 用于接收单元格的所属类型。

		String k = ""; // 用于接收每个单元格的数据。

		int j = 0; // 用于设置主键的。

		Map<String, String> str; // 用于接收每列的数据。

		while (rows.hasNext()) { // 遍历所有的行.

			str = new HashMap<String, String>();// 产生对对象。

			cell = rows.next().iterator(); // 得到每行的列集合。

			while (cell.hasNext()) {

				c = cell.next(); // 得到单元格信息

				type = c.getCellType(); // 得到单元格数据类型

				switch (type) { // 判断数据类型

				case Cell.CELL_TYPE_BLANK:

					k = "";

					break;

				case Cell.CELL_TYPE_BOOLEAN:

					k = c.getBooleanCellValue() + "";

					break;

				case Cell.CELL_TYPE_ERROR:

					k = c.getErrorCellValue() + "";

					break;

				case Cell.CELL_TYPE_FORMULA:

					k = c.getCellFormula();

					break;

				case Cell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(c)) {
						k = new DataFormatter().formatRawCellContents(c.getNumericCellValue(), 0, "yyyy-mm-dd");// 格式化日期
					} else {
						k = "" + c.getNumericCellValue();
					}

					break;

				case Cell.CELL_TYPE_STRING:

					k = c.getStringCellValue();

					break;

				default:

					break;

				}

				str.put(String.valueOf((j++)), k); // 赋值。

			}

			if (!str.isEmpty()) { // 判断是不是为空

				ls.add(str);

			}

		}

		return ls;

	}

	private static List<List<String>> getDataList(Sheet sheet) {

		List<List<String>> ls = new ArrayList<List<String>>();

		Iterator<Row> rows = sheet.iterator();// 行集合

		Iterator<Cell> cell; // 得到每行的列集合.

		Cell c = null; // 接收每一列。

		int type; // 用于接收单元格的所属类型。

		String k = ""; // 用于接收每个单元格的数据。

		List<String> str; // 用于接收每列的数据。

		while (rows.hasNext()) { // 遍历所有的行.
			Row row = rows.next();
			str = new ArrayList<String>();// 产生对对象。

			for (int i = 0; i < row.getLastCellNum(); i++) {

				c = row.getCell(i); // 得到单元格信息

				try {
					type = c.getCellType(); // 得到单元格数据类型

					switch (type) { // 判断数据类型

					case Cell.CELL_TYPE_BLANK:

						k = "";

						break;

					case Cell.CELL_TYPE_BOOLEAN:

						k = c.getBooleanCellValue() + "";

						break;

					case Cell.CELL_TYPE_ERROR:

						k = c.getErrorCellValue() + "";

						break;

					case Cell.CELL_TYPE_FORMULA:

						k = c.getCellFormula();

						break;

					case Cell.CELL_TYPE_NUMERIC:

						if (HSSFDateUtil.isCellDateFormatted(c)) {
							k = new DataFormatter().formatRawCellContents(c.getNumericCellValue(), 0, "yyyy-mm-dd");// 格式化日期
						} else {
							k = "" + c.getNumericCellValue();
						}
						break;

					case Cell.CELL_TYPE_STRING:

						k = c.getStringCellValue();

						break;

					default:

						k = "";
						break;

					}
				} catch (NullPointerException e) {
					k = "";
				}
				str.add(k); // 赋值。
			}

			if (!str.isEmpty()) { // 判断是不是为空

				ls.add(str);

			}

		}
		return ls;

	}

	/**
	 * 
	 * 读取 excel 1997-2007版本 表格信息。
	 * 
	 * @param filePath
	 *            文件路径。
	 * 
	 * @param sheetNum
	 *            活动的sheet编号，编号从0开始
	 * @param type
	 *            0：列的数据存放在Map里； 1：列的数据存放在List里；
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * 
	 */

	public static List<?> readEcelFile(String filePath,

	int sheetNum, int type) throws IOException {

		Workbook book = null;

		int i = 0;

		try {

			InputStream instream = new FileInputStream(new File(filePath));// 得到流.

			book = new HSSFWorkbook(instream); // excel97版本以前的

			i = 1;

		} finally {

			if (i == 0) {

				book = new XSSFWorkbook(filePath); // excel2007版本的

			}

			if (book != null) {
				if (type == 0)

				{
					return getData(book.getSheetAt(sheetNum));
				} else {
					return getDataList(book.getSheetAt(sheetNum));
				}

			} else {

				return null;

			}

		}

	}

	public static List<?> readEcelFilebyStream(InputStream instream, String filename,

	int sheetNum, int type) throws IOException {

		Workbook book = null;
		if (filename.toLowerCase().endsWith("xls"))
			book = new HSSFWorkbook(instream);// excel97版本以前的
		else if (filename.toLowerCase().endsWith("xlsx"))
			book = new XSSFWorkbook(instream);// excel2007版本的
		if (book != null) {
			if (type == 0)

			{
				return getData(book.getSheetAt(sheetNum));
			} else {
				return getDataList(book.getSheetAt(sheetNum));
			}

		} else {

			return null;

		}

	}

	/**
	 * 
	 * 读取 excel2007 版本。
	 * 
	 * @param filePath
	 *            文件路径
	 * 
	 * @param sheetNum
	 *            活动的sheet编号，编号从0开始
	 * 
	 * @return
	 * 
	 */

	public static List<Map<String, String>> readXSSFExcelFile(String filePath,

	int sheetNum) {

		try {

			Workbook book = new XSSFWorkbook(filePath);

			return getData(book.getSheetAt(sheetNum));

		} catch (IOException e) {

			e.printStackTrace();

		}

		return null;

	}

	/**
	 * 
	 * 读取excel 97及之前的
	 * 
	 * @param filePath
	 *            文件路径
	 * 
	 * @param sheetNum
	 *            活动的sheet编号，编号从0开始
	 * 
	 * @return
	 * 
	 */

	public static List<Map<String, String>> readHSSFExcelFile(String filePath,

	int sheetNum) {

		try {

			InputStream instream = new FileInputStream(new File(filePath));// 得到流.

			Workbook book = new HSSFWorkbook(instream);

			// 得到excel表格.

			// book=new XSSFWorkbook(filePath);

			return getData(book.getSheetAt(sheetNum)); // 得到所有行.

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return null;

	}

}
