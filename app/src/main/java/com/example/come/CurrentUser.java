package com.example.come;

import android.app.Application;

import com.example.come.db.RoomDB;
import com.example.come.db.User;

import java.util.ArrayList;
import java.util.List;

public class CurrentUser extends Application {
    private String username;
    private String password;
    private String name;
    private String bio;
    private String imgStr;
    private int img;
    private String listStr;
    private List<String> restaurantList;
    private List<String> following = new ArrayList<String>();

    public void setValuesFromUserObj(User user){
        setUsername(user.getUserName());
        setPassword(user.getPassword());
        setName(user.getName());
        setBio(user.getBio());
        setImgStr(user.getProfilePhoto());
        setListFromCsv(user.getRestaurantList());
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String _username) {
        this.username = _username;
    }

    public String getPassword() {return password;}
    public void setPassword(String _password){this.password = _password;}

    public String getName() { return name; }
    public void setName(String _name){ this.name = _name; }

    public String getBio() { return bio; }
    public void setBio(String _bio) { this.bio = _bio; }

    public String getImgStr(){ return imgStr; }
    public void setImgStr(String _imgStr){
        this.imgStr = _imgStr;
        String uri = "@drawable/" + this.imgStr.substring(0, this.imgStr.indexOf('.'));
        int imageResource =
                getResources().getIdentifier(uri, null, getApplicationContext().getPackageName());
        setImg(imageResource);
    }

    public int getImg(){ return img; }
    public void setImg(int _img){ this.img = _img; }

    public List<String> getRestaurantList(){ return this.restaurantList; }
    public void setRestaurantList(List<String> _list) { this.restaurantList = _list; }

    public void setListFromCsv(String strLst){
        this.listStr = strLst;
        List<String> ret = new ArrayList<String>();
        while(strLst.indexOf(',') != -1){
            String val = strLst.substring(0, strLst.indexOf(','));
            if(val.trim().length() > 0){
                ret.add(val.trim());
            }
            strLst = strLst.substring(strLst.indexOf(',')+1);
        }

        // If there is a leftover value after the final comma
        if(strLst.trim().length() > 0){
            ret.add(strLst.trim());
        }

        this.restaurantList = ret;
    }

    public void follow(String username){
        following.add(username);
    }

    public void unfollow(String username){
        following.remove(username);
    }

    public boolean follows(String username){
        return following.contains(username);
    }

    public User getUser(){
        return new User(username, password, name, imgStr, bio, listStr);
    }
}

