package uk.ac.ebi.pride.utilities.iongen.model;

/**
 * During the process of {@link PrecursorIon} cleavage into different type of {@link ProductIon}.
 * User input errors such as the cleavage position, {@link uk.ac.ebi.pride.utilities.iongen.model.ProductIon#getCharge()},
 * , and so on.
 *
 *
 * Creator: Qingwei-XU
 * Date: 31/10/12
 * Version: 0.1-SNAPSHOT
 */

public class IonCleavageException extends Exception {
    public IonCleavageException(String message) {
        super(message);
    }
}
