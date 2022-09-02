package uk.ac.ebi.pride.utilities.term;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qingwei
 * @author ntoro
 *
 * @since 25/11/13
 */
public enum SearchEngineCvTermReference {
    // all of them are direct children of MS:1001456 (analysis software)
    MS__4000_SERIES_EXPLORER_SOFTWARE("MS", "MS:1000659", "4000 Series Explorer Software"),
    MS__4700_EXPLORER("MS", "MS:1000537", "4700 Explorer"),
    MS__6300_SERIES_ION_TRAP_DATA_ANALYSIS_SOFTWARE("MS", "MS:1000688", "6300 Series Ion Trap Data Analysis Software"),
    MS_AB_SCIEX_TOF_TOF_SERIES_EXPLORER_SOFTWARE("MS", "MS:1001483", "AB SCIEX TOF/TOF Series Explorer Software"),
    MS_ANALYSIS_SOFTWARE("MS", "MS:1001456", "analysis software"),
    MS_ANALYST("MS", "MS:1000551", "Analyst"),
    MS_ASCORE("MS", "MS:1001984", "Ascore"),
    MS_BIOANALYST("MS", "MS:1000667", "BioAnalyst"),
    MS_BIOTOOLS("MS", "MS:1000707", "BioTools"),
    MS_BIOWORKS("MS", "MS:1000533", "Bioworks"),
    MS_BYONIC("MS", "MS:1002261", "Byonic"),
    MS_CHROMATOF_HRT_SOFTWARE("MS", "MS:1001877", "ChromaTOF HRT software"),
    MS_CHROMATOF_SOFTWARE("MS", "MS:1001799", "ChromaTOF software"),
    MS_CLINPROT_MICRO("MS", "MS:1000709", "CLINPROT micro"),
    MS_CLINPROT("MS", "MS:1000708", "CLINPROT"),
    MS_COMET("MS", "MS:1002251", "Comet"),
    MS_COMPASS_FOR_HCT_ESQUIRE("MS", "MS:1000713", "Compass for HCT/esquire"),
    MS_COMPASS_FOR_MICROTOF("MS", "MS:1000714", "Compass for micrOTOF"),
    MS_COMPASS_OPENACCESS("MS", "MS:1000715", "Compass OpenAccess"),
    MS_COMPASS("MS", "MS:1000712", "Compass"),
    MS_DATA_EXPLORER("MS", "MS:1000536", "Data Explorer"),
    MS_DATAANALYSIS("MS", "MS:1000719", "DataAnalysis"),
    MS_DEBUNKER("MS", "MS:1001973", "DeBunker"),
    MS_DIRECTAG("MS", "MS:1001586", "DirecTag"),
    MS_EMPOWER("MS", "MS:1001795", "Empower"),
    MS_FLEXANALYSIS("MS", "MS:1000535", "FlexAnalysis"),
    MS_FLEXIMAGING("MS", "MS:1000722", "flexImaging"),
    MS_GENESPRING_MS("MS", "MS:1000684", "Genespring MS"),
    MS_GPS_EXPLORER("MS", "MS:1000661", "GPS Explorer"),
    MS_GREYLAG("MS", "MS:1001461", "greylag"),
    MS_ISOBARIQ("MS", "MS:1002210", "IsobariQ"),
    MS_LIGHTSIGHT_SOFTWARE("MS", "MS:1000662", "LightSight Software"),
    MS_MALDI_SOLUTIONS_MICROBIAL_IDENTIFICATION("MS", "MS:1001878", "MALDI Solutions Microbial Identification"),
    MS_MALDI_SOLUTIONS("MS", "MS:1001558", "MALDI Solutions"),
    MS_MARKERVIEW_SOFTWARE("MS", "MS:1000665", "MarkerView Software"),
    MS_MASCOT_DISTILLER("MS", "MS:1001488", "Mascot Distiller"),
    MS_MASCOT_INTEGRA("MS", "MS:1001489", "Mascot Integra"),
    MS_MASCOT_PARSER("MS", "MS:1001478", "Mascot Parser"),
    MS_MASCOT("MS", "MS:1001207", "Mascot"),
    MS_MASSHUNTER_BIOCONFIRM("MS", "MS:1000683", "MassHunter BioConfirm"),
    MS_MASSHUNTER_MASS_PROFILER("MS", "MS:1000685", "MassHunter Mass Profiler"),
    MS_MASSHUNTER_METABOLITE_ID("MS", "MS:1000682", "MassHunter Metabolite ID"),
    MS_MASSHUNTER_QUALITATIVE_ANALYSIS("MS", "MS:1000680", "MassHunter Qualitative Analysis"),
    MS_MASSHUNTER_QUANTITATIVE_ANALYSIS("MS", "MS:1000681", "MassHunter Quantitative Analysis"),
    MS_MASSLYNX("MS", "MS:1000534", "MassLynx"),
    MS_MAXQUANT("MS", "MS:1001583", "MaxQuant"),
    MS_METLIN("MS", "MS:1000686", "METLIN"),
    MS_MRMPILOT_SOFTWARE("MS", "MS:1000666", "MRMPilot Software"),
    MS_MS_GF_("MS", "MS:1002048", "MS-GF+"),
    MS_MS_GF("MS", "MS:1002047", "MS-GF"),
    MS_MSQUANT("MS", "MS:1001977", "MSQuant"),
    MS_MULTIQUANT("MS", "MS:1000674", "MultiQuant"),
    MS_MYRIMATCH("MS", "MS:1001585", "MyriMatch"),
    MS_MZIDLIB("MS", "MS:1002237", "mzidLib"),
    MS_OMSSA("MS", "MS:1001475", "OMSSA"),
    MS_PANALYZER("MS", "MS:1002076", "PAnalyzer"),
    MS_PEAKS_NODE("MS", "MS:1001948", "PEAKS Node"),
    MS_PEAKS_ONLINE("MS", "MS:1001947", "PEAKS Online"),
    MS_PEAKS_STUDIO("MS", "MS:1001946", "PEAKS Studio"),
    MS_PEPITOME("MS", "MS:1001588", "Pepitome"),
    MS_PERCOLATOR("MS", "MS:1001490", "Percolator"),
    MS_PHENYX("MS", "MS:1001209", "Phenyx"),
    MS_PINPOINT("MS", "MS:1001912", "PinPoint"),
    MS_PRO_BLAST("MS", "MS:1000671", "Pro BLAST"),
    MS_PRO_ICAT("MS", "MS:1000669", "Pro ICAT"),
    MS_PRO_ID("MS", "MS:1000668", "Pro ID"),
    MS_PRO_QUANT("MS", "MS:1000670", "Pro Quant"),
    MS_PROFILEANALYSIS("MS", "MS:1000728", "ProfileAnalysis"),
    MS_PROTEINEXTRACTOR("MS", "MS:1001487", "ProteinExtractor"),
    MS_PROTEINLYNX_GLOBAL_SERVER("MS", "MS:1000601", "ProteinLynx Global Server"),
    MS_PROTEINPILOT_SOFTWARE("MS", "MS:1000663", "ProteinPilot Software"),
    MS_PROTEINPROSPECTOR("MS", "MS:1002043", "ProteinProspector"),
    MS_PROTEINSCAPE("MS", "MS:1000734", "ProteinScape"),
    MS_PROTEIOS("MS", "MS:1000600", "Proteios"),
    MS_PROTEOME_DISCOVERER("MS", "MS:1000650", "Proteome Discoverer"),
    MS_PROTEOWIZARD_SOFTWARE("MS", "MS:1000615", "ProteoWizard software"),
    MS_QUANTANALYSIS("MS", "MS:1000736", "QuantAnalysis"),
    MS_SCAFFOLD("MS", "MS:1001561", "Scaffold"),
    MS_SEQUEST("MS", "MS:1001208", "SEQUEST"),
    MS_SPECTRAST("MS", "MS:1001477", "SpectraST"),
    MS_SPECTRUM_MILL_FOR_MASSHUNTER_WORKSTATION("MS", "MS:1000687", "Spectrum Mill for MassHunter Workstation"),
    MS_SQID("MS", "MS:1001886", "SQID"),
    MS_TAGRECON("MS", "MS:1001587", "TagRecon"),
    MS_TISSUEVIEW_SOFTWARE("MS", "MS:1000664", "TissueView Software"),
    MS_TOPP_SOFTWARE("MS", "MS:1000752", "TOPP software"),
    MS_TRANS_PROTEOMIC_PIPELINE_SOFTWARE("MS", "MS:1002286", "Trans-Proteomic Pipeline software"),
    MS_TRANS_PROTEOMIC_PIPELINE("MS", "MS:1002285", "Trans-Proteomic Pipeline"),
    MS_UNIFY("MS", "MS:1001796", "Unify"),
    MS_VOYAGER_BIOSPECTROMETRY_WORKSTATION_SYSTEM("MS", "MS:1000539", "Voyager Biospectrometry Workstation System"),
    MS_X_TANDEM("MS", "MS:1001476", "X!Tandem"),
    MS_XCALIBUR("MS", "MS:1000532", "Xcalibur"),
    MS_XCMS("MS", "MS:1001582", "XCMS"),
    MS_PROLUCID("MS", "MS:1002596", "ProLuCID"),


    //PRIDE SearchEngines
    PRIDE_PEPSPLICE("PRIDE", "PRIDE:0000145", "PepSplice"),
    PRIDE_PEPTIDE_PROPHET("PRIDE", "PRIDE:0000101", "PeptideProphet"),

    //ProteoGrouper
    MS_PROTEOGROUPER("MS", "MS:1002235", "ProteoGrouper:PDH score");


    private final String cvLabel;
    private final String accession;
    private final String name;
    private final String value;

    SearchEngineCvTermReference(String cvLabel, String accession, String name) {
        this.cvLabel = cvLabel;
        this.accession = accession;
        this.name = name;
        this.value = null;
    }

    public String getCvLabel() {
        return cvLabel;
    }

    public String getAccession() {
        return accession;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    private static String convertName(String name) {
        name = name.trim().toUpperCase().replaceAll("[^A-Z0-9]", "_");
        if (name.startsWith("[0-9]")) {
            return "_" + name;
        } else {
            return name;
        }
    }

    private static boolean matchRegexpName(String regexp, String name) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(name.trim().toLowerCase());
        return matcher.find();
    }

    /**
     * Tries to guess which search engine is described by the passed name. In case no matching parameter
     * is found, null is returned.
     */
    public static SearchEngineCvTermReference findParamByName(String searchEngineName) {
        if (searchEngineName == null) {
            return null;
        }

        SearchEngineCvTermReference param;

        try {
            param = SearchEngineCvTermReference.valueOf(convertName(searchEngineName));
            return param;
        } catch (IllegalArgumentException e) {
            param = null;
        }


        if (matchRegexpName(".*mascot.*", searchEngineName)) {
            param = MS_MASCOT;
        } else if (matchRegexpName(".*omssa.*", searchEngineName)) {
            param = MS_OMSSA;
        } else if (matchRegexpName(".*sequest.*", searchEngineName)) {
            param = MS_SEQUEST;
        } else if (matchRegexpName(".*spectrum.*mill.*", searchEngineName)) {
            param = MS_SPECTRUM_MILL_FOR_MASSHUNTER_WORKSTATION;
        } else if (matchRegexpName(".*spectra.*st.*", searchEngineName)) {
            param = MS_SPECTRAST;
        } else if (matchRegexpName(".*x.*tandem.*", searchEngineName)) {
            param = MS_X_TANDEM;
        }

        if (param == null) {
            param = MS_ANALYSIS_SOFTWARE;
        }

        return param;
    }

}
