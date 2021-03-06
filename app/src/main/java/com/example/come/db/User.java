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
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "profilePhoto")
    public String profilePhoto;
    @ColumnInfo(name = "bio")
    public String bio;
    @ColumnInfo(name= "restaurantList")
    public String restaurantList;

    public User(@NonNull String userName, @NonNull String password, String name, String profilePhoto, String bio, String restaurantList) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.profilePhoto = profilePhoto;
        this.bio = bio;
        this.restaurantList = restaurantList;
    }

    public String getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(String restaurantList) {
        this.restaurantList = restaurantList;
    }


    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String toString(){
        return userName;
    }
}
