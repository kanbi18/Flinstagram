package com.example.flinstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flinstagram.models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<Post> posts;
    Context context;

    public PostAdapter(ArrayList<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.post_item, parent, false);
        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView usernameTV;
        public ImageView postImageIV;
        public TextView descriptionTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            usernameTV = itemView.findViewById(R.id.post_username);
            postImageIV = itemView.findViewById(R.id.post_image);
            descriptionTV = itemView.findViewById(R.id.post_description);
        }


        public void bind(Post post) {
            usernameTV.setText(post.getUser().getUsername());
            descriptionTV.setText(post.getDescription());

            Glide.with(context)
                    .load(post.getImage())
                    .into(postImageIV);
        }
    }

    public void addAll(List<Post> posts){
        posts.addAll(posts);
        notifyDataSetChanged();
    }
}
