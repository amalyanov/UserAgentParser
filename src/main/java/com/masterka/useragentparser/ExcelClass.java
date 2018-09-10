/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterka.useragentparser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author masterka
 */
public class ExcelClass {
    HSSFWorkbook workBook;
    HSSFSheet workSheet;
    int iColIn; //Column number with User Agent Information in raw format
    int iColOutOs;//Column number for OS output processed User Agent information
    int iColOutBrowser;//Column number for Browser output processed User Agent information
    String fileName; //FileName of file with data

    public int getiColOutBrowser() {
        return iColOutBrowser;
    }

    public void setiColOutBrowser(int iColOutBrowser) {
        this.iColOutBrowser = iColOutBrowser;
    }

    public int getiColIn() {
        return iColIn;
    }

    public void setiColIn(int iColIn) {
        this.iColIn = iColIn;
    }

    public int getiColOutOs() {
        return iColOutOs;
    }

    public void setiColOutOs(int iColOutOs) {
        this.iColOutOs = iColOutOs;
    }

    public ExcelClass(String fileName) throws IOException {
        this.fileName = fileName;
        workBook = new HSSFWorkbook(new FileInputStream(fileName));
        workSheet = workBook.getSheetAt(0);
    }
    
    /**
     *
     */
    public void ProcessUA() throws IOException{
        try {
            if ((getiColIn()==0)||(getiColOutBrowser()==0)||(getiColOutOs()==0)) {
                throw new IllegalParameterValue("One of parameters doesn't have non-zero vallue.");
            }
            ParserClass parser = new ParserClass();
            FormulaEvaluator evaluator = workBook.getCreationHelper().createFormulaEvaluator();
            Iterator <Row> itRow = workSheet.iterator();
            while (itRow.hasNext()) {
                Row currentRow = itRow.next();
                System.out.println(Integer.toString(currentRow.getRowNum()));
                Cell userAgentCell = currentRow.getCell(iColIn);
                CellValue cellValue = evaluator.evaluate(userAgentCell);
                
                String uaInformation = cellValue.getStringValue();
                Cell OSCell = currentRow.getCell(iColOutOs);
                OSCell.setCellValue(parser.ParseToString(uaInformation, parser.C_OS));
                Cell OSBrowser = currentRow.getCell(iColOutBrowser);
                OSBrowser.setCellValue(parser.ParseToString(uaInformation, parser.C_BROWSER));
            }
        }
        catch (IllegalParameterValue | NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void SaveFile(String fileName) throws IOException{
        FileOutputStream fos;
        fos = new FileOutputStream((fileName.equals("")? this.fileName : fileName));
        workBook.write(fos);
        workBook.close();
    }
    
    
}
