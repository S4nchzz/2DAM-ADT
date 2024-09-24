package evaluacion_inicial.notification;

import java.time.LocalDate;

import evaluacion_inicial.DateFormat;

public abstract class Notification {
    private int id;
    private DateFormat fecha;
    private String mensaje;

    public Notification (int id, String mensaje) {
        this.id = id;
        
        LocalDate LocalDate = java.time.LocalDate.now();      
        this.fecha = new DateFormat(LocalDate.getDayOfMonth(), LocalDate.getMonthValue(), LocalDate.getYear());
        
        this.mensaje = mensaje;
    }

    abstract String getEmailOrTlfn();

    public Notification () {
    }

    public int getId() {
        return id;
    }

    public DateFormat getFecha() {
        return fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(DateFormat fecha) {
        this.fecha = fecha;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Notification ID: " + id + " | " + "Notification Date: " + fecha.getDateStringFormat()
                + " | " + "Notification Message: " + mensaje;
    }
}
