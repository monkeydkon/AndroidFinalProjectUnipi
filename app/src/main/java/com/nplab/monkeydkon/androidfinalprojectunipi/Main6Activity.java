package com.nplab.monkeydkon.androidfinalprojectunipi;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Main6Activity extends AppCompatActivity {

    String username;

    String formatedDate;

    String pinakida;

    DatabaseReference mDatabase;

    ListView listView;

    ArrayList<String> closed = new ArrayList<>();

    ArrayList<String> closedTheseis = new ArrayList<>();


    String parkingToCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        scheduleNotification();


        Intent intent = getIntent();
        username = intent.getStringExtra("whoIsLoggedIn");
        formatedDate = intent.getStringExtra("dateOfParking");
        pinakida = intent.getStringExtra("pinakida");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        listView = findViewById(R.id.listView);

        mDatabase.child("users").child(username).child("theseis").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot giveMeTheKey : dataSnapshot.getChildren()){
                    closed.add(giveMeTheKey.getKey());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, closed);
                listView.setAdapter(arrayAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        parkingToCancel = closed.get(i);
                        mDatabase.child("users").child(username).child("theseis").child(parkingToCancel).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot giveMeTheKey : dataSnapshot.getChildren()){
                                    closedTheseis.add(giveMeTheKey.getKey());
                                }
                                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, closedTheseis);
                                listView.setAdapter(arrayAdapter2);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        final String cancel = closedTheseis.get(i);
                                        mDatabase.child("users").child(username).child("theseis").child(parkingToCancel).child(cancel).removeValue();
                                        mDatabase.child("parkings").child(parkingToCancel).child("theseis").child(cancel).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.getChildrenCount() == 1L){
                                                    mDatabase.child("parkings").child(parkingToCancel).child("theseis").child(cancel).setValue("free");
                                                }else{

                                                    mDatabase.child("parkings").child(parkingToCancel).child("theseis").child(cancel).child(formatedDate).removeValue();

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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void scheduleNotification() {
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);

        Intent intent = new Intent("kon.action.DISPLAY_NOTIFICATION");
        PendingIntent broadcast = PendingIntent.getBroadcast(this,100,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),broadcast);
    }


}
