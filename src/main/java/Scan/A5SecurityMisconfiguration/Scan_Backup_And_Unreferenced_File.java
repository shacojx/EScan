/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A5SecurityMisconfiguration;

import FunctionPlus.HttpCommon;
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
        Scan_Backup_And_Unreferenced_File scan_bakk = new Scan_Backup_And_Unreferenced_File();
        List<String> list_file = scan_bakk.CheckBackupFile(domain);
        if (list_file.size() == 0) {
            return false;
        } else {
            return true;
        }

    }

    public List<String> CheckBackupFile(String domain) {
        List<String> ListLink = new ArrayList<>();
        try {
            String url = "";

            Backup_And_Unreferenced_Files BackUpFile = new Backup_And_Unreferenced_Files();
            BackUpFile.setDomain(domain);
            url = BackUpFile.getPayload();
            payloadx = url;
            paramx = "";
            signaturex = "";
            String html = getHtml(url);
            ListLink = getHref(html, domain);
            for (int i = 0; i < ListLink.size(); i++) {
                if (!ListLink.get(i).contains(domain)) ;
                {
                    ListLink.remove(i);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ListLink;
    }

    private String getHtml(String url) {
        try {
            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .addHeader("Host", "www.google.com.vn")
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                response.close();
            }
            return response.body().string();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";

    }

    public List<String> getHref(String html, String domain) {
        List listUrl = new ArrayList();
        try {
            Pattern p = Pattern.compile("href=[\\\"\\\']([^\\\"\\\']*)[\\\"\\\']");
            Matcher m = p.matcher(html);
            String url = "";
            while (m.find()) {
                url = m.group(0); // this variable should contain the link URL
                url = url.replaceAll("href=\"", "");
                url = url.replaceAll("href=\'", "");
                url = url.replaceAll("\">", "");
                url = url.replaceAll("\'>", "");
                url = url.replaceAll("\"", "");
                url = url.replaceAll("\'", "");
                url = url.trim();
                if (!url.contains("javascript") && url.contains("/url?q=") && !url.contains("https://accounts.google.com/")) {
                    listUrl.add(url);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listUrl;
    }
}
