import java.io.Serializable;
/**
 * Třída {@code Room} představuje pokoj v hotelu.
 * Obsahuje informace o čísle pokoje, velikosti, ceně za nocleh a rezervaci.
 * Implementuje rozhraní `Serializable`, aby bylo možné pokoje ukládat do souboru.
 *
 * @author Filip Nachtman
 * @version 1.0
 */
public class Room implements Serializable {

    // Číslo pokoje pro daný pokoj
    private int roomNumber;

    // Velikost místnosti v metrech čtverečních
    private double size;

    // Cena za noc za pokoj
    private double pricePerNight;

    // Označuje, zda je místnost rezervována nebo ne
    private boolean reserved;

    // Jedinečný identifikátor místnosti
    private int id;

    /**
     * Vytvoří nový pokojový objekt s daným číslem pokoje, velikostí, cenou za noc,
     * a stav rezervace.
     *
     * @param id jedinečný identifikátor místnosti
     * @param roomNumber číslo pokoje pro pokoj
     * @param size velikosti místnosti v metrech čtverečních
     * @param pricePerNight cena za noc za pokoj
     * @param reserved označuje, zda je místnost rezervována či nikoli
     */
    public Room(int id,int roomNumber, double size, double pricePerNight, boolean reserved) {
        this.roomNumber = roomNumber;
        this.size = size;
        this.pricePerNight = pricePerNight;
        this.reserved = reserved;

    }

    /**
     * Vrátí číslo pokoje.
     *
     * @return Číslo pokoje.
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Nastaví číslo pokoje.
     *
     * @param roomNumber Číslo pokoje.
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Vrátí velikost pokoje v metrových čtverečních.
     *
     * @return Velikost pokoje v metrových čtverečních.
     */
    public double getSize() {
        return size;
    }

    /**
     * Nastaví velikost pokoje v metrových čtverečních.
     *
     * @param size Velikost pokoje v metrových čtverečních.
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Vrátí cenu za noc
     *
     * @return cena za noc
     */
    public double getPricePerNight() {
        return pricePerNight;
    }

    /**
     * Nastaví cenu pokoje.
     *
     * @param pricePerNight cena pokoje za jednu noc.
     */
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    /**
     * Vrátí zda je pokoj rezervován
     *
     * @return pokud je rezerován tak vrací true jinak false
     */
    public boolean isReserved() {
        return reserved;
    }

    /**
     * Nastaví rezevraci pokoje
     *
     * @param reserved pokud je volný tak true jinak false
     */
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    /**
     * Vrátí jedinečný identifikátor místnosti
     *
     * @return jedinečný identifikátor místnosti
     */
    public int getId() {
        return id;
    }

    /**
     * Nastaví jedinečný identifikátor místnosti
     *
     * @param id jedinečný identifikátor místnosti
     */
    public Room setId(int id) {
        this.id = id;
        return this;
    }

}
