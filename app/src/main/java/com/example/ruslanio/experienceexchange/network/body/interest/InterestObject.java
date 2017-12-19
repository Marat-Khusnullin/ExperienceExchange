package com.example.ruslanio.experienceexchange.network.body.interest;

/**
 * Created by Ruslanio on 19.12.2017.
 */

public class InterestObject {

    private int id;
    private String name;
    private double percentage;

    public InterestObject() {
    }

    public InterestObject(int id, String name, double percentage) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
