package com.company;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        CreateCaseFile newFile = new CreateCaseFile();
        String FilePath = newFile.WriteDataToFile();



        //TODO Pack file
        //TODO Sending to Webdav
        //TODO B2B request - soap - create
        //TODO B2B getTask
        //TODO Cleaning files
    }
}
