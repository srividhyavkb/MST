package Resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;
public class formatAsTable  {
	
	public final String header1 = "No. of Test Cases Executed";
	public final String header2 = "No. of Test Cases Passed";
	public final String header3 = "No. of Test Cases Executed";
	public final String header4 = "No. of Test Cases Executed";
	public static void createTestExecutionTable(int testExecuted,int testPassed,int testFailed, int testSkipped) throws FileNotFoundException, IOException {
		/* Start with Creating a workbook and worksheet object */
		Workbook wb = new XSSFWorkbook();
		XSSFSheet sheet = (XSSFSheet) wb.createSheet();
		/* Define the data range including headers */
		AreaReference my_data_range = new AreaReference(new CellReference(0, 0), new CellReference(4, 2), null);

		/* Create an object of type XSSFTable */
		XSSFTable my_table = sheet.createTable(my_data_range);

		/* get CTTable object */
		CTTable cttable = my_table.getCTTable();

		/* Let us define the required Style for the table */
		CTTableStyleInfo table_style = cttable.addNewTableStyleInfo();
		table_style.setName("TableStyleMedium9");

		/* Set Table Style Options */
		table_style.setShowColumnStripes(true); // showColumnStripes=0
		table_style.setShowRowStripes(true); // showRowStripes=1

		/* Set Range to the Table */
		cttable.setRef(my_data_range.formatAsString());
		cttable.setDisplayName("MYTABLE"); /* this is the display name of the table */
		cttable.setName("Test"); /* This maps to "displayName" attribute in <table>, OOXML */
		cttable.setId(1L); // id attribute against table as long value

		CTTableColumns columns = cttable.addNewTableColumns();
		columns.setCount(4L); // define number of columns

		/* Define Header Information for the Table */
		for (int i = 0; i < 4; i++) {
			CTTableColumn column = columns.addNewTableColumn();
			column.setName("Column" + i);
			column.setId(i + 1);
		}
		
		
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)10);
		font.setFontName("Arial");
	    font.setColor(IndexedColors.BLACK.getIndex());
	    font.setBold(true);
	    font.setItalic(true);
	    style.setFont(font);

		XSSFRow row1 = sheet.createRow(0);
		row1.setRowStyle(style);
		XSSFCell localXSSFCell1, localXSSFCell2, localXSSFCell3, localXSSFCell4;
		localXSSFCell1 = row1.createCell(0);
		localXSSFCell2 = row1.createCell(1);
		localXSSFCell3 = row1.createCell(2);
		localXSSFCell4 = row1.createCell(3);
		localXSSFCell1.setCellValue("No. of Test Cases Executed");
		localXSSFCell2.setCellValue("No. of Test Cases Passed");
		localXSSFCell3.setCellValue("No. of Test Cases Failed");
		localXSSFCell4.setCellValue("No of Test Cases Skipped");

		XSSFRow row2 = sheet.createRow(1);
		XSSFCell localXSSFCell21, localXSSFCell22, localXSSFCell23, localXSSFCell24;
		localXSSFCell21 = row2.createCell(0);
		localXSSFCell22 = row2.createCell(1);
		localXSSFCell23 = row2.createCell(2);
		localXSSFCell24 = row2.createCell(3);
		localXSSFCell21.setCellValue(testExecuted);
		localXSSFCell22.setCellValue(testPassed);
		localXSSFCell23.setCellValue(testFailed);
		localXSSFCell24.setCellValue(testSkipped);
		/* Write output as File */
		FileOutputStream fileOut = new FileOutputStream(
				"C:/Users/Somnath Baul/eclipse-workspace/MobileSeeTestWebAutomation/Excel_Format_As_Table.xlsx");
		wb.write(fileOut);
		fileOut.close();
	}

	
		
	}

