package com.aliyun.tair.springbootexample.model;

import java.io.Serializable;

public class Rank implements Serializable {
    private String city;
    double score;

    public Rank(String city, double score) {
        this.city = city;
        this.score = score;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
