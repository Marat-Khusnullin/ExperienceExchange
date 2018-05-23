package com.example.ruslanio.experienceexchange.database;

import android.content.Context;

import com.example.ruslanio.experienceexchange.database.abstracts.ExExDatabase;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.database.model.LessonBlock;
import com.example.ruslanio.experienceexchange.database.model.User;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempBlock;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempLesson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslanio on 13.12.2017.
 */

public class DataBaseManager {

    private static DataBaseManager MANAGER;
    private ExExDatabase mDatabase;

    private DataBaseManager(Context context) {
        mDatabase = ExExDatabase.getInstance(context);
    }

    public static DataBaseManager getInstance(Context context){
        if (MANAGER == null){
            MANAGER = new DataBaseManager(context);
        }
        return MANAGER;
    }

    public List<Interest> getAllInterests(){
        return mDatabase.getInterestsDao().getAll();
    }

    public void insertInterest(Interest interest){
        mDatabase.getInterestsDao().add(interest);
    }

    public void insertInterest(List<Interest> interests){
        mDatabase.getInterestsDao().add(interests.toArray(new Interest[interests.size()]));
    }

    public void setNewInterests(List<Interest> interests){
        mDatabase.getInterestsDao().clearInterests();
        insertInterest(interests);
    }

    public List<Course> getAllMyCourses(){
        List<Course> courses = mDatabase.getCourseDao().getAll(true);
        for (Course course:courses){
            List<Lesson> lessons = mDatabase.getLessonDao().getLessonsByCourseId(course.getId());
            course.setLessons(lessons);
        }
        return courses;
    }

    public List<Interest> getUserInterests(){
        return mDatabase.getInterestsDao().getByCurrentUser(true);
    }

    public User getCurrentUser(){
        if(mDatabase.getUsersDao().getUser().size()!=0)
        return mDatabase.getUsersDao().getUser().get(0);
        return null;
    }

    public void updateCurrentUser(User user){
        user.setFullName(user.getFirstName()+ " " + user.getLastName());
        mDatabase.getUsersDao().clearTable();
        mDatabase.getUsersDao().add(user);
    }

    public void clearUsers() {
        mDatabase.getUsersDao().clearTable();
    }

    public String getCurrentToken(){
        return getCurrentUser().getToken();
    }

    public int getCurrentUserId(){
        return getCurrentUser().getId();
    }

    public Interest getInterestByName(String name){
        return mDatabase.getInterestsDao().getByName(name);
    }

    public void updateInterest(Interest interest){
        mDatabase.getInterestsDao().add(interest);
    }


    public void insertLesson(Lesson lesson){
        List<LessonBlock> blocks = lesson.getBlocks();
        long id = mDatabase.getLessonDao().add(lesson);
        for (LessonBlock block: blocks){
            block.setLessonId(id);
        }
        mDatabase.getLessonBlockDao().add(blocks.toArray(new LessonBlock[blocks.size()]));
    }

    public List<TempLesson> getTemporaryLessons(){
        List<TempLesson> tempLessons = mDatabase.getTempLessonDao().getAll();
        for (TempLesson tempLesson: tempLessons){
            tempLesson.setBlocks(mDatabase.getTempBlockDao().getAllByLessonId(tempLesson.getId()));
        }
        return tempLessons;
    }

    public void deleteAllTemporaryLessons(){
        mDatabase.getTempLessonDao().deleteAll();
    }

    public void insertTemporaryLesson(TempLesson tempLesson){
        long id = mDatabase.getTempLessonDao().add(tempLesson);

        List<TempBlock> tempBlocks = tempLesson.getBlocks();
        for (TempBlock block:tempBlocks){
            block.setTempLessonId(id);
        }
        mDatabase.getTempBlockDao().add(tempBlocks.toArray(new TempBlock[tempBlocks.size()]));
    }

    public void insertCourse(Course course){
        String author = getCurrentUser().getFullName();
        List<Lesson> lessons = course.getLessons();

        course.setAvailableLessons(lessons.size());
        course.setAuthor(author);

        long courseId = mDatabase.getCourseDao().add(course);

        for (Lesson lesson: lessons){
            lesson.setCourseId(courseId);
        }
        for (Lesson lesson: lessons){
            long lessonId = mDatabase.getLessonDao().add(lesson);
            List<LessonBlock> blocks = lesson.getBlocks();
            for(LessonBlock block:blocks){
                block.setLessonId(lessonId);
            }
            mDatabase.getLessonBlockDao().add(blocks.toArray(new LessonBlock[blocks.size()]));
        }
    }

    public void deleteTempLesson(TempLesson tempLesson){
        mDatabase.getTempLessonDao().delete(tempLesson);
    }

    public List<Lesson> TempLessonsToRawLessons(List<TempLesson> tempLessons){
        List<Lesson> result = new ArrayList<>();
        for (TempLesson tempLesson: tempLessons){
            Lesson lesson = new Lesson();
            lesson.setCount(tempLesson.getCount());
            lesson.setName(tempLesson.getName());

            List<LessonBlock> blocks = new ArrayList<>();
            for (TempBlock tempBlock:tempLesson.getBlocks()){
                LessonBlock block = new LessonBlock();
                block.setType(tempBlock.getType());
                block.setValue(tempBlock.getValue());
                block.setOrderNumber(tempBlock.getOrder());
                blocks.add(block);
            }
            lesson.setBlocks(blocks);
            result.add(lesson);
        }
        return result;
    }

    public void deleteAll() {
        mDatabase.getInterestsDao().clearInterests();
        mDatabase.getCourseDao().clearCourses();
    }
}
