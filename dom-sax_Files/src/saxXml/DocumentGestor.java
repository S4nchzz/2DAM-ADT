package saxXml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class DocumentGestor extends DefaultHandler {
    private final List<EmpleadoLabelData> empleados;
    private EmpleadoLabelData e;
    private int index = -2;
    private boolean finalCheck;

    public DocumentGestor() {
        super();
        empleados = new ArrayList<>();
        index = -2;
        finalCheck = false;
    }

    @Override
    public void startDocument() {
        System.out.println("Start of the document");
    }

    @Override
    public void endDocument() {
        System.out.println("End of the document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.println("Start Element: " + qName);

        if (index == -2) {
            e = new EmpleadoLabelData();
        } 
        
        finalCheck = false;
        if (index == 3) {
            index = 0;
            e = new EmpleadoLabelData();
        } else {
            index++;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println("End Element: " + qName);
        finalCheck = true;
        empleados.add(e);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (!finalCheck) {
            System.out.println("Characters: " + new String(ch, start, length));
            switch (index) {
                case 1:
                    e.setApellido(new String(ch, start, length));
                    break;
                case 2:
                    e.setnEmpleado(Integer.valueOf(new String(ch, start, length)));
                    break;
                case 3:
                    e.setSalario(Double.valueOf(new String(ch, start, length)));
                    break;
    
                default:
                    break;
            }
        }
    }

    public List<EmpleadoLabelData> getElements() {
        return empleados;
    }
}
