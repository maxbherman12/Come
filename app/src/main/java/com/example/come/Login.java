package com.example.come;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.come.db.RoomDB;
import com.example.come.db.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity {
    EditText userL, passL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        RoomDB db = RoomDB.getInstance(getApplicationContext());
        userL = findViewById(R.id.EditTextUsername);
        passL = findViewById(R.id.EditTextPassword);
        Button loginButton = findViewById(R.id.loginButton);

        List<User> allUsers = db.UserDao().getAllUsers();
        if (allUsers.size()== 0) {
            User newUser = new User("come", "come", "Come",
                    "comeprofile.jpg","","");
            db.UserDao().insertUser(newUser);
        }

        loginButton.setOnClickListener(v -> {
            String userLog = userL.getText().toString();
            String passLog = passL.getText().toString();

            User userDB = db.UserDao().findUserByUsername(userLog);
            if(userDB == null){
                Toast.makeText(getApplicationContext(), "Incorrect data!", Toast.LENGTH_SHORT).show();
            }
            else{
                String username = userDB.getUserName();
                String pass = userDB.getPassword();

                if (username.equals(userLog) && pass.equals(passLog)){
                    //Here we have to store the current user to use during all the connection
                    ((CurrentUser) Login.this.getApplication()).setValuesFromUserObj(userDB);
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Login.this, MainActivity.class);
                    Login.this.startActivity(myIntent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Incorrect data!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}