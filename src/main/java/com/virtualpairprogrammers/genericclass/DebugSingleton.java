package com.virtualpairprogrammers.genericclass;

//import com.virtualpairprogrammers.servlets.GestoreEccezioni;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;


public class DebugSingleton {

    private static DebugSingleton debugSinglton = null;
    private boolean isDebugginMode;

    // println text colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // println background colors
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public boolean isDebugginMode() {
        return isDebugginMode;
    }

    public void setDebugginMode(boolean debugginMode) {
        isDebugginMode = debugginMode;
    }


    private DebugSingleton() {
        isDebugginMode = false;
    }

    public static DebugSingleton getInstance() {
        if (debugSinglton == null) {
            debugSinglton = new DebugSingleton();
        }
        return debugSinglton;
    }

    public void println(String str)
    {
        if(isDebugginMode)
            System.out.println(ANSI_PURPLE + str + ANSI_RESET);
    }

}