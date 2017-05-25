package com.company;

import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by pllstm on 25.05.2017.
 */
public class GlobalVariables {

    static String pathToProgram=Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceFirst("/","");;


    public GlobalVariables() {

    }


    public static String getPath() {

        return pathToProgram;
    }


}
