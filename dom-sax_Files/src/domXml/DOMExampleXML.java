package domXml;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.Scanner;

public class DOMExampleXML {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null); // Nodo padre

            createFile(document);
            loadFile(builder);
        } catch (ParserConfigurationException e) {

        }

    }
    
    private static void createFile(Document document) {
        document.setXmlVersion("1.0");
        Element ele = document.createElement("empleado"); // nodo hijo del padre
        document.getDocumentElement().appendChild(ele);

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Apellido: ");
        final String nodeApellido = sc.nextLine();

        System.out.println("Numero del empleado: ");
        final String nodeNumeroEmpleado = sc.nextLine();

        System.out.println("Salario: ");
        final String nodeSalario = sc.nextLine();

        createElement("apellido", nodeApellido, ele, document);
        createElement("nEmpleado", nodeNumeroEmpleado, ele, document);
        createElement("salario", nodeSalario, ele, document);
        
        sc.close();

        Source source = new DOMSource(document);
        Result resultXML = new StreamResult(new File("./dom-sax_Files/src/empleados.xml"));
        Result resultSOUT = new StreamResult(System.out);

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultXML);
            transformer.transform(source, resultSOUT);
        } catch (TransformerException e) {
            
        }
    }
    
    private static void createElement(final String datoEmpleado, final String valor, Element raiz, Document document) {
        Element element = document.createElement(datoEmpleado);
        raiz.appendChild(element);
        Text texto = document.createTextNode(valor);
        element.appendChild(texto);
        
        
    }

    private static void loadFile(DocumentBuilder builder) throws SAXException, IOException {
        Document document = builder.parse("./src/domXml/empleados.xml");
        
        System.out.println(document.getDocumentElement().getNodeName());
        NodeList nodelist = document.getElementsByTagName("empleado");
        
        for (int i = 0; i < nodelist.getLength(); i++) {
            Node empleado = nodelist.item(i);
            if (Node.ELEMENT_NODE == empleado.getNodeType()) {
                Element ele = (Element)empleado;
                System.out.println("nEmpleado: " + ele.getElementsByTagName("nEmpleado").item(0).getTextContent());
                System.out.println("apellido: " + ele.getElementsByTagName("apellido").item(0).getTextContent());
                System.out.println("salario: " + ele.getElementsByTagName("salario").item(0).getTextContent());
            }
        }
    }
}
