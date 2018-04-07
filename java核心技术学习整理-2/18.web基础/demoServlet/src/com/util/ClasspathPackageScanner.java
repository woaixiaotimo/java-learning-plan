package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Created by 啊Q on 2018/4/5.
 */
public class ClasspathPackageScanner {

    private String basePackage;
    private ClassLoader cl;

    /**
     * 用基础包和类加载器构造一个实例。
     *
     * @param basePackage 要扫描的基本包
     */
    public ClasspathPackageScanner(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();
    }

    /**
     * 用基础包和类加载器构造一个实例。
     *
     * @param basePackage 要扫描的基本包
     * @param cl          使用此类加载来查找包     
     */
    public ClasspathPackageScanner(String basePackage, ClassLoader cl) {
        this.basePackage = basePackage;
        this.cl = cl;
    }

    /**
     * 获取位于指定包中的所有完全限定名称
     * 及其子包。
     *
     * @return 全名的列表。
     * @throws IOException       
     */
    public List<String> getFullyQualifiedClassNameList() throws IOException {
        return doScan(basePackage, new ArrayList());
    }

    /**
     * 实际上执行扫描程序。
     *      
     *
     * @param basePackage 包路径
     * @param nameList    包含结果的列表。
     * @return 全名的列表。
     * @throws IOException      
     */
    private List<String> doScan(String basePackage, List<String> nameList) throws IOException {
        //将包路径转换为文件路径
        String splashPath = StringUtil.dotToSplash(basePackage);

        //获取文件的绝对路径
        URL url = cl.getResource(splashPath);
        String filePath = StringUtil.getRootPath(url);
        filePath = java.net.URLDecoder.decode(filePath, "utf-8");

        //获取该包中的类。
        //如果Web服务器解压缩jar文件，那么这些类将以。的形式存在
        //目录中的普通文件。
        //如果Web服务器没有解压缩该jar文件，那么类将存在于jar文件中。
        List<String> names = null; //包含类文件的名称。 例如，Apple.class将被存储为“Apple”
        if (isJarFile(filePath)) {
            // jar file
//            if (logger.isDebugEnabled()) {
//                logger.debug("{} 是一个JAR包", filePath);
//            }

            names = readFromJarFile(filePath, splashPath);
        } else {
            // directory
//            if (logger.isDebugEnabled()) {
//                logger.debug("{} 是一个目录", filePath);
//            }

            names = readFromDirectory(filePath);
        }

        for (String name : names) {
            if (isClassFile(name)) {
                //nameList.add(basePackage + "." + StringUtil.trimExtension(name));
                nameList.add(toFullyQualifiedName(name, basePackage));
            } else {
                // this is a directory
                // check this directory for more classes
                // do recursive invocation
                doScan(basePackage + "." + name, nameList);
            }
        }

//        if (logger.isDebugEnabled()) {
//            for (String n : nameList) {
//                logger.debug("找到{}", n);
//            }
//        }

        return nameList;
    }

    /**
     * Convert short class name to fully qualified name.
     * e.g., String -> java.lang.String
     */
    private String toFullyQualifiedName(String shortName, String basePackage) {
        StringBuilder sb = new StringBuilder(basePackage);
        sb.append('.');
        sb.append(StringUtil.trimExtension(shortName));

        return sb.toString();
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
//        if (logger.isDebugEnabled()) {
//            logger.debug("从JAR包中读取类: {}", jarPath);
//        }

        JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
        JarEntry entry = jarIn.getNextJarEntry();

        List<String> nameList = new ArrayList<String>();
        while (null != entry) {
            String name = entry.getName();
            if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                nameList.add(name);
            }

            entry = jarIn.getNextJarEntry();
        }

        return nameList;
    }

    private List<String> readFromDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();

        if (null == names) {
            return null;
        }

        return Arrays.asList(names);
    }

    private boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    private boolean isJarFile(String name) {
        return name.endsWith(".jar");
    }

    /**
     * For test purpose.
     */
    public static void main(String[] args) throws Exception {
        ClasspathPackageScanner scan = new ClasspathPackageScanner("test");
        List<String> list = scan.getFullyQualifiedClassNameList();
        System.out.println("list = " + list);
    }

}
