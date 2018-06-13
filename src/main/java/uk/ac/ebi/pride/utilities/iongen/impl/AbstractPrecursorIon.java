package uk.ac.ebi.pride.utilities.iongen.impl;

import uk.ac.ebi.pride.utilities.iongen.model.IonCleavageException;
import uk.ac.ebi.pride.utilities.iongen.model.PrecursorIon;
import uk.ac.ebi.pride.utilities.iongen.model.ProductIon;
import uk.ac.ebi.pride.utilities.mol.*;
import uk.ac.ebi.pride.utilities.iongen.ion.FragmentIonType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Implement most of methods of {@link PrecursorIon}. Extends {@link DefaultPeptideIon} which inherit
 * peptide ion methods: such as {@link uk.ac.ebi.pride.utilities.iongen.impl.DefaultPeptideIon#getMass()},
 * {@link uk.ac.ebi.pride.utilities.iongen.impl.DefaultPeptideIon#getMassOverCharge()}, and so on.
 *
 * @author Qingwei XU
 * @version 0.1-SNAPSHOT
 */
public abstract class AbstractPrecursorIon extends DefaultPeptideIon implements PrecursorIon {
    public AbstractPrecursorIon(Peptide peptide, int charge) {
        super(peptide, charge);
    }

    private Map<Integer, PTModification> getProductIonPTM(int start, int end, Map<Integer, PTModification> precursorPTM) {
        Map<Integer, PTModification> ptm = new HashMap<>();


        Iterator<Integer> it = precursorPTM.keySet().iterator();
        Integer position;
        PTModification modification;
        while (it.hasNext()) {
            position = it.next();
            modification = precursorPTM.get(position);

            // [start, end)
            if (position >= start && position < end) {
                ptm.put(position, modification);
            }
        }

        return ptm;
    }

    /**
     * A precursor ion can cleavage different type of product ions.
     *
     * @param type can not set null.
     * @param position from [1..peptide.length-1]
     * @param charge from [1..3], based on the precursor charge.
     * @return {@link ProductIon}, can not return a null.
     * @throws IonCleavageException, when user set position and charge overflow the range of setting.
     * @throws NullPointerException, when type is null.
     */
    @Override
    public ProductIon getProductIon(ProductIonType type, int position, int charge) throws IonCleavageException {
        if (type == null) {
            throw new NullPointerException("Product ion type can not be null!");
        }

        if (position <= 0) {
            throw new IonCleavageException("The product ion cleavages position (" + position + ") cannot be less than 1");
        } else if (position >= getPeptide().getLength()) {
            throw new IonCleavageException("The product ion cleavages position (" + position + ") cannot be greater then the precursor ion length (" + getPeptide().getLength() + "). ");
        }

        if (charge <= 0 || charge > 3) {
            throw new IonCleavageException("The product ion charge(" + charge + ") should be from 1 to 3.");
        } else if (charge > getCharge()) {
            throw new IonCleavageException("The product ion charge(" + charge + ") should be less or equal to precursor ion charge(" + getCharge() + ")");
        }

        List<AminoAcid> acidList = getPeptide().getAminoAcids();
        Group n_terminal = getPeptide().getNTerminalGroup();
        Group c_terminal = getPeptide().getCTerminalGroup();
        int length = getPeptide().getLength();

        int start;
        int end;
        List<AminoAcid> prodAcidList;
        Peptide prodPeptide;
        ProductIon productIon;
        Map<Integer, PTModification> ptm;

        FragmentIonType fragmentIonType = type.getGroup();
        if (fragmentIonType.equals(FragmentIonType.A_ION) || fragmentIonType.equals(FragmentIonType.B_ION) || fragmentIonType.equals(FragmentIonType.C_ION)) {
            start = 0;
            end = position;
            prodAcidList = acidList.subList(start, end);
            ptm = getProductIonPTM(start, end, getPeptide().getPTM());
            prodPeptide = new Peptide(prodAcidList, n_terminal, null, ptm);
            productIon = new DefaultProductIon(this, type, position, prodPeptide, charge);
        } else if (fragmentIonType.equals(FragmentIonType.X_ION) || fragmentIonType.equals(FragmentIonType.Y_ION) || fragmentIonType.equals(FragmentIonType.Z_ION)) {
            start = position;
            end = length;
            prodAcidList = acidList.subList(start, end);
            // there exists c-terminal modification, which position equals the sequence length.
            ptm = getProductIonPTM(start, end + 1, getPeptide().getPTM());
            prodPeptide = new Peptide(prodAcidList, null, c_terminal, ptm);
            productIon = new DefaultProductIon(this, type, length - position, prodPeptide, charge);
        } else {
            throw new IonCleavageException(type.getGroup() + " is not A, B, C, X, Y, Z ions");
        }

        return productIon;
    }

}
