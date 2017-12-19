package com.example.ruslanio.experienceexchange.network.body.lesson;

import java.util.List;

/**
 * Created by Ruslanio on 19.12.2017.
 */

public class LessonBody {

    private String name;
    private List<Block> blocks;

    public LessonBody() {
    }

    public LessonBody(String name, List<Block> blocks) {
        this.name = name;
        this.blocks = blocks;
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
}
