package com.example.ruslanio.experienceexchange.network.body.interest;

import java.util.List;

/**
 * Created by Ruslanio on 19.12.2017.
 */

public class InterestBody {

    private List<InterestObject> interests;

    public InterestBody(List<InterestObject> interests) {
        this.interests = interests;
    }

    public InterestBody() {
    }

    public List<InterestObject> getInterests() {
        return interests;
    }

    public void setInterests(List<InterestObject> interests) {
        this.interests = interests;
    }
}
