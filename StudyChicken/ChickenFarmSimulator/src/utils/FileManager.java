package utils;

import ec.edu.espe.farmsimulator.model.Chicken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class FileManager {
    private static final String DEFAULT_FILE_PATH = "chickens.json";
    
    private static final Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat("yyyy-MM-dd")
        .create();

    public static void save(List<Chicken> chickens) {
        save(chickens, DEFAULT_FILE_PATH);
    }

    public static void save(List<Chicken> chickens, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(chickens, writer); // Guarda la lista completa con bornOnDate
            System.out.println("Date saved in " + fileName);
        } catch (IOException e) {
            System.out.println(" Error saving data " + e.getMessage());
        }
    }

    public static List<Chicken> read() {
        return read(DEFAULT_FILE_PATH);
    }

    public static List<Chicken> read(String fileName) {
    List<Chicken> chickens = new ArrayList<>();
    File file = new File(fileName);

    if (!file.exists()) {
        System.out.println("File does not exist. Creating new file with an empty list.");
        save(chickens, fileName);
        return chickens;
    }

    try {
        String json = new String(Files.readAllBytes(file.toPath()));
        if (json.trim().isEmpty()) {
            System.out.println("File is empty. Starting with an empty list.");
            return chickens;
        }

        Chicken[] chickenArray = gson.fromJson(json, Chicken[].class);
        if (chickenArray != null) {
            chickens = new ArrayList<>(List.of(chickenArray));
        }
        System.out.println("Successfully loaded " + chickens.size() + " chickens from " + fileName);
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    } catch (com.google.gson.JsonSyntaxException e) {
        System.err.println("Invalid JSON format. Creating a new file.");
        try {
            Files.move(file.toPath(), Paths.get(fileName + ".corrupt"));
            save(chickens, fileName);
        } catch (IOException ex) {
            System.err.println("Error renaming corrupt file: " + ex.getMessage());
        }
    }

    return chickens;
}
}