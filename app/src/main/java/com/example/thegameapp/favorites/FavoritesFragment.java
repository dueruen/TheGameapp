package com.example.thegameapp.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thegameapp.R;
import com.example.thegameapp.favorites.entities.FavoriteEntity;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.rec_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final FavoritesAdapter adapter = new FavoritesAdapter();
        recyclerView.setAdapter(adapter);

        favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
        favoritesViewModel.getFavorites().observe(this, new Observer<List<FavoriteEntity>>() {
            @Override
            public void onChanged(List<FavoriteEntity> favoriteEntities) {
                adapter.setFavorites(favoriteEntities);
            }
        });
        return root;
    }
}
