package com.example.come.ui.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.come.R;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class HorizontalScrollAdapter_Post  extends PagerAdapter {



    private int currentPosition;
    //private int count=6;
    private Activity activity;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private Context ctx;
    private int[] ImageArray; //= new int[] {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};
    //private  int PICK_PHOTO_CODE= 999;
    HorizontalScrollAdapter_Post(Context context) {ctx = context;
    activity = (Activity) ctx;
    }



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
        //instantiatedImageViews+=1;
        ImageView imageView = new ImageView(ctx);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //imageView.setImageResource(ImageArray[position]);
        imageView.setImageResource(R.drawable.add_your_image);
        container.addView(imageView, position);
        imageView.setTag(position);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(position == 0) {
                    Toast.makeText(ctx, "One", Toast.LENGTH_SHORT).show();
                } else if(position == 1) {
                    Toast.makeText(ctx, "Two", Toast.LENGTH_SHORT).show();
                } else if(position == 2) {
                    Toast.makeText(ctx, "Three", Toast.LENGTH_SHORT).show();
                }
                 */
                currentPosition=position;
                selectImage();
                  }

            });

        //count=+1;
        return imageView;
    }
    private void selectImage(){
        //Activity activity = (Activity) ctx;
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

    public void setButtonArray(int[] imageArray) {
        ImageArray = imageArray;
    }




}


