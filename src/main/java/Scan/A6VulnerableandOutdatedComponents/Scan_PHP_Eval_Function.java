/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A6VulnerableandOutdatedComponents;

import Entity.UrlOb;
import FunctionPlus.HttpCommon;
import PayloadSignature.A6VulnerableandOutdatedComponents.PHP_Eval;
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
public class Scan_PHP_Eval_Function {
    public String paramx;
    public String payloadx;
    public String signaturex;
    public boolean PHP_Eval_Funtion(UrlOb urlOB, String cookie) {
        try {
            if(urlOB.getParam() != null){
                PHP_Eval php = new PHP_Eval();
                String param = urlOB.getParam();
                List<String> lparam = Arrays.asList(urlOB.getParam().split("&"));
                String[] payload = php.getPayload();
                payloadx = payload[0];
                for(String p : lparam){
                    param = p + payload[0];
                }
                String[] signature = php.getSignature();
                if (urlOB.getParam() != null && urlOB.getUrl().endsWith("php")) {
                    for (String x : payload) {
                        String html = "";
                        if (urlOB.getMethod().equals("GET")) {
                            String url = urlOB.getUrl() +"?"+ param;
                            html = GetHtmlString(url, cookie);
                        } else if (urlOB.getMethod().equals("POST")) {
                            html = PostHtmlString(urlOB.getUrl(), param, cookie);
                        }
                        for (String sig : signature) {
                            if (html.contains(sig)){
                                signaturex = sig;
                                paramx = param;
                                return true;
                            }

                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return false;
    }

    private String GetHtmlString(String url, String cookie) {

        String html = "";
        try {
            if (cookie != null) {
                OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url((String) url)
                        .method("GET", null)
                        .addHeader("Cookie", cookie)
                        .build();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }

                html = response.body().string();
            } else {
                OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url((String) url)
                        .method("GET", null)
                        .build();
                boolean conn = true;
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }

                html = response.body().string();
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }

        return html;
    }

    private String PostHtmlString(String url, String param, String cookie) {
        String html = "";
        try {
            if (cookie == null) {
                OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, param);
                Request request = new Request.Builder()
                        .url(url)
                        .method("POST", body)
                        .addHeader("Connection", "close")
                        .build();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }
                html = response.body().string();

            } else {
                OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, param);
                Request request = new Request.Builder()
                        .url(url)
                        .method("POST", body)
                        .addHeader("Connection", "close")
                        .addHeader("Cookie", cookie)
                        .build();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }
                html = response.body().string();
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return html;
    }
}
