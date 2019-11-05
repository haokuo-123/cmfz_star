package com.baizhi.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class TestPoi {
    public static void main(String[] args) throws Exception {
        //准备excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建单元格，合并指定区域
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, 0, 4);
        //设置日期样式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd HH:mm:ss");
        //获取单元格样式对象
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //绑定日期格式
        cellStyle.setDataFormat(format);
        //设置样式为居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //创建字体对象
        HSSFFont font = workbook.createFont();
        //设置字体加粗
        font.setBold(true);
        //设置字体颜色红色
        font.setColor(Font.COLOR_RED);
        //字体绑定到样式身上
        cellStyle.setFont(font);
        //创建工作表
        HSSFSheet sheet = workbook.createSheet("测试");
        //当前工作表中单元格合并
        sheet.addMergedRegion(cellRangeAddress);
        //当前工作表设置列宽
        sheet.setColumnWidth(0,25*256);
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        //创建单元格
        HSSFCell cell = row.createCell(0);
        //写值
        cell.setCellValue(new Date());
        //设置当前单元格样式
        cell.setCellStyle(cellStyle);
        workbook.write(new FileOutputStream(new File("E:/JAVA166班/JAVA166班/课件/后期项目/test.xls")));



    }
}
