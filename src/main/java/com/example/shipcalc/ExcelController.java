package com.example.shipcalc;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelController {

    @GetMapping("/excel")
    public String ExcelPage(){
        return "excel";
    }

    @PostMapping("/excel/ship")
    public String readShip(@RequestParam("file")MultipartFile file, Model model) throws IOException {

        List<ShipmentData> dataList = new ArrayList<>();
        List<String> listCountry = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if(!extension.equals("xlsx") && !extension.equals("xls")){
            throw new IOException("엑셀파일만 업르도 해주세요.");
        }

        Workbook workbook = null;

        if(extension.equals("xlsx")){
            workbook = new XSSFWorkbook(file.getInputStream());
        }else if (extension.equals("xls")){
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        //국가명 초기화
        Sheet worksheet = workbook.getSheetAt(0);
        Row rowCountry = worksheet.getRow(0);


        for (int i=1; i<rowCountry.getPhysicalNumberOfCells(); i++){
            listCountry.add(rowCountry.getCell(i).getStringCellValue());
        }

        System.out.println("rowCountry.getPhysicalNumberOfCells() : " + rowCountry.getPhysicalNumberOfCells());
        System.out.println("listCountry : " + listCountry);

//        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
//
//            Row row = worksheet.getRow(i);
//            ShipmentData data = new ShipmentData();
//
//            data.setNum((int) row.getCell(0).getNumericCellValue());
//            data.setName(row.getCell(1).getStringCellValue());
//
//            dataList.add(data);
//        }


        return "excelList";

    }



}
