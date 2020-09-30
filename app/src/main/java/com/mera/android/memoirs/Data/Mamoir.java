package com.mera.android.memoirs.Data;

/**
 * Created by AmiraRabea on 7/25/2018.
 */

public class Mamoir {
    private int id;
    private String address;
    private String topic;
    private String date;

        // Create constructor with no-arguments
    public Mamoir() {
    }

        // Create constructor with no-arguments
    public Mamoir(int id,String address, String topic, String date) {
        this.address = address;
        this.topic = topic;
        this.date = date;
        this.id = id;
    }

        // getAddress method
    public String getAddress() {
        return address;
    }

        // setAddress method
    public void setAddress(String address) {
        this.address = address;
    }

        // getTopic method
    public String getTopic() {
        return topic;
    }

        // setTopic method
    public void setTopic(String topic) {
        this.topic = topic;
    }

        // getData method
    public String getData() {
        return date;
    }

        // setData method
    public void setData(String data) {
        this.date = data;
    }

        // getId method
    public int getId() {
        return id;
    }

        // setId method
    public void setId(int id) {
        this.id = id;
    }

        // getDate method
    public String getDate() {
        return date;
    }

        // setData method
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return address+"\n"+topic+"\n"+date;
    }

}
