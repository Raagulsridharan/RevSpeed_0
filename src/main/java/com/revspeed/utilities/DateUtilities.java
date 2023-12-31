package com.revspeed.utilities;

public class DateUtilities {
    public static java.sql.Date convertToSqlDate(java.util.Date utilDate) {
        // Convert java.util.Date to java.sql.Date
        return new java.sql.Date(utilDate.getTime());
    }
    public static java.util.Date convertToUtilDate(java.sql.Date sqlDate) {
        // Convert java.util.Date to java.sql.Date
        return new java.util.Date(sqlDate.getTime());
    }
}
