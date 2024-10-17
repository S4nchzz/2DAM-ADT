package org.adtproject.libreria;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JAXBContext context;

        ArrayList<Libro> libros = new ArrayList<>();
        libros.add(new Libro("Iyan", "Iyan S.A", "01002"));
        Libreria lib = new Libreria(libros, "Libreria baroja", "Algun sitio");

        try {
            context = JAXBContext.newInstance(Libreria.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(lib, System.out);
            m.marshal(lib, new File("./src/main/resources/libreria.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}