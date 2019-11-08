package com.kasperknop.googleservicesexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// This is a very simple app with all code for interacting with Firebase Realtime Database
// in this activity. Consider extending LiveData and using a ViewModel for cleaner code:
// https://firebase.googleblog.com/2017/12/using-android-architecture-components.html

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("pokemon");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkIfSignedIn();
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
    }

    private void checkIfSignedIn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null)
            Toast.makeText(this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        else
            startLoginActivity();
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    public void saveData(View v) {
        myRef.setValue(new Pokemon(editText.getText().toString(), 42));
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myRef.removeEventListener(listener);
    }

    private ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Pokemon pokemon = dataSnapshot.getValue(Pokemon.class);

            if (pokemon != null)
                textView.setText(pokemon.getName());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void signOut(View v) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        startLoginActivity();
                    }
                });
    }
}
