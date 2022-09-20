package com.solutiontech.imgpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.solutiontech.imgpick.activities.Gallery;
import com.solutiontech.imgpick.activities.Pickimage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ongallery(View view) {
        Intent i = new Intent(this, Gallery.class);
        startActivity(i);

    }

    public void onSelectimg(View view) {
        Intent i = new Intent(this, Pickimage.class);
        startActivity(i);
    }


}