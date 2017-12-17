package com.example.ruslanio.experienceexchange.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.ruslanio.experienceexchange.database.model.LessonBlock;

import java.util.List;

/**
 * Created by Ruslanio on 17.12.2017.
 */
@Dao
public interface LessonBlockDao  extends BaseDao<LessonBlock> {

    @Query("SELECT * FROM table_lesson_block WHERE lessonId = :id")
    List<LessonBlock> getAllByLessonId(int id);

}
