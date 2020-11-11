package com.springboot.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


/**
 * 根据读取配置的excel判断是否为法定节假日，及法定工作日
 * @author Administrator
 *
 */
public class HolidayExcelUtil {
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private List<String> holidays = new ArrayList<String>();
    private List<String> workdays = new ArrayList<String>();
    private Date now = new Date();
    private transient static Logger logger = LoggerFactory.getLogger(HolidayExcelUtil.class);
    private final static String xls = "xls";  
    private final static String xlsx = "xlsx";  
    
    /**
     * 判断当天是否是工作日 (工作日：true；节假日：false)
     * @param filePath
     * @return
     */
    public boolean isWorkDay(String filePath){
        boolean flag = true;
        parseExcel(filePath);//读取excel中的节假日和工作日
        int dateType = getDateType();
        //如果excel不存在当前日期。判断是否周六日
        if(dateType==0){
            Calendar c = Calendar.getInstance();
            if(c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY||
                    c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
                flag = false;
            }
        }else{//如果存在当前日期，根据返回的类型判断
            if(dateType==1){//节假日
                flag = false;
            }
/*            else if(dateType==2){//工作日
                flag = true;
            }*/
        }
        logger.info("当前日期是："+(flag==true?"工作日":"节假日"));
        return flag;
    }
    
    /**
     * 根据判断当前时间是否是节假日还是工作日  (excel中不存在当前日期：0；节假日：1；工作日：2)
     * 如果当前日期在excel中的节假日和工作日都写了，默认的工作日
     * @return
     */
    private int getDateType(){
        int type = 0;
        String today = sdf.format(now);
        if(holidays.size()>0){
            for(String holiday:holidays){
                if(holiday.equals(today)){
                    type = 1;
                    break;
                }
            }
        }
        if(workdays.size()>0){
            for(String workday:workdays){
                if(workday.equals(today)){
                    type = 2;
                }
            }
        }
        return type;
    }
    
    
    /**
     * 读取excel中的节假日和工作日
     * @param filePath
     */
    private void parseExcel(String filePath){
        if(filePath==null||"".equals(filePath)){
            return ;
        }
        Workbook workbook = null;
        try {
        	File file = new File(filePath);
        	//检查文件
            checkFile(file);
        	
          //获得Workbook工作薄对象
            workbook = getWorkBook(file);
            if(workbook != null){
                for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                	//获得当前sheet工作表
                    Sheet sheet = workbook.getSheetAt(sheetNum);
                    if(sheet == null){
                        continue;
                    }
                    //获得当前sheet的开始行
                    int firstRowNum  = sheet.getFirstRowNum();
                    //获得当前sheet的结束行
                    int lastRowNum = sheet.getLastRowNum();
                    //获取第一列数据-节假日
                    for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                        //获得当前行
                        Row row = sheet.getRow(rowNum);
                        if(row == null){
                            continue;
                        }  
                        Cell cell2 = row.getCell(0);
                        //取得rowNum行的第一列
                        Date date = cell2.getDateCellValue();
                        String dateStr = sdf.format(date);
                        holidays.add(dateStr);
                    }
                    //获取第二列数据-工作日
                    for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                        //获得当前行
                        Row row = sheet.getRow(rowNum);
                        if(row == null){
                            continue;
                        }  
                        Cell cell2 = row.getCell(1);
                        //取得rowNum行的第一列
                        Date date = cell2.getDateCellValue();
                        String dateStr = sdf.format(date);
                        workdays.add(dateStr);
                    } 
                }
            }
            logger.info(holidays.toString());
            logger.info(workdays.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(workbook!=null){
                try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
                workbook = null;
            }
        }
    }
    
    //public static void checkFile(MultipartFile file) throws IOException{
    public static void checkFile(File file) throws IOException{  
        //判断文件是否存在  
        if(null == file){  
            logger.error("文件不存在！");  
            throw new FileNotFoundException("文件不存在！");  
        }  
        //获得文件名  
      //String fileName = file.getOriginalFilename();
    	String fileName = file.getName();  
        //判断文件是否是excel文件  
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){  
            logger.error(fileName + "不是excel文件");  
            throw new IOException(fileName + "不是excel文件");  
        }  
    }
    
    //public static Workbook getWorkBook(MultipartFile file) {
    public static Workbook getWorkBook(File file) {
        //获得文件名
        //String fileName = file.getOriginalFilename();
    	String fileName = file.getName();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            //InputStream is = file.getInputStream();
        	InputStream is = ((MultipartFile) file).getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(xls)){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(xlsx)){
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }
    
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 把数字当成String来读，避免出现1读成1.0的情况
		if (cell.getCellTypeEnum() == CellType.NUMERIC) {
			cell.setCellType(CellType.STRING);
		}
		// 判断数据的类型
		switch (cell.getCellTypeEnum()) {
		case NUMERIC: // 数字
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case BLANK: // 空值
			cellValue = "";
			break;
		case ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}  
    
    public static void main(String[] args) {
//        HolidayUtil h = new HolidayUtil();
//        boolean flag = h.isWorkDay("holidaySet.xls");
//        boolean flag = h.isWorkDay("");
    }
}
