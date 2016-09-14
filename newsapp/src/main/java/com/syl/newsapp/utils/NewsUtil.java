package com.syl.newsapp.utils;

import android.util.Xml;

import com.syl.newsapp.bean.NewsItem;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by j3767 on 2016/8/31.
 *
 * @Describe解析xml文件
 * @Called
 */
public class NewsUtil {

    public static List<NewsItem> getAllItems(String path) {
    List<NewsItem> list = new ArrayList<>();
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                XmlPullParser pullParser = Xml.newPullParser();
                pullParser.setInput(inputStream, "UTF-8");

                int eventType = pullParser.getEventType();

                NewsItem item = null;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        //具体判断是从哪个标签开始.item的开始标签
                        if ("item".equals(pullParser.getName())) {
                            item = new NewsItem();
                        } else if ("title".equals(pullParser.getName())) {
                            item.setTitle(pullParser.nextText());
                        } else if ("description".equals(pullParser.getName())) {
                            item.setDescription(pullParser.nextText());
                        } else if ("image".equals(pullParser.getName())) {
                            item.setImage(pullParser.nextText());
                        } else if ("type".equals(pullParser.getName())) {
                            item.setType(pullParser.nextText());
                        } else if ("comment".equals(pullParser.getName())) {
                            item.setComment(pullParser.nextText());
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        //item的结束标签
                        if ("item".equals(pullParser.getName())) {
                            list.add(item);
                        }
                    }
                    eventType = pullParser.next();
                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
//    <title>军报评徐才厚</title>
//    <description>人死账不消 反腐步不停，支持，威武，顶，有希望了。
//    </description>
//    <image>http://192.168.2.205:8080/img/a.jpg</image>
//    <type>1</type>
//    <comment>163</comment>
}
