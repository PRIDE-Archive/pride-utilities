package uk.ac.ebi.pride.utilities.contaminant;

import uk.ac.ebi.pride.utilities.exception.ContaminantFileException;

import java.io.*;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * This code is licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * ==Overview==
 * <p>
 * This class check if a peptide match a contaminant and try to remove it.
 * <p>
 * Created by ypriverol (ypriverol@gmail.com) on 24/11/2017.
 */
public class MatchContaminant {

    private static MatchContaminant instance;

    private String contaminantSequence = null;

    /**
     * Private Constructor
     */
    private MatchContaminant() throws ContaminantFileException {
        try {
            URI uriFile = MatchContaminant.class.getClassLoader().getResource("contaminants/contaminants.fasta").toURI();
            File contaminantFile = new File(uriFile);
            contaminantSequence = FastaReaderContaminantHelper.getInstance(contaminantFile).allSequences().toUpperCase();
        } catch (Exception e) {
            throw new  ContaminantFileException("The Contaminant File has been found");
        }
    }

    /**
     * Singleton class to retrieve the MatchContaminant
     * @return
     */
    public static MatchContaminant instance() throws ContaminantFileException {
        if (instance == null){
            instance = new MatchContaminant();
        }
        return instance;
    }


    public boolean isPeptideContaminant(String sequence){
        return (contaminantSequence != null && contaminantSequence.contains(sequence.toUpperCase()));
    }


    private static class FastaReaderContaminantHelper{

        private BufferedReader br;
        private int lineNumber;
        private String lastLineRead;

        /**
         * Get an instance of this class
         * @return
         * @throws Exception If there is a problem
         */
        public static FastaReaderContaminantHelper getInstance( File file ) throws Exception {


            if ( file == null ) {

                throw new IllegalArgumentException( "file may not be null" );
            }

            if ( ! file.exists() ) {

                throw new IllegalArgumentException( "File does not exist: " + file.getAbsolutePath() );
            }

            FileInputStream fileInputStream = new FileInputStream( file );

            return getInstance( fileInputStream );
        }


        /**
         * Get an instance of this class
         * @return
         * @throws Exception If there is a problem
         */
        public static FastaReaderContaminantHelper getInstance( InputStream inputStream ) throws Exception {

            if (inputStream == null)
                throw new IllegalArgumentException( "inputStream may not be null" );


            FastaReaderContaminantHelper reader = new FastaReaderContaminantHelper();

            InputStreamReader isr = new InputStreamReader(inputStream);
            reader.br = new BufferedReader( isr );

            return reader;
        }

        public String allSequences() throws Exception {
            StringBuilder allSequences = new StringBuilder("");
            String line;
            while((line = readNext()) != null){
                allSequences.append(line).append("\n");
            }
            return allSequences.toString();
        }



        public String readNext() throws Exception {

		/*
		 * It is assumed the last read correctly returned a Set of headers and a sequence
		 * So, it is therefor assumed the BufferedReader's next line read will be a header line
		 * followed by sequence lines (unless last read returned false (end of file) )
		 */

            String line;
            if( this.lineNumber == 0 )
                this.lastLineRead = this.br.readLine();

            line = this.lastLineRead;

            if (line == null) return null;			// we've reached the end of the file
            this.lineNumber++;

            if (!line.startsWith( ">" ) )
                throw new ContaminantFileException( "Line Number: " + this.lineNumber + " - Expected header line, but line did not start with \">\"." );

            String headerLine = line;

            // the headers for this entry
            Set<String> headers = new HashSet<>();
            StringBuilder sequence = new StringBuilder();

            line = line.substring(1, line.length());	// strip off the leading ">" on the header line

		/*
		 * In FASTA files, multiple headers can be associated with the same sequence, and will
		 * be present on the same line.  The separate headers are separated by the CONTROL-A
		 * character, so we split on that here, and save each to the headers Set
		 */
            String[] lineHeaders = line.split("\\cA");
            for (String lineHeader : lineHeaders) headers.add(lineHeader);

            // The next line must be a sequence line
            line = this.br.readLine();
            this.lastLineRead = line;

            while (line.startsWith( ";" )) {
                this.lineNumber++;
                line = this.br.readLine();
                this.lastLineRead = line;
            }
            if (line == null || line.startsWith( ">" ))
                throw new ContaminantFileException( "Did not get a sequence line after a header line (Line Number: " + this.lineNumber );


            // loop through the file, reading sequence lines until we hit the next header line (or the end of the file)
            while (line != null) {

                //If we've reached a new header line (marked with a leading ">"), then we're done.
                if (line.startsWith( ">" )) {
                    break;
                }

                this.lineNumber++;

                // build the sequence, if it's not a comment line
                if (!line.startsWith( ";" )) {

                    // upper-case the sequence line
                    line = line.toUpperCase();

                    sequence.append( line );
                }

                line  = this.br.readLine();
                this.lastLineRead = line;
            }

            String sequenceString = sequence.toString();

            sequenceString = sequenceString.trim();

            // If we've made it here, we've read another sequence entry in the FASTA data
            return sequenceString;

        }


    }
}


