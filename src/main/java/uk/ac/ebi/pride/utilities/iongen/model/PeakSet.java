package uk.ac.ebi.pride.utilities.iongen.model;

import uk.ac.ebi.pride.utilities.util.ApproximateComparator;

import java.util.*;

/**
 * Creator: Qingwei-XU
 * Date: 02/11/12
 */
public class PeakSet extends TreeSet<Peak> {
    private ApproximateComparator comparator = new ApproximateComparator();

    public PeakSet() {
    }

    public PeakSet(SortedSet<Peak> peakList) {
        super(peakList);
    }

    /**
     * Translate the m/z Array and intensity Array into a set of peaks.
     * <P>Notice: the length of two array should be equal.</P>
     * @param mzArray if null, return a empty peak set.
     * @param intensityArray if null, return a empty peak set.
     */
    public static PeakSet getInstance(double[] mzArray, double[] intensityArray) {
        if (mzArray == null || intensityArray == null) {
            return new PeakSet();
        }

        if (mzArray.length != intensityArray.length) {
            throw new IllegalArgumentException("the mz array not equal to intensity array!");
        }

        PeakSet peakSet = new PeakSet();

        Peak peak;
        for (int i = 0; i < mzArray.length; i++) {
            peak = new Peak(mzArray[i], intensityArray[i]);
            peakSet.add(peak);
        }

        return peakSet;
    }

    /**
     * split the whole peaks into list of subsets. Each subset m/z length less than or equal splitSize.
     * subset is (startMZ, endMZ], endMz - startMz <= splitSize.
     */
    public List<PeakSet> splitWindow(int splitSize) {
        if (splitSize <= 1) {
            throw new IllegalArgumentException("Can not split peaks into windows which size less than 2");
        }

        List<PeakSet> windowList = new ArrayList<PeakSet>();

        int reset = 0;
        Peak peak;
        PeakSet window = new PeakSet();
        Iterator<Peak> it = iterator();
        peak = it.next();
        while (true) {
            if (Double.compare(peak.getMz() - reset, splitSize) <= 0) {
                window.add(peak);
                if (! it.hasNext()) {
                    windowList.add(window);
                    break;
                }
                peak = it.next();
            } else {
                windowList.add(window);
                reset += splitSize;
                window = new PeakSet();
            }
        }

        return windowList;
    }

    /**
     * Create a subset of peak set. The range is [m/z - interval, m/z + interval].
     *
     * @param mz should be in the range of peak set. that is, [first_peak.m/z, last_peak.m/z]. Otherwise,
     *           return empty peak set.
     * @param interval should great than 0. Otherwise, return empty peak set.
     */
    public PeakSet subSet(double mz, double interval) {
        if (size() == 0) {
            return new PeakSet();
        }

        if (Double.compare(mz, first().getMz() - interval) < 0) {
            return new PeakSet();
        }

        if (Double.compare(mz, last().getMz() + interval) > 0) {
            return new PeakSet();
        }

        if (interval <= 0d) {
            return new PeakSet();
        }

        double start = mz - interval;
        double end = mz + interval;

        Peak startPeak;
        Peak endPeak;
        if (Double.compare(start, first().getMz()) <= 0) {
            startPeak = first();
        } else {
            //create a mock
            startPeak = new Peak(start, 0d);
            // location concrete start
            startPeak = floor(startPeak);
        }

        if (Double.compare(end, last().getMz()) >= 0) {
            endPeak = last();
        } else {
            endPeak = new Peak(end, 0d);
            endPeak = ceiling(endPeak);
        }

        NavigableSet<Peak> set = subSet(startPeak, true, endPeak, true);

        // create a new peak set, therefore, we can remove a peak from peak set safely.
        PeakSet result = new PeakSet(set);

        if (comparator.compare(mz - Objects.requireNonNull(startPeak).getMz(), interval) > 0) {
            result.remove(startPeak);
        }

        if (comparator.compare(endPeak.getMz() - mz, interval) > 0) {
            result.remove(endPeak);
        }

        return result;
    }

    /**
     *
     * @return m/z array of peak set.
     */
    public double[] getMzArray() {
        double[] mzArray = new double[size()];

        Iterator<Peak> it = iterator();
        Peak peak;
        int i = 0;
        while (it.hasNext()) {
            peak = it.next();
            mzArray[i] = peak.getMz();
            i++;
        }

        return mzArray;
    }

    /**
     *
     * @return intensity array of peak set.
     */
    public double[] getIntensityArray() {
        double[] intensityArray = new double[size()];

        Iterator<Peak> it = iterator();
        Peak peak;
        int i = 0;
        while (it.hasNext()) {
            peak = it.next();
            intensityArray[i] = peak.getIntensity();
            i++;
        }

        return intensityArray;
    }

    /**
     * based on intensity descent order, and m/z ascent order.
     */
    private class IntensityComparator implements Comparator<Peak> {
        @Override
        public int compare(Peak o1, Peak o2) {
            int result = Double.compare(o2.getIntensity(), o1.getIntensity());
            result = result == 0 ? Double.compare(o1.getMz(), o2.getMz()) : result;

            return result;
        }
    }

    /**
     *
     * @return the maximum intensity peak within the peaks window. If more than one peaks,
     * return the first matching peak.
     */
    public Peak getMaxIntensityPeak() {
        return getTopIntensityPeak(1).first();
    }

    public PeakSet getTopIntensityPeak(int top) {
        if (top < 1) {
            throw new IllegalArgumentException(top + " should be great than 1");
        }

        PeakSet peakList = new PeakSet();

        TreeSet<Peak> intentOrderPeakSet = new TreeSet<Peak>(new IntensityComparator());
        intentOrderPeakSet.addAll(this);

        Peak peak;
        Iterator<Peak> it = intentOrderPeakSet.iterator();
        int count = 0;
        while (it.hasNext()) {
            count++;
            if (count > top) {
                break;
            }

            peak = it.next();
            peakList.add(peak);
        }

        return peakList;
    }
}
