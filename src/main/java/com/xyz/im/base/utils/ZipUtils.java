package com.xyz.im.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.FileUtil;

import java.io.*;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author xyz
 * @date 2019-11-27
 */
@Slf4j
public class ZipUtils {

    public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if (sourceFile.exists() == false) {
            System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
        } else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                if (zipFile.exists()) {
                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
                } else {
                    File[] sourceFiles = sourceFile.listFiles();
                    if (null == sourceFiles || sourceFiles.length < 1) {
                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                    } else {
                        fos = new FileOutputStream(zipFile);
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        byte[] bufs = new byte[1024 * 10];
                        for (int i = 0; i < sourceFiles.length; i++) {
                            //创建ZIP实体，并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            //读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis, 1024 * 10);
                            int read = 0;
                            while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                                zos.write(bufs, 0, read);
                            }
                        }
                        flag = true;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                //关闭流
                try {
                    if (null != bis) bis.close();
                    if (null != zos) zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        return flag;
    }

    public static byte[] getZipBytes(Map<String, String> fileMap, String zipName) {
        File zipFile = new File(zipName + ".zip");
        try (FileOutputStream out = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(out)) {
            fileMap.forEach((fileName, fileContent) -> {
                try {
                    zos.putNextEntry(new ZipEntry(fileName));
                    zos.write(fileContent.getBytes());
                    zos.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            log.error("#ZipUtils.getZipBytes exception, ", e);
        }
        if (zipFile.exists()) {
            try {
//                return FileUtils.readFileToByteArray(zipFile);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                FileUtil.deleteContents(zipFile);
            }
        }
        return null;
    }

}
