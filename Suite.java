/**
 * Třída {@code Suite} představuje luxusní apartmá v hotelu.
 * Je odvozena od třídy {@code Room} a rozšiřuje ji o informace o počtu lůžek a whirlpoolu.
 *
 * @author Filip Nachtman
 * @version 1.0
 */
public class Suite extends Room {
    private int numBeds;
    private boolean hasJacuzzi;

    /**
     * Vytvoří nový objekt Suite s daným ID, číslem pokoje, velikostí, cenou za noc,
     * stav rezervace, počet lůžek a dostupnost jacuzzi.
     *
     * @param id id místnosti
     * @param roomNumber číslo místnosti v místnosti
     * @param size velikosti místnosti v metrech čtverečních
     * @param pricePerNight cena za noc v pokoji
     * @param reserved označuje, zda je místnost rezervována či nikoli
     * @param numBeds počet lůžek v apartmá
     * @param hasJacuzzi označuje, zda apartmá má jacuzzi nebo ne
     */
    public Suite(int id, int roomNumber, double size, double pricePerNight, boolean reserved, int numBeds, boolean hasJacuzzi) {
        super(id, roomNumber, size, pricePerNight, reserved);
        this.numBeds = numBeds;
        this.hasJacuzzi = hasJacuzzi;
    }

    /**
     * Vrátí počet lůžek v apartmá
     *
     * @return počet lůžek v apartmá
     */
    public int getNumBeds() {
        return numBeds;
    }

    /**
     * Nastavuje počet lůžek v apartmá.
     *
     * @param numBeds počet lůžek v apartmá
     */
    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    /**
     * Vrací, zda má apartmá vířivku nebo ne.
     *
     * @return true, pokud má apartmá jacuzzi, false jinak
     */
    public boolean isHasJacuzzi() {
        return hasJacuzzi;
    }

    /**
     * Nastavuje dostupnost jacuzzi v apartmá.
     *
     * @param hasJacuzzi true, pokud má apartmá jacuzzi, false jinak
     */
    public void setHasJacuzzi(boolean hasJacuzzi) {
        this.hasJacuzzi = hasJacuzzi;
    }

    /**
     * Vrátí řetězcovou reprezentaci objektu Suite.
     *
     * @return řetězcovou reprezentaci objektu Suite
     */
    @Override
    public String toString() {
        return String.format("- Suite č. %d (%.1f m², %.1f Kč/noc, %s, %d lůžek, %s s whirlpoolem)",
                getRoomNumber(), getSize(), getPricePerNight(), isReserved() ? "rezervováno" : "volné",
                getNumBeds(), isHasJacuzzi() ? "ano" : "ne");
    }
}
