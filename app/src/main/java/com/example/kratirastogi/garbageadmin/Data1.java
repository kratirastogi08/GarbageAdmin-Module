package com.example.kratirastogi.garbageadmin;

import android.widget.EditText;

public class Data1 {
    String id,name;
    double lat,lng;
    Date date;
    String status,numplate,number;


    public Data1(String id, String name, double lat, double lng,String status,String numplate,String number,Date date ) {

        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
       this.date=date;
        this.status=status;
        this.numplate=numplate;
        this.number=number;



    }
public Data1()
{

}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumplate() {
        return numplate;
    }

    public void setNumplate(String numplate) {
        this.numplate = numplate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
