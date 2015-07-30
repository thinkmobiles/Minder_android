package com.example.minder_android.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.minder_android.core.Const.DATE_TIME_FORMAT;

/**
 * Created by Max on 23.07.15.
 */
public class DateTimeConverter {
    public static String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date = new Date();
       return dateFormat.format(date);
    }
    public static String longToDateString(final Long _timeInMs, SimpleDateFormat _dateFormat) {
        if(_timeInMs == null) return "";
        final Calendar calendar = Calendar.getInstance();
        //calendar.setTimeZone(TimeZone.getDefault());
        calendar.setTimeInMillis(_timeInMs);
        return _dateFormat.format(calendar.getTime());
    }

    public static Date convertFromUTC(final Date _utcDate) throws ParseException {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
        df.setTimeZone(Calendar.getInstance().getTimeZone());
        final String utcDateString = df.format(_utcDate);
        df.setTimeZone(TimeZone.getTimeZone("UTC+0000"));
        return df.parse(utcDateString);
    }

    public static Date convertToUTC(final Date _localDate) throws ParseException {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
        df.setTimeZone(TimeZone.getTimeZone("UTC+0000"));
        final String localDateString = df.format(_localDate);
        df.setTimeZone(TimeZone.getDefault());
        return df.parse(localDateString);
    }

    public static Date getDateFromString(String _string, String _format) throws ParseException {
        DateFormat format = new SimpleDateFormat(_format, Locale.getDefault());
        return format.parse(_string);
    }
}
