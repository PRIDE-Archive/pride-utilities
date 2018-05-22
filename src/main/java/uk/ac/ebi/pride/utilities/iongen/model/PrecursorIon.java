package uk.ac.ebi.pride.utilities.iongen.model;

import uk.ac.ebi.pride.utilities.mol.ProductIonType;

/**
 * A kind of {@link PeptideIon}, which can cleavage different types of product ions.
 *
 * @see uk.ac.ebi.pride.utilities.iongen.impl.AbstractPrecursorIon
 *
 * @author Qingwei XU
 * @version 0.1-SNAPSHOT
 */
public interface PrecursorIon extends PeptideIon {
    /**
     * A precursor ion can cleavage different type of product ions.
     *
     * @param type can not set null, otherwise throw NullPointerException.
     * @param position from [1..peptide.length-1]
     * @param charge from [1..3], based on the precursor charge.
     * @return {@link ProductIon}, can not return a null.
     * @throws IonCleavageException, when user set position and charge overflow the range of setting.
     */
    ProductIon getProductIon(ProductIonType type, int position, int charge) throws IonCleavageException;
}
