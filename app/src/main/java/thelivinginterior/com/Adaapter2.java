package thelivinginterior.com;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adaapter2 extends RecyclerView.Adapter<Adaapter2.MyViewHolder> {
ArrayList<Pojo> arrayList;
Activity activity;
Adaapter2(Activity activity, ArrayList<Pojo> arrayList)
{
    this.activity=activity;
    this.arrayList=arrayList;
}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v= activity.getLayoutInflater().inflate(R.layout.myrow2,parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
    Pojo pojo=arrayList.get((arrayList.size()-1)-position);

    holder.textView.setText(""+pojo.getDescription());
        Uri uri=Uri.parse(pojo.getImage());
        holder.videoView.setVideoURI(uri);
        holder.videoView.seekTo(5);
        holder.videoView.start();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.videoView.seekTo(5);
                holder.videoView.start();
            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
    VideoView videoView;
    TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView=itemView.findViewById(R.id.video_rec);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
