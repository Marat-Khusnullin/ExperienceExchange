package com.example.ruslanio.experienceexchange.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.ruslanio.experienceexchange.database.model.User;

import java.util.List;

/**
 * Created by Ruslanio on 04.12.2017.
 */

@Dao
public interface UsersDao extends BaseDao<User>{

    @Query("SELECT * FROM table_users")
    List<User> getAll();

}
