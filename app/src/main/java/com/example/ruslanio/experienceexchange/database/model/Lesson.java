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
    private boolean isTemporary;
    private long courseId;
    @Ignore
    private List<LessonBlock> blocks;


    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public boolean isTemporary() {
        return isTemporary;
    }

    public void setTemporary(boolean temporary) {
        isTemporary = temporary;
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
