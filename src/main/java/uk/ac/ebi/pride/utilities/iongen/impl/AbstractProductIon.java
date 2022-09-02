package uk.ac.ebi.pride.utilities.iongen.impl;

import uk.ac.ebi.pride.utilities.iongen.model.PrecursorIon;
import uk.ac.ebi.pride.utilities.iongen.model.ProductIon;
import uk.ac.ebi.pride.utilities.mol.Group;
import uk.ac.ebi.pride.utilities.mol.Peptide;
import uk.ac.ebi.pride.utilities.mol.ProductIonType;

/**
 * Implement most of methods of {@link ProductIon} interface. This class implements Comparable interface, which
 * can be storage into sorted collections based on cleavage position ascending order. This class Extends
 * {@link DefaultPeptideIon} which inherit peptide ion methods: such as
 * {@link uk.ac.ebi.pride.utilities.iongen.impl.DefaultPeptideIon#getMass()},
 * {@link uk.ac.ebi.pride.utilities.iongen.impl.DefaultPeptideIon#getMassOverCharge()}, and so on.
 *
 * @author Qingwei XU
 * @version 0.1-SNAPSHOT
 */
public abstract class AbstractProductIon extends DefaultPeptideIon implements ProductIon, Comparable<ProductIon> {
    private final PrecursorIon precursorIon;
    private final ProductIonType type;
    /**
     * the position from [1..precursor.peptide.length-1]
     */
    private final int position;

    /**
     * Create product ion instance. There are some constraints among the {@link PrecursorIon},
     * {@link ProductIonType}, cleavage position, and the {@link ProductIon}.
     * <P>
     *     Notice: Not suggest user to create a instance by calling construct method directly.
     * </P>
     * Reference {@link AbstractPrecursorIon#getProductIon(uk.ac.ebi.pride.utilities.mol.ProductIonType, int, int)}
     * see how to generate the product ion.
     *
     * @param precursorIon should not set null! Otherwise throw NullPointerException.
     * @param type should not set null! Otherwise throw NullPointerException.
     * @param position from [1..precursor.peptide.length-1]
     * @param peptide product ion peptide. should not set null! Otherwise throw NullPointerException.
     * @param charge from [1..3], should not great than precursor ion charges.
     */
    public AbstractProductIon(PrecursorIon precursorIon, ProductIonType type, int position, Peptide peptide, int charge) {
        //based on DefaultPeptideIon create ProductIon.
        super(peptide, charge);

        if (precursorIon == null) {
            throw new NullPointerException("Precursor ion can not set null!");
        }

        if (type == null) {
            throw new NullPointerException("Product ion type can not set null!");
        }

        // absolute the charges of precursor ion and product ion.
        int precursorZ = precursorIon.getCharge() > 0 ? precursorIon.getCharge() : precursorIon.getCharge() * -1;
        int productZ = charge > 0 ? charge : charge * -1;

        if (precursorZ <= 0) {
            throw new IllegalArgumentException("precursor ion charge should not less than zero! " + precursorIon);
        }

        if (productZ > 3) {
            throw new IllegalArgumentException("product ion change (" + productZ + ") should not great than 3!");
        }

        if (productZ > precursorZ) {
            throw new IllegalArgumentException("product ion change (" + productZ + ") should not great than precursor ion (" + precursorZ + ")");
        }


        this.precursorIon = precursorIon;
        this.type = type;
        this.position = position;
    }

    @Override
    public ProductIonType getType() {
        return type;
    }

    public PrecursorIon getPrecursorIon() {
        return precursorIon;
    }

    @Override
    public int getPosition() {
        return position;
    }

    /**
     * Based on ascending order of product ion cleavage position.
     */
    @Override
    public int compareTo(ProductIon o) {
        return getPosition() - o.getPosition();
    }

    @Override
    public double getMass() {
        double mass = super.getMass();

        switch (getType()) {
            case B:
                mass = mass - Group.H.getMass();
                break;
            case B_H2O:
                mass = mass - Group.H.getMass() - Group.H2O.getMass();
                break;
            case B_NH3:
                mass = mass - Group.H.getMass() - Group.NH3.getMass();
                break;
            case Y:
                mass = mass + Group.H.getMass();
                break;
            case Y_H2O:
                mass = mass + Group.H.getMass() - Group.H2O.getMass();
                break;
            case Y_NH3:
                mass = mass + Group.H.getMass() - Group.NH3.getMass();
                break;
            case A:
                mass = mass - Group.H.getMass() - Group.CO.getMass();
                break;
            case A_H2O:
                mass = mass - Group.H.getMass() - Group.CO.getMass() - Group.H2O.getMass();
                break;
            case A_NH3:
                mass = mass - Group.H.getMass() - Group.CO.getMass() - Group.NH3.getMass();
                break;
            case X:
                mass = mass - Group.H.getMass() + Group.CO.getMass();
                break;
            case X_H2O:
                mass = mass - Group.H.getMass() + Group.CO.getMass() - Group.H2O.getMass();
                break;
            case X_NH3:
                mass = mass - Group.H.getMass() + Group.CO.getMass() - Group.NH3.getMass();
                break;
            case C:
                mass = mass + Group.NH3.getMass() - Group.H.getMass() ;
                break;
            case C_H2O:
                mass = mass + Group.NH3.getMass() - Group.H.getMass() - Group.H2O.getMass();
                break;
            case C_NH3:
                mass = mass + Group.NH3.getMass() - Group.H.getMass() - Group.NH3.getMass();
                break;
            case Z:
                mass = mass - Group.NH.getMass() + Group.H.getMass() ;
                break;
            case Z_H2O:
                mass = mass - Group.NH.getMass() + Group.H.getMass() - Group.H2O.getMass();
                break;
            case Z_NH3:
                mass = mass - Group.NH.getMass() + Group.H.getMass() - Group.NH3.getMass();
                break;
        }

        return mass;
    }
}
