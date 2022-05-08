package com.example.come.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "follower")
public class Follower {
    //Creating id of the table
    @PrimaryKey(autoGenerate = true)
    public int followerId;
    public int user1;
    public int follower1;

    public int getId() {
        return followerId;
    }

    public void setId(int id) {
        this.followerId = id;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public int getFollower1() {
        return follower1;
    }

    public void setFollower1(int follower1) {
        this.follower1 = follower1;
    }
}
