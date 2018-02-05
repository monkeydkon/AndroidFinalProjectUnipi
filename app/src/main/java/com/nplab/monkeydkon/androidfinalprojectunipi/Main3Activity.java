package com.nplab.monkeydkon.androidfinalprojectunipi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {

    EditText editText8, editText9;
    DatabaseReference mDatabase;
    Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        editText8 = findViewById(R.id.editText8);
        editText9 = findViewById(R.id.editText9);
        button4 = findViewById(R.id.button4);

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void login(View view){

        final String username = editText8.getText().toString();
        final String password = editText9.getText().toString();

        if(username.equals("") || password.equals("")){
            Toast.makeText(this,"You left at least a field empty. Try again.",Toast.LENGTH_SHORT).show();
        }else{

            mDatabase.child("users").child(username).child("password").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue().toString().equals(password)){

                        Toast.makeText(getApplicationContext(),"You are now logged in.",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),Main4Activity.class);
                        intent.putExtra("whoIsLoggedIn", username);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Wrong username or password. Try again.",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

    }

}
