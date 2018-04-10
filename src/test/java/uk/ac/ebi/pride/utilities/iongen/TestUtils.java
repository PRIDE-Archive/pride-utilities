package uk.ac.ebi.pride.utilities.iongen;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @author Qingwei XU
 */
public class TestUtils {

    public static Map<String, List<String>> generateTestset(String fileName) {
        BufferedReader reader = null;
        Map<String, List<String>> testSet = new HashMap<String, List<String>>();

        String line;
        String input = null;
        List<String> outputs = null;
        InputStream in = TestUtils.class.getResourceAsStream(fileName);

        try {

            reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                String COMMENT = "#";
                if (line.startsWith(COMMENT)) {
                    continue;
                }

                String INPUT = "INPUT:";
                String OUTPUT = "OUTPUT:";
                if (line.startsWith(INPUT)) {
                    // new test case
                    input = reader.readLine();
                    outputs = new ArrayList<String>();
                } else if (line.startsWith(OUTPUT)) {
                    String END = "END;";
                    while (! (line = reader.readLine()).startsWith(END)) {
                        outputs.add(line);
                    }
                    testSet.put(input, outputs);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return testSet;
    }

    @Test
    public void testGenerateTestset() {
        String file = "/default_product_ions.mascot";
        Map<String, List<String>> testset = generateTestset(file);

        System.out.println(Arrays.toString(testset.keySet().toArray()));
    }
}
