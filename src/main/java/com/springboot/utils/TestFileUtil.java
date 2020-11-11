package com.springboot.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

public class TestFileUtil {
	
    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return TestFileUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }
    
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		File file = new File("");

		// 返回父目录的抽象路径
		file.getParentFile();
		// 文件是否存在
		file.exists();
		// 创建一个空的文件.该方法会抛出异常
		file.createNewFile();
		// 删除
		file.delete();
		// 获得路径
		file.getAbsolutePath();
		// 获得文件名
		file.getName();
		// 得到文件的父级,返回String
		file.getParent();
		// 判断是否是文件夹
		file.isDirectory();
		// 获得当前路径下的所有文件名
		String[] listAll = file.list();
		// 获得当前路径下的某种文件名
		String[] listJava = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
		// 获得当前路径下的所有文件
		File[] listFilesAll = file.listFiles();
		// 获得当前路径下的某种文件
		File[] listFilesJava = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java") || new File(name).isDirectory();
            }
        });
		
		
		file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File dir) {
                return dir.exists();
            }
        });
		
		
	}
}
