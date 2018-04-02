package com.epam.lab.xml.parsers;

import com.epam.lab.xml.entity.*;
import com.epam.lab.xml.entity.Package;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MedParserSAX extends DefaultHandler implements MedParser {

    private String value;
    private List<Medicine> medicines = new ArrayList<>();
    private Medicine medicine = new Medicine();
    private Version version = new Version();
    private Form form = new Form();
    private Package pack = new Package();
    private Certificate certificate = new Certificate();
    private FormEnum formEnum;

    @Override
    public List<Medicine> parse(File xml) {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            MedParserSAX handler = new MedParserSAX();
            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);
            FileReader reader = new FileReader(xml);
            xr.parse(new InputSource(reader));

            medicines = handler.getMedicines();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return medicines;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("medication")){
            medicine = new Medicine();
        } else if (qName.equals("form")){
           formEnum = FormEnum.byValue(attributes.getValue(0));
        } else if (qName.equals("certificate")){
            certificate.setNumber(attributes.getValue(0));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //Because there are special bytecode that allow efficient switch statement evaluation when there are a lot of cases.
        //https://stackoverflow.com/questions/6705955/why-switch-is-faster-than-if
        switch (qName) {
            case "version":
                form.addVersion(version);
                version = new Version();
                break;
            case "form":
                form.setName(formEnum);
                medicine.addForm(form);
                form = new Form();
                break;
            case "medicine":
                medicine = new Medicine();
                break;
            case "name":
                medicine.setName(value);
                break;
            case "pharm":
                medicine.setPharm(value);
                break;
            case "group":
                medicine.setGroup(value);
                break;
            case "analog":
                medicine.addAnalog(value);
                break;
            case "certificate":
                version.setCertificate(certificate);
                certificate = new Certificate();
                break;
            case "dateOfIssue":
                certificate.setDateOfIssue(value);
                break;
            case "expDate":
                certificate.setExpDate(value);
                break;
            case "organisation":
                certificate.setOrganisation(value);
                break;
            case "type":
                pack.setType(value);
                break;
            case "quantity":
                pack.setQuantity(Integer.valueOf(value));
                break;
            case "price":
                pack.setPrice(Double.valueOf(value));
                break;
            case "package":
                version.setPack(pack);
                pack = new Package();
                break;
            case "dosage":
                version.setDosage(value);
                break;
            case "medication":
                medicines.add(medicine);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        value = String.copyValueOf(ch, start, length).trim();

    }

    public List<Medicine> getMedicines(){
        return medicines;
    }


}

