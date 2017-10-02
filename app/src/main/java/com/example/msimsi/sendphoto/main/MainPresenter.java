package com.example.msimsi.sendphoto.main;

import android.util.Log;

/**
 * Created by MSIMSI on 2.10.2017.
 * Copyright (c) 2017 Kolektif Labs to present
 * All rights reserved.
 */


public class MainPresenter implements MainContract.presenter {

    private final MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onUploadPhotoClicked() {
        Log.e("FEWEFWEF","EFEWFFWE");
    }
}
