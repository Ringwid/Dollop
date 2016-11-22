package com.ringwid.dollop.config;

public class DollopConfig extends Configuration {

    private String ebayUsername;
    private String ebayPassword;
    private Integer time;
    private Boolean onlineMode;

    public DollopConfig(String path) {
        load(path);
        init();
    }

    private void init() {
        this.onlineMode = getConfigurationProvider().getProperty("onlineMode", Boolean.class);
        this.ebayUsername = getConfigurationProvider().getProperty("ebayUsername", "".getClass());
        this.ebayPassword = getConfigurationProvider().getProperty("ebayPassword", "".getClass());
        this.time = getConfigurationProvider().getProperty("seconds", Integer.class);
    }

    public String getEbayUsername() {
        return ebayUsername;
    }

    public String getEbayPassword() {
        return ebayPassword;
    }

    public Integer getTime() {
        return time;
    }

    public Boolean getOnlineMode() {
        return onlineMode;
    }
}
