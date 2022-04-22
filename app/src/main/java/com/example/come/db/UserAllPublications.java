package com.example.come.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserAllPublications {
    @Embedded
    public User user;
    @Relation(parentColumn = "userId",
            entityColumn = "fk_userId")
    public List<Publication> publications;
}
