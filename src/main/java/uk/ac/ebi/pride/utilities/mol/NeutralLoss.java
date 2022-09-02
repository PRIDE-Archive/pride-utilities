package uk.ac.ebi.pride.utilities.mol;

import java.util.Objects;

/**
 * neutral losses
 *
 * @author rwang
 * Date: 14-Jun-2010
 * Time: 17:55:53
 */
public class NeutralLoss implements Mass, Cloneable {
    // todo: monoisotopic mass and average mass should be different.
    public static final NeutralLoss WATER_LOSS = new NeutralLoss("H2O", "\\u00ba", 18.015, 18.010565);
    public static final NeutralLoss AMMONIA_LOSS = new NeutralLoss("NH3", "*", 17.027, 17.026549);

    private final String name;
    private final String sign;
    private final double avgMass;
    private final double monoMass;

    public NeutralLoss(String name, String sign,
                        double avgMass, double monoMass) {
        this.name = name;
        this.sign = sign;
        this.avgMass = avgMass;
        this.monoMass = monoMass;
    }

    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }

    public double getAvgMass() {
        return avgMass;
    }

    public double getMonoMass() {
        return monoMass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NeutralLoss)) return false;

        NeutralLoss that = (NeutralLoss) o;

        return Double.compare(that.avgMass, avgMass) == 0 && Double.compare(that.monoMass, monoMass) == 0 && Objects.equals(name, that.name) && Objects.equals(sign, that.sign);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        temp = avgMass != 0.0d ? Double.doubleToLongBits(avgMass) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = monoMass != 0.0d ? Double.doubleToLongBits(monoMass) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "NeutralLoss{" +
                "name='" + name + '\'' +
                '}';
    }
}
