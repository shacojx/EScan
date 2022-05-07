/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A7IdentificationAndAuthenticationFailures;

import PayloadSignature.A7IdentificationandAuthenticationFailures.Session_ID_in_URL;

/**
 *
 * @author shacojx
 */
public class Scan_Session_ID_in_URL {

    public String paramx;
    public String payloadx;
    public String signaturex;

    public Boolean CheckSessionIdInUrl(String param) {
        boolean check = false;
        try {
            Session_ID_in_URL sid = new Session_ID_in_URL();
            String[] ListSidPayload = sid.getSessionPayload();
            for (String x : ListSidPayload) {
                if (param.contains(x)) {
                    paramx = param;
                    payloadx = x;
                    signaturex = x;
                    check = true;
                    break;
                }
            }
            return check;
        } catch (Exception e) {
            System.out.println("loi ne");
        }
        return check;

    }
}
