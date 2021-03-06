package thelivinginterior.com;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adaapter extends RecyclerView.Adapter<Adaapter.MyViewHolder> {
ArrayList<Pojo> arrayList;
Activity activity;

Adaapter(Activity activity, ArrayList<Pojo> arrayList)
{
    this.activity=activity;
    this.arrayList=arrayList;
}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v= activity.getLayoutInflater().inflate(R.layout.myrow,parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Pojo pojo=arrayList.get((arrayList.size()-1)-position);

    if(pojo.getDescription().isEmpty() && !pojo.getImage().isEmpty())
    {
        Glide.with(activity).load(pojo.getImage()).into(holder.imageView);
        holder.textView.setVisibility(View.GONE);
    }
    else {
        holder.textView.setText("" + pojo.getDescription());
        Glide.with(activity).load(pojo.getImage()).into(holder.imageView);

    }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);

            textView=itemView.findViewById(R.id.text);
        }
    }
}
