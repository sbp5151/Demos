package com.boping.mvp_demo;

/**
 * @author :boping
 * @date :2019-03-03 15:29
 * @Email :sbp5151@163.com
 */
public interface ILoginView {

    void showLoading();

    void hideLoading();

    String getName();

    String getPassword();

    void toMainActivity();

    void showFailedError(String errorMeg);
}
