package com.example.come;

import android.app.Application;

import java.util.List;

public class CurrentUser extends Application {
    private String username;
    private String name;
    private String bio;
    private int img;
    private List<String> list;

    public String getUsername() {
        return username;
    }
    public void setUsername(String _username) {
        this.username = _username;
    }

    public String getName() { return name; }
    public void setName(String _name){ this.name = _name; }

    public String getBio() { return bio; }
    public void setBio(String _bio) { this.bio = _bio; }

    public int getImg(){ return img; }
    public void setImg(int _img){ this.img = _img; }

    public List<String> getList(){ return this.list; }
    public void setList(List<String> _list) { this.list = _list; }
}

