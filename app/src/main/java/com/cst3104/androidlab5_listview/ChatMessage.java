package com.cst3104.androidlab5_listview;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatMessage {


    private String text;
    private int status;
    private String currentDateandTime;

    public ChatMessage(String text, int status) {
    this.status = status;
    this.text = text;

}
 public void setCurrentDateandTime () {

     Date timeNow = new Date(); //when was this code run
     SimpleDateFormat sdf = new SimpleDateFormat(MainActivity.mycontext.getString(R.string.date_pattern), Locale.getDefault());
     currentDateandTime = sdf.format(timeNow); //convert date to String
 }

 public String getMessageText () {
        return text;
 }

 public String getCurrentDateandTime () {
        return currentDateandTime;
 }

 public int getStatus () {
        return status;
 }

 public String getStringStatus () {
        String stringStatus;
        if (status == 0) {
            stringStatus = MainActivity.mycontext.getString(R.string.message_status1);
        }
        else {
            stringStatus = MainActivity.mycontext.getString(R.string.message_status2);
        }
        return stringStatus;
 }

    @NonNull
    @Override
    public String toString() {
        String thisMessage = MainActivity.mycontext.getString(R.string.message_in_string1)
                + text + MainActivity.mycontext.getString(R.string.message_in_string2) +
                getStringStatus() + MainActivity.mycontext.getString(R.string.message_in_string3)
                + getCurrentDateandTime();
        return thisMessage;
    }

}
