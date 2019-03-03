package com.boping.mvp_demo;

import android.content.Context;
import android.os.Handler;


/**
 * @author :boping
 * @date :2019-03-03 15:54
 * @Email :sbp5151@163.com
 */
public class LoginMode {



    public LoginMode(Context context) {

    }

    public void login(final LoginBean loginBean, final ILoginListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(loginBean.getName().equals("boping")&&loginBean.getPassword().equals("12345"))
                    listener.loginSucceed();
                else listener.loginFailed("用户名或者密码错误");
            }
        }).start();
    }
}
