package com.ringwid.dollop.config;

import java.io.File;

public class DollopConfig extends Configuration {

    private String ebayUsername;
    private String ebayPassword;
    private Integer time;
    private Boolean onlineMode;

    public DollopConfig(String path) {
        if (new File(path).exists()) {
            valid = true;
        } else {
            return;
        }
        load(path);
        init();
    }

    private void init() {
        this.onlineMode = getConfigurationProvider().getProperty("onlineMode", Boolean.class);
        this.ebayUsername = getConfigurationProvider().getProperty("ebayUsername", "".getClass());
        this.ebayPassword = getConfigurationProvider().getProperty("ebayPassword", "".getClass());
        this.time = getConfigurationProvider().getProperty("seconds", Integer.class);
    }

    public String getEbayPassword() {
        return ebayPassword;
    }

    public String getEbayUsername() {
        return ebayUsername;
    }

    public Boolean getOnlineMode() {
        return onlineMode;
    }

    public Integer getTime() {
        return time;
    }

    public boolean isValid() {
        return valid;
    }
}
