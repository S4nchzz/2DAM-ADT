package evaluacion_inicial.docu;

public class Nie extends Documentacion {
    private String numero;
    private char letraInicial;
    private char letraFinal;

    public Nie (char letraInicial, String numero, char letraFinal) {
        this.numero = numero;
        this.letraInicial = letraInicial;
        this.letraFinal = letraFinal;
    }

    public Nie() {
    }

    @Override
    public boolean validar() {
        if ((letraInicial != 'X' && letraInicial != 'Y' && letraInicial != 'Z' && letraInicial != 'x' && letraInicial != 'y' && letraInicial != 'z')) {
            return false;
        }

        if (numero.length() != 7) {
            return false;
        }

        // Number is not between 0~9
        for (char c : numero.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }

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
        return this.numero;
    }

    public char getLetraInicial() {
        return this.letraInicial;
    }

    public char getLetraFinal() {
        return this.letraFinal;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setLetraInicial(char letraInicial) {
        this.letraInicial = letraInicial;
    }

    public void setLetraFinal(char letraFinal) {
        this.letraFinal = letraFinal;
    }

    @Override
    public String toString() {
        return letraInicial +  numero + letraFinal;
    }
}
