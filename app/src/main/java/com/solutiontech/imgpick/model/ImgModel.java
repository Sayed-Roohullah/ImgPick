package com.solutiontech.imgpick.model;

import android.net.Uri;
import android.provider.ContactsContract;

public class ImgModel {
     private String name;
     private Uri uri;

    public ImgModel(String name, Uri uri) {
        this.name = name;
        this.uri = uri;
    }
    public ImgModel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
