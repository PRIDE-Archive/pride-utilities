package uk.ac.ebi.pride.utilities.iongen.impl;


import uk.ac.ebi.pride.utilities.iongen.model.PeptideIon;
import uk.ac.ebi.pride.utilities.mol.*;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Qingwei XU
 * @version 0.1-SNAPSHOT
 */
public class DefaultPeptideIon implements PeptideIon {

    private Peptide peptide;
    private int charge;
    private double mass;

    private double calculateMass() {
        double mass = 0;
        Group c_terminal = this.peptide.getCTerminalGroup();
        Group n_terminal = this.peptide.getNTerminalGroup();
        List<AminoAcid> acidList = this.peptide.getAminoAcids();

        for (AminoAcid acid : acidList) {
            mass += acid.getMonoMass();
        }
        mass += c_terminal == null ? 0 : c_terminal.getMass();
        mass += n_terminal == null ? 0 : n_terminal.getMass();

        //calculate modifications
        Map<Integer, PTModification> ptm = peptide.getPTM();
        Integer position;
        PTModification modification;
        for (Integer integer : ptm.keySet()) {
            position = integer;
            modification = ptm.get(position);
            if (modification.getMonoMassDeltas() != null && !modification.getMonoMassDeltas().isEmpty())
                mass += modification.getMonoMassDeltas().get(0);
        }

        return mass;
    }

    /**
     * Create a peptide ion.
     * @param peptide can not set null, otherwise throw NullPointerException.
     * @param charge
     */
    public DefaultPeptideIon(Peptide peptide, int charge) {
        if (peptide == null) {
            throw new NullPointerException("Peptide is null!");
        }
        if (charge <= 0) {
            throw new IllegalArgumentException("ion charge should be great than 0.");
        }

        this.peptide = peptide;
        this.charge = charge;
        this.mass = calculateMass();
    }

    @Override
    public double getMassOverCharge() {
        double peptideMass = getMass();

        if (charge == 0) {
            return peptideMass;
        }

        //calculate ion mass by adding/substracting protons
        double ionMass = peptideMass + charge * Element.H.getMass();
        int z = charge < 0 ? charge * -1 : charge;

        return ionMass / z;
    }

    @Override
    public int getCharge() {
        return charge;
    }

    @Override
    public double getMass() {
        return this.mass;
    }

    public Peptide getPeptide() {
        return peptide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultPeptideIon that = (DefaultPeptideIon) o;

        return charge == that.charge && Double.compare(that.mass, mass) == 0 && !(peptide != null ? !peptide.equals(that.peptide) : that.peptide != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = peptide != null ? peptide.hashCode() : 0;
        result = 31 * result + charge;
        temp = mass != +0.0d ? Double.doubleToLongBits(mass) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DefaultPeptideIon{" +
                "peptide=" + peptide +
                ", charge=" + charge +
                ", mass=" + mass +
                '}';
    }
}
