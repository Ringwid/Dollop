package com.ringwid.dollop;

import com.ringwid.dollop.api.RemoteAPI;
import com.ringwid.dollop.config.DollopConfig;
import com.ringwid.dollop.utils.Utils;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project Dollop
 */
public class Dollop extends Thread {

    private Logger logger;
    private RemoteAPI remoteAPI;
    private LaunchArgument launchArgument;
    private long lastWorkingTime = System.currentTimeMillis();

    public Dollop(Logger logger, LaunchArgument launchArgument) {
        this.logger = logger;
        this.launchArgument = launchArgument;

        logger.info("Launching Dollop System...");

        switch (launchArgument.getDebugLevel()) {
            case 0:
                break;
            case 1:
                logger.setLevel(Level.FINE);
                break;
            case 2:
                logger.setLevel(Level.FINER);
                break;
            case 3:
                logger.setLevel(Level.FINEST);
                break;
            default:
                if (launchArgument.getDebugLevel() > 0) {
                    logger.setLevel(Level.ALL);
                }
                break;
        }

        logger.info("Log level set: " + logger.getLevel());

        try {
            URL url = new URL("http://www.ebay.com/");
            url.openConnection().connect();
        } catch (Exception e) {
            logger.severe("Unable to connect to Ebay: ");
            shutdown(true);
            e.printStackTrace();
        }

        logger.info("Reading config ad action files...");

        DollopConfig dollopConfig = new DollopConfig(launchArgument.getConfigFile() == null ? Utils.getJarDir(Dollop.class).getAbsolutePath() + "/config.yml" : launchArgument.getConfigFile());
        if (dollopConfig.getOnlineMode()) {
            logger.info("Enabling internet interface...");

            this.remoteAPI = new RemoteAPI(this);
        }
    }

    public void emergencyShutdown() {
        shutdown(true);
    }

    public void shutdown() {
        shutdown(false);
    }

    private void shutdown(boolean b) {
        if (b) {
            System.exit(0);
        } else {
            // TODO: 11/11/2016
        }
    }

    public long getLastWorkingTime() {
        return lastWorkingTime;
    }

}
