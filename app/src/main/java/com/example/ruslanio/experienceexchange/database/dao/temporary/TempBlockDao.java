package com.example.ruslanio.experienceexchange.database.dao.temporary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.ruslanio.experienceexchange.database.dao.BaseDao;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempBlock;

import java.util.List;

/**
 * Created by Ruslanio on 17.12.2017.
 */
@Dao
public interface TempBlockDao extends BaseDao<TempBlock> {


    @Query("SELECT * FROM table_temporary_blocks WHERE tempLessonId = :id")
    List<TempBlock> getAllByLessonId(int id);

    @Query("SELECT * FROM table_temporary_blocks")
    List<TempBlock> getAll();
}
