package org.adtproject.ficherosEjXml.ej3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Personas {
    private List<Persona> personList;

    public Personas() {
        personList = new ArrayList<>();
    }

    public Personas(ArrayList<Persona> personList) {
        this.personList = personList;
    }

    @XmlElementWrapper(name = "personList")
    @XmlElement(name = "persona")
    public List<Persona> getPersonList() {
        return personList;
    }
}
