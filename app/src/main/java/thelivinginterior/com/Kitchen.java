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

public class Kitchen extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        recyclerView = findViewById(R.id.bad_room);
        arrayList= new ArrayList<>();
        recImage();

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Kitchen.this, MainActivity.class);
        startActivity(i);


        super.onBackPressed();
    }

    private void recImage() {


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        for (int i = 0; i < 5; i++) {
            String path = i + ".jpg";


            FirebaseStorage storage = FirebaseStorage.getInstance();

            StorageReference storageReference = storage.getReference().child("viewPagerPics")
                    .child(path);

            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    arrayList.add(uri.toString());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(new MyAdapter(getBaseContext(), arrayList));
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