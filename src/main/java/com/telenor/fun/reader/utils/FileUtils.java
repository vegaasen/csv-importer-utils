package com.telenor.fun.reader.utils;

import java.io.*;

/**
 * @author <a href="vegard.aasen@telenor.com">Vegard Aasen</a>
 */
public class FileUtils {

    private static final int DEFAULT_BUFFER_SIZE = 65536;
    private static int bufferSize = DEFAULT_BUFFER_SIZE;

    public static File getFile(final String filename) throws FileNotFoundException {
        if (!"".equals(filename)) {
            final File file = new File(filename);
            if(file.exists()) {
                return file;
            }
            throw new FileNotFoundException(String.format("Unable to find file %s", filename));
        }
        throw new IllegalArgumentException("Missing argument fileName.");
    }

    public static BufferedReader getBufferedReader(final File file) throws FileNotFoundException {
        if(file!=null) {
            return new BufferedReader(new FileReader(file));
        }
        throw new IllegalArgumentException("Missing argument");
    }

    public static BufferedInputStream readStreamFromFile(final File file) throws IOException {
        final int size = (int) file.length();
        try {
            return new BufferedInputStream(new FileInputStream(file), bufferSize);
        } catch (final FileNotFoundException e) {
            throw new IOException("File not found");
        }
    }

    public static BufferedInputStream readStreamFromFile(final String filename) throws IOException {
        final File file = new File(filename);
        return readStreamFromFile(file);
    }

    public static byte[] readBytesFromFile(final String filename) throws IOException {
        final File file = new File(filename);
        final int size = (int) file.length();
        final byte[] buff = new byte[size];
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file), bufferSize);
            in.read(buff, 0, size);
            return buff;
        } catch (final FileNotFoundException e) {
            throw new IOException("File `" + filename + "' not found");
        } catch (final IOException e) {
            throw new IOException("Error writing file `" + filename + "'", e);
        } finally {
            closeSilently(in);
        }
    }

    public static void closeSilently(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (final IOException e) {
            //
        }
    }

}
