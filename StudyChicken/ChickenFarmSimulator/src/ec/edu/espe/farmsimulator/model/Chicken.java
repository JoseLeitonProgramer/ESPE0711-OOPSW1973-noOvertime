package ec.edu.espe.farmsimulator.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Jose Leiton
 */
public class Chicken {

    private int id;
    private String name;
    private String color;
    private int ageInMonths;
    private String bornOnDate;
    private boolean notMolting;

    @Override
    public String toString() {
        return "Chicken{" + "id=" + id + ", name=" + name + ", color=" + color + ", age=" + ageInMonths + ", BornOnDate=" + bornOnDate + ", notMolting=" + notMolting + '}';
    }

    public Chicken(int id, String name, String color, int ageInMonths, Date BornOnDate, boolean notMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.bornOnDate = BornOnDate != null ? sdf.format(BornOnDate) : null;
        this.notMolting = notMolting;
        this.ageInMonths = computeAgeInMonths();
    }

    public Chicken() {
    }

    public void enterChicken() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id of chicken: ");
        setId(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter name of chicken: ");
        setName(sc.nextLine());
        System.out.print("Enter color of chicken: ");
        setColor(sc.nextLine());
        System.out.print("Enter birth date of chicken (YYYY-MM-DD): ");
        String birthDateInput = sc.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date bornOnDate = sdf.parse(birthDateInput);
            setBornOnDate(sdf.format(bornOnDate));
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        System.out.print("Is molting? (true/false): ");
        setNotMolting(sc.nextBoolean());
    }

    public void readChicken(List<Chicken> chickens) {
        if (chickens.isEmpty()) {
            System.out.println("No chickens available.");
            return;
        }
        for (Chicken chicken : chickens) {
            System.out.println(chicken);
        }
    }

    public Chicken findChickenById(ArrayList<Chicken> chickens, int id) {
        for (Chicken chicken : chickens) {
            if (chicken.getId() == id) {
                return chicken;
            }
        }
        return null;
    }

    public void updateChicken(Chicken up) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the attribute to update:");
        System.out.println("1. Name");
        System.out.println("2. Color");
        System.out.println("3. Not Molting");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = sc.nextLine();
                up.setName(newName);
                System.out.println("Name updated to: " + newName);
                break;
            case 2:
                System.out.print("Enter new color: ");
                String newColor = sc.nextLine();
                up.setColor(newColor);
                System.out.println("Color updated to: " + newColor);
                break;
            case 3:
                System.out.print("Is molting? (true/false): ");
                boolean newNotMolting = sc.nextBoolean();
                up.setNotMolting(newNotMolting);
                System.out.println("Molting status updated to: " + newNotMolting);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void deleteChicken(ArrayList<Chicken> chickens, int id) {
        Chicken chickenToDelete = findChickenById(chickens, id);
        if (chickenToDelete != null) {
            chickens.remove(chickenToDelete);
            System.out.println("Chicken with ID " + id + " has been deleted.");
        } else {
            System.out.println("Chicken with ID " + id + " not found.");
        }
    }

    public int computeAgeInMonths() {
        if (bornOnDate == null) {
            return 0;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = sdf.parse(bornOnDate);
            Date currentDate = new Date();

            long diffInMillies = currentDate.getTime() - birthDate.getTime();
            long diffInMonths = diffInMillies / (1000L * 60 * 60 * 24 * 30);

            return (int) diffInMonths;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the ageInMonths
     */
    public int getAgeInMonths() {
        return ageInMonths;
    }

    /**
     * @param ageInMonths the ageInMonths to set
     */
    public void setAgeInMonths(int ageInMonths) {
        //TODO compute the agge based on the born on date attribute 
        this.ageInMonths = ageInMonths;
    }

    /**
     * @return the bornOnDate
     */
    public String getBornOnDate() {
        return bornOnDate;
    }

    /**
     * @param bornOnDate the bornOnDate to set
     */
    public void setBornOnDate(String bornOnDate) {
        this.bornOnDate = bornOnDate;
        this.ageInMonths = computeAgeInMonths();
    }

    /**
     * @return the notMolting
     */
    public boolean isNotMolting() {
        return notMolting;
    }

    /**
     * @param notMolting the notMolting to set
     */
    public void setNotMolting(boolean notMolting) {
        this.notMolting = notMolting;
    }

}
