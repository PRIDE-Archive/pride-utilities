package uk.ac.ebi.pride.utilities.iongen.model;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.junit.Assert;
import org.junit.Test;
import uk.ac.ebi.pride.utilities.iongen.impl.DefaultPrecursorIon;
import uk.ac.ebi.pride.utilities.mol.PTModification;
import uk.ac.ebi.pride.utilities.mol.Peptide;
import uk.ac.ebi.pride.utilities.mol.ProductIonPair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qingwei
 */
public class PeptideScoreTest {
    private PrecursorIon precursorIon = new DefaultPrecursorIon("VVSVLTVLHQDWLNGK", 2);
    private double[] mzArray = {296.14764404296875,297.8748779296875,299.8394775390625,301.9700927734375,313.1361083984375,318.13177490234375,319.06524658203125,321.8583984375,324.0738525390625,346.1568603515625,347.647705078125,352.64306640625,364.8765869140625,367.04449462890625,368.3057861328125,379.1202392578125,381.1158447265625,383.120849609375,395.15576171875,399.054443359375,431.1387939453125,432.7166748046875,452.03759765625,464.1614990234375,467.65277099609375,468.3175048828125,477.6627197265625,480.1109619140625,482.14892578125,482.8671875,496.13037109375,498.61944580078125,499.25299072265625,500.15936279296875,511.4937744140625,518.9674072265625,522.1351318359375,523.444580078125,530.2315673828125,532.186767578125,532.89404296875,542.7333984375,546.4239501953125,548.884521484375,550.0625,553.5577392578125,562.949951171875,567.662109375,578.1263427734375,579.1461181640625,580.9564208984375,581.797607421875,594.943603515625,600.254150390625,607.177978515625,609.4302978515625,612.97265625,615.19775390625,617.135009765625,618.16259765625,619.437744140625,642.97412109375,646.497802734375,647.28515625,653.9801025390625,655.803955078125,662.205078125,664.173583984375,665.3328857421875,672.17236328125,676.257080078125,677.22119140625,680.03662109375,681.35302734375,694.3941650390625,695.2271728515625,697.050048828125,698.04150390625,701.40380859375,716.0185546875,720.319580078125,725.052978515625,727.874755859375,732.268310546875,733.448486328125,745.466064453125,750.5518798828125,775.48193359375,776.29736328125,781.940673828125,787.103759765625,793.29833984375,803.08154296875,806.0294189453125,807.6246337890625,810.1236572265625,811.0411376953125,825.2757568359375,834.2576904296875,841.4625244140625,843.5106201171875,844.3162841796875,853.0150146484375,855.2154541015625,860.614013671875,861.3262939453125,870.3343505859375,877.4801025390625,887.431640625,919.0341796875,921.4595947265625,926.7269287109375,930.07958984375,932.7298583984375,933.7288818359375,939.450927734375,940.2603759765625,948.3387451171875,949.336669921875,950.6212158203125,955.6859130859375,961.4154052734375,962.5234375,963.4901123046875,964.47021484375,965.6632080078125,967.062744140625,970.1131591796875,979.478271484375,980.43408203125,982.78759765625,984.25341796875,989.6199951171875,996.5858154296875,997.367919921875,998.3538818359375,999.465576171875,1046.85498046875,1053.76220703125,1076.53955078125,1077.896484375,1092.36669921875,1093.445556640625,1094.3232421875,1095.625244140625,1110.54150390625,1111.474609375,1112.5283203125,1132.6220703125,1150.483642578125,1151.37353515625,1162.3681640625,1177.922119140625,1178.888671875,1179.63134765625,1191.5478515625,1192.351806640625,1193.48583984375,1209.42529296875,1210.769287109375,1211.79150390625,1220.52685546875,1230.4716796875,1254.25830078125,1293.052734375,1293.679931640625,1294.510498046875,1310.67626953125,1311.56591796875,1312.462158203125,1342.11962890625,1349.5771484375,1350.6845703125,1377.569580078125,1379.4453125,1381.729248046875,1405.443359375,1406.44970703125,1407.2138671875,1423.671142578125,1424.708984375,1455.453369140625,1464.211181640625,1473.658447265625,1476.03076171875,1488.60498046875,1490.8076171875,1491.693359375,1505.753662109375,1524.13232421875,1573.6689453125,1592.799560546875,1593.594482421875,1604.56396484375,1605.345458984375,1606.278076171875,1606.979248046875,1609.817138671875,1610.905517578125,1644.198974609375};
    private double[] intensityArray = {3611.0,1156.0,9668.0,2645.0,2961.0,12209.0,875.0,986.0,767.0,2061.0,20026.0,2198.0,1074.0,3253.0,1370.0,1463.0,3317.0,1318.0,3779.0,1382.0,1342.0,936.0,3586.0,4944.0,6084.0,3037.0,727.0,6786.0,3240.0,1630.0,942.0,9697.0,1273.0,2490.0,1947.0,2909.0,434.0,16993.0,712.0,9431.0,639.0,3693.0,8663.0,6894.0,749.0,4510.0,3342.0,531.0,2087.0,5130.0,22315.0,2056.0,11536.0,2427.0,704.0,2194.0,1333.0,10399.0,8779.0,8811.0,553.0,2309.0,998.0,1411.0,1042.0,2060.0,3358.0,3384.0,1679.0,1618.0,4762.0,2262.0,7766.0,3459.0,6006.0,734.0,1611.0,3043.0,667.0,3381.0,1107.0,3633.0,2433.0,17853.0,4053.0,664.0,338.0,1928.0,1433.0,1236.0,4079.0,3612.0,2888.0,1634.0,2068.0,2203.0,6761.0,1682.0,972.0,752.0,7147.0,16602.0,9579.0,4247.0,10715.0,1139.0,4267.0,2610.0,1468.0,1273.0,4544.0,1758.0,2251.0,368.0,1751.0,13980.0,1.0,7749.0,4605.0,2191.0,2154.0,2246.0,4553.0,3200.0,931.0,2500.0,1540.0,366.0,3012.0,4868.0,1718.0,2646.0,6779.0,565.0,21549.0,18580.0,3410.0,2220.0,4896.0,5441.0,2125.0,2644.0,10501.0,2831.0,1029.0,42432.0,11147.0,10253.0,1976.0,2841.0,491.0,910.0,10643.0,6930.0,1091.0,9795.0,7461.0,1672.0,7712.0,16224.0,415.0,1003.0,1628.0,955.0,11707.0,2673.0,2534.0,13649.0,12289.0,6483.0,1008.0,1713.0,681.0,809.0,500.0,660.0,1796.0,2358.0,1593.0,5477.0,3291.0,6626.0,1665.0,1194.0,1340.0,2123.0,12565.0,5261.0,848.0,1346.0,2252.0,3696.0,4031.0,4237.0,398.0,10375.0,7542.0,1077.0,7972.0,0.0};
    private PeakSet peakSet = PeakSet.getInstance(mzArray, intensityArray);

    @Test
    public void testPeptideScore() {
        PeptideScore peptideScore = new PeptideScore(precursorIon, peakSet);
        int length = precursorIon.getPeptide().getLength();

        ProductIonSet byProductIonSet = peptideScore.getProductIonSet();
        int byCount = byProductIonSet.size();
        Assert.assertEquals(0, byProductIonSet.size() % (length - 1));

        peptideScore.setIonPair(ProductIonPair.A_X);
        ProductIonSet axProductIonSet = peptideScore.getProductIonSet();
        int axCount = axProductIonSet.size();
        Assert.assertEquals(0, axProductIonSet.size() % (length - 1));

        Assert.assertEquals(byCount, axCount);

        peptideScore.setShowAmmoniaLoss(true);
        peptideScore.setShowWaterLoss(false);
        Assert.assertEquals(axCount * 2, peptideScore.getProductIonSet().size());

        peptideScore.setShowAmmoniaLoss(false);
        peptideScore.setShowWaterLoss(true);
        Assert.assertEquals(axCount * 2, peptideScore.getProductIonSet().size());

        peptideScore.setShowAmmoniaLoss(true);
        peptideScore.setShowWaterLoss(true);
        Assert.assertEquals(axCount * 3, peptideScore.getProductIonSet().size());

        axProductIonSet = peptideScore.getProductIonSet();
        List<ProductIonSet> windows = axProductIonSet.splitWindow(100);
        int sum = 0;
        for (ProductIonSet window : windows) {
            sum += window.size();
        }
        Assert.assertEquals(sum, axProductIonSet.size());
    }

    @Test
    public void testCumulativeProbability() {
        double faction = 0.00001;

        BinomialDistribution distribution = new BinomialDistribution(3, 0.5);

        BigDecimal b0 = new BigDecimal(distribution.probability(0));
        BigDecimal b1 = new BigDecimal(distribution.probability(1));
        BigDecimal b2 = new BigDecimal(distribution.probability(2));
        BigDecimal b3 = new BigDecimal(distribution.probability(3));

        BigDecimal c2 = new BigDecimal(distribution.cumulativeProbability(2));
        Assert.assertTrue(c2.subtract(b0.add(b1).add(b2)).doubleValue() <= faction);

        BigDecimal ic2 = new BigDecimal(distribution.cumulativeProbability(1, 3));
        Assert.assertTrue(ic2.subtract(b2.add(b3)).doubleValue() <= faction);
    }

    @Test
    public void testMatchedSet() {
        PeptideScore peptideScore = new PeptideScore(precursorIon, peakSet);
        ProductIonSet productIonSet = peptideScore.getProductIonSet();

        double score;
        for (int i = 1; i <= 10; i++) {
            score = peptideScore.getScore(productIonSet, i, 100);
            Assert.assertTrue(score > 0);
        }
    }

    @Test
    public void testPeptideScore2() {
        double[] mzArray = {212.22,222.12,230.26,231.23,233.18,239.03,240.14,243.11,248.22,249.27,257.12,258.2,259.22,261.21,262.16,277.14,278.29,297.15,323.25,326.22,340.27,341.24,342.27,343.38,351.23,353.56,354.31,358.25,359.25,360.26,362.19,363.27,368.25,369.3,371.27,372.08,376.23,377.3,386.43,387.29,390.33,389.65,391.28,407.3,444.46,454.42,453.43,455.29,463.07,465.27,471.31,472.5,473.56,480.11,481.1,482.26,483.28,484.53,489.35,490.38,500.4,499.63,501.67,515.39,518.32,519.22,526.26,533.05,535.04,535.91,536.72,544.06,553.31,559.99,568.43,569.34,570.34,571.47,581.14,582.18,583.3,584.21,585.31,588.37,589.39,590.7,591.44,593.34,598.26,599.73,600.49,601.33,608.51,610.42,609.48,611.3,612.47,613.34,614.42,618.5,628.34,629.34,630.35,631.39,634.77,639.25,646.31,647.42,656.61,664.98,665.72,674.19,675.15,677.06,681.41,692.49,693.52,694.38,695.93,699.38,698.53,700.63,707.55,709.37,708.65,710.28,711.61,713.22,712.42,714.17,715.26,716.47,717.53,721.14,722.09,725.31,726.23,727.21,729.62,730.56,743.44,758.31,760.34,761.45,795.57,805.34,806.35,808.16,813.45,812.49,814.55,822.49,821.76,823.36,824.38,827.57,830.47,831.49,832.51,839.38,840.51,841.48,842.46,843.34,854.34,853.46,855.53,859.25,860.58,870.45,871.46,872.3,873.37,888.49,889.48,890.66,905.41,920.46,923.52,925.5,933.39,934.84,937.59,938.68,940.47,941.54,942.62,943.5,950.41,951.42,952.5,953.35,954.41,955.47,956.85,958.55,959.56,960.57,970.48,969.49,971.49,972.54,973.5,987.5,988.41,989.43,1051.61,1052.63,1053.61,1055.52,1065.41,1066.65,1067.57,1069.53,1068.68,1070.55,1071.66,1072.68,1081.98,1083.56,1082.86,1084.61,1085.57,1086.62,1087.54,1088.61,1089.39,1097.65,1100.48,1101.53,1102.51,1117.57,1180.51,1194.51,1193.65,1195.67,1197.47,1198.54,1199.79,1200.68,1211.59,1210.91,1212.65,1215.69,1216.64,1217.71,1228.67,1229.57,1230.63,1294.28,1295.31,1312.7,1311.84,1313.65,1329.64,1330.62,1331.72,1339.79};
        double[] intArray = {82.2,30.1,196.4,44.8,352.4,38.9,182.6,29.1,954.5,61.9,40.0,158.3,29.4,429.6,60.3,33.5,25.8,27.3,43.7,25.7,183.8,120.5,84.3,30.6,34.7,38.3,29.2,958.8,850.9,126.7,45.6,30.1,107.2,70.1,55.7,60.2,1411.0,224.2,25.5,69.2,289.1,68.4,87.1,79.0,27.7,134.3,79.1,31.1,40.2,30.7,575.9,265.8,30.3,153.3,50.8,102.4,71.6,30.3,3266.0,571.6,51.0,43.7,50.5,27.6,382.4,99.0,152.8,33.8,778.9,165.7,25.5,526.4,31.9,34.3,90.9,35.3,57.3,74.8,122.4,273.9,67.6,40.4,48.6,1712.0,481.6,809.8,266.4,92.7,29.7,705.1,237.2,69.8,1181.0,316.6,149.4,100.9,61.1,27.3,45.0,58.0,194.3,243.8,143.8,31.6,49.8,34.8,740.9,238.8,163.7,276.5,253.1,26.6,27.3,59.3,64.4,161.8,113.9,88.0,51.4,175.1,85.6,38.5,73.7,668.3,51.3,266.5,109.7,224.1,158.0,88.1,48.7,765.1,240.8,2009.0,185.0,65.4,45.3,55.5,1224.0,79.0,101.7,57.0,153.7,79.9,38.4,129.7,36.3,59.6,275.4,107.5,42.7,327.1,70.8,205.1,124.6,47.6,2062.0,816.4,112.3,42.0,76.1,59.5,44.5,75.8,192.6,30.5,45.4,116.3,44.3,87.8,529.6,150.4,40.4,465.0,194.3,42.2,27.3,29.8,43.8,49.5,43.0,43.9,39.5,27.4,192.3,442.6,209.9,45.3,71.4,75.7,108.5,186.2,201.4,78.8,38.0,1432.0,824.5,70.7,1042.0,251.5,486.9,47.8,34.5,1416.0,585.5,176.7,157.8,119.1,27.1,42.2,116.2,232.0,58.2,399.2,214.4,262.1,74.5,30.9,60.1,723.6,291.3,445.5,92.2,1622.0,614.7,196.5,28.6,25.1,1634.0,771.2,157.3,49.1,39.6,122.9,63.5,83.6,54.3,253.3,65.8,41.4,451.2,149.0,322.4,806.5,401.6,62.6,1672.0,814.2,247.6,32.2,30.2,149.8,170.9,63.0,503.8,352.2,72.9,88.2};

        PeakSet peakset = PeakSet.getInstance(mzArray, intArray);
        PrecursorIon precursor = new DefaultPrecursorIon("FLEQQNQVLQTK", 2);
        PeptideScore peptideScore = new PeptideScore(precursor, peakset);
        ProductIonSet productIonSet = peptideScore.getProductIonSet();
        double[] weightList = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        System.out.println(peptideScore.getWeightedAvgScore(productIonSet, 100, weightList));
    }

    @Test
    public void testPeptideScore3() {
        double[] mzArray = {259.16,320.13,359.08,431.14,462.22,463.29,480.37,498.32,548.17,599.3,605.31,610.55,624.23,627.28,628.35,633.13,633.95,675.43,677.71,679.89,680.78,681.57,685.33,686.6,687.66,688.7,689.4,690.28,691.23,692.27,697.37,703.2,704.26,705.07,707.26,708.34,709.14,715.38,716.18,716.92,717.56,719.01,763.24,764.28,828.4,829.34,918.32,919.29,960.52,1017.34,1018.44,1036.51,1146.25,1147.31,1247.13,1248.29};
        double[] intArray = {5,8,5,5,29,6,7,5,19,6,8,8,7,8,14,7,5,5,11,92,14,9,9,35,14,49,49,10,7,7,8,17,13,10,15,14,9,26,56,8,95,7,7,7,7,9,28,10,5,22,7,5,24,17,6,6};

        PeakSet peakset = PeakSet.getInstance(mzArray, intArray);
        Peptide peptide = new Peptide("LTTEATQTPVSNK");
        List<Double> monoMass = new ArrayList<>();
        monoMass.add(79.96633);
        PTModification modification = new PTModification("x", null, null, monoMass, null);
        peptide.addModification(8, modification);
        PrecursorIon precursor = new DefaultPrecursorIon(peptide, 5);
        PeptideScore peptideScore = new PeptideScore(precursor, peakset);
        ProductIonSet productIonSet = peptideScore.getProductIonSet();
        double[] weightList = {0.5, 0.75, 1, 1, 1, 1, 0.75, 0.5, 0.25, 0.25};

        System.out.println(peptideScore.getWeightedAvgScore(productIonSet, 100, weightList));
    }
}
