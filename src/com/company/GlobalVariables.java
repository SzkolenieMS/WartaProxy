package com.company;

import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by pllstm on 25.05.2017.
 */
public class GlobalVariables {

    private static Random random = new Random();
    private static int fileNumber = random.nextInt(899) + 1000;
    private static boolean ifAudaSmart = true;
    private static boolean addon = true;
    private static boolean newAplicationUse = true;

    public static boolean isAddon() {
        return addon;
    }

    public static boolean getNewAplicationUse() {
        return newAplicationUse;
    }

    public static boolean getIfAudaSmart() {
        return ifAudaSmart;
    }

    public static Random getRandom() {
        return random;
    }

    public static int getFileNumber() {
        return fileNumber;
    }

    public static void setPathToProgram(String pathToProgram) {
        GlobalVariables.pathToProgram = pathToProgram;
    }

    static String pathToProgram = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceFirst("/", "");
    ;


    public GlobalVariables() {

    }

    public static String getPathToProgram() {
        return pathToProgram;
    }

}
