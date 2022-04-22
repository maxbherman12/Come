package com.example.come.db;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "picture", foreignKeys = @ForeignKey(entity = Publication.class,
        parentColumns = "publicationId",
        childColumns = "fk_publicationId",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "pictureId", unique = true), @Index(value = "fk_publicationId")})
public class Picture {
    //Creating id of the table
    @PrimaryKey(autoGenerate = true)
    public int pictureId;
    public String url;
    public int fk_publicationId;

    public int getpictureId() {
        return pictureId;
    }

    public void setId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFk_publicationId(int fk_publicationId) {
        this.fk_publicationId = fk_publicationId;
    }
    public int getFk_publicationId() {
        return fk_publicationId;
    }
}
