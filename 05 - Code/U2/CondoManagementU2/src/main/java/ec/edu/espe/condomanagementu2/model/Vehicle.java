package ec.edu.espe.condomanagementu2.model;

/**
 *
 * @author Alexander Maza
 */
public class Vehicle {
    
    private String vehicleID;
    private String make;
    private String model;
    private String licensePlate;

    public Vehicle(String vehicleID, String make, String model, String licensePlate) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "vehicleID=" + vehicleID + ", make=" + make + ", model=" + model + ", licensePlate=" + licensePlate + '}';
    }

    
    
    
    /**
     * @return the vehicleID
     */
    public String getVehicleID() {
        return vehicleID;
    }

    /**
     * @param vehicleID the vehicleID to set
     */
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the licensePlate
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @param licensePlate the licensePlate to set
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    

