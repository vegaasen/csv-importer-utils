package com.telenor.fun.reader.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="vegard.aasen@telenor.com">Vegard Aasen</a>
 */
public final class CsvUtilsTest {

    private String location;
    private String locationForStrangeFormat;
    private File csvFile;

    @Before
    public void setUp() throws FileNotFoundException {
        location = "C:\\_dev\\workspace_telenor\\playhouse\\file-reader\\src\\test\\resources\\test.csv";
        locationForStrangeFormat = "C:\\_dev\\workspace_telenor\\playhouse\\file-reader\\src\\test\\resources\\test_strange_delim.csv";
        csvFile = FileUtils.getFile(location);
    }

    @Test
    public void shouldGetObjectFromCsvFile_omittedStuff() {
        CsvUtils.setOmitFirstLine(true);
        final String expectedElement = "123";
        final Map<Integer, Map<String, String>> csvElements = CsvUtils.getElementsFromCsv(csvFile, true);
        assertNotNull(csvElements);
        assertFalse(csvElements.isEmpty());
        Map<String, String> firstRow = csvElements.get(0);
        assertNotNull(firstRow);
        assertFalse(firstRow.isEmpty());
        assertEquals(expectedElement, firstRow.get("Id"));
    }

    @Test
    public void shouldGetObjectFromCsvFile() {
        final String expectedElement = "Id";
        final Map<Integer, Map<String, String>> csvElements = CsvUtils.getElementsFromCsv(csvFile);
        assertNotNull(csvElements);
        assertFalse(csvElements.isEmpty());
        Map<String, String> firstRow = csvElements.get(0);
        assertNotNull(firstRow);
        assertFalse(firstRow.isEmpty());
        assertEquals(expectedElement, firstRow.get("0"));
    }

    @Test
    public void shouldGetObjectFromCsvFile_withTitles() {
        final String expectedElement = "Id";
        final Map<Integer, Map<String, String>> csvElements = CsvUtils.getElementsFromCsv(csvFile, true);
        assertNotNull(csvElements);
        assertFalse(csvElements.isEmpty());
        Map<String, String> firstRow = csvElements.get(0);
        assertNotNull(firstRow);
        assertFalse(firstRow.isEmpty());
        assertEquals(expectedElement, firstRow.get("Id"));
    }

    @Test
    public void shouldGetObjectFromCsvFilePath() throws FileNotFoundException {
        final String expectedElement = "123";
        final Map<Integer, Map<String, String>> csvElements = CsvUtils.getElementsFromCsv(location);
        assertNotNull(csvElements);
        assertFalse(csvElements.isEmpty());
        Map<String, String> firstRow = csvElements.get(1);
        assertNotNull(firstRow);
        assertFalse(firstRow.isEmpty());
        assertEquals(expectedElement, firstRow.get("0"));
    }

    @Test
    public void shouldGetStuffFromCsvFile_withTitles() throws FileNotFoundException {
        final String expectedElement = "Id";
        final Map<Integer, Map<String, String>> csvElements = CsvUtils.getElementsFromCsv(location, true);
        assertNotNull(csvElements);
        assertFalse(csvElements.isEmpty());
        Map<String, String> firstRow = csvElements.get(0);
        assertNotNull(firstRow);
        assertFalse(firstRow.isEmpty());
        assertEquals(expectedElement, firstRow.get("Id"));
    }

    @Test
    public void shouldGetStuffFromCsvByStringFilePath_strangeDelimiter_with_headers() throws FileNotFoundException {
        CsvUtils.setDelimiter("#");
        final String expectedElement = "123";
        final Map<Integer, Map<String, String>> csvElements =
                CsvUtils.getElementsFromCsv(locationForStrangeFormat, true);
        assertNotNull(csvElements);
        assertFalse(csvElements.isEmpty());
        Map<String, String> firstRow = csvElements.get(1);
        assertNotNull(firstRow);
        assertFalse(firstRow.isEmpty());
        assertEquals(expectedElement,firstRow.get("Id"));
    }

    @Test
    public void shouldSetCharsetAndStillReturnCorrectStuff_ISO88591() throws FileNotFoundException {
        CsvUtils.setDelimiter("#");
        CsvUtils.setCharset(Charset.forName("ISO-8859-1"));
        final String expectedElement = "123";
        final Map<Integer, Map<String, String>> csvElements =
                CsvUtils.getElementsFromCsv(locationForStrangeFormat, true);
        assertNotNull(csvElements);
        assertFalse(csvElements.isEmpty());
        Map<String, String> firstRow = csvElements.get(1);
        assertNotNull(firstRow);
        assertFalse(firstRow.isEmpty());
        assertEquals(expectedElement,firstRow.get("Id"));
    }

    @Test
    public void shouldSetCharsetAndStillReturnCorrectStuff_UTF8() throws FileNotFoundException {
        CsvUtils.setDelimiter("#");
        CsvUtils.setCharset(Charset.forName("UTF-8"));
        final String expectedElement = "123";
        final Map<Integer, Map<String, String>> csvElements =
                CsvUtils.getElementsFromCsv(locationForStrangeFormat, true);
        assertNotNull(csvElements);
        assertFalse(csvElements.isEmpty());
        Map<String, String> firstRow = csvElements.get(1);
        assertNotNull(firstRow);
        assertFalse(firstRow.isEmpty());
        assertEquals(expectedElement,firstRow.get("Id"));
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldNotFindCsvFile() throws FileNotFoundException {
        CsvUtils.getElementsFromCsv("C:\\does\\not\\exists\\text.csv");
    }

    @After
    public void tearDown() {
        CsvUtils.setDelimiter(String.valueOf(CsvUtils.DEFAULT_DELIMITER));
    }

}
