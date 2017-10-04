package com.example.msimsi.sendphoto.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.msimsi.sendphoto.R;
import com.example.msimsi.sendphoto.data.entity.PhotoItem;
import com.example.msimsi.sendphoto.data.entity.PreSignedPostItem;
import com.example.msimsi.sendphoto.data.entity.ResourceItem;
import com.example.msimsi.sendphoto.data.entity.UploadPhotoItem;
import com.example.msimsi.sendphoto.data.response.PreSignedUrlResponse;
import com.example.msimsi.sendphoto.utility.CheckPermission;
import com.example.msimsi.sendphoto.utility.FileUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

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
    private final int TAG_PERMISSION_CODE = 1;

    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private double userLatitude;
    private double userLongitude;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this); // Create Instance of MainPresenter

        if (!CheckPermission.checkPermission(MainActivity.this)) {
            CheckPermission.requestPermission(MainActivity.this, TAG_PERMISSION_CODE);
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
    public void sendResource() {

        ResourceItem resource = new ResourceItem();
        resource.setContentType("image/jpeg");

        PreSignedPostItem preSignedPostModel = new PreSignedPostItem(resource);

        Log.v("Resource Body: ", preSignedPostModel.toString());

        mainPresenter.getPreSignedUrl(preSignedPostModel);
    } // Send resource body for Pre-Signed Post

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
    } // Set necessary parameters for Upload Photo Call

    @Override
    public void sendPostParameters(Response<PreSignedUrlResponse> preSignedUrlResponse) {

        Map<String, String> headerMap = getStringMapHeaders(preSignedUrlResponse); // Set required headers for upload photo call

        File file = FileUtils.getFile(this, uri);

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(uri)),
                        file
                );

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        mainPresenter.fetchLocationInfo(MainActivity.this); // Get Location Info from Presenter

        RequestBody requestBody = getRequestBody(preSignedUrlResponse, file); // Creates requested body for upload photo call

        mainPresenter.postPhoto(headerMap, requestBody, body);  //Sends required parameters to presenter for upload photo call
    }

    @NonNull
    private Map<String, String> getStringMapHeaders(Response<PreSignedUrlResponse> preSignedUrlResponse) {

        Map<String, String> headerMap = new HashMap<>();

        headerMap.put("key", preSignedUrlResponse.body().getData().getKey());
        headerMap.put("policy", preSignedUrlResponse.body().getData().getPolicy());
        headerMap.put("x-amz-algorithm", preSignedUrlResponse.body().getData().getXAmzAlgorithm());
        headerMap.put("acl", preSignedUrlResponse.body().getData().getAcl());
        headerMap.put("success_action_status", preSignedUrlResponse.body().getData().getSuccessActionStatus());
        headerMap.put("x-amz-date", preSignedUrlResponse.body().getData().getXAmzDate());
        headerMap.put("x-amz-signature", preSignedUrlResponse.body().getData().getXAmzSignature());
        headerMap.put("x-amz-credential", preSignedUrlResponse.body().getData().getXAmzCredential());

        for (String key : headerMap.keySet()) {
            Log.e(key, headerMap.get(key));
        }

        return headerMap;
    }

    @NonNull
    private RequestBody getRequestBody(Response<PreSignedUrlResponse> preSignedUrlResponse, File file) {

        PhotoItem photoItem = new PhotoItem();

        photoItem.setName(city.toLowerCase());
        photoItem.setLocation(String.valueOf(userLatitude) + "," + String.valueOf(userLongitude));
        photoItem.setUrl(preSignedUrlResponse.body().getData().getUrl() + '/' + preSignedUrlResponse.body().getData().getKey().replace("${filename}", file.getName()));

        UploadPhotoItem uploadPhotoItem = new UploadPhotoItem();
        uploadPhotoItem.setPhoto(photoItem);

        String json = new Gson().toJson(uploadPhotoItem);
        return RequestBody.create(JSON, json);
    }

    @Override
    public void showToast() {
        Toast.makeText(MainActivity.this, "Take a photo first !", Toast.LENGTH_SHORT).show();
    }
}
