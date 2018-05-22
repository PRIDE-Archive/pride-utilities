package uk.ac.ebi.pride.utilities.util;

/**
 * General functions for all libraries in PRIDE Related with String handling
 */
public class StringUtils {

    public static final String NO_PEPTIDE_REGEX = "[^ABCDEFGHIJKLMNPQRSTUVWXYZ]";


    /**
     * Check if an string is empty. Return tru if the string is null or the length is 0.
     * @param s check if is empty and string including nullity check
     * @return true if is empty.
     */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }



}
