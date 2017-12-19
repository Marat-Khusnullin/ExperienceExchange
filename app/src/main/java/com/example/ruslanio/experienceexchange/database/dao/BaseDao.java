package com.example.ruslanio.experienceexchange.database.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ruslanio.experienceexchange.database.model.BaseModel;
import com.example.ruslanio.experienceexchange.database.model.Interest;

import java.util.List;

/**
 * Created by Ruslanio on 08.12.2017.
 */

public interface BaseDao<T extends BaseModel> {

    @Delete
    void delete(T object);

    @Delete
    void delete(T... objects);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(T object);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(T... objects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long add(T object);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> add(T... objects);
}
