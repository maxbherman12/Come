package com.example.come.ui.post;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.come.ui.home.HorizontalScrollAdapter;

public class HorizontalScrollAdapter_Post  extends PagerAdapter {

    private Context ctx;
    private int[] ImageArray; //= new int[] {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};
    HorizontalScrollAdapter_Post(Context context) {ctx = context;}


    @Override
    public int getCount() {
        return ImageArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // this creates imageview which contains the image, or page for given position
        // creates page for the views i want to display, container is the view
        ImageView imageView = new ImageView(ctx);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(ImageArray[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

    public void setImageArray(int[] imageArray) {
        ImageArray = imageArray;
    }
}


