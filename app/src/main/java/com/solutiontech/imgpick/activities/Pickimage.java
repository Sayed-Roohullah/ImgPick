package com.solutiontech.imgpick.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.solutiontech.imgpick.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Pickimage extends AppCompatActivity {
    ImageView imageView ;
    Bitmap ProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickimage);
        imageView = findViewById(R.id.image);


    }


    public void onSelect(View view) {
        pickImage();
    }
    public void pickImage() {
        // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1 ) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                ProfilePic = extras.getParcelable("data");
                imageView.setImageBitmap(ProfilePic);
            }
        }
    }


}