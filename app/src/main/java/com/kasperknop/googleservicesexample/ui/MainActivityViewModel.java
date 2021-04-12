package com.kasperknop.googleservicesexample.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.kasperknop.googleservicesexample.data.Pokemon;
import com.kasperknop.googleservicesexample.data.PokemonRepository;
import com.kasperknop.googleservicesexample.data.UserRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;

    public MainActivityViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
        pokemonRepository = PokemonRepository.getInstance();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void savePokemon(String pokemon) {
        pokemonRepository.savePokemon(pokemon);
    }

    public LiveData<Pokemon> getPokemon() {
        return pokemonRepository.getPokemon();
    }

    public void signOut() {
        userRepository.signOut();
    }
}
