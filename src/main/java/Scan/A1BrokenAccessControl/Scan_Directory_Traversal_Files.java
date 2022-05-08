/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A1BrokenAccessControl;

import Entity.UrlOb;
import FunctionPlus.HttpCommon;
import PayloadSignature.A1BrokenAccessControl.LFI;
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
public class Scan_Directory_Traversal_Files {
    public String paramx;
    public String payloadx;
    public String signaturex;
    public boolean Scan(UrlOb ob, String cookies) {
        try {
            String param = ob.getParam();
            if (param != null) {
            String method = ob.getMethod();
            String url = ob.getUrl();
            List<String> lparam = Arrays.asList(param.split("&"));
            String param_tmp = "";
            LFI lfi = new LFI();
            String[] payload = lfi.getArrPayLFI();
            String[] Sig = lfi.getArrSigLFI();

                for (String x : payload) {
                    String html = gethtml(ob, cookies, x);
                    for (String signature : Sig) {
                        if (html.contains(signature)) {
                            payloadx = x;
                            signaturex = signature;

                            return true;
                        } else {
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("loi LFI 1");
        }
        return false;
    }

    private String gethtml(UrlOb ob, String cookies, String x) {

        String html = "";
        try {
            String method = ob.getMethod();
            String param = ob.getParam();
            List<String> lparam = Arrays.asList(param.split("&"));
            String param_tmp = "";
            for(String pa : lparam){
                param_tmp = param_tmp+pa+x+"&";
            }
            paramx = param_tmp;
            String url = ob.getUrl();

            if (cookies != null) {
                if (method.equals("GET")) {
                    OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url((String) url +"?"+ param_tmp)
                            .method("GET", null)
                            .addHeader("Cookie", cookies)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        response.close();
                    }
                    html = response.body().string();
                    return html;
                } else {
                    OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                    RequestBody body = RequestBody.create(mediaType, param_tmp);
                    Request request = new Request.Builder()
                            .url(url)
                            .method("POST", body)
                            .addHeader("Cookie", cookies)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        response.close();
                    }
                    html = response.body().string();
                    return html;
                }

            } else {
                if (method.equals("GET")) {
                    OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url((String) url +"?"+ param_tmp)
                            .method("GET", null)
                            .build();
                    System.out.println(url +"?"+ param_tmp);

                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        response.close();
                    }
                    html = response.body().string();
                    return html;
                } else {
                    OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                    RequestBody body = RequestBody.create(mediaType, param_tmp);
                    Request request = new Request.Builder()
                            .url(url)
                            .method("POST", body)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        response.close();
                    }
                    html = response.body().string();

                    return html;
                }
            }
        } catch (Exception e) {
            System.out.println("Loi LFI 2");
        }
        return html;

    }
}
