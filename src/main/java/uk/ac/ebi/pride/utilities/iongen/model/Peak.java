package uk.ac.ebi.pride.utilities.iongen.model;

/**
 * Peak including m/z and intensity double value. Currently, we implement Comparable interface,
 * user can put Peak object into {@link java.util.SortedSet}, which based on m/z ascent order.
 * <p>
 * Creator: Qingwei-XU
 */
public class Peak implements Comparable<Peak> {
    private final double intensity;
    private final double mz;

    public Peak(double mz, double intensity) {
        if (mz < 0d) {
            throw new IllegalArgumentException("mz value should not less than 0");
        }

        if (intensity < 0d) {
            throw new IllegalArgumentException("intensity value should not less than 0");
        }

        this.intensity = intensity;
        this.mz = mz;
    }

    public double getIntensity() {
        return intensity;
    }

    public double getMz() {
        return mz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Peak peak = (Peak) o;

        return Double.compare(peak.intensity, intensity) == 0 && Double.compare(peak.mz, mz) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = intensity != 0.0d ? Double.doubleToLongBits(intensity) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = mz != 0.0d ? Double.doubleToLongBits(mz) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Based on m/z ascent order and intensity descent order.
     * @see PeakSet
     */
    @Override
    public int compareTo(Peak peak) {
        int result = Double.compare(mz, peak.getMz());
        result = result == 0 ? Double.compare(peak.getIntensity(), intensity) : result;

        return result;
    }

    @Override
    public String toString() {
        return "Peak{" +
                "intensity=" + intensity +
                ", mz=" + mz +
                '}';
    }
}
