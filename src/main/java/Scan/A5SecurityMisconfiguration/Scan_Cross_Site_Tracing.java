/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A5SecurityMisconfiguration;

import Entity.UrlOb;
import FunctionPlus.HttpCommon;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class Scan_Cross_Site_Tracing {
    public String paramx;
    public String payloadx;
    public String signaturex;
    public boolean Scan(UrlOb OB, String cookie) {
        boolean check = false;
        try {
            String url = OB.getUrl();
            if (OB.getMethod().equals("GET")) {
                if (trace(url, cookie)) {
                    check = true;
                    paramx = cookie;
                    payloadx = "";
                    signaturex = "";
                } else {
                    check = false;
                }
            }
            return check;
        }catch (Exception e){
            System.out.println("loi ne");
        }
        return check;

    }

    private boolean trace(String url, String cookie) {
        int code = 0;
        try {
            if (cookie != null) {
                OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url((String) url)
                        .method("TRACE", null)
                        .addHeader("Cookie", cookie)
                        .build();
                boolean conn = true;
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }
                code = response.code();
                if(code == 200) {
                    return true;
                } else {
                    return false;
                }
            } else {
                OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url((String) url)
                        .method("TRACE", null)
                        .build();
                boolean conn = true;
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }
                code = response.code();
                if (code == 200)
                    return true;
                else
                    return false;
            }
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
        }
        return false;

    }
}
