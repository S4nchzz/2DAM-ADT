package org.adtproject.ficherosEjXml.ej3;

import org.adtproject.ficherosEjXml.ej1.Persona;

import javax.swing.text.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Ej3 {
    private static final String datFilePath = "./src/main/java/org/adtproject/ficherosEjXml/fichpersonaObj.dat";
    private static final String xmlFilePath = "./src/main/java/org/adtproject/ficherosEjXml/ej3/PersonasMarshall.xml";
    public static void main(String[] args) {
        ArrayList<org.adtproject.ficherosEjXml.ej3.Persona> personList = refactorDatFileToJAXB(readDatObjFile());
        Personas personas = new Personas(personList);

        marshall(personas);
        for (org.adtproject.ficherosEjXml.ej3.Persona p : unmarshall().getPersonList()) {
            System.out.println("Nombre: " + p.getNombre() + " | DNI: " + p.getDni() + "| Telf: " + p.getTelf());
        }
    }

    private static void marshall(Personas personas) {
        JAXBContext context = null;
        Marshaller marshaller = null;

        try {
            context = JAXBContext.newInstance(Personas.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,  Boolean.TRUE);
            marshaller.marshal(personas, new File(xmlFilePath));
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Personas unmarshall() {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Personas.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Personas) unmarshaller.unmarshal(new File(xmlFilePath));
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private static ArrayList<org.adtproject.ficherosEjXml.ej3.Persona> refactorDatFileToJAXB(ArrayList<Persona> personDatList) {
        ArrayList<org.adtproject.ficherosEjXml.ej3.Persona> personJAXBList = null;
        if (personDatList != null) {
            personJAXBList = new ArrayList<>();
            for (Persona p : personDatList) {
                personJAXBList.add(new org.adtproject.ficherosEjXml.ej3.Persona(p.getNombre(), p.getDni(), p.getTelf()));
            }
        }

        return personJAXBList;
    }

    private static ArrayList<org.adtproject.ficherosEjXml.ej1.Persona> readDatObjFile() {
        final ArrayList<org.adtproject.ficherosEjXml.ej1.Persona> personList = new ArrayList<>();
        ObjectInputStream objin = null;

        try {
            objin = new ObjectInputStream(new FileInputStream(new File(datFilePath)));

            Object obj = null;
            while ((obj = objin.readObject()) != null) {
                personList.add((org.adtproject.ficherosEjXml.ej1.Persona)obj);
            }

            return null; // No data founded or error on read
        } catch (IOException | ClassNotFoundException e) {
            try {objin.close();} catch (IOException ex) {}
            return personList;
        }
    }
}
