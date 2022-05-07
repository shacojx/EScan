/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A2CryptographicFailures;

import FunctionPlus.HttpCommon;
import PayloadSignature.A2CryptographicFailures.Text_File;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class Scan_Text_Files_Accounts {

    public String paramx = "";
    public String payloadx;
    public String signaturex = "";

    public boolean Text_Files_Accounts(String domain) {
        try {
            Text_File tx = new Text_File();
            String[] InText = tx.getIntext();
            String intext = "";
            for (String x : InText) {
                intext = intext + "|" + x;
            }

            String query = "site:" + domain + " intitle:\"index of\" intext:\"" + intext + "\"";
            boolean check = checkfile(query);
            if (check) {
                payloadx = query;
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean checkfile(String query) {
        try {
            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://www.google.com/search?q=" + query)
                    .method("GET", null)
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36")
                    .addHeader("Sec-Ch-Ua-Platform", "\"Windows\"")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                response.close();
            }
            String html = response.body().string();
            if (html.contains("role=\"text\"")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
