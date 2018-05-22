package uk.ac.ebi.pride.utilities.mol;

/**
 * Mass provides a set of interfaces to get monoisotopic mass and average mass.
 *
 * @author rwang
 * Date: 10-Aug-2010
 * Time: 09:31:57
 */
public interface Mass {
    double getMonoMass();
    double getAvgMass();
}
