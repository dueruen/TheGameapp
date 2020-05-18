package com.example.thegameapp.games;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thegameapp.R;
import com.example.thegameapp.games.entities.Game;
import com.example.thegameapp.games.entities.Genre;
import com.example.thegameapp.games.entities.Publisher;

import java.util.ArrayList;
import java.util.List;


public class GamesAdapter extends RecyclerView.Adapter<com.example.thegameapp.games.GamesAdapter.GamesHolder> {

    public interface OnGameListener {
        void onGameClick(int position, String gameID);
    }

    private OnGameListener mCallback;
    private List<Game> games = new ArrayList<>();

    public GamesAdapter(OnGameListener gameListener) {
        this.mCallback = gameListener;
    }

    @NonNull
    @Override
    public com.example.thegameapp.games.GamesAdapter.GamesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_item, parent, false);
        return new GamesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.thegameapp.games.GamesAdapter.GamesHolder holder, int position) {
        Game current = games.get(position);
        holder.textViewTitle.setText(current.getTitle());
        holder.textViewScore.setText(String.valueOf(current.getScore()));
        holder.textViewReleaseDate.setText(current.getReleaseDate());

        //Build genre string
        Genre[] genres = current.getGenre();
        StringBuilder genresSB = new StringBuilder();

        for(int i = 0; i < genres.length; i++) {
            genresSB.append(genres[i].getName());
            genresSB.append("\n");
        }
        holder.textViewGenre.setText(genresSB.toString());


        Glide.with(holder.imageView).load(current.getImageURL()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        if (games == null) {
            return 0;
        }
        return games.size();
    }

    public void setGames(List<Game> list) {
        this.games = list;
        notifyDataSetChanged();
    }

    class GamesHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        private TextView textViewTitle;
        private TextView textViewScore;
        private ImageView imageView;
        private TextView textViewGenre;
        private TextView textViewReleaseDate;

        public GamesHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewTitle = itemView.findViewById(R.id.game_title);
            textViewScore = itemView.findViewById(R.id.game_score);
            imageView = itemView.findViewById(R.id.game_image);
            textViewReleaseDate = itemView.findViewById(R.id.realeaseDateTV);
            textViewGenre = itemView.findViewById(R.id.genreTextView);
        }

        @Override
        public void onClick(View v) {
            int index = this.getLayoutPosition();
            String gameID = games.get(index).getId();
            mCallback.onGameClick(index, gameID);
        }
    }
}
