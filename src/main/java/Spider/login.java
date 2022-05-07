/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class login {
    public boolean check_login(String html) throws IOException {
        List Login = new ArrayList();
        Login = Arrays.asList(html.split("\n"));
        for(Object x : Login){
            if(x.toString().toLowerCase().contains("login")
                    ||x.toString().toLowerCase().contains("dang nhap")
                    ||x.toString().toLowerCase().contains("dang_nhap")
                    ||x.toString().toLowerCase().contains("singin")
                    ||x.toString().toLowerCase().contains("sing_in")
            ){
                return true;
            }
        }
        return false;
    }
    public String getCookie(String UrlLogin,String User,String Pass) throws IOException {
        String cookie="";
        List<String> Listparam= new ArrayList<String>();
        String html = GetHtmlString(UrlLogin);
        Listparam = get_name_param(html);
        String param=Listparam.get(0) + "=" + User + "&" + Listparam.get(1) + "=" + Pass + "&" + Listparam.get(2) + "=submit";
        ArrayList<String> temp = new ArrayList<>();
        String[] listx = UrlLogin.split("/");

        for(int i =0; i< listx.length-1; i++){
            temp.add(listx[i]);
        }

        String newUrl  = "";
        for(String x : temp )
            newUrl = newUrl + x+ "/";
        newUrl = newUrl + Listparam.get(3);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, param);
        Request request = new Request.Builder()
                .url(newUrl)
                .method("POST", body)
                .addHeader("Connection", "close")
                .build();
        Response response = client.newCall(request).execute();
        List<String> Cookielist = response.headers().values("Set-Cookie");
        for(String x : Cookielist){
            cookie = cookie + x +";";
        }
         return cookie;
    }
    public List<String> login(String url,String param) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType,param );
        Request request = new Request.Builder()
                .url(url )
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        String html = response.body().string();
        List<String> Cookielist = response.headers().values("Set-Cookie");

        return Cookielist;
    }
    public String GetHtmlString(String url) throws IOException {
        String html = "";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        html = response.body().string();
        return html;
    }
    private String GetFormLogin(String html){
        List Listhtml = Arrays.asList(html.split("\n"));
        boolean continues = true;
        boolean add = false;
        int index = 0;
        html = "";
        while(continues){
            String Indexhtml = (String) Listhtml.get(index);
            if((Indexhtml.contains("<form") && Indexhtml.contains("login"))  || add){
                add = true;
                html = html + "\n" + Indexhtml;
            }
            if(Indexhtml.contains("</form>")){
                continues=false;
            }
            index = index +1;
        }
        return html;
    }
    public List<String> get_name_param(String html) throws IOException {
        html = html.toLowerCase();
        String username="";
        String password="";
        String login="";
        String action="";

        Pattern actionRe  = Pattern.compile("action=\"([^\"]*)\"");
        Pattern nameRe  = Pattern.compile("name=\"([^\"]*)\"");
        Pattern valueRE  = Pattern.compile("value=\"([^\"]*)\"");
        Pattern inputRE  = Pattern.compile("<input(.*)\"([^>]*)\\\"");
        List<String> ListParam=new ArrayList<String>();
        html = GetFormLogin(html);
        Matcher inputTag = inputRE.matcher(html);
        Matcher actionTag = actionRe.matcher(html);
        while(actionTag.find()){
            action = actionTag.group(1);
        }
        while(inputTag.find()){
            if (inputTag.group(1).contains("type=\"text\"")){
                Matcher usernameTag = nameRe.matcher(inputTag.group(1));
               while (usernameTag.find()){
                   username = usernameTag.group(1);
               }
            }
            if (inputTag.group(1).contains("type=\"password\"")){
                Matcher passwordTag = nameRe.matcher(inputTag.group(1));
                while (passwordTag.find()){
                    password = passwordTag.group(1);
                }
            }
            if (inputTag.group(1).contains("type=\"submit\"")){

                Matcher loginTag = valueRE.matcher(inputTag.group(1));
                while (loginTag.find()){
                    login = loginTag.group(1);
                }
            }

        }
        ListParam.add(username);
        ListParam.add(password);
        ListParam.add(login);
        ListParam.add(action);
        return ListParam;
    }
}
