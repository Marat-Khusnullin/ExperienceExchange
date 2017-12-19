package com.example.ruslanio.experienceexchange.network.body;

/**
 * Created by Ruslanio on 19.12.2017.
 */

public class CourseBody {

    private String title;
    private String summary;
    private String cover;
    private int interest_id;

    public CourseBody() {
    }

    public CourseBody(String title, String summary, String cover, int interest_id) {
        this.title = title;
        this.summary = summary;
        this.cover = cover;
        this.interest_id = interest_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getInterest_id() {
        return interest_id;
    }

    public void setInterest_id(int interest_id) {
        this.interest_id = interest_id;
    }
}
