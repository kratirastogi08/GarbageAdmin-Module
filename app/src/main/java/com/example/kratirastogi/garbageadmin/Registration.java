package com.example.kratirastogi.garbageadmin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Registration extends AppCompatActivity {
EditText mobno,name,plate,loc;
Button reg;
LatLng l;
String n,m,vn,a;
    DatabaseReference databaseReference;
String newdate,newdate1,newdate2;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        imageView = findViewById(R.id.imageView);
        Glide.with(this).asGif().load(R.mipmap.garbi).into(imageView);
        ImageView t = findViewById(R.id.imageView9);
        t.setBackgroundColor(Color.argb(50, 255, 140, 0));
        name=findViewById(R.id.name);
        mobno=findViewById(R.id.mobno);
        plate=findViewById(R.id.plate);
        loc=findViewById(R.id.loc);
        reg=findViewById(R.id.reg);
         databaseReference=FirebaseDatabase.getInstance().getReference("Collector").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = name.getText().toString();
                m = mobno.getText().toString();
                vn = plate.getText().toString();
                a = loc.getText().toString();
                if (TextUtils.isEmpty(n)) {
                    name.setError("Enter name");
                } else if (TextUtils.isEmpty(m)) {
                    mobno.setError("Enter mobile number");
                } else if (TextUtils.isEmpty(vn)) {
                    plate.setError("Enter vehicle license plate");
                } else if (TextUtils.isEmpty(a)) {
                    loc.setError("Enter start location");
                } else {
                    l = getLocationFromAddress(Registration.this, a);
                    final java.util.Calendar currentdate = java.util.Calendar.getInstance();
                    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    currentdate.add(java.util.Calendar.DAY_OF_MONTH, 1);
                    newdate = sdf.format(currentdate.getTime());

                    currentdate.add(java.util.Calendar.DAY_OF_MONTH, 1);
                    newdate1 = sdf.format(currentdate.getTime());

                    currentdate.add(java.util.Calendar.DAY_OF_MONTH, 1);
                    newdate2 = sdf.format(currentdate.getTime());

                    databaseReference.setValue(new Data1(FirebaseAuth.getInstance().getCurrentUser().getUid(), n, l.latitude, l.longitude, "Inactive", vn, m, new Date(new Date1("1970-01-01", l.latitude, l.longitude), new Date2("1970-01-01", l.latitude, l.longitude), new Date3("1970-01-01", l.latitude, l.longitude)))).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Registration.this, "saved", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Registration.this, Home.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Registration.this, "failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }
}
