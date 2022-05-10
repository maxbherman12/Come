package com.example.come.db;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "picture")
public class Picture {
    //Creating id of the table
    @PrimaryKey(autoGenerate = true)
    public int pictureId;
    public String url;
    public int fk_publicationId;

    public Picture(String url, int fk_publicationId) {
        this.url = url;
        this.fk_publicationId = fk_publicationId;
    }

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
