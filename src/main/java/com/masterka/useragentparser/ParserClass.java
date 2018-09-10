/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterka.useragentparser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua_parser.Parser;
import ua_parser.Client;

/**
 *
 * @author masterka
 */
public class ParserClass {
    public final int C_OS = 0;
    public final int C_BROWSER = 1;
    
    public ParserClass() {
    }
    
    public String ParseToString(String uagent){
        String strReturn = "";
        uagent = uagent.replace("'","");
        Parser uaParser;
        try {
            uaParser = new Parser();
            Client c = uaParser.parse(uagent);
            strReturn = nullString(c.userAgent.family);
            strReturn = strReturn.concat(" ").concat(nullString(c.userAgent.major));
            strReturn = strReturn.concat(";").concat(nullString(c.os.family)).concat(" ").concat(nullString(c.os.major));
        } catch (IOException ex) {
            Logger.getLogger(ParserClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return strReturn;
    }
    public String ParseToString(String uagent, int iTypeOutput){
        String strReturn = "";
        Parser uaParser;
        try {
            uaParser = new Parser();
            Client c = uaParser.parse(uagent);
            if (iTypeOutput==C_OS) {
                strReturn = strReturn.concat(nullString(c.os.family)).concat(" ").concat(nullString(c.os.major));
            } else {
                strReturn = nullString(c.userAgent.family);
                strReturn = strReturn.concat(" ").concat(nullString(c.userAgent.major));
            }
        } catch (IOException ex) {
            Logger.getLogger(ParserClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return strReturn;
    }

    private String nullString(String arg)
    {
        return (arg == null? "":arg);
    }
    
}
