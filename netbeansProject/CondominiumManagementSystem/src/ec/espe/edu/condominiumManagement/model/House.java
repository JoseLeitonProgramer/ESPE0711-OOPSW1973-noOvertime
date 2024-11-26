package ec.espe.edu.condominiumManagement.model;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Jose Leiton
 */
public class House {
    private int id;
    private int ageInYears;
    private String nameOwner;
    private int numberHouse;
    private float expense;
    private int pets;
    private int vehicles;
    private int residents;
    private LocalDate bornOnDate;  // Cambiar a LocalDate
    private boolean not;

    @Override // sobrecargando toString
    public String toString() {
        return "House{" + "id=" + id + ", age=" + ageInYears + ", nameOwner=" + nameOwner + ", numberHouse=" + numberHouse + ", expense=" + expense + ", pets=" + pets + ", vehicles=" + vehicles + ", residents=" + residents + ", bornOnDate=" + bornOnDate + ", not=" + not + '}';
    }

    public House(int id, String nameOwner, int numberHouse, float expense, int pets, int vehicles, int residents, LocalDate bornOnDate, boolean not) {
        this.id = id;
        this.nameOwner = nameOwner;
        this.numberHouse = numberHouse;
        this.expense = expense;
        this.pets = pets;
        this.vehicles = vehicles;
        this.residents = residents;
        this.bornOnDate = bornOnDate;
        this.not = not;

       
        this.ageInYears = computeAgeInYears();
    }

    
    private int computeAgeInYears() {
        if (bornOnDate == null) {
            return 0;
        }
        LocalDate currentDate = LocalDate.now(); 
        Period period = Period.between(bornOnDate, currentDate);  
        return period.getYears();  
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public int getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(int numberHouse) {
        this.numberHouse = numberHouse;
    }

    public float getExpense() {
        return expense;
    }

    public void setExpense(float expense) {
        this.expense = expense;
    }

    public int getPets() {
        return pets;
    }

    public void setPets(int pets) {
        this.pets = pets;
    }

    public int getVehicles() {
        return vehicles;
    }

    public void setVehicles(int vehicles) {
        this.vehicles = vehicles;
    }

    public int getResidents() {
        return residents;
    }

    public void setResidents(int residents) {
        this.residents = residents;
    }

    public LocalDate getBornOnDate() {
        return bornOnDate;
    }

    public void setBornOnDate(LocalDate bornOnDate) {
        this.bornOnDate = bornOnDate;
    }

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    public int getAgeInYears() {
        return ageInYears;
    }

    public void setAgeInYears(int ageInYears) {
        this.ageInYears = ageInYears;
    }
}


