
package com.example.ruslanio.experienceexchange.network.pojo.lesson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonAddedResponce {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private Result result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
