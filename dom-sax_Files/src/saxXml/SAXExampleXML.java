package saxXml;

import java.io.IOException;

import javax.xml.parsers.*;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXExampleXML {
    public static void main(String[] args) {
        readDocument();
    }

    private static void readDocument() {
        
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = saxFactory.newSAXParser();
            XMLReader xml = parser.getXMLReader();
            
            DocumentGestor gestor = new DocumentGestor();

            xml.setContentHandler(gestor);
            InputSource input = new InputSource("./dom-sax_Files/src/empleados.xml");

            try {
                xml.parse(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (EmpleadoLabelData e : gestor.getElements()) {
                System.out.println(e);
            }
            

        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
}

