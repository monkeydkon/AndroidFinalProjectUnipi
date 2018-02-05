package com.nplab.monkeydkon.androidfinalprojectunipi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText editText, editText2, editText3, editText4, editText5, editText6, editText7;
    TextView textView;
    Button button5;

    int cars= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        editText6 = (EditText)findViewById(R.id.editText6);
        editText7 = (EditText)findViewById(R.id.editText7);



    }

    // SHOW MORE EDITTEXTS FOR EXTRA CAR NUMBER
    public void moreCars(View view){
        if (cars == 0) {
            editText6.animate().alpha(1.0f);
            cars = 1;
        }else if (cars == 1){
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
