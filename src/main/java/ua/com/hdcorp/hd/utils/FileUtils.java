package ua.com.hdcorp.hd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {
   public static final String ROOT_PATH = "/";
   // public static final String ROOT_PATH = "C:";
    public static final String ROOT_FOLDER = "img";

    public static String convertDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(date);
    }
}
