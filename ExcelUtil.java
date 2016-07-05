package com.aexp.wsgcat.seleniumframework;

import java.util.LinkedHashMap;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * To handle excel as input source interface.
 * Created by bvenkatr on 01/27/2015.
 */
public interface ExcelUtil {
	/**
     * sets the File name.
     * @param fileName Name of the file to pass to.
     */
	void setExcelWorkBook(final String fileName);
	/**
     * get the file Name.
     * @return workbook object.
     */
	Workbook getExcelWorkBook();
	 /**
     * sets the sheet Name.
     * @param sheetName Name of the sheet to pass to.
     */
	void setSheetByName(final String sheetName);
	/**
     * get the sheet Name.
     * @return sheet object.
     */
	Sheet getSheetByName();
	 /**
     * sets the sheet number.
     * @param sheetNum Num of the sheet to pass to.
     */
	void setSheetByNum(final int sheetNum);
	/**
     * get the sheet number.
     * @return sheet object.
     */
	Sheet getSheetByNum();
	 /**
     * sets the row number.
     * @param rowNum Number of the row to pass to.
     */
	void setRow(final int rowNum);
	/**
     * get the row num.
     * @return row object.
     */
	Row getRow();
	/**
     * gets the data from all rows corresponding to a column.
     * @param testCaseId test case Id for finding row data\object.
     * @return LinkedHashmap with key value pairs of column name with value.
     */
	LinkedHashMap<String, Object> getCellsByTestCaseID(final String testCaseId);
	/**
     * find the row number corresponding to a text match.
     * @param searchString string to find in a row.
     */
	void findRow(final String searchString);
	/**
     * gets the cell value for corresponding Row,col,sheet.
     * @param rowNum row num.
     * @param colNum col num.
     * @return Row object.
     */
	Object getCellValue(final int rowNum, final int colNum);
	/**
     * gets the cell value for corresponding TCID and Column name.
     * @param testCaseId test case Id for finding row data\object.
     * @param columnName to find the corresponding cell value.
     * @return cell value.
     */
	Object getCellValueByColName(final String testCaseId, final String columnName);
}
