import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Třída {@code Main} obsahuje rozhodovací menu pro výběr pokoje podle typu, zda je rezervován, či podle m². Dále zde mužeme zadat pokoj do systému a vytvořít rezervaci .
 *
 * @author Nachtman Filip
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // načtení seznamu pokojů ze souboru
        List<Room> rooms = FileIO.loadRoomsFromFile("rooms.txt");
        List<Reservation> reservations = FileIO_Reservation.loadReservationsFromFile("reservations.txt");

        // vytvoření scanneru pro vstup od uživatele
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                // výpis možností filtrování

                System.out.println("┌-----------------------------------------------------------------------------┐");
                System.out.println("|                    {Vyberte si z následujících možností}                    |");
                System.out.println("├-----------------------------------------------------------------------------┤");
                System.out.println("|1|                   {Zobrazit všechny pokoje v databázi}                    |");
                System.out.println("|2|                      {Zobrazit všechny volné pokoje}                      |");
                System.out.println("|3|                    {Zobrazit všechny obsazené pokoje}                     |");
                System.out.println("|4|                   {Zobrazit pokoje podle velikosti v m²}                  |");
                System.out.println("|5|                       {Zobrazit pokoje podle typu}                        |");
                System.out.println("|6|                          {Zadat novou rezervaci}                          |");
                System.out.println("|7|                   {Zobrazit všechny rezervace v databázi}                 |");
                System.out.println("|8|                                   {Konec}                                 |");
                System.out.println("└-----------------------------------------------------------------------------┘\n");

                // přečtení vstupu od uživatele
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {


                    case 1:
                        // zobrazení všech pokojů
                        System.out.println("Seznam všech pokojů:");
                        for (Room room : rooms) {
                            if (room instanceof Suite) {
                                Suite suite = (Suite) room;

                            }
                            if (room instanceof EconomyRoom) {
                                EconomyRoom economyRoom = (EconomyRoom) room;
                            }
                        }


                    case 2:
                        // zobrazení volných pokojů
                        for (Room room : rooms) {
                            if (!room.isReserved()) {
                                System.out.println(room);
                            }
                        }
                        System.out.println();
                        break;

                    case 3:
                        // zobrazení obsazených pokojů
                        for (Room room : rooms) {
                            if (room.isReserved()) {
                                System.out.println(room);
                            }
                        }
                        System.out.println();
                        break;

                    case 4:
                        // zobrazení pokojů podle velikosti
                        while (true) {
                            try {
                                System.out.println("Zadejte minimální velikost pokoje:");
                                double minSize = scanner.nextDouble();
                                System.out.println("Zadejte maximální velikost pokoje:");
                                double maxSize = scanner.nextDouble();
                                for (Room room : rooms) {
                                    if (room.getSize() >= minSize && room.getSize() <= maxSize) {
                                        System.out.println(room);
                                    }
                                }
                                System.out.println();
                                break;  // opustí loop pokud je vstup validní
                            } catch (InputMismatchException e) {
                                System.out.println("Chyba: Neplatný vstup. Zadejte číslo.");
                                scanner.nextLine();  // bude se opakovat dokud nebude userInput true
                            }
                        }
                        break;

                    case 5:
                        // Zobrazit pokoje podle typu
                        while (true) {
                            try {
                                System.out.print("Zadejte typ pokoje (suite, economy): ");
                                String type = scanner.nextLine().toLowerCase();
                                if (!"suite".equals(type) && !"economy".equals(type)) {
                                    throw new IllegalArgumentException("Neplatný typ pokoje: " + type);
                                }
                                for (Room room : rooms) {
                                    if (room instanceof Suite && "suite".equals(type)) {
                                        System.out.println(room);
                                    } else if (room instanceof EconomyRoom && "economy".equals(type)) {
                                        System.out.println(room);
                                    }
                                }
                                System.out.println();
                                break;  // opustí loop pokud je vstup validní
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                // bude se opakovat dokud nebude userInput true
                            }
                        }
                        break;

                    case 6:
                        // udělání rezervace -> uložení do druhého txt souboru (reservations.txt)
                        Reservation.makeReservation(rooms, reservations);
                        FileIO_Reservation.saveReservationsToFile(reservations, "reservations.txt");
                        System.out.println();
                        break;


                    case 7:
                        // výpis všech rezervací zatím udělaných
                        System.out.println("Seznam všech rezervací:");
                        for (Reservation reservation : reservations) {
                            System.out.println(reservation.toString());
                        }
                        break;

                    case 8:
                        // konec programu
                        return;

                    default:
                        // neplatná volba
                        System.out.println("Neplatná volba");
                }
            }catch (InputMismatchException e) {
                System.out.println("Neplatný vstup !!!!!!!! Zadejte číslo:");
                scanner.nextLine(); // vyčistit řádek
        }
      }

    }
}




