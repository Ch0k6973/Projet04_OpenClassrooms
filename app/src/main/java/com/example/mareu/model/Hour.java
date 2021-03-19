package com.example.mareu.model;


public class Hour {

    private String hour;

    public Hour(int hour, int min){
        this.hour = "" + atoiHour(hour) + "H" + atoiMin(min) + "";
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getHour() {
        return hour;
    }

    private String atoiHour(int hour){
        String sHour;
        if (hour == 0)
            sHour = "00";
        else if (0 < hour && hour < 10)
            sHour = "0" + hour + "";
        else
            sHour = "" + hour + "";
        return  sHour;
    }

    private String atoiMin(int min){
        String sMin;
        if (min == 0)
            sMin = "00";
        else if (0 < min && min < 10)
            sMin = "0" + min + "";
        else
            sMin = "" + min + "";
        return  sMin;
    }
}
