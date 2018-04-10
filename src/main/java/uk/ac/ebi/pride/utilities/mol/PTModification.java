package uk.ac.ebi.pride.utilities.mol;

import java.util.Collections;
import java.util.List;

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

        return (avgMassDeltas != null ? avgMassDeltas.equals(that.avgMassDeltas) : that.avgMassDeltas == null) && (label != null ? label.equals(that.label) : that.label == null) && (monoMassDeltas != null ? monoMassDeltas.equals(that.monoMassDeltas) : that.monoMassDeltas == null) && !(name != null ? !name.equals(that.name) : that.name != null) && !(type != null ? !type.equals(that.type) : that.type != null);

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
