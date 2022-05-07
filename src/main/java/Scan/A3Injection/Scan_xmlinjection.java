/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A3Injection;

import Entity.UrlOb;
import FunctionPlus.HttpCommon;
import PayloadSignature.A3Injection.XMLXPathInjection;
import java.util.Arrays;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class Scan_xmlinjection {

    public String paramx;
    public String payloadx;
    public String signaturex;

    public boolean Scan(UrlOb uri) {
        boolean check = false;
        try {
            if (uri.getParam() != null) {
                XMLXPathInjection xmlxp = new XMLXPathInjection();
                String[] payload = xmlxp.getArrPayXMLXPathin();
                String[] signature = xmlxp.getArrSigXMLXPathin();
                List<String> lparam = Arrays.asList(uri.getParam().split("&"));
                String param_tmp = "";
                String cookie = null;
                if (cookie == null) {
                    if (uri.getMethod().equalsIgnoreCase("get") && uri.getParam() != null) {
                        param_tmp = "";
                        for (String p : payload) {
                            for (String pa : lparam) {
                                param_tmp = param_tmp + pa + p + "&";
                            }
                            paramx = param_tmp;
                            System.out.println(uri.getUrl() + "?" + uri.getParam() + p);
                            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                                    .build();
                            Request request = new Request.Builder()
                                    .url(uri.getUrl() + "?" + param_tmp)
                                    .method("GET", null)
                                    .build();
                            Response response = client.newCall(request).execute();
                            if (!response.isSuccessful()) {
                                response.close();
                            }
                            String content = response.body().string();
                            for (String s : signature) {
                                if (content.contains(s)) {
                                    payloadx = p;
                                    signaturex = s;
                                    check = true;
                                    break;
                                }
                            }
                            if (check = true) {
                                break;
                            }
                        }
                    } else if (uri.getMethod().equalsIgnoreCase("post") && uri.getParam() != null) {
                        param_tmp = "";
                        for (String p : payload) {
                            for (String pa : lparam) {
                                param_tmp = param_tmp + pa + p + "&";
                            }
                            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                                    .build();
                            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                            RequestBody body = RequestBody.create(mediaType, param_tmp);
                            Request request = new Request.Builder()
                                    .url(uri.getUrl())
                                    .method("POST", body)
                                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                    .build();
                            Response response = client.newCall(request).execute();
                            if (!response.isSuccessful()) {
                                response.close();
                            }
                            String content = response.body().string();
                            for (String s : signature) {
                                if (content.contains(s)) {
                                    signaturex = s;
                                    payloadx = p;
                                    check = true;
                                    break;
                                }
                            }
                            if (check = true) {
                                break;
                            }
                        }

                    } else {
                        check = false;
                    }
                } else {
                    if (uri.getMethod().equalsIgnoreCase("get") && uri.getParam() != null) {
                        param_tmp = "";
                        for (String p : payload) {
                            for (String pa : lparam) {
                                param_tmp = param_tmp + pa + p + "&";
                            }
                            System.out.println(uri.getUrl() + "?" + uri.getParam() + p);
                            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                                    .build();
                            Request request = new Request.Builder()
                                    .url(uri.getUrl() + "?" + param_tmp)
                                    .method("GET", null)
                                    .addHeader("Cookie", cookie)
                                    .build();
                            Response response = client.newCall(request).execute();
                            if (!response.isSuccessful()) {
                                response.close();
                            }
                            String content = response.body().string();
                            for (String s : signature) {
                                if (content.contains(s)) {
                                    signaturex = s;
                                    payloadx = p;
                                    check = true;
                                    break;
                                }
                            }
                            if (check = true) {
                                break;
                            }
                        }
                    } else if (uri.getMethod().equalsIgnoreCase("post") && uri.getParam() != null) {
                        param_tmp = "";
                        for (String p : payload) {
                            for (String pa : lparam) {
                                param_tmp = param_tmp + pa + p + "&";
                            }
                            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                                    .build();
                            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                            RequestBody body = RequestBody.create(mediaType, param_tmp);
                            Request request = new Request.Builder()
                                    .url(uri.getUrl())
                                    .method("POST", body)
                                    .addHeader("Cookie", cookie)
                                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                    .build();
                            Response response = client.newCall(request).execute();
                            if (!response.isSuccessful()) {
                                response.close();
                            }
                            String content = response.body().string();
                            for (String s : signature) {
                                if (content.contains(s)) {
                                    signaturex = s;
                                    payloadx = p;
                                    check = true;
                                    break;
                                }
                            }
                            if (check = true) {
                                break;
                            }
                        }

                    } else {
                        check = false;
                    }
                }
            }

            return check;

        } catch (Exception e) {
            System.out.println("loi XML");
        }
        return check;

    }
}
