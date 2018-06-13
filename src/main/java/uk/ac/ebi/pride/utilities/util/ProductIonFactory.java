package uk.ac.ebi.pride.utilities.util;

import uk.ac.ebi.pride.utilities.iongen.model.IonCleavageException;
import uk.ac.ebi.pride.utilities.iongen.model.PrecursorIon;
import uk.ac.ebi.pride.utilities.iongen.model.ProductIon;
import uk.ac.ebi.pride.utilities.iongen.impl.ImmoniumIon;
import uk.ac.ebi.pride.utilities.mol.ProductIonType;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a general interface for most users to get theoretical m/z values list of product ions and immonium ions.
 * Reference ProductIonFactoryTest code to get details about how to use these methods.
 *
 * @author Qingwei XU
 * @version 0.1-SNAPSHOT
 */
public class ProductIonFactory {
    /**
     *
     */
    public static List<ProductIon> createDefaultProductIons(PrecursorIon precursorIon, ProductIonType type, int charge) {
        List<ProductIon> productIons = new ArrayList<>();

        ProductIon ion = null;
        //the default product ions cleavages position is [1..length-1]
        for (int i = 1; i< precursorIon.getPeptide().getLength(); i++) {
            try {
                ion = precursorIon.getProductIon(type, i, charge);
            } catch (IonCleavageException e) {
                e.printStackTrace();
            }
            productIons.add(ion);
        }

        return productIons;
    }

    public  static List<ImmoniumIon> createImmoniumProductIons(PrecursorIon precursorIon, int charge) {
        List<ImmoniumIon> productIons = new ArrayList<>();

        ImmoniumIon ion;
        //the immonium product ions cleavages position is [1..length]
        for (int i = 0; i < precursorIon.getPeptide().getLength(); i++) {
            ion = new ImmoniumIon(precursorIon, i, charge);
            productIons.add(ion);
        }

        return productIons;
    }

}
