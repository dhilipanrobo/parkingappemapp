package com.skyparking.admin.parkingappemapp;

import java.util.ArrayList;

public class velreplist {
    public int get;

    public velreplist() {
    }

    public String getTokenno() {
        return tokenno;
    }

    public String getDate_in() {
        return Date_in;
    }

    public String getDate_out() {
        return Date_out;
    }

    public String getTime_in() {
        return Time_in;
    }

    public String getTime_Out() {
        return Time_Out;
    }

    public String getNo_Day() {
        return No_Day;
    }

    public String getAmount() {
        return Amount;
    }

    public void setTokenno(String tokenno) {
        this.tokenno = tokenno;
    }

    public void setDate_in(String date_in) {
        Date_in = date_in;
    }

    public void setDate_out(String date_out) {
        Date_out = date_out;
    }

    public void setTime_in(String time_in) {
        Time_in = time_in;
    }

    public void setTime_Out(String time_Out) {
        Time_Out = time_Out;
    }

    public void setNo_Day(String no_Day) {
        No_Day = no_Day;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getV_NO() {
        return V_NO;
    }

    public void setV_NO(String v_NO) {
        V_NO = v_NO;
    }

    String V_NO;
    String tokenno;
    String Date_in;
    String Date_out;
    String Time_in;
    String Time_Out;
    String No_Day;
    String Amount;

    public String getV_Type() {
        return V_Type;
    }

    public void setV_Type(String v_Type) {
        V_Type = v_Type;
    }

    String V_Type;

    public velreplist(String tokenno){
        this.tokenno=tokenno;
        this.Date_in=Date_in;
        this.Date_out=Date_out;
        this.Time_in=Time_in;
        this.Time_Out=Time_Out;
        this.No_Day=No_Day;
        this.Amount=Amount;
        this.V_NO=V_NO;
        this.V_Type=V_Type;

    }
}

