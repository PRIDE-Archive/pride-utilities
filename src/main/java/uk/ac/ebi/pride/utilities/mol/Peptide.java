package uk.ac.ebi.pride.utilities.mol;

import java.util.*;

/**
 * A peptide including three parts: N terminal group, C terminal group and a couple of AminoAcids.
 *
 * @author ypriverol
 * @version 0.1-SNAPSHOT
 */
public class Peptide {

    private List<AminoAcid> acidList;
    private Group n_terminal;
    private Group c_terminal;

    // the ptm position from [0, sequence.length-1]
    private Map<Integer, PTModification> ptm = new HashMap<Integer, PTModification>();

    private List<AminoAcid> generateAminoAcids(String sequence) {
        List<AminoAcid> acidList = new ArrayList<AminoAcid>();

        char[] cList = sequence.toCharArray();
        int position = 0;
        for (char c : cList) {
            AminoAcid acid = AminoAcid.getAminoAcid(c);
            if (null == acid) {
                throw new IllegalArgumentException("There exist unrecognized AminoAcids in the sequence. position=" + position);
            }

            acidList.add(acid);
            position++;
        }


        return acidList;
    }

    /**
     * Create a peptide without PTM. C terminal is {@link Group#H}, and n terminal is {@link Group#OH}.
     * @param sequence can not set null or space string. System will translate sequence into a list of
     *                 {@link AminoAcid}. If can not recognize the amino acid character, system will throw
     *                 IllegalArgumentException.
     */
    public Peptide(String sequence) {
        this(sequence, null);
    }

    /**
     * Create a peptide without PTM.
     * @param sequence can not set null or space string. System will translate sequence into a list of
     *                 {@link AminoAcid}. If can not recognize the amino acid character, system will throw
     *                 IllegalArgumentException.
     * @param n_terminal can set null, if peptide have no N terminal.
     * @param c_terminal can set null, if peptide have no C terminal.
     */
    public Peptide(String sequence, Group n_terminal, Group c_terminal) {
        this(sequence, n_terminal, c_terminal, null);
    }

    /**
     * Create a peptide with PTM. C terminal is {@link Group#H}, and n terminal is {@link Group#OH}.
     * @param sequence can not set null or space string. System will translate sequence into a list of
     *                 {@link AminoAcid}. If can not recognize the amino acid character, system will throw
     *                 IllegalArgumentException.
     * @param ptm can set null, if no PTM on the peptide. Otherwise,
     *            system call {@link #addALLModification(java.util.Map)} method to put ptm.
     */
    public Peptide(String sequence, Map<Integer, PTModification> ptm) {
        this(sequence, Group.H, Group.OH, ptm);
    }

    /**
     * Create a peptide with PTM.
     * @param sequence can not set null or space string. System will translate sequence into a list of
     *                 {@link AminoAcid}. If can not recognize the amino acid character, system will throw
     *                 IllegalArgumentException.
     * @param n_terminal can set null, if peptide have no N terminal.
     * @param c_terminal can set null, if peptide have no C terminal.
     * @param ptm can set null, if no PTM on the peptide. Otherwise,
     *            system call {@link #addALLModification(java.util.Map)} method to put ptm.
     */
    public Peptide(String sequence, Group n_terminal, Group c_terminal, Map<Integer, PTModification> ptm) {
        if (sequence == null || sequence.trim().length() == 0) {
            throw new IllegalArgumentException("peptide ion sequence is empty! ");
        }

        this.acidList = generateAminoAcids(sequence);
        this.n_terminal = n_terminal;
        this.c_terminal = c_terminal;

        addALLModification(ptm);
    }




    /**
     * Create a peptide without PTM. C terminal is {@link Group#H}, and n terminal is {@link Group#OH}.
     * @param acidList amino acid list. Can not set null or empty list.
     */
    public Peptide(List<AminoAcid> acidList) {
        this(acidList, null);
    }

    /**
     * Create a peptide without PTM.
     * @param acidList amino acid list. Can not set null or empty list.
     * @param n_terminal can set null, if peptide have no N terminal.
     * @param c_terminal can set null, if peptide have no C terminal.
     */
    public Peptide(List<AminoAcid> acidList, Group n_terminal, Group c_terminal) {
        this(acidList, n_terminal, c_terminal, null);
    }

    /**
     * Create a peptide with PTM. C terminal is {@link Group#H}, and n terminal is {@link Group#OH}.
     * @param acidList amino acid list. Can not set null or empty list.
     * @param ptm can set null, if no PTM on the peptide. Otherwise,
     *            system call {@link #addALLModification(java.util.Map)} method to put ptm.
     */
    public Peptide(List<AminoAcid> acidList, Map<Integer, PTModification> ptm) {
        this(acidList, Group.H, Group.OH, ptm);
    }

    /**
     * Create a peptide with PTM.
     * @param acidList amino acid list. Can not set null or empty list.
     * @param n_terminal can set null, if peptide have no N terminal.
     * @param c_terminal can set null, if peptide have no C terminal.
     * @param ptm can set null, if no PTM on the peptide. Otherwise,
     *            system call {@link #addALLModification(java.util.Map)} method to put ptm.
     */
    public Peptide(List<AminoAcid> acidList, Group n_terminal, Group c_terminal, Map<Integer, PTModification> ptm) {
        if (acidList == null || acidList.size() == 0) {
            throw new NullPointerException("Peptide amino acid list is empty!");
        }

        this.acidList = acidList;
        this.n_terminal = n_terminal;
        this.c_terminal = c_terminal;

        addALLModification(ptm);
    }

    /**
     * Add a modification into peptide.
     * @param position value [0..peptide.length-1]. If overflow, return false.
     * @param modification if null, return false.
     */
    public boolean addModification(Integer position, PTModification modification) {
        if (modification == null) {
            return false;
        }

        if (position < 0) {
            return false;
        }

        this.ptm.put(position, modification);

        return true;

    }

    /**
     * If patch add modifications, we will rollback to the point of before patch add.
     */
    public boolean addALLModification(Map<Integer, PTModification> modifications) {
        if (modifications == null) {
            return false;
        }

        // create save point. Because PTModification is read only class, so
        // we can clone ptm by using Map.putAll method.
        Map<Integer, PTModification> tmpPTM = new HashMap<Integer, PTModification>(this.ptm);

        Integer position;
        PTModification modification;
        for (Integer integer : modifications.keySet()) {
            position = integer;
            modification = modifications.get(position);

            if (!addModification(position, modification)) {
                //rollback to save point.
                ptm = tmpPTM;
                return false;
            }
        }

        return true;

    }

    public void removeModification(int location) {
        ptm.remove(location);
    }

    public void removeModification(PTModification modification) {
        ptm.remove(modification);
    }

    public void clearModifications() {
        ptm.clear();
    }

    public String getSequence() {
        if (acidList == null || acidList.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (AminoAcid acid : acidList) {
            sb.append(acid.getOneLetterCode());
        }

        return sb.toString();
    }

    public List<AminoAcid> getAminoAcids() {
        return acidList;
    }

    public Group getNTerminalGroup() {
        return n_terminal;
    }

    public Group getCTerminalGroup() {
        return c_terminal;
    }

    public int getLength() {
        return acidList.size();
    }

    /**
     * @return a unmodifiable collection, which just be used to browse the ptm contents.
     */
    public Map<Integer, PTModification> getPTM() {
        return Collections.unmodifiableMap(ptm);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (n_terminal != null && ! n_terminal.equals(Group.H)) {
            sb.append(n_terminal.getName()).append("-");
        }

        sb.append(getSequence());

        if (c_terminal != null && ! c_terminal.equals(Group.OH)) {
            sb.append("-").append(c_terminal.getName());
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Peptide peptide = (Peptide) o;

        if (acidList != null ? !acidList.equals(peptide.acidList) : peptide.acidList != null) return false;
        if (c_terminal != null ? !c_terminal.equals(peptide.c_terminal) : peptide.c_terminal != null) return false;
        if (n_terminal != null ? !n_terminal.equals(peptide.n_terminal) : peptide.n_terminal != null) return false;
        return ptm != null ? ptm.equals(peptide.ptm) : peptide.ptm == null;
    }

    @Override
    public int hashCode() {
        int result = acidList != null ? acidList.hashCode() : 0;
        result = 31 * result + (n_terminal != null ? n_terminal.hashCode() : 0);
        result = 31 * result + (c_terminal != null ? c_terminal.hashCode() : 0);
        result = 31 * result + (ptm != null ? ptm.hashCode() : 0);
        return result;
    }
}
