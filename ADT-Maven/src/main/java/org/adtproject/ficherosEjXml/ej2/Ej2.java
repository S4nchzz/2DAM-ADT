package org.adtproject.ficherosEjXml.ej2;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class Ej2 {
    public static void main(String[] args) {
        readDocument();
    }

    private static void readDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            XMLReader xml = parser.getXMLReader();

            DocumentGestor gestor = new DocumentGestor();

            xml.setContentHandler(gestor);
            InputSource input = new InputSource("./src/main/java/org/adtproject/ficherosEjXml/ej1/Personas.xml");

            try {
                xml.parse(input);
            } catch (IOException e) {

            }

        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
