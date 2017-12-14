package uk.ac.ebi.pride.utilities.contaminant;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests reading in and matching from the contaminates FASTA file.
 */
public class MatchContaminantTest {

  /**
   * Checks if a contaminant peptide is present, and if a non-contaminant peptide is not present.
   * @throws Exception assert failures from incorrect contaminant searching results.
   */
    @Test
    public void isPeptideContaminant() throws Exception {
        String peptide = "FNTGVQEGAKALYANLEPKAEQYAVI";
        Assert.assertTrue(MatchContaminant.instance().isPeptideContaminant(peptide));
        peptide = "KKKKKKKKKKKKKKRRRR";
        Assert.assertTrue(!MatchContaminant.instance().isPeptideContaminant(peptide));
    }

}