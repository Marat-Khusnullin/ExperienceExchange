package com.example.ruslanio.experienceexchange.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ruslanio on 17.12.2017.
 */
@Entity(tableName = "table_lesson_block",
        foreignKeys = @ForeignKey(
        entity = Lesson.class,
        parentColumns = "id",
        childColumns = "lessonId",
        onDelete = ForeignKey.CASCADE))
public class LessonBlock extends BaseModel {

    @PrimaryKey
    private int id;
    private long lessonId;
    private int orderNumber;
    private int type;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
