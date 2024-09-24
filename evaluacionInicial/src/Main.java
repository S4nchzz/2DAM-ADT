package evaluacion_inicial;

import java.util.ArrayList;
import java.util.List;

import evaluacion_inicial.docu.Nif;
import evaluacion_inicial.notification.NEmail;

public class Main {
    public static void main(String[] args) {
        Cliente c1 = new Cliente(new Nif("1000", 'A'), "iyan", "sanchez", "10291021", new DateFormat(9, 7, 2004), false,TipoPago.E);
        Cliente c = Cliente.nuevoCliente();
        System.out.println(c.toString());

        for (int i = 0; i < 30; i++) {
            c.anadirNotificacion(new NEmail("example" + i + "@gmail.com", i, "Notification " + i));
        }

        System.out.println(c.notificacinesEn(2024));
        System.out.println(c1.notificacinesEn(2024));

        List<Cliente> list = new ArrayList<>();
        list.add(c);
        list.add(c1);

        Cliente.infoClientes(list);
    }
}