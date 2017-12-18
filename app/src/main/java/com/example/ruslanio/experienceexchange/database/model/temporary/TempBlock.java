package com.example.ruslanio.experienceexchange.database.model.temporary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.ruslanio.experienceexchange.database.model.BaseModel;

/**
 * Created by Ruslanio on 17.12.2017.
 */

@Entity(tableName = "table_temporary_blocks",
        foreignKeys = @ForeignKey(
                entity = TempLesson.class,
                parentColumns = "id",
                childColumns = "tempLessonId"))
public class TempBlock extends BaseModel {

    @PrimaryKey
    private int id;
    private int type;
    private String value;
    private int order;
    private long tempLessonId;

    public long getTempLessonId() {
        return tempLessonId;
    }

    public void setTempLessonId(long tempLessonId) {
        this.tempLessonId = tempLessonId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
