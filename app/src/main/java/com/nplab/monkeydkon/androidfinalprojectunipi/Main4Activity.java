package com.nplab.monkeydkon.androidfinalprojectunipi;

import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.util.Arrays.asList;

public class Main4Activity extends AppCompatActivity {

    ArrayList<String> parking;

    DatePicker datePicker;
    TimePicker timePicker;
    Button button6, button7, button8;

    private DatabaseReference mDatabase;

    String parkingChosen;

    ArrayList<String> theseis = new ArrayList<String>();

    ArrayList<String> pinakides = new ArrayList<String>();

    ListView listView;

    String formatedDate;

    String username;

    String thesiWanted;

    String arriveGeneral;

    String dismissGeneral;

    String pinakida;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();
        username = intent.getStringExtra("whoIsLoggedIn");

        mDatabase = FirebaseDatabase.getInstance().getReference();


        listView = findViewById(R.id.listView);

        datePicker= findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);

        // create array list from simple list for not several "adds"
        final ArrayList<String> perioxes = new ArrayList<String>(asList("ilioupoli","alimos"));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, perioxes);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if (i == 0) {
                    parking = new ArrayList<String>(asList("parkcity","sunparking"));
                }else if (i == 1){
                    parking = new ArrayList<String>(asList("superparking","parkadoros"));
                }

                ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, parking);


                listView.setAdapter(arrayAdapter1);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        parkingChosen = parking.get(i);

                        listView.setVisibility(View.GONE);
                        datePicker.setVisibility(View.VISIBLE);
                        button6.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"Select date of arrival",Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });


    }

    public void setTime(View view){

        int day  = datePicker.getDayOfMonth();
        int month= datePicker.getMonth();
        int year = datePicker.getYear() - 1900;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        formatedDate = simpleDateFormat.format(new Date(year, month, day));

        Toast.makeText(getApplicationContext(),"Select time of arrival",Toast.LENGTH_SHORT).show();

        datePicker.setVisibility(View.GONE);
        button6.setVisibility(View.GONE);
        timePicker.setVisibility(View.VISIBLE);
        button7.setVisibility(View.VISIBLE);


    }

    public void timeSet(View view){

        String arrivalHour = timePicker.getCurrentHour().toString();
        String arrivalMinute = timePicker.getCurrentMinute().toString();
        arriveGeneral = arrivalHour + " " + arrivalMinute;

        Toast.makeText(getApplicationContext(),"Select date of leave",Toast.LENGTH_SHORT).show();


        button7.setVisibility(View.GONE);
        button8.setVisibility(View.VISIBLE);


        //                                             C O N T I N U E
        //                                             H    E    R   E
    }

    public void allSet(View view){

        final String dismissHour = timePicker.getCurrentHour().toString();
        String dismissMinute = timePicker.getCurrentMinute().toString();
        dismissGeneral = dismissHour + " " + dismissMinute;

        timePicker.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        button8.setVisibility(View.GONE);






        mDatabase.child("parkings").child(parkingChosen).child("theseis").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot giveMeTheKey : dataSnapshot.getChildren()){
                    theseis.add(giveMeTheKey.getKey());
                }
                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, theseis);
                listView.setAdapter(arrayAdapter2);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                thesiWanted = theseis.get(i);

                mDatabase.child("users").child(username).child("car numbers").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot giveMeTheCars : dataSnapshot.getChildren()){
                            if(!giveMeTheCars.getValue().equals("")){
                                pinakides.add(giveMeTheCars.getValue().toString());
                            }
                        }
                        final ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, pinakides);
                        listView.setAdapter(arrayAdapter3);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                pinakida = pinakides.get(i);
                                mDatabase.child("parkings").child(parkingChosen).child("theseis").child(thesiWanted).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                            // P E R I O R I S M O I
                                            if(true){
                                                showConfirmMessage();
                                                if(confirmed==true){
                                                    showMessage("Your seat is closed","Do you want another one?");
                                                }
                                            }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    public void showMessage(String title, String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(text);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Main4Activity.this.recreate();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(),Main5Activity.class);
                intent.putExtra("whoIsLoggedIn", username);
                intent.putExtra("dateOfParking", formatedDate);
                intent.putExtra("pinakida", pinakida);

                startActivity(intent);
            }
        });
        builder.show();
    }

    boolean confirmed = false;

    public void showConfirmMessage(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Payment via Creditcard");
        builder.setMessage("");
        EditText number = new EditText(this);
        number.setHint("eg 123 456 789");
        builder.setView(number);
        builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                mDatabase.child("parkings").child(parkingChosen).child("theseis").child(thesiWanted).child(formatedDate).child(pinakida).child("arrive").setValue(arriveGeneral);
                mDatabase.child("parkings").child(parkingChosen).child("theseis").child(thesiWanted).child(formatedDate).child(pinakida).child("leave").setValue(dismissGeneral);
                mDatabase.child("users").child(username).child("theseis").child(parkingChosen).child(thesiWanted).setValue(true);

                confirmed = true;

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(getApplicationContext(),Main5Activity.class);
                intent.putExtra("whoIsLoggedIn", username);
                intent.putExtra("dateOfParking", formatedDate);
                intent.putExtra("pinakida", pinakida);

                startActivity(intent);

            }
        });
        builder.show();
    }

    public void sendNotification(){

    }

}
