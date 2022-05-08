/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A7IdentificationAndAuthenticationFailures;

import Entity.UrlOb;
import FunctionPlus.HttpCommon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class Scan_Insecure_Login_Forms {

    public String paramx = "";
    public String payloadx = "";
    public String signaturex = "";

    public boolean Scan(UrlOb ob, String cookie) {
        try {
            String url = ob.getUrl();
            Map<String, String> ListFomr = new HashMap<String, String>();
            String html = GetHtmlString(url, cookie);
            if (html.length() > 10) {
                ListFomr = getForm(html);
                //login
                int logincode = GetPostMethod(ListFomr, url);
                //test status login with bwapp ,this is signature of site when Successful login,change it
                //we will get the username,password
                if (logincode == 302) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return false;
    }

    private List<String> get_name_param(String html) {
        List<String> listparam = new ArrayList<>();
        Pattern userp = Pattern.compile("name=\"([^\"]*)\"");
        Pattern value = Pattern.compile("value=\"([^\"]*)\"");
        Matcher param = userp.matcher(html);
        Matcher param1 = value.matcher(html);
        while (param.find()) {
            listparam.add(param.group(1));
        }
        while (param1.find()) {
            listparam.add(param1.group(1));
        }
        return listparam;
    }

    private Map<String, String> getForm(String html) {
        try {
            html = html.toLowerCase();
            Map<String, String> ListFomr = new HashMap<String, String>();
            Pattern fontPattern = Pattern.compile("<font(.*?>)(.*.)<\\/font>");
            List Listhtml = Arrays.asList(html.split("\n"));
            html = "";
            int B = 1;
            String user = "";
            String pass = "";
            boolean continues = true;
            boolean add = false;
            int index = 0;
            while (continues) {
                String Indexhtml = "";
                try {
                    Indexhtml = (String) Listhtml.get(index);
                } catch (Exception e) {

                    return ListFomr;
                }
                user = "";
                pass = "";
                if ((Indexhtml.contains("<form") && Indexhtml.contains("login")) || add) {
                    add = true;
                    html = html + "\n" + Indexhtml;
                }
                if (Indexhtml.contains("</form>")) {
                    continues = false;
                }
                index = index + 1;
            }
            List<String> listparam = get_name_param(html);
            paramx = listparam.toString();
            Matcher fontP = fontPattern.matcher(html);
            int i = 0;
            while (fontP.find()) {
                B = 1 ^ B;
                if (B == 1) {
                    user = fontP.group(2);

                    ListFomr.put(listparam.get(i), user);
                    i++;
                } else {
                    pass = fontP.group(2);
                    ListFomr.put(listparam.get(i), pass);
                    i++;
                }

            }
            if (listparam.size() >= 2) {
                ListFomr.put(listparam.get(listparam.size() - 2), listparam.get(listparam.size() - 1));
            }
            return ListFomr;
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return null;
    }

    private int GetPostMethod(Map<String, String> ListDataFormBodyString, String url) {
        try {
            List<String> ListElemen = new ArrayList<String>();
            Map<String, String> ListFomr = new HashMap<String, String>();
            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            FormBody.Builder formBuilder = new FormBody.Builder();
            //add data to body
            for (Map.Entry<String, String> x : ListDataFormBodyString.entrySet()) {
                formBuilder.add(x.getKey(), String.valueOf(x.getValue()));
            }
            RequestBody formBody = formBuilder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", formBody)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                response.close();
            }
            return response.code();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return 0;
    }

    public String GetHtmlString(String url, String cookie) {

        String html = "";
        try {
            if (cookie != null) {
                OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = RequestBody.create(mediaType, "");
                Request request = new Request.Builder()
                        .url(url)
                        .method("POST", body)
                        .addHeader("Cookie", cookie)
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
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = RequestBody.create(mediaType, "");
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
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return html;
    }
}
