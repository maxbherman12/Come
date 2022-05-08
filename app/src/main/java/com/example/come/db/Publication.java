package com.example.come.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "publication", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "userName",
        childColumns = "fk_userName",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "publicationId", unique = true), @Index(value = "fk_userName")})
public class Publication {

    //Creating id of the table
    @PrimaryKey(autoGenerate = true)
    public int publicationId;
    public String caption;
    public String city;
    public String restaurant;
    public String fk_userName;

    public Publication(String caption, String city, String restaurant, String fk_userName) {
        this.caption = caption;
        this.city = city;
        this.restaurant = restaurant;
        this.fk_userName = fk_userName;
    }

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
    public void setFk_userName(String fk_userName) {
        this.fk_userName = fk_userName;
    }
    public String getFk_userName() {
        return fk_userName;
    }

    public static Publication[] populatePublication() {
        return new Publication[] {
                new Publication("Best dinner of my live!!", "Madrid", "La Habanera", "come"),
                new Publication("I love this brunch", "Madrid", "La Rollerie", "gorka"),
                new Publication("This oriental market is crashing it!", "Madrid", "Yatai Market", "max"),
                new Publication("Healthy & tasty meal", "Madrid", "Honest Greens", "sören"),
                new Publication("Perfect place for hummus lovers", "Madrid", "Humuseria", "michael"),
                new Publication("This restaurant has the perfect spots!", "Madrid", "Ella Sky Bar", "theEater"),
                new Publication("I am dreaming with this burger", "Madrid", "Steakburger", "burgerLover")

        };
    }
}
