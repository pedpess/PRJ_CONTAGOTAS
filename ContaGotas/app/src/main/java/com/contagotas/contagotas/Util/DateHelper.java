package com.contagotas.contagotas.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 10/26/15.
 */
public class DateHelper {

    public static String getDateCurret(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }
}
