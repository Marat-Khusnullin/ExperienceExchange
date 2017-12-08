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


}
