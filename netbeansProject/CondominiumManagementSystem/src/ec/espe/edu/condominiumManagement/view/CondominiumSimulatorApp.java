package ec.espe.edu.condominiumManagement.view;

import ec.espe.edu.condominiumManagement.model.House;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CondominiumSimulatorApp {

    public static void main(String[] args) {
        System.out.println("Jose's Condominium Simulator");

        // Crear objeto Scanner para recibir entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar y leer datos desde la consola
        System.out.print("Enter House ID: ");
        int id = scanner.nextInt();

        scanner.nextLine();  // Limpiar el buffer del Scanner

        System.out.print("Enter Owner's Name: ");
        String nameOwner = scanner.nextLine();

        System.out.print("Enter House Number: ");
        int numberHouse = scanner.nextInt();

        System.out.print("Enter Monthly Expense: ");
        float expense = scanner.nextFloat();

        System.out.print("Enter Number of Pets: ");
        int pets = scanner.nextInt();

        System.out.print("Enter Number of Vehicles: ");
        int vehicles = scanner.nextInt();

        System.out.print("Enter Number of Residents: ");
        int residents = scanner.nextInt();

        System.out.print("Enter the Year of Birth (YYYY): ");
        int year = scanner.nextInt();

        System.out.print("Enter the Month of Birth (MM): ");
        int month = scanner.nextInt();

        System.out.print("Enter the Day of Birth (DD): ");
        int day = scanner.nextInt();

        LocalDate bornOnDate = LocalDate.of(year, month, day);

        House house = new House(id, nameOwner, numberHouse, expense, pets, vehicles, residents, bornOnDate, false);

        // Mostrar los detalles de la casa
        System.out.println("\nHouse details:");
        System.out.println("House --> " + house);

        // Guardar los datos en un archivo JSON
        saveHouseToJson(house);

        // Guardar los datos en un archivo CSV
        saveHouseToCsv(house);

        scanner.close();
    }

    private static void saveHouseToJson(House house) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String json = gson.toJson(house);

        try (FileWriter writer = new FileWriter("house_data.json")) {
            writer.write(json);
            System.out.println("House data saved to house_data.json");
        } catch (IOException e) {
            System.err.println("An error occurred while saving the data to JSON: " + e.getMessage());
        }
    }

    // Método para guardar la casa en un archivo CSV
    private static void saveHouseToCsv(House house) {
        StringBuilder csvData = new StringBuilder();

        // Crear la cabecera del CSV
        csvData.append("ID,Owner,House Number,Expense,Pets,Vehicles,Residents,BirthDate\n");

        // Formatear la fecha de nacimiento a un formato legible usando DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDateFormatted = house.getBornOnDate().format(formatter);

        // Agregar los datos de la casa
        csvData.append(house.getId()).append(",")
                .append(house.getNameOwner()).append(",")
                .append(house.getNumberHouse()).append(",")
                .append(house.getExpense()).append(",")
                .append(house.getPets()).append(",")
                .append(house.getVehicles()).append(",")
                .append(house.getResidents()).append(",")
                .append(birthDateFormatted).append("\n");

        try (FileWriter writer = new FileWriter("house_data.csv")) {
            writer.write(csvData.toString());
            System.out.println("House data saved to house_data.csv");
        } catch (IOException e) {
            System.err.println("An error occurred while saving the data to CSV: " + e.getMessage());
        }
    }

    // Adaptador personalizado para LocalDate
    public static class LocalDateAdapter extends TypeAdapter<LocalDate> {

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.toString());  // Convertir LocalDate a su formato ISO (yyyy-MM-dd)
            }
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            String dateStr = in.nextString();
            return LocalDate.parse(dateStr);  // Parsear el string al formato LocalDate
        }
    }
}

