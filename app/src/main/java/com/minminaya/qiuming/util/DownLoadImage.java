package com.minminaya.qiuming.util;

import android.widget.Toast;

import com.minminaya.qiuming.App;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Niwa on 2017/7/2.
 */
public class DownLoadImage {
    /**
     * 用来下载图片，客户端是普通的URLConnection
     *
     * @param urlString 图片的地址
     * @param fileName  文件名
     * @param savePath  保存的路径
     *                  <p>
     *                  <p>
     *                  <p>
     *                  下载完会打印出下载成功
     */
    public static void downLoad(String urlString, String fileName, String savePath) throws IOException {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
        con.setConnectTimeout(5 * 1000);
        InputStream is = con.getInputStream();

        byte[] bs = new byte[1024];
        int len;
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath() + "\\" + fileName);
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
        System.out.println(urlString + "下载成功");

    }
}
