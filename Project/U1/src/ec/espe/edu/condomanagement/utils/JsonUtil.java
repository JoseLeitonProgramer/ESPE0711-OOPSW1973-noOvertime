package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonUtil {

    // Método para guardar un objeto JSON en un archivo
    public static void saveToJson(String filePath, JSONObject jsonObject) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer un objeto JSON desde un archivo
    public static JSONObject readFromJson(String filePath, JSONObject defaultObject) {
        File file = new File(filePath);
        if (!file.exists()) {
            saveToJson(filePath, defaultObject);
            System.out.println("Default file created: " + filePath);
            return defaultObject;
        }

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = jsonParser.parse(reader);
            return (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return defaultObject;
        }
    }
}
