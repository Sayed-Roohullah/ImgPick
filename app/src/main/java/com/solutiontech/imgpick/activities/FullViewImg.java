package com.solutiontech.imgpick.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.solutiontech.imgpick.R;

import java.io.File;
import java.util.ArrayList;

public class FullViewImg extends AppCompatActivity {

    int position;
    ImageView imageView;
    String imageLink;
    ArrayList<File> myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view_img);

        imageView = findViewById(R.id.fullimage);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            myImage = (ArrayList) bundle.getParcelableArrayList("imageList");
            position = bundle.getInt("pos", 0);
            imageLink = bundle.getString("image");

        }

        imageView.setImageURI(Uri.parse(imageLink));

        imageView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                position=((position -1 )<0)? (myImage.size()-1) :(position-1);
                imageView.setImageURI(Uri.parse(myImage.get(position).toString()));
            }

            public void onSwipeRight() {
                //    Toast.makeText(getApplicationContext(), "Swiped right", Toast.LENGTH_SHORT).show();
                position = ((position + 1) % myImage.size());
                imageView.setImageURI(Uri.parse(myImage.get(position).toString()));
            }

            public void onSwipeLeft() {
                //    Toast.makeText(getApplicationContext(), "Swiped left", Toast.LENGTH_SHORT).show();
                position=((position -1 )<0)? (myImage.size()-1) :(position-1);
                imageView.setImageURI(Uri.parse(myImage.get(position).toString()));
            }

            public void onSwipeBottom() {
                //   Toast.makeText(getApplicationContext(), "Swiped bottom", Toast.LENGTH_SHORT).show();
                position = ((position + 1) % myImage.size());
                imageView.setImageURI(Uri.parse(myImage.get(position).toString()));

            }

        });
    }

    class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context ctx) {
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 300;
            private static final int SWIPE_VELOCITY_THRESHOLD = 300;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.i("TAG", "onSingleTapConfirmed:");
                Toast.makeText(getApplicationContext(), "Single Tap Detected", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.i("TAG", "onLongPress:");
                Toast.makeText(getApplicationContext(), "Long Press Detected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(getApplicationContext(), "Double Tap Detected", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public void onSwipeRight() {
        }

        public void onSwipeLeft() {
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }
    }

}