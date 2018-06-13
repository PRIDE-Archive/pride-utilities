package uk.ac.ebi.pride.utilities.iongen.model;

import java.util.*;

/**
 * @author qingwei
 * Date: 10/12/12
 */
public class ProductIonSet extends TreeSet<ProductIon> {
    private static class MZComparator implements Comparator<ProductIon> {
        /**
         * based on m/z ascent order, charge ascent order and position ascent order.
         */
        @Override
        public int compare(ProductIon o1, ProductIon o2) {
            int result = Double.compare(o1.getMassOverCharge(), o2.getMassOverCharge());

            result = result == 0 ? o1.getCharge() - o2.getCharge() : result;
            result = result == 0 ? o1.getPosition() - o2.getPosition() : result;

            return result;
        }
    }

    public ProductIonSet() {
        super(new MZComparator());
    }

    /**
     * split the whole fragment ion list into list of subsets. Each subset m/z length less than or equal splitSize.
     * subset is (startMZ, endMZ], endMz - startMz <= splitSize.
     */
    public List<ProductIonSet> splitWindow(int splitSize) {
        if (splitSize <= 1) {
            throw new IllegalArgumentException("Can not split peaks into windows which size less than 2");
        }

        List<ProductIonSet> windowList = new ArrayList<>();

        int reset = 0;
        ProductIon ion;
        ProductIonSet window = new ProductIonSet();
        Iterator<ProductIon> it = iterator();
        ion = it.next();
        while (true) {
            if (Double.compare(ion.getMassOverCharge() - reset, splitSize) <= 0) {
                window.add(ion);
                if (! it.hasNext()) {
                    windowList.add(window);
                    break;
                }
                ion = it.next();
            } else {
                windowList.add(window);
                reset += splitSize;
                window = new ProductIonSet();
            }
        }

        return windowList;
    }
}
