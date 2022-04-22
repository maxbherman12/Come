package com.example.come.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PublicationAllPictures {
    @Embedded
    public Publication publication;
    @Relation(parentColumn = "publicationId",
            entityColumn = "fk_publicationId")
    public List<Picture> pictureList;
}
