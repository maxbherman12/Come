package com.example.come.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PublicationDao {

    @Query("SELECT * FROM Publication WHERE publicationId = :id LIMIT 1")
    Publication findPublicationById(int id);;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertPublication(Publication publication);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPublications(Publication... publication);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updatePublication(Publication publication);

    @Query("DELETE FROM publication")
    void deleteAllPublications();

    @Query("SELECT * FROM Publication ORDER BY fk_userName ASC")
    List<Publication> getAllPublications();

    @Query("SELECT * FROM Publication WHERE fk_userName = :uName")
    Publication getPublicationByUserId(String uName);

    @Transaction
    @Query("SELECT * FROM Publication")
    List<Publication> getPublicationPictures();
}
