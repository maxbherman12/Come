package com.example.come.ui.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.come.R;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HorizontalScrollAdapter_Post  extends PagerAdapter {


    private Uri[] uriArray;
    private int currentPosition;
    private Activity activity;
    private Context ctx;
    private int[] ImageArray;


    HorizontalScrollAdapter_Post(Context context, Uri[] UriArray) {
    ctx = context;
    activity = (Activity) ctx;
    uriArray=UriArray;
    }



    @Override
    public int getCount() {
        return uriArray.length;
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
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageURI(uriArray[position]);
        container.addView(imageView, 0);
        imageView.setTag(position);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition=position;
                selectImage();
                  }

            });
        return imageView;
    }

    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        Bundle extras = new Bundle();
        extras.putString("Username", "teststring");
        intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtras(extras);
                activity.startActivityForResult(Intent.createChooser(intent, "Selectet_Image"), 999 );

        }

    public int getCurrentPosition() {
        return currentPosition;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}


