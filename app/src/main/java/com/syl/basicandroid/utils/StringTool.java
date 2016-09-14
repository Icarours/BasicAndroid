package com.syl.basicandroid.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by j3767 on 2016/8/28.
 *
 * @Describe
 * @Called
 * 字节流转化为字符串
 */
public class StringTool {
    public static String decodeString(InputStream inputStream) throws IOException {
        //底层字节流转换为字符串
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len;
        byte[] buf = new byte[1024];
        while ((len = inputStream.read(buf)) > 0) {
            byteArrayOutputStream.write(buf, 0, len);
        }
        String responseValue = byteArrayOutputStream.toString();
        return responseValue;
    }
}
