package com.example.ruslanio.experienceexchange.database.model.temporary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.ruslanio.experienceexchange.database.model.BaseModel;

import java.util.List;

/**
 * Created by Ruslanio on 17.12.2017.
 */

@Entity(tableName = "table_temporary_lesson")
public class TempLesson extends BaseModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int count;
    @Ignore
    private List<TempBlock> blocks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<TempBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<TempBlock> blocks) {
        this.blocks = blocks;
    }
}
