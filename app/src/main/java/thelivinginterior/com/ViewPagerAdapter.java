package thelivinginterior.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    ArrayList<Integer> image;
    LayoutInflater inflater;
    public ViewPagerAdapter(Context context, ArrayList<Integer> image)
    {
        this.image=image;
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
        ImageView img=(ImageView) view.findViewById(R.id.imageview);
        img.setImageResource(image.get(position));
        container.addView(view,0);
        return view;
    }

}
