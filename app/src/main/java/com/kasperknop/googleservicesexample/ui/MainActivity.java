package com.kasperknop.googleservicesexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.kasperknop.googleservicesexample.R;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private MainActivityViewModel viewModel;
    private TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        checkIfSignedIn();
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        welcomeMessage = findViewById(R.id.welcomeMessage);

        viewModel.getPokemon().observe(this, pokemon -> {
            if (pokemon != null)
                textView.setText(pokemon.getName());
        });
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                String message = "Welcome " + user.getDisplayName();
                welcomeMessage.setText(message);
            } else
                startLoginActivity();
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    public void saveData(View v) {
        viewModel.savePokemon(editText.getText().toString());
    }

    public void signOut(View v) {
        viewModel.signOut();
    }
}
