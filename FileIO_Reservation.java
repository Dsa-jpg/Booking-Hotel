import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída {@code FileIO_Reservation} obsahuje metody pro ukládání a načítání seznamu rezervací do/ze souboru.
 * Obslužná třída pro čtení a zápis rezervačních dat do a ze souboru.
 *
 * @author Nachtman Filip
 * @version 1.0
 */
public class FileIO_Reservation {

    /**
     * Načte rezervace ze souboru s daným názvem souboru.
     *
     * @param fileName název souboru, ze kterého se mají načíst rezervace
     * @return seznam rezervací přečtených ze souboru
     */
    public static List<Reservation> loadReservationsFromFile(String fileName) {
        List<Reservation> reservations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 6) {
                    // přeskočení řádku s chybným počtem hodnot
                    continue;
                }
                int roomNumber = Integer.parseInt(parts[0]);
                boolean reserved = Boolean.parseBoolean(parts[1]);
                String name = parts[2];
                String email = parts[3];
                LocalDate startDate = LocalDate.parse(parts[4]);
                LocalDate endDate = LocalDate.parse(parts[5]);

                reservations.add(new Reservation(
                        roomNumber,
                        reserved,
                        name,
                        email,
                        startDate,
                        endDate));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    /**
     * Uloží dané rezervace do souboru s daným názvem souboru.
     *
     * @param reservations rezervace k uložení do souboru
     * @param fileName název souboru, do kterého se mají rezervace uložit
     */
    public static void saveReservationsToFile(List<Reservation> reservations, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Reservation reservation : reservations) {
                writer.write(String.format("%d,%b,%s,%s,%s,%s",
                        reservation.getRoomNumber(),
                        reservation.isReserved(),
                        reservation.getName(),
                        reservation.getEmail(),
                        reservation.getStartDate(),
                        reservation.getEndDate()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
