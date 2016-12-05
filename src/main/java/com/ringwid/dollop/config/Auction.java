package com.ringwid.dollop.config;

import java.io.File;
import java.util.Map;

public class Auction extends Configuration {

    private Integer seconds;

    public Auction(String path) {
        if (new File(path).exists()) {
            valid = true;
        } else {
            return;
        }
        load(path);
        init();
    }

    private void init() {
        this.seconds = getConfigurationProvider().getProperty("seconds", Integer.class);
    }

}
