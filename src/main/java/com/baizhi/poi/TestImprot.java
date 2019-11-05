package com.baizhi.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestImprot {
    public static void main(String[] args) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("E:/JAVA166班/JAVA166班/课件/后期项目/user.xls")));
        HSSFSheet sheet = workbook.getSheet("学生信息");
        List<User> list = new ArrayList<>();
        for (int i = 0; i <= sheet.getLastRowNum() ; i++) {
            User user = new User();
            HSSFRow row = sheet.getRow(i);

            HSSFCell cell = row.getCell(0);
            String id = cell.getStringCellValue();
            user.setId(id);

            HSSFCell cell1 = row.getCell(1);
            String name = cell1.getStringCellValue();
            user.setName(name);

            HSSFCell cell2 = row.getCell(2);
            Date date = cell2.getDateCellValue();
            user.setBir(date);

            list.add(user);


        }
        for (User user : list) {
            System.out.println(user);
        }
    }
}
