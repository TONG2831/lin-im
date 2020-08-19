package com.xyz.im.base.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

@Slf4j
public class FileUtils {

    /**
     * 传入文件所在resources下路径，读取文件，将每一行封装到list中
     *
     * @param filepath
     * @return
     */
    public static List<String> fileToList(String filepath) {
        List<String> subscribers = Lists.newArrayList();
        try (
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                subscribers.add(line);
            }
        } catch (IOException e) {
            log.error("read file error", e);
        }
        return subscribers;
    }

    public static List<String> localFileToList(String filePath) {
        List<String> contents = Lists.newArrayList();
        File file = new File(filePath);
        try (
                InputStream is = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
        } catch (IOException e) {
            log.error("read file error", e);
        }
        return contents;
    }

    public static void writeToLocal(String filePath, String fileName, String content) {
        OutputStream os = null;
        BufferedWriter bw = null;
        try {
            File file = new File(filePath + "/" + fileName);
            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
            os = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(content);
            bw.flush();
        } catch (Exception e) {
            log.error("write file error", e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                log.error("write file error", e);
            }
        }
    }

}
