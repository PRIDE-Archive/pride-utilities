package uk.ac.ebi.pride.utilities.iongen.ion;

import uk.ac.ebi.pride.utilities.mol.NeutralLoss;

/**
 * Collection of methods for FragmentIonType
 * <p/>
 * @author rwang
 */
public class FragmentIonUtilities {
    /**
     * Use pride ontology to map ion type.
     *
     * @param ionType pride ontology accession or the name of the ion.
     * @return IonType  ion type.
     */
    public static FragmentIonType getFragmentIonType(String ionType) {
        if (ionType == null) {
            return FragmentIonType.NON_IDENTIFIED_ION;
        }
        ionType = ionType.toLowerCase();

        if ("pride:0000233".equals(ionType) || ionType.contains("a ion")) {
            return FragmentIonType.A_ION;
        } else if ("pride:0000194".equals(ionType) || ionType.contains("b ion")) {
            return FragmentIonType.B_ION;
        } else if ("pride:0000236".equals(ionType) || ionType.contains("c ion")) {
            return FragmentIonType.C_ION;
        } else if ("pride:0000227".equals(ionType) || ionType.contains("x ion")) {
            return FragmentIonType.X_ION;
        } else if ("pride:0000193".equals(ionType) || ionType.contains("y ion")) {
            return FragmentIonType.Y_ION;
        } else if ("pride:0000230".equals(ionType) || ionType.contains("z ion")
                || ionType.contains("zh ion") || ionType.contains("zhh ion")) {
            return FragmentIonType.Z_ION;
        } else if ("pride:0000239".equals(ionType) || ionType.contains("immonium")) {
            return FragmentIonType.IMMONIUM_ION;
        } else if ("pride:0000199".equals(ionType) || ionType.contains("in source")) {
            return FragmentIonType.IN_SOURCE_ION;
        } else if ("pride:0000201".equals(ionType) || ionType.contains("co-eluting")) {
            return FragmentIonType.CO_ELUTING_ION;
        } else {
            return FragmentIonType.NON_IDENTIFIED_ION;
        }
    }

    /**
     * Retrun true if the ion is a, b, c, x, y, z.
     *
     * @param ionType ion type.
     * @return boolean  true if it is backbone ion type.
     */
    public static boolean isBackboneFragmentIonType(FragmentIonType ionType) {
        return (ionType.equals(FragmentIonType.A_ION) || ionType.equals(FragmentIonType.B_ION) || ionType.equals(FragmentIonType.C_ION) ||
                ionType.equals(FragmentIonType.X_ION) || ionType.equals(FragmentIonType.Y_ION) || ionType.equals(FragmentIonType.Z_ION));
    }

    /**
     * Check whether the fragment ion type is N-Terminal fragment ions.
     *
     * @param ionType fragment ion type.
     * @return boolean  Return true if the ion is C-Terminal fragment ions.
     */
    public static boolean isNTerminalFragmentation(FragmentIonType ionType) {
        return (ionType.equals(FragmentIonType.A_ION) || ionType.equals(FragmentIonType.B_ION) || ionType.equals(FragmentIonType.C_ION));
    }

    /**
     * Check whether the fragment ion type is C-Terminal fragment ions.
     *
     * @param ionType fragment ion type.
     * @return boolean  Return true if the ion is C-Terminal fragment ions.
     */
    public static boolean isCTerminalFragmentation(FragmentIonType ionType) {
        return (ionType.equals(FragmentIonType.X_ION) || ionType.equals(FragmentIonType.Y_ION) || ionType.equals(FragmentIonType.Z_ION));
    }

    /**
     * Check whether the fragment ion type is C-Terminal side chain fragment ions.
     *
     * @param ionType fragment ion type.
     * @return boolean  Return true if the ion is C-Terminal side chain fragment ions.
     */
    public static boolean isCTerminalSideChainFragmentation(FragmentIonType ionType) {
        return ionType.equals(FragmentIonType.V_ION);
    }

    /**
     * Check whether the fragment ion type is N-Terminal fragment ions.
     *
     * @param ionType fragment ion type.
     * @return boolean  Return true if the ion is N-Terminal fragment ions.
     */
    public static boolean isNTerminalSideChainFragmentation(FragmentIonType ionType) {
        return ionType.equals(FragmentIonType.D_ION);
    }

    /**
     * Check wether the fragment ion type is Immonium fragment ion.
     *
     * @param ionType fragment ion type.
     * @return boolean  Return true if the ion is immonium fragment ion.
     */
    public static boolean isImmoniumFragmentation(FragmentIonType ionType) {
        return ionType.equals(FragmentIonType.IMMONIUM_ION);
    }

    /**
     * Return NeutralLoss object based on the input string.
     * <p/>
     * String contains h2o will return WATER_LOSS.
     * String contains nh3 will return AMMONIA_LOSS.
     *
     */
    public static NeutralLoss getFragmentIonNeutralLoss(String ionTypeStr) {
        NeutralLoss frag = null;
        if (ionTypeStr != null) {
            if (ionTypeStr.toLowerCase().contains("h2o")) {
                frag = NeutralLoss.WATER_LOSS;
            } else if (ionTypeStr.toLowerCase().contains("nh3")) {
                frag = NeutralLoss.AMMONIA_LOSS;
            }
        }
        return frag;
    }
}
