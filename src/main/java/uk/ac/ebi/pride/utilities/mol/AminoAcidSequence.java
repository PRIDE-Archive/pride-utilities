package uk.ac.ebi.pride.utilities.mol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Peptide represents a list of <cod> AminoAcid </code>
 *
 * @author rwang
 * Date: 21-Oct-2010
 * Time: 13:56:07
 */
public class AminoAcidSequence implements Mass {

    /** Stores all the amino acids */
    private final java.util.List<AminoAcid> aminoAcids;

    /**
     * Default constructor, this will make a empty Peptide object
     */
    public AminoAcidSequence() {
        this(new AminoAcid[0]);
    }

    /**
     * Constructor to ake a Peptide object with specified amino acids.
     *
     * @param residues  a list of amino acids
     */
    public AminoAcidSequence(AminoAcid... residues) {
        aminoAcids = new ArrayList<>();
        aminoAcids.addAll(Arrays.asList(residues));
    }

    /**
     * Returns a new list of amino acids
     *
     * @return a list of amino acids
     */
    public List<AminoAcid> getAminoAcids() {
        return new ArrayList<>(aminoAcids);
    }

    /**
     * Returns the amino acid with specified index
     *
     * @param index index is zero based.
     * @return AminoAcid    return null when there is no index.
     */
    public AminoAcid getAminoAcid(int index) {
        if (index >= 0 && aminoAcids.size() > index) {
            return aminoAcids.get(index);
        } else {
            return null;
        }
    }

    /**
     * Return the number of amino acids
     * @return int   the number of amino acids
     */
    public int getNumberOfAminoAcids() {
        return aminoAcids.size();
    }

    /**
     * Add a new amino acid to the end of the Peptide.
     *
     * @param residue   amino acid
     */
    public void addAminoAcid(AminoAcid residue) {
        if (residue == null) {
            throw new IllegalArgumentException("Can not add null amino acid to peptide");
        } else {
            aminoAcids.add(residue);
        }
    }

    /**
     * Add a collection of amino acids to the end of the Peptide.
     *
     * @param residues  a collection of amino acids
     */
    public void addAminoAcids(Collection<AminoAcid> residues) {
        if (residues == null) {
            throw new IllegalArgumentException("Can not add null collection of amino acids to peptide");
        } else {
            aminoAcids.addAll(residues);
        }
    }

    /**
     * Remove all amino acid
     */
    public void removeAll() {
        aminoAcids.clear();
    }

    /**
     * Remove amino acid at the index.
     *
     * @param index index of the amino acid
     */
    public void remove(int index) {
        if (index >= 0 && aminoAcids.size() > index) {
            aminoAcids.remove(index);
        }
    }

    /**
     * Get the sum of average mass.
     *
     * @return double   average mass of the peptide
     */
    public double getAvgMass() {
        double avgMass = 0;

        for (AminoAcid aminoAcid : aminoAcids) {
            avgMass += aminoAcid.getAvgMass();
        }

        return avgMass;
    }

    /**
     * Get the sum of monoisotopic mass.
     *
     * @return double   monoisotopic mass of the peptide.
     */
    public double getMonoMass() {
        double monoMass = 0;

        for (AminoAcid aminoAcid : aminoAcids) {
            monoMass += aminoAcid.getMonoMass();
        }

        return monoMass;
    }

    /**
     * Return the length of the peptide.
     *
     * @return int  the length of the peptide
     */
    public int getLength() {
        return aminoAcids.size();
    }

    /**
     * Get the string of peptide in three letter format.
     *
     * @return String   three letter code string of the peptide.
     */
    public String getThreeLetterCodeString() {
        StringBuilder code = new StringBuilder();

        for (AminoAcid aminoAcid : aminoAcids) {
            code.append(aminoAcid.getThreeLetterCode());
        }

        return code.toString();
    }

    /**
     * Get the string of peptide in one letter format.
     *
     * @return String   one letter code string of the peptide.
     */
    public String getOneLetterCodeString() {
        StringBuilder code = new StringBuilder();

        for (AminoAcid aminoAcid : aminoAcids) {
            code.append(aminoAcid.getOneLetterCode());
        }

        return code.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AminoAcidSequence)) return false;

        AminoAcidSequence aminoAcidSequence = (AminoAcidSequence) o;

        return !(aminoAcids != null ? !aminoAcids.equals(aminoAcidSequence.aminoAcids) : aminoAcidSequence.aminoAcids != null);

    }

    @Override
    public int hashCode() {
        return aminoAcids != null ? aminoAcids.hashCode() : 0;
    }

    /**
     * This method calls getOneLetterCodeString()
     * @return String   peptide string.
     */
    @Override
    public String toString() {
        return getOneLetterCodeString();
    }
}
