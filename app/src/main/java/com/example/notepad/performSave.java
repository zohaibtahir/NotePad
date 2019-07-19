package com.example.notepad;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class performSave {
   public static ArrayList<utility> myList = new ArrayList<>();
   public static int color;

   public static String getCurrentDate(){
       Date c = Calendar.getInstance().getTime();
       SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
       String formatedDate = df.format(c);
       return formatedDate;
   }
}
