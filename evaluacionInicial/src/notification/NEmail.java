package evaluacion_inicial.notification;

public class NEmail extends Notification {
    private String direccion;
    
    public NEmail(String direccion, int id , String mensaje) {
        super(id, mensaje);
        this.direccion = direccion;
    }

    @Override
    String getEmailOrTlfn() {
        return this.direccion;
    }

    public NEmail() {
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Direccion: " + direccion;
    }
}
