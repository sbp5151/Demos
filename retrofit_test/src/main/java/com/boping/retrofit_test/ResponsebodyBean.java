package com.boping.retrofit_test;

public class ResponsebodyBean {

    private String version;
    private String url;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ResponsebodyBean{" +
                "version='" + version + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
