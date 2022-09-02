package uk.ac.ebi.pride.utilities.iongen.ion;

import java.awt.*;

/**
 * FragmentIonTypeColor stores all the color for each FragmentIonType.
 * <p>
 * This class is decoupled from FragmentIonType.
 *
 * @author rwang
 * Date: 09-Aug-2010
 * Time: 09:05:29
 */
public class FragmentIonTypeColor {
    public final static Color A_ION_COLOR = Color.black;
    public final static Color B_ION_COLOR = Color.blue;
    public final static Color C_ION_COLOR = Color.cyan;
    public final static Color D_ION_COLOR = Color.gray;
    public final static Color X_ION_COLOR = Color.green;
    public final static Color Y_ION_COLOR = new Color(220, 30, 0);
    public final static Color Z_ION_COLOR = Color.orange;
    public final static Color V_ION_COLOR = Color.gray;
    public final static Color W_ION_COLOR = Color.gray;
    public final static Color AMBIGUOUS_ION_COLOR = Color.darkGray;
    public final static Color IMMONIUM_ION_COLOR = new Color(55, 160, 40);
    public final static Color IN_SOURCE_ION_COLOR = Color.gray;
    public final static Color CO_ELUTING_ION_COLOR = Color.gray;
    public final static Color NON_IDENTIFIED_ION_COLOR = Color.gray;
    public final static Color PRECURSOR_ION_COLOR = Color.green;

    /**
     * Get the color according to the input ion type.
     *
     * @param ionType   ion type.
     * @return Color    ion type's color.
     */
    public static Color getColor(FragmentIonType ionType) {
        if (FragmentIonType.A_ION.equals(ionType)) {
            return A_ION_COLOR;
        } else if (FragmentIonType.B_ION.equals(ionType)) {
            return B_ION_COLOR;
        } else if (FragmentIonType.C_ION.equals(ionType)) {
            return C_ION_COLOR;
        } else if (FragmentIonType.D_ION.equals(ionType)) {
            return D_ION_COLOR;
        } else if (FragmentIonType.X_ION.equals(ionType)) {
            return X_ION_COLOR;
        } else if (FragmentIonType.Y_ION.equals(ionType)) {
            return Y_ION_COLOR;
        } else if (FragmentIonType.Z_ION.equals(ionType)) {
            return Z_ION_COLOR;
        } else if (FragmentIonType.V_ION.equals(ionType)) {
            return V_ION_COLOR;
        } else if (FragmentIonType.W_ION.equals(ionType)) {
            return W_ION_COLOR;
        } else if (FragmentIonType.AMBIGUOUS_ION.equals(ionType)) {
            return AMBIGUOUS_ION_COLOR;
        } else if (FragmentIonType.IMMONIUM_ION.equals(ionType)) {
            return IMMONIUM_ION_COLOR;
        } else if (FragmentIonType.IN_SOURCE_ION.equals(ionType)) {
            return IN_SOURCE_ION_COLOR;
        } else if (FragmentIonType.CO_ELUTING_ION.equals(ionType)) {
            return CO_ELUTING_ION_COLOR;
        } else if (FragmentIonType.NON_IDENTIFIED_ION.equals(ionType)) {
            return NON_IDENTIFIED_ION_COLOR;
        } else if (FragmentIonType.PRECURSOR_ION.equals(ionType)) {
            return PRECURSOR_ION_COLOR;
        } else{
            return null;
        }
    }
}
