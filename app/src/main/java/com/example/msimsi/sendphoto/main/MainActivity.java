package com.example.msimsi.sendphoto.main;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.msimsi.sendphoto.R;
import com.example.msimsi.sendphoto.data.entity.PhotoItem;
import com.example.msimsi.sendphoto.data.entity.UploadPhotoItem;
import com.example.msimsi.sendphoto.utility.CheckPermission;
import com.example.msimsi.sendphoto.utility.FileUtils;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by MSIMSI on 2.10.2017.
 * All rights reserved.
 */

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.main_take_photo_button)
    Button takePhoto;

    @BindView(R.id.main_send_photo_button)
    Button sendPhoto;

    @BindView(R.id.uploded_photo_image)
    ImageView uploadedImage;

    private MainPresenter mainPresenter;
    private Uri uri;

    private final int REQUEST_GALLERY_CODE = 200;
    private String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE};

    private double userLatitude;
    private double userLongitude;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this); // Create Instance of MainPresenter

        int PERMISSION_ALL = 1;

        if (!CheckPermission.hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        sendPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri != null) {
                    mainPresenter.onUploadPhotoClicked();
                } else {
                    mainPresenter.showWarning();
                }
            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.onTakePhotoClicked();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            uri = data.getData();

            Picasso.with(MainActivity.this).load(data.getData()).noPlaceholder().centerCrop().fit()
                    .into(uploadedImage);
        }
    }


    @Override
    public void onShowPermisson() {

        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setType("image/*");
        startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
    } // Opens Gallery

    @Override
    public void giveLocationInfo(Double latitude, Double longitude, String city) {

        userLatitude = latitude;
        userLongitude = longitude;
        this.city = city;
    } // Set necessary parameters for Upload PhotoItem Call

    @Override
    public void sendRequestBody(String url) {

        mainPresenter.fetchLocationInfo(MainActivity.this); // Get Location Info from Presenter

        UploadPhotoItem requestBody = getRequestBody(url); // Creates requested body for upload photo call

        mainPresenter.postPhoto(requestBody);  //Sends required parameters to presenter for upload photo call
    }

    @NonNull
    private UploadPhotoItem getRequestBody(String url) {

        PhotoItem photoItem = new PhotoItem();

        photoItem.setName(city.toLowerCase());
        photoItem.setLocation(String.valueOf(userLatitude) + "," + String.valueOf(userLongitude));
        photoItem.setUrl(url);

        UploadPhotoItem uploadPhotoItem = new UploadPhotoItem();
        uploadPhotoItem.setPhoto(photoItem);

        return uploadPhotoItem;
    }

    @Override
    public void showToast() {
        Toast.makeText(MainActivity.this, "Take a photo first !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendFile() {

        File file = FileUtils.getFile(this, uri);

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(uri)),
                        file
                );

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        mainPresenter.postPhotoToUploadsIm(body);
    }
}
