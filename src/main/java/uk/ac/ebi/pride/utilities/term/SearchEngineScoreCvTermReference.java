package uk.ac.ebi.pride.utilities.term;

/**
 * @author qingwei
 * @author ntoro
 * @since 25/11/13
 */
public enum SearchEngineScoreCvTermReference {

    // coming from children terms of MS:1001153 (search engine specific score)
    MS_SEARCH_ENGINE_SPECIFIC_SCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001153", "search engine specific score"),

    MS_ASCORE_ASCORE(SearchEngineCvTermReference.MS_ASCORE, "MS", "MS:1001985", "Ascore:Ascore"),

    MS_BYONIC__PEPTIDE_ABSLOGPROB(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002309", "Byonic: Peptide AbsLogProb"),
    MS_BYONIC__PEPTIDE_ABSLOGPROB2D(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002311", "Byonic: Peptide AbsLogProb2D"),
    MS_BYONIC__PROTEIN_ABSLOGPROB(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002310", "Byonic: Protein AbsLogProb"),
    MS_BYONIC_BEST_LOGPROB(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002268", "Byonic:Best LogProb"),
    MS_BYONIC_BEST_SCORE(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002269", "Byonic:Best Score"),
    MS_BYONIC_DELTA_SCORE(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002263", "Byonic:Delta Score"),
    MS_BYONIC_DELTAMOD_SCORE(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002264", "Byonic:DeltaMod Score"),
    MS_BYONIC_PEP(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002265", "Byonic:PEP"),
    MS_BYONIC_PEPTIDE_LOGPROB(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002266", "Byonic:Peptide LogProb"),
    MS_BYONIC_PROTEIN_LOGPROB(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002267", "Byonic:Protein LogProb"),
    MS_BYONIC_SCORE(SearchEngineCvTermReference.MS_BYONIC, "MS", "MS:1002262", "Byonic:Score"),

    MS_COMBINED_FDRSCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1002125", "combined FDRScore"),

    MS_COMET_DELTACN(SearchEngineCvTermReference.MS_COMET, "MS", "MS:1002253", "Comet:deltacn"),
    MS_COMET_DELTACNSTAR(SearchEngineCvTermReference.MS_COMET, "MS", "MS:1002254", "Comet:deltacnstar"),
    MS_COMET_EXPECTATION_VALUE(SearchEngineCvTermReference.MS_COMET, "MS", "MS:1002257", "Comet:expectation value"),
    MS_COMET_MATCHED_IONS(SearchEngineCvTermReference.MS_COMET, "MS", "MS:1002258", "Comet:matched ions"),
    MS_COMET_SPRANK(SearchEngineCvTermReference.MS_COMET, "MS", "MS:1002256", "Comet:sprank"),
    MS_COMET_SPSCORE(SearchEngineCvTermReference.MS_COMET, "MS", "MS:1002255", "Comet:spscore"),
    MS_COMET_TOTAL_IONS(SearchEngineCvTermReference.MS_COMET, "MS", "MS:1002259", "Comet:total ions"),
    MS_COMET_XCORR(SearchEngineCvTermReference.MS_COMET, "MS", "MS:1002252", "Comet:xcorr"),

    MS_DEBUNKER_SCORE(SearchEngineCvTermReference.MS_DEBUNKER, "MS", "MS:1001974", "DeBunker:score"),
    MS_FDRSCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001874", "FDRScore"),
    MS_HIGHER_SCORE_BETTER(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1002108", "higher score better"),
    MS_IDENTITYE_SCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001569", "IdentityE Score"),
    MS_LOWER_SCORE_BETTER(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1002109", "lower score better"),

    MS_MASCOT_EXPECTATION_VALUE(SearchEngineCvTermReference.MS_MASCOT, "MS", "MS:1001172", "Mascot:expectation value"),
    MS_MASCOT_HOMOLOGY_THRESHOLD(SearchEngineCvTermReference.MS_MASCOT, "MS", "MS:1001370", "Mascot:homology threshold"),
    MS_MASCOT_IDENTITY_THRESHOLD(SearchEngineCvTermReference.MS_MASCOT, "MS", "MS:1001371", "Mascot:identity threshold"),
    MS_MASCOT_MATCHED_IONS(SearchEngineCvTermReference.MS_MASCOT, "MS", "MS:1001173", "Mascot:matched ions"),
    MS_MASCOT_PTM_SITE_ASSIGNMENT_CONFIDENCE(SearchEngineCvTermReference.MS_MASCOT, "MS", "MS:1002012", "Mascot:PTM site assignment confidence"),
    MS_MASCOT_SCORE(SearchEngineCvTermReference.MS_MASCOT, "MS", "MS:1001171", "Mascot:score"),
    MS_MASCOT_TOTAL_IONS(SearchEngineCvTermReference.MS_MASCOT, "MS", "MS:1001174", "Mascot:total ions"),

    MS_MAXQUANT_P_SITE_LOCALIZATION_PROBABILITY(SearchEngineCvTermReference.MS_MAXQUANT, "MS", "MS:1001982", "MaxQuant:P-site localization probability"),
    MS_MAXQUANT_PHOSPHO__STY__PROBABILITIES(SearchEngineCvTermReference.MS_MAXQUANT, "MS", "MS:1001980", "MaxQuant:Phospho (STY) Probabilities"),
    MS_MAXQUANT_PHOSPHO__STY__SCORE_DIFFS(SearchEngineCvTermReference.MS_MAXQUANT, "MS", "MS:1001981", "MaxQuant:Phospho (STY) Score Diffs"),
    MS_MAXQUANT_PTM_DELTA_SCORE(SearchEngineCvTermReference.MS_MAXQUANT, "MS", "MS:1001983", "MaxQuant:PTM Delta Score"),
    MS_MAXQUANT_PTM_SCORE(SearchEngineCvTermReference.MS_MAXQUANT, "MS", "MS:1001979", "MaxQuant:PTM Score"),

    MS_MRMAID_PEPTIDE_SCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1002221", "MRMaid:peptide score"),

    MS_MS_GF_DENOVOSCORE(SearchEngineCvTermReference.MS_MS_GF, "MS", "MS:1002050", "MS-GF:DeNovoScore"),
    MS_MS_GF_ENERGY(SearchEngineCvTermReference.MS_MS_GF, "MS", "MS:1002051", "MS-GF:Energy"),
    MS_MS_GF_EVALUE(SearchEngineCvTermReference.MS_MS_GF, "MS", "MS:1002053", "MS-GF:EValue"),
    MS_MS_GF_PEP(SearchEngineCvTermReference.MS_MS_GF, "MS", "MS:1002056", "MS-GF:PEP"),
    MS_MS_GF_PEPQVALUE(SearchEngineCvTermReference.MS_MS_GF, "MS", "MS:1002055", "MS-GF:PepQValue"),
    MS_MS_GF_QVALUE(SearchEngineCvTermReference.MS_MS_GF, "MS", "MS:1002054", "MS-GF:QValue"),
    MS_MS_GF_RAWSCORE(SearchEngineCvTermReference.MS_MS_GF, "MS", "MS:1002049", "MS-GF:RawScore"),
    MS_MS_GF_SPECEVALUE(SearchEngineCvTermReference.MS_MS_GF, "MS", "MS:1002052", "MS-GF:SpecEValue"),

    MS_MSFIT_MOWSE_SCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001501", "MSFit:Mowse score"),
    MS_MSQUANT_PTM_SCORE(SearchEngineCvTermReference.MS_MSQUANT, "MS", "MS:1001978", "MSQuant:PTM-score"),

    MS_MYRIMATCH_MVH(SearchEngineCvTermReference.MS_MYRIMATCH, "MS", "MS:1001589", "MyriMatch:MVH"),
    MS_MYRIMATCH_MZFIDELITY(SearchEngineCvTermReference.MS_MYRIMATCH, "MS", "MS:1001590", "MyriMatch:mzFidelity"),
    MS_MYRIMATCH_NMATCHS(SearchEngineCvTermReference.MS_MYRIMATCH, "MS", "MS:1001121", "number of matched peaks"),
    MS_MYRIMATCH_NOMATCHS(SearchEngineCvTermReference.MS_MYRIMATCH, "MS", "MS:1001362", "number of unmatched peaks"),

    MS_OMSSA_EVALUE(SearchEngineCvTermReference.MS_OMSSA, "MS", "MS:1001328", "OMSSA:evalue"),
    MS_OMSSA_PVALUE(SearchEngineCvTermReference.MS_OMSSA, "MS", "MS:1001329", "OMSSA:pvalue"),

    MS_PARAGON_CONFIDENCE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001167", "Paragon:confidence"),
    MS_PARAGON_CONTRIB(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001170", "Paragon:contrib"),
    MS_PARAGON_EXPRESSION_CHANGE_P_VALUE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001169", "Paragon:expression change p-value"),
    MS_PARAGON_EXPRESSION_ERROR_FACTOR(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001168", "Paragon:expression error factor"),
    MS_PARAGON_SCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001166", "Paragon:score"),
    MS_PARAGON_TOTAL_PROTSCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001165", "Paragon:total protscore"),
    MS_PARAGON_UNUSED_PROTSCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001164", "Paragon:unused protscore"),

    MS_PEAKS_PEPTIDESCORE(SearchEngineCvTermReference.MS_PEAKS_STUDIO, "MS", "MS:1001950", "PEAKS:peptideScore"),
    MS_PEAKS_PROTEINSCORE(SearchEngineCvTermReference.MS_PEAKS_STUDIO, "MS", "MS:1001951", "PEAKS:proteinScore"),

    MS_PEPTIDESHAKER_PSM_SCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE,"MS", "MS:1002466", "PeptideShaker: PSM Score"),
    MS_PEPTIDESHAKER_PSM_CONFIDENCE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE,"MS", "MS:1002467", "PeptideShaker: PSM Confidence"),
    MS_PEPTIDESHAKER_PROTEIN_SCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE,"MS", "MS:1002470", "PeptideShaker: Protein Score"),
    MS_PEPTIDESHAKER_PROTEIN_CONFIDENCE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE,"MS", "MS:1002471", "PeptideShake: Protein Confidence"),

    MS_PERCOLATOR_PEP(SearchEngineCvTermReference.MS_PERCOLATOR, "MS", "MS:1001493", "percolator:PEP"),
    MS_PERCOLATOR_Q_VALUE(SearchEngineCvTermReference.MS_PERCOLATOR, "MS", "MS:1001491", "percolator:Q value"),
    MS_PERCOLATOR_SCORE(SearchEngineCvTermReference.MS_PERCOLATOR, "MS", "MS:1001492", "percolator:score"),

    MS_PHENYX_AC(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001388", "Phenyx:AC"),
    MS_PHENYX_AUTO(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001393", "Phenyx:Auto"),
    MS_PHENYX_ID(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001389", "Phenyx:ID"),
    MS_PHENYX_MODIF(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001398", "Phenyx:Modif"),
    MS_PHENYX_NUMBEROFMC(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001397", "Phenyx:NumberOfMC"),
    MS_PHENYX_PEPPVALUE(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001396", "Phenyx:PepPvalue"),
    MS_PHENYX_PEPTIDES1(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001391", "Phenyx:Peptides1"),
    MS_PHENYX_PEPTIDES2(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001392", "Phenyx:Peptides2"),
    MS_PHENYX_PEPZSCORE(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001395", "Phenyx:Pepzscore"),
    MS_PHENYX_SCORE(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001390", "Phenyx:Score"),
    MS_PHENYX_USER(SearchEngineCvTermReference.MS_PHENYX, "MS", "MS:1001394", "Phenyx:User"),

    MS_PROFOUND_CLUSTER(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001499", "Profound:Cluster"),
    MS_PROFOUND_CLUSTERRANK(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001500", "Profound:ClusterRank"),
    MS_PROFOUND_Z_VALUE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001498", "Profound:z value"),

    MS_PROTEINEXTRACTOR_SCORE(SearchEngineCvTermReference.MS_PROTEINEXTRACTOR, "MS", "MS:1001507", "ProteinExtractor:Score"),

    MS_PROTEINLYNX_LADDER_SCORE(SearchEngineCvTermReference.MS_PROTEINLYNX_GLOBAL_SERVER, "MS", "MS:1001571", "ProteinLynx:Ladder Score"),
    MS_PROTEINLYNX_LOG_LIKELIHOOD(SearchEngineCvTermReference.MS_PROTEINLYNX_GLOBAL_SERVER, "MS", "MS:1001570", "ProteinLynx:Log Likelihood"),

    MS_PROTEINPROSPECTOR_EXPECTATION_VALUE(SearchEngineCvTermReference.MS_PROTEINPROSPECTOR, "MS", "MS:1002045", "ProteinProspector:expectation value"),
    MS_PROTEINPROSPECTOR_SCORE(SearchEngineCvTermReference.MS_PROTEINPROSPECTOR, "MS", "MS:1002044", "ProteinProspector:score"),

    MS_PROTEINSCAPE_INTENSITYCOVERAGE(SearchEngineCvTermReference.MS_PROTEINSCAPE, "MS", "MS:1001505", "ProteinScape:IntensityCoverage"),
    MS_PROTEINSCAPE_PFFSOLVEREXP(SearchEngineCvTermReference.MS_PROTEINSCAPE, "MS", "MS:1001503", "ProteinScape:PFFSolverExp"),
    MS_PROTEINSCAPE_PFFSOLVERSCORE(SearchEngineCvTermReference.MS_PROTEINSCAPE, "MS", "MS:1001504", "ProteinScape:PFFSolverScore"),
    MS_PROTEINSCAPE_PROFOUNDPROBABILITY(SearchEngineCvTermReference.MS_PROTEINSCAPE, "MS", "MS:1001497", "ProteinScape:ProfoundProbability"),
    MS_PROTEINSCAPE_SEARCHEVENTID(SearchEngineCvTermReference.MS_PROTEINSCAPE, "MS", "MS:1001496", "ProteinScape:SearchEventId"),
    MS_PROTEINSCAPE_SEARCHRESULTID(SearchEngineCvTermReference.MS_PROTEINSCAPE, "MS", "MS:1001495", "ProteinScape:SearchResultId"),
    MS_PROTEINSCAPE_SEQUESTMETASCORE(SearchEngineCvTermReference.MS_PROTEINSCAPE, "MS", "MS:1001506", "ProteinScape:SequestMetaScore"),

    MS_SCAFFOLD_PEPTIDE_PROBABILITY(SearchEngineCvTermReference.MS_SCAFFOLD, "MS", "MS:1001568", "Scaffold:Peptide Probability"),
    MS_SCAFFOLD_PROTEIN_PROBABILITY(SearchEngineCvTermReference.MS_SCAFFOLD, "MS", "MS:1001579", "Scaffold:Protein Probability"),

    MS_SEQUEST_CONSENSUS_SCORE(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001163", "SEQUEST:consensus score"),
    MS_SEQUEST_DELTACN(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001156", "SEQUEST:deltacn"),
    MS_SEQUEST_DELTACNSTAR(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1002250", "SEQUEST:deltacnstar"),
    MS_SEQUEST_EXPECTATION_VALUE(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001159", "SEQUEST:expectation value"),
    MS_SEQUEST_MATCHED_IONS(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001161", "SEQUEST:matched ions"),
    MS_SEQUEST_PEPTIDEIDNUMBER(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001219", "SEQUEST:PeptideIdnumber"),
    MS_SEQUEST_PEPTIDENUMBER(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001218", "SEQUEST:PeptideNumber"),
    MS_SEQUEST_PEPTIDERANKSP(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001217", "SEQUEST:PeptideRankSp"),
    MS_SEQUEST_PEPTIDESP(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001215", "SEQUEST:PeptideSp"),
    MS_SEQUEST_PROBABILITY(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001154", "SEQUEST:probability"),
    MS_SEQUEST_SEQUENCES(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001372", "SEQUEST:Sequences"),
    MS_SEQUEST_SF(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001160", "SEQUEST:sf"),
    MS_SEQUEST_SP(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001157", "SEQUEST:sp"),
    MS_SEQUEST_SPRANK(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1002249", "SEQUEST:sprank"),
    MS_SEQUEST_SPSCORE(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1002248", "SEQUEST:spscore"),
    MS_SEQUEST_SUM(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001374", "SEQUEST:Sum"),
    MS_SEQUEST_TIC(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001373", "SEQUEST:TIC"),
    MS_SEQUEST_TOTAL_IONS(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001162", "SEQUEST:total ions"),
    MS_SEQUEST_UNIQ(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001158", "SEQUEST:Uniq"),
    MS_SEQUEST_XCORR(SearchEngineCvTermReference.MS_SEQUEST, "MS", "MS:1001155", "SEQUEST:xcorr"),

    MS_SONAR_SCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001502", "Sonar:Score"),

    MS_SPECTRAST_DELTA(SearchEngineCvTermReference.MS_SPECTRAST, "MS", "MS:1001420", "SpectraST:delta"),
    MS_SPECTRAST_DISCRIMINANT_SCORE_F(SearchEngineCvTermReference.MS_SPECTRAST, "MS", "MS:1001419", "SpectraST:discriminant score F"),
    MS_SPECTRAST_DOT_BIAS(SearchEngineCvTermReference.MS_SPECTRAST, "MS", "MS:1001418", "SpectraST:dot_bias"),
    MS_SPECTRAST_DOT(SearchEngineCvTermReference.MS_SPECTRAST, "MS", "MS:1001417", "SpectraST:dot"),

    MS_SPECTRUMMILL_DISCRIMINANT_SCORE(SearchEngineCvTermReference.MS_SPECTRUM_MILL_FOR_MASSHUNTER_WORKSTATION, "MS", "MS:1001580", "SpectrumMill:Discriminant Score"),
    MS_SPECTRUMMILL_SCORE(SearchEngineCvTermReference.MS_SPECTRUM_MILL_FOR_MASSHUNTER_WORKSTATION, "MS", "MS:1001572", "SpectrumMill:Score"),
    MS_SPECTRUMMILL_SPI(SearchEngineCvTermReference.MS_SPECTRUM_MILL_FOR_MASSHUNTER_WORKSTATION, "MS", "MS:1001573", "SpectrumMill:SPI"),

    MS_SQID_DELTASCORE(SearchEngineCvTermReference.MS_SQID, "MS", "MS:1001888", "SQID:deltaScore"),
    MS_SQID_PROTEIN_SCORE(SearchEngineCvTermReference.MS_SQID, "MS", "MS:1001889", "SQID:protein score"),
    MS_SQID_SCORE(SearchEngineCvTermReference.MS_SQID, "MS", "MS:1001887", "SQID:score"),

    MS_X_TANDEM_EXPECT(SearchEngineCvTermReference.MS_X_TANDEM, "MS", "MS:1001330", "X!Tandem:expect"),
    MS_X_TANDEM_HYPERSCORE(SearchEngineCvTermReference.MS_X_TANDEM, "MS", "MS:1001331", "X!Tandem:hyperscore"),

    // ProLuCID
    MS_PROLUCID_XSCORE(SearchEngineCvTermReference.MS_PROLUCID, "MS", "MS:1002534", "The ProLuCID result 'XCorr'"),
    MS_PROLUCID_DELTA(SearchEngineCvTermReference.MS_PROLUCID, "MS", "MS:1002535", "The ProLuCID result 'DeltaCn'"),


    MS_ZCORE_PROBSCORE(SearchEngineCvTermReference.MS_ANALYSIS_SOFTWARE, "MS", "MS:1001952", "ZCore:probScore"),

    // PRIDE Ontology: children of PRIDE:0000049 (Peptide search engine output parameter)
    PRIDE_MASCOT_EXPECT_VALUE(SearchEngineCvTermReference.MS_MASCOT, "PRIDE", "PRIDE:0000212", "Mascot expect value"),
    PRIDE_MASCOT_SCORE(SearchEngineCvTermReference.MS_MASCOT, "PRIDE", "PRIDE:0000069", "Mascot score"),
    PRIDE_OMSSA_E_VALUE(SearchEngineCvTermReference.MS_OMSSA, "PRIDE", "PRIDE:0000185", "OMSSA E-value"),
    PRIDE_OMSSA_P_VALUE(SearchEngineCvTermReference.MS_OMSSA, "PRIDE", "PRIDE:0000186", "OMSSA P-value"),
    PRIDE_PEPSPLICE_DELSTASCORE(SearchEngineCvTermReference.PRIDE_PEPSPLICE, "PRIDE", "PRIDE:0000149", "PepSplice Deltascore"),
    PRIDE_PEPSPLICE_FALSE_DISCOVERY_RATE(SearchEngineCvTermReference.PRIDE_PEPSPLICE, "PRIDE", "PRIDE:0000147", "PepSplice False Discovery Rate"),
    PRIDE_PEPSPLICE_P_VALUE(SearchEngineCvTermReference.PRIDE_PEPSPLICE, "PRIDE", "PRIDE:0000148", "PepSplice P-value"),
    PRIDE_PEPSPLICE_PENALTY(SearchEngineCvTermReference.PRIDE_PEPSPLICE, "PRIDE", "PRIDE:0000151", "PepSplice Penalty"),
    PRIDE_PEPSPLICE_SCORE_COUNT(SearchEngineCvTermReference.PRIDE_PEPSPLICE, "PRIDE", "PRIDE:0000150", "PepSplice Score Count"),
    PRIDE_PEPTIDE_PROPHET_DISCRIMINANT_SCORE(SearchEngineCvTermReference.PRIDE_PEPTIDE_PROPHET, "PRIDE", "PRIDE:0000138", "Discriminant score"),
    PRIDE_PEPTIDE_PROPHET_PROBABILITY(SearchEngineCvTermReference.PRIDE_PEPTIDE_PROPHET, "PRIDE", "PRIDE:0000099", "PeptideProphet probability score"),
    PRIDE_SEQUEST_CN(SearchEngineCvTermReference.MS_SEQUEST, "PRIDE", "PRIDE:0000052", "Cn"),
    PRIDE_SEQUEST_DELTA_CN(SearchEngineCvTermReference.MS_SEQUEST, "PRIDE", "PRIDE:0000012", "Delta Cn"),
    PRIDE_SEQUEST_SCORE(SearchEngineCvTermReference.MS_SEQUEST, "PRIDE", "PRIDE:0000053", "Sequest score"),
    PRIDE_SPECTRUM_MILL_PEPTIDE_SCORE(SearchEngineCvTermReference.MS_SPECTRUM_MILL_FOR_MASSHUNTER_WORKSTATION, "PRIDE", "PRIDE:0000177", "Spectrum Mill peptide score"),
    PRIDE_X_CORRELATION(SearchEngineCvTermReference.MS_SEQUEST, "PRIDE", "PRIDE:0000013", "X correlation"),
    PRIDE_X_TANDEM_HYPERSCORE(SearchEngineCvTermReference.MS_X_TANDEM, "PRIDE", "PRIDE:0000176", "X!Tandem Hyperscore"),
    PRIDE_XTANDEM_EXPECTANCY_SCORE(SearchEngineCvTermReference.MS_X_TANDEM, "PRIDE", "PRIDE:0000183", "X|Tandem expectancy score"),
    PRIDE_XTANDEM_ZSCORE(SearchEngineCvTermReference.MS_X_TANDEM, "PRIDE", "PRIDE:0000182", "X|Tandem Z score"),

    //ProteoGrouper
    MS_PROTEOGROUPER_PDHSCORE(SearchEngineCvTermReference.MS_PROTEOGROUPER, "MS", "MS:1002235", "ProteoGrouper:PDH score");

    private final SearchEngineCvTermReference searchEngineParam;
    private final String cvLabel;
    private final String accession;
    private final String name;
    private final String score;

    SearchEngineScoreCvTermReference(SearchEngineCvTermReference searchEngineParam, String cvLabel, String accession, String name) {
        this.cvLabel = cvLabel;
        this.accession = accession;
        this.name = name;
        this.score = null;
        this.searchEngineParam = searchEngineParam;
    }


    public SearchEngineCvTermReference getSearchEngineParam() {
        return searchEngineParam;
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

    public String getScore() {
        return score;
    }

    public static SearchEngineScoreCvTermReference getSearchEngineScoreParamByName(String searchEngineName) {
        SearchEngineCvTermReference searchEngineParam = SearchEngineCvTermReference.findParamByName(searchEngineName);

        if (SearchEngineCvTermReference.MS_MASCOT.equals(searchEngineParam)) {
            return SearchEngineScoreCvTermReference.MS_MASCOT_SCORE;
        } else if (SearchEngineCvTermReference.MS_OMSSA.equals(searchEngineParam)) {
            return SearchEngineScoreCvTermReference.MS_OMSSA_EVALUE;
        } else if (SearchEngineCvTermReference.MS_SEQUEST.equals(searchEngineParam)) {
            return SearchEngineScoreCvTermReference.MS_SEQUEST_CONSENSUS_SCORE;
        } else if (SearchEngineCvTermReference.MS_SPECTRUM_MILL_FOR_MASSHUNTER_WORKSTATION.equals(searchEngineParam)) {
            return SearchEngineScoreCvTermReference.MS_SPECTRUMMILL_SCORE;
        } else if (SearchEngineCvTermReference.MS_X_TANDEM.equals(searchEngineParam)) {
            return SearchEngineScoreCvTermReference.MS_X_TANDEM_HYPERSCORE;
        } else if (SearchEngineCvTermReference.MS_ASCORE.equals(searchEngineParam)) {
            return SearchEngineScoreCvTermReference.MS_ASCORE_ASCORE;
        } else if (SearchEngineCvTermReference.MS_BYONIC.equals(searchEngineParam)) {
            return SearchEngineScoreCvTermReference.MS_BYONIC_BEST_SCORE;
        } else if (SearchEngineCvTermReference.MS_PERCOLATOR.equals(searchEngineParam)) {
            return SearchEngineScoreCvTermReference.MS_PERCOLATOR_SCORE;
        } else if  (SearchEngineCvTermReference.MS_PROLUCID.equals(searchEngineParam)){
            return SearchEngineScoreCvTermReference.MS_PROLUCID_XSCORE;
        }  else if  (SearchEngineCvTermReference.MS_PROTEOGROUPER.equals(searchEngineParam)){
            return SearchEngineScoreCvTermReference.MS_PROTEOGROUPER_PDHSCORE;
        }

        if (searchEngineParam != null) {
            return SearchEngineScoreCvTermReference.MS_SEARCH_ENGINE_SPECIFIC_SCORE;
        }

        return null;
    }

    public static SearchEngineScoreCvTermReference getSearchEngineScoreParamByAccession(String accession) {
        for (SearchEngineScoreCvTermReference scoreParam : SearchEngineScoreCvTermReference.values()) {
            if (scoreParam.accession.equalsIgnoreCase(accession)) {
                return scoreParam;
            }
        }

        return null;
    }


}
