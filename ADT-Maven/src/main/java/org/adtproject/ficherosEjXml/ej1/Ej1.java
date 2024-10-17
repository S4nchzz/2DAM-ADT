package org.adtproject.ficherosEjXml.ej1;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ej1 {
    private static final String datFilePath = "./src/main/java/org/adtproject/ficherosEjXml/ej1/fichpersonaObj.dat";
    private static final String xmlFilePath = "./src/main/java/org/adtproject/ficherosEjXml/ej1/Personas.xml";

    public static void main(String[] args) {
        List<Persona> list = new ArrayList<>();
        list.add(new Persona("Iyan", "1", "1", 20));
        list.add(new Persona("Juan", "2", "2", 20));
        list.add(new Persona("Abril", "3", "3", 20));

        loadObjIntoDat(list);
        createXmlDomFile();
    }

    private static void loadObjIntoDat(List<Persona> personaList) {
        try {
            ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(new File((datFilePath))));

            for (Persona p : personaList) {
                objout.writeObject(p);
            }

            objout.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createXmlDomFile() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            DOMImplementation dom = builder.getDOMImplementation();
            Document doc = dom.createDocument(null, "Personas", null);

            List<Persona> personDataRead = readDatFile();

            Element raiz = doc.getDocumentElement();
            for (Persona per : personDataRead) {
                Node name = doc.createElement("nombre");
                name.appendChild(doc.createTextNode(per.getNombre()));

                raiz.appendChild(name);

                Node dni = doc.createElement("dni");
                dni.appendChild(doc.createTextNode(per.getDni()));

                raiz.appendChild(dni);

                Node telf = doc.createElement("telf");
                telf.appendChild(doc.createTextNode(per.getTelf()));

                raiz.appendChild(telf);

                Node edad = doc.createElement("edad");
                edad.appendChild(doc.createTextNode(String.valueOf(per.getEdad())));

                raiz.appendChild(edad);
            }

            Source source = new DOMSource(doc);
            Result resultXML = new StreamResult(new File(xmlFilePath));
            Result resultSout = new StreamResult(System.out);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultXML);
            transformer.transform(source, resultSout);

        } catch (Exception e) {

        }
    }

    private static List<Persona> readDatFile() {
        List<Persona> descriptedPersons = new ArrayList<>();
        try {
            ObjectInputStream objin = new ObjectInputStream(new FileInputStream(new File(datFilePath)));

            Object obj = null;
            while ((obj = objin.readObject()) != null) {
                descriptedPersons.add((Persona)obj);
            }

            return null; // No obj founded or error in read
        } catch (IOException | ClassNotFoundException e) {

        }

        return descriptedPersons;
    }

    private static void createDomElement(Element ele) {

    }
}
