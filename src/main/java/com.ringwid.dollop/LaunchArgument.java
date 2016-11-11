package com.ringwid.dollop;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project Dollop
 */
public class LaunchArgument {

    private int debugLevel = 0;
    private boolean userInterface = true;
    private String configFile;
    private List<String> auctionFiles;

    public LaunchArgument set(String field, Object object) {
        try {
            Field fieldInstance = getClass().getDeclaredField(field);
            fieldInstance.setAccessible(true);
            fieldInstance.set(this, object);
        } catch (Exception ignored) {
            throw new IllegalArgumentException("Option " + field + " can not be set to " + object.getClass());
        }

        return this;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public List<String> getAuctionFiles() {
        return auctionFiles;
    }

    public void setAuctionFiles(List<String> auctionFiles) {
        this.auctionFiles = auctionFiles;
    }

    public int getDebugLevel() {
        return debugLevel;
    }

    public void setDebugLevel(int debugLevel) {
        this.debugLevel = debugLevel;
    }

    public boolean isUserInterface() {
        return userInterface;
    }

    public void setUserInterface(boolean userInterface) {
        this.userInterface = userInterface;
    }
}
