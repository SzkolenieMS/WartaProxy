package com.company;


import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by pllstm on 25.05.2017.
 */
public class CreateCaseFile {
    private String caseToUploadBaseFileLocation;
    private String caseToUploadfileLocation;
    private int aplikacja = 1;
    private Document readedFile = null;
    String todaysDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());


    private CreateCaseFile() {
        caseToUploadBaseFileLocation = GlobalVariables.getPathToProgram() + "Base\\" + "case_to_upload.xml";
        caseToUploadfileLocation = GlobalVariables.getPathToProgram() + "Example\\" + "case_to_upload.xml";
//        this.PrepareDataToWrite();

    }

    private Document ReadDataFromFile() {
        Document doc = null;
        try {
            File fXmlFile = new File(caseToUploadBaseFileLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;

    }

    private Document PrepareDataToWrite(){

        readedFile = ReadDataFromFile();
        SetAplication();
        SetUserLogin();
        SetIdZdarzenia();
        SetIdSzkody();
        SetNumerZdarzenia();
        SetIdZadania();
        SetNumberSzkody();
        SetAplikacjaMobilna();
        SetNumerTelefonu();
        SetEmail();
        SetNazwaZadania();

        return readedFile;

    }
    void WriteDataToFile() {
        Document preparedData = this.PrepareDataToWrite();
        Source input = new DOMSource(preparedData);
        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(caseToUploadfileLocation));
            transformer.transform(input, output);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }




    private void SetAplication() {
        if (aplikacja == 1) {
            SetTextOfElementByTag("aplikacja", "Audatex");
        } else {
            SetTextOfElementByTag("aplikacja", "audasmart");
        }
    }
    private void SetUserLogin() {
        SetTextOfElementByTag("user_login", "JA14649");
    }

    private void SetIdZdarzenia() {
        int firstRandom = GlobalVariables.getRandom().nextInt(899999) + 100000;
        SetTextOfElementByTag("id_zdarzenia", String.valueOf(firstRandom));
    }

    private void SetIdSzkody() {
        int firstRandom = GlobalVariables.getRandom().nextInt(899999) + 100000;
        SetTextOfElementByTag("id_szkody", String.valueOf(firstRandom));

    }

    private void SetNumerZdarzenia() {
        SetTextOfElementByTag("numer_zdarzenia", "W"+todaysDate+GlobalVariables.getFileNumber());
    }

    private void SetNumberSzkody() {
        SetTextOfElementByTag("numer_szkody", "W"+todaysDate+GlobalVariables.getFileNumber()+"-01");
    }

    private void SetIdZadania(){
        int firstRandom = GlobalVariables.getRandom().nextInt(899) + 1000;
        SetTextOfElementByTag("id_zadania", String.valueOf(firstRandom));

    }

    private void SetNazwaZadania(){
        if (GlobalVariables.isAddon()) {
            SetTextOfElementByTag("nazwa_zadania", "Warsztat - ogledziny dodatkowe");
        }else {
            SetTextOfElementByTag("nazwa_zadania", "Ogledziny pojazdu");
        }
    }

    private void SetAplikacjaMobilna(){
        if (GlobalVariables.getNewAplicationUse()) {
            SetTextOfElementByTag("czy_aplikacja_mobilna", "true");
        } else {
            removeNodeBaseOnParent("dane_szkody", "czy_aplikacja_mobilna");
        }
    }

    private void SetNumerTelefonu(){
        if (GlobalVariables.getIfAudaSmart()) {
            SetTextOfElementByTag("audasmart_numer_telefonu", "+7777");
            removeNodeBaseOnParent("dane_szkody", "numer_telefonu");
        } else {
            SetTextOfElementByTag("numer_telefonu", "+7777");
            removeNodeBaseOnParent("dane_szkody", "audasmart_numer_telefonu");
        }
    }
    private void SetEmail(){
        if (GlobalVariables.getIfAudaSmart()) {
            SetTextOfElementByTag("audasmart_adres_email", "maciej.ppp@o2.pl");
            removeNodeBaseOnParent("dane_szkody", "adres_email");
        } else {
            SetTextOfElementByTag("adres_email", "maciej.ppp@o2.pl");
            removeNodeBaseOnParent("dane_szkody", "audasmart_adres_email");
        }
    }


    private  void SetTextOfElementByTag(String elementName, String contentToSet) {
        readedFile.getElementsByTagName(elementName).item(0).setTextContent(contentToSet);
        getTextContentAndPrint(elementName);
    }

    private  void getTextContentAndPrint(String elementName) {
        System.out.println("Aktualna " + elementName + " : " + readedFile.getElementsByTagName(elementName).item(0).getTextContent());
    }

    private  void removeNodeBaseOnParent(String parent, String nodeName){

        readedFile.getElementsByTagName(parent).item(0).removeChild(readedFile.getElementsByTagName(nodeName).item(0));

    }

}
