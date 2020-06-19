package thelivinginterior.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    private final Context context;
    ArrayList<String> image;
    LayoutInflater inflater;
    public ViewPagerAdapter(Context context, ArrayList<String> image)
    {
        this.image=image;
        this.context=context;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=inflater.inflate(R.layout.slide,container,false);
        ImageView img= view.findViewById(R.id.imageview);
        String link=image.get(position);
        Glide.with(context).load(link).into(img);
        container.addView(view,0);
        return view;
    }

}
