package com.epam.lab.xml.parsers;

import com.epam.lab.xml.entity.*;
import com.epam.lab.xml.entity.Package;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class MedParserStAX implements MedParser{

    @Override
    public List<Medicine> parse(File xml) {

        List<Medicine> medicines = new ArrayList<>();
        Medicine medicine = new Medicine();
        Version version = new Version();
        Certificate certificate = new Certificate();
        Form form = new Form();
        Package pack = new Package();
        String value = null;
        FormEnum formEnum = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader;
        try {
            reader = factory.createXMLStreamReader(Files.newInputStream(xml.toPath()));
            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if ("medication".equals(reader.getLocalName())) {
                            medicine = new Medicine();
                        }
                        if ("form".equals(reader.getLocalName())) {
                            formEnum = FormEnum.byValue(reader.getAttributeValue(0));
                        }
                        if ("certificate".equals(reader.getLocalName())) {
                            certificate.setNumber(reader.getAttributeValue(0));
                        }

                        break;

                    case XMLStreamConstants.CHARACTERS:
                        value = reader.getText().trim();
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
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
                        break;
                }
            }
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
        return medicines;
    }

}