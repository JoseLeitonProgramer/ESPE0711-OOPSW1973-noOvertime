package ec.espe.edu.condomanagement.view;

import ec.espe.edu.condomanagement.model.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import utils.FileManager;

public class PrjCondoManagement {

    private static Scanner scanner = new Scanner(System.in);
    private static Administrator admin = new Administrator("A1", "Admin");
    private static Resident currentResident; // Variable para mantener la sesión del residente

    public static void main(String[] args) {
        // Cargar datos de Administrator desde archivo JSON
        admin = FileManager.loadAdministrator("admin.json");

        // Menú principal
        while (true) {
            System.out.println("********** Condo Management System **********");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Resident");
            System.out.println("3. Save Data");
            System.out.println("4. Load Data");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    residentLogin();
                    break;
                case 3:
                    saveData();
                    break;
                case 4:
                    loadData();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    private static void saveData() {
        FileManager.saveAdministrator(admin, "admin.json");
        System.out.println("Data saved successfully.");
    }

    private static void loadData() {
        admin = FileManager.loadAdministrator("admin.json");
        System.out.println("Data loaded successfully.");
    }

    private static void residentLogin() {
        System.out.print("Enter Resident ID: ");
        String id = scanner.nextLine();
        currentResident = admin.findResidentById(id); // Buscar residente por ID

        if (currentResident != null) {
            System.out.println("Welcome, " + currentResident.getName());
            residentMenu();
        } else {
            System.out.println("Resident not found. Returning to main menu.");
        }
    }

    private static void residentMenu() {
        while (true) {
            System.out.println("\n********** Resident Menu **********");
            System.out.println("1. View Personal Information");
            System.out.println("2. Generate Utility Bill");
            System.out.println("3. Logout");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    viewPersonalInformation();
                    break;
                case 2:
                    generateResidentUtilityBill();
                    break;
                case 3:
                    currentResident = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void generateResidentUtilityBill() {
        if (currentResident == null) {
            System.out.println("You must log in to generate a utility bill.");
            return;
        }

        System.out.print("Enter Utility Bill ID: ");
        String billId = scanner.nextLine();
        System.out.print("Enter Amount: ");
        float amount = scanner.nextFloat();
        scanner.nextLine();

        UtilityBill newBill = new UtilityBill(billId, currentResident, amount);
        admin.addUtilityBill(newBill);

        System.out.println("Utility bill generated successfully.");
    }

    private static void viewPersonalInformation() {
        System.out.println("\nPersonal Information:");
        System.out.println("ID: " + currentResident.getId());
        System.out.println("Name: " + currentResident.getName());
        System.out.println("Phone: " + currentResident.getPhone());
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n********** Admin Menu **********");
            System.out.println("1. Manage Residents");
            System.out.println("2. Manage Vehicles");
            System.out.println("3. Generate Utility Bill");
            System.out.println("4. Process Payment");
            System.out.println("5. Manage Area Reservations");
            System.out.println("6. View All Residents");
            System.out.println("7. View All Vehicles");
            System.out.println("8. View All Utility Bills");
            System.out.println("9. View All Payments");
            System.out.println("10. Logout");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    manageResidents();
                    break;
                case 2:
                    manageVehicles();
                    break;
                case 3:
                    generateUtilityBill();
                    break;
                case 4:
                    processPayments();
                    break;
                case 5:
                    manageAreaReservations();
                    break;
                case 6:
                    admin.displayAllResidents();
                    break;
                case 7:
                    admin.displayAllVehicles();
                    break;
                case 8:
                    admin.displayAllUtilityBills();
                    break;
                case 9:
                    admin.displayAllPayments();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void generateUtilityBill() {
        System.out.print("Enter Resident ID to generate the bill for: ");
        String residentId = scanner.nextLine();
        Resident resident = admin.findResidentById(residentId);

        if (resident == null) {
            System.out.println("Resident not found. Please verify the ID.");
            return;
        }

        System.out.print("Enter Utility Bill ID: ");
        String billId = scanner.nextLine();
        System.out.print("Enter Amount: ");
        float amount = scanner.nextFloat();
        scanner.nextLine();

        UtilityBill bill = new UtilityBill(billId, resident, amount);
        admin.addUtilityBill(bill);

        System.out.println("Utility bill successfully added for: " + resident.getName());
    }

    private static void processPayments() {
        System.out.print("Enter Payment Amount: ");
        float amount = scanner.nextFloat();
        scanner.nextLine();
        Payment payment = new Payment("P" + System.currentTimeMillis(), null, amount, new Date(), "Cash");
        admin.processPayment(payment);
        System.out.println("Payment processed.");
    }

    private static void manageResidents() {
        System.out.println("\n1. Add Resident");
        System.out.println("2. Remove Resident");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                addResident();
                break;
            case 2:
                removeResident();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void addResident() {
        System.out.print("Enter Resident ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Resident Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Resident Phone: ");
        String phone = scanner.nextLine();

        Resident resident = new Resident(id, name, phone);
        admin.addResident(resident);
        System.out.println("Resident added successfully.");
    }

    private static void removeResident() {
        System.out.print("Enter Resident ID to Remove: ");
        String id = scanner.nextLine();
        admin.removeResident(id);
        System.out.println("Resident removed successfully.");
    }

    private static void manageVehicles() {
        System.out.print("Enter the Vehicle's License Plate: ");
        String licensePlate = scanner.nextLine();
        System.out.print("Enter the Vehicle Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter the Vehicle Model: ");
        String model = scanner.nextLine();

        Vehicle newVehicle = new Vehicle("V" + System.currentTimeMillis(), make, model, licensePlate);
        admin.getVehicles().add(newVehicle);
        System.out.println("Vehicle added successfully.");
    }

    private static void manageAreaReservations() {
        System.out.print("Enter Resident ID for Reservation: ");
        String residentId = scanner.nextLine();
        Resident resident = admin.findResidentById(residentId);

        if (resident == null) {
            System.out.println("Resident ID not found.");
            return;
        }

        System.out.print("Enter Area Name: ");
        String area = scanner.nextLine();
        System.out.print("Enter Reservation Date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();

        Date reservationDate = null;
        try {
            reservationDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (Exception e) {
            System.out.println("Invalid Date Format.");
            return;
        }

        AreaReservation reservation = new AreaReservation("AR" + System.currentTimeMillis(), resident, area, reservationDate, "10:00AM");
        resident.addReservation(reservation);
        System.out.println("Area reservation created successfully.");
    }
}
