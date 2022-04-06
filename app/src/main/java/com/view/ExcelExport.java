package com.view;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.entities.Submission;
import com.util.UserPart;
public class ExcelExport extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(
           Map<String, Object> model,
           Workbook workbook, 
           HttpServletRequest request,
           HttpServletResponse response) throws Exception {
    	System.out.println("buildexceldocument method called");
       // define excel file name to be exported
       response.addHeader("Content-Disposition", "attachment;fileName=ParticipationData.xlsx");

       // read data provided by controller
       @SuppressWarnings("unchecked")
       List<UserPart> list = (List<UserPart>) model.get("list");

       // create one sheet 
       Sheet sheet = workbook.createSheet("Participation");

       // create row0 as a header
       Row row0 = sheet.createRow(0);
       row0.createCell(0).setCellValue("USER NAME");
       row0.createCell(1).setCellValue("USER EMAILID");
       row0.createCell(2).setCellValue("SCORE");
       row0.createCell(3).setCellValue("RANK");
       

       // create row1 onwards from List<T>
       int rowNum = 1;
       for(UserPart spec : list) {
           Row row = sheet.createRow(rowNum++);
           row.createCell(0).setCellValue(spec.getUser().getName());
           row.createCell(1).setCellValue(spec.getUser().getEmailid());
           row.createCell(2).setCellValue(spec.getParticipation().getScore());
           row.createCell(3).setCellValue(spec.getRank());
           
       }
    }
}

