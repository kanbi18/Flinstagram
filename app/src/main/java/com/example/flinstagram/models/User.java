package com.example.flinstagram.models;

import org.parceler.Parcel;

import java.util.ArrayList;


@Parcel
public class User {

    String  username;
    ArrayList<Post> posts;

    public User() {}

    public User(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public Post getMostRecentPost() {
        return posts.get(posts.size() -1);
    }

}
