package com.aliyun.tair.springbootexample.model;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private String position;
    private String route;

    public User(String id, String name, String position, String route) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.route = route;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
            .append(id).append('\"');
        sb.append(",\"name\":\"")
            .append(name).append('\"');
        sb.append(",\"position\":\"")
            .append(position).append('\"');
        sb.append(",\"route\":\"")
            .append(route).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
