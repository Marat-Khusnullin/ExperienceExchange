package com.example.ruslanio.experienceexchange.database.model;

import com.example.ruslanio.experienceexchange.network.pojo.lesson.Block;

import java.util.List;

/**
 * Created by Марат on 22.05.2018.
 */

public class LessonTrue {

    private int id;

    private String name;
    private int number;
    private long courseId;
    private List<Block> blocks;


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


    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
