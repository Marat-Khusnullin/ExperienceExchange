package com.example.ruslanio.experienceexchange.database.abstracts;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.ruslanio.experienceexchange.database.dao.CourseDao;
import com.example.ruslanio.experienceexchange.database.dao.InterestsDao;
import com.example.ruslanio.experienceexchange.database.dao.UsersDao;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.database.model.User;

/**
 * Created by Ruslanio on 04.12.2017.
 */

@Database(entities = {Interest.class, User.class, Course.class}, version = 1)
public abstract class ExExDatabase extends RoomDatabase {

    private static final String DB_NAME = "database_name";
    private static volatile ExExDatabase mInstance;

    public static synchronized ExExDatabase getInstance(Context context){
        if (mInstance == null)
            mInstance = create(context);
        return mInstance;
    }

    public abstract InterestsDao getInterestsDao();

    public abstract UsersDao getUsersDao();

    public abstract CourseDao getCourseDao();

    private static ExExDatabase create(Context context) {
        return Room.databaseBuilder(context, ExExDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

}
