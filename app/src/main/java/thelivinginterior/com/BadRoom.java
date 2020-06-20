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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BadRoom extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_room);
        recyclerView = findViewById(R.id.bad_room);
        arrayList=new ArrayList();
        recImage();

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(BadRoom.this, MainActivity.class);
        startActivity(i);


        super.onBackPressed();
    }

    private void recImage() {


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        for (int i = 1; i <= 5; i++) {
            String path = i + ".jpeg";


            FirebaseStorage storage = FirebaseStorage.getInstance();

            StorageReference storageReference = storage.getReference().child("DemoPics")
                    .child(path);

            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    arrayList.add(uri.toString());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(new RecyclerAdapter(arrayList,BadRoom.this));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("myApp", "error");
                }
            });


        }
    }
}