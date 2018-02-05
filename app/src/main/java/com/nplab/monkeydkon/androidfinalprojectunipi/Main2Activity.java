package com.nplab.monkeydkon.androidfinalprojectunipi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    int cars= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    // SHOW MORE EDITTEXTS FOR EXTRA CAR NUMBER
    public void moreCars(View view){
        if (cars == 0) {
            EditText editText6 = (EditText) findViewById(R.id.editText6);
            editText6.animate().alpha(1.0f);
            cars = 1;
        }else if (cars == 1){
            EditText editText7 = (EditText) findViewById(R.id.editText7);
            editText7.animate().alpha(1.0f);
            cars += 1;
        }else{
            Toast.makeText(this, "You can only register a maximum value of 3 cars", Toast.LENGTH_SHORT).show();
        }
    }

    //REGISTER
    public void signUp(View view){


        // TO CODE
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
