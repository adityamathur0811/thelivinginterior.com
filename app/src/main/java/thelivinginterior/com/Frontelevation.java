package thelivinginterior.com;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public  class Frontelevation extends AppCompatActivity  {
    RecyclerView recyclerView;
    ArrayList<Pojo> arrayList;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_elevation);
        recyclerView = findViewById(R.id.garden);
        arrayList= new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference("FrontElevation");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList=new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Pojo pojo=snapshot.getValue(Pojo.class);
                    arrayList.add(pojo);
                    recyclerView.setAdapter(new Adaapter(Frontelevation.this,arrayList));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Frontelevation.this, MainActivity.class);
        startActivity(i);


        super.onBackPressed();
    }


}