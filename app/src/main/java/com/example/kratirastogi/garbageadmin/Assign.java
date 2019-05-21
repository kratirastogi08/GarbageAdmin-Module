package com.example.kratirastogi.garbageadmin;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assign extends AppCompatActivity {
ListView lv;
    List<ListViewItemDTO> ret ;
    String dat;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);
        lv=findViewById(R.id.lv);
        Intent intent=getIntent();
        dat=intent.getStringExtra("date");
        ret=new ArrayList<ListViewItemDTO>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Collector");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                  Data1 d=  ds.getValue(Data1.class);
                if(!dat.equals(d.getDate().getD1().getDate())&&!dat.equals(d.getDate().getD1().getDate())&&!dat.equals(d.getDate().getD1().getDate()))
                {
                    ListViewItemDTO dto = new ListViewItemDTO();
                    dto.setName(d.getName());
                    dto.setMobno(d.getNumber());
                    dto.setId(d.getId());
                    dto.setLat(d.getLat());
                    dto.setLng(d.getLng());
                    ret.add(dto);

                }
                }
                final ListViewBaseAdapter listViewDataAdapter = new ListViewBaseAdapter(Assign.this, ret);

                listViewDataAdapter.notifyDataSetChanged();

                lv.setAdapter(listViewDataAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ListViewItemDTO item = (ListViewItemDTO) parent.getItemAtPosition(position);
                        Toast.makeText(Assign.this, ""+item.getId(), Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(Assign.this,NonAssignedCollector.class);
                        i.putExtra("id",item.getId());
                        i.putExtra("lat",String.valueOf(item.getLat()));
                        i.putExtra("lng",String.valueOf(item.getLng()));
                        i.putExtra("date",dat);
                        startActivity(i);

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
