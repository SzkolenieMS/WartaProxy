package com.company;


import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by pllstm on 25.05.2017.
 */
public class CreateCaseFile {
    private String fileLocation;

    public CreateCaseFile() {
        fileLocation = GlobalVariables.getPath() + "Base\\" + "case_to_upload.xml";
    }

    public Document ReadDataFromFile() {
        Document doc = null;
        try {

            File fXmlFile = new File(fileLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;

    }

    public String WriteDataToFile()  {
        String outputFileLocation = "";




        return outputFileLocation;
    }


}
