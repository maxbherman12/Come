package com.example.come.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PictureDao {
    @Query("SELECT * FROM Picture WHERE pictureId = :pictureId LIMIT 1")
    Picture findPictureById(int pictureId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertPicture(Picture picture);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPictures(Picture... picture);

    @Query("SELECT * FROM Picture ORDER BY fk_publicationId ASC")
    List<Picture> getAllPictures();

    @Delete
    void deletePicture(Picture picture);

    @Query("SELECT * FROM Picture WHERE fk_publicationId = :id")
    Picture getPictureByPublicationId(int id);
}
