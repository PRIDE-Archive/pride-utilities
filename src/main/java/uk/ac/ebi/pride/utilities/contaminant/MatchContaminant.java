package uk.ac.ebi.pride.utilities.contaminant;

import org.apache.commons.lang3.StringUtils;
import uk.ac.ebi.pride.utilities.exception.ContaminantFileException;

import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FileUtils;

public class MatchContaminant {

    private static MatchContaminant instance;
    private String contaminantSequence;

    /**
     * Private Constructor
     */
    private MatchContaminant() throws ContaminantFileException {
        try {
            File contaminatsFile;
            URL url = MatchContaminant.class.getClassLoader().getResource("contaminants/contaminants.fasta");
            if (url == null) {
                throw new ContaminantFileException("Unable to read contaminant file: contaminants/contaminants.fasta");
            } else {
                try {
                    contaminatsFile = File.createTempFile("contaminants", ".fasta");
                    contaminatsFile.deleteOnExit();
                    FileUtils.copyURLToFile(url, contaminatsFile);
                } catch (IOException e) {
                    throw new ContaminantFileException("Unable to read contaminant file: contaminants/contaminants.fasta");
                }
            }
            contaminantSequence = FastaReaderContaminantHelper.getInstance(contaminatsFile).allSequences().toUpperCase();
        } catch (Exception e) {
            throw new ContaminantFileException("The Contaminant File cannot been found: \n" + e.toString());
        }
    }

    /**
     * Singleton class to retrieve the MatchContaminant
     * @return the single MatchContaminant instance.
     */
    public static MatchContaminant instance() throws ContaminantFileException {
        if (instance == null){
            instance = new MatchContaminant();
        }
        return instance;
    }


  /**
   * Tests if the provided sequence is a contaminate or not.
   * @param sequence the peptide sequence to test.
   * @return true if the peptide is a contaminant, false otherwise.
   */
    public boolean isPeptideContaminant(String sequence){
        return (contaminantSequence != null && contaminantSequence.contains(sequence.toUpperCase()));
    }


  /**
   * Private class to handle reading in the contaminant FASTA file.
   */
  private static class FastaReaderContaminantHelper{
        private LineNumberReader br;
        private String lastLineRead;

        /**
         * Get an instance of this class
         * @throws Exception If there is a problem
         */
        static FastaReaderContaminantHelper getInstance(File fastaFile) throws Exception {
            if (fastaFile == null) {
                throw new IllegalArgumentException("Fasta file cannot be null");
            }
            if (!fastaFile.exists()) {
                throw new IllegalArgumentException("Fasta file does not exist: " + fastaFile.getPath());
            }
            return getInstance(new FileInputStream(fastaFile));
        }

        /**
         * Get an instance of this FastaReaderContaminantHelper class.
         */
        static FastaReaderContaminantHelper getInstance(InputStream inputStream) {
            if (inputStream == null) {
                throw new IllegalArgumentException( "InputStream cannot be null" );
            }
            FastaReaderContaminantHelper reader = new FastaReaderContaminantHelper();
            reader.br = new LineNumberReader(new InputStreamReader(inputStream));
            return reader;
        }

        /**
         * Reads all the contaminant sequences.
         * @return all the sequences, separated by new lines.
         * @throws Exception problems reading from the contaminates FASTA file.
         */
        String allSequences() throws Exception {
            StringBuilder allSequences = new StringBuilder("");
            String line;
            while((line = readNext()) != null){
                allSequences.append(line).append("\n");
            }
            return allSequences.toString();
        }

        /**
         * Reads in the next sequence from the contaminates file.
         * @return the next contaminates sequence, null for no further sequences left.
         * @throws Exception problems reading from the contaminates FASTA file.
         */
        String readNext() throws Exception {
            /* It is assumed the last read correctly returned a set of headers and a sequence.
             * It is therefore assumed the BufferedReader's next line read will be a header line
             * followed by sequence lines, unless there are no more lines left to read. */
            String line;
            if (this.br.getLineNumber()==0) {
                this.lastLineRead = this.br.readLine();
            }
            line = this.lastLineRead;
            if (line==null) {
                return null; // we've reached the end of the file
            }
            while (StringUtils.isBlank(line)) { //skip through empty lines
                line = this.br.readLine();
                this.lastLineRead = line;
                if (line==null) {
                    return null; // we've reached the end of the file
                }
            }
            if (!line.startsWith( ">" ) ) {
                throw new ContaminantFileException( "Line Number: " + this.br.getLineNumber() + " - Expected header line, but line did not start with \">\"." );
            }
            Set<String> headers = new HashSet<>(); // todo unused currently
            StringBuilder sequence = new StringBuilder();
            line = line.substring(1, line.length());	// strip off the leading ">" on the header line
            /* In FASTA files, multiple headers can be associated with the same sequence, and will
             * be present on the same line.  The separate headers are separated by the CONTROL-A
             * character, so we split on that here, and save each to the headers Set */
            Collections.addAll(headers, line.split("\\cA"));
            line = this.br.readLine();  // must be a sequence line
            this.lastLineRead = line;
            while (line.startsWith( ";" )) {
                line = this.br.readLine();
                this.lastLineRead = line;
            }
            if (StringUtils.isEmpty(line) || line.startsWith( ">" )) {
                throw new ContaminantFileException( "Did not get a sequence line after a header line (Line Number: " + this.br.getLineNumber() );
            }
            while (line!=null) { // read sequence lines until we hit the next header line, or the end of the file
                if (line.startsWith( ">" )) { // we've reached a new header line
                    break;
                }
                if (!line.startsWith( ";" )) { // build the sequence, it's not a comment line
                    line = line.toUpperCase();
                    sequence.append(line);
                }
                line = this.br.readLine();
                this.lastLineRead = line;
            }
            if (sequence.length()<1) {
                throw new ContaminantFileException( "Unable to read the next sequence properly, perhaps the file is badly formatted? (Line Number: " + this.br.getLineNumber());
            }
            return sequence.toString().trim(); // the next sequence entry in the FASTA file
        }
    }
}


