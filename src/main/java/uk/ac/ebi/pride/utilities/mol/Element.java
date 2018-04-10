package uk.ac.ebi.pride.utilities.mol;

/**
 * Chemical element, this is a enum class. User can get the standard mass value based on
 * the element name.
 *
 * @author Qingwei XU
 */
public enum Element {

    H( "H",  1.007825),
    Li("Li", 7.016003),
    C( "C",  12.000000),
    N( "N",  14.003074),
    O( "O",  15.994915),
    F( "F",  18.998403),
    Na("Na", 22.989768),
    Mg("Mg", 23.985142),
    P( "P",  30.973762),
    S( "S",  31.972071),
    Cl("Cl", 34.968853),
    K( "K",  38.963707),
    Ca("Ca", 39.962591),
    Br("Br", 78.918336),
    I( "I",  126.904473),
    Cs("Cs", 132.905429);

    private String name;
    private double mass;

    Element(String name, double mass) {
        this.name = name;
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    @Override
    public String toString() {
        return "Element{" +
                "name='" + name + '\'' +
                ", mass=" + mass +
                '}';
    }
}
