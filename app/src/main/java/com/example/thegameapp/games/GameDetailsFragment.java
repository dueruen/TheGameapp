package com.example.thegameapp.games;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thegameapp.R;
import com.example.thegameapp.favorites.FavoritesViewModel;
import com.example.thegameapp.favorites.entities.FavoriteEntity;
import com.example.thegameapp.games.entities.Game;
import com.example.thegameapp.games.entities.Publisher;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GameDetailsFragment extends Fragment implements View.OnClickListener {

    private GamesViewModel gamesViewModel;
    private FavoritesViewModel favoritesViewModel;
    private TextView gameTitleTV;
    private ScrollView gameDescriptionSV;
    private TextView gameDescriptionTV;
    private TextView scoreTV;
    private TextView publisherTV;
    private TextView releaseTV;
    private ImageView gameIV;
    private Button addToFavoritesButton;
    private boolean isFavorite;

    private Game current;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_game_details, container, false);

        gameTitleTV = root.findViewById(R.id.gameTitleTextView);
        gameDescriptionSV = root.findViewById(R.id.gameDescriptionScrollView);
        gameDescriptionTV = gameDescriptionSV.findViewById(R.id.description);
        scoreTV = root.findViewById(R.id.metacriticScoreTextView);
        publisherTV = root.findViewById(R.id.publisherTextView);
        releaseTV = root.findViewById(R.id.releaseDateTextView);
        gameIV = root.findViewById(R.id.gameImage);

        String gameID = getArguments().getString("GAME_ID");

        favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
        addToFavoritesButton = root.findViewById(R.id.addToFavoritesButton);
        addToFavoritesButton.setOnClickListener(this);

        favoritesViewModel.getFavorites().observe(this, new Observer<List<FavoriteEntity>>() {
            @Override
            public void onChanged(List<FavoriteEntity> favoriteEntities) {
                if (favoriteEntities.size()!= 0) {
                    for (FavoriteEntity f : favoriteEntities) {
                        if (Integer.toString(f.getId()).equals(gameID)) {
                            addToFavoritesButton.setText(R.string.remove_from_favorites_button);
                            isFavorite = true;
                            return;
                        }
                    }
                }
                addToFavoritesButton.setText(R.string.add_to_favorites_button);
                isFavorite = false;
            }
        });

        gamesViewModel = ViewModelProviders.of(this).get(GamesViewModel.class);
        gamesViewModel.getGameByID(gameID).observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                //Since the ID-based query will only return 1 result,
                //this will ensure that nothing happens until it is done loading
                if(games.size() == 1) {
                    current = games.get(0);
                    updateGameData();
                }
            }
        });

        return root;
    }

    public void updateGameData() {
        gameTitleTV.setText(current.getTitle());
        gameDescriptionTV.setText(current.getDescription());
        scoreTV.setText(String.valueOf(current.getScore()));
        releaseTV.setText(current.getReleaseDate());

        Glide.with(gameIV).load(current.getImageURL()).into(gameIV);

        Publisher[] publishers = current.getPublishers();
        StringBuilder publishersSB = new StringBuilder();

        for(int i = 0; i < publishers.length; i++) {
            publishersSB.append(publishers[i].getName());
            publishersSB.append("\n");
        }
        publisherTV.setText(publishersSB.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addToFavoritesButton:
                favoritesButtonOnClick(v);
                break;
            default:
                break;
        }

    }


    private void favoritesButtonOnClick(View v) {
        if (isFavorite) {
            favoritesViewModel.delete(new FavoriteEntity(Integer.parseInt(current.getId()), current.getTitle(),current.getScore(),current.getImageURL()));
            String message = String.format("%s successfully removed from favorites",current.getTitle());
            Snackbar snack = Snackbar.make(v,message, Snackbar.LENGTH_LONG);
            snack.show();
            return;
        }
        favoritesViewModel.insert(new FavoriteEntity(Integer.parseInt(current.getId()), current.getTitle(),current.getScore(),current.getImageURL()));
        String message = String.format("%s successfully added to favorites",current.getTitle());
        Snackbar snack = Snackbar.make(v,message, Snackbar.LENGTH_LONG);
        snack.show();
    }
}
