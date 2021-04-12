package com.kasperknop.googleservicesexample.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PokemonRepository {
    private final DatabaseReference myRef;
    private final PokemonLiveData pokemon;
    private static PokemonRepository instance;

    public PokemonRepository(){
        myRef = FirebaseDatabase.getInstance().getReference("pokemon");
        pokemon = new PokemonLiveData(myRef);
    }

    public static synchronized PokemonRepository getInstance() {
        if(instance == null)
            instance = new PokemonRepository();
        return instance;
    }

    public void savePokemon(String pokemon) {
        myRef.setValue(new Pokemon(pokemon, 42));
    }

    public PokemonLiveData getPokemon() {
        return pokemon;
    }
}
