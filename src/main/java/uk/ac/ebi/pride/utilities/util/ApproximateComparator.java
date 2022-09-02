package uk.ac.ebi.pride.utilities.util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * In double add, minus, compare operator, can not get exact 0. "fraction" used to approximate compute.
 * In the range of faction, we all believe that they are equal.
 *
 * @author qingwei
 * Date: 11/12/12
 */
public class ApproximateComparator {

    private final MathContext context;

    public ApproximateComparator() {
        this(5);
    }

    public ApproximateComparator(int fraction) {
        if (fraction < 0) {
            throw new IllegalArgumentException("faction should be great than 0");
        }

        this.context = new MathContext(fraction);
    }

    public int compare(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(d1, context);
        BigDecimal bd2 = new BigDecimal(d2, context);

        return bd1.compareTo(bd2);
    }
}
