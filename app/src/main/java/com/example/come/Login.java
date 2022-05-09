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
            String username = "come";
            String password = "come";
            String photo = "comeprofile.jpg";
            User newUser = new User(username, password, photo);
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
                    saveUserData(userDB);
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

    private void saveUserData(User user){
        ((CurrentUser) Login.this.getApplication()).setUsername(user.getUserName());
        ((CurrentUser) Login.this.getApplication()).setName("Max Herman");
        ((CurrentUser) Login.this.getApplication()).setBio("This is my bio from login");

        String imgFilepath = user.getProfilePhoto();
        String uri = "@drawable/" + imgFilepath.substring(0, imgFilepath.indexOf('.'));
        int imageResource =
                getResources().getIdentifier(uri, null, getApplicationContext().getPackageName());
        ((CurrentUser) Login.this.getApplication()).setImg(imageResource);

        String[] restaurants = {"Humuseria", "Cherry Pecas", "La Musa Latina", "Vietnamese Express"};
        ArrayList<String> lst = new ArrayList<>(Arrays.asList(restaurants));
        ((CurrentUser) Login.this.getApplication()).setList(lst);
    }

}