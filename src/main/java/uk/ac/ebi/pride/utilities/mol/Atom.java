package uk.ac.ebi.pride.utilities.mol;


/**
 * Atom is an enum which stores all chemical elements.
 * <p/>
 * @author rwang
 * Date: 10-Aug-2010
 * Time: 09:54:54
 */
public enum Atom implements Mass {

    //todo: check the monomass and avgmass values.
    O_16("oxygen-16 atom", "O", 15.99491463, 15.99940),
    C_12("carbon-12 atom", "C", 12, 12.01070);

    private final String name;
    private final String formula;
    private final double charge;
    private final double monoMass;
    private final double avgMass;

    Atom(String name, String formula,
         double monoMass, double avgMass) {
        this.name = name;
        this.formula = formula;
        this.charge = 0;
        this.monoMass = monoMass;
        this.avgMass = avgMass;
    }

    public String getName() {
        return name;
    }

    public String getFormula() {
        return formula;
    }

    public double getCharge() {
        return charge;
    }

    public double getMonoMass() {
        return monoMass;
    }

    public double getAvgMass() {
        return avgMass;
    }
}

