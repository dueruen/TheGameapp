package com.example.thegameapp.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thegameapp.R;
import com.example.thegameapp.games.entities.Game;
import com.example.thegameapp.games.GamesAdapter;
import com.example.thegameapp.games.GamesViewModel;

import java.util.List;

public class HomeFragment extends Fragment implements GamesAdapter.OnGameListener {

    private GamesViewModel gamesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.gameRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final GamesAdapter adapter = new GamesAdapter(this);
        recyclerView.setAdapter(adapter);

        gamesViewModel = ViewModelProviders.of(this).get(GamesViewModel.class);

        gamesViewModel.getGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                adapter.setGames(games);
            }
        });
        return root;
    }

    @Override
    public void onGameClick(int position, String gameID) {
        Bundle args = new Bundle();
        args.putString("GAME_ID", gameID);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_HomeFragment_to_GameDetails, args);
    }
}

