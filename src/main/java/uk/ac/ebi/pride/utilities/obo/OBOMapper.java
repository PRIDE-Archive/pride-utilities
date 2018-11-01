package uk.ac.ebi.pride.utilities.obo;

import org.apache.log4j.Logger;
import org.obolibrary.oboformat.model.Frame;
import org.obolibrary.oboformat.model.OBODoc;
import org.obolibrary.oboformat.parser.OBOFormatParser;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;


/**
 * Parser for the MS-OBO ontology of the HUPO-PSI.
 *
 * @author ypriverol
 *
 */
public class OBOMapper {

    /** the logger for this class */
    private static final Logger LOGGER = Logger.getLogger(OBOMapper.class);
    private OBODoc oboDoc = null;

    InputStream source;
    private HashMap<String, OboCvTerm> termMap;

    public OBOMapper(File source) {
        OBOFormatParser oboReader = new OBOFormatParser();
        try{
            this.source = new FileInputStream(source);
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(this.source));
            oboDoc = oboReader.parse(bufferedReader);
            oboDoc.getInstanceFrames();
            initMapper();
        } catch (IOException e) {
            LOGGER.error("The File can't be open --" + source.getAbsolutePath() + e.getMessage());
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
            LOGGER.error("The File can't be open --" + source.toString() + e.getMessage());
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

    public static OBOMapper getPSIMSInstance() throws URISyntaxException {
        InputStream url = OBOMapper.class.getClassLoader().getResourceAsStream("obo/psi-ms.obo");
        OBOMapper mapper = new OBOMapper(url);
        return mapper;
    }

    public Set<OboCvTerm> getTerms() {
        return new HashSet<>(termMap.values());
    }

    /**
     * Gets the entry in the OBO with the given name. If none is found, returns
     * null.
     * @return
     */
    public OboCvTerm getTermByAccession(String accession) {
        if(termMap.containsKey(accession))
            return termMap.get(accession);
        return null;
    }

}
