
package com.example.ruslanio.experienceexchange.network.pojo.interest.send;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InterestSendResponce {

    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
