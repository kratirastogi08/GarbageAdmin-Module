package com.example.kratirastogi.garbageadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NonAssignedCollector extends AppCompatActivity {
RadioButton r1,r2,r3,r4,r5;
Button but;
String id,lat,lng,dat;
DatabaseReference databaseReference,refer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_assigned_collector);
        Intent i=getIntent();
       id= i.getStringExtra("id");
       lat=i.getStringExtra("lat");
       lng=i.getStringExtra("lng");
       dat=i.getStringExtra("date");
       databaseReference= FirebaseDatabase.getInstance().getReference("Collector").child(id).child("date");
       refer=FirebaseDatabase.getInstance().getReference("Collector").child(id);
        r1=findViewById(R.id.r1);

        r3=findViewById(R.id.r3);
        r4=findViewById(R.id.r4);
        r5=findViewById(R.id.r5);
        but=findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(r1.isChecked())
            {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar=Calendar.getInstance();
                try {
                    calendar.setTime(sdf.parse(dat));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                final String newdate=sdf.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH,1);
                final String newdate1=sdf.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH,1);
                final String newdate2=sdf.format(calendar.getTime());
                databaseReference.setValue(new Date(new Date1(newdate,Double.valueOf(lat),Double.valueOf(lng)),new Date2(newdate1,Double.valueOf(lat),Double.valueOf(lng)),new Date3(newdate2,Double.valueOf(lat),Double.valueOf(lng)))).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {    refer.child("State").setValue("1");
                            Toast.makeText(NonAssignedCollector.this, "assigned", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

                else
                    if(r3.isChecked())
                    {

                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                        Calendar calendar=Calendar.getInstance();
                        try {
                            calendar.setTime(sdf.parse(dat));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        final String newdate=sdf.format(calendar.getTime());
                        calendar.add(Calendar.DAY_OF_MONTH,3);
                        final String newdate1=sdf.format(calendar.getTime());
                        calendar.add(Calendar.DAY_OF_MONTH,3);
                        final String newdate2=sdf.format(calendar.getTime());
                        databaseReference.setValue(new Date(new Date1(newdate,Double.valueOf(lat),Double.valueOf(lng)),new Date2(newdate1,Double.valueOf(lat),Double.valueOf(lng)),new Date3(newdate2,Double.valueOf(lat),Double.valueOf(lng)))).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {   refer.child("State").setValue("3");
                                    Toast.makeText(NonAssignedCollector.this, "assigned", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                    else
                    if(r4.isChecked())
                    {

                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                        Calendar calendar=Calendar.getInstance();
                        try {
                            calendar.setTime(sdf.parse(dat));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        final String newdate=sdf.format(calendar.getTime());
                        calendar.add(Calendar.DAY_OF_MONTH,5);
                        final String newdate1=sdf.format(calendar.getTime());
                        calendar.add(Calendar.DAY_OF_MONTH,5);
                        final String newdate2=sdf.format(calendar.getTime());
                        databaseReference.setValue(new Date(new Date1(newdate,Double.valueOf(lat),Double.valueOf(lng)),new Date2(newdate1,Double.valueOf(lat),Double.valueOf(lng)),new Date3(newdate2,Double.valueOf(lat),Double.valueOf(lng)))).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {   refer.child("State").setValue("5");
                                    Toast.makeText(NonAssignedCollector.this, "assigned", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                    else
                    if(r5.isChecked())
                    {

                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                        Calendar calendar=Calendar.getInstance();
                        try {
                            calendar.setTime(sdf.parse(dat));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        final String newdate=sdf.format(calendar.getTime());
                        calendar.add(Calendar.DAY_OF_MONTH,7);
                        final String newdate1=sdf.format(calendar.getTime());
                        calendar.add(Calendar.DAY_OF_MONTH,7);
                        final String newdate2=sdf.format(calendar.getTime());
                        databaseReference.setValue(new Date(new Date1(newdate,Double.valueOf(lat),Double.valueOf(lng)),new Date2(newdate1,Double.valueOf(lat),Double.valueOf(lng)),new Date3(newdate2,Double.valueOf(lat),Double.valueOf(lng)))).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {   refer.child("State").setValue("7");
                                    Toast.makeText(NonAssignedCollector.this, "assigned", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
            }
        });
    }
}
