package com.company;


import java.util.Random;

/**
 * Created by pllstm on 25.05.2017.
 *
 */
public class GlobalVariables {

    private static String pathToProgram = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceFirst("/", "");
    private static Random random = new Random();
    private static int fileNumber = random.nextInt(899) + 1000;
    private static boolean ifAudaSmart = false;
    private static boolean addon = false;
    private static boolean newAplicationUse = false;
    private static String aplicationType = "Audatex"; //"audasmart"
    private static String accidentDate = "2015-11-01";
    private static String policyToFile = "ACK";
    private static String VIN = "VNKKL983X0A103379";
    private static String VAT = "TAK";
    private static String firstRegistrationDate = "2017-11-01";
    private static double amortyzacja = 11.22;

    public static String getVIN() {
        return VIN;
    }

    public static double getAmortyzacja(){
        return amortyzacja;

    }

    public static String getAplicationType() {
        return aplicationType;
    }

    public static String getVAT() {
        return VAT;
    }

    public static String getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

     static String getPolicyToFile() {
        return policyToFile;
    }

     static String getAccidentDate() {
        return accidentDate;
    }


     static boolean isIfAudaSmart() {
        return ifAudaSmart;
    }

     static boolean isNewAplicationUse() {
        return newAplicationUse;
    }

     static boolean isAddon() {
        return addon;
    }


     static Random getRandom() {
        return random;
    }

     static int getFileNumber() {
        return fileNumber;
    }

//    public static void setPathToProgram(String pathToProgram) {
//        GlobalVariables.pathToProgram = pathToProgram;
//    }



    public GlobalVariables() {

    }

     static String getPathToProgram() {
        return pathToProgram;
    }

}
