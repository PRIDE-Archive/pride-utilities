package uk.ac.ebi.pride.utilities.annotator;

/**
 * This code is licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">...</a>
 * <p>
 * ==Overview==
 *
 * @author ypriverol on 26/10/2018.
 */
public enum SupportedOntologies {

    EFO("efo"),
    PRIDE("pride");

    private final String ontologyId;

    SupportedOntologies(String ontologyId) {
        this.ontologyId = ontologyId;
    }

    public String getOntologyId() {
        return ontologyId;
    }
}
