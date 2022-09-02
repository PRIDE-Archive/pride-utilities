package uk.ac.ebi.pride.utilities.iongen.ion;

import java.util.Objects;

/**
 * FragmentIonType defines different type of fragment ion annotations.
 * Refer to FragmentIonTypeColor for specific color.
 *
 * @author rwang
 * {@code @author:} ypriverol
 */
public class FragmentIonType implements Cloneable {
    public static final FragmentIonType A_ION = new FragmentIonType("a ion", "a");
    public static final FragmentIonType B_ION = new FragmentIonType("b ion", "b");
    public static final FragmentIonType C_ION = new FragmentIonType("c ion", "c");
    /** d ions from partial side chain fragmentation of a ions */
    public static final FragmentIonType D_ION = new FragmentIonType("d ion", "d");
    public static final FragmentIonType X_ION = new FragmentIonType("x ion", "x");
    public static final FragmentIonType Y_ION = new FragmentIonType("y ion", "y");
    public static final FragmentIonType Z_ION = new FragmentIonType("z ion", "z");
    /** v ions from complete side chain fragmentation of y ions*/
    public static final FragmentIonType V_ION = new FragmentIonType("v ion", "v");
    /** w ions from partial side chain fragmentation of z ions */
    public static final FragmentIonType W_ION = new FragmentIonType("w ion", "w");
    public static final FragmentIonType AMBIGUOUS_ION = new FragmentIonType("ambiguous ion", "u");
    public static final FragmentIonType IMMONIUM_ION = new FragmentIonType("immonium ion", "immonium");
    public static final FragmentIonType IN_SOURCE_ION = new FragmentIonType("in source ion", "in source");
    public static final FragmentIonType CO_ELUTING_ION = new FragmentIonType("co-eluting ion", "co-eluting");
    public static final FragmentIonType NON_IDENTIFIED_ION = new FragmentIonType("non-identified ion", "non-identified");
    public static final FragmentIonType PRECURSOR_ION = new FragmentIonType("Precursor ion", "Precursor");

    private final String name;
    private final String label;

    private FragmentIonType(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FragmentIonType)) return false;

        FragmentIonType that = (FragmentIonType) o;

        return Objects.equals(label, that.label) && Objects.equals(name, that.name);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "FragmentIonType{" +
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
