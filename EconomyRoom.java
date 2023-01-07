/**
 * Třída {@code EconomyRoom} představuje ekonomický pokoj v hotelu.
 * Je odvozena od třídy {@code Room} a rozšiřuje ji o informace o balkónu a vlastní ledničce.
 *
 * @author Nachtman Filip
 * @version 1.0
 */
public class EconomyRoom extends Room {

    // Označuje, zda má místnost balkon
    private boolean hasBalcony;

    // Označuje, zda má pokoj vlastní lednici
    private boolean hasOwnFridge;

    /**
     * Vytvoří nový objekt EconomyRoom s daným ID, číslem pokoje, velikostí, cenou za noc,
     * stav rezervace, dostupnost balkonu a dostupnost lednice.
     *
     * @param id id místnosti
     * @param roomNumber číslo místnosti v místnosti
     * @param size velikosti místnosti v metrech čtverečních
     * @param pricePerNight cena za noc v pokoji
     * @param reserved označuje, zda je místnost rezervována či nikoli
     * @param hasBalcony označuje, zda má pokoj balkon nebo ne
     * @param hasOwnFridge udává, zda má pokoj vlastní lednici nebo ne
     */
    public EconomyRoom(int id, int roomNumber, double size, double pricePerNight, boolean reserved,  boolean hasBalcony, boolean hasOwnFridge) {
        super(id, roomNumber, size, pricePerNight, reserved);
        this.hasBalcony = hasBalcony;
        this.hasOwnFridge = hasOwnFridge;
    }

    /**
     * Vrací, zda má pokoj balkon nebo ne.
     *
     * @return true, pokud má pokoj balkon, false jinak
     */
    public boolean isHasBalcony() {
        return hasBalcony;
    }

    /**
     * Nastavuje dostupnost balkonu místnosti.
     *
     * @param hasBalcony true, pokud má pokoj balkon, false jinak
     */
    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    /**
     * Vrací, zda má pokoj vlastní lednici nebo ne.
     *
     * @return true, pokud má pokoj vlastní lednici, false jinak
     */
    public boolean isHasOwnFridge() {
        return hasOwnFridge;
    }

    /**
     * Nastaví dostupnost chladničky v místnosti.
     *
     * @param hasOwnFridge true, pokud má pokoj vlastní lednici, false jinak
     * @return tento objekt EconomyRoom
     */
    public EconomyRoom setHasOwnFridge(boolean hasOwnFridge) {
        this.hasOwnFridge = hasOwnFridge;
        return this;
    }


    /**
     * Vrátí řetězcovou reprezentaci objektu EconomyRoom.
     *
     * @return reprezentaci řetězce objektu EconomyRoom
     */
    @Override
    public String toString() {
        return String.format("- Economy č. %d (%.1f m², %.1f Kč/noc, %s, %s s balkónem, %s s vlastní ledničkou)",
                getRoomNumber(), getSize(), getPricePerNight(), isReserved() ? "rezervováno" : "volné",
                isHasBalcony() ? "ano" : "ne", isHasOwnFridge() ? "ano" : "ne");
    }
}
