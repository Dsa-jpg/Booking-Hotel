import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

/**
 * Třída {@code Reservation} představuje rezervaci pokoje v hotelu.
 * Obsahuje informace o čísle pokoje, rezevraci a kdy byla udělaná rezevrace.
 *
 * @author Filip Nachtman
 * @version 1.0
 */
public class Reservation {

    /**
     * Konstanta, která představuje počet dní, za které se považuje jedna noc.
     * Pro účely programu se předpokládá, že jedna noc trvá přesně 1 den.
     */
    private static final double DAYS_PER_NIGHT = 1;

    /**
     * Číslo pokoje, který je rezervován.
     */
    private int roomNumber;

    /**
     * Označení, zda je pokoj rezervovaný nebo volný.
     */
    private boolean reserved;

    /**
     * Jméno osoby, která pokoj rezervovala.
     */
    private String name;

    /**
     * E-mailová adresa osoby, která pokoj rezervovala.
     */
    private String email;

    /**
     * Datum, od kterého je pokoj rezervovaný.
     */
    private LocalDate startDate;

    /**
     * Datum, do kterého je pokoj rezervovaný.
     */
    private LocalDate endDate;

    /**
     * Vytvoří nový objekt Rezervace s daným číslem pokoje, stavem rezervace,
     * datum zahájení a datum ukončení.
     *
     * @param roomNumber číslo pokoje pro rezervaci
     * @param reserved označuje, zda je místnost rezervována či nikoli
     * @param name jméno člověka, co zadal rezervaci
     * @param email e-mail člověka, co zadal rezervaci
     * @param startDate datum zahájení rezervace
     * @param endDate datum ukončení rezervace
     */
    public Reservation(int roomNumber, boolean reserved,String name, String email, LocalDate startDate, LocalDate endDate) {
        this.roomNumber = roomNumber;
        this.reserved = reserved;
        this.name = name;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Vrátí číslo pokoje pro rezervaci.
     *
     * @return  číslo pokoje pro rezervaci
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Vrátí hodnotu, která označuje, zda je pokoj rezervovaný nebo volný.
     *
     * @return true, pokud je pokoj rezervovaný, false, pokud je volný.
     */
    public boolean isReserved() {
        return true;
    }

    /**
     * Vrátí datum, od kterého je pokoj rezervovaný.
     *
     * @return Datum začátku rezervace pokoje jako objekt typu LocalDate.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Vrátí datum, do kterého je pokoj rezervovaný.
     *
     * @return Datum konce rezervace pokoje jako objekt typu LocalDate.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Nastaví hodnotu, která označuje, zda je pokoj rezervovaný nebo ne
     *
     * @param reserved pokud je rezervovaný vrací true, jinak false
     */
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    /**
     * Vratí jméno osoby, co zadala rezervaci
     *
     * @return name jméno osoby, co zadala rezervaci
     */
    public String getName() {
        return name;
    }

    /**
     * Vratí e-mail, osoby, co zadala rezervaci
     *
     * @return email  osoby, co zadala rezervaci
     */
    public String getEmail() {
        return email;
    }

    /**
     * Vytvoří novou rezervaci pokoje.
     * Uživatel vybere číslo pokoje, zadá své jméno a email, a zvolí datum rezervace.
     * Po úspěšném vytvoření rezervace se změní stav pokoje na rezervovaný a přidá se do seznamu rezervací.
     *
     * @param rooms Seznam pokojů, ze kterého je možné vybrat pokoj pro rezervaci.
     * @param reservations Seznam rezervací, do kterého se přidá nová rezervace.
     * @throws IOException Pokud nastane chyba při vstupu/výstupu dat.
     */
    public static void makeReservation(List<Room> rooms, List<Reservation> reservations) throws IOException {
        // zobrazit seznam pokojů a vybrat číslo pokoje, který chce uživatel rezervovat
        System.out.println("Vyberte číslo pokoje, který chcete rezervovat:");
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            System.out.printf("%d) %s\n", i + 1, room);
        }
        int roomNumber = readInt("Zadejte číslo pokoje: ", 1, rooms.size());
        Room selectedRoom = rooms.get(roomNumber - 1);
        double pricePerDay = selectedRoom.getPricePerNight() / DAYS_PER_NIGHT;

        // ověření, zda je pokoj volný
        if (selectedRoom.isReserved()) {
            System.out.println("Vybraný pokoj je již rezervován.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Zadejte jméno: ");
        String name = scanner.nextLine();

        System.out.print("Zadejte email (jan.novak@seznam.cz): ");
        String email = scanner.nextLine();

        // zadání data rezervace
        LocalDate startDate = readDate("Zadejte počátek rezervace (dd.mm.yyyy): ");
        if (startDate.isBefore(LocalDate.now())) {
            System.out.println("Nelze vybrat datum v minulosti!");
            return;
        }
        LocalDate endDate = readDate("Zadejte konec rezervace (dd.mm.yyyy): ");
        if (endDate.isBefore(startDate)){
            System.out.println("Konec rezevrace nesmí být před začátkem rezervace.");
            return;
        }
        // ošetření, aby rezervace nebyla v minulosti
        if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
            System.out.println("Rezervace nesmí být v minulosti.");
            return;
        }
        // celková délka rezervace
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        // vytvoření nové rezervace a změna stavu pokoje na rezervovaný

        Reservation reservation = new Reservation(selectedRoom.getRoomNumber(), selectedRoom.isReserved(),name, email, startDate, endDate);
        selectedRoom.setReserved(true);
        // přidání rezervace do seznamu rezervací
        reservations.add(reservation);

        // výpis informací o rezervaci
        System.out.println();
        System.out.println("Rezervace úspěšně vytvořena:");
        System.out.println();
        System.out.println("┌-----------------------------------");
        System.out.println("| Vaše rezervace ");
        System.out.println("| Jméno : " + reservation.getName());
        System.out.println("| E-mail : " + reservation.getEmail());
        System.out.println("| Číslo rezervace : " + reservation.getRoomNumber());
        System.out.println("| Check - in : " + reservation.getStartDate());
        System.out.println("| Check - out : " + reservation.getEndDate());
        System.out.println("| Cena za pobyt v pokoji č." + reservation.getRoomNumber() + " je : "+ pricePerDay*days + "CZK.");
        System.out.println("└-----------------------------------");


        // otevření souboru rooms.txt pro čtení a zápis
        RandomAccessFile raf = new RandomAccessFile("rooms.txt", "rw");

        // vyhledání pozice řádku s vybraným pokojem
        int lineNumber = roomNumber - 1; // počítáme od nuly
        long position = 0; // počáteční pozice
        String line;
        while (lineNumber > 0 && (line = raf.readLine()) != null) {
            position = raf.getFilePointer();
            lineNumber--;
        }

        // přeskočení řádku s chybným počtem hodnot
        if ((line = raf.readLine()) == null || line.split(",").length != 7 && line.split(",").length != 8) {
            raf.close();
            return;
        }

        // zápis změněné hodnoty isReserved do souboru
        raf.seek(position); // nastavení pozice na začátek řádku
        raf.writeBytes(line.replace("false", "true")); // nahrazení hodnoty "false" za "true"

        raf.close();

    }

    /**
     * Načte od uživatele datum ve formátu dd.MM.yyyy a vrátí ho jako objekt typu {@code LocalDate }.
     * Při neplatném zadání datumu se zobrazí chybové hlášení a uživatel bude požádán o opakování vstupu.
     *
     * @param prompt Text, který se zobrazí před vstupem datumu.
     * @return Načtené datum jako objekt typu `LocalDate`.
     */
    public static LocalDate readDate(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Neplatné datum, zadejte datum ve formátu dd.mm.yyyy.");
            }
        }
    }

    /**
     * Načte od uživatele celé číslo a vrátí ho jako hodnotu typu int.
     * Při neplatném zadání čísla se zobrazí chybové hlášení a uživatel bude požádán o opakování vstupu.
     *
     * @param prompt Text, který se zobrazí před vstupem čísla.
     * @param min Minimální povolená hodnota vstupního čísla.
     * @param max Maximální povolená hodnota vstupního čísla.
     * @return Načtené číslo jako hodnota typu int.
     */
    public static int readInt(String prompt, int min, int max) {
        // kód pro načtení celého čísla od uživatele
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        int input = scanner.nextInt();
        return input;
    }

    /**
     * Vrátí řetězcovou reprezentaci objektu Reservation.
     *
     * @return řetězcovou reprezentaci objektu Reservation
     */
    @Override
    public String toString() {
        return String.format("-Reservation : room č. %d ( %s, na jméno - %s, email osoby - %s, Začátek rezervace - %s, Konec rezervace - %s)",
                roomNumber, reserved ? "rezervováno" : "volné", name, email, startDate, endDate);
    }

}


