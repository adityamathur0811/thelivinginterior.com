package thelivinginterior.com;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {
    ViewPager viewPager;
    CircleIndicator indicator;
    int pagecount=0;

    String imgUrl;


    Integer[] pic ={R.drawable.a,R.drawable.images,R.drawable.pic};
    ArrayList<Integer> picarray=new ArrayList<Integer>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, container, false);
        viewPager=v.findViewById(R.id.viewpager);
        indicator=v.findViewById(R.id.indicator);
        viewPagerImages();


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference().child("viewPagerPics")
                .child("1.jpeg");

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("myApp",uri.toString());
                Toast.makeText(getContext(), ""+uri.toString(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("myApp","error");
            }
        });





        return v;
    }
    public void viewPagerImages()
    {
        for (int i=0;i<pic.length;i++)

            picarray.add(pic[i]);

        viewPager.setAdapter( new ViewPagerAdapter(getContext(),picarray));
        indicator.setViewPager(viewPager);

        final Handler handler=new Handler();
        final Runnable update=new Runnable() {
            @Override
            public void run()
            {
                if (pagecount==pic.length)
                {
                    pagecount=0;

                }
                viewPager.setCurrentItem(pagecount++,true);


            }
        };
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },3000,3000);


    }



}