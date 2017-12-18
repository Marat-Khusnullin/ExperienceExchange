package com.example.ruslanio.experienceexchange.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.ruslanio.experienceexchange.database.model.Course;

import java.util.List;

/**
 * Created by Ruslanio on 13.12.2017.
 */

@Dao
public interface CourseDao extends BaseDao<Course> {

    @Query("SELECT * FROM table_courses WHERE my = :isMy")
    List<Course> getAll(boolean isMy);

}
