/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A5SecurityMisconfiguration;

import FunctionPlus.HttpCommon;
import PayloadSignature.A4InsecureDesign.sensitive_file;
import PayloadSignature.A5SecurityMisconfiguration.Backup_And_Unreferenced_Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class Scan_Backup_And_Unreferenced_File {

    public String paramx;
    public String payloadx;
    public String signaturex;

    public boolean Scan(String domain) {
        try {
            domain = domain.replaceAll("http://", "");
            domain = domain.replaceAll("https://", "");
            domain = domain.replaceAll("/", "");
            domain = domain.replaceAll("www.", "");
            sensitive_file sf = new sensitive_file();
            String[] InText = sf.getSenFile();
            String intext = "";
            for (String x : InText) {
                intext = intext + "|" + x;
            }
            String query = "site:" + domain + " intext:(" + intext + ")";
            boolean check = checkfile(query);
            if (check) {
                paramx = "";
                payloadx = query;
                signaturex = "";
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
                signaturex = "role=\"text\"";
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;

    }
}
