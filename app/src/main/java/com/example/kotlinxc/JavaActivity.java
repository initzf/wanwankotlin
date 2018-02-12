package com.example.kotlinxc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kotlinxc.bean.UserInfoEntity;
import com.example.kotlinxc.tools.ApiService;
import com.example.kotlinxc.tools.AppExecutors;
import com.example.kotlinxc.tools.ExecutorTool;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JavaActivity extends AppCompatActivity {
    private static final String TAG = "JavaActivity";

    ExecutorTool executorTool;

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        mTextView = findViewById(R.id.tvShowContent);

        String msg = getIntent().getStringExtra("msg");

        Toast.makeText(this, "接收到值:" + msg, Toast.LENGTH_SHORT).show();

        getData();

        executorTool = new ExecutorTool<UserInfoEntity>() {
            @Override
            public void onSyncResponse(UserInfoEntity userInfoEntity) {
                Log.i(TAG, "onResponseListener: " + userInfoEntity.getName() + "-----" + Thread.currentThread().getName());

                mTextView.setText(userInfoEntity.getName());
            }
        };
    }

    private void getData() {

        AppExecutors.getInstance().getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {
                String json = new Tools().getUserInfo();

                UserInfoEntity userInfoEntity = new Gson().fromJson(json, UserInfoEntity.class);

                executorTool.asyncResponse(userInfoEntity);
            }
        });
    }


    private void getData2() {
        Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit.baseUrl("https://api.douban.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


        Retrofit service = retrofit.build();

        ApiService mApiService = service.create(ApiService.class);

        mApiService.getUserInfo("admin").enqueue(new Callback<UserInfoEntity>() {
            @Override
            public void onResponse(Call<UserInfoEntity> call, Response<UserInfoEntity> response) {

                Log.i(TAG, "onResponse: " + Thread.currentThread().getName() + "-----" + response.body().getName());

            }

            @Override
            public void onFailure(Call<UserInfoEntity> call, Throwable t) {

            }
        });
    }


}
