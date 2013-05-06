package com.telenor.fun.reader.run;

import com.telenor.fun.reader.utils.CsvUtils;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author <a href="vegard.aasen@telenor.com">Vegard Aasen</a>
 */
public class Run {

    public static void main(String... args) {
        try {
            CsvUtils.setDelimiter(";");
            CsvUtils.setCharset(Charset.forName("windows-1252"));
            Map<Integer, Map<String, String>> csvElements = CsvUtils.getElementsFromCsv(
                    "C:\\temp\\certificates\\all_certs_020513.csv",
                    true
            );
            System.out.println(String.format("Amount of elements {%s}..", csvElements.size()));
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
        System.exit(1);
    }

}
