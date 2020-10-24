package com.example.flinstagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.flinstagram.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Post> posts;
    ImageButton takePictureBtn;
    public static final String TAG = "MainActivity";
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        takePictureBtn = findViewById(R.id.take_picture);
//        takePictureBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, PictureActivity.class);
//                startActivity(intent);
//            }
//        });

        RecyclerView postsTimeline = findViewById(R.id.post_timeline);
        posts = new ArrayList<>();
        postAdapter = new PostAdapter((ArrayList<Post>) posts, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        postsTimeline.setLayoutManager(layoutManager);
        postsTimeline.setAdapter(postAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(postsTimeline.getContext(),
                layoutManager.getOrientation());
        postsTimeline.addItemDecoration(dividerItemDecoration);

        queryPosts();
    }

    public void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);;
        query.include(Post.KEY_USER);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue retrieving posts", e);
                    return;
                }
                postAdapter.addAll(posts);
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription());
                }
            }
        });
    }

}