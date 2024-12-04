package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.vinylrecord.model.VinylRecord;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Leiton
 */
public class VinylRecordManager {
      private static final String FILE_PATH = "vinyl_records.json";
    private List<VinylRecord> records;
    private Gson gson;

    public VinylRecordManager() {
        records = new ArrayList<>();
        gson = new Gson();
        loadRecords();
    }

    public void addRecord(VinylRecord record) {
        records.add(record);
        saveRecords();
    }

    public List<VinylRecord> getRecords() {
        return records;
    }

    private void saveRecords() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(records, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRecords() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            java.lang.reflect.Type recordListType = new TypeToken<ArrayList<VinylRecord>>(){}.getType();
            records = gson.fromJson(reader, recordListType);
            if (records == null) {
                records = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            records = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
