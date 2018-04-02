package com.epam.lab.xml.parsers;

import com.epam.lab.xml.entity.*;
import com.epam.lab.xml.entity.Package;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedParserDOM implements MedParser {

    private List<Medicine> medList = new ArrayList<>();

    @Override
    public List<Medicine> parse(File xml) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xml);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element) {
                    Medicine medicine = new Medicine();
                    Element element = (Element) node;

                    medicine.setName(element.getElementsByTagName("name").item(0).getTextContent().trim());
                    medicine.setPharm(element.getElementsByTagName("pharm").item(0).getTextContent().trim());
                    medicine.setGroup(element.getElementsByTagName("group").item(0).getTextContent().trim());
                    NodeList analogs = element.getElementsByTagName("analogs");
                    medicine.setAnalogs(getAnalogs(analogs));
                    NodeList versions = element.getElementsByTagName("versions");
                    medicine.setForms(getForms(versions));
                    medList.add(medicine);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return medList;
    }

    private List<String> getAnalogs(NodeList analogs) {
        List<String> lAnalogs = new ArrayList<>();
        NodeList nAnalogs = analogs.item(0).getChildNodes();
        for (int i = 0; i < nAnalogs.getLength(); i++) {
            Node analog = nAnalogs.item(i);
            if (analog.getNodeType() == Node.ELEMENT_NODE)
                lAnalogs.add(analog.getTextContent().trim());
        }
        return lAnalogs;
    }

    private List<Form> getForms(NodeList versions) {
        List<Form> lForms = new ArrayList<>();
        for (int i = 0; i < versions.getLength(); i++) {
            Node nVers = versions.item(i);
            if (nVers.getNodeType() == Node.ELEMENT_NODE) {
                Element eVers = (Element) nVers;
                NodeList forms = eVers.getElementsByTagName("form");
                for (int j = 0; j < forms.getLength(); j++) {
                    Node nForm = forms.item(j);
                    Element eForm = (Element) nForm;
                    Form form = new Form();
                    FormEnum formEnum = FormEnum.byValue(eForm.getAttribute("name").trim());
                    form.setName(formEnum);
                    NodeList vers = nForm.getChildNodes();
                    form.setVersions(getVersions(vers));
                    lForms.add(form);
                }
            }
        }
        return lForms;
    }

    private List<Version> getVersions(NodeList vers) {
        List<Version> lVers = new ArrayList<>();
        for (int k = 0; k < vers.getLength(); k++) {
            Node nVer = vers.item(k);
            if (nVer.getNodeType() == Node.ELEMENT_NODE) {
                Element eVer = (Element) nVer;
                Version version = new Version();
                version.setDosage(eVer.getElementsByTagName("dosage").item(0).getTextContent().trim());
                Element cert = (Element) eVer.getElementsByTagName("certificate").item(0);
                Certificate certificate = getCertificate(cert);
                version.setCertificate(certificate);
                Element pack = (Element) eVer.getElementsByTagName("package").item(0);
                Package aPackage = getPackage(pack);
                version.setPack(aPackage);
                lVers.add(version);
            }
        }
        return lVers;
    }

    private Certificate getCertificate(Element certificate) {
        Certificate cert = new Certificate();
        cert.setNumber(certificate.getAttribute("number").trim());
        cert.setDateOfIssue(certificate.getElementsByTagName("dateOfIssue").item(0).getTextContent().trim());
        cert.setExpDate(certificate.getElementsByTagName("expDate").item(0).getTextContent().trim());
        cert.setOrganisation(certificate.getElementsByTagName("organisation").item(0).getTextContent().trim());
        return cert;
    }

    private Package getPackage(Element pack) {
        Package aPackage = new Package();
        aPackage.setType(pack.getElementsByTagName("type").item(0).getTextContent().trim());
        aPackage.setQuantity(Integer.valueOf(pack.getElementsByTagName("quantity").item(0).getTextContent()));
        aPackage.setPrice(Double.valueOf(pack.getElementsByTagName("price").item(0).getTextContent()));
        return aPackage;
    }
}