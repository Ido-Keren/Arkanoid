package Movement.HitAffects;

/**
 * Interface for all Notifiers of a hit.
 */
public interface HitNotifier {

    /**
     * Method adds hl as a listener to hit events.
     *
     * @param hl the listener to add to hit events.
     */
    void addHitListener(HitListener hl);

    /**
     * Method removes hl from the list of listeners to hit events.
     *
     * @param hl the listener to remove from hit events.
     */
    void removeHitListener(HitListener hl);
}
