pride-utilities
===============

[![Java CI with Maven](https://github.com/PRIDE-Utilities/pride-utilities/actions/workflows/maven.yml/badge.svg)](https://github.com/PRIDE-Utilities/pride-utilities/actions/workflows/maven.yml)

# About PRIDE Utilities

The primary purpose of PRIDE Utilities library is to provide commonly used classes shared by all the PRIDE Tool Suite. You may also find it useful for your own computational proteomics projects.

# License

pride-utilities is a PRIDE API licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt).

# How to cite it:

Perez-Riverol, Yasset, Julian Uszkoreit, Aniel Sanchez, Tobias Ternent, Noemi del Toro, Henning Hermjakob, Juan Antonio Vizcaíno, and Rui Wang.
"ms-data-core-api: an open-source, metadata-oriented library for computational proteomics."
Bioinformatics 31, no. 17 (2015): 2903-2905. [ms-data-core-api](https://bioinformatics.oxfordjournals.org/content/31/17/2903.full)


# Main Features
* Data Structures to represent AminoAcids, Mass Table, etc.
* Functions to compute different Properties such as isoelectric point, retention time, sequence length and masses.
* Reference table of the main Ontology terms used in PRIDE-toolsuite. 
* General functionalities and data structures classes missing in JDK, such as: Tuple.
* General functionalities like checking email format and availability.

# The library provides four key modules:

* mol: contains classes describing entities at the molecular level, such as: amino acids, neutrual losses, peptides and fragment ions.
* gui: contains several GUI components, you can use them if you want to replicate some of the features in PRIDE Inspector.
* data: contains data structures classes are missing from JDK, such as: Tuple.
* util: contains a selection of convenient classes. For examples: for formatting protein related informations, for checking Internet availability or for verify email addresses.

**Note**: the library is still evolving, we are committed to expand this library and add more useful classes.

# Getting PRIDE Utilities

The zip file in the releases section contains the PRIDE Utilities jar file and all other required libraries.

Maven Dependency

PRIDE Utilities library can be used in Maven projects, you can include the following snippets in your Maven pom file.
 
```maven
 <dependency>
   <groupId>uk.ac.ebi.pride.utils</groupId>
   <artifactId>pride-utilities</artifactId>
   <version>0.1.23-SNAPSHOT</version>
 </dependency> 
```
 
```maven
 <!-- EBI repo -->
 <repository>
     <id>nexus-ebi-repo</id>
     <url>http://www.ebi.ac.uk/intact/maven/nexus/content/repositories/ebi-repo</url>
 </repository>
 
 <!-- EBI SNAPSHOT repo -->
 <snapshotRepository>
    <id>nexus-ebi-repo-snapshots</id>
    <url>http://www.ebi.ac.uk/intact/maven/nexus/content/repositories/ebi-repo-snapshots</url>
 </snapshotRepository>

```

Note: you need to change the version number to the latest version.

For developers, the latest source code is available from our SVN repository.

# Getting Help

If you have questions or need additional help, please contact the PRIDE Helpdesk at the EBI: pride-support at ebi.ac.uk (replace at with @).

Please send us your feedback, including error reports, improvement suggestions, new feature requests and any other things you might want to suggest to the PRIDE team.

# This library has been used in:

* Wang, R., Fabregat, A., Ríos, D., Ovelleiro, D., Foster, J. M., Côté, R. G., ... & Vizcaíno, J. A. (2012). PRIDE Inspector: a tool to visualize and validate MS proteomics data. Nature biotechnology, 30(2), 135-137. [PDF File](http://www.nature.com/nbt/journal/v30/n2/pdf/nbt.2112.pdf), [Pubmed Record](http://www.ncbi.nlm.nih.gov/pubmed/22318026)

* Côté, R. G., Griss, J., Dianes, J. A., Wang, R., Wright, J. C., van den Toorn, H. W., ... & Vizcaíno, J. A. (2012). The PRoteomics IDEntification (PRIDE) Converter 2 framework: an improved suite of tools to facilitate data submission to the PRIDE database and the ProteomeXchange consortium. Molecular & Cellular Proteomics, 11(12), 1682-1689. [PRIDE Converter 2](https://code.google.com/p/pride-converter-2/) 

* Vizcaíno, J. A., Côté, R. G., Csordas, A., Dianes, J. A., Fabregat, A., Foster, J. M., ... & Hermjakob, H. (2013). The PRoteomics IDEntifications (PRIDE) database and associated tools: status in 2013. Nucleic acids research, 41(D1), D1063-D1069. [PRIDE-Archive](http://www.ebi.ac.uk/pride/archive/)

* Perez-Riverol, Y., Xu, Q.W., Wang, R., Uszkoreit, J., Griss, J., Sanchez, A., Reisinger, F., Csordas, A., Ternent, T., del-Toro, N. and Dianes, J.A., 2016. PRIDE Inspector Toolsuite: Moving Toward a Universal Visualization Tool for Proteomics Data Standard Formats and Quality Assessment of ProteomeXchange Datasets. Molecular & Cellular Proteomics, 15(1), pp.305-317. [PRIDE Inspector Toolsuite](http://www.mcponline.org/content/15/1/305.full)

How to use pride-utilities
===============

# Using PRIDE-Utilities 

Here we will show you how to use the PRIDE Utilities library to calculate m/z delta and calculate theoretical mass of a given peptide.

### Calculate m/z Delta:

You can find the method for calculating m/z delta from MoleculeUtilities in uk.ac.ebi.pride.mol package. It requires four input parameters:

```java 
/*
 * sequence is the peptide sequence in String,
 * precursorMz is the precusor m/z in double,
 * precursorCharge is the precursor charge in double,
 *ptmMasses is a list of post translational modifications in double.
*/

// Direct call on the method
Double mzDelta = MolecularUtilitites.calculateDeltaMz(sequence, precursorMz, precursorCharge, ptmMasses);
```

### Calculate Theoretical Mass

You can also find the method for calculating theoretical mass value from MoleculeUtilities. It needs two input parameters:

```java
 /*
  *sequence is the peptide sequence in String,  
  *masses is a optional list array of masses you want to add as extras.
  The following lines of code shows you how:
 */

 // Direct call on the method
 double result = MolecularUtilitites.calculateTheoreticalMass(sequence, masses);

 // Tip: Take a close look at other methods within MoleculeUtilitites, you might find them useful. 
```
