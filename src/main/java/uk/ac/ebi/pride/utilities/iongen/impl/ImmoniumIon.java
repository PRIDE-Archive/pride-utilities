package uk.ac.ebi.pride.utilities.iongen.impl;

import uk.ac.ebi.pride.utilities.iongen.model.PrecursorIon;
import uk.ac.ebi.pride.utilities.mol.AminoAcid;
import uk.ac.ebi.pride.utilities.mol.Element;
import uk.ac.ebi.pride.utilities.mol.Group;
import uk.ac.ebi.pride.utilities.mol.PTModification;

/**
 *
 *
 * Creator: Qingwei-XU
 *    Date: ${DATE}
 * Version: 0.1-SNAPSHOT
 */
public class ImmoniumIon implements Comparable<ImmoniumIon> {
    private String typeName = "Immonium";
    private AminoAcid acid;
    private int charge;
    private int position;
    private PTModification modification;

    /**
     * the position from [0..length-1]
     */
    public ImmoniumIon(PrecursorIon precursorIon, int position) {
        this(precursorIon, position, 1);
    }

    public ImmoniumIon(PrecursorIon precursorIon, int position, int charge) {
        if (precursorIon == null) {
            throw new IllegalArgumentException("Precursor is not null!");
        }
        if (charge <= 0) {
            throw new IllegalArgumentException("Precursor charge should great than 0!");
        }

        this.acid = precursorIon.getPeptide().getAminoAcids().get(position);
        this.position = position;
        this.charge = charge;

        this.modification = precursorIon.getPeptide().getPTM().get(position);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(typeName).append("_").append(acid.getOneLetterCode()).append(position);
        if (charge > 1) {
            // if positive charge == 1 do not display +
            for (int i = 1; i <= charge; i++) {
                builder.append('+');
            }
        } else if (charge < 0) {
            for (int i = -1; i >= charge; i--) {
                builder.append('-');
            }
        }

        return builder.toString();
    }

    public double getMass() {
        double mass = acid.getMonoMass();

        // calculate modification mass
        if (modification != null) {
            mass += modification.getMonoMassDeltas().get(0);
        }

        mass = mass - Group.CO.getMass();

        return mass;
    }

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

    public int getCharge() {
        return charge;
    }

    public int getPosition() {
        return position;
    }

    public AminoAcid getAcid() {
        return acid;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public int compareTo(ImmoniumIon o) {
        return this.getPosition() - o.getPosition();
    }
}
