package com.example.come.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {


    @Query("SELECT * FROM User WHERE userName = :username LIMIT 1")
    User findUserByUsername(String username);

    @Query("SELECT password FROM User WHERE password = :password LIMIT 1")
    String findUserByPass(String password);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUsers(User... user);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateUser(User user);

    @Query("DELETE FROM User")
    void deleteAllUsers();

    @Query("SELECT * FROM User ORDER BY userName ASC")
    List<User> getAllUsers();

    @Transaction
    @Query("SELECT * FROM User")
    List<User> getUserPublications();
}
