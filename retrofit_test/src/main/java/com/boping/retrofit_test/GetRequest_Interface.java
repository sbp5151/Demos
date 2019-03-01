package com.boping.retrofit_test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetRequest_Interface {

    public static final String GET_VERSION_CODE = "/download/checkvadmsg.php?t=1";
    @GET("GET_VERSION_CODE")
    Call<ResponsebodyBean> getCall();
}
