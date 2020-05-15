package com.example.thegameapp.games;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thegameapp.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class GamesAdapter extends RecyclerView.Adapter<com.example.thegameapp.games.GamesAdapter.GamesHolder> {

    private List<Game> games = new ArrayList<>();

    @NonNull
    @Override
    public com.example.thegameapp.games.GamesAdapter.GamesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_item, parent, false);
        return new com.example.thegameapp.games.GamesAdapter.GamesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.thegameapp.games.GamesAdapter.GamesHolder holder, int position) {
        Game current = games.get(position);
        holder.textViewTitle.setText(current.getTitle());
        holder.textViewScore.setText(String.valueOf(current.getScore()));

        try {
            URL url = new URL(current.getImageURL());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.imageView.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    class GamesHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewScore;
        private ImageView imageView;

        public GamesHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.game_title);
            textViewScore = itemView.findViewById(R.id.game_score);
            imageView = itemView.findViewById(R.id.game_image);
        }
    }
}
