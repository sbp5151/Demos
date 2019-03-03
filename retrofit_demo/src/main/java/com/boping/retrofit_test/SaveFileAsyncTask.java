package com.boping.retrofit_test;

import android.os.AsyncTask;

import com.boping.retrofit_test.utilcode.util.FileUtils;
import com.boping.retrofit_test.utilcode.util.LogUtils;
import com.boping.retrofit_test.utilcode.util.PathUtils;
import com.boping.retrofit_test.utilcode.util.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * @author :boping
 * @date :2019-03-03 00:24
 * @Email :sbp5151@163.com
 */
public class SaveFileAsyncTask extends AsyncTask<Void, Integer, Boolean> {


    ResponseBody responseBody;

    public SaveFileAsyncTask(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        LogUtils.d("文件开始存储...");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        LogUtils.d("下载进度：" + values);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return saveToDisk();
    }

    @Override
    protected void onPostExecute(Boolean isSucceed) {

        if (isSucceed)
            ToastUtils.showShort("下载成功");
        else
            ToastUtils.showShort("下载失败");
        super.onPostExecute(isSucceed);
    }

    private boolean saveToDisk() {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = responseBody.byteStream();
            File file = new File(PathUtils.getExternalStoragePath() + File.separator + "myFile" + File.separator + System.currentTimeMillis() + "_lbj.jpg");
            FileUtils.createOrExistsFile(file);
            fos = new FileOutputStream(file);
            long all = responseBody.contentLength();
            byte[] buf = new byte[2048];
            int len;
            long sum = 0;

            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                sum += len;
                int progress = (int) (sum / all * 100);
                onProgressUpdate(progress);
            }
            fos.flush();
            return true;
        } catch (IOException e) {
            LogUtils.e(e.toString());
            return false;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
