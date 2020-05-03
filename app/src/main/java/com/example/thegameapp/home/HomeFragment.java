package com.example.thegameapp.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.thegameapp.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel =
          //      ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        //homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
          //  @Override
            ////  textView.setText(s);

        //final RecyclerView recyclerView = root.findViewById(R.id.my_recycler_view);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));


                // use a linear layout manager
          //      layoutManager = ;
            //    recyclerView.setLayoutManager(layoutManager);

                // specify an adapter (see also next example)
              //  mAdapter = new MyAdapter(myDataset);
                //recyclerView.setAdapter(mAdapter);

          //  }
        //});
        return root;
    }
}
