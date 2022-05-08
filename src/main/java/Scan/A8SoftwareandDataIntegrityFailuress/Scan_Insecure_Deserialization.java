/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A8SoftwareandDataIntegrityFailuress;

import Entity.UrlOb;
import FunctionPlus.HttpCommon;
import PayloadSignature.A8SoftwareandDataIntegrityFailures.Insecure_Deserialization;
import Scan.A2CryptographicFailures.Scan_Base64EncodeSecret;
import java.util.Base64;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class Scan_Insecure_Deserialization {

    public String paramx = "";
    public String payloadx = "";
    public String signaturex = "";

    public boolean Scan(UrlOb ob, String cookie) {
        try {
            Scan_Base64EncodeSecret b64 = new Scan_Base64EncodeSecret();
            String cleartext = null;
            if (cookie != null) {
                if (b64.Check_Base64(cookie)) {
                    byte[] decodedBytes = Base64.getDecoder().decode(cookie);
                    cleartext = new String(decodedBytes);
                } else {
                    Insecure_Deserialization sig = new Insecure_Deserialization();
                    String[] ListSig = sig.getSIG();
                    for (String x : ListSig) {
                        if (cookie.contains(x)) {
                            cleartext = cookie;
                        }
                    }
                }
                if (cleartext.contains("user")) {
                    cleartext = cleartext.replaceAll("user", "admin");
                } else if ((cleartext.contains("admin")) || (cleartext.contains("root"))) {
                    cleartext = cleartext.replaceAll("admin", "user");
                    cleartext = cleartext.replaceAll("root", "user");
                }
                if (cleartext != null) {
                    if (checkStatuscode(ob, cookie)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return false;
    }

    private boolean checkStatuscode(UrlOb ob, String cookies) {
        try {
            String method = ob.getMethod();
            String param = ob.getParam();
            String url = ob.getUrl();
            if (method.equals("GET")) {
                OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url((String) url)
                        .method("GET", null)
                        .addHeader("Cookie", cookies)
                        .build();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }
                if (response.code() == 200 || response.code() == 302) {
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
                        .build();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }
                if (response.code() == 200 || response.code() == 302) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return false;
    }
}
