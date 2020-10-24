package com.example.flinstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ParseLoginActivity extends AppCompatActivity {

    private static final String TAG = "ParseLoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_login);

        if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        final EditText editUsername = findViewById(R.id.username_box);
        final EditText editPassword = findViewById(R.id.password_box);
        Button signinButton = findViewById(R.id.sign_up_btn);
        Button loginButton = findViewById(R.id.log_in_btn);


        signinButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i(TAG, "onclick signup button");
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();
                signupUser(username, password);

            }
        });
        
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onclick login Button");
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();
                loginUser(username, password);           
            }
        });

    }

    private void signupUser(final String username, final String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    loginUser(username, password);
                } else {
                    Log.e(TAG, "Issue signing up", e);
                }
            }
        });

    }

    public void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(ParseLoginActivity.this, "Wrong username and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                goMainActivity();
                Toast.makeText(ParseLoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity() {
        Intent intent = new Intent(ParseLoginActivity.this, PictureActivity.class);
        Log.i(TAG, "Login Intent Activated");
        startActivity(intent);
    }

}