package evaluacion_inicial.notification;

public class NTelefonica extends Notification {
    private String numero;

    public NTelefonica (String numero, int id, String mensaje) {
        super(id, mensaje);
        this.numero = numero;
    }

    @Override
    String getEmailOrTlfn() {
        return this.numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Numero: " + numero;
    }
}
