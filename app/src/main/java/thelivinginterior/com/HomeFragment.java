package thelivinginterior.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment implements Button.OnClickListener {
    ViewPager viewPager;
    CircleIndicator indicator;
    RecyclerView recyclerView;
    int pageCount=0;

    Button b1,b2,b3,b4,b5,b6;



    ArrayList<String> picArray;
    ArrayList<String> demoPic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        picArray = new ArrayList<>();
        demoPic = new ArrayList<>();
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        viewPager=v.findViewById(R.id.viewpager);
        indicator=v.findViewById(R.id.indicator);
        recyclerView=v.findViewById(R.id.rec_id);
        b1=v.findViewById(R.id.bedRoom);
        b2=v.findViewById(R.id.drawingRoom);
        b3=v.findViewById(R.id.kitchen);
        b4=v.findViewById(R.id.bathRoom);
        b5=v.findViewById(R.id.terrace);
        b6=v.findViewById(R.id.garden);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);



        viewPagerImages();
        recImage();


        return v;
    }
    public void viewPagerImages() {

        for (int i = 0; i < 5; i++) {
            String path = i + ".jpg";


            FirebaseStorage storage = FirebaseStorage.getInstance();

            StorageReference storageReference = storage.getReference().child("viewPagerPics")
                    .child(path);

            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    demoPic.add(uri.toString());
                    viewPager.setAdapter(new ViewPagerAdapter(getContext(), demoPic));
                    indicator.setViewPager(viewPager);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("myApp", "error");
                }
            });


        }

            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (pageCount == demoPic.size())
                    {
                        pageCount = 0;
                    }
                    viewPager.setCurrentItem(pageCount++, true);


                }
            };
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, 3000, 3000);




    }
    private void recImage() {

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        for (int i = 1; i <= 5; i++) {
            String path = i + ".jpeg";


            FirebaseStorage storage = FirebaseStorage.getInstance();

            StorageReference storageReference = storage.getReference().child("DemoPics")
                    .child(path);

            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    picArray.add(uri.toString());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(new RecyclerAdapter(picArray, getActivity()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("myApp", "error");
                }
            });


        }
    }


    @Override
    public void onClick(View v) {
        if (v==b1)
        {
            Intent i=new Intent(getActivity(),BadRoom.class);
            startActivity(i);
        }
        if (v==b2)
        {
            Intent i=new Intent(getActivity(),DrawingRoom.class);
            startActivity(i);
        }


    }
}