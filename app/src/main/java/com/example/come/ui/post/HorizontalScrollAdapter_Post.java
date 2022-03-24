package com.example.come.ui.post;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.come.R;
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
        /*LayoutInflater inflater = LayoutInflater.from(ctx);
        LinearLayout linearLayout = new LinearLayout(ctx);
        Button pictureAddButton = new Button(linearLayout.getContext());
        pictureAddButton.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
        pictureAddButton.setText("Add picture");
        pictureAddButton.setBackgroundColor(Color.TRANSPARENT);
        //pictureAddButton.setHeight(LayoutParams.MATCH_PARENT);
        container.addView(pictureAddButton, 0);
        return pictureAddButton;*/
        ImageView imageView = new ImageView(ctx);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //imageView.setImageResource(ImageArray[position]);
        imageView.setImageResource(R.drawable.add_your_image);
        container.addView(imageView, 0);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0) {
                    Toast.makeText(ctx, "One", Toast.LENGTH_SHORT).show();
                } else if(position == 1) {
                    Toast.makeText(ctx, "Two", Toast.LENGTH_SHORT).show();
                } else if(position == 2) {
                    Toast.makeText(ctx, "Three", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

    public void setButtonArray(int[] imageArray) {
        ImageArray = imageArray;
    }
}


