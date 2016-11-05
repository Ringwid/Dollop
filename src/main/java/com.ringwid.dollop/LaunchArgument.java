package com.ringwid.dollop;

import java.lang.reflect.Field;

/**
 * Project Dollop
 */
public class LaunchArgument {

    private int debugLevel = 0;
    private boolean userInterface = true;

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

}
