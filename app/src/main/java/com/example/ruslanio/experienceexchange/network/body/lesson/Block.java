package com.example.ruslanio.experienceexchange.network.body.lesson;

/**
 * Created by Ruslanio on 19.12.2017.
 */

public class Block {

    private String type;
    private String content;
    private int number;

    public Block() {
    }

    public Block(String type, String content, int number) {
        this.type = type;
        this.content = content;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
