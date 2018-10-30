package uk.ac.ebi.pride.utilities.annotator;

import uk.ac.ebi.pride.utilities.term.CvTermReference;

/**
 * This code is licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * ==Overview==
 *
 * @author ypriverol on 30/10/2018.
 */
public enum MSRunAttributes {
    MSRUN_FRACTION_IDENTIFIER(CvTermReference.MS_FRACTION_IDENTIFIER, null),
    MSRUN_REPLICATE_IDENTIFIER(CvTermReference.MS_TECHNICAL_REPLICATE, null),
    MSRUN_LABEL(CvTermReference.MS_LABELING_MSRUN, CvTermReference.MS_LABEL_FREE_SAMPLE);


    private final CvTermReference cvTerm;
    private final CvTermReference defaultTerm;

    MSRunAttributes(CvTermReference cvTerm, CvTermReference defaultTerm) {
        this.cvTerm = cvTerm;
        this.defaultTerm = defaultTerm;
    }

    public CvTermReference getCvTerm() {
        return cvTerm;
    }

    public CvTermReference getDefaultTerm() {
        return defaultTerm;
    }
}
