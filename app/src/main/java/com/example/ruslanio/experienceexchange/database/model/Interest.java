package com.example.ruslanio.experienceexchange.database.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ruslanio on 29.11.2017.
 */

@Entity(tableName = "table_interests")
public class Interest extends BaseModel{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String interestName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }
}
