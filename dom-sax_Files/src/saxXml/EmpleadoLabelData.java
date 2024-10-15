package saxXml;

public class EmpleadoLabelData {
    private String apellido;
    private int nEmpleado;
    private double salario;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getnEmpleado() {
        return nEmpleado;
    }

    public void setnEmpleado(int nEmpleado) {
        this.nEmpleado = nEmpleado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "EmpleadoLabelData{" +
            "apellido='" + apellido + '\'' +
            ", nEmpleado=" + nEmpleado +
            ", salario=" + salario +
            '}';
    }
}