package uk.ac.ebi.pride.utilities.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <code> NumberUtilities </code> provides methods for number handling.
 * <p/>
 * @author rwang
 * @author ypriverol
 * Date: 10-Oct-2010
 * Time: 10:03:46
 */
public class NumberUtilities {

    /**
     * Check whether a string is a number
     *
     * @param string string to check
     * @return boolean  true if it is a number
     */
    public static boolean isNumber(String string) {
        if(string != null && string.length() ==1 && !Character.isDigit(string.charAt(0))){
            return false;
        }
        if (string == null || string.isEmpty()) {
            return false;
        }

        int i = 0;
        if (string.charAt(0) == '-') {
            if (string.length() > 1) {
                i++;
            } else {
                return false;
            }
        }

        boolean hasDot = false;
        boolean hasE = false;
        int eIndex = -1;
        for (; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '.') {
                if (hasDot) {
                    return false;
                } else {
                    hasDot = true;
                    continue;
                }
            }

            if (c == 'e' || c == 'E') {
                if (hasE) {
                    return false;
                } else {
                    hasE = true;
                    eIndex = i;
                    continue;
                }
            }

            if (hasE && c == '-') {
                if (eIndex == i -1) {
                    hasE = false;
                    continue;
                } else {
                    return false;
                }
            }

            if (hasE || !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether an string is an integer.
     * 
     * @param string    input string
     * @return  boolean true means it is an integer
     */
    public static boolean isInteger(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }

        int i = 0;
        if (string.charAt(0) == '-') {
            if (string.length() > 1) {
                i++;
            } else {
                return false;
            }
        }

        for (; i < string.length(); i++) {
            char c = string.charAt(i);

            if (!Character.isDigit(c)) {
                return false;
            }
        }

        try {
            Integer.parseInt(string);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    /**
     * Check whether an string is a non-negative integer.
     *
     * @param string input string
     * @return boolean true means it is a non-negative integer.
     */
    public static boolean isNonNegativeInteger(String string) {
        if (isInteger(string)) {
            int i = Integer.parseInt(string);
            return i >= 0;
        } else {
            return false;
        }
    }

    /**
     * Returns the value scaled to the indicated decimal places
     *
     * @param value         the value to scale
     * @param decimalPlaces the number of decimal places
     * @return a scaled double to the indicated decimal places
     */
    public static double scaleDouble(double value, int decimalPlaces) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
