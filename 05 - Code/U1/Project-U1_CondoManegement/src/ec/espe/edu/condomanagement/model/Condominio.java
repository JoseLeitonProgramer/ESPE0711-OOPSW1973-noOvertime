package ec.espe.edu.condomanagement.model;

/**
 *
 * @author Jose Leiton
 */
public class Condominio {
    private String nombre;
    private String direccion;
    private int totalDepartamentos;

    // Constructor
    public Condominio(String nombre, String direccion, int totalDepartamentos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.totalDepartamentos = totalDepartamentos;
    }

    // Métodos getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTotalDepartamentos() {
        return totalDepartamentos;
    }

    public void setTotalDepartamentos(int totalDepartamentos) {
        this.totalDepartamentos = totalDepartamentos;
    }

    // Método para mostrar información del condominio
    public void mostrarInfo() {
        System.out.println("Nombre del Condominio: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Total de Departamentos: " + totalDepartamentos);
    }
}