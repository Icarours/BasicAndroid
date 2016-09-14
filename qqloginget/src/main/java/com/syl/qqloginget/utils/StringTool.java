package com.syl.qqloginget.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by j3767 on 2016/9/3.
 *
 * @Describe
 * @Called
 */
public class StringTool {
    public static String decode2String(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int len ;
        byte[] buf = new byte[1024];
        while ((len = inputStream.read(buf))>0){
            baos.write(buf,0,len);
        }
        String resultValue = baos.toString();
        return resultValue;
    }
}
