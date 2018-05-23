package com.example.ruslanio.experienceexchange.network.pojo.lesson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Марат on 22.05.2018.
 */

public class LessonsByCourseResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private List<ResultByCourse> result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ResultByCourse> getResult() {
        return result;
    }

    public void setResult(List <ResultByCourse> result) {
        this.result = result;
    }
}
