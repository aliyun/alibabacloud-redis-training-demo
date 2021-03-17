package com.aliyun.tair.springbootexample.model;

import java.io.Serializable;

public class News implements Serializable {
    private String name;
    private String content;

    public News(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
