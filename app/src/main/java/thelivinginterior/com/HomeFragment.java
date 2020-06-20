package thelivinginterior.com;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {
    ViewPager viewPager;
    CircleIndicator indicator;
    int pageCount=0;


    
    ArrayList<String> picArray;
    ArrayList<String> demoPic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, container, false);
        viewPager=v.findViewById(R.id.viewpager);
        indicator=v.findViewById(R.id.indicator);

        viewPagerImages();



        return v;
    }
    public void viewPagerImages() {
        picArray = new ArrayList<>();
        demoPic = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String path = i + ".jpeg";


            FirebaseStorage storage = FirebaseStorage.getInstance();

            StorageReference storageReference = storage.getReference().child("viewPagerPics")
                    .child(path);

            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    demoPic.add(uri.toString());
                    picArray.addAll(demoPic);
                    viewPager.setAdapter(new ViewPagerAdapter(getContext(), picArray));
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
                    if (pageCount == demoPic.size()) {
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

}