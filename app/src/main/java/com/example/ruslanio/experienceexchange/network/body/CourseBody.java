package com.example.ruslanio.experienceexchange.network.body;

/**
 * Created by Ruslanio on 19.12.2017.
 */

public class CourseBody {

    private String title;
    private String summary;
    private String cover;
    private int[] interests;

    public CourseBody() {
    }

    public CourseBody(String title, String summary, String cover, int[] interests) {
        this.title = title;
        this.summary = summary;
        this.cover = cover;
        this.interests = interests;
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

    public int[] getInterest_id() {
        return interests;
    }

    public void setInterest_id(int[] interest_id) {
        this.interests = interest_id;
    }
}
