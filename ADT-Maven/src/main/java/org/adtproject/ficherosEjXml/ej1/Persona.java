package org.adtproject.ficherosEjXml.ej1;

import java.io.Serializable;

public record Persona(String getNombre, String getDni, String getTelf, int getEdad) implements Serializable {

}
