package com.example.thegameapp.ui.favorites;

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
import com.example.thegameapp.entities.FavoritEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.GameHolder> {

    private List<FavoritEntity> favorites = new ArrayList<>();

    @NonNull
    @Override
    public GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_item, parent, false);
        return new GameHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameHolder holder, int position) {
        FavoritEntity current = favorites.get(position);
        holder.textViewTitle.setText(current.getTitle());
        holder.textViewScore.setText(String.valueOf(current.getScore()));

        try {
            URL url = new URL(current.getImage());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.imageView.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (favorites == null) {
            return 0;
        }
        return favorites.size();
    }

    public void setFavorites(List<FavoritEntity> list) {
        this.favorites = list;
        notifyDataSetChanged();
    }

    class GameHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewScore;
        private ImageView imageView;

        public GameHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.game_title);
            textViewScore = itemView.findViewById(R.id.game_score);
            imageView = itemView.findViewById(R.id.game_image);
        }
    }
}
