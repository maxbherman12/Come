package com.example.come.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user",
        indices = {@Index(value = "userName", unique = true)})
public class User {

    @PrimaryKey()
    @ColumnInfo(defaultValue = "come")
    @NonNull
    public String userName;

    @ColumnInfo(defaultValue = "come")
    @NonNull
    public String password;

    public String profilePhoto;

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public User(@NonNull String userName, @NonNull String password, String profilePhoto) {
        this.userName = userName;
        this.password = password;
        this.profilePhoto = profilePhoto;
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

    public static User[] populateUser() {
        return new User[] {
                new User("come", "come", "comeprofile.jpg"),
                new User("gorka", "come", "gorka.JPG"),
                new User("max", "come", "max.jpg"),
                new User("sören", "come", "soren.jpg"),
                new User("michael", "come", "michael.jpeg"),
                new User("theEater", "come", "bigboy.jpg"),
                new User("burgerLover", "come", "burgerboy.jpg")

        };
    }

    public String toString(){
        return userName;
    }
}
