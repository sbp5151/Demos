package com.boping.retrofit_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_load).setOnClickListener(view -> load());
    }
    private void load(){

        GetRequest_Interface getService = RetrofitManager.getInstance(this).getRetrofit().create(GetRequest_Interface.class);
        Call<ResponsebodyBean> call = getService.getCall();

        call.enqueue(new Callback<ResponsebodyBean>() {
            @Override
            public void onResponse(Call<ResponsebodyBean> call, Response<ResponsebodyBean> response) {
                Toast.makeText(MainActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: "+response.toString());
            }

            @Override
            public void onFailure(Call<ResponsebodyBean> call, Throwable t) {
                Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
