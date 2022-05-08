/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A5SecurityMisconfiguration;

import Entity.robot_file_module;
import FunctionPlus.HttpCommon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author shacojx
 */
public class Scan_Robots_File_Disclosure {
    
    public boolean Scan(String domain){
        try {
            Scan_Robots_File_Disclosure scan_robot = new Scan_Robots_File_Disclosure();
            List<String> list_file = scan_robot.Robots_File_Disclosure(domain);
            if(list_file.size() == 0){
                return false;
            }else{
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(Scan_Robots_File_Disclosure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<String> Robots_File_Disclosure(String domain) throws IOException {
        String User_agent = "";
        List<String> Allow = new ArrayList<String>();
        List<String> Disallow = new ArrayList<String>();
        List<String> ListString = new ArrayList<String>();
        List<String> ListuserAgnt = new ArrayList<String>();
        List<String> Listurl = new ArrayList<String>();

        List<robot_file_module> ListRobot = new ArrayList<robot_file_module>();
        String robot = haveRobots(domain);
        boolean add = false;
        if (robot.length() > 0) {
            ListuserAgnt = Arrays.asList(robot.split("User-agent"));
            for (String UserAgent : ListuserAgnt) {
                UserAgent = "User-agent" + UserAgent;
                ListString = Arrays.asList(UserAgent.split("\n"));
                for (String x : ListString) {
                    if (x.contains("User-agent")) {
                        try {
                            User_agent = x.split(":")[1].trim();
                        } catch (Exception e) {
                            User_agent = null;
                        }
                    }
                    if (x.contains("Allow")) {
                        try {
                            Allow.add(x.split(":")[1].trim());
                        } catch (Exception e) {
                            Allow.add(null);
                        }
                    }
                    if (x.contains("Disallow")) {
                        try {
                            Disallow.add(x.split(":")[1].trim());
                        } catch (Exception e) {
                            Disallow.add(null);
                        }
                    }
                }
                robot_file_module rbm = new robot_file_module(User_agent, Allow, Disallow);
                ListRobot.add(rbm);
                User_agent = "";
                Allow = new ArrayList<String>();
                Disallow = new ArrayList<String>();
            }
            for (robot_file_module x : ListRobot) {
                int code = 0;
                if (x.getDisallow().size() > 0) {
                    if (x.getUser_agent().contains("*")) {
                        Disallow = x.getDisallow();
                        for (String paths : Disallow) {
                            paths = domain + paths;
                            code = checkStatusCode(paths);
                            if (code == 200) {
                                Listurl.add(paths);
                            }
                        }
                    }
                }
            }
        }
        return Listurl;
    }


    private int checkStatusCode(String url) throws IOException {
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
        return response.code();
    }

    private String haveRobots(String domain) throws IOException {
        OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(domain + "/robots.txt")
                .method("GET", null)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("Connection", "close")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            response.close();
        }
        String html = response.body().string();
        if (response.code() == 200) {
            return html;
        }
        return "";
    }
}
