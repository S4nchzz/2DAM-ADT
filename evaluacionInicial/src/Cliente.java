package evaluacion_inicial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import evaluacion_inicial.docu.Documentacion;
import evaluacion_inicial.docu.Nie;
import evaluacion_inicial.docu.Nif;
import evaluacion_inicial.notification.Notification;

public class Cliente {
    private Documentacion id;
    private String nombre;
    private String direccion;
    private String telefono;
    private DateFormat fechaNac;
    private boolean suscripcion;
    private TipoPago pagoRef;
    private ArrayList<Notification> notificationList;

    public Cliente(Documentacion id, String nombre, String direccion, String telefono, DateFormat fechaNac, boolean suscripcion, TipoPago pagoRef) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.suscripcion = suscripcion;
        this.pagoRef = pagoRef;

        this.notificationList = new ArrayList<>();
    }
    
    public Cliente() {
    }

    public static Cliente nuevoCliente() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nif o nie (1 | 2)");

        int response = sc.nextInt();
        while (response != 1 && response != 2) {
            System.out.println("Nif o nie (1 | 2)");
            response = sc.nextInt();
        }

        Documentacion docu = null;

        boolean validated = false;
        switch (response) {
            case 1:
                while (!validated) {
                    System.out.println("Escriba numero NIF sin la letra");
                    int numeroNif = sc.nextInt();

                    System.out.println("Escriba letra NIF (Solo se recogera el primer caracter escrito)");
                    String letraInput = sc.next();

                    char letra = letraInput.charAt(0);

                    docu = new Nif(String.valueOf(numeroNif), letra);

                    if (!docu.validar()) {
                        validated = false;
                        System.out.println(
                                "No se ha podido validar el NIF, recuerde que el NIF esta compuesto de un numero de 8 cifras y una letra de la A~Z, repitiendo proceso \n ----------------");
                    } else {
                        validated = true;
                    }
                }

                break;

            case 2:
            while (!validated) {
                System.out.println("Escriba la primera letra del NIE (Solo se recogera el primer caracter escrito)");
                String firstCharInput = sc.next();

                char firstChar = firstCharInput.charAt(0);

                System.out.println("Escriba el numero del NIE");
                int numeroNie = sc.nextInt();

                System.out.println("Escriba la ultima letra del NIE (Solo se recogera el primer caracter escrito)");
                String lastCharInput = sc.next();

                char lastChar = lastCharInput.charAt(0);

                docu = new Nie(firstChar, String.valueOf(numeroNie), lastChar);
                if (!docu.validar()) {
                    validated = false;
                    System.out.println("No se ha podido validar, recuerde que el NIE tiene una letra (X,Y,Z), un numero de 7 digitos y una letra entre la A y la Z, repitiendo proceso \n ----------------");
                } else {
                    validated = true;
                }
            }

            break;
        }

        System.out.println("Nombre: ");
        final String name = sc.next();

        System.out.println("Direccion: ");
        final String direccion = sc.next();
        
        System.out.println("Telefono: ");
        final String telefono = sc.next();
        
        System.out.println("Dia de nacimiento: ");
        final int day = sc.nextInt();
        
        System.out.println("Mes de nacimiento: ");
        final int month = sc.nextInt();

        System.out.println("Año de nacimiento: ");
        final int year = sc.nextInt();

        System.out.println("Tipo de pago (E (efectivo) | T (transferencia) | C (tarjeta))");

        String paymentType = sc.next();
        while (!paymentType.equals("E") && !paymentType.equals("T") && !paymentType.equals("C")) {
            System.out.println("Tipo de pago (E (efectivo) | T (transferencia) | C (tarjeta))");
            paymentType = sc.next();
        }

        TipoPago type = null;
        switch (paymentType) {
            case "E":
                type = TipoPago.E;
                break;
            case "T":
                type = TipoPago.T;
                break;
            case "C":
                type = TipoPago.C;
                break;
            default:
                break;
        }

        sc.close();
        return new Cliente(docu, name, direccion, telefono, new DateFormat(day, month, year), false, type);
    }

    public static void infoClientes(List<Cliente> clientList) {
        File createFile = new File("./InfoClientes.txt");

        try {
            FileOutputStream out = new FileOutputStream(createFile);
            
            for (Cliente c : clientList) {
                out.write(("nombre " + "(" + c.id + ") " + c.fechaNac.getDateStringFormat() + " Tfno: " + c.telefono + " Dir: " + c.getDireccion()).getBytes());
                out.write(("\n").getBytes());
            }

            out.close();
        } catch (IOException e) {
            // File not found
            System.out.println(e.getMessage());
        }
    }

    public void anadirNotificacion(Notification noti) {
        this.notificationList.add(noti);
    }

    public String notificacinesEn(int ano) {
        int [] nNotificacionesXMes = new int[12];

        int monthIterator = 1;
        int countPerMoth = 0;
        for (; monthIterator <= 12; monthIterator++) {
            for (Notification noti : this.notificationList) {
                if (noti.getFecha().getAno() == ano && noti.getFecha().getMes() == monthIterator) {
                    countPerMoth++;
                }
            }

            nNotificacionesXMes[monthIterator - 1] = countPerMoth;
            countPerMoth = 0;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nNotificacionesXMes.length - 1; i++) {
            switch (i + 1) {
                case 1:
                    sb.append("Enero: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 2:
                    sb.append("Febrero: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 3:
                    sb.append("Marzo: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 4:
                    sb.append("Abril: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 5:
                    sb.append("Mayo: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 6:
                    sb.append("Junio: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 7:
                    sb.append("Julio: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 8:
                    sb.append("Agosto: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 9:
                    sb.append("Septiembre: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 10:
                    sb.append("Octubre: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 11:
                    sb.append("Noviembre: " + nNotificacionesXMes[i] + "\n");
                    break;
                case 12:
                    sb.append("Diciembre: " + nNotificacionesXMes[i] + "\n");
                    break;
                default:
                    break;
            }
        }

        return sb.toString();
    }

    public Documentacion getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public DateFormat getFechaNac() {
        return fechaNac;
    }

    public boolean isSuscripcion() {
        return suscripcion;
    }

    public TipoPago getPagoRef() {
        return pagoRef;
    }

    public void setId(Documentacion id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaNac(DateFormat fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setSuscripcion(boolean suscripcion) {
        this.suscripcion = suscripcion;
    }

    public void setPagoRef(TipoPago pagoRef) {
        this.pagoRef = pagoRef;
    }

    @Override
    public String toString() {
        return "Cliente id: " + id +
        ", nombre: " + nombre +
        ", direccion: " + direccion +
        ", telefono: " + telefono +
        ", fecha de nacimiento: " + fechaNac.getDateStringFormat() +
        ", suscripcion: " + suscripcion +
        ", referencia de pago: " + pagoRef;
    }
}