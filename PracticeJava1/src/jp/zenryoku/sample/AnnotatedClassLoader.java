package jp.zenryoku.sample;

import javafx.scene.effect.Reflection;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * ClassLoaderクラスを拡張する。
 */
public class AnnotatedClassLoader extends ClassLoader{
    /**
     * テスト用のメインメソッド
     * @param args
     */
    public static void main(String[] args) {
        String pkgName = "jp.zenryoku.sample";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            System.out.println("Error");
            System.exit(-1);
        }
        List<File> dirList = new ArrayList<>();
        try {
            Enumeration<URL> meration = loader.getResources(pkgName);
            while(meration.hasMoreElements()) {
                URL u = meration.nextElement();
                System.out.println("File: " + u.getFile());
                dirList.add(new File(u.getFile()));
            }
            List<Class> clsList = new ArrayList<>();
            for(File f : dirList) {
                clsList.addAll(findClass(f, pkgName));
            }
            System.out.println("*** Testing " + clsList.size() + "***");
            clsList.forEach(clz -> System.out.println(clz.getClass().toString()));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Class> findClass(File f, String pkgName) {
        List<Class> list = new ArrayList<>();
        if (!f.exists()) {
            return list;
        }
        System.out.println("Package: " + pkgName);
        if (f.isDirectory()) {
            assert !f.getName().contains(".");
            list.addAll(findClass(f, pkgName));
        } else if (f.getName().endsWith(".class") || f.getName().endsWith(".java")) {
            try {
                list.add(Class.forName(pkgName + "." + f.getName().substring(f.getName().length() -6)));
            } catch(ClassNotFoundException cne) {
                cne.printStackTrace();
            }
        }
        return list;
    }
}
