package com.example.come.ui.home;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class HorizontalScrollAdapter_Fetched extends PagerAdapter {

    private Context ctx;
   // private int[] ImageArray; //= new int[] {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};
    private Uri[] UriArray;
    HorizontalScrollAdapter_Fetched(Context context) {ctx = context;}


    @Override
    public int getCount() {
        return UriArray.length;
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
        imageView.setImageURI(UriArray[position]); //when we use old part
        //imageView.setImageURI(UriArray[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

    public void setUriArray(Uri[] uriArray) {
        UriArray = uriArray;
    } //this is the function when we use non uris for the first part of project

    //public void setUriArray(Uri[] urisOfPosts) {UriArray = urisOfPosts;}
}

