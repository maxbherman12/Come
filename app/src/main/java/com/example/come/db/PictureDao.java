package com.example.come.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PictureDao {
    @Query("SELECT * FROM Picture WHERE pictureId = :pictureId LIMIT 1")
    Picture findPictureById(int pictureId);;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertPicture(Picture picture);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPictures(Picture... picture);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updatePicture(Picture picture);

    @Query("DELETE FROM Picture")
    void deleteAllPictures();

    @Query("SELECT * FROM Picture ORDER BY fk_publicationId ASC")
    LiveData<List<Picture>> getAllPictures();
}
