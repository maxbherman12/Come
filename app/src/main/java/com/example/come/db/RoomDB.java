package com.example.come.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities={User.class, Publication.class, Picture.class}, version=1, exportSchema=false)
public abstract class RoomDB extends RoomDatabase{
    //Database instance creation
    private static RoomDB database;
    //Database name definition
    private static String DATABASE_NAME="database";

    // Method returning the db and creating it if there isn't created yet
    public synchronized static RoomDB getInstance(Context context){

        if (database == null){
            synchronized (RoomDB.class){
                database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME).
                        allowMainThreadQueries().
                        fallbackToDestructiveMigration().
                        addCallback(new RoomDatabase.Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Log.d("RoomDB", "populating with data...");
                                new PopulateDbAsync(database).execute();
                            }
                        }).
                        build();
            }

        }
        return database;
    }
    public void clearDb() {
        if (database != null) {
            new PopulateDbAsync(database).execute();
        }
    }
    // Data Access Objects for SQL database queries
    public abstract UserDao UserDao();
    public abstract PictureDao PictureDao();
    public abstract PublicationDao PublicationDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final UserDao UserDao;
        private final PublicationDao PublicationDao;
        private final PictureDao PictureDao;

        public PopulateDbAsync(RoomDB instance) {
            UserDao = instance.UserDao();
            PublicationDao = instance.PublicationDao();
            PictureDao = instance.PictureDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDao.deleteAllUsers();
            PublicationDao.deleteAllPublications();
            PictureDao.deleteAllPictures();

            User userOne = new User();
            User userTwo = new User();
            User userThree = new User();

            userOne.setUserName("Adam McKay");
            userTwo.setUserName("Denis Villeneuve");
            userThree.setUserName("Morten Tyldum");

            UserDao.insertUser(userOne);
            UserDao.insertUser(userTwo);
            UserDao.insertUser(userThree);

            Publication publicationOne = new Publication();
            Publication publicationTwo = new Publication();
            Publication publicationThree = new Publication();
            Publication publicationFour = new Publication();

            publicationOne.setCaption("Hello, I am learning android");
            publicationOne.setFk_userId(userOne.userId);
            publicationOne.setCaption("I am also learning how to connect db to fragment");
            publicationOne.setFk_userId(userTwo.userId);
            publicationOne.setCaption("Just trying");
            publicationOne.setFk_userId(userTwo.userId);
            publicationOne.setCaption("I am new");
            publicationOne.setFk_userId(userThree.userId);

            PublicationDao.insertPublications(publicationOne, publicationTwo, publicationThree, publicationFour);

            return null;
        }
    }
}
