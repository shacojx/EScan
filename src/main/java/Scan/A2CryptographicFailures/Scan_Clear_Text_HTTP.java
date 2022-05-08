/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A2CryptographicFailures;

import Entity.UrlOb;

/**
 *
 * @author shacojx
 */
public class Scan_Clear_Text_HTTP {

    public String paramx;
    public String payloadx;
    public String signaturex;

    public boolean Scan(UrlOb ob) {
        boolean check = false;
        try {
            String url = ob.getUrl();
            if (url.contains("http:") && !url.contains("https:") && ob.getMethod().equalsIgnoreCase("post")) {
                check = true;
                paramx = "";
                payloadx = "";
                signaturex = "";
            } else {
                check = false;
            }
            return check;
        } catch (Exception e) {
            System.out.println("loi HTTP");
        }
        return check;

    }
}
