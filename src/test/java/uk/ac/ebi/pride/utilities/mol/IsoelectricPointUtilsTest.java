package uk.ac.ebi.pride.utilities.mol;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author ypriverol
 * @author rwang
 */
public class IsoelectricPointUtilsTest {

    private IsoelectricPointUtils.BjellpI bjellpI;

    @Before
    public void setUp() {
        bjellpI = new IsoelectricPointUtils.BjellpI();

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testCalculate() {
        String peptideSeq = "GGTAVILDIFR";
        peptideSeq = peptideSeq.replace("*","");
        peptideSeq = IsoelectricPointUtils.replaceSpecialAA(peptideSeq);
        double pi = bjellpI.calculate(peptideSeq);
        Assert.assertEquals(5.84, pi, 0.0);
    }
}
