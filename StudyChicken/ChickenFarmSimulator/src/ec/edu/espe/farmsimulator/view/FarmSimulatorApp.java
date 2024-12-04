package ec.edu.espe.farmsimulator.view;

import ec.edu.espe.farmsimulator.model.Chicken;
import utils.FileManager;
import java.util.*;

/**
 *
 * @author Jose Leiton
 */
public class FarmSimulatorApp {

    public static void main(String[] args) {
        System.out.println("Alan's Chicken Farm Simulator");
        Scanner sc = new Scanner(System.in);
        char opcion;

        ArrayList<Chicken> chickens = new ArrayList<>(FileManager.read());
        Chicken chicken;

        do {
            System.out.println("\n---- Menu ----");
            System.out.println("1.- Enter chicken");
            System.out.println("2.- Read chicken");
            System.out.println("3.- Update chicken");
            System.out.println("4.- Delete chicken");
            System.out.println("5.- Quit");
            System.out.print("Enter the option: ");
            opcion = sc.next().charAt(0);
            sc.nextLine();

            switch (opcion) {
                case '1':
                    chicken = new Chicken();
                    chicken.enterChicken();
                    chickens.add(chicken);
                    FileManager.save(chickens);
                    System.out.println("Chicken added: " + chicken);
                    break;
                case '2':
                    System.out.println("Listing all chickens:");
                    if (chickens.isEmpty()) {
                        System.out.println("No chickens available.");
                    } else {
                        chicken = new Chicken();
                        chicken.readChicken(chickens);
                    }
                    break;
                case '3':
                    System.out.println("Updating chicken...");
                    System.out.print("Enter ID of a chicken to update: ");
                    if (sc.hasNextInt()) {
                        int up = sc.nextInt();
                        sc.nextLine();
                        chicken = new Chicken();
                        Chicken chickenToUpdate = chicken.findChickenById(chickens, up);
                        if (chickenToUpdate != null) {
                            chicken.updateChicken(chickenToUpdate);
                            FileManager.save(chickens);
                        } else {
                            System.out.println("Chicken with ID " + up + " not found.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a numeric ID.");
                        sc.next();
                    }
                    break;
                case '4':
                    System.out.println("Deleting chicken...");
                    System.out.print("Enter ID of a chicken to delete: ");
                    if (sc.hasNextInt()) {
                        int idToDelete = sc.nextInt();
                        sc.nextLine();
                        chicken = new Chicken();
                        chicken.deleteChicken(chickens, idToDelete);
                        FileManager.save(chickens);
                    } else {
                        System.out.println("Invalid input. Please enter a numeric ID.");
                        sc.next();
                    }
                    break;
                case '5':
                    FileManager.save(chickens);
                    System.out.println("Saving data and exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (opcion != '5');
    }
}
