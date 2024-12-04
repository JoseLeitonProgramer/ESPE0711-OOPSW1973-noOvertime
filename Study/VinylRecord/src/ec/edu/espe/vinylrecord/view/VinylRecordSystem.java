package ec.edu.espe.vinylrecord.view;

import ec.edu.espe.vinylrecord.model.VinylRecord;
import java.util.Scanner;
import utils.VinylRecordManager;

/**
 *
 * @author Jose Leiton
 */
public class VinylRecordSystem {

    public static void main(String[] args) {
        VinylRecordManager manager = new VinylRecordManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the vinyl record:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the title of the vinyl record:");
        String title = scanner.nextLine();

        System.out.println("Enter the artist of the vinyl record:");
        String artist = scanner.nextLine();

        System.out.println("Enter the year of the vinyl record:");
        int year = scanner.nextInt();

        VinylRecord record = new VinylRecord(id, title, artist, year);
        manager.addRecord(record);

        System.out.println("Current Vinyl Records:");
        for (VinylRecord r : manager.getRecords()) {
            System.out.println(r);
        }

        scanner.close();
    }

}
