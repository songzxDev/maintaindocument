package cn.cgztb.maintaindocument.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.cgztb.maintaindocument.domain.MaintainDocumentExcelCell;

public class MaintainDocumentPOIUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MaintainDocumentPOIUtil.class);

	private MaintainDocumentPOIUtil() {

	}

	private static MaintainDocumentPOIUtil maintainDocumentPOIUtil = null;

	public synchronized static MaintainDocumentPOIUtil getInstance() {
		if (maintainDocumentPOIUtil == null) {
			maintainDocumentPOIUtil = new MaintainDocumentPOIUtil();
		}
		return maintainDocumentPOIUtil;
	}

	/**
	 * 
	 * @Title: getLastExcelCellsByConditions
	 * @Description: 上海审批流程配置文档的部分涉及金额判断的单元格的背景颜色需要设置为：红色，此方法是为了获取所需修改背景色的单元格的基本信息
	 * @param workbook
	 *          对应相应Excel文档的（org.apache.poi.ss.usermodel.Workbook）对象
	 * @param conditions
	 *          : Map<String, Object> 中的 key : Excel文档页签名称_当前页签部门列的下标 value : 当前页签涉及金额判断的所有部门名称的 List 集合
	 * @param fromRowIndex
	 *          : 遍历Excel文档的sheet页签时，从文档的行下标为：fromRowIndex 开始遍历
	 * @return Map<String, Object> 中的 key : Excel文档页签名称 value : 当前页签涉及金额判断的单元格的行信息和列信息的 List 集合
	 * @throws Exception
	 * @return: Map<String,Object>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getLastExcelCellsByConditions(org.apache.poi.ss.usermodel.Workbook workbook, Map<String, Object> conditions, int fromRowIndex) throws Exception {
		Map<String, Object> lastExcelCellBeans = new HashMap<String, Object>();
		if (workbook != null) {
			if (conditions != null && !conditions.isEmpty()) {
				for (String key : conditions.keySet()) {
					if (key.indexOf("_") == -1) {
						continue;
					}
					String params[] = key.split("_");
					String sheetName = params[0];
					int departmentNameCellIndex = Integer.parseInt(params[1]);
					List<String> departmentNames = (List<String>) conditions.get(key);
					// 得到工作表Sheet
					org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet(sheetName);
					if (sheet == null) {
						continue;
					}
					List<MaintainDocumentExcelCell> lastExcelCells = new ArrayList<MaintainDocumentExcelCell>();
					if (departmentNames != null && !departmentNames.isEmpty()) {
						for (String departmentName : departmentNames) {
							if (isParameterEmpty(departmentName)) {
								continue;
							}
							// 循环行Row
							for (int rowNum = fromRowIndex; rowNum <= sheet.getLastRowNum(); rowNum++) {
								org.apache.poi.ss.usermodel.Row row = sheet.getRow(rowNum);
								if (row == null) {
									continue;
								}
								org.apache.poi.ss.usermodel.Cell cell = row.getCell(departmentNameCellIndex);
								String cellValue = "";
								if (cell == null) {
									continue;
								}
								/*
								 * 判断单元格是否为合并单元格，如果是合并单元格则调用从合并单元格取内容的方法
								 */
								if (isMergedRegion(sheet, rowNum, cell.getColumnIndex())) {
									// 获取合并单元格内容
									cellValue = getMergedRegionCellsValue(sheet, row.getRowNum(), cell.getColumnIndex());
								} else {
									cellValue = getExcelCellValue(cell);
								}
								if (!isParameterEmpty(cellValue) && cellValue.equals(departmentName)) {
									MaintainDocumentExcelCell lastExcelCell = new MaintainDocumentExcelCell();
									lastExcelCell.setRowIndex(rowNum);
									lastExcelCell.setColumnIndex(row.getLastCellNum());
									lastExcelCell.setSheetName(sheetName);
									lastExcelCell.setCellValue(cellValue);
									lastExcelCells.add(lastExcelCell);
								}
							}// for 循环行【Row】结束
						}// 遍历 List 集合：departmentNames的 for 循环结束
					}
					lastExcelCellBeans.put(sheetName, lastExcelCells);
				} // 遍历 Map 集合：conditions 的 for 循环结束
			}
		}
		return lastExcelCellBeans;
	}

	/**
	 * 
	 * @Title: eidtWorkbook
	 * @Description: 编辑对应Excel文档的（org.apache.poi.ss.usermodel.Workbook）对象
	 * @param workbook
	 * @param conditions
	 * @param editAttribute
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	public void eidtWorkbook(org.apache.poi.ss.usermodel.Workbook workbook, Map<String, Object> conditions, String editAttribute) {
		if (workbook != null) {
			if (conditions != null && !conditions.isEmpty()) {
				for (String sheetName : conditions.keySet()) {
					org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet(sheetName);
					if (sheet == null) {
						continue;
					}
					if (editAttribute.equals("EDIT_STYLE_ONLY")) {
						List<MaintainDocumentExcelCell> lastExcelCells = (List<MaintainDocumentExcelCell>) conditions.get(sheetName);
						if (lastExcelCells != null && !lastExcelCells.isEmpty()) {
							for (MaintainDocumentExcelCell lastExcelCell : lastExcelCells) {
								if (lastExcelCell == null || !(lastExcelCell.getSheetName().equals(sheetName))) {
									continue;
								}
								int rowIndex = lastExcelCell.getRowIndex();
								int lastCellNum = lastExcelCell.getColumnIndex();
								int firstCellNum = 0;
								if (sheetName.equals("36")) {
									firstCellNum = 4;
								} else {
									firstCellNum = 3;
								}
								org.apache.poi.ss.usermodel.Row row = sheet.getRow(rowIndex);
								if (row != null) {
									for (int i = firstCellNum; i < lastCellNum; i++) {
										org.apache.poi.ss.usermodel.Cell cell = row.getCell(i);
										if (cell == null) {
											continue;
										}
										changeExcelCellStyle(workbook, cell, IndexedColors.RED.getIndex());

										logger.info("更新页签 【 " + sheetName + " 】 ：第 （" + (rowIndex + 1) + "） 行，第（" + (getExcelColumnName(i)) + "）列的单元格背景色操作已完成！");
									}// 遍历所需变更单元格背景色的行的 for 循环结束
								}
							}
						}
					} else {
						// TODO
					}
				}// 遍历所需修改的Excel文档中的指定页签的 for 循环结束
			}
		}
	}

	/**
	 * 判断字符串是否为空(判断 null、""、"null"、"undefined"四种情况)
	 * 
	 * @param str
	 * @return 为空返回TRUE，不为空返回FALSE
	 * @return 列出方法的返回值列表（为空返回TRUE，不为空返回FALSE）
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	private boolean isParameterEmpty(String str) {
		return str == null || "".equals(str.trim()) || "null".equals(str.trim()) || "undefined".equals(str.trim());
	}

	/**
	 * 
	 * @Title: changeExcelCellStyle
	 * @Description: 上海审批流程配置文档中，涉及金额判断的区县部门需要设置背景色为：红色
	 * @param workbook
	 * @param toCell
	 * @param colorIndex
	 * @return: void
	 */
	private void changeExcelCellStyle(org.apache.poi.ss.usermodel.Workbook workbook, org.apache.poi.ss.usermodel.Cell toCell, short colorIndex) {
		CellStyle oldCellStyle = toCell.getCellStyle();
		CellStyle newCellStyle = workbook.createCellStyle();
		/*
		 * 设置单元格背景色
		 */
		org.springframework.beans.BeanUtils.copyProperties(oldCellStyle, newCellStyle);
		newCellStyle.setFillForegroundColor(colorIndex);
		newCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		toCell.setCellStyle(newCellStyle);
	}

	/**
	 * 
	 * @Title: getExcelCellValue
	 * @Description: 获取单元格的值
	 * @param cell
	 * @return
	 * @return: String
	 */
	private String getExcelCellValue(Cell cell) {
		if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
			/*
			 * 如果excel单元格存储的是 "excel函数" ，获取 "excel函数" 计算之后的结果
			 */
			try {
				return String.valueOf(cell.getNumericCellValue());
			} catch (IllegalStateException e) {
				return String.valueOf(cell.getRichStringCellValue());
			}
		} else {
			return String.valueOf(cell.getStringCellValue());
		}

	}

	/**
	 * 
	 * @Title: isMergedRegion
	 * @Description: 判断单元格是否为合并单元格
	 * @param sheet
	 * @param rowIndex
	 * @param columnIndex
	 * @return
	 * @return: boolean
	 */
	private boolean isMergedRegion(org.apache.poi.ss.usermodel.Sheet sheet, int rowIndex, int columnIndex) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (rowIndex >= firstRow && rowIndex <= lastRow) {
				if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取合并单元格的值
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	private String getMergedRegionCellsValue(org.apache.poi.ss.usermodel.Sheet sheet, int rowIndex, int columnIndex) {
		if (sheet != null) {
			int sheetMergeCount = sheet.getNumMergedRegions();
			for (int i = 0; i < sheetMergeCount; i++) {
				CellRangeAddress ca = sheet.getMergedRegion(i);
				if (ca == null)
					continue;
				int firstColumn = ca.getFirstColumn();
				int lastColumn = ca.getLastColumn();
				int firstRow = ca.getFirstRow();
				int lastRow = ca.getLastRow();
				if (rowIndex >= firstRow && rowIndex <= lastRow) {
					if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
						org.apache.poi.ss.usermodel.Row row = sheet.getRow(firstRow);
						org.apache.poi.ss.usermodel.Cell cell = row.getCell(firstColumn);
						return getExcelCellValue(cell);
					}
				}
			}
		}
		return null;
	}

	private String getExcelColumnName(int columnIndex) {
		String columnName = "";
		String[] columnNames = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		columnName = columnNames[columnIndex];
		return columnName;
	}

	public static void main(String[] args) {
		maintainDocumentPOIUtil = MaintainDocumentPOIUtil.getInstance();
		logger.info(maintainDocumentPOIUtil.getExcelColumnName(3));
	}
}
