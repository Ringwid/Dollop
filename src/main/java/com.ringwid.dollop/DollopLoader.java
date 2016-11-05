package com.ringwid.dollop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Project Dollop
 */
public class DollopLoader {

    public final String INITIAL_COMMAND_USAGE = "";
    private final Logger logger;

    public DollopLoader(String[] args) {
        HashMap<String, List<String>> argMap = new LinkedHashMap<String, List<String>>();
        String previous = null;
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                if (previous == null) {
                    throw new IllegalArgumentException(INITIAL_COMMAND_USAGE);
                }
                argMap.get(previous).add(arg);
            } else {
                previous = arg;
                argMap.put(arg, new ArrayList<>());
            }
        }

        LaunchArgument launchArgument = new LaunchArgument();
        argMap.forEach((arg, subArg) -> {
            try {
                switch (arg) {
                    case "-debugLevel":
                        launchArgument.set("debugLevel", Integer.valueOf(subArg.get(0)));
                        break;
                    case "-ui":
                        launchArgument.set("ui", Boolean.valueOf(subArg.get(0)));
                        break;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INITIAL_COMMAND_USAGE + "(" + e.getMessage() + ")");
            }
        });

        this.logger = Logger.getLogger("Dollop/Main");

        Dollop dollop = new Dollop(logger, launchArgument);
        dollop.start();
        if (System.currentTimeMillis() - dollop.getLastWorkingTime() > 5000 && !dollop.isAlive()) {
            forceShutdown();
        }
    }

    private void forceShutdown() {
        logger.info("Force shutting down...");
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) {
        new DollopLoader(args);
    }

}
