package com.xyz.im.base.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropUtils {

    /**
     * 传入属性文件存放的地址（resources目录下开始算），读取属性文件
     *
     * @param filepath
     * @return
     */
    public static Properties getProperties(String filepath) {
        Properties props = new Properties();
        try (
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);
        ) {
            props.load(is);
        } catch (IOException e) {
            log.error("加载属性文件出错", e);
        }
        return props;
    }
}
