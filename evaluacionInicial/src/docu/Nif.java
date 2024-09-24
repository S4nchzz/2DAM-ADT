package evaluacion_inicial.docu;

public class Nif extends Documentacion{
    private String numero;
    private char letraFinal;

    public Nif(String numero, char letraFinal) {
        this.numero = numero;
        this.letraFinal = letraFinal;
    }

    public Nif() {
    }

    @Override
    public boolean validar() {
        if (numero.length() != 8) {
            // Not enougth numbers
            return false;
        }

        // Chekc if there is there is non numerical values
        for (char c : numero.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }

        // Check the last char
        if (!(letraFinal >= 'a' && letraFinal <= 'z') && !(letraFinal >= 'A' && letraFinal <= 'Z')) {
            return false;
        }

        return true;
    }

    @Override
    public String mostrar() {
        return toString();
    }

    public String getNumero() {
        return numero;
    }

    public char getLetraFinal() {
        return letraFinal;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setLetraFinal(char letraFinal) {
        this.letraFinal = letraFinal;
    }

    @Override
    public String toString() {
        return numero + letraFinal;
    }
}
