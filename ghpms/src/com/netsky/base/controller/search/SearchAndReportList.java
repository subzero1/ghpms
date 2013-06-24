package com.netsky.base.controller.search;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta08_reportfield;
import com.netsky.base.export.ExportExcel;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.DateFormatUtil;
import com.netsky.base.utils.NumberFormatUtil;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.utils.RegExp;

/**
 * 查询、报表统计
 * 
 * @author Chiang 2010-03-10
 */
@Controller
public class SearchAndReportList {

	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;

	/**
	 * @return the queryService
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService
	 *            the queryService to set
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	/**
	 * @return the exceptionService
	 */
	public ExceptionService getExceptionService() {
		return exceptionService;
	}

	/**
	 * @param exceptionService
	 *            the exceptionService to set
	 */
	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}

	/**
	 * 输出报表统计结果
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/search/report.do")
	public ModelAndView Report(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String HSqlWhere;
		String HSqlFrom;
		String HSqlSelect;
		String HSqlGroup;
		String HSqlOrder;
		String tableName;
		String module_name = "综合";
		StringBuffer searchStr = new StringBuffer();
		/**
		 * 输出结果列表
		 */
		List<List<Td_Struct>> result = new ArrayList<List<Td_Struct>>();
		if (request.getParameterValues("ids") == null || request.getParameterValues("ids").length == 0) {
			/**
			 * 默认显示行
			 */
			/**
			 * 每页记录数，默认20行
			 */
			int pageRowSize = 18;
			/**
			 * 当前页，默认第一页
			 */
			if (request.getParameter("pageRowSize") != null && request.getParameter("pageRowSize").length() > 0) {
				pageRowSize = Integer.parseInt(request.getParameter("pageRowSize"));
			}

			/**
			 * 获取报表模块名称
			 */

			request.setAttribute("module_name", module_name);

			for (int i = 0; i < pageRowSize; i++) {
				List<Td_Struct> row = new ArrayList<Td_Struct>();
				for (int j = 0; j < 9; j++) {
					Td_Struct t = new Td_Struct();
					t.title = false;
					row.add(t);
				}
				result.add(row);
			}
			request.setAttribute("resultList", result);
			return new ModelAndView("/WEB-INF/jsp/search/report.jsp");
		}
		Map<String, List<String>> result_map;
		/**
		 * x坐标统计项
		 */
		List<Ta08_reportfield> XList = new ArrayList<Ta08_reportfield>();
		List<List<String>> XValueList = new ArrayList<List<String>>();
		/**
		 * y坐标统计项
		 */
		List<Ta08_reportfield> YList = new ArrayList<Ta08_reportfield>();
		List<List<String>> YValueList = new ArrayList<List<String>>();
		/**
		 * 排列结果列表
		 */
		List<String> YResult;
		List<String> XResult;

		/**
		 * 搜索条件
		 */
		List<String[]> searchField = new ArrayList<String[]>();
		try {
			HSqlWhere = this.makeSearch(request, searchStr, searchField);
			Ta08_reportfield ta08 = (Ta08_reportfield) queryService.searchById(Ta08_reportfield.class, new Long(request
					.getParameter("ids")));
			tableName = ta08.getObject_name().substring(ta08.getObject_name().lastIndexOf(".") + 1);
			HSqlFrom = " from " + tableName + " " + tableName;
			HSqlSelect = "select ";
			HSqlGroup = " group by ";
			HSqlOrder = " order by ";
			/**
			 * 获取统计字段
			 */
			String sum[] = request.getParameterValues("sum");
			String sum_ids = "";
			if (sum != null && sum.length > 0) {
				for (int i = 0; i < sum.length; i++) {
					sum_ids += "," + sum[i];
				}
				sum_ids = sum_ids.replaceFirst(",", "");
			}
			if ("".equals(sum_ids)) {
				sum_ids = "-1";
			}
			String HSql = "select ta08 from Ta08_reportfield ta08 where ta08.id in (" + sum_ids + ")";
			ResultObject sum_ro = queryService.search(HSql);
			while (sum_ro.next()) {
				ta08 = (Ta08_reportfield) sum_ro.get("ta08");
				HSqlSelect += " ,sum(" + ta08.getName() + ")";
			}
			HSqlSelect += ",count(" + tableName + ".id)";
			HSqlSelect = HSqlSelect.replaceFirst(",", "");

			/**
			 * 处理统计条件
			 */
			String statistic[] = request.getParameterValues("statistic");
			String way[] = request.getParameterValues("way");

			if (statistic != null && way != null && statistic.length == way.length) {
				for (int i = 0; i < statistic.length; i++) {
					if (statistic[i] != null && statistic[i].length() > 0) {
						ta08 = (Ta08_reportfield) queryService.searchById(Ta08_reportfield.class, Long
								.valueOf(statistic[i]));
						if ("X".equals(way[i])) {
							XList.add(ta08);
							XValueList.add(new ArrayList<String>());
						} else {
							YList.add(ta08);
							YValueList.add(new ArrayList<String>());
						}
					}
				}
				for (int i = 0; i < YList.size(); i++) {
					ta08 = YList.get(i);
					HSqlGroup += "," + ta08.getName();
					HSqlSelect += "," + ta08.getName();
					if (HSqlWhere.indexOf("where") != -1) {
						HSqlWhere += " and " + ta08.getName() + " is not null ";
					} else {
						HSqlWhere += " where " + ta08.getName() + " is not null ";
					}
				}
				for (int i = 0; i < XList.size(); i++) {
					ta08 = XList.get(i);
					HSqlGroup += "," + ta08.getName();
					HSqlSelect += "," + ta08.getName();
					if (HSqlWhere.indexOf("where") != -1) {
						HSqlWhere += " and " + ta08.getName() + " is not null ";
					} else {
						HSqlWhere += " where " + ta08.getName() + " is not null ";
					}
				}
				HSqlGroup = HSqlGroup.replaceFirst(",", "");
			}
			if (HSqlGroup.equals(" group by ")) {
				HSqlGroup = "";
			}
			for (int i = 0; i < YList.size(); i++) {
				HSqlOrder += "," + YList.get(i).getName();
			}
			for (int i = 0; i < XList.size(); i++) {
				HSqlOrder += "," + XList.get(i).getName();
			}
			if (" order by ".equals(HSqlOrder)) {
				HSqlOrder = "";
			}
			HSqlOrder = HSqlOrder.replaceFirst(",", "");

			/**
			 * 统计
			 */
			ResultObject ro = queryService.search(HSqlSelect + HSqlFrom + HSqlWhere + HSqlGroup + HSqlOrder);
			result_map = new HashMap<String, List<String>>();
			/**
			 * 处理结果集
			 */
			while (ro.next()) {
				List<String> value = new ArrayList<String>();
				sum_ro.reSet();
				while (sum_ro.next()) {
					ta08 = (Ta08_reportfield) sum_ro.get("ta08");
					Object o = ro.get("sum(" + ta08.getName() + ")");
					if (o != null) {
						value.add(NumberFormatUtil.roundToString(o.toString(), 0));
					} else {
						value.add("");
					}
				}

				String key = "";
				for (int i = 0; i < YList.size(); i++) {
					ta08 = YList.get(i);
					if (!YValueList.get(i).contains(
							(ro.get(ta08.getName()) != null ? ro.get(ta08.getName()).toString() : ""))) {
						YValueList.get(i)
								.add((ro.get(ta08.getName()) != null ? ro.get(ta08.getName()).toString() : ""));
					}
					key += "," + (ro.get(ta08.getName()) != null ? ro.get(ta08.getName()) : "");
				}
				for (int i = 0; i < XList.size(); i++) {
					ta08 = XList.get(i);
					if (!XValueList.get(i).contains(
							(ro.get(ta08.getName()) != null ? ro.get(ta08.getName()).toString() : ""))) {
						XValueList.get(i)
								.add((ro.get(ta08.getName()) != null ? ro.get(ta08.getName()).toString() : ""));
					}
					key += "," + (ro.get(ta08.getName()) != null ? ro.get(ta08.getName()) : "");
				}
				key = key.replaceFirst(",", "");
				value.add("" + ro.get("count(" + tableName + ".id)"));
				result_map.put(key, value);

			}
			YResult = this.permutation(YValueList);
			XResult = this.permutation(XValueList);
			/**
			 * 构建结果列表
			 */

			/**
			 * 添加标题行
			 */
			for (int i = 0; i <= XValueList.size(); i++) {
				List<Td_Struct> title_tds = new ArrayList<Td_Struct>();
				if (i == 0) {
					/**
					 * 处理y坐标标题
					 */
					for (int j = 0; j < YList.size(); j++) {
						Td_Struct t = new Td_Struct();
						t.value = YList.get(j).getComments();
						t.rowspan = XValueList.size() + 1;
						title_tds.add(t);
					}
				}
				if (i < XValueList.size()) {
					for (int j = 0; j < XResult.size(); j++) {
						String xTitle[] = XResult.get(j).replaceAll(",", " , ").split(",");
						for (int k = 0; k < sum_ro.getLength(); k++) {
							Td_Struct t = new Td_Struct();
							t.value = xTitle[i];
							title_tds.add(t);
						}
						Td_Struct t = new Td_Struct();
						t.value = xTitle[i];
						title_tds.add(t);
					}
				} else {
					if (XResult.size() > 0) {
						for (int k = 0; k < YList.size(); k++) {
							Td_Struct t = new Td_Struct();
							t.show = false;
							title_tds.add(t);
						}
						for (int j = 0; j < XResult.size(); j++) {
							sum_ro.reSet();
							while (sum_ro.next()) {
								ta08 = (Ta08_reportfield) sum_ro.get("ta08");
								Td_Struct t = new Td_Struct();
								t.value = ta08.getComments();
								title_tds.add(t);
							}
							Td_Struct t = new Td_Struct();
							t.value = "数量";
							title_tds.add(t);
						}
					} else {
						sum_ro.reSet();
						while (sum_ro.next()) {
							ta08 = (Ta08_reportfield) sum_ro.get("ta08");
							Td_Struct t = new Td_Struct();
							t.value = ta08.getComments();
							title_tds.add(t);
						}
						Td_Struct t = new Td_Struct();
						t.value = "数量";
						title_tds.add(t);
					}
				}
				result.add(title_tds);
			}

			/**
			 * 循环处理行
			 */
			if (YResult.size() > 0) {
				for (int i = 0; i < YResult.size(); i++) {

					List<Td_Struct> tdList = new ArrayList<Td_Struct>();
					/**
					 * 添加y坐标标题
					 */
					String yTitle[] = YResult.get(i).replaceAll(",", " , ").split(",");
					for (int l = 0; l < yTitle.length; l++) {
						Td_Struct t = new Td_Struct();
						t.value = yTitle[l].trim();
						t.align = "left";
						tdList.add(t);
					}
					/**
					 * 填充数据
					 */
					if (XResult.size() > 0) {
						for (int j = 0; j < XResult.size(); j++) {
							List<String> tds = result_map.get(YResult.get(i) + "," + XResult.get(j));
							if (tds == null || tds.size() < sum_ro.getLength()) {
								for (int k = 0; k <= sum_ro.getLength(); k++) {
									Td_Struct t = new Td_Struct();
									t.title = false;
									t.param = this.setParam(YList, XList, YResult.get(i), XResult.get(j));
									tdList.add(t);
								}
							} else {
								for (int k = 0; k < tds.size(); k++) {
									Td_Struct t = new Td_Struct();
									t.value = tds.get(k);
									t.title = false;
									t.param = this.setParam(YList, XList, YResult.get(i), XResult.get(j));
									tdList.add(t);
								}
							}
						}
					} else {
						List<String> tds = result_map.get(YResult.get(i));
						if (tds == null || tds.size() < sum_ro.getLength()) {
							for (int k = 0; k <= sum_ro.getLength(); k++) {
								Td_Struct t = new Td_Struct();
								t.title = false;
								tdList.add(t);
							}
						} else {
							for (int k = 0; k < tds.size(); k++) {
								Td_Struct t = new Td_Struct();
								t.value = tds.get(k);
								t.title = false;
								t.param = this.setParam(YList, XList, YResult.get(i), null);
								tdList.add(t);
							}
						}
					}
					result.add(tdList);
				}
			} else {
				/**
				 * 填充数据
				 */
				List<Td_Struct> tdList = new ArrayList<Td_Struct>();
				if (XResult.size() > 0) {
					for (int j = 0; j < XResult.size(); j++) {
						List<String> tds = result_map.get(XResult.get(j));
						if (tds == null || tds.size() <= sum_ro.getLength()) {
							for (int k = 0; k < sum_ro.getLength(); k++) {
								Td_Struct t = new Td_Struct();
								t.title = false;
								tdList.add(t);
							}
						} else {
							for (int k = 0; k < tds.size(); k++) {
								Td_Struct t = new Td_Struct();
								t.value = tds.get(k);
								t.title = false;
								t.param = this.setParam(YList, XList, null, XResult.get(j));
								tdList.add(t);
							}
						}
					}
				} else {
					List<String> tds = result_map.get("");
					if (tds == null || tds.size() <= sum_ro.getLength()) {
						for (int k = 0; k < sum_ro.getLength() + YList.size(); k++) {
							Td_Struct t = new Td_Struct();
							t.title = false;
							tdList.add(t);
						}
					} else {
						for (int k = 0; k < YList.size(); k++) {
							Td_Struct t = new Td_Struct();
							t.title = true;
							tdList.add(t);
						}
						for (int k = 0; k < tds.size(); k++) {
							Td_Struct t = new Td_Struct();
							t.value = tds.get(k);
							t.title = false;
							t.param = this.setParam(YList, XList, null, null);
							tdList.add(t);
						}
					}
				}
				result.add(tdList);
			}

			/**
			 * 处理标题跨行或跨列显示
			 */

			/**
			 * y坐标标题
			 */
			for (int i = 1; i < result.size(); i++) {
				for (int j = 0; j < YList.size(); j++) {
					Td_Struct t2 = result.get(i).get(j);
					for (int k = i - 1; k >= 0; k--) {
						Td_Struct t1 = result.get(k).get(j);
						if (!t1.show) {
							continue;
						} else if ((t1.value == null && t2.value == null) || t1.value.equals(t2.value)) {
							if (t1.rowspan != null)
								t1.rowspan++;
							else
								t1.rowspan = 2;
							t2.show = false;
							break;
						} else {
							break;
						}
					}
				}
			}

			/**
			 * x坐标标题
			 */
			for (int i = 0; i < XList.size(); i++) {
				for (int j = YList.size(); j < result.get(i).size(); j++) {
					Td_Struct t2 = result.get(i).get(j);
					for (int k = j - 1; k >= 0; k--) {
						Td_Struct t1 = result.get(i).get(k);
						if (!t1.show) {
							continue;
						} else if ((t1.value == null && t2.value == null) || t1.value.equals(t2.value)) {
							if (t1.colspan != null)
								t1.colspan++;
							else
								t1.colspan = 2;
							t2.show = false;
							break;
						} else {
							break;
						}
					}
				}
			}

			/**
			 * 增加合计行
			 */
			List<Td_Struct> row = new ArrayList<Td_Struct>();
			if (YList.size() > 0) {
				for (int i = 1; i < YList.size(); i++) {
					Td_Struct t = new Td_Struct();
					t.title = true;
					row.add(t);
				}
				Td_Struct t = new Td_Struct();
				t.title = true;
				t.value = "合计";
				row.add(t);
			}
			List<String> sumList = new ArrayList<String>();
			for (int j = XList.size() + 1; j < result.size(); j++) {
				for (int i = YList.size(); i < result.get(j).size(); i++) {
					if (i - YList.size() >= sumList.size()) {
						sumList.add(result.get(j).get(i).value);
					} else {
						String str = sumList.get(i - YList.size()) != null
								&& sumList.get(i - YList.size()).length() > 0 ? sumList.get(i - YList.size()) : "0";
						String str2 = result.get(j).get(i).value != null && result.get(j).get(i).value.length() > 0 ? result
								.get(j).get(i).value
								: "0";
						sumList.set(i - YList.size(), NumberFormatUtil.addToString(str, str2));
					}
				}
			}
			for (int i = 0; i < sumList.size(); i++) {
				Td_Struct t = new Td_Struct();
				t.title = true;
				t.value = sumList.get(i);
				t.align = "right";
				row.add(t);
			}

			result.add(row);

			request.setAttribute("searchStr", searchStr.toString());
			request.setAttribute("sum", request.getParameterValues("sum"));
			request.setAttribute("YList", YList);
			request.setAttribute("XList", XList);
			request.setAttribute("sqlWhere", HSqlWhere.replaceAll("\"", "&quot;"));
			request.setAttribute("resultList", result);
		} catch (Exception ex) {
			return exceptionService.exceptionControl("ProjectReportList", "工程报表统计错误", ex);
		}
		return new ModelAndView("/WEB-INF/jsp/search/report.jsp");
	}

	/**
	 * 输出报表统计明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/search/reportDetail.do")
	public ModelAndView ReportDetail(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		ResultObject ro;
		QueryBuilder queryBuilder;
		List<?> fieldList = null;
		List<List<Td_Struct>> resultList = null;
		Long module_id;
		if (request.getParameter("module_id") != null && !request.getParameter("module_id").equals("")) {
			module_id = Long.valueOf(request.getParameter("module_id"));
		} else {
			module_id = new Long(100);
		}
		if (request.getParameter("ids") == null || request.getParameter("ids").length() == 0) {
			return null;
		}
		List<String> idList;
		try {
			String HSqlWhere = request.getParameter("sqlWhere");
			if (HSqlWhere == null || HSqlWhere.length() == 0) {
				HSqlWhere = request.getParameter("str").replaceFirst("and", "where");
			} else {
				HSqlWhere += request.getParameter("str");
			}
			Ta08_reportfield ta08;
			if (module_id.longValue() == 100 && request.getParameter("gcstr") != null
					&& request.getParameter("gcstr").length() > 0) {
				if (HSqlWhere == null || HSqlWhere.length() == 0) {
					HSqlWhere = " where Td00_gcxx.gcmc like '%" + request.getParameter("gcstr")
							+ "%' or Td00_gcxx.gcbh like '%" + request.getParameter("gcstr") + "%'";
				} else {
					HSqlWhere += " and (Td00_gcxx.gcmc like '%" + request.getParameter("gcstr")
							+ "%' or Td00_gcxx.gcbh like '%" + request.getParameter("gcstr") + "%')";
				}
			}
			ta08 = (Ta08_reportfield) queryService.searchById(Ta08_reportfield.class, new Long(request
					.getParameter("ids")));
			String tableName = ta08.getObject_name().substring(ta08.getObject_name().lastIndexOf(".") + 1);
			String HSqlFrom = " from " + tableName + " " + tableName;
			String HSqlSelect = "select " + tableName;
			String ids = "";
			idList = new ArrayList<String>();
			for (int i = 0; request.getParameterValues("ids") != null && i < request.getParameterValues("ids").length; i++) {
				ids += "," + request.getParameterValues("ids")[i];
				idList.add(request.getParameterValues("ids")[i]);
			}
			String HSql = " from Ta08_reportfield where (comments in ('表单名称','表单编号','项目名称','机房名称','计划完成时间') and module_id =" + module_id
					+ ") or id in(" + ids.replaceFirst(",", "") + ") order by ord";
			fieldList = queryService.searchList(HSql);

			ro = queryService.search(HSqlSelect + HSqlFrom + HSqlWhere);
			resultList = new ArrayList<List<Td_Struct>>();
			while (ro.next()) {
				List<Td_Struct> rowList = new ArrayList<Td_Struct>();
				Object o = ro.get(tableName);
				Td_Struct field = new Td_Struct();
				field.value = PropertyInject.getProperty(o, "tax_code") + "";
				rowList.add(field);
				for (int i = 0; i < fieldList.size(); i++) {
					ta08 = (Ta08_reportfield) fieldList.get(i);
					Object result = null;
					result = PropertyInject.getProperty(o, ta08.getName());
					field = new Td_Struct();
					if (result instanceof Double) {
						field.value = NumberFormatUtil.roundToString((Double) result);
					} else if (result instanceof java.util.Date) {
						field.value = DateFormatUtil.FormatDate((java.util.Date) result);
					} else {
						if (result != null)
							field.value = StringFormatUtil.format(result.toString());
						else
							field.value = "";
					}
					field.align = StringFormatUtil.format(ta08.getAlign());
					field.width = ta08.getWidth() + "";
					rowList.add(field);
				}
				resultList.add(rowList);
			}
		} catch (Exception ex) {
			return exceptionService.exceptionControl("ProjectReportList", "报表明细错误", ex);
		}
		/**
		 * 处理表格宽度
		 */
		//Integer tablewidth = new Integer(0);
		Long tablewidth = new Long(0L);
		for (int i = 0; i < fieldList.size(); i++) {
			Ta08_reportfield ta08 = (Ta08_reportfield) fieldList.get(i);
			tablewidth += ta08.getWidth();
		}

		/**
		 * 显示流程和表单图标单元格默认90;
		 */
		tablewidth += 90;
		/**
		 * 设置返回对象
		 */
		request.setAttribute("idList", idList);
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("tablewidth", tablewidth);
		request.setAttribute("resultList", resultList);
		return new ModelAndView("/WEB-INF/jsp/search/reportDetail.jsp");
	}

	/**
	 * 输出报表统计明细导出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws WriteException
	 */
	@RequestMapping("/search/detailExport.do")
	public void detailExport(HttpServletRequest request, HttpServletResponse response) throws Exception,
			WriteException {
		request.setCharacterEncoding("utf-8");
		ResultObject ro;
		List<?> fieldList = null;
		Long module_id;
		if (request.getParameter("module_id") != null && !request.getParameter("module_id").equals("")) {
			module_id = Long.valueOf(request.getParameter("module_id"));
		} else {
			module_id = new Long(100);
		}
		if (request.getParameter("ids") == null || request.getParameter("ids").length() == 0) {
			return;
		}
		List<String> idList;

		String HSqlWhere = request.getParameter("sqlWhere");
		if (HSqlWhere == null || HSqlWhere.length() == 0) {
			HSqlWhere = request.getParameter("str").replaceFirst("and", "where");
		} else {
			HSqlWhere += request.getParameter("str");
		}
		Ta08_reportfield ta08;
		if (module_id.longValue() == 100 && request.getParameter("gcstr") != null
				&& request.getParameter("gcstr").length() > 0) {
			if (HSqlWhere == null || HSqlWhere.length() == 0) {
				HSqlWhere = " where Td00_gcxx.gcmc like '%" + request.getParameter("gcstr")
						+ "%' or Td00_gcxx.gcbh like '%" + request.getParameter("gcstr") + "%'";
			} else {
				HSqlWhere += " and (Td00_gcxx.gcmc like '%" + request.getParameter("gcstr")
						+ "%' or Td00_gcxx.gcbh like '%" + request.getParameter("gcstr") + "%')";
			}
		}
		ta08 = (Ta08_reportfield) queryService
				.searchById(Ta08_reportfield.class, new Long(request.getParameter("ids")));
		String tableName = ta08.getObject_name().substring(ta08.getObject_name().lastIndexOf(".") + 1);
		String HSqlFrom = " from " + tableName + " " + tableName;
		String HSqlSelect = "select ";
		String ids = "";
		idList = new ArrayList<String>();
		for (int i = 0; request.getParameterValues("ids") != null && i < request.getParameterValues("ids").length; i++) {
			ids += "," + request.getParameterValues("ids")[i];
			idList.add(request.getParameterValues("ids")[i]);
		}
		String HSql = " from Ta08_reportfield where (comments in ('工程编号','工程名称') and module_id =" + module_id
				+ ") or id in(" + ids.replaceFirst(",", "") + ") order by ord";
		fieldList = queryService.searchList(HSql);
		for (int i = 0; i < fieldList.size(); i++) {
			ta08 = (Ta08_reportfield) fieldList.get(i);
			HSqlSelect += "," + tableName + "." + ta08.getName() + " as " + ta08.getComments();
		}

		HSqlSelect = HSqlSelect.replaceFirst(",", "");
		ro = queryService.search(HSqlSelect + HSqlFrom + HSqlWhere);

		String file_name = "查询结果导出.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=GBK;filename="
				+ new String(file_name.getBytes("GBK"), "iso8859-1"));
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		jxl.write.WritableSheet ws = wwb.createSheet("统计表", 0);
		ExportExcel.Ro2Excel(ro, ws);
		wwb.write();
		wwb.close();
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	/**
	 * 对给入list中的值进行排列组合，外层list为大项，内层list为具体值
	 * 
	 * @param list
	 * @return 结果列表
	 */
	private List<String> permutation(List<List<String>> list) {
		List<String> result = new ArrayList<String>();
		if (list == null || list.size() == 0) {
			return result;
		} else if (list.size() == 1) {
			return list.get(0);
		} else if (list.size() == 2) {
			List<String> fList = list.get(0);
			List<String> bList = list.get(1);
			if (bList == null || bList.size() == 0) {
				return fList;
			}
			if (fList == null || fList.size() == 0) {
				return bList;
			}
			for (int i = 0; i < fList.size(); i++) {
				for (int j = 0; j < bList.size(); j++) {
					result.add(fList.get(i) + "," + bList.get(j));
				}
			}
		} else if (list.size() > 2) {
			/**
			 * 当需要排列组合项目超过2时，则先对最后两项排列，然后与前面项重新组合
			 */
			List<List<String>> list_t = new ArrayList<List<String>>();
			List<String> fList = list.get(list.size() - 2);
			List<String> bList = list.get(list.size() - 1);
			list_t.add(fList);
			list_t.add(bList);
			/**
			 * 对最后两项进行排列
			 */
			List<String> list_r = permutation(list_t);
			/**
			 * 重新构建需要排列的list
			 */
			list.remove(list.size() - 1);
			list.remove(list.size() - 1);
			list.add(list_r);
			return permutation(list);
		}
		return result;
	}

	/**
	 * 输出报表明细结果
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/search/searchList.do")
	public ModelAndView SearchList(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String[] columns = request.getParameterValues("fields_select");
		StringBuffer searchStr = new StringBuffer();
		ResultObject ro;
		QueryBuilder queryBuilder;
		List<Ta08_reportfield> fieldList = null;
		Long flow_id;
		Long module_id;
		String module_name = "";
		/**
		 * 每页记录数，默认20行
		 */
		int pageRowSize = 20;
		int totalPages = 1;
		int totalRows = 0;
		Integer page = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		/**
		 * 处理分页数据
		 */

		if (request.getParameter("numPerPage") != null && request.getParameter("numPerPage").length() > 0) {
			pageRowSize = Integer.parseInt(request.getParameter("numPerPage"));
		}

		if (request.getParameter("module_id") != null && !request.getParameter("module_id").equals("")) {
			module_id = Long.valueOf(request.getParameter("module_id"));
		} else {
			module_id = new Long(100);
		}

		request.setAttribute("module_name", module_name);
		request.setAttribute("module_id", module_id);

		List<String[]> searchField = new ArrayList<String[]>();

		List<List<Td_Struct>> resultList = null;
		if (request.getParameterValues("ids") == null || request.getParameterValues("ids").length == 0) {
			request.setAttribute("pageRowSize", pageRowSize);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("page", page);
			return new ModelAndView("/WEB-INF/jsp/search/searchList.jsp");
		}
		if (request.getParameter("flow_id") != null && request.getParameter("flow_id").length() > 0) {
			try {
				flow_id = new Long(request.getParameter("flow_id"));
			} catch (NumberFormatException e) {
				return exceptionService.exceptionControl("searchList", "错误的flow_id格式", e);
			}
		} else {
			flow_id = null;
		}

		try {
			String HSqlWhere = this.makeSearch(request, searchStr, searchField);
			Ta08_reportfield ta08 = (Ta08_reportfield) queryService.searchById(Ta08_reportfield.class, new Long(request
					.getParameter("ids")));
			String tableName = ta08.getObject_name().substring(ta08.getObject_name().lastIndexOf(".") + 1);
			String HSqlFrom = " from " + tableName + " " + tableName;
			String HSqlSelect = "select " + tableName;

			if (columns == null || columns.length == 0) {
				/**
				 * 获取默认查询字段
				 */
				queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
				queryBuilder.eq("showflag", new Long(1));
				queryBuilder.eq("module_id", module_id);
				queryBuilder.addOrderBy(Order.asc("ord"));
				fieldList = (List<Ta08_reportfield>) queryService.searchList(queryBuilder);
			} else {
				/**
				 * 获取选中查询字段
				 */
				fieldList = new ArrayList<Ta08_reportfield>();
				for (int i = 0; i < columns.length; i++) {
					Ta08_reportfield o = (Ta08_reportfield) queryService.searchById(Ta08_reportfield.class, Long
							.valueOf(columns[i]));
					fieldList.add(o);
				}

			}
			ro = queryService.searchByPage(HSqlSelect + HSqlFrom + HSqlWhere, page, pageRowSize);
			totalRows = ro.getTotalRows();
			totalPages = ro.getTotalPages();
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("page", page);
			request.setAttribute("pageRowSize", pageRowSize);
			resultList = new ArrayList<List<Td_Struct>>();
			while (ro.next()) {
				List<Td_Struct> rowList = new ArrayList<Td_Struct>();
				Object o = ro.get(tableName);
				Td_Struct field = new Td_Struct();
				field.value = PropertyInject.getProperty(o, "id") + "";

				rowList.add(field);
				for (int i = 0; i < fieldList.size(); i++) {
					ta08 = fieldList.get(i);
					Object result = null;
					result = PropertyInject.getProperty(o, ta08.getName());
					field = new Td_Struct();
					if (result instanceof Double) {
						field.value = NumberFormatUtil.roundToString((Double) result);
					} else if (result instanceof java.util.Date) {
						field.value = DateFormatUtil.FormatDate((java.util.Date) result);
					} else {
						if (result != null)
							field.value = StringFormatUtil.format(result.toString());
						else
							field.value = "";
					}
					field.align = StringFormatUtil.format(ta08.getAlign());
					field.width = ta08.getWidth() + "";
					rowList.add(field);
				}
				resultList.add(rowList);
			}
		} catch (Exception ex) {
			return exceptionService.exceptionControl("SearchList", "查询列表错误", ex);
		}

		/**
		 * 处理表格宽度
		 */
		Long tablewidth = new Long(0L);
		for (int i = 0; i < fieldList.size(); i++) {
			Ta08_reportfield ta08 = (Ta08_reportfield) fieldList.get(i);
			tablewidth += ta08.getWidth();
		}

		/**
		 * 显示流程和表单图标单元格默认70;
		 */
		tablewidth += 70;
		/**
		 * 设置返回对象
		 */
		request.setAttribute("fields_select", columns);
		request.setAttribute("searchField", searchField);
		request.setAttribute("total", ro.getLength());
		request.setAttribute("searchStr", searchStr.toString());
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("tablewidth", tablewidth);
		request.setAttribute("resultList", resultList);
		return new ModelAndView("/WEB-INF/jsp/search/searchList.jsp");
	}

	/**
	 * 导出查询结果
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/search/searchListExport.do")
	public void searchListExport(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String[] columns = request.getParameterValues("fields_select");
		StringBuffer searchStr = new StringBuffer();
		ResultObject ro;
		QueryBuilder queryBuilder;
		Long module_id;
		List<Ta08_reportfield> fieldList = null;
		if (request.getParameterValues("ids") == null || request.getParameterValues("ids").length == 0) {
			return;
		}
		if (request.getParameter("module_id") != null && !request.getParameter("module_id").equals("")) {
			module_id = Long.valueOf(request.getParameter("module_id"));
		} else {
			module_id = new Long(100);
		}
		try {
			String HSqlWhere = this.makeSearch(request, searchStr, null);
			Ta08_reportfield ta08 = (Ta08_reportfield) queryService.searchById(Ta08_reportfield.class, new Long(request
					.getParameter("ids")));
			String tableName = ta08.getObject_name().substring(ta08.getObject_name().lastIndexOf(".") + 1);
			String HSqlFrom = " from " + tableName + " " + tableName;
			String HSqlSelect = "select ";
			if (columns == null || columns.length == 0) {
				/**
				 * 获取默认查询字段
				 */
				queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
				queryBuilder.eq("showflag", new Integer(1));
				queryBuilder.eq("module_id", module_id);
				queryBuilder.addOrderBy(Order.asc("ord"));
				fieldList = (List<Ta08_reportfield>) queryService.searchList(queryBuilder);
			} else {
				/**
				 * 获取选中查询字段
				 */
				fieldList = new ArrayList<Ta08_reportfield>();
				for (int i = 0; i < columns.length; i++) {
					Ta08_reportfield o = (Ta08_reportfield) queryService.searchById(Ta08_reportfield.class, Long
							.valueOf(columns[i]));
					fieldList.add(o);
				}

			}
			for (int i = 0; i < fieldList.size(); i++) {
				ta08 = fieldList.get(i);
				HSqlSelect += "," + tableName + "." + ta08.getName() + " as " + ta08.getComments();
			}

			HSqlSelect = HSqlSelect.replaceFirst(",", "");
			ro = queryService.search(HSqlSelect + HSqlFrom + HSqlWhere);

			String file_name = "查询结果导出.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=GBK;filename="
					+ new String(file_name.getBytes("GBK"), "iso8859-1"));
			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
			jxl.write.WritableSheet ws0 = wwb.createSheet("查询列表", 0);
			ExportExcel.Ro2Excel(ro, ws0);
			wwb.write();
			wwb.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception ex) {
			return;
		}
	}

	/**
	 * 处理查询条件
	 * 
	 * @param request
	 * @return HSql where 部分
	 * @throws Exception
	 */
	private String makeSearch(HttpServletRequest request, StringBuffer searchStr, List<String[]> searchField)
			throws Exception {
		String result = "";
		String ids[] = request.getParameterValues("ids");
		String id = "";
		if (ids == null || ids.length == 0) {
			ids=(String[]) request.getAttribute("ids");
			if(ids == null || ids.length == 0)
				throw new Exception("未找到查询关键字");
		}
		for (int i = 0; i < ids.length; i++) {
			id += "," + ids[i];
		}
		id = id.replaceFirst(",", "");
		String HSql = "select ta08 from Ta08_reportfield ta08 where ta08.id in (" + id + ")";
		ResultObject ro = queryService.search(HSql);
		while (ro.next()) {
			Ta08_reportfield ta08 = (Ta08_reportfield) ro.get("ta08");
			String tableName = ta08.getObject_name().substring(ta08.getObject_name().lastIndexOf(".") + 1);
			if (ta08.getSearchtype().intValue() == 1) {
				/**
				 * 关键字类型
				 */
				if (request.getParameter(ta08.getId() + "") != null
						&& request.getParameter(ta08.getId() + "").length() > 0) {
					result += " and " + tableName + "." + ta08.getName() + " like '%"
							+ request.getParameter(ta08.getId() + "") + "%'";
					searchStr.append(ta08.getComments() + "like：" + request.getParameter(ta08.getId() + "") + "；　");
					if (searchField != null) {
						String field[] = new String[3];
						field[0] = ta08.getId() + "";
						field[1] = ta08.getId() + "";
						field[2] = request.getParameter(ta08.getId() + "");
						searchField.add(field);
					}
				}
			} else if (ta08.getSearchtype().intValue() == 2 || ta08.getSearchtype().intValue() == 4) {
				/**
				 * 多选类型与人员类型
				 */
				if (request.getParameter(ta08.getId() + "") != null
						&& request.getParameter(ta08.getId() + "").length() > 0) {
					result += " and " + tableName + "." + ta08.getName() + " in ('"
							+ request.getParameter(ta08.getId() + "").replaceAll(",", "','") + "')";
					searchStr.append(ta08.getComments() + "in：" + request.getParameter(ta08.getId() + "") + "；　");
				}
				if (searchField != null) {
					String field[] = new String[3];
					field[0] = ta08.getId() + "";
					field[1] = ta08.getId() + "";
					field[2] = request.getParameter(ta08.getId() + "");
					searchField.add(field);
				}
			} else if (ta08.getSearchtype().intValue() == 3) {
				/**
				 * 金额类型
				 */
				if (request.getParameter(ta08.getId() + "_low") != null
						&& request.getParameter(ta08.getId() + "_low").length() > 0) {
					result += " and " + tableName + "." + ta08.getName() + " >= "
							+ request.getParameter(ta08.getId() + "_low");
					searchStr.append(ta08.getComments() + "≥" + request.getParameter(ta08.getId() + "_low") + "；　");
					if (searchField != null) {
						String field[] = new String[3];
						field[0] = ta08.getId() + "";
						field[1] = ta08.getId() + "_low";
						field[2] = request.getParameter(ta08.getId() + "_low");
						searchField.add(field);
					}
				}
				if (request.getParameter(ta08.getId() + "_high") != null
						&& request.getParameter(ta08.getId() + "_high").length() > 0) {
					result += " and " + tableName + "." + ta08.getName() + " <= "
							+ request.getParameter(ta08.getId() + "_high");
					searchStr.append(ta08.getComments() + "≤" + request.getParameter(ta08.getId() + "_high") + "；　");
					if (searchField != null) {
						String field[] = new String[3];
						field[0] = ta08.getId() + "";
						field[1] = ta08.getId() + "_high";
						field[2] = request.getParameter(ta08.getId() + "_high");
						searchField.add(field);
					}
				}
			} else if (ta08.getSearchtype().intValue() == 5) {
				/**
				 * 日期类型
				 */
				String low_date = request.getParameter(ta08.getId() + "_low");
				String high_date = request.getParameter(ta08.getId() + "_high");
				
				/*
				 *支持几种觉的时间格式 （modified by lixiangyu）
				 */
				String date_pattern = null;
				RegExp regExp = new RegExp(); 
				if(regExp.match("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}", low_date)){
					date_pattern = "yyyy-mm-dd hh24:mi:ss";
				}
				else if(regExp.match("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}", low_date)){
					date_pattern = "yyyy-mm-dd hh24:mi";
				}
				else{
					date_pattern = "yyyy-mm-dd";
				}
				
				if (low_date != null && low_date.length() > 0) {
					result += " and " + tableName + "." + ta08.getName() + " >= to_date('"
							+ low_date + "','"+date_pattern+"')";
					searchStr.append(ta08.getComments() + "≥" + low_date + "；　");
					if (searchField != null) {
						String field[] = new String[3];
						field[0] = ta08.getId() + "";
						field[1] = ta08.getId() + "_low";
						field[2] = low_date;
						searchField.add(field);
					}
				}
				if (high_date != null
						&& high_date.length() > 0) {
					result += " and " + tableName + "." + ta08.getName() + " <= to_date('"
							+ high_date + "','"+date_pattern+"')";
					searchStr.append(ta08.getComments() + "≤" + high_date + "；　");
					if (searchField != null) {
						String field[] = new String[3];
						field[0] = ta08.getId() + "";
						field[1] = ta08.getId() + "_high";
						field[2] = high_date;
						searchField.add(field);
					}
				}
			}
		}

		return result.replaceFirst("and", "where");
	}

	/**
	 * 获取td对应查询参数
	 * 
	 * @return
	 */
	private String setParam(List<Ta08_reportfield> Y, List<Ta08_reportfield> X, String YResult, String XResult) {
		String result = "";
		if (Y.size() > 0 && Y.size() == YResult.split(",").length) {
			String Yvalue[] = YResult.split(",");
			for (int i = 0; i < Y.size(); i++) {
				Ta08_reportfield ta08 = Y.get(i);
				String tableName = ta08.getObject_name().substring(ta08.getObject_name().lastIndexOf(".") + 1);
				result += " and " + tableName + "." + ta08.getName() + "=\\\'" + Yvalue[i] + "\\\'";
			}
		}
		if (X.size() > 0 && X.size() == XResult.split(",").length) {
			String Xvalue[] = XResult.split(",");
			for (int i = 0; i < X.size(); i++) {
				Ta08_reportfield ta08 = X.get(i);
				String tableName = ta08.getObject_name().substring(ta08.getObject_name().lastIndexOf(".") + 1);
				result += " and " + tableName + "." + ta08.getName() + "=\\\'" + Xvalue[i] + "\\\'";
			}
		}
		return result;
	}

	public class Td_Struct {
		private String value;

		private Integer rowspan;

		private Integer colspan;

		private boolean show = true;

		private boolean title = true;

		private String align;

		private String width;

		private String param;

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}

		/**
		 * @return the rowspan
		 */
		public Integer getRowspan() {
			return rowspan;
		}

		/**
		 * @param rowspan
		 *            the rowspan to set
		 */
		public void setRowspan(Integer rowspan) {
			this.rowspan = rowspan;
		}

		/**
		 * @return the colspan
		 */
		public Integer getColspan() {
			return colspan;
		}

		/**
		 * @param colspan
		 *            the colspan to set
		 */
		public void setColspan(Integer colspan) {
			this.colspan = colspan;
		}

		/**
		 * @return the show
		 */
		public boolean isShow() {
			return show;
		}

		/**
		 * @param show
		 *            the show to set
		 */
		public void setShow(boolean show) {
			this.show = show;
		}

		/**
		 * @return the align
		 */
		public String getAlign() {
			return align;
		}

		/**
		 * @param align
		 *            the align to set
		 */
		public void setAlign(String align) {
			this.align = align;
		}

		/**
		 * @return the width
		 */
		public String getWidth() {
			return width;
		}

		/**
		 * @param width
		 *            the width to set
		 */
		public void setWidth(String width) {
			this.width = width;
		}

		/**
		 * @return
		 */
		public boolean isTitle() {
			return title;
		}

		/**
		 * @param title
		 */
		public void setTitle(boolean title) {
			this.title = title;
		}

		/**
		 * @return the param
		 */
		public String getParam() {
			return param;
		}

		/**
		 * @param param
		 *            the param to set
		 */
		public void setParam(String param) {
			this.param = param;
		}

	}

	/**
	 * 统计导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/search/reportExport.do")
	public void reportExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String HSqlWhere;
		String HSqlFrom;
		String HSqlSelect;
		String HSqlGroup;
		String HSqlOrder;
		String tableName;
		StringBuffer searchStr = new StringBuffer();
		/**
		 * 输出结果列表
		 */
		List<List<Td_Struct>> result = new ArrayList<List<Td_Struct>>();
		if (request.getParameterValues("ids") == null || request.getParameterValues("ids").length == 0) {
			return;
		}
		Map<String, List<String>> result_map;
		/**
		 * x坐标统计项
		 */
		List<Ta08_reportfield> XList = new ArrayList<Ta08_reportfield>();
		List<List<String>> XValueList = new ArrayList<List<String>>();
		/**
		 * y坐标统计项
		 */
		List<Ta08_reportfield> YList = new ArrayList<Ta08_reportfield>();
		List<List<String>> YValueList = new ArrayList<List<String>>();
		/**
		 * 排列结果列表
		 */
		List<String> YResult;
		List<String> XResult;
		HSqlWhere = this.makeSearch(request, searchStr, null);
		Ta08_reportfield ta08 = (Ta08_reportfield) queryService.searchById(Ta08_reportfield.class, new Long(request
				.getParameter("ids")));
		tableName = ta08.getObject_name().substring(ta08.getObject_name().lastIndexOf(".") + 1);
		HSqlFrom = " from " + tableName + " " + tableName;
		HSqlSelect = "select ";
		HSqlGroup = " group by ";
		HSqlOrder = " order by ";
		/**
		 * 获取统计字段
		 */
		String sum[] = request.getParameterValues("sum");
		String sum_ids = "";
		if (sum != null && sum.length > 0) {
			for (int i = 0; i < sum.length; i++) {
				sum_ids += "," + sum[i];
			}
			sum_ids = sum_ids.replaceFirst(",", "");
		}
		if ("".equals(sum_ids)) {
			sum_ids = "-1";
		}
		String HSql = "select ta08 from Ta08_reportfield ta08 where ta08.id in (" + sum_ids + ")";
		ResultObject sum_ro = queryService.search(HSql);
		while (sum_ro.next()) {
			ta08 = (Ta08_reportfield) sum_ro.get("ta08");
			HSqlSelect += " ,sum(" + ta08.getName() + ")";
		}
		HSqlSelect += ",count(" + tableName + ".id)";
		HSqlSelect = HSqlSelect.replaceFirst(",", "");

		/**
		 * 处理统计条件
		 */
		String statistic[] = request.getParameterValues("statistic");
		String way[] = request.getParameterValues("way");

		if (statistic != null && way != null && statistic.length == way.length) {
			for (int i = 0; i < statistic.length; i++) {
				if (statistic[i] != null && statistic[i].length() > 0) {
					ta08 = (Ta08_reportfield) queryService.searchById(Ta08_reportfield.class, Long
							.valueOf(statistic[i]));
					if ("X".equals(way[i])) {
						XList.add(ta08);
						XValueList.add(new ArrayList<String>());
					} else {
						YList.add(ta08);
						YValueList.add(new ArrayList<String>());
					}
				}
			}
			for (int i = 0; i < YList.size(); i++) {
				ta08 = YList.get(i);
				HSqlGroup += "," + ta08.getName();
				HSqlSelect += "," + ta08.getName();
				if (HSqlWhere.indexOf("where") != -1) {
					HSqlWhere += " and " + ta08.getName() + " is not null ";
				} else {
					HSqlWhere += " where " + ta08.getName() + " is not null ";
				}
			}
			for (int i = 0; i < XList.size(); i++) {
				ta08 = XList.get(i);
				HSqlGroup += "," + ta08.getName();
				HSqlSelect += "," + ta08.getName();
				if (HSqlWhere.indexOf("where") != -1) {
					HSqlWhere += " and " + ta08.getName() + " is not null ";
				} else {
					HSqlWhere += " where " + ta08.getName() + " is not null ";
				}
			}
			HSqlGroup = HSqlGroup.replaceFirst(",", "");
		}
		if (HSqlGroup.equals(" group by ")) {
			HSqlGroup = "";
		}
		for (int i = 0; i < YList.size(); i++) {
			HSqlOrder += "," + YList.get(i).getName();
		}
		for (int i = 0; i < XList.size(); i++) {
			HSqlOrder += "," + XList.get(i).getName();
		}
		if (" order by ".equals(HSqlOrder)) {
			HSqlOrder = "";
		}
		HSqlOrder = HSqlOrder.replaceFirst(",", "");

		/**
		 * 统计
		 */
		ResultObject ro = queryService.search(HSqlSelect + HSqlFrom + HSqlWhere + HSqlGroup + HSqlOrder);
		result_map = new HashMap<String, List<String>>();
		/**
		 * 处理结果集
		 */
		while (ro.next()) {
			List<String> value = new ArrayList<String>();
			sum_ro.reSet();
			while (sum_ro.next()) {
				ta08 = (Ta08_reportfield) sum_ro.get("ta08");
				Object o = ro.get("sum(" + ta08.getName() + ")");
				if (o != null) {
					value.add(NumberFormatUtil.roundToString(o.toString(), 0));
				} else {
					value.add("");
				}
			}

			String key = "";
			for (int i = 0; i < YList.size(); i++) {
				ta08 = YList.get(i);
				if (!YValueList.get(i).contains(
						(ro.get(ta08.getName()) != null ? ro.get(ta08.getName()).toString() : ""))) {
					YValueList.get(i).add((ro.get(ta08.getName()) != null ? ro.get(ta08.getName()).toString() : ""));
				}
				key += "," + (ro.get(ta08.getName()) != null ? ro.get(ta08.getName()) : "");
			}
			for (int i = 0; i < XList.size(); i++) {
				ta08 = XList.get(i);
				if (!XValueList.get(i).contains(
						(ro.get(ta08.getName()) != null ? ro.get(ta08.getName()).toString() : ""))) {
					XValueList.get(i).add((ro.get(ta08.getName()) != null ? ro.get(ta08.getName()).toString() : ""));
				}
				key += "," + (ro.get(ta08.getName()) != null ? ro.get(ta08.getName()) : "");
			}
			key = key.replaceFirst(",", "");
			value.add("" + ro.get("count(" + tableName + ".id)"));
			result_map.put(key, value);

		}
		YResult = this.permutation(YValueList);
		XResult = this.permutation(XValueList);
		/**
		 * 构建结果列表
		 */

		/**
		 * 添加标题行
		 */
		for (int i = 0; i <= XValueList.size(); i++) {
			List<Td_Struct> title_tds = new ArrayList<Td_Struct>();
			if (i == 0) {
				/**
				 * 处理y坐标标题
				 */
				for (int j = 0; j < YList.size(); j++) {
					Td_Struct t = new Td_Struct();
					t.value = YList.get(j).getComments();
					t.rowspan = XValueList.size() + 1;
					title_tds.add(t);
				}
			}
			if (i < XValueList.size()) {
				for (int j = 0; j < XResult.size(); j++) {
					String xTitle[] = XResult.get(j).replaceAll(",", " , ").split(",");
					for (int k = 0; k < sum_ro.getLength(); k++) {
						Td_Struct t = new Td_Struct();
						t.value = xTitle[i];
						title_tds.add(t);
					}
					Td_Struct t = new Td_Struct();
					t.value = xTitle[i];
					title_tds.add(t);
				}
			} else {
				if (XResult.size() > 0) {
					for (int k = 0; k < YList.size(); k++) {
						Td_Struct t = new Td_Struct();
						t.show = false;
						title_tds.add(t);
					}
					for (int j = 0; j < XResult.size(); j++) {
						sum_ro.reSet();
						while (sum_ro.next()) {
							ta08 = (Ta08_reportfield) sum_ro.get("ta08");
							Td_Struct t = new Td_Struct();
							t.value = ta08.getComments();
							title_tds.add(t);
						}
						Td_Struct t = new Td_Struct();
						t.value = "数量";
						title_tds.add(t);
					}
				} else {
					sum_ro.reSet();
					while (sum_ro.next()) {
						ta08 = (Ta08_reportfield) sum_ro.get("ta08");
						Td_Struct t = new Td_Struct();
						t.value = ta08.getComments();
						title_tds.add(t);
					}
					Td_Struct t = new Td_Struct();
					t.value = "数量";
					title_tds.add(t);
				}
			}
			result.add(title_tds);
		}

		/**
		 * 循环处理行
		 */
		if (YResult.size() > 0) {
			for (int i = 0; i < YResult.size(); i++) {

				List<Td_Struct> tdList = new ArrayList<Td_Struct>();
				/**
				 * 添加y坐标标题
				 */
				String yTitle[] = YResult.get(i).replaceAll(",", " , ").split(",");
				for (int l = 0; l < yTitle.length; l++) {
					Td_Struct t = new Td_Struct();
					t.value = yTitle[l].trim();
					t.align = "left";
					tdList.add(t);
				}
				/**
				 * 填充数据
				 */
				if (XResult.size() > 0) {
					for (int j = 0; j < XResult.size(); j++) {
						List<String> tds = result_map.get(YResult.get(i) + "," + XResult.get(j));
						if (tds == null || tds.size() < sum_ro.getLength()) {
							for (int k = 0; k <= sum_ro.getLength(); k++) {
								Td_Struct t = new Td_Struct();
								t.title = false;
								t.param = this.setParam(YList, XList, YResult.get(i), XResult.get(j));
								tdList.add(t);
							}
						} else {
							for (int k = 0; k < tds.size(); k++) {
								Td_Struct t = new Td_Struct();
								t.value = tds.get(k);
								t.title = false;
								t.param = this.setParam(YList, XList, YResult.get(i), XResult.get(j));
								tdList.add(t);
							}
						}
					}
				} else {
					List<String> tds = result_map.get(YResult.get(i));
					if (tds == null || tds.size() < sum_ro.getLength()) {
						for (int k = 0; k <= sum_ro.getLength(); k++) {
							Td_Struct t = new Td_Struct();
							t.title = false;
							tdList.add(t);
						}
					} else {
						for (int k = 0; k < tds.size(); k++) {
							Td_Struct t = new Td_Struct();
							t.value = tds.get(k);
							t.title = false;
							t.param = this.setParam(YList, XList, YResult.get(i), null);
							tdList.add(t);
						}
					}
				}
				result.add(tdList);
			}
		} else {
			/**
			 * 填充数据
			 */
			List<Td_Struct> tdList = new ArrayList<Td_Struct>();
			if (XResult.size() > 0) {
				for (int j = 0; j < XResult.size(); j++) {
					List<String> tds = result_map.get(XResult.get(j));
					if (tds == null || tds.size() <= sum_ro.getLength()) {
						for (int k = 0; k < sum_ro.getLength(); k++) {
							Td_Struct t = new Td_Struct();
							t.title = false;
							tdList.add(t);
						}
					} else {
						for (int k = 0; k < tds.size(); k++) {
							Td_Struct t = new Td_Struct();
							t.value = tds.get(k);
							t.title = false;
							t.param = this.setParam(YList, XList, null, XResult.get(j));
							tdList.add(t);
						}
					}
				}
			} else {
				List<String> tds = result_map.get("");
				if (tds == null || tds.size() <= sum_ro.getLength()) {
					for (int k = 0; k < sum_ro.getLength() + YList.size(); k++) {
						Td_Struct t = new Td_Struct();
						t.title = false;
						tdList.add(t);
					}
				} else {
					for (int k = 0; k < YList.size(); k++) {
						Td_Struct t = new Td_Struct();
						t.title = true;
						tdList.add(t);
					}
					for (int k = 0; k < tds.size(); k++) {
						Td_Struct t = new Td_Struct();
						t.value = tds.get(k);
						t.title = false;
						t.param = this.setParam(YList, XList, null, null);
						tdList.add(t);
					}
				}
			}
			result.add(tdList);
		}

		/**
		 * 处理标题跨行或跨列显示
		 */

		/**
		 * y坐标标题
		 */
		for (int i = 1; i < result.size(); i++) {
			for (int j = 0; j < YList.size(); j++) {
				Td_Struct t2 = result.get(i).get(j);
				for (int k = i - 1; k >= 0; k--) {
					Td_Struct t1 = result.get(k).get(j);
					if (!t1.show) {
						continue;
					} else if ((t1.value == null && t2.value == null) || t1.value.equals(t2.value)) {
						if (t1.rowspan != null)
							t1.rowspan++;
						else
							t1.rowspan = 2;
						t2.show = false;
						break;
					} else {
						break;
					}
				}
			}
		}

		/**
		 * x坐标标题
		 */
		for (int i = 0; i < XList.size(); i++) {
			for (int j = YList.size(); j < result.get(i).size(); j++) {
				Td_Struct t2 = result.get(i).get(j);
				for (int k = j - 1; k >= 0; k--) {
					Td_Struct t1 = result.get(i).get(k);
					if (!t1.show) {
						continue;
					} else if ((t1.value == null && t2.value == null) || t1.value.equals(t2.value)) {
						if (t1.colspan != null)
							t1.colspan++;
						else
							t1.colspan = 2;
						t2.show = false;
						break;
					} else {
						break;
					}
				}
			}
		}

		/**
		 * 增加合计行
		 */
		List<Td_Struct> row = new ArrayList<Td_Struct>();
		if (YList.size() > 0) {
			for (int i = 1; i < YList.size(); i++) {
				Td_Struct t = new Td_Struct();
				t.title = true;
				row.add(t);
			}
			Td_Struct t = new Td_Struct();
			t.title = true;
			t.value = "合计";
			row.add(t);
		}
		List<String> sumList = new ArrayList<String>();
		for (int j = XList.size() + 1; j < result.size(); j++) {
			for (int i = YList.size(); i < result.get(j).size(); i++) {
				if (i - YList.size() >= sumList.size()) {
					sumList.add(result.get(j).get(i).value);
				} else {
					String str = sumList.get(i - YList.size()) != null && sumList.get(i - YList.size()).length() > 0 ? sumList
							.get(i - YList.size())
							: "0";
					String str2 = result.get(j).get(i).value != null && result.get(j).get(i).value.length() > 0 ? result
							.get(j).get(i).value
							: "0";
					sumList.set(i - YList.size(), NumberFormatUtil.addToString(str, str2));
				}
			}
		}
		for (int i = 0; i < sumList.size(); i++) {
			Td_Struct t = new Td_Struct();
			t.title = true;
			t.value = sumList.get(i);
			t.align = "right";
			row.add(t);
		}

		result.add(row);

		/**
		 * 导出部分，以上为查询部分，与Report方法中内容相同
		 */
		String file_name = "统计结果导出.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=GBK;filename="
				+ new String(file_name.getBytes("GBK"), "iso8859-1"));
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		jxl.write.WritableSheet ws = wwb.createSheet("统计表", 0);
		for (int i = 0; i < result.size(); i++) {
			List<Td_Struct> list = result.get(i);
			for (int j = 0; j < list.size(); j++) {
				Td_Struct td = list.get(j);
				Label label;
				Number number;
				if (td.title) {
					label = new Label(j, i, td.value, getTextCellAlignCenterFormat());
					ws.addCell(label);
				} else {
					// label = new Label(j, i, td.value,
					// getTextCellAlignRightFormat());
					if (td.value != null && td.value.matches("^[-]{0,1}[0-9]+[\\.]{0,1}[0-9]+$")) {
						number = new Number(j, i, Double.parseDouble(td.value), getTextCellAlignRightFormat());
						ws.addCell(number);
					} else {
						label = new Label(j, i, td.value, getTextCellAlignLeftFormat());
						ws.addCell(label);
					}
				}

				ws.mergeCells(j, i, j + (td.colspan != null ? td.colspan - 1 : 0), i
						+ (td.rowspan != null ? td.rowspan - 1 : 0));
				if (td.value != null) {
					if (ws.getColumnWidth(i) < td.value.getBytes().length) {
						ws.setColumnView(i, td.value.getBytes().length);
					}
				}

			}
		}
		wwb.write();
		wwb.close();
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	/**
	 * 设定文本域格式wcf_Text，有边框，居中对齐
	 */
	private static WritableCellFormat getTextCellAlignLeftFormat() throws WriteException {
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 9, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf_Text = new WritableCellFormat(wf);
		wcf_Text.setAlignment(Alignment.LEFT);
		wcf_Text.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf_Text.setBorder(Border.ALL, BorderLineStyle.THIN);
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
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException ModelAndView
	 */
	@RequestMapping("/search/searchListX.do")
	public ModelAndView searchListX(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		long x=new Date().getTime();
		request.setCharacterEncoding("utf-8");
		String[] columns = request.getParameterValues("fields_select");
		StringBuffer searchStr = new StringBuffer();
		ResultObject ro;
		QueryBuilder queryBuilder;
		List<Ta08_reportfield> fieldList = null;
		Long flow_id;
		Long module_id;
		String module_name = "";
		
		String queryStr=convertUtil.toString(new String(request.getParameter("queryStr").getBytes("iso-8859-1"),"gbk"),"");
		/**
		 * 每页记录数，默认20行
		 */
		int pageRowSize = 20;
		int totalPages = 1;
		int totalRows = 0;
		Integer page = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		/**
		 * 处理分页数据
		 */

		if (request.getParameter("numPerPage") != null && request.getParameter("numPerPage").length() > 0) {
			pageRowSize = Integer.parseInt(request.getParameter("numPerPage"));
		}

		if (request.getParameter("module_id") != null && !request.getParameter("module_id").equals("")) {
			module_id = Long.valueOf(request.getParameter("module_id"));
		} else {
			module_id = new Long(100);
		}

		request.setAttribute("module_name", module_name);
		request.setAttribute("module_id", module_id);

		List<String[]> searchField = new ArrayList<String[]>();

		
		
		
		List<List<Td_Struct>> resultList = null;
		/**
		if (request.getParameterValues("ids") == null || request.getParameterValues("ids").length == 0) {
			request.setAttribute("pageRowSize", pageRowSize);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("page", page);
			return new ModelAndView("/WEB-INF/jsp/search/searchList.jsp");
		}
		*/
		if (request.getParameter("flow_id") != null && request.getParameter("flow_id").length() > 0) {
			try {
				flow_id = new Long(request.getParameter("flow_id"));
			} catch (NumberFormatException e) {
				return exceptionService.exceptionControl("searchList", "错误的flow_id格式", e);
			}
		} else {
			flow_id = null;
		}

		try {
			/**
			 * 获取默认查询字段
			 */
			queryBuilder = new HibernateQueryBuilder(Ta08_reportfield.class);
			queryBuilder.eq("showflag", new Long(1));
			queryBuilder.eq("module_id", module_id);
			queryBuilder.addOrderBy(Order.asc("ord"));
			fieldList = (List<Ta08_reportfield>) queryService.searchList(queryBuilder);
			
			String ids[]=new String [fieldList.size()];
			Ta08_reportfield report=null;
			for(int k=0;k<ids.length;k++){
				ids[k]=String.valueOf(fieldList.get(k).getId());
			}
			request.setAttribute("ids", ids);
			
			
			String HSqlWhere = this.makeSearch(request, searchStr, searchField);
			Ta08_reportfield ta08 = (Ta08_reportfield) queryService.searchById(Ta08_reportfield.class, new Long(ids[0]));
			String tableName = ta08.getObject_name().substring(ta08.getObject_name().lastIndexOf(".") + 1);
			String HSqlFrom = " from " + tableName + " " + tableName;
			String HSqlSelect = "select " + tableName;
			
			String HqlStr=HSqlSelect+HSqlWhere+HSqlFrom;
			if(!queryStr.equals("")){
			if(module_id==90){//目标库
				HqlStr+=" where "+tableName+".zybh like '%"+queryStr+"%' or "+tableName+".zymc like '%"+queryStr+"%'" ;
			}else if(module_id==101){//项目信息查询
				HqlStr+=" where "+tableName+".xmbh like '%"+queryStr+"%' or "+tableName+".xmmc like '%"+queryStr+"%'" ;
			}else if(module_id==102){//工程信息查询
				HqlStr+=" where "+tableName+".gcbh like '%"+queryStr+"%' or "+tableName+".gcmc like '%"+queryStr+"%'" ;
			}
			}
			ro = queryService.searchByPage(HqlStr, page, pageRowSize);
			totalRows = ro.getTotalRows();
			totalPages = ro.getTotalPages();
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("page", page);
			request.setAttribute("pageRowSize", pageRowSize);
			resultList = new ArrayList<List<Td_Struct>>();
			while (ro.next()) {
				List<Td_Struct> rowList = new ArrayList<Td_Struct>();
				Object o = ro.get(tableName);
				Td_Struct field = new Td_Struct();
				field.value = PropertyInject.getProperty(o, "id") + "";

				rowList.add(field);
				for (int i = 0; i < fieldList.size(); i++) {
					ta08 = fieldList.get(i);
					Object result = null;
					result = PropertyInject.getProperty(o, ta08.getName());
					field = new Td_Struct();
					if (result instanceof Double) {
						field.value = NumberFormatUtil.roundToString((Double) result);
					} else if (result instanceof java.util.Date) {
						field.value = DateFormatUtil.FormatDate((java.util.Date) result);
					} else {
						if (result != null)
							field.value = StringFormatUtil.format(result.toString());
						else
							field.value = "";
					}
					field.align = StringFormatUtil.format(ta08.getAlign());
					field.width = ta08.getWidth() + "";
					rowList.add(field);
				}
				resultList.add(rowList);
			}
		} catch (Exception ex) {
			return exceptionService.exceptionControl("SearchList", "查询列表错误", ex);
		}

		/**
		 * 处理表格宽度
		 */
		Long tablewidth = new Long(0L);
		for (int i = 0; i < fieldList.size(); i++) {
			Ta08_reportfield ta08 = (Ta08_reportfield) fieldList.get(i);
			tablewidth += ta08.getWidth();
		}

		/**
		 * 显示流程和表单图标单元格默认70;
		 */
		tablewidth += 70;
		/**
		 * 设置返回对象
		 */
		request.setAttribute("fields_select", columns);
		request.setAttribute("searchField", searchField);
		request.setAttribute("total", ro.getLength());
		request.setAttribute("searchStr", searchStr.toString());
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("tablewidth", tablewidth);
		request.setAttribute("resultList", resultList);
		return new ModelAndView("/WEB-INF/jsp/search/searchList.jsp");
	
	}
}
