package com.example.wether.db;

public class DataBaseBean {
    public String city;
    public String content;
    public int id;

    public DataBaseBean() {
    }

    public DataBaseBean(String city, String content, int id) {
        this.city = city;
        this.content = content;
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
