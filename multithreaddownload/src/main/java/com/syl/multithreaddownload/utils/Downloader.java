package com.syl.multithreaddownload.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by j3767 on 2016/9/3.
 *
 * @Describe 1.为什么要实现的多线程下载, 可以大大提高下载的速度
 * 2.多线程下载的步骤
 * 在本地先建立一个同服务器同样大小的空文件RandomAccessFile
 * 划分多条线程,每条线程负责下载各自对应的数据
 * 每条现场需要将各自的下载数据写到最初的空文件中去
 * @Called
 */
public class Downloader {
    //http://192.168.2.205:8080/test.txt
    private static int threadCount = 3;
    private static String path = "http://192.168.2.205:8080/qny.mp3";
    private static int runningThreadCount = 3;

    public static void main(String[] args) throws Exception {
        System.out.println(getFileName(path));
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            //拿到文件的大小
            int contentLength = connection.getContentLength();
            System.out.println("文件长度contentLength===" + contentLength);

            RandomAccessFile randomAccessFile = new RandomAccessFile(getFileName(path), "rw");
            randomAccessFile.setLength(contentLength);
            randomAccessFile.close();

            //启动3条线程下载,每条线程现在数据的平均大小
            int blockSize = contentLength / threadCount;
            for (int i = 0; i < threadCount; i++) {
                int startIndex = i * blockSize;
                int endIndex = (i + 1) * blockSize - 1;
                //如果是最后一条线程,就辛苦一点,多现在一些
                if (i == threadCount - 1) {
                    endIndex = contentLength - 1;
                }
                //启动一条线程去下载各自的目标数据,每条线程现在数据的平均大小
                new MultiThreadDownloader(i, startIndex, endIndex).start();
            }
        }
    }

    /**
     * 获取文件名
     *
     * @param path
     * @return
     */
    public static String getFileName(String path) {
        int index = path.lastIndexOf("/");
        String fileName = path.substring(index + 1);
        return fileName;
    }

    /**
     * 创建多线程下载的子线程
     */
    public static class MultiThreadDownloader extends Thread {
        private int threadId;
        private int startIndex;
        private int endIndex;
        private long currentPosition;

        /**
         * @param threadId   线程
         * @param startIndex 文件起始位置
         * @param endIndex   文件结束位置
         */
        public MultiThreadDownloader(int threadId, int startIndex, int endIndex) {
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.currentPosition = startIndex;//最开始的当前位置就是startIndex
        }

        @Override
        public void run() {
            //下载代码
            System.out.println("线程--" + threadId + "开始下载了");
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                //partial Content如果服务器只返回某段具体的服务器数据,那么返回的成功状态码是206
                int responseCode = connection.getResponseCode();
                System.out.println(responseCode);
                if (responseCode == 206) {
                    //获取目标段数据
                    InputStream inputStream = connection.getInputStream();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(getFileName(path), "rw");

                    //根据file是否存在决定从哪儿开始下载
                    File file = new File(threadId + ".position");
                    if (file.exists() && file.length() > 0) {
                        //之前下载了部分数据,读取文件中的记录,从记录位置开始下载
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String value = br.readLine();
                        //文件可能很长,需要将value转化为long值
                        currentPosition = Long.parseLong(value);
                        connection.setRequestProperty("Range", "bytes=" + currentPosition + "-" + endIndex);
                        randomAccessFile.seek(currentPosition);
                    } else {
                        //没有文件就正常下载
                        //Range: bytes=0-499
                        connection.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
                        randomAccessFile.seek(startIndex);
                    }

                    //指定每条线程开始写文件的位置
                    randomAccessFile.seek(startIndex);

                    int len;
                    byte[] buf = new byte[1024*50];
                    while ((len = inputStream.read(buf)) > 0) {
                        randomAccessFile.write(buf, 0, len);

                        //文件的当前读取位置,文件的实时读取位置
                        currentPosition += len;
                        //将实时位置写到一个文件中
                        RandomAccessFile rff = new RandomAccessFile(threadId + ".position", "rws");
                        //断点下载,将currentPosition作为文件内容写入文件内
                        rff.write((currentPosition + "").getBytes());
                        rff.close();
                    }
                    randomAccessFile.close();
                    inputStream.close();
                }
                System.out.println("线程--" + threadId + "结束下载了........");
                File file = new File(threadId+".position");
                file.renameTo(new File(threadId+".position.finish"));

                synchronized (Downloader.class){
                    runningThreadCount--;
                    if (runningThreadCount <=0){
                        //将下载完成的finish文件删除掉
                        for (int i = 0; i < threadCount; i++) {
                            File f = new File(i+".position.finish");
                            f.delete();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("下载失败.....");
                e.printStackTrace();
            }
        }
    }
}

