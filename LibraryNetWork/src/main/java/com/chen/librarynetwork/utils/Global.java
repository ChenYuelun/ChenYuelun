package com.chen.librarynetwork.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by chenyuelun on 2018/1/19.
 */

public class Global {
    private Global() {}

    public static String encodeURIComponent(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8")
                    .replace("+", "%20")
                    .replace("!", "%21")
                    .replace("'", "%27")
                    .replace("(", "%28")
                    .replace(")", "%29")
                    .replace("~", "%7E");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeURIComponent(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
