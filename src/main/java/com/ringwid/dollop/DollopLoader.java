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
                switch (arg.toLowerCase()) {
                    case "-debuglevel":
                        launchArgument.setDebugLevel(Integer.valueOf(subArg.get(0)));
                        break;
                    case "-ui":
                        launchArgument.setUserInterface(Boolean.valueOf(subArg.get(0)));
                        break;
                    case "-configpath":
                        launchArgument.setConfigFile(subArg.get(0));
                        break;
                    case "-autionpath":
                        launchArgument.setAuctionFileFolder(subArg.get(0));
                    case "-auction":
                        launchArgument.setAuctionFiles(subArg);
                        break;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INITIAL_COMMAND_USAGE + "(" + e.getMessage() + ")");
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Exception ignored) {

            }
        });

        this.logger = Logger.getLogger("com.ringwid.dollop.Dollop");

        Dollop dollop = new Dollop(logger, launchArgument);
        dollop.start();
        if (System.currentTimeMillis() - dollop.getLastWorkingTime() > 5000 && !dollop.isAlive()) {
            forceShutdown();
        }
    }

    private void forceShutdown() {
        logger.info("Force shutting down...");
        Thread.getAllStackTraces().keySet().forEach(Thread::interrupt);
    }

    public static void main(String[] args) {
        new DollopLoader(args);
    }

}
