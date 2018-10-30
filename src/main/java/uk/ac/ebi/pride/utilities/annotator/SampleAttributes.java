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
 * @author ypriverol on 26/10/2018.
 */
public enum SampleAttributes {

    ORGANISM(CvTermReference.EFO_ORGANISM,
            new SampleClass[]{SampleClass.HUMAN, SampleClass.CELL_LINES, SampleClass.VERTEBRATES, SampleClass.NON_VERTEBRATES, SampleClass.PLANTS} ,
            null, null),

    ORGANISM_PART(CvTermReference.EFO_ORGANISM_PART,
            new SampleClass[]{SampleClass.HUMAN, SampleClass.VERTEBRATES, SampleClass.NON_VERTEBRATES, SampleClass.PLANTS, SampleClass.CELL_LINES},
            null, CvTermReference.EFO_DEFAULT_ORGANISM_PART),

    DISEASE(CvTermReference.EFO_DISEASE,
            new SampleClass[]{SampleClass.HUMAN, SampleClass.VERTEBRATES, SampleClass.NON_VERTEBRATES, SampleClass.PLANTS, SampleClass.CELL_LINES}, null,
            CvTermReference.PRIDE_DEFAULT_DISEASE),

    CELL_TYPE(CvTermReference.EFO_CELL_TYPE,
            new SampleClass[]{SampleClass.HUMAN, SampleClass.VERTEBRATES, SampleClass.NON_VERTEBRATES, SampleClass.PLANTS, SampleClass.CELL_LINES}, null, null),

    DEVELOPMENT_STAGE(CvTermReference.EFO_DEVELOPMENTAL_STAGE,
            new SampleClass[]{SampleClass.HUMAN}, new SampleClass[]{SampleClass.VERTEBRATES}, null),

    STRAIN(CvTermReference.EFO_STRAIN
            , null, new SampleClass[]{SampleClass.HUMAN, SampleClass.VERTEBRATES, SampleClass.NON_VERTEBRATES, SampleClass.PLANTS, SampleClass.CELL_LINES}, null),

    SEX(CvTermReference.EFO_SEX,  new SampleClass[]{SampleClass.HUMAN}, new SampleClass[]{SampleClass.VERTEBRATES}, null),
    INDIVIDUAL(CvTermReference.PRIDE_INDIVIDUAL,
            new SampleClass[]{SampleClass.HUMAN, SampleClass.CELL_LINES, SampleClass.VERTEBRATES, SampleClass.NON_VERTEBRATES, SampleClass.PLANTS} , null, null),

    CELL_LINE_CODE(CvTermReference.EFO_CELL_LINE,
            new SampleClass[]{SampleClass.CELL_LINES}, null, null);

    private final CvTermReference efoTerm;
    private final SampleClass[] requiredSampleClasses;
    private final SampleClass[] optionalSampleClasses;
    private final CvTermReference defaultValue;

    SampleAttributes(CvTermReference efoTerm, SampleClass[] requiredSampleClasses, SampleClass[] optionalSampleClasses, CvTermReference defaultValue) {
        this.efoTerm = efoTerm;
        this.requiredSampleClasses = requiredSampleClasses;
        this.optionalSampleClasses = optionalSampleClasses;
        this.defaultValue = defaultValue;
    }

    public CvTermReference getEfoTerm() {
        return efoTerm;
    }

    public SampleClass[] getRequiredSampleClasses() {
        return requiredSampleClasses;
    }

    public SampleClass[] getOptionalSampleClasses() {
        return optionalSampleClasses;
    }

    public CvTermReference getDefaultValue() {
        return defaultValue;
    }
}
