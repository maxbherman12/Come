package com.example.come;

import android.app.Application;

public class MyApplication extends Application {
    private String userName;

    public String getSomeVariable() {
        return userName;
    }

    public void setSomeVariable(String someVariable) {
        this.userName = someVariable;
    }
}

