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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

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
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment implements CardView.OnClickListener {
    ViewPager viewPager;
    CircleIndicator indicator;
    RecyclerView recyclerView,recyclerView2;
    DatabaseReference databaseReference;
    ArrayList<Pojo> arrayList;
    int pageCount=0;

    CardView b1,b2,b3,b4,b5,b6,b7,b8;

    ArrayList<String> picArray,pic;
    ArrayList<String> demoPic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        picArray = new ArrayList<>();


        View v= inflater.inflate(R.layout.fragment_home, container, false);
        viewPager=v.findViewById(R.id.viewpager);
        indicator=v.findViewById(R.id.indicator);
        recyclerView=v.findViewById(R.id.recyclerView);
        recyclerView2=v.findViewById(R.id.recyclerView2);
        b1=v.findViewById(R.id.bedRoom);
        b2=v.findViewById(R.id.bathRoom);
        b3=v.findViewById(R.id.drawingRoom);
        b4=v.findViewById(R.id.kitchen);
        b5=v.findViewById(R.id.terrace);
        b6=v.findViewById(R.id.frontElevation);
        b7=v.findViewById(R.id.furniture);
        b8=v.findViewById(R.id.maps);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b8.setOnClickListener(this);
        b7.setOnClickListener(this);



        viewPagerImages();
        getVideo();
        getImages();


        return v;
    }
    public void viewPagerImages() {

        databaseReference= FirebaseDatabase.getInstance().getReference("ViewPager");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                demoPic = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Pojo pojo=snapshot.getValue(Pojo.class);
                    demoPic.add(pojo.getImage());
                    viewPager.setAdapter(new ViewPagerAdapter(getContext(), demoPic));
                    indicator.setViewPager(viewPager);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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


    @Override
    public void onClick(View v) {
        if (v==b1)
        {
            Intent i=new Intent(getActivity(),BadRoom.class);
            startActivity(i);
        }
        if (v==b2)
        {
            Intent i=new Intent(getActivity(),BathRoom.class);
            startActivity(i);
        }
        if (v==b3)
        {
            Intent i=new Intent(getActivity(),DrawingRoom.class);
            startActivity(i);
        }
        if (v==b4)
        {
            Intent i=new Intent(getActivity(),Kitchen.class);
            startActivity(i);
        }
        if (v==b5)
        {
            Intent i=new Intent(getActivity(),Terrace.class);
            startActivity(i);
        }
        if (v==b6)
        {
            Intent i=new Intent(getActivity(),Frontelevation.class);
            startActivity(i);
        }
       if (v==b7)
        {
            Intent i=new Intent(getActivity(),MainActivity.class);
            i.putExtra("which","product");
            startActivity(i);
        }
        if(v==b8)
        {
            Intent i=new Intent(getActivity(),MainActivity.class);
            i.putExtra("which","services");
            startActivity(i);
        }


    }

    private void getImages()
    {
        StaggeredGridLayoutManager linearLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        databaseReference= FirebaseDatabase.getInstance().getReference("HomeImages");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList=new ArrayList<Pojo>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Pojo pojo=snapshot.getValue(Pojo.class);
                    arrayList.add(pojo);
                    recyclerView.setAdapter(new Adaapter(getActivity(),arrayList));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void getVideo()
    {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(linearLayoutManager);

        databaseReference= FirebaseDatabase.getInstance().getReference("Video");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList=new ArrayList<Pojo>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Pojo pojo=snapshot.getValue(Pojo.class);
                    arrayList.add(pojo);
                    recyclerView2.setAdapter(new Adaapter2(getActivity(),arrayList));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}