package com.company;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by pllstm on 25.05.2017.
 * Creating case from the template
 */
public class CreateCaseFile {
    private String caseToUploadBaseFileLocation;
    private String caseToUploadfileLocation;
    private Document readedFile = null;
    String todaysDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());


    public CreateCaseFile() {
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

    private Document PrepareDataToWrite() {
        readedFile = ReadDataFromFile();
        PrepareHeader();
        PrepareDaneSzkody();
        PrepareDanePojazdu();
        PrepareDaneWyliczeniowe();
        return readedFile;
    }

    private void PrepareHeader() {
        SetAplication();
        SetUserLogin();
    }

    private void PrepareDaneSzkody() {
        SetIdZdarzenia();
        SetIdSzkody();
        SetNumerZdarzenia();
        SetIdZadania();
        SetNumberSzkody();
        SetNazwaZadania();
        SetDataZdarzenia();
        SetAplikacjaMobilna();
        SetNumerTelefonu();
        SetEmail();
        SetPolicy();
    }

    private void PrepareDanePojazdu() {
        SetVinNumber();
        SetFirstRegistrationDate();
    }

    private void PrepareDaneWyliczeniowe() {
        SetVAT();
        SetWAR();
    }

    void WriteDataToFile() {
        Document preparedData = this.PrepareDataToWrite();
        Source input = new DOMSource(preparedData);
        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(caseToUploadfileLocation));
            transformer.transform(input, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SetAplication() {
        SetTextOfElementByTag("aplikacja", GlobalVariables.getAplicationType());
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
        SetTextOfElementByTag("numer_zdarzenia", "W" + todaysDate + GlobalVariables.getFileNumber());
    }

    private void SetNumberSzkody() {
        SetTextOfElementByTag("numer_szkody", "W" + todaysDate + GlobalVariables.getFileNumber() + "-01");
    }

    private void SetIdZadania() {
        int firstRandom = GlobalVariables.getRandom().nextInt(899) + 1000;
        SetTextOfElementByTag("id_zadania", String.valueOf(firstRandom));

    }

    private void SetNazwaZadania() {
        if (GlobalVariables.isAddon()) {
            SetTextOfElementByTag("nazwa_zadania", "Warsztat - ogledziny dodatkowe");
        } else {
            SetTextOfElementByTag("nazwa_zadania", "Ogledziny pojazdu");
        }
    }

    private void SetDataZdarzenia() {
        SetTextOfElementByTag("data_zdarzenia", GlobalVariables.getAccidentDate());

    }

    private void SetAplikacjaMobilna() {
        if (GlobalVariables.isNewAplicationUse()) {
            SetTextOfElementByTag("czy_aplikacja_mobilna", "true");
        } else {
            removeNodeBaseOnParent("dane_szkody", "czy_aplikacja_mobilna");
        }
    }

    private void SetNumerTelefonu() {
        if (GlobalVariables.isIfAudaSmart()) {
            SetTextOfElementByTag("audasmart_numer_telefonu", "+7777");
            removeNodeBaseOnParent("dane_szkody", "numer_telefonu");
        } else {
            SetTextOfElementByTag("numer_telefonu", "+7777");
            removeNodeBaseOnParent("dane_szkody", "audasmart_numer_telefonu");
        }
    }

    private void SetEmail() {
        if (GlobalVariables.isIfAudaSmart()) {
            SetTextOfElementByTag("audasmart_adres_email", "maciej.ppp@o2.pl");
            removeNodeBaseOnParent("dane_szkody", "adres_email");
        } else {
            SetTextOfElementByTag("adres_email", "maciej.ppp@o2.pl");
            removeNodeBaseOnParent("dane_szkody", "audasmart_adres_email");
        }
    }

    private void SetPolicy() {
        SetTextOfElementByTag("rodzaj_polisy", GlobalVariables.getPolicyToFile());
    }


    private void SetVinNumber() {
        SetTextOfElementByTag("nr_VIN", GlobalVariables.getVIN());
    }


    private void SetFirstRegistrationDate() {
        SetTextOfElementByTag("data_pierw_rejestr", GlobalVariables.getFirstRegistrationDate());
    }


    private void SetVAT() {
        NodeList nList = readedFile.getElementsByTagName("rodz_kalkulacji");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            eElement.getElementsByTagName("VAT").item(0).setTextContent(GlobalVariables.getVAT());
        }
        getTextContentAndPrint("VAT");
    }

    private void SetWAR() {
        NodeList nList = readedFile.getElementsByTagName("rodz_kalkulacji");
        Node nNode = nList.item(1);
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            eElement.getElementsByTagName("amortyzacja_czesci").item(0).setTextContent(String.valueOf(GlobalVariables.getAmortyzacja()));
            System.out.println("Aktualna amortyzacja_czesci : " + eElement.getElementsByTagName("amortyzacja_czesci").item(0).getTextContent());
        }
    }


    private void SetTextOfElementByTag(String elementName, String contentToSet) {
        readedFile.getElementsByTagName(elementName).item(0).setTextContent(contentToSet);
        getTextContentAndPrint(elementName);
    }

    private void getTextContentAndPrint(String elementName) {
        System.out.println("Aktualna " + elementName + " : " + readedFile.getElementsByTagName(elementName).item(0).getTextContent());
    }

    private void removeNodeBaseOnParent(String parent, String nodeName) {
        System.out.println("--------Skasowano node " + nodeName);
        readedFile.getElementsByTagName(parent).item(0).removeChild(readedFile.getElementsByTagName(nodeName).item(0));

    }

}
