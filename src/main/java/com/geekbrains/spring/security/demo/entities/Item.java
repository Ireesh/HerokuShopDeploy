package com.geekbrains.spring.security.demo.entities;

public class Item {
    private String content;

    public Item() {
    }

    public Item(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}