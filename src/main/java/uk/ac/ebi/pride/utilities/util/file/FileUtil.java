package uk.ac.ebi.pride.utilities.util.file;


import java.io.*;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Utility class for file access
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class FileUtil {

    static public final String DOT = ".";
    private FileUtil() {
    }
    /**
     * Get file extension for a given file
     *
     * @param file given file
     * @return String  file extension
     */
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        return getFileExtension(fileName);
    }

    public static String getFileExtension(String fileName) {
        int mid = fileName.lastIndexOf(DOT);
        String fileNameExt = null;
        if (mid > 0) {
            fileNameExt = fileName.substring(mid + 1).toLowerCase();
        }

        return fileNameExt;
    }

    /**
     * Check whether a given file is a binary file
     *
     * @param file given file object
     * @return boolean true means binary file
     * @throws java.io.IOException exception while reading the given file
     */
    public static boolean isBinaryFile(File file) throws IOException {
        try (InputStream in = Files.newInputStream(file.toPath())) {
            int size = file.length() > 500 ? 500 : new Long(file.length()).intValue();
            byte[] bytes = new byte[size];

            in.read(bytes, 0, bytes.length);
            short bin = 0;
            for (byte thisByte : bytes) {
                char it = (char) thisByte;
                if (!Character.isWhitespace(it) && !Character.isISOControl(it)) {
                    bin++;
                }

                if (bin >= 5) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String tail(File file, int numberOfChars) throws IOException {
        try (RandomAccessFile fileHandler = new RandomAccessFile(file, "r")) {
            long fileLength = file.length() - 1;
            StringBuilder sb = new StringBuilder();

            for (long filePointer = fileLength; filePointer > (fileLength - numberOfChars); filePointer--) {
                fileHandler.seek(filePointer);
                int readByte = fileHandler.readByte();
                sb.append((char) readByte);
            }

            return sb.reverse().toString();
        }
    }

    public static String getDecompressedFileName(File file) throws IOException {
        String fileName = file.getName();
        ZipFile zipFile = null;


        try {
            // get file as input stream
            if (isGzipped(file)) {
                String compressedFileName = file.getName();
                fileName = compressedFileName.substring(0, compressedFileName.length() - 3);
            } else if (isZipped(file) && file.exists() && file.canRead()) {
                zipFile = new ZipFile(file);
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                // read only the first entry from zip
                if (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    fileName = entry.getName();
                }
            }
        } finally {
            if (zipFile != null) {
                zipFile.close();
            }
        }

        return getRealFileName(fileName);
    }

    public static String getRealFileName(String fileName) {
        String name = fileName;

        if (name.contains("/") || name.contains("\\")) {
            String[] parts = name.split("/");
            name = parts[parts.length - 1];
            parts = name.split("\\\\");
            name = parts[parts.length - 1];
        }

        return name;
    }

    public static InputStream getFileInputStream(File file) throws IOException {
        InputStream fileInputStream = null;

        if (isGzipped(file)) {
            fileInputStream = new GZIPInputStream(Files.newInputStream(file.toPath()));
        } else if (isZipped(file)) {
            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            // read only the first entry from zip
            if (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                fileInputStream = zipFile.getInputStream(entry);
            }
        } else {
            fileInputStream = Files.newInputStream(file.toPath());
        }

        return fileInputStream;
    }

    public static boolean isZipped(File file) {
        String fileExtension = FileUtil.getFileExtension(file);
        return "zip".equals(fileExtension);
    }

    public static boolean isGzipped(File file) {
        String fileExtension = FileUtil.getFileExtension(file);
        return "gz".equals(fileExtension);
    }

    public static boolean isFileEmpty(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine() == null;
        }

    }
}
