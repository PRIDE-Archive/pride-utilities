package uk.ac.ebi.pride.utilities.util.Math;

/**
 * @author ypriverol
 */
public class MathUtils {

    /**
     *  @return {lowest, highest}
     */
    public static double[] findMinMax(double[] data) {

        double lowest = Double.POSITIVE_INFINITY;
        double highest = Double.NEGATIVE_INFINITY;

        for (double aData : data) {
            if (lowest > aData) lowest = aData;
            if (highest < aData) highest = aData;
        }
        return new double[]{lowest, highest};
    }

}
