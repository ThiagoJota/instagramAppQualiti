package com.pitang.mobile.infraestrutura.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    public static String formatoHoraMinuto(Date date) {
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public static String formatoDataCompleta(Long date) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
    }

}
