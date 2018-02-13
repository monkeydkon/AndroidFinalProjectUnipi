package com.nplab.monkeydkon.androidfinalprojectunipi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.util.Arrays.asList;

public class Main4Activity extends AppCompatActivity {

    ArrayList<String> parking;

    DatePicker datePicker;
    TimePicker timePicker;
    Button button6, button7, button8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        final ListView listView = findViewById(R.id.listView);

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
        String formatedDate = simpleDateFormat.format(new Date(year, month, day));


        datePicker.setVisibility(View.GONE);
        button6.setVisibility(View.GONE);
        timePicker.setVisibility(View.VISIBLE);
        button7.setVisibility(View.VISIBLE);

        Toast.makeText(this, "select time of arrival",Toast.LENGTH_SHORT).show();

    }

    public void timeSet(View view){

        String arrivalHour = timePicker.getCurrentHour().toString();
        String arrivalMinute = timePicker.getCurrentMinute().toString();
        Toast.makeText(this,"select time of leave",Toast.LENGTH_SHORT).show();
        button7.setVisibility(View.GONE);
        button8.setVisibility(View.VISIBLE);


        //                                             C O N T I N U E
        //                                             H    E    R   E
    }

    public void allSet(View view){
        String dismissHour = timePicker.getCurrentHour().toString();
        String dismissMinute = timePicker.getCurrentMinute().toString();
        Toast.makeText(this, dismissHour+" "+dismissMinute,Toast.LENGTH_SHORT).show();
    }
}
