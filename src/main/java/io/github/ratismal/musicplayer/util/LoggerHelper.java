package io.github.ratismal.musicplayer.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class LoggerHelper {

    public static void log(Level logLevel, Object object) {
        Logger.getLogger("mp").log(logLevel, object.toString());
    }

    public static void all(Object object) {
        log(Level.ALL, object);
    }

    public static void fatal(Object object) {
        log(Level.SEVERE, object);
    }

    public static void info(Object object) {
        log(Level.INFO, object);
    }

    public static void off(Object object) {
        log(Level.OFF, object);
    }

    public static void trace(Object object) {
        log(Level.FINE, object);
    }

    public static void warn(Object object) {
        log(Level.WARNING, object);
    }


}
