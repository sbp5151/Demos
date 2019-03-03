package com.boping.retrofit_test;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


/**
 * 请求方法：
 *
 * @GET GET请求
 * @POST POST请求
 * @DELETE DELETE请求
 * @HEAD HEAD请求
 * @OPTIONS OPTIONS请求
 * @PATCH PATCH请求
 * <p>
 * 请求参数：
 * @Headers 添加请求头
 * @Path 替换路径
 * @Query 替代参数值，通常是结合get请求的
 * @FormUrlEncoded 用表单数据提交
 * @Field 替换参数值，是结合post请求的
 */
public interface RetrofitApi {


    public static final String GET_VERSION_CODE = "/download/checkvadmsg.php";

    // 1、GET请求，如果后面参数跟的是“？”，例如 download/checkvadmsg.php?t=1
    @GET(GET_VERSION_CODE)
    Call<ResponseBodyBean> getVersions(@Query("t") String versionName);

    //2、GET请求，如果需要替换{}，例如https:download/checkvadmsg.php/{t}
    @GET(GET_VERSION_CODE + "{t}")
    Call<ResponseBodyBean> getVersions2(@Path("t") String versionName);

    //3、Post请求
    @POST(GET_VERSION_CODE)
    Call<ResponseBodyBean> postTest(@Body ReceptionBodyBean body);

    //4、文件下载
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);

    // 5、文件上传
    @POST(GET_VERSION_CODE)
    @Multipart
    Call<ResponseBody> uploadFile(@Part MultipartBody.Part file, @PartMap Map<String, String> map);
}
