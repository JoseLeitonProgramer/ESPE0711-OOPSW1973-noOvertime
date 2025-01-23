package ec.edu.espe.condomanagementu2.model;


public class AreaPaymentHistory {
    private String residentId;
    private String area;
    private Double amount;
    private String status;  // El estado del pago
    private String id;  // El ID del pago

    // Constructor
    public AreaPaymentHistory(String residentId, String area, Double amount, String status, String id) {
        this.residentId = residentId;
        this.area = area;
        this.amount = amount;
        this.status = status;
        this.id = id;  // Inicializar el ID
    }

    // Métodos getter y setter para status e id
    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getResidentId() {
        return residentId;
    }

    public String getArea() {
        return area;
    }

    public Double getAmount() {
        return amount;
    }

    // Otros getters y setters
}

