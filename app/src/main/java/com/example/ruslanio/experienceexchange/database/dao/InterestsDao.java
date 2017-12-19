package com.example.ruslanio.experienceexchange.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.ruslanio.experienceexchange.database.model.Interest;

import java.util.List;

/**
 * Created by Ruslanio on 04.12.2017.
 */

@Dao
public interface InterestsDao extends BaseDao<Interest> {

    @Query("SELECT * FROM table_interests")
    List<Interest> getAll();

    @Query("DELETE FROM table_interests")
    void clearInterests();

    @Query("SELECT * FROM table_interests WHERE interestName = :name LIMIT 1")
    Interest getByName(String name);

    @Query("SELECT * FROM table_interests WHERE isClient = :daun")
    List<Interest> getByCurrentUser(boolean daun);
}
