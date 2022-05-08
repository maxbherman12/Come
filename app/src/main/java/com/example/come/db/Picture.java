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
                new Picture("R.drawable.dinner.jpg", 1),
                new Picture("R.drawable.dinner2.jpg", 1),

                new Picture("R.drawable.brunch.jpg", 2),
                new Picture("R.drawable.brunch2.jpeg", 2),
                new Picture("R.drawable.brunch3.jpeg", 2),

                new Picture("R.drawable.yataimarket.jpeg", 3),
                new Picture("R.drawable.yatai1.jpg", 3),
                new Picture("R.drawable.yatai2.jpg", 3),
                new Picture("R.drawable.yatai3.jpg", 3),


                new Picture("R.drawable.honestgreens.jpeg", 4),
                new Picture("R.drawable.honestgreens2.jpeg", 4),
                new Picture("R.drawable.honestgreen3.jpeg", 4),
                new Picture("R.drawable.honestgreens4.jpeg", 4),

                new Picture("R.drawable.humuseria.jpeg", 5),
                new Picture("R.drawable.humuseria2.jpeg", 5),

                new Picture("R.drawable.spotrestaurant.jpeg", 6),

                new Picture("R.drawable.burger.jpeg", 7),
                new Picture("R.drawable.burger2.jpeg", 7)





        };
    }
}
