package com.example.ruslanio.experienceexchange.network.pojo.course.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Марат on 22.05.2018.
 */

public class CourseResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private CourseResult result = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CourseResult getResult() {
        return result;
    }

    public void setResult(CourseResult result) {
        this.result = result;
    }

}
