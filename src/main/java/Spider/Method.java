/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spider;

import java.io.IOException;
import java.util.ArrayList;
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
public class Method {

    public void Method(String web) throws IOException {
        String url = web;
        Map<String, String> ListFomr = new HashMap<String, String>();
        ListFomr = getForm(web);
        GetPostMethod(ListFomr, url);
    }

    public Method() {

    }

    public boolean check_method(String html) {

        return false;
    }

    //request to site
    public List<String> GetPostMethod(Map<String, String> ListDataFormBodyString, String url) throws IOException {
        List<String> ListElemen = new ArrayList<String>();
        Map<String, String> ListFomr = new HashMap<String, String>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        FormBody.Builder formBuilder = new FormBody.Builder();
        //add data to body
        for (Map.Entry<String, String> x : ListDataFormBodyString.entrySet()) {
            formBuilder.add(x.getKey(), String.valueOf(x.getValue()));
        }
        RequestBody formBody = formBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        String html = response.body().string();
        ListFomr = getForm(html);
        return ListElemen;
    }

    public String GetFormString(String html) {
        Pattern p1 = Pattern.compile("<form(.*.)>(.|\n)*?<*form>");
        Matcher m1 = p1.matcher(html);
        String form = "";
        while (m1.find()) {
            if (m1.group(0).contains("method=\"post\"")) {
                //get form tag
                form = m1.group(0); // this variable should contain the link URL
            }

        }
        return form;
    }

    //get param
    public Map<String, String> getForm(String html) throws IOException {
        Map<String, String> ListFomr = new HashMap<String, String>();
        Pattern p1 = Pattern.compile("<form(.*.)>(.|\n)*?<*form>");
        Pattern namePattern = Pattern.compile("name=\"(.*?[^\"])\"");
        Pattern valuePattern = Pattern.compile("value=\"(.*.)\"");
        Pattern inputPattern = Pattern.compile("<input(.*.)>");
        Matcher m1 = p1.matcher(html);
        String url = "";
        String InputTag = "";
        String nameTag = "";
        String ValueTag = "";

        while (m1.find()) {
            if (m1.group(0).contains("method=\"post\"")) {
                //get form tag
                url = m1.group(0); // this variable should contain the link URL
                Matcher input = inputPattern.matcher(url);
                // get input tag
                while (input.find()) {
                    InputTag = input.group(0);
                    Matcher name = namePattern.matcher((String) InputTag);
                    Matcher value = valuePattern.matcher((String) InputTag);
                    //get name tag
                    while (name.find()) {
                        nameTag = name.group(1);
                    }
                    if (InputTag.contains("value")) {
                        while (value.find()) {
                            ValueTag = value.group(1);
                        }
                    } else {
                        ValueTag = "a";
                    }

                    System.out.println(nameTag + " : " + ValueTag);
                    ListFomr.put(nameTag, ValueTag);
                }
            }
        }

        return ListFomr;
    }

}
