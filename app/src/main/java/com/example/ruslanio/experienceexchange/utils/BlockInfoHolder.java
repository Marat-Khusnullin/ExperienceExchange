package com.example.ruslanio.experienceexchange.utils;

/**
 * Created by Ruslanio on 18.12.2017.
 */

public class BlockInfoHolder  {
    
    private int count;
    private int type;
    private String value;

    public BlockInfoHolder(int count, int type, String value) {
        this.count = count;
        this.type = type;
        this.value = value;
    }

    public BlockInfoHolder() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
