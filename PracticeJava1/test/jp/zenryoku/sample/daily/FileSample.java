package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class FileSample {
    private static final String DST_DIR = "resources/files/";
    private static final String FILE_NAME = DST_DIR + "sample.txt";

    private void readFile(File f) throws IOException {
        BufferedReader read = null;
        try {
            read = Files.newBufferedReader(f.toPath());
            String line = null;
            while ((line = read.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            read.close();
        }
    }

    private void writeFile(File f, String toWrite) throws IOException {
        BufferedWriter write = null;
        try {
            write =  Files.newBufferedWriter(f.toPath());
            write.write("This is a file");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            write.close();
        }
    }

    private File sampleFile() {
        return new File(DST_DIR + "sample.txt");
    }

    private File filesDir() {
        return new File(DST_DIR);
    }

    private void failTest(Exception e) {
        e.printStackTrace();
        fail(e.getMessage());
    }

    @Test
    public void testWrite() throws IOException{
        File f = new File(DST_DIR + "test.txt");
        writeFile(f, "This is a Pen");
    }

    @Test
    public void testRead() throws IOException {
        File f = new File(DST_DIR + "sample.txt");
        readFile(f);
    }

    @Test
    public void testFile() {
        File f = new File(DST_DIR);
        String[] st = f.list();
        for (String s : st) {
            System.out.println(s);
        }
    }

    @Test
    public void testCanRead() {
        File f = new File(DST_DIR + "sample1.txt");
        assertTrue(f.canRead());
    }

    @Test
    public void testTmp() throws IOException{
        File dir = new File(DST_DIR);
        File f = File.createTempFile("tmp"
                , ".txt", dir);
    }

    @Test
    public void testDelete() {
        File f = new File(DST_DIR
                + "tmp575838181373531995.txt");
        f.delete();
    }
    @Test
    public void testExist() {
        File f = new File(DST_DIR + "test.txt");
        assertTrue(f.exists());
    }

    @Test
    public void testMkdir() {
        File f = new File(DST_DIR
                + "aaa");
        f.mkdir();
    }

    @Test
    public void testIsFile() {
        File file = sampleFile();
        File dir = filesDir();

        assertTrue(file.isFile());
        assertFalse(dir.isFile());
    }

    @Test
    public void testIsDir() {
        File file = sampleFile();
        File dir = filesDir();
        assertTrue(dir.isDirectory());
        assertFalse(file.isDirectory());

    }
}
