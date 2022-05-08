package com.example.come.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserAllPublications {
    @Embedded
    public User user;
    @Relation(parentColumn = "userName",
            entityColumn = "fk_userName")
    public List<Publication> publications;
}
