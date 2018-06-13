package uk.ac.ebi.pride.utilities.mol;

import uk.ac.ebi.pride.utilities.exception.IllegalAminoAcidSequenceException;

import java.util.ArrayList;
import java.util.List;

import static uk.ac.ebi.pride.utilities.mol.NeutralLoss.WATER_LOSS;
import static uk.ac.ebi.pride.utilities.mol.NuclearParticle.PROTON;

/**
 * <p>Utility class for Molecule operations</p>
 *
 * @author Yasset Perez-Riverol
 *         Date: 24-sep-2010
 *         Time: 11:51:12
 */
public class MoleculeUtilities {
    /**
     * Converts a chart to an AminoAcid if it matches with one of them
     *
     * @param aa the chart to convert
     * @return a corresponding AminoAcid or null if there is not match
     */
    public static AminoAcid getAminoacid(char aa) {
        for (AminoAcid aminoAcid : AminoAcid.values()) {
            if (aa == aminoAcid.getOneLetterCode())
                return aminoAcid;
        }
        return null;
    }

    /**
     * Check if the sequence is valid in terms of the letters contained
     *
     * @param seq the sequence to check
     * @return true if the sequence is valid, false in other case
     */
    public static boolean isAminoAcidSequence(String seq) {
        for (int i = 0; i < seq.length(); i++) {
            char residue = seq.charAt(i);
            AminoAcid aminoAcid = getAminoacid(residue);
            if (aminoAcid == null) return false;
        }
        return true;
    }

    /**
     * Search within a mass window (inclusive).
     *
     * @param massRangeStart mass window start.
     * @param massRangeEnd   mass window end.
     * @param isMonoMass     choose between mono mass and average mass.
     * @return List<AminoAcid>  returns a list of qualified amino acids.
     */
    public static List<AminoAcid> searchForAminoAcid(double massRangeStart, double massRangeEnd, boolean isMonoMass) {
        List<AminoAcid> acs = new ArrayList<>();
        AminoAcid[] acEntries = AminoAcid.values();
        for (AminoAcid acEntry : acEntries) {
            double acMass = isMonoMass ? acEntry.getMonoMass() : acEntry.getAvgMass();
            if (acMass >= massRangeStart && acMass <= massRangeEnd) {
                acs.add(acEntry);
            }
        }
        return acs;
    }

    /**
     * Search and return a combination of amino acid according the number of residue specified.
     *
     * @param massRangeStart mass window start.
     * @param massRangeEnd   mass window end.
     * @param isMonoMass     choose between mono mass and average mass.
     * @param numOfResidue   number of residues.
     * @return List<Peptide>    list of peptides.
     */
    public static java.util.List<AminoAcidSequence> searchForPeptide(double massRangeStart, double massRangeEnd,
                                                           boolean isMonoMass, int numOfResidue) {
        java.util.List<AminoAcidSequence> aminoAcidSequences = new ArrayList<>();
        AminoAcid[] acEntries = AminoAcid.values();

        // create all combinations.
        for (int i = 0; i < numOfResidue; i++) {
            java.util.List<AminoAcidSequence> tmpAminoAcidSequences = new ArrayList<>();
            for (AminoAcid acEntry : acEntries) {
                if (i == 0) {
                    AminoAcidSequence aminoAcidSequence = new AminoAcidSequence(acEntry);
                    aminoAcidSequences.add(aminoAcidSequence);
                } else {
                    for (AminoAcidSequence aminoAcidSequence : aminoAcidSequences) {
                        AminoAcidSequence tmpAminoAcidSequence = new AminoAcidSequence();
                        tmpAminoAcidSequence.addAminoAcids(aminoAcidSequence.getAminoAcids());
                        tmpAminoAcidSequence.addAminoAcid(acEntry);
                        tmpAminoAcidSequences.add(tmpAminoAcidSequence);
                    }
                }
            }
            if (i != 0) {
                aminoAcidSequences = tmpAminoAcidSequences;
            }
        }

        // remove unqualified ones
        java.util.List<AminoAcidSequence> tmpAminoAcidSequences = new ArrayList<>();
        for (AminoAcidSequence aminoAcidSequence : aminoAcidSequences) {
            double mass = isMonoMass ? aminoAcidSequence.getMonoMass() : aminoAcidSequence.getAvgMass();
            if (mass >= massRangeStart && mass <= massRangeEnd) {
                tmpAminoAcidSequences.add(aminoAcidSequence);
            }
        }
        aminoAcidSequences = tmpAminoAcidSequences;
        return aminoAcidSequences;
    }

    /**
     * Calculates the theoretical mass taking into account the mono-mass of every AminoAcid
     * of the sequence and the array of masses passed
     *
     * @param seq    the sequence to calculate the mono-mass of every AminoAcid
     * @param masses the extra masses to add to the theorical mass (i.e. PTM or WATTER)
     * @return the theoretical mass
     * @throws IllegalAminoAcidSequenceException
     *          if the sequence is not valid
     */
    public static double calculateTheoreticalMass(String seq, double... masses)
            throws IllegalAminoAcidSequenceException {
        if (!isAminoAcidSequence(seq)) throw new IllegalAminoAcidSequenceException();

        double theoreticalMass = 0;
        for (int i = 0; i < seq.length(); i++) {
            char aa = seq.charAt(i);
            AminoAcid aminoAcid = getAminoacid(aa);
            theoreticalMass += aminoAcid.getMonoMass();
        }

        for (double mass : masses) {
            theoreticalMass += mass;
        }

        return theoreticalMass;
    }

    /**
     * Calculate the mono delta m/z for the given sequence, precursor m/z and precursor charge
     * If invalid, -1 will be returned.
     *
     * @param sequence        amino acid sequence
     * @param precursorMz     precursor m/z
     * @param precursorCharge precursor charge
     * @param ptmMasses       a list of ptm monoisotopic masses
     * @return Double monoisotopic delta m/z
     */
    public static Double calculateDeltaMz(String sequence, double precursorMz,
                                          int precursorCharge, List<Double> ptmMasses) {
        // this is double object because sometime double could be -1;
        Double deltaMass = null;

        // check the legality of the input arguments first
        if (precursorMz > 0 && precursorCharge != 0) {
            try {
                // create a new double array
                // attach water loss monoisotopic mass
                int length = ptmMasses.size();
                double[] masses = new double[length + 1];
                for (int i = 0; i < ptmMasses.size(); i++) {
                    masses[i] = ptmMasses.get(i);
                }
                masses[length] = WATER_LOSS.getMonoMass();

                // theoretical mass
                double theoreticalMass = MoleculeUtilities.calculateTheoreticalMass(sequence, masses);
                // precursor mass
                double precursorMass = Math.abs(precursorMz * precursorCharge - precursorCharge * PROTON.getMonoMass());

                // delta mass
                deltaMass = (precursorMass - theoreticalMass) / precursorCharge;
            } catch (IllegalAminoAcidSequenceException ex) {
                // do nothing
            }
        }

        return deltaMass;
    }

    /**
     * Calculate the mono delta m/z for the given sequence, based on theoretical and experimental mass
     *
     * @param precursorMz     precursor m/z
     * @param theoreticalMass a theoretical mass
     * @return Double monoisotopic delta m/z
     */
    public static Double calculateDeltaMz(double precursorMz, double theoreticalMass) {
        // this is double object because sometime double could be -1;
        Double deltaMass = null;

        // check the legality of the input arguments first
        if (precursorMz > 0 && theoreticalMass != 0) {
            try {
                // precursor mass
                // delta mass
                deltaMass = (precursorMz - theoreticalMass);
            } catch (IllegalAminoAcidSequenceException ex) {
                // do nothing
            }
        }

        return deltaMass;
    }



    /**
     * Calculate peptide's monoisotopic mass.
     *
     * @param aminoAcidSequence         peptide sequence.
     * @param modifications   a list of modifications.
     * @param massCorrections a list of mass corrections.
     * @return double   final peptide mass.
     */
    public static double calculatePeptideMonoMass(AminoAcidSequence aminoAcidSequence,
                                                  List<PTModification> modifications,
                                                  double... massCorrections) {
        return calculatePeptideMass(aminoAcidSequence, modifications, true, massCorrections);
    }

    /**
     * Calculate peptide's average mass.
     *
     * @param aminoAcidSequence         peptide sequence.
     * @param modifications   a list of modifications.
     * @param massCorrections a list of mass corrections.
     * @return double   final peptide mass.
     */
    public static double calculatePeptideAvgMass(AminoAcidSequence aminoAcidSequence,
                                                 List<PTModification> modifications,
                                                 double... massCorrections) {
        return calculatePeptideMass(aminoAcidSequence, modifications, false, massCorrections);
    }

    /**
     * Calculate peptide's mass
     * Note: all the modification masses, massCorrections are only going to be added.
     *
     * @param aminoAcidSequence         peptide sequence.
     * @param modifications   a list of modifications.
     * @param monoMass        whether to use mono masses or average masses.
     * @param massCorrections a list of mass corrections.
     * @return double   final peptide mass.
     */
    private static double calculatePeptideMass(AminoAcidSequence aminoAcidSequence,
                                               List<PTModification> modifications,
                                               boolean monoMass,
                                               double... massCorrections) {
        // calculate peptide mass
        double mass = monoMass ? aminoAcidSequence.getMonoMass() : aminoAcidSequence.getAvgMass();
        // add modifications
        if (modifications != null) {
            for (PTModification modification : modifications) {
                List<Double> massDeltas = monoMass ? modification.getMonoMassDeltas() : modification.getAvgMassDeltas();
                if (massDeltas != null && !massDeltas.isEmpty()) {
                    mass += massDeltas.get(0);
                }
            }
        }
        // add corrections
        for (double massCorrection : massCorrections) {
            mass += massCorrection;
        }
        return mass;
    }

    /**
     * calculate the sum of the mono-isotopic masses.
     *
     * @param masses a array of input masses.
     * @return double   mono-isotopic masses.
     */
    public static double calculateMonoMass(Mass... masses) {
        double mass = 0;
        for (Mass m : masses) {
            mass += m.getMonoMass();
        }
        return mass;
    }

    /**
     * calculate the sum of the average masses.
     *
     * @param masses a array of input masses.
     * @return double   average masses.
     */
    public static double calculateAvgMass(Mass... masses) {
        double mass = 0;
        for (Mass m : masses) {
            mass += m.getAvgMass();
        }
        return mass;
    }

    public static int calcMissedCleavages(String sequence) {

        //Always remove the last K or R from sequence
        sequence = sequence.replaceAll("[K|R]$", "");

        //We assume the hypothesis KR|P
        sequence = sequence.replaceAll("[K|R]P", "");
        int initialLength = sequence.length();

        sequence = sequence.replaceAll("[K|R]", "");
        return initialLength - sequence.length();
    }


    public static void main(String[] args) {
        Double delta = calculateDeltaMz("IKQIVBLWTR", 424.4242933333333, 3, new ArrayList<>());
        System.out.println(delta);
    }
}
