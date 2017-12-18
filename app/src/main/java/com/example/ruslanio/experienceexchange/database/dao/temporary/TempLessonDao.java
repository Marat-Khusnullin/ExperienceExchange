package com.example.ruslanio.experienceexchange.database.dao.temporary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.ruslanio.experienceexchange.database.dao.BaseDao;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempLesson;

import java.util.List;

/**
 * Created by Ruslanio on 17.12.2017.
 */
@Dao
public interface TempLessonDao extends BaseDao<TempLesson> {

    @Query("SELECT * FROM table_temporary_lesson")
    List<TempLesson> getAll();
}
