package evaluacion_inicial;

public class DateFormat {
    private final int dia;
    private final int mes;
    private final int ano;

    public DateFormat (int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String getDateStringFormat() {
        return dia + "/" + mes + "/" + ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }    
}
