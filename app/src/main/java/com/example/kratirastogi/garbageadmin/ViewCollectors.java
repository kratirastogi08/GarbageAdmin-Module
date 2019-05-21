package com.example.kratirastogi.garbageadmin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ViewCollectors extends AppCompatActivity {
int day,month,year;
    java.sql.Date dated;
    Button cal,assign;
    DatabaseReference databaseReference;
    TextView tv,textView4,tap;
   int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_collectors);
        databaseReference= FirebaseDatabase.getInstance().getReference("Collector");
        cal=findViewById(R.id.cal);
        assign=findViewById(R.id.assign);
        textView4=findViewById(R.id.textView4);
        tap=findViewById(R.id.tap);
         tv=findViewById(R.id.tv);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker = new DatePickerDialog(ViewCollectors.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yeard, int monthd, int dayOfMonth) {
                        day = dayOfMonth;
                        month = monthd;
                        year = yeard;
                        mcurrentDate.set(year, month, day);



                        java.util.Date d = mcurrentDate.getTime();
                        dated = new java.sql.Date(d.getTime());
                       databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(DataSnapshot dataSnapshot) {
                               for(DataSnapshot ds:dataSnapshot.getChildren())
                               {
                                com.example.kratirastogi.garbageadmin.Data1 d= ds.getValue(com.example.kratirastogi.garbageadmin.Data1.class);


                                if(dated.toString().equals(d.getDate().getD1().getDate()) ||dated.toString().equals(d.getDate().getD2().getDate())|| dated.toString().equals(d.getDate().getD3().getDate()))
                                {
                                   c++;
                                }
                               }
                               assign.setVisibility(View.GONE);
                               tv.setVisibility(View.VISIBLE);
                               tap.setVisibility(View.INVISIBLE);
                               textView4.setVisibility(View.VISIBLE);
                               tv.setText(String.valueOf(c));
                               if(c==0)
                               {
                                   tap.setVisibility(View.VISIBLE);
                                   tv.setVisibility(View.INVISIBLE);
                                   textView4.setVisibility(View.INVISIBLE);
                                   tap.setText("Not assigned any driver");
                                   assign.setVisibility(View.VISIBLE);
                                   assign.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent i=new Intent(ViewCollectors.this,Assign.class);
                                           i.putExtra("date",dated.toString());
                                           startActivity(i);
                                       }
                                   });
                               }
                               c=0;



                           }

                           @Override
                           public void onCancelled(DatabaseError databaseError) {

                           }

                       });
                    }
                }, mYear, mMonth, mDay);


                mDatePicker.getDatePicker().setMinDate(new Date().getTime());
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }

        });

    }
}
