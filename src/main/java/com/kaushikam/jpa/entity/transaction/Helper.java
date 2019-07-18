package com.kaushikam.jpa.entity.transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static Date dateFromString(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(format);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid format. Format should be 'dd/MM/yyyy'");
        }
    }
}
