package uk.ac.ebi.pride.utilities.mol;

import uk.ac.ebi.pride.utilities.iongen.ion.FragmentIonType;

/**
 * Creator: Qingwei-XU
 * Date: 23/10/12
 */

public enum ProductIonType {
    A("a", FragmentIonType.A_ION, null, ProductIonPair.A_X),
    A_NH3("a*", FragmentIonType.A_ION, NeutralLoss.AMMONIA_LOSS, ProductIonPair.A_X),
    A_H2O("a°", FragmentIonType.A_ION, NeutralLoss.WATER_LOSS, ProductIonPair.A_X),
    B("b", FragmentIonType.B_ION, null, ProductIonPair.B_Y),
    B_NH3("b*", FragmentIonType.B_ION, NeutralLoss.AMMONIA_LOSS, ProductIonPair.B_Y),
    B_H2O("b°", FragmentIonType.B_ION, NeutralLoss.WATER_LOSS, ProductIonPair.B_Y),
    C("c", FragmentIonType.C_ION, null, ProductIonPair.C_Z),
    C_NH3("c*", FragmentIonType.C_ION, NeutralLoss.AMMONIA_LOSS, ProductIonPair.C_Z),
    C_H2O("c°", FragmentIonType.C_ION, NeutralLoss.WATER_LOSS, ProductIonPair.C_Z),
    X("x", FragmentIonType.X_ION, null, ProductIonPair.A_X),
    X_NH3("x*", FragmentIonType.X_ION, NeutralLoss.AMMONIA_LOSS, ProductIonPair.A_X),
    X_H2O("x°", FragmentIonType.X_ION, NeutralLoss.WATER_LOSS, ProductIonPair.A_X),
    Y("y", FragmentIonType.Y_ION, null, ProductIonPair.B_Y),
    Y_NH3("y*", FragmentIonType.Y_ION, NeutralLoss.AMMONIA_LOSS, ProductIonPair.B_Y),
    Y_H2O("y°", FragmentIonType.Y_ION, NeutralLoss.WATER_LOSS, ProductIonPair.B_Y),
    Z("z", FragmentIonType.Z_ION, null, ProductIonPair.C_Z),
    Z_NH3("z*", FragmentIonType.Z_ION, NeutralLoss.AMMONIA_LOSS, ProductIonPair.C_Z),
    Z_H2O("z°", FragmentIonType.Z_ION, NeutralLoss.WATER_LOSS, ProductIonPair.C_Z);

    private String name;
    private FragmentIonType group;
    private NeutralLoss loss;
    private ProductIonPair pair;

    ProductIonType(String name, FragmentIonType group, NeutralLoss loss, ProductIonPair pair) {
        this.name = name;
        this.group = group;
        this.loss = loss;
        this.pair = pair;
    }

    public String getName() {
        return name;
    }

    public FragmentIonType getGroup() {
        return group;
    }

    public NeutralLoss getLoss() {
        return loss;
    }

    public ProductIonPair getPair() {
        return pair;
    }
}
