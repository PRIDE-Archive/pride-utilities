package uk.ac.ebi.pride.utilities.contaminant;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This code is licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * ==Overview==
 * <p>
 * This class
 * <p>
 * Created by ypriverol (ypriverol@gmail.com) on 24/11/2017.
 */
public class MatchContaminantTest {

    @Test
    public void isPeptideContaminant() throws Exception {

        String peptide = "FNTGVQEGAKALYANLEPKAEQYAVI";
        Assert.assertTrue(MatchContaminant.instance().isPeptideContaminant(peptide));

        peptide = "KKKKKKKKKKKKKKRRRR";
        Assert.assertTrue(!MatchContaminant.instance().isPeptideContaminant(peptide));
    }

}