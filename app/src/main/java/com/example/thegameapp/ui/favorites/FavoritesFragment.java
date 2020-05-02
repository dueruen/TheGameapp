package com.example.thegameapp.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thegameapp.R;
import com.example.thegameapp.entities.User;
import com.example.thegameapp.ui.viewModels.UserViewModel;

public class FavoritesFragment extends Fragment {

    private UserViewModel userViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.rec_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final FavoritesAdapter adapter = new FavoritesAdapter();
        recyclerView.setAdapter(adapter);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //if (user != null) {
                //    EditText nameView = root.findViewById(R.id.pro_name);
                //    nameView.setText(user.getName());
                //}
                adapter.setFavorites(user.getFavorits());
            }
        });


        return root;
    }
}
