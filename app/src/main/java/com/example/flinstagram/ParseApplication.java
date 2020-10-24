package com.example.flinstagram;

import android.app.Application;
import android.content.Intent;

import com.example.flinstagram.models.Post;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ParseApplication extends Application {
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("9xWaDKIFFU6EixWnQHfF71HrUzecXMBhbZz1iQVE")
                .clientKey("EWnVCTUaOLBwiWWjXmiEoUb2ZdfzbSshOZ4TWeyU")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

    public void createUser(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(ParseApplication.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }


    public ParseUser getCurrentUser() {
        // After login, Parse will cache it on disk, so
        // we don't need to login every time we open this
        // application
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            return currentUser;
        } else {
            // show the signup or login screen
        }
        return currentUser;
    }
}
