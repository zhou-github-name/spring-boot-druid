package com.springboot.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;

public class ExcelHeaderUtils {
	
	/**
	 * 表头样式及字体策略
	 * @return
	 */
	public static WriteCellStyle excelHeaderSet() {
		WriteCellStyle headWriteCellStyle = new WriteCellStyle();
		// 垂直居中,水平居中
		headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
		// 上下左右边框及颜色
		headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		headWriteCellStyle.setBorderLeft(BorderStyle.THIN);
		headWriteCellStyle.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headWriteCellStyle.setBorderTop(BorderStyle.THIN);
		headWriteCellStyle.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headWriteCellStyle.setBorderRight(BorderStyle.THIN);
		headWriteCellStyle.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headWriteCellStyle.setBorderBottom(BorderStyle.THIN);
		headWriteCellStyle.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		// 字体策略
		WriteFont headWriteFont = new WriteFont();
		//字体
		headWriteFont.setFontName("等线");
		// 字体大小
		headWriteFont.setFontHeightInPoints((short) 11);
		// 取消加粗
		headWriteFont.setBold(false);
		headWriteCellStyle.setWriteFont(headWriteFont);
		// 取消 自动换行
		headWriteCellStyle.setWrapped(false);
		return headWriteCellStyle;
	}
	
	/**
	 * 内容样式及字体策略
	 * @return
	 */
	public static WriteCellStyle excelContentSet() {
		WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
		// 垂直居中,水平居中
		contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
		//contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
		//contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
		//contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
		//contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
		//contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
		// 字体策略
		WriteFont contentWriteFont = new WriteFont();
		//字体
		contentWriteFont.setFontName("等线");
		// 字体大小
		contentWriteFont.setFontHeightInPoints((short) 11);
		contentWriteCellStyle.setWriteFont(contentWriteFont);
		return contentWriteCellStyle;
	}
	
	
	
	
	
	
	
	
	
	
	

}
