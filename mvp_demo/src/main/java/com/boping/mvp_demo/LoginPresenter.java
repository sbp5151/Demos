package com.boping.mvp_demo;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

/**
 * @author :boping
 * @date :2019-03-03 15:55
 * @Email :sbp5151@163.com
 */
public class LoginPresenter {

    private ILoginView mILoginView;
    private final LoginMode loginMode;
    private final Handler handler;

    public LoginPresenter(Context context, ILoginView mILoginView) {
        this.mILoginView = mILoginView;
        loginMode = new LoginMode(context);
        handler = new Handler();
    }

    public void login(){

        if(TextUtils.isEmpty(mILoginView.getName())){
            mILoginView.showFailedError("用户名不能为空");
            return;
        }
        if(TextUtils.isEmpty(mILoginView.getPassword())){
            mILoginView.showFailedError("密码不能为空");
            return;
        }
        mILoginView.showLoading();
        loginMode.login(new LoginBean(mILoginView.getName(), mILoginView.getPassword()), new ILoginListener() {
            @Override
            public void loginSucceed() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mILoginView.hideLoading();
                        mILoginView.toMainActivity();
                    }
                });
            }

            @Override
            public void loginFailed(final String errorMsg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mILoginView.hideLoading();
                        mILoginView.showFailedError(errorMsg);
                    }
                });
            }
        });
    }
}
