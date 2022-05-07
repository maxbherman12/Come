package com.example.come.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "publication", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "userId",
        childColumns = "fk_userId",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "publicationId", unique = true), @Index(value = "fk_userId")})
public class Publication {

    //Creating id of the table
    @PrimaryKey(autoGenerate = true)
    public int publicationId;
    public String caption;
    public String city;
    public String restaurant;
    public int fk_userId;

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
    public void setFk_userId(int fk_userId) {
        this.fk_userId = fk_userId;
    }
    public int getFk_userId() {
        return fk_userId;
    }
}
