package com.example.ruslanio.experienceexchange.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

/**
 * Created by Ruslanio on 17.12.2017.
 */

@Entity(tableName = "table_lessons",foreignKeys = @ForeignKey(
        entity = Course.class,
        parentColumns = "id",
        childColumns = "courseId"))
public class Lesson extends BaseModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private int count;

    private long courseId;
    @Ignore
    private List<LessonBlock> blocks;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LessonBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<LessonBlock> blocks) {
        this.blocks = blocks;
    }
}
