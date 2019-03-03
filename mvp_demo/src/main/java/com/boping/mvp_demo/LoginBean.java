package com.boping.mvp_demo;

/**
 * @author :boping
 * @date :2019-03-03 15:56
 * @Email :sbp5151@163.com
 */
public class LoginBean {

    private String name;
    private String password;

    public LoginBean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
