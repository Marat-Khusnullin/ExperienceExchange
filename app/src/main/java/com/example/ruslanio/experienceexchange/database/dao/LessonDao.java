package com.example.ruslanio.experienceexchange.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.ruslanio.experienceexchange.database.model.Lesson;

import java.util.List;

/**
 * Created by Ruslanio on 17.12.2017.
 */
@Dao
public interface LessonDao extends BaseDao<Lesson> {

    @Query("SELECT * FROM table_lessons WHERE courseId = :id")
    List<Lesson> getLessonsByCourseId(int id);
}
