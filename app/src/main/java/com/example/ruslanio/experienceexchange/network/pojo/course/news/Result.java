
package com.example.ruslanio.experienceexchange.network.pojo.course.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("interest_id")
    @Expose
    private Integer interestId;
    @SerializedName("students_number")
    @Expose
    private Integer studentsNumber;
    @SerializedName("lessons_number")
    @Expose
    private Integer lessonsNumber;
    @SerializedName("likers_number")
    @Expose
    private Integer likersNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public Integer getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(Integer studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public Integer getLessonsNumber() {
        return lessonsNumber;
    }

    public void setLessonsNumber(Integer lessonsNumber) {
        this.lessonsNumber = lessonsNumber;
    }

    public Integer getLikersNumber() {
        return likersNumber;
    }

    public void setLikersNumber(Integer likersNumber) {
        this.likersNumber = likersNumber;
    }

}
