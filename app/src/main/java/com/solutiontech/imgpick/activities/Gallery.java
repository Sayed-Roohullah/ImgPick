package com.solutiontech.imgpick.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
 import android.provider.MediaStore;
import android.widget.Toast;

import com.solutiontech.imgpick.R;
import com.solutiontech.imgpick.adapter.CustomAdapter;
import com.solutiontech.imgpick.model.ImgModel;


import java.util.ArrayList;
import java.util.List;

public class Gallery extends AppCompatActivity implements onItemClickListener {

        RecyclerView recyclerView;
        ArrayList<ImgModel> myimageFile;
        CustomAdapter customAdapter;
        List<String> mList;
        String absolut_pathImgs;

        String absolut_pathname;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gallery);


            recyclerView = findViewById(R.id.recycleview);
            CheckUserPermsions();
            mList = new ArrayList<>();

        }

        void CheckUserPermsions() {
            if (Build.VERSION.SDK_INT >= 23) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{
                                    Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                    return;
                }
            }

            display();  // init the contact list

        }


        //get acces to location permsion
        final private int REQUEST_CODE_ASK_PERMISSIONS = 123;


        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            switch (requestCode) {
                case REQUEST_CODE_ASK_PERMISSIONS:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        display();  // init the contact list
                    } else {
                        // Permission Denied
                        Toast.makeText(this, "your message", Toast.LENGTH_SHORT)
                                .show();
                    }
                    break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }


        private ArrayList<ImgModel> findImage() {

             Uri uri;
             Cursor cursor;
             int data_index,index_name;


            ArrayList<ImgModel> imageList = new ArrayList< >();
              absolut_pathImgs = null;
              absolut_pathname = null;
            uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

            cursor = getApplicationContext().getContentResolver().query(uri,projection,
                    null,null,null);
            if (cursor!=null) {
                data_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                index_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                while (cursor.moveToNext()) {
                    ImgModel model = new ImgModel();
                    absolut_pathImgs = cursor.getString(data_index);
                    absolut_pathname = cursor.getString(index_name);
                    model.setUri(Uri.parse(absolut_pathImgs));
                    model.setName(absolut_pathname);
                    imageList.add(model);
                }
            }
            return imageList;
        }


        private void display() {

            myimageFile = new ArrayList<>();
            myimageFile = findImage();


            for (int j = 0; j < myimageFile.size(); j++) {

                mList.add(String.valueOf(myimageFile.get(j)));
                customAdapter = new CustomAdapter(myimageFile, this);
                recyclerView.setAdapter(customAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

            }


        }


        @Override
        public void onClick(int position) {

            Toast.makeText(this, "Postion: "+position, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Gallery.this,FullViewImg.class);
            intent.putExtra("image",String.valueOf(myimageFile.get(position)));
            intent.putExtra("pos",position);
            intent.putExtra("imageList",myimageFile);
            startActivity(intent);

        }
}