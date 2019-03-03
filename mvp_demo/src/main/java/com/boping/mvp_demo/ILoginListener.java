package com.boping.mvp_demo;

/**
 * @author :boping
 * @date :2019-03-03 16:00
 * @Email :sbp5151@163.com
 */
public interface ILoginListener {

    void loginSucceed();
    void loginFailed(String errorMsg);
}
