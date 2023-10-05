/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DOxlaj
 */
public class DateHandler {
    public Date getDateFromString(String stringDate) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        if(stringDate.length() != 0){
            Date formatedDate = formatter.parse(stringDate);
            return formatedDate;
        } else {
            return null;
        }
    }
    
    public Date getDateFromString(String stringDate, String format) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        if(stringDate.length() != 0){
            Date formatedDate = formatter.parse(stringDate);
            return formatedDate;
        } else {
            return null;
        }
    }
    
    public String getStringFromDate(Date date, String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
}
