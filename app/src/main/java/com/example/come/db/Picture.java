package com.example.come.db;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
/*
@Entity(tableName = "picture", foreignKeys = @ForeignKey(entity = Publication.class,
        parentColumns = "publicationId",
        childColumns = "fk_publicationId",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "pictureId", unique = true), @Index(value = "fk_publicationId")})
 */
@Entity(tableName = "picture",
        indices = {@Index(value="pictureId", unique = true),
                   @Index(value = "fk_publicationId")})
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

    public static Picture[] populatePicture() {
        return new Picture[] {
                new Picture("dinner.jpg", 1),
                new Picture("dinner2.jpg", 1),

                new Picture("brunch.jpg", 2),
                new Picture("brunch2.jpeg", 2),
                new Picture("brunch3.jpeg", 2),

                new Picture("yataimarket.jpeg", 3),
                new Picture("yatai1.jpg", 3),
                new Picture("yatai2.jpg", 3),
                new Picture("yatai3.jpg", 3),


                new Picture("honestgreens.jpeg", 4),
                new Picture("honestgreens2.jpeg", 4),
                new Picture("honestgreen3.jpeg", 4),
                new Picture("honestgreens4.jpeg", 4),

                new Picture("humuseria.jpeg", 5),
                new Picture("humuseria2.jpeg", 5),

                new Picture("spotrestaurant.jpeg", 6),

                new Picture("burger.jpeg", 7),
                new Picture("burger2.jpeg", 7)





        };
    }
}
