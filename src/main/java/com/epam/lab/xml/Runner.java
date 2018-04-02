package com.epam.lab.xml;

import com.epam.lab.xml.entity.Medicine;
import com.epam.lab.xml.entity.Medicines;
import com.epam.lab.xml.parsers.MedParser;
import com.epam.lab.xml.parsers.MedParserDOM;
import com.epam.lab.xml.parsers.MedParserSAX;
import com.epam.lab.xml.parsers.MedParserStAX;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.List;

public class Runner {
    public static void main(String[] args) {

        File marshaled = new File("xml/marshaled.xml");
        File xml = new File("xml/med.xml");
        File xsd = new File("xml/medValidation.xsd");
        File output = new File("xml/med.html");
        Source sourceXml = new StreamSource(xml);
        Source xslt = new StreamSource("xml/medToHtml.xsl");

        MedParser domParser = new MedParserDOM();
        MedParser staxParser = new MedParserStAX();
        MedParser saxParser = new MedParserSAX();

        List<Medicine> medicines1 = domParser.parse(xml);
        List<Medicine> medicines2 = staxParser.parse(xml);
        List<Medicine> medicines3 = saxParser.parse(xml);

        System.out.println((char) 27 + "[32m" + "Parsed by DOM" + (char) 27 + "[0m");
        for (Medicine medicine: medicines1){
            System.out.println(medicine);
        }
        System.out.println((char) 27 + "[32m" + "\nParsed by StAX" + (char) 27 + "[0m");
        for (Medicine medicine: medicines2){
            System.out.println(medicine);
        }
        System.out.println((char) 27 + "[32m" + "\nParsed by SAX" + (char) 27 + "[0m");

        for (Medicine medicine: medicines3){
            System.out.println(medicine);
        }
        Medicines medicines = new Medicines();
        medicines.setMedicines(medicines1);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Medicines.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(medicines, marshaled);
            System.out.println((char) 27 + "[32m" + "\nMarshaled by JAXB" + (char) 27 + "[0m");
            marshaller.marshal(medicines, System.out);

            System.out.println((char) 27 + "[32m" + "\nUnmarshaled by JAXB"+ (char) 27 + "[0m");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Medicines medicines4 = (Medicines) jaxbUnmarshaller.unmarshal(xml);

            for (Medicine medicine: medicines4.getMedicines()){
                System.out.println(medicine);
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        XmlToHtmlConverter.convertXMLToHTML(sourceXml, xslt, output);
        System.out.println(Validator.validateXMLByXSD(marshaled, xsd) ? (char) 27 + "[32m" + "Valid :)" + (char) 27 + "[0m" : "Not valid :C");
    }
}
