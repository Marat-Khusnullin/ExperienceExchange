package com.example.ruslanio.experienceexchange.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ruslanio on 29.11.2017.
 */

@Entity(tableName = "table_users")
public class User extends BaseModel{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
