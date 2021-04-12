package com.kasperknop.googleservicesexample.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PokemonLiveData extends LiveData<Pokemon> {
    DatabaseReference databaseReference;

    private final ValueEventListener listener = new ValueEventListener(){

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Pokemon pokemon = snapshot.getValue(Pokemon.class);
            setValue(pokemon);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    public PokemonLiveData(DatabaseReference ref){
        databaseReference = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}