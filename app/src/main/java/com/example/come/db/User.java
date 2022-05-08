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

    public User(@NonNull String userName, @NonNull String password) {
        this.userName = userName;
        this.password = password;
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
                new User("come", "come"),
                new User("gorka", "come"),
                new User("max", "come"),
                new User("sören", "come"),
                new User("michael", "come"),
                new User("theEater", "come"),
                new User("burgerLover", "come")

        };
    }

    public String toString(){
        return userName;
    }
}
