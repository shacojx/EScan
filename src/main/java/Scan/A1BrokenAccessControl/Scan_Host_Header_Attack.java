/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A1BrokenAccessControl;

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
public class Scan_Host_Header_Attack {
    public String paramx;
    public String payloadx;
    public String signaturex;
    public boolean GetHeaders(UrlOb ob, String cookies) {
        String method = ob.getMethod();
        String param = ob.getParam();
        String url = ob.getUrl();
        paramx = param;
        payloadx = "";
        signaturex = "";

        try {
            if (cookies != null) {
                if (method.equals("GET")) {
                    OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url((String) url)
                            .method("GET", null)
                            .addHeader("Cookie", cookies)
                            .addHeader("Host", "awss.ddns.me")
                            .build();
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        response.close();
                    }
                    if (response.code() == 200) {
                        return true;
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
                            .addHeader("Host", "awss.ddns.me")
                            .build();
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        response.close();
                    }
                    if (response.code() == 200) {
                        return true;
                    }
                }

            } else {
                if (method.equals("GET")) {
                    OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url((String) url)
                            .method("GET", null)
                            .addHeader("Host", "awss.ddns.me")
                            .build();
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        response.close();
                    }
                    if (response.code() == 200) {
                        return true;
                    }
                } else {
                    OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                    RequestBody body = RequestBody.create(mediaType, param);
                    Request request = new Request.Builder()
                            .url(url)
                            .method("POST", body)
                            .addHeader("Host", "awss.ddns.me")
                            .build();
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        response.close();
                    }
                    if (response.code() == 200) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {


        }
        return false;
    }
}
