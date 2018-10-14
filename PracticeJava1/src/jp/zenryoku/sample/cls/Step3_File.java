package jp.zenryoku.sample.cls;

import java.io.File;

public class Step3_File {
    private int classCount = 0;
    public static void main(String[] args) {
        Step3_File test = new Step3_File();
        try {
            test.testFileDir("jp.zenryoku.sample");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getRootPath(File root, String pkgName) {
        String result = "";
        String tmp = pkgName.replaceAll("\\.", "/");
        String path = root.toPath().toString();
        System.out.println("rootPath: " + pkgName);
        System.out.println("test: " + tmp);
        int chk = path.indexOf(tmp);
        if (chk != -1) {
            System.out.println("path: " + path + " chk: " + chk);
            String tmpPkg = path.substring(chk);
            System.out.println("RoootPkg: " + tmpPkg.replaceAll("/", "."));
        } else {
            result = "";
        }
        return result;
    }

    public void testFileDir(String pkgName) throws Exception{
        File dir = new File(".");
        listPrinter(dir.listFiles(), pkgName);
        System.out.println("All Class is " + classCount);
    }

    private void listPrinter(File dir, String pkgName) {
        if (dir.getName().endsWith(".class")) {
            System.out.println("Package:" + getRootPath(dir, dir.toPath().toString()));
            System.out.println("File: " + printPath(dir, pkgName) + ":" + dir.getName());
            classCount++;
        }
    }

    private void listPrinter(File[] list, String pkgName) throws Exception{
        for (File f : list) {
            if (f.isDirectory()) {
                listPrinter(f.listFiles(), pkgName);
            } else if (f.isFile()) {
                listPrinter(f, pkgName);
            }
        }
    }

    private String printPath(File file, String pkgName) {
        return pkgName;
    }
}
