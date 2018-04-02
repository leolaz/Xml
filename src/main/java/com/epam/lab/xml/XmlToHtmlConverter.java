package com.epam.lab.xml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class XmlToHtmlConverter {

    public static void convertXMLToHTML(Source xml, Source xslt, File outputHtml) {
        StringWriter sw = new StringWriter();

        try {
            FileWriter fw = new FileWriter(outputHtml);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(xslt);
            transformer.transform(xml, new StreamResult(sw));
            fw.write(sw.toString());
            fw.close();

            System.out.println((char) 27 + "[32m" + "Converted to Html!:\n" + (char) 27 + "[0m" + outputHtml.getPath());

        } catch (IOException | TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        }
    }
}