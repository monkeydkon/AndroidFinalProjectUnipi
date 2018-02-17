package com.nplab.monkeydkon.androidfinalprojectunipi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main5Activity extends AppCompatActivity {

    String username;
    String formatedDate;
    String pinakida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Intent intent = getIntent();
        username = intent.getStringExtra("whoIsLoggedIn");
        formatedDate = intent.getStringExtra("dateOfParking");
        Button button9 = findViewById(R.id.button9);
        Button button10 = findViewById(R.id.button10);
    }

    public void closeSeat(View view){
        Intent intent = new Intent(getApplicationContext(),Main4Activity.class);
        intent.putExtra("whoIsLoggedIn", username);
        startActivity(intent);
    }

    public void cancel(View view){
        Intent intent = new Intent(getApplicationContext(),Main6Activity.class);
        intent.putExtra("whoIsLoggedIn", username);
        intent.putExtra("dateOfParking", formatedDate);
        intent.putExtra("pinakida", pinakida);

        startActivity(intent);
    }


}
