package com.xyz.im.base.utils;

import com.google.common.collect.Lists;

import java.util.Properties;

import static com.xyz.im.base.utils.DateUtils.*;
import static com.xyz.im.base.utils.EncryptUtils.*;
import static com.xyz.im.base.utils.ExcelUtils.*;
import static com.xyz.im.base.utils.FileUtils.*;
import static com.xyz.im.base.utils.PropUtils.*;
import static com.xyz.im.base.utils.ZipUtils.*;

/**
 * 工具类统一测试（防止main泛滥）
 *
 * @author xyz
 * @date 2020/7/9
 */
public class UtilTest {

    private static void testRegexUtils() {
        String regex = "^|\\d{3,4}?-?\\d{7,8}$";
        String content = "021-98137896";
        System.out.println(content.matches(regex));
    }

    private static void testStringUtils() {

    }

    private static void testZipUtils() {
        String sourceFilePath = "/Users/dump.rdb";
        String zipFilePath = "//Users";
        String fileName = "SuanFaTest";
        boolean flag = fileToZip(sourceFilePath, zipFilePath, fileName);
        if (flag) {
            System.out.println("文件打包成功!");
        } else {
            System.out.println("文件打包失败!");
        }
    }

    private static void testPropUtils() {
        System.out.println(PropUtils.class.getClassLoader().getResource(""));
        String prop = "file/weather/city.properties";
        System.out.println(prop);
        Properties properties = getProperties(prop);
        System.out.println(properties.get("101210101"));
    }

    private static void testFileUtils() {
        //        fileToList("dss ");

        writeToLocal("", "22.txt", "2231\r\newqe");
    }

    private static void testExcelUtils() {
        SheetItem sheetItem = new SheetItem();
        sheetItem.setHeaders(Lists.newArrayList("1", "2"));
        sheetItem.setName("SuanFaTest");
        sheetItem.setValues(Lists.newArrayList(Lists.newArrayList("q", "q2"), Lists.newArrayList("w", "w2")));
        localDownload(sheetItem, "SuanFaTest");
    }

    private static void testEncryptUtils() {
        System.out.println(md5Encode("jie080903?"));
    }

    private static void testDateUtils() {
        System.out.println(getThisSunday("yyyyMMdd"));
    }

    public static void main(String[] args) {
        testRegexUtils();
    }
}
