package ua.com.hdcorp.hd.web;

import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
//        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        calendar.set(2019, 9, 28, 0, 0,0 );
        Date date = calendar.getTime();
        System.out.println(calendar.getTime());
        System.out.println(date.getTime());


    }
}
