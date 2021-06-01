package com.config;

import java.io.Serializable;

public class ConfigModel implements Serializable {
    public String url;
    public String user;
    public String password;

    public String getUrl() {
        return url;
    }
    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }


}
