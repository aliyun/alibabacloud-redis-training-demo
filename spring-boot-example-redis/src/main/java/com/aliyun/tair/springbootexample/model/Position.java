package com.aliyun.tair.springbootexample.model;

import java.io.Serializable;

public class Position implements Serializable {
    private String name;
    private String city;
    private int status;

    public Position() {
    }

    public Position(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Position(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
