package uk.ac.ebi.pride.utilities.mol;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * PTModification stores modification details, this class is not enum because
 * there are many different types of modifications. This is a read only class,
 * the mono mass deltas and avg mass deltas list can not modified.
 *
 * @author rwang
 * @author ypriverol
 */
public class PTModification {

    private final String name;
    private final String type;
    private final String label;
    private final List<Double> monoMassDeltas;
    private final List<Double> avgMassDeltas;

    public PTModification(String name, String type, String label,
                        List<Double> monoMass, List<Double> avgMass) {
        this.name = name;
        this.type = type;
        this.label = label;
        this.monoMassDeltas = monoMass;
        this.avgMassDeltas = avgMass;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public List<Double> getMonoMassDeltas() {
        return Collections.unmodifiableList(monoMassDeltas);
    }

    public List<Double> getAvgMassDeltas() {
        return Collections.unmodifiableList(avgMassDeltas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PTModification)) return false;

        PTModification that = (PTModification) o;

        return (Objects.equals(avgMassDeltas, that.avgMassDeltas)) && (Objects.equals(label, that.label)) && (Objects.equals(monoMassDeltas, that.monoMassDeltas)) && Objects.equals(name, that.name) && Objects.equals(type, that.type);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (monoMassDeltas != null ? monoMassDeltas.hashCode() : 0);
        result = 31 * result + (avgMassDeltas != null ? avgMassDeltas.hashCode() : 0);
        return result;
    }

}
