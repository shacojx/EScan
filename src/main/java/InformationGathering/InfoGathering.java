/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InformationGathering;

import FunctionPlus.HttpCommon;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class InfoGathering {
    public Info Scan(String domain) {
        Info inf = new Info();
        try{
            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(domain)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            String sttcode = response.code()+"";
            String server = response.header("Server");
            String code = response.header("X-Powered-By");
            String codex = "N/A";
            String serverx = "N/A";
            if(server == null){
                serverx = "N/A";
            }else{
                serverx = server;
            }
            if(code == null){
                codex = "N/A";
            }else{
                codex = code;
            }
            inf.setCode(codex);
            inf.setServer(serverx);
            inf.setSttcode(sttcode);
            return inf;
        }catch (Exception e){
            System.out.println("loi ne");
        }
        return inf;
    }
}
