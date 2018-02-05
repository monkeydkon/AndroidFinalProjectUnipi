package com.nplab.monkeydkon.androidfinalprojectunipi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    EditText editText, editText2, editText3, editText4, editText5, editText6, editText7;
    TextView textView;
    Button button5;

    private DatabaseReference mDatabase;



    int cars= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        textView = findViewById(R.id.textView);
        button5 = findViewById(R.id.button5);

        mDatabase = FirebaseDatabase.getInstance().getReference();





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

        String firstName = editText.getText().toString();
        String lastName = editText2.getText().toString();
        String userName = editText3.getText().toString();
        String passWord = editText4.getText().toString();
        String carNumber1 = editText5.getText().toString();
        String carNumber2 = editText6.getText().toString();
        String carNumber3 = editText7.getText().toString();

        if(firstName.equals("") || lastName.equals("")|| userName.equals("") || passWord.equals("") || carNumber1.equals(""))
        {
            Toast.makeText(this,"You left a field empty. Try again",Toast.LENGTH_SHORT).show();
        }else{

            mDatabase.child("users").child(userName).child("firstname").setValue(firstName);
            mDatabase.child("users").child(userName).child("lastname").setValue(lastName);
            mDatabase.child("users").child(userName).child("password").setValue(passWord);
            mDatabase.child("users").child(userName).child("car numbers").child("car number1").setValue(carNumber1);
            mDatabase.child("users").child(userName).child("car numbers").child("car number2").setValue(carNumber2);
            mDatabase.child("users").child(userName).child("car numbers").child("car number3").setValue(carNumber3);


        }


        // TO CODE
        //Intent intent = new Intent(this,MainActivity.class);
        //startActivity(intent);
    }
}
