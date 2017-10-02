package com.example.msimsi.sendphoto.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.msimsi.sendphoto.R;
import com.example.msimsi.sendphoto.SendPhotoApi;
import com.example.msimsi.sendphoto.SendPhotoApplication;
import com.example.msimsi.sendphoto.data.entity.PreSignedPostItem;
import com.example.msimsi.sendphoto.data.entity.ResourceItem;
import com.example.msimsi.sendphoto.data.response.PreSignedUrlResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.main_take_photo_button)
    Button takePhoto;

    @BindView(R.id.main_send_photo_button)
    Button sendPhoto;

    @BindView(R.id.uploded_photo_image)
    ImageView uploadedImage;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);

        sendPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.onUploadPhotoClicked();
            }
        });

        ResourceItem resource = new ResourceItem();
        resource.setContentType("image/jpeg");

        PreSignedPostItem preSignedPostModel = new PreSignedPostItem(resource);

        sendNetWorkRequest(preSignedPostModel);

    }

    private void sendNetWorkRequest(PreSignedPostItem preSignedPostItem) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(SendPhotoApplication.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        SendPhotoApi photoUploadClient = retrofit.create(SendPhotoApi.class);
        Call<PreSignedPostItem> call = photoUploadClient.getPreSignedUrl(preSignedPostItem);

        call.enqueue(new Callback<PreSignedPostItem>() {
            @Override
            public void onResponse(Call<PreSignedPostItem> call, Response<PreSignedPostItem> response) {

                PreSignedUrlResponse preSignedUrlResponse = new PreSignedUrlResponse();
                preSignedUrlResponse.setData(response.body().getData());

                Log.e("key", preSignedUrlResponse.getData().getKey());
            }

            @Override
            public void onFailure(Call<PreSignedPostItem> call, Throwable t) {
                Log.e("token", t.toString());
            }
        });
    }
}
