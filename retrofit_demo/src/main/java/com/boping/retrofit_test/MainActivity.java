package com.boping.retrofit_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.boping.retrofit_test.utilcode.util.AppUtils;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 简介：
 * https://www.jianshu.com/p/0fda3132cf98
 * ①：基于OkHttp封装的一个网络加载框架；
 * ②：超级解耦 https://www.jianshu.com/p/45cb536be2f4
 * ③：支持同步、异步、RxJava
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RetrofitApi retrofitApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_load).setOnClickListener(view -> getTest());
        retrofitApi = RetrofitManager.getInstance(this).getRetrofit().create(RetrofitApi.class);
    }

    private void getTest() {

        Call<ResponseBodyBean> call = retrofitApi.getVersions(Integer.toString(AppUtils.getAppVersionCode()));

        call.enqueue(new Callback<ResponseBodyBean>() {
            @Override
            public void onResponse(Call<ResponseBodyBean> call, Response<ResponseBodyBean> response) {
                Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: " + response.toString());
            }

            @Override
            public void onFailure(Call<ResponseBodyBean> call, Throwable t) {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postTest() {

        retrofitApi.postTest(new ReceptionBodyBean()).enqueue(new Callback<ResponseBodyBean>() {
            @Override
            public void onResponse(Call<ResponseBodyBean> call, Response<ResponseBodyBean> response) {

            }

            @Override
            public void onFailure(Call<ResponseBodyBean> call, Throwable t) {

            }
        });
    }

    private void fileDownload(String fileUrl) {

        Call<ResponseBody> call = retrofitApi.downloadFile(fileUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200)
                    new SaveFileAsyncTask(response.body()).execute();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private void uploadFile(String filePath) {

        File file = new File(filePath);
        RequestBody body = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("header", "filename", body);

        HashMap<String, String> map = new HashMap<>();
        map.put("parameter1", "1");
        map.put("parameter2", "2");

        retrofitApi.uploadFile(part, map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
