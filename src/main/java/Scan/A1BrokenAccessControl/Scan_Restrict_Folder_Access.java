/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A1BrokenAccessControl;

import Entity.tree;
import FunctionPlus.HttpCommon;
import PayloadSignature.A1BrokenAccessControl.Restrict_Folder_Access_sig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class Scan_Restrict_Folder_Access {
    public String paramx = "";
    public String payloadx = "";
    public String signaturex = "";
    private List<String> ListFolder = new ArrayList<>();

    public boolean Restrict_Folder_Access(List<tree> tree, String domain, String cookie) {
        List<String> ListUrlVul = new ArrayList<>();
        try {

            ListFolder.add(domain);
            for (int i = 0; i <= ListFolder.size(); i++) {
                String x = "";
                try {
                    x = ListFolder.get(i);
                } catch (Exception e) {
                    break;
                }
                String exfather = x;
                if (!x.equals(domain))
                    exfather = x.split("/")[Arrays.asList(x.split("/")).size() - 1];
                if (exfather.endsWith("/"))
                    exfather = exfather.substring(0, exfather.length() - 1);
                if (x.endsWith("/"))
                    x = x.substring(0, x.length() - 1);
                for (tree t : tree) {
                    if (t.getFather().equals(exfather) && t.getChill().length() > 0) {
                        if (checkfolder(t.getChill())) {
                            if (!ListFolder.contains(x + t.getChill())) {
                                ListFolder.add(x + "/" + t.getChill());
                            }
                        }
                    }
                }
            }

            for (String x : ListFolder) {
                //System.out.println("url="+x);
                if (check_Restrict_Folder_Access(x, cookie)) {
                    ListUrlVul.add(x);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (ListUrlVul.size()>0)
        return true;
        else return false;
    }

    public boolean check_Restrict_Folder_Access(String url, String cookie) {
        try {
            if (cookie != null) {
                OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .method("GET", null)
                        .addHeader("Cookie", cookie)
                        .build();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }
                String html = response.body().string();
                html = html.replaceAll("\\<.*?>", "");
                if (response.code() == 200) {
                    Restrict_Folder_Access_sig sig = new Restrict_Folder_Access_sig();
                    String[] SIG = sig.getSIGs();
                    for (String x : SIG) {
                        if (!html.contains(x))
                            return false;
                    }

                    return true;
                }


            } else {
                OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .method("GET", null)
                        .build();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }
                String html = response.body().string();
                html = html.replaceAll("\\<.*?>", "");
                if (response.code() == 200) {
                    Restrict_Folder_Access_sig sig = new Restrict_Folder_Access_sig();
                    String[] SIG = sig.getSIGs();
                    for (String x : SIG) {
                        if (!html.contains(x))
                            return false;
                    }

                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private boolean checkfolder(String child) {
        if (child.contains(".")) {
            return false;
        }
        return true;
    }
}
