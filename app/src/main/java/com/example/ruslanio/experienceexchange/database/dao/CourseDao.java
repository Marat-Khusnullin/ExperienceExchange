package com.example.ruslanio.experienceexchange.database.dao;

import android.arch.persistence.room.Query;

import com.example.ruslanio.experienceexchange.database.model.Course;

import java.util.List;

/**
 * Created by Ruslanio on 13.12.2017.
 */

public interface CourseDao {

    @Query("SELECT * FROM table_courses")
    List<Course> getAll();
}
