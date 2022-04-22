package com.example.come.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user",
        indices = {@Index(value = "userName", unique = true)})
public class User {
    //Creating id of the table
    @PrimaryKey(autoGenerate = true)
    public int userId;

    @ColumnInfo(defaultValue = "come")
    @NonNull
    public String userName;

    @ColumnInfo(defaultValue = "come")
    @NonNull
    public String password;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
