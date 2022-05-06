package com.example.come.ui.profile;

import java.util.List;

public class ProfileData {
    String name;
    String username;
    String bio;
    int image;
    List<String> toVisitList;

    public ProfileData(String _name, String _username, String _bio,
                       int _image, List<String> _toVisitList){
        this.name = _name;
        this.username = _username;
        this.bio = _bio;
        this.image = _image;
        this.toVisitList = _toVisitList;
    }

    public String getName(){ return this.name; }

    public String getUsername() { return this.username; }

    public String getBio(){ return this.bio; }

    public int getImage(){ return this.image; }

    public List<String> getToVisitList(){ return this.toVisitList; }

}
