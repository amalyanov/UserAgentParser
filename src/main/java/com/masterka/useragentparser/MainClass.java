/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterka.useragentparser;

import java.io.IOException;

/**
 *
 * @author masterka
 */
public class MainClass {

    public static void main(String[] arg) throws IOException {
        String fileNameInput = arg[0];
        String fileNameOutput = "";
        int iColIn = 0;
        int iColOSOut = 0;
        int iColBrowserOut = 0;
        if (arg.length>=2){
            fileNameOutput = arg[1];
        }
        if (arg.length>=3){
            iColIn = Integer.parseInt(arg[2]);
        }
        if (arg.length>=4){
            iColOSOut = Integer.parseInt(arg[3]);
        }
        if (arg.length>=5){
            iColBrowserOut = Integer.parseInt(arg[4]);
        }
        
        ExcelClass excelClass = new ExcelClass(fileNameInput);
        excelClass.setiColIn(iColIn);
        excelClass.setiColOutBrowser(iColBrowserOut);
        excelClass.setiColOutOs(iColOSOut);
        excelClass.ProcessUA();
        excelClass.SaveFile(fileNameOutput);
       
    }
}
