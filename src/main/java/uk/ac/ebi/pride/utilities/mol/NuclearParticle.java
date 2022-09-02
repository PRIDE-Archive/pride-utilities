package uk.ac.ebi.pride.utilities.mol;

/**
 * NuclearParticle is an enum which stores all the nuclear particles.
 *
 * @author rwang
 * Date: 10-Aug-2010
 * Time: 09:48:18
 */
public enum NuclearParticle implements Mass {

//    Corrected values (C. Amsler et al., "Review of Particle Physics" Physics Letters B667, 1 (2008))
//    m(1H)         = 1.00727646677 u = mass of proton; charge +1
//    m(1H+e-)      = 1.00782504 u = mass of proton + mass of electron
//    m(e-)         = 0.00054858026 u = mass of electron; charge -1
//    m(e-)         = 0.00054857990924 u = new determination of electrons mass

    //todo: check the monomass and avgmass values.
    PROTON ();

    private final String name;
    private final String formula;
    private final double charge;
    private final double monoMass;
    private final double avgMass;

    NuclearParticle() {
        this.name = "proton";
        this.formula = "H";
        this.charge = 1;
        this.monoMass = 1.00727647;
        this.avgMass = 1.00727647;
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
