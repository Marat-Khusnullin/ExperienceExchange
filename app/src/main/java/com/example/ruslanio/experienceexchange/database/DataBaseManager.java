package com.example.ruslanio.experienceexchange.database;

import android.content.Context;

import com.example.ruslanio.experienceexchange.database.abstracts.ExExDatabase;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.database.model.User;

import java.util.List;

/**
 * Created by Ruslanio on 13.12.2017.
 */

public class DataBaseManager {

    private static DataBaseManager MANAGER;
    private ExExDatabase mDatabase;

    private DataBaseManager(Context context) {
        mDatabase = ExExDatabase.getInstance(context);
    }

    public static DataBaseManager getInstance(Context context){
        if (MANAGER == null){
            MANAGER = new DataBaseManager(context);
        }
        return MANAGER;
    }

    public List<Interest> getAllInterests(){
        return mDatabase.getInterestsDao().getAll();
    }

    public void insertInterest(Interest interest){
        mDatabase.getInterestsDao().add(interest);
    }

    public void insertInterest(List<Interest> interests){
        mDatabase.getInterestsDao().add(interests.toArray(new Interest[interests.size()]));
    }

    public void setNewInterests(List<Interest> interests){
        mDatabase.getInterestsDao().clearInterests();
        insertInterest(interests);
    }

    public User getCurrentUser(){
        return mDatabase.getUsersDao().getUser().get(0);
    }

    public void updateCurrentUser(User user){
        mDatabase.getUsersDao().clearTable();
        mDatabase.getUsersDao().add(user);
    }

    public String getCurrentToken(){
        return getCurrentUser().getToken();
    }

    public Interest getInterestByName(String name){
        return mDatabase.getInterestsDao().getByName(name);
    }

    public void updateInterest(Interest interest){
        mDatabase.getInterestsDao().update(interest);
    }
}
