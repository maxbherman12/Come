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
    @ColumnInfo(name = "userName")
    @NonNull
    public String userName;
    @ColumnInfo(name = "password")
    @NonNull
    public String password;
    @ColumnInfo(name = "profilePhoto")
    public String profilePhoto;
    @ColumnInfo(name = "bio")
    public String bio;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

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
