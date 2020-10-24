package com.example.flinstagram.models;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_USER = "user";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_DESCRIPTION = "description";

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public void createObject(String user, String description, String image) {
        ParseObject entity = new ParseObject("Post");

        entity.put("description", description);
        entity.put("image", new ParseFile("resume.txt", "My string content".getBytes()));
        entity.put("user", ParseUser.getCurrentUser());

        // Saves the new object.
        // Notice that the SaveCallback is totally optional!
        entity.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.e("Post", "Problem with saving post");
            }
        });
    }
}
