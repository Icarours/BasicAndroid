package com.syl.phoneluck.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by j3767 on 2016/8/28.
 *
 * @Describe
 * 字节流转化为字符串
 * @Called
 */
public class StringTool {
    public static String decodeString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int len;
        byte[] buf = new byte[1024];
        while ((len = inputStream.read(buf))>0){
            baos.write(buf,0,len);
        }
        String responseValue = baos.toString();
        return responseValue;
    }
}
