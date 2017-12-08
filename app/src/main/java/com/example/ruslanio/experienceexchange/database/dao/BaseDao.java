package com.example.ruslanio.experienceexchange.database.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

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

    @Delete
    void update(T object);

    @Delete
    void update(T... objects);

    @Insert
    void add(T object);

    @Insert
    void add(T... objects);
}
