package uk.ac.ebi.pride.utilities.obo;

import org.obolibrary.oboformat.model.Frame;
import org.obolibrary.oboformat.model.OBODoc;
import org.obolibrary.oboformat.parser.OBOFormatParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Parser for the MS-OBO ontology of the HUPO-PSI.
 *
 * @author ypriverol
 *
 */
public class OBOMapper {

    /** the logger for this class */
    private static final Logger LOGGER = Logger.getLogger(OBOMapper.class.getName());
    private OBODoc oboDoc = null;

    InputStream source;
    private HashMap<String, OboCvTerm> termMap;

    public OBOMapper(File source) {
        OBOFormatParser oboReader = new OBOFormatParser();
        try{
            this.source = Files.newInputStream(source.toPath());
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(this.source));
            oboDoc = oboReader.parse(bufferedReader);
            oboDoc.getInstanceFrames();
            initMapper();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"The File can't be open --" + source.getAbsolutePath() + e.getMessage());
        }
    }

    public OBOMapper(InputStream source) {
        this.source = source;
        OBOFormatParser oboReader = new OBOFormatParser();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(source))) {
            oboDoc = oboReader.parse(bufferedReader);
            oboDoc.getInstanceFrames();
            initMapper();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"The File can't be open --" + source + e.getMessage());
        }
    }

    private void initMapper() {
        termMap = new HashMap<>();

        for(Frame frame: oboDoc.getTermFrames()){
            String id = frame.getId();

            String NAME_TAG = "name";
            String name = (String) frame.getTagValue(NAME_TAG);

            String DEF_TAG = "def";
            String description = (String) frame.getTagValue(DEF_TAG);

            termMap.put(id, new OboCvTerm(oboDoc.getHeaderFrame().getTagValue("default-namespace").toString(),name, id));
        }
    }

    public static OBOMapper getPSIMSInstance() {
        OBOMapper mapper = new OBOMapper(OBOMapper.class.getClassLoader().getResourceAsStream("obo/psi-ms.obo"));
        return mapper;
    }

    public Set<OboCvTerm> getTerms() {
        return new HashSet<>(termMap.values());
    }

    /**
     * Gets the entry in the OBO with the given name. If none is found, returns
     * null.
     */
    public OboCvTerm getTermByAccession(String accession) {
        if(termMap.containsKey(accession))
            return termMap.get(accession);
        return null;
    }

}
