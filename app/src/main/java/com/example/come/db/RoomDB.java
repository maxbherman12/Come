package com.example.come.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={User.class, Publication.class, Picture.class}, version=8)
public abstract class RoomDB extends RoomDatabase{
    //Database instance creation
    private static RoomDB database;
    //Database name definition
    private static String DATABASE_NAME = "database";

    // Data Access Objects for SQL database queries
    public abstract UserDao UserDao();
    public abstract PublicationDao PublicationDao();
    public abstract PictureDao PictureDao();


    // Method returning the db and creating it if there isn't created yet
    public synchronized static RoomDB getInstance(Context context) {

        if (database == null) {
            database = buildDatabase(context);
        }
        return database;
    }
    private static RoomDB buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME).
                        createFromAsset("userDatabase.db").
                        allowMainThreadQueries().
                        fallbackToDestructiveMigration().
                        build();
            }
        }
