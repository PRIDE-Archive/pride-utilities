package uk.ac.ebi.pride.utilities.obo;

import java.util.List;

/**
 * This code is licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * ==Overview==
 *
 * @author ypriverol on 23/10/2018.
 */
public class OboCvTerm {

    String cvLabel;
    String name;
    String accession;
    List<String> parentAccessions;

    public OboCvTerm(String cvLabel, String name, String accession) {
        this.cvLabel = cvLabel;
        this.name = name;
        this.accession = accession;
    }

    public String getCvLabel() {
        return cvLabel;
    }

    public void setCvLabel(String cvLabel) {
        this.cvLabel = cvLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }
}
