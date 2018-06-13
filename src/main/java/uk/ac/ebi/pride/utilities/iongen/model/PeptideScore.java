package uk.ac.ebi.pride.utilities.iongen.model;

import org.apache.commons.math3.distribution.BinomialDistribution;
import uk.ac.ebi.pride.utilities.util.ProductIonFactory;
import uk.ac.ebi.pride.utilities.mol.ProductIonPair;
import uk.ac.ebi.pride.utilities.mol.ProductIonType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author qingwei
 * Date: 10/12/12
 */
public class PeptideScore {
    private PeakSet peakSet;

    private PrecursorIon precursorIon;

    private ProductIonPair ionPair = ProductIonPair.B_Y;

    private boolean showWaterLoss = false;

    private boolean showAmmoniaLoss = false;

    private double tolerance = 0.5;

    public PeptideScore(PrecursorIon precursorIon, PeakSet peakSet,
                        ProductIonPair ionPair, double tolerance,
                        boolean showWaterLoss, boolean showAmmoniaLoss) {
        this(precursorIon, peakSet);

        if (tolerance <= 0) {
            throw new IllegalArgumentException("Tolerance should be great than 0.");
        }

        this.ionPair = ionPair;
        this.tolerance = tolerance;
        this.showWaterLoss = showWaterLoss;
        this.showAmmoniaLoss = showAmmoniaLoss;
    }

    public PeptideScore(PrecursorIon precursorIon, PeakSet peakSet) {
        if (precursorIon == null) {
            throw new NullPointerException("Precursor is null!");
        }

        if (peakSet == null) {
            throw new NullPointerException("Spectrum peak list is null!");
        }

        this.precursorIon = precursorIon;
        this.peakSet = peakSet;
    }

    public ProductIonPair getIonPair() {
        return ionPair;
    }

    public void setIonPair(ProductIonPair ionPair) {
        this.ionPair = ionPair;
    }

    public boolean isShowWaterLoss() {
        return showWaterLoss;
    }

    public void setShowWaterLoss(boolean showWaterLoss) {
        this.showWaterLoss = showWaterLoss;
    }

    public boolean isShowAmmoniaLoss() {
        return showAmmoniaLoss;
    }

    public void setShowAmmoniaLoss(boolean showAmmoniaLoss) {
        this.showAmmoniaLoss = showAmmoniaLoss;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    /**
     *  probability of success.
     */
    public double getProbability(int peakDepth, int splitSize) {
        if (peakDepth <= 0) {
            throw new IllegalArgumentException("peak depth should great than 0");
        }

        BigDecimal p = BigDecimal.ONE.divide(new BigDecimal(splitSize)).multiply(new BigDecimal(peakDepth));
        return p.doubleValue();
    }

    /**
     * generate product ion list.
     */
    private List<ProductIon> addProductIonListByCharge(PrecursorIon precursorIon, int charge) {
        List<ProductIon> ionList = new ArrayList<>();

        switch (ionPair) {
            case B_Y:
                ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.B, charge));
                ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.Y, charge));
                if (showWaterLoss) {
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.B_H2O, charge));
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.Y_H2O, charge));
                }
                if (showAmmoniaLoss) {
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.B_NH3, charge));
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.Y_NH3, charge));
                }
                break;
            case A_X:
                ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.A, charge));
                ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.X, charge));
                if (showWaterLoss) {
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.A_H2O, charge));
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.X_H2O, charge));
                }
                if (showAmmoniaLoss) {
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.A_NH3, charge));
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.X_NH3, charge));
                }
                break;
            case C_Z:
                ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.C, charge));
                ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.Z, charge));
                if (showWaterLoss) {
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.C_H2O, charge));
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.Z_H2O, charge));
                }
                if (showAmmoniaLoss) {
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.C_NH3, charge));
                    ionList.addAll(ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.Z_NH3, charge));
                }
                break;
        }

        return ionList;
    }

    public ProductIonSet getProductIonSet() {
        ProductIonSet productIonSet = new ProductIonSet();

        List<ProductIon> ionList;
        int charge = precursorIon.getCharge();
        charge = charge > 3 ? 3 : charge;
        for (int i = 1; i <= charge; i++) {
            ionList = addProductIonListByCharge(precursorIon, i);
            productIonSet.addAll(ionList);
        }

        return productIonSet;
    }

    /**
     * match the peak set to fragment ion list with an ion tolerance of [-tolerance, +tolerance]
     */
    public ProductIonSet getMatchedSet(ProductIonSet productIonSet, PeakSet peakSet) {
        if (productIonSet == null) {
            throw new NullPointerException("Product Ion Set is null!");
        }

        ProductIonSet matchedSet = new ProductIonSet();
        if (productIonSet.size() == 0) {
            return matchedSet;
        }

        Iterator<ProductIon> it = productIonSet.iterator();
        ProductIon productIon;
        PeakSet subset;
        while (it.hasNext()) {
            productIon = it.next();
            subset = peakSet.subSet(productIon.getMassOverCharge(), tolerance);
            if (subset.size() > 0) {
                matchedSet.add(productIon);
            }
        }

        return matchedSet;
    }

    /**
     * Peak set was first separated into windows of splitSize m/z units. Within each window, only the top
     * peakDepth peaks were retained by intensity.
     */
    public List<PeakSet> getDepthPeakSetList(int peakDepth, int splitSize) {
        List<PeakSet> peakDepthWindows = new ArrayList<>();

        PeakSet peakDepthWindow;
        List<PeakSet> windows = peakSet.splitWindow(splitSize);
        for (PeakSet window : windows) {
            peakDepthWindow = window.getTopIntensityPeak(peakDepth);
            peakDepthWindows.add(peakDepthWindow);
        }

        return peakDepthWindows;
    }

    /**
     * get a peptide score based on peakDepth.
     */
    public double getScore(ProductIonSet productIonSet, int peakDepth, int splitSize) {
        List<PeakSet> depthPeakSetList = getDepthPeakSetList(peakDepth, splitSize);
        PeakSet allDepthPeakSet = new PeakSet();

        for (PeakSet depthPeakSet : depthPeakSetList) {
            allDepthPeakSet.addAll(depthPeakSet);
        }

        ProductIonSet matchedSet = getMatchedSet(productIonSet, allDepthPeakSet);

        int N = productIonSet.size();
        int n = matchedSet.size();
        double p = getProbability(peakDepth, splitSize);

        BinomialDistribution distribution = new BinomialDistribution(N, p);

        // get cumulative probability which n-1 < x <= N, that means n <= x <= N
        double cumulative = distribution.cumulativeProbability(n-1, N);

        return (-10) * Math.log10(cumulative);
    }

    /**
     * get a weighted average peptide score.
     */
    public double getWeightedAvgScore(ProductIonSet productIonSet, int splitSize, double[] weightList) {
        if (weightList == null || weightList.length == 0) {
            throw new IllegalArgumentException("Weight list is null or empty!");
        }

        double totalScore = 0;
        double weightSum = 0;
        double score;
        for (int depth = 0; depth < weightList.length; depth++) {
            score = getScore(productIonSet, depth + 1, splitSize);
            if (Double.isInfinite(score)) {
                break;
            }
            totalScore += score * weightList[depth];
            weightSum += weightList[depth];
        }

        return totalScore / weightSum;
    }

}
