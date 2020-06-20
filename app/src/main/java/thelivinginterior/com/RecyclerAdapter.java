package thelivinginterior.com;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewAdapter> {
    ArrayList arrayList;
    Activity activity;
    RecyclerAdapter(ArrayList arrayList,Activity activity)
    {
        this.arrayList=arrayList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=activity.getLayoutInflater().inflate(R.layout.slide,parent,false);
        return new MyViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter holder, int position) {
        String link=arrayList.get(position).toString();
        Glide.with(activity).load(link).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewAdapter extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }

}
