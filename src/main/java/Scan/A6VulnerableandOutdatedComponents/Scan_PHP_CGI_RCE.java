/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A6VulnerableandOutdatedComponents;

import Entity.UrlOb;
import FunctionPlus.HttpCommon;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class Scan_PHP_CGI_RCE {

    public String paramx = "";
    public String payloadx = "/?-s";
    public String signaturex = "<?php";

    public boolean Scan(UrlOb ob, String cookie) {
        try {
            if (ob.getParam() != null) {
                String html = GetHtmlString(ob, cookie);
                if (html.toLowerCase().contains("<?php")) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return false;
    }

    private String GetHtmlString(UrlOb ob, String cookies) {
        try {
            if (ob.getParam() != null) {
                String method = ob.getMethod();
                String param = ob.getParam();
                String url = ob.getUrl();
                Response response = null;
                if (url.endsWith("/")) {
                    url = url + "?-s";
                } else {
                    url = url + "/?-s";
                }
                String html = "";
                if (cookies != null) {
                    if (method.equals("GET")) {
                        OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                                .build();
                        Request request = new Request.Builder()
                                .url((String) url)
                                .method("GET", null)
                                .addHeader("Cookie", cookies)
                                .build();
                        response = client.newCall(request).execute();
                        if (!response.isSuccessful()) {
                            response.close();
                        }
                    } else {
                        OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                                .build();
                        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                        RequestBody body = RequestBody.create(mediaType, param);
                        Request request = new Request.Builder()
                                .url(url)
                                .method("POST", body)
                                .addHeader("Cookie", cookies)
                                .build();
                        response = client.newCall(request).execute();
                        if (!response.isSuccessful()) {
                            response.close();
                        }
                    }

                } else {
                    if (method.equals("GET")) {
                        OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                                .build();
                        Request request = new Request.Builder()
                                .url((String) url)
                                .method("GET", null)
                                .build();
                        response = client.newCall(request).execute();
                        if (!response.isSuccessful()) {
                            response.close();
                        }
                    } else {
                        OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                                .build();
                        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                        RequestBody body = RequestBody.create(mediaType, param);
                        Request request = new Request.Builder()
                                .url(url)
                                .method("POST", body)
                                .build();
                        response = client.newCall(request).execute();
                        if (!response.isSuccessful()) {
                            response.close();
                        }
                    }
                }
                html = response.body().string();
                return html;
            }

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return "";
    }
}
