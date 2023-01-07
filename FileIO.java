import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída {@code FileIO} obsahuje metody pro ukládání a načítání seznamu pokojů do/ze souboru.
 *
 * @author Nachtman Filip
 * @version 1.0
 */
public class FileIO {

    private static final int SUITE_ID = 0;
    private static final int ECONOMY_ID = 1;

    /**
     *
     *Metoda pro ukládání seznamu pokojů do souboru.
     *@param rooms seznam pokojů, které se mají uložit do souboru
     *@param fileName název souboru, do kterého se pokoje ukládají
     */
    public static void saveRoomsToFile(List<Room> rooms, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Room room : rooms) {
                if (room instanceof Suite) {
                    Suite suite = (Suite) room;
                    writer.write(String.format("%d,%d,%f,%f,%b,%d,%b",
                            SUITE_ID,
                            suite.getRoomNumber(),
                            suite.getSize(),
                            suite.getPricePerNight(),
                            suite.isReserved(),
                            suite.getNumBeds(),
                            suite.isHasJacuzzi()));


                } else if (room instanceof EconomyRoom) {
                    EconomyRoom economyRoom = (EconomyRoom) room;
                    writer.write(String.format("%d,%d,%f,%f,%b,%b,%b",
                            ECONOMY_ID,
                            economyRoom.getRoomNumber(),
                            economyRoom.getSize(),
                            economyRoom.getPricePerNight(),
                            economyRoom.isReserved(),
                            economyRoom.isHasBalcony(),
                            economyRoom.isHasOwnFridge()));


                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *Metoda pro načtení seznamu pokojů ze souboru.
     *@param fileName název souboru, ze kterého se pokoje načítají
     *@return seznam pokojů, které byly načteny ze souboru
     */
    public static List<Room> loadRoomsFromFile(String fileName) {
        List<Room> rooms = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 7 && parts.length != 8) {
                    // přeskočení řádku s chybným počtem hodnot
                    continue;
                }
                int id = Integer.parseInt(parts[0]);
                int roomNumber = Integer.parseInt(parts[1]);
                double size = Double.parseDouble(parts[2]);
                double pricePerNight = Double.parseDouble(parts[3]);
                boolean reserved = Boolean.parseBoolean(parts[4]);
                if (id == SUITE_ID) {
                    rooms.add(new Suite(
                            id,
                            roomNumber,
                            size,
                            pricePerNight,
                            reserved,
                            Integer.parseInt(parts[5]),
                            Boolean.parseBoolean(parts[6])));

                } else if (id == ECONOMY_ID) {
                    rooms.add(new EconomyRoom(
                            id,
                            roomNumber,
                            size,
                            pricePerNight,
                            reserved,
                            Boolean.parseBoolean(parts[5]),
                            Boolean.parseBoolean(parts[6])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }

}
