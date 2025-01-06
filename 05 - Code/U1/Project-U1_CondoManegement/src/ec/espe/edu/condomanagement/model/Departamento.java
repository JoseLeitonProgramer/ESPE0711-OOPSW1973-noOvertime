package ec.espe.edu.condomanagement.model;

/**
 *
 * @author Jose Leiton
 */
public class Departamento extends Condominio {
    private int numeroDepartamento;
    private String propietario;
    private double cuotaMantenimiento;

    // Constructor
    public Departamento(String nombre, String direccion, int totalDepartamentos, int numeroDepartamento, String propietario, double cuotaMantenimiento) {
        super(nombre, direccion, totalDepartamentos);
        this.numeroDepartamento = numeroDepartamento;
        this.propietario = propietario;
        this.cuotaMantenimiento = cuotaMantenimiento;
    }

    // Métodos getters y setters
    public int getNumeroDepartamento() {
        return numeroDepartamento;
    }

    public void setNumeroDepartamento(int numeroDepartamento) {
        this.numeroDepartamento = numeroDepartamento;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public double getCuotaMantenimiento() {
        return cuotaMantenimiento;
    }

    public void setCuotaMantenimiento(double cuotaMantenimiento) {
        this.cuotaMantenimiento = cuotaMantenimiento;
    }

    // Método para mostrar información del departamento
    @Override
    public void mostrarInfo() {
        super.mostrarInfo(); // Llama al método de la clase padre
        System.out.println("Número de Departamento: " + numeroDepartamento);
        System.out.println("Propietario: " + propietario);
        System.out.println("Cuota de Mantenimiento: " + cuotaMantenimiento);
    }
}