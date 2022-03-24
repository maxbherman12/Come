package com.example.come.ui.post;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.come.ui.home.HorizontalScrollAdapter;

public class HorizontalScrollAdapter_Post  extends PagerAdapter {

    private Context ctx;
    private int[] ButtonArray; //= new int[] {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};
    HorizontalScrollAdapter_Post(Context context) {ctx = context;}


    @Override
    public int getCount() {
        return ButtonArray.length;
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
        LayoutInflater inflater = LayoutInflater.from(ctx);
        LinearLayout linearLayout = new LinearLayout(ctx);
        Button pictureAddButton = new Button(linearLayout.getContext());
        pictureAddButton.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
        pictureAddButton.setText("Add picture");
        pictureAddButton.setBackgroundColor(Color.TRANSPARENT);
        //pictureAddButton.setHeight(LayoutParams.MATCH_PARENT);
        container.addView(pictureAddButton, 0);
        return pictureAddButton;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((Button) object);
    }

    public void setButtonArray(int[] buttonArray) {
        ButtonArray = buttonArray;
    }
}


