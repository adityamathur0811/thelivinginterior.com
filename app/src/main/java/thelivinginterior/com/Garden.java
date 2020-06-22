package thelivinginterior.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Garden extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Pojo> arrayList;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);
        recyclerView = findViewById(R.id.garden);
        arrayList= new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference("BadRoomImages");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList=new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Pojo pojo=snapshot.getValue(Pojo.class);
                    arrayList.add(pojo);
                    recyclerView.setAdapter(new Adaapter(Garden.this,arrayList));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Garden.this, MainActivity.class);
        startActivity(i);


        super.onBackPressed();
    }


}