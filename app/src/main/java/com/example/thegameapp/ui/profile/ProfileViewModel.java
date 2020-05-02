package com.example.thegameapp.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.thegameapp.entities.User;
import com.example.thegameapp.repositories.UserRepository;

public class ProfileViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<User> currentUser;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        currentUser = userRepository.getUser();
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public LiveData<User> getUser() {
        return currentUser;
    }
}