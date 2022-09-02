package uk.ac.ebi.pride.utilities.mol;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Chemical Group, which including at least one Element. User can get mass value of chemical group.
 * This is a read only class, which implements {@link #hashCode()} and {@link #equals(Object)}
 * methods.
 *
 * @see Element
 *
 * @author Qingwei XU
 */
public class Group {
    private double mass = 0;
    private final List<Element> elements;

    /**
     */
    public static final Group H = new Group(Element.H);

    /**
     */
    public static final Group OH = new Group(Element.O, Element.H);

    /**
     *  CO group used in calcute the mass of a and x ion.
     */
    public static final Group CO = new Group(Element.C, Element.O);

    /**
     *  NH group used in calcute the mass of z ion.
     */
    public static final Group NH = new Group(Element.N, Element.H);

    /**
     * Water group.
     */
    public static final Group H2O = new Group(Element.H, Element.H, Element.O);

    /**
     *  Ammonia group
     */
    public static final Group NH3 = new Group(Element.N, Element.H, Element.H, Element.H);

    /**
     * A chemical group maybe include more than one chemical elements. We overwrite
     * the equals and hashCode method, thus, user can put object of Group class
     * into the hash collection.
     *
     * <P>
     * Notice that: Group.OH and Group.HO are not same group, although both of them mass
     * value are same. If user set elementList is null, system will throw NullPointerException.
     * </P>
     *
     * @param elementList a var-arg @see uk.ac.ebi.pride.iongen.model.Element
     */
    public Group(Element... elementList) {
        if (elementList == null) {
            throw new NullPointerException("Elements can not set null!");
        }

        this.elements = Arrays.asList(elementList);

        for (Element e : elements) {
            this.mass += e.getMass();
        }
    }

    public double getMass() {
        return mass;
    }

    public List<Element> getElements() {
        return elements;
    }

    /**
     * User can get the group name based on the elements name which are included in the chemical group.
     * We comment user to call getName() method, to display chemical group in their visualization interface.
     */
    public String getName() {
        StringBuilder builder = new StringBuilder();

        for (Element e : elements) {
            builder.append(e.getName());
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return "Group{" +
                "mass=" + mass +
                ", elements=" + elements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return Double.compare(group.mass, mass) == 0 && Objects.equals(elements, group.elements);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = mass != 0.0d ? Double.doubleToLongBits(mass) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (elements != null ? elements.hashCode() : 0);
        return result;
    }

}
