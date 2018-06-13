package uk.ac.ebi.pride.utilities.iongen;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.ebi.pride.utilities.iongen.model.PrecursorIon;
import uk.ac.ebi.pride.utilities.iongen.model.ProductIon;
import uk.ac.ebi.pride.utilities.iongen.impl.DefaultPrecursorIon;
import uk.ac.ebi.pride.utilities.iongen.impl.ImmoniumIon;
import uk.ac.ebi.pride.utilities.mol.Element;
import uk.ac.ebi.pride.utilities.mol.PTModification;
import uk.ac.ebi.pride.utilities.mol.Peptide;
import uk.ac.ebi.pride.utilities.mol.ProductIonType;
import uk.ac.ebi.pride.utilities.util.ProductIonFactory;

import java.util.*;


/**
 *
 *
 * @author Qingwei XU
 * @version 0.1-SNAPSHOT
 */
public class ProductIonFactoryTest {
    private void printProductIons(List<ProductIon> ions) {
        for (ProductIon ion : ions) {
            System.out.println(ion + "\t" + ion.getMassOverCharge());
        }
    }

    /**
     * This is a demo code to generate default product ions.
     */
    @Test
    public void testDefaultProductIons() {
        String sequence = "SSEDPNEDIVER";
        PrecursorIon precursorIon = new DefaultPrecursorIon(sequence, 3);
        List<ProductIon> ionList = ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.Y, 2);

        System.out.println();
        System.out.println();
        System.out.println("default product ions");
        printProductIons(ionList);
        //Product ion implements Comparable interface, which order by product ion's position.
//        Collections.sort(ionList);
//        printProductIons(ionList);
    }

    private void printImmoniumIons(List<ImmoniumIon> ionList) {
        for (ImmoniumIon ion : ionList) {
            System.out.println(ion + "\t" + ion.getMassOverCharge());
        }
    }

    /**
     * This is a demo code to generate default product ions.
     */
    @Test
    public void testImmuniumIons() {
        String sequence = "APGFGDNR";
        PrecursorIon precursorIon = new DefaultPrecursorIon(sequence, 3);
        List<ImmoniumIon> ionList = ProductIonFactory.createImmoniumProductIons(precursorIon, 1);

        System.out.println();
        System.out.println();
        System.out.println("immonium ions");
        printImmoniumIons(ionList);
    }

    @Test
    public void testModificatedProductIons() {
        String sequence = "HEAMITDLEER";

        PrecursorIon precursorIon = new DefaultPrecursorIon(sequence, 3);
        Peptide peptide = precursorIon.getPeptide();

        List<Double> massList1 = new ArrayList<>();
        massList1.add(Element.C.getMass());
        List<Double> massList2 = new ArrayList<>();
        massList2.add(Element.Cl.getMass());
        PTModification m1 = new PTModification(null, null, null, massList1, null);
        PTModification m2 = new PTModification(null, null, null, massList2, null);
        Map<Integer, PTModification> ptm = new HashMap<>();
        ptm.put(1, m1);
        ptm.put(4, m2);
        peptide.addALLModification(ptm);

        List<ProductIon> ionList = ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.Y, 1);

        System.out.println();
        System.out.println();
        System.out.println("modification ions");
        printProductIons(ionList);
    }


    private void compareResults(List<ProductIon> ionList, List<String> mascotList) {
        ProductIon ion;

        for (int i = 0; i < ionList.size(); i++) {
            ion = ionList.get(i);
            // if result is blank, then skip test.
            String content = mascotList.get(i);
            double mascot;

            try {
                mascot = Double.parseDouble(content);
            } catch (NumberFormatException e) {
                mascot = 0;
            }

            if (mascot == 0) {
                continue;
            }

//            if (Math.abs(ion.getMassOverCharge() - mascot) > deviation) {
//                System.out.println(ion);
//                System.out.println("=====================");
//                System.out.println(ion.getMassOverCharge());
//                System.out.println(mascot);
//                System.out.println();
//            }

            Assert.assertTrue(Math.abs(ion.getMassOverCharge() - mascot) <= Constants.DEVIATION);
        }
    }



    @Test
    public void patchDefaultProductIons() {
        Map<String, List<String>> testSet = TestUtils.generateTestset("/default_product_ions.mascot");

        Iterator<String> it = testSet.keySet().iterator();
        String title;

        List<String> mascotList;
        String sequence;
        int precursorCharge;
        ProductIonType type;
        int productCharge;

        String[] items;
        while (it.hasNext()) {
            title = it.next();

            items = title.split("; ");
            sequence = items[0];
            precursorCharge = Integer.parseInt(items[1]);
            type = ProductIonType.valueOf(items[2]);
            productCharge = Integer.parseInt(items[3]);
            mascotList = testSet.get(title);

            PrecursorIon precursorIon = new DefaultPrecursorIon(sequence, precursorCharge);
            List<ProductIon> ionList = ProductIonFactory.createDefaultProductIons(precursorIon, type, productCharge);
            compareResults(ionList, mascotList);
        }
    }

    @Test
    public void patchModificatedProductIons() {
        Map<String, List<String>> testSet = TestUtils.generateTestset("/modificated_product_ions.proteomecluster");
        Iterator<String> it = testSet.keySet().iterator();
        String title;
        String[] items;
        List<String> results;

        String sequence;
        int precursorCharge;
        ProductIonType type;
        int productCharge;
        int modificatedPosition;
        double massDifference;
        String modicationString;

        while (it.hasNext()) {
            title = it.next();
            results = testSet.get(title);

            items = title.split("; ");
            sequence = items[0];
            precursorCharge = Integer.parseInt(items[1]);
            modicationString = items[2];
            type = ProductIonType.valueOf(items[3]);
            productCharge = Integer.parseInt(items[4]);

            PrecursorIon precursorIon = new DefaultPrecursorIon(sequence, precursorCharge);
            Peptide peptide = precursorIon.getPeptide();
            PTModification modification;
            String[] modifications = modicationString.split(", ");
            List<Double> monoMassDeltas = new ArrayList<>();
            for (int i = 0; i < modifications.length; ) {
                modificatedPosition = Integer.parseInt(modifications[i]);
                massDifference = Double.parseDouble(modifications[i + 1]);
                monoMassDeltas.add(massDifference);
                modification = new PTModification(null, null, null, monoMassDeltas, null);
                peptide.addModification(modificatedPosition, modification);
                i = i + 2;
            }

            List<ProductIon> ionList = ProductIonFactory.createDefaultProductIons(precursorIon, type, productCharge);
            compareResults(ionList, results);
        }
    }

    @Test
    public void patchImmoniumIons() {
        // test non-modification peptide generate immonium ions.
        //i[n] = a[n] - b[n-1]    n in [1..length-2]
        // the first and last immonium ions not be included in this test.
        String sequence = "APGFGDNR";
        PrecursorIon precursorIon = new DefaultPrecursorIon(sequence, 3);
        List<ImmoniumIon> ionList = ProductIonFactory.createImmoniumProductIons(precursorIon, 1);
        List<ProductIon> bIonList = ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.B, 1);
        List<ProductIon> aIonList = ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.A, 1);

        for (int i = 1; i < ionList.size() - 2; i++) {
            Assert.assertTrue(Math.abs(ionList.get(i).getMass() - (aIonList.get(i).getMass() - bIonList.get(i - 1).getMass())) <= Constants.DEVIATION);
        }

        // test modification peptide generate immonium ions.
        //i[n] = a[n] - b[n-1]    n in [1..length-2]
        List<Double> massList1 = new ArrayList<>();
        massList1.add(Element.C.getMass());
        List<Double> massList2 = new ArrayList<>();
        massList2.add(Element.Cl.getMass());
        PTModification m1 = new PTModification(null, null, null, massList1, null);
        PTModification m2 = new PTModification(null, null, null, massList2, null);
        Map<Integer, PTModification> ptm = new HashMap<>();
        ptm.put(1, m1);
        ptm.put(4, m2);
        precursorIon.getPeptide().addALLModification(ptm);
        ionList = ProductIonFactory.createImmoniumProductIons(precursorIon, 1);
        bIonList = ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.B, 1);
        aIonList = ProductIonFactory.createDefaultProductIons(precursorIon, ProductIonType.A, 1);

        for (int i = 1; i < ionList.size() - 1; i++) {
            Assert.assertTrue(Math.abs(ionList.get(i).getMass() - (aIonList.get(i).getMass() - bIonList.get(i - 1).getMass())) <= Constants.DEVIATION);
        }
    }
}
