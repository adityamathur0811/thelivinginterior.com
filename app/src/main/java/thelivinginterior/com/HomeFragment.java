package thelivinginterior.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {
    ViewPager viewPager;
    CircleIndicator indicator;
    int pagecount=0;
    Integer[] pic ={R.drawable.a,R.drawable.images,R.drawable.pic};
    ArrayList<Integer> picarray=new ArrayList<Integer>();

    private static Uri filepath;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, container, false);
        viewPager=v.findViewById(R.id.viewpager);
        indicator=v.findViewById(R.id.indicator);
        viewPagerImages();



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