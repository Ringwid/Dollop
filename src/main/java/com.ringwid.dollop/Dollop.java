package com.ringwid.dollop;

import java.util.logging.Logger;

/**
 * Project Dollop
 */
public class Dollop extends Thread {

    private final Logger logger;
    private LaunchArgument launchArgument;
    private long lastWorkingTime = System.currentTimeMillis();

    public Dollop(Logger logger, LaunchArgument launchArgument) {
        this.logger = logger;
        this.launchArgument = launchArgument;
    }

    public long getLastWorkingTime() {
        return lastWorkingTime;
    }

}
