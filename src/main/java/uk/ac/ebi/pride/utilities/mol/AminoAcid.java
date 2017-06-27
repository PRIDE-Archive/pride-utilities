package uk.ac.ebi.pride.utilities.mol;


/**
 * Enumeration of the amino acids.
 * <p/>
 * @author rwang
 * @author ypriverol
 * Date: 14-Jun-2010
 * Time: 14:35:29
 */
public enum AminoAcid implements Mass {

    A("Alanine", 'A', "Ala", "C3H5ON", 71.03711, 71.0788),
    /** B is an average between N and D */
    B("Asparagine or aspartic acid", 'B', "Asx", "", 114.53495, 114.5962),
    R("Arginine", 'R', "Arg", "C6H12ON4", 156.10111, 156.1875),
    N("Asparagine", 'N', "Asn", "C4H6O2N2", 114.04293, 114.1038),
    D("Aspartic acid", 'D', "Asp", "C4H5O3N", 115.02694, 115.0886),
    C("Cysteine", 'C', "Cys", "C3H5ONS", 103.00919, 103.1388),
    E("Glutamic acid", 'E', "Glu", "C5H7O3N", 129.04259, 129.1155),
    Q("Glutamine", 'Q', "Gln", "C5H8O2N2", 128.05858, 128.1307),
    G("Glycine", 'G', "Gly", "C2H3ON", 57.02146, 57.0519),
    H("Histidine", 'H', "His", "C6H7ON3", 137.05891, 137.1411),
    I("Isoleucine", 'I', "Ile", "C6H11ON", 113.08406, 113.1594),
    L("Leucine", 'L', "Leu", "C6H11ON", 113.08406, 113.1594),
    J("Isoleucine or Leucine", 'J', "Ile or Leu", "C6H11ON", 113.08406, 113.1594),
    K("Lysine", 'K', "Lys", "C6H12ON2", 128.09496, 128.1741),
    M("Methionine", 'M', "Met", "C5H9ONS", 131.04049, 131.1926),
    F("Phenylalanine", 'F', "Phe", "C9H9ON", 147.06841, 147.1766),
    P("Proline", 'P', "Pro", "C5H7ON", 97.05276, 97.1167),
    O("Pyrrolysine", 'O', "Pyl", "C12H21N3O3", 109.0528, 109.13),
    S("Serine", 'S', "Ser", "C3H5O2N", 87.03203, 87.0782),
    T("Threonine", 'T', "Thr", "C4H7O2N", 101.04768, 101.1051),
    U("Selenocysteine", 'U', "SeC", "C3H5NOSe", 150.95363, 150.0379),
    V("Valine", 'V', "Val", "C5H9ON", 99.06841, 99.1326),
    W("Tryptophan", 'W', "Trp", "C11H10ON2", 186.07931, 186.2132),
    /** Some Search Engines and Daabases used the X Amino Acid for unknown amino acids*/
    X("Unknown Amino Acid", 'X', "Xaa", "Unknown",0.0,0.0),
    Y("Tyrosine", 'Y', "Tyr", "C9H9O2N", 163.06333, 163.1760),
    /** Z is an average btween E and Q */
    Z("Glutamine or glutamic acid", 'Z', "Glx", "", 128.55059, 128.6231);

    private final String name;
    private final char oneLetterCode;
    private final String threeLetterCode;
    private final String chemicalFormula;
    private final double monoMass;
    private final double avgMass;

    private AminoAcid(String name, char oneLetterCode,
                      String threeLetterCode, String chemicalFormula,
                      double monoMass, double avgMass) {
        this.name = name;
        this.oneLetterCode = oneLetterCode;
        this.threeLetterCode = threeLetterCode;
        this.chemicalFormula = chemicalFormula;
        this.monoMass = monoMass;
        this.avgMass = avgMass;
    }

    public String getName() {
        return name;
    }

    public char getOneLetterCode() {
        return oneLetterCode;
    }

    public String getThreeLetterCode() {
        return threeLetterCode;
    }

    public String getChemicalFormula() {
        return chemicalFormula;
    }

    public double getMonoMass() {
        return monoMass;
    }

    public double getAvgMass() {
        return avgMass;
    }

    public static AminoAcid getAminoAcid(char oneLetterCode){
        switch( oneLetterCode ) {
            case 'A':
                return AminoAcid.A;
            case 'B':
                return AminoAcid.B;
            case 'R':
                return AminoAcid.R;
            case 'N':
                return AminoAcid.N;
            case 'D':
                return AminoAcid.D;
            case 'C':
                return AminoAcid.C;
            case 'E':
                return AminoAcid.E;
            case 'Q':
                return AminoAcid.Q;
            case 'G':
                return AminoAcid.G;
            case 'H':
                return AminoAcid.H;
            case 'I':
                return AminoAcid.I;
            case 'L':
                return AminoAcid.L;
            case 'J':
                return AminoAcid.J;
            case 'K':
                return AminoAcid.K;
            case 'M':
                return AminoAcid.M;
            case 'F':
                return AminoAcid.F;
            case 'P':
                return AminoAcid.P;
            case 'O':
                return AminoAcid.O;
            case 'S':
                return AminoAcid.S;
            case 'T':
                return AminoAcid.T;
            case 'V':
                return AminoAcid.V;
            case 'U':
                return AminoAcid.U;
            case 'W':
                return AminoAcid.W;
            case 'X':
                return AminoAcid.X;
            case 'Y':
                return AminoAcid.Y;
            case 'Z':
                return AminoAcid.Z;
            default:
                return null;
        }
    }
}
