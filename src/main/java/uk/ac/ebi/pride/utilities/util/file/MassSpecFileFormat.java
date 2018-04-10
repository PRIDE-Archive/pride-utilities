package uk.ac.ebi.pride.utilities.util.file;



import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Different mass spec file format, also include support methods for detecting the format
 *
 * @author Rui Wang
 * @version $Id$
 *          <p/>
 *          todo: add quant file formats
 */
public enum MassSpecFileFormat {

    MGF("mgf", true),
    MZML("mzml", true),
    MZIDENTML("mzid", true),
    PRIDE("xml", true),
    DAT("dat", true),
    XTANDEM("xml", true),
    PKL("pkl", true),
    PKL_SPO("spo", true),
    SEQUEST_DTA("dta", true),
    SEQUEST_OUT("out", true),
    OMSSA_OMX("omx", true),
    MSGF("msgf", true),
    SPECTRAST("xls", true),
    CRUX("txt", true),
    PEPTIDE_PROPHET("pepxml", true),
    PROTEIN_PROPHET("protxml", true),
    PROTEIN_PILOT("group", true),
    VEMS_PKX("pkx", true),
    MS2("ms2", true),
    MZDATA("mzdata", true),
    MZXML("mzxml", true),
    MZTAB("mztab", true),
    BRUKER_BAF("baf", true),
    BRUKER_FID("fid", true),
    BRUKER_YEP("yep", true),
    ABI_WIFF("wiff", true),
    ABI_SCAN("scan", true),
    ABI_MTD("mtd", true),
    RAW("raw", true),
    AGILENT_MASSHUNTER_RAW("d", false),
    IBD("ibd", true),
    IMG("img", true),
    IMZML("imzml", true),
    HDR ("hdr", true),
    FASTA("fasta", true),
    TIF("tif", true),
    GIF("gif", true),
    PNG("png", true),
    JPG("jpg", true);


    private String fileExtension;
    private boolean fileFormat;


    MassSpecFileFormat(String fileExtension, boolean fileFormat) {
        this.fileExtension = fileExtension;
        this.fileFormat = fileFormat;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public boolean isFileFormat() {
        return fileFormat;
    }

    /**
     * Check whether a folder is a recognized mass spec data folder
     *
     * @param folder input folder
     * @return boolean true means mass spec data folder
     */
    public static boolean isMassSpecDataFolder(File folder) throws IOException {
        boolean msFolder = false;

        if (folder.isDirectory()) {
            MassSpecFileFormat format = checkFormat(folder);
            if (format != null) {
                msFolder = true;
            }
        }

        return msFolder;
    }

    /**
     * Detect mass spec file format
     *
     * @param file input file
     * @return MassSpecFileFormat  mass spec file format
     */
    public static MassSpecFileFormat checkFormat(File file) throws IOException {
        MassSpecFileFormat format = null;

        boolean isFile = file.isFile();

        String ext = FileUtil.getFileExtension(file);

        boolean emptyFile = !FileUtil.isFileEmpty(file);

        if (ext != null && emptyFile) {
            if ("xml".equals(ext.toLowerCase())) {
                // read file content to detect the type
                format = checkXmlFormat(file);
            } else if ("zip".equals(ext.toLowerCase())) {
                format = checkZippedFileExtension(file);
                if (format == null) {
                    format = checkZippedFileContent(file);
                }
            } else if ("gz".equals(ext.toLowerCase())) {
                format = checkGzippedFileExtension(file);
                if (format == null) {
                    format = checkGzippedFileContent(file);
                }
            } else if ("txt".equals(ext.toLowerCase())) {
                format = null;
            } else if ("xls".equals(ext.toLowerCase())) {
                format = null;
            } else {
                format = checkFormat(ext, isFile);
            }
        }

        return format;
    }

    /**
     * Detect mass spec file format
     *
     * @param url input URL
     * @return MassSpecFileFormat  mass spec file format
     */
    public static MassSpecFileFormat checkFormat(URL url) throws IOException {

        MassSpecFileFormat format = null;

        String urlPath = url.getFile();

        String ext = getUrlFileExtension(urlPath);

        if (ext != null) {
            if ("xml".equalsIgnoreCase(ext)) {
                // Try to detect the type from file name ending
                if (urlPath.endsWith(".xt.xml")) {
                    format = MassSpecFileFormat.XTANDEM;
                } else if (urlPath.endsWith(".pride.xml")) {
                    format = MassSpecFileFormat.PRIDE;
                }
            } else if ("zip".equalsIgnoreCase(ext)) {
                format = checkCompressedFormat(urlPath, "zip");
            } else if ("gz".equalsIgnoreCase(ext)) {
                format = checkCompressedFormat(urlPath, "gz");
            } else if ("txt".equalsIgnoreCase(ext)) {
                format = null;
            } else if ("xls".equalsIgnoreCase(ext)) {
                format = null;
            } else {
                format = checkFormat(ext, true);
            }
        }

        return format;
    }

    private static String getUrlFileExtension(String urlPath) {
        String ext = null;
        int lastDotIndex = urlPath.lastIndexOf(".");
        if (lastDotIndex >= 0) {
            // Exclude '.' from file extension
            ext = urlPath.substring(lastDotIndex + 1).toLowerCase();
        }
        return ext;
    }

    /**
     * Detect file format of compressed file by detecting file extension
     *
     * @param filename filename of compressed file
     * @param comprExt filename extension of compressed file, excluding dot '.'
     * @return MassSpecFileFormat mass spec file format
     */
    private static MassSpecFileFormat checkCompressedFormat(String filename, String comprExt) {

        // Remove compression file extension from filename to check
        String testFilename = filename.substring(0, (filename.length() - comprExt.length()));

        // Get filename extension for uncompressed file
        String ext = FileUtil.getFileExtension(new File(testFilename));

        for (MassSpecFileFormat value : values()) {
            if (value.getFileExtension().equals(ext) && value.isFileFormat()) {
                return value;
            }
        }

        return null;
    }

    /**
     * Detect file format by detecting file extension
     *
     * @param ext    extension of a given file or folder
     * @param isFile whether input is a file
     * @return MassSpecFileFormat  mass spec file format
     */
    private static MassSpecFileFormat checkFormat(String ext, boolean isFile) {
        for (MassSpecFileFormat value : values()) {
            if (value.getFileExtension().equalsIgnoreCase(ext) && (value.isFileFormat() == isFile)) {
                return value;
            }
        }

        return null;
    }

    /**
     * Detect the file format of a xml file
     *
     * @param file xml file
     * @return MassSpecFileFormat  mass spec file format
     */
    private static MassSpecFileFormat checkXmlFormat(File file) throws IOException {
        MassSpecFileFormat format = null;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            // read first ten lines
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                content.append(reader.readLine());
            }
            // detect format
            format = detectFormat(content.toString());
        } catch (IOException ioe) {
            //do nothing here
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return format;
    }

    private static MassSpecFileFormat checkZippedFileExtension(File file) throws IOException {
        MassSpecFileFormat format = null;

        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(file);

            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            if (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String fileName = entry.getName();
                String fileExtension = FileUtil.getFileExtension(fileName);
                format = checkFormat(fileExtension, true);
            }
        } finally {
            if (zipFile != null) {
                zipFile.close();
            }
        }

        return format;
    }

    /**
     * Check the file format of a zip file
     *
     * @param file zipped file
     * @return mass spec file format
     */
    private static MassSpecFileFormat checkZippedFileContent(File file) throws IOException {
        MassSpecFileFormat format = null;

        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(file);

            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            if (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                // reading buffer size
                int BUFFER = 1048;
                byte data[] = new byte[BUFFER];

                InputStream inputStream = zipFile.getInputStream(entry);
                inputStream.read(data, 0, BUFFER);

                // convert byte array to string
                String content = new String(data);
                format = detectFormat(content);
            }
        } finally {
            if (zipFile != null) {
                zipFile.close();
            }
        }

        return format;
    }

    private static MassSpecFileFormat checkGzippedFileExtension(File file) throws IOException {
        MassSpecFileFormat format;

        String fileName = file.getName();
        fileName = fileName.substring(0, fileName.length() - 3);
        String fileExtension = FileUtil.getFileExtension(fileName);
        format = checkFormat(fileExtension, true);

        return format;
    }

    /**
     * Check the file format of a gzipped file
     *
     * @param file gzipped file
     * @return mass spec file format
     */
    private static MassSpecFileFormat checkGzippedFileContent(File file) throws IOException {
        MassSpecFileFormat format;

        GZIPInputStream gzipInputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            gzipInputStream = new GZIPInputStream(new BufferedInputStream(fileInputStream));

            // reading buffer size
            int BUFFER = 1048;
            byte data[] = new byte[BUFFER];

            gzipInputStream.read(data, 0, BUFFER);

            // convert byte array to string
            String content = new String(data);
            format = detectFormat(content);

        } finally {
            if (gzipInputStream != null) {
                gzipInputStream.close();
            }
        }

        return format;
    }

    /**
     * Detect the format based on a given string
     *
     * @param content input content
     * @return mass spec file format
     */
    private static MassSpecFileFormat detectFormat(String content) {
        MassSpecFileFormat format = null;

        if (MassSpecFileRegx.PRIDE_XML_PATTERN.matcher(content).find()) {
            format = PRIDE;
        } else if (MassSpecFileRegx.MZML_PATTERN.matcher(content).find()) {
            format = MZML;
        } else if (MassSpecFileRegx.MZIDENTML_PATTERN.matcher(content).find()) {
            format = MZIDENTML;
        } else if (MassSpecFileRegx.MZXML_PATTERN.matcher(content).find()) {
            format = MZXML;
        } else if (MassSpecFileRegx.MZDATA_PATTERN.matcher(content).find()) {
            format = MZDATA;
        }

        return format;
    }
}
