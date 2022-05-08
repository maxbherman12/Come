package com.example.come;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.come.db.RoomDB;
import com.example.come.db.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
        //Create a condition that if there is no user create a default one

        List<User> allUsers = db.UserDao().getAllUsers();

        if (allUsers.size()== 0) {
            String username = "come";
            String password = "come";
            User newUser = new User(username, password);
            db.UserDao().insertUser(newUser);
        }


        loginButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userLog = userL.getText().toString();
                String passLog = passL.getText().toString();

                User userDB = db.UserDao().findUserByName(userLog);
                if(userDB == null){
                    Toast.makeText(getApplicationContext(), "Incorrect data!", Toast.LENGTH_SHORT).show();
                }
                else{
                    String username = userDB.getUserName();
                    String pass = userDB.getPassword();
                    if (username.equals(userLog) && pass.equals(passLog)){
                        // TODO Auto-generated method stub
                        Intent myIntent = new Intent(Login.this, MainActivity.class);
                        //myIntent.putExtra("key", value); //Optional parameters
                        Login.this.startActivity(myIntent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Incorrect data!", Toast.LENGTH_SHORT).show();
                    }
                }


            }

        });

    }
}