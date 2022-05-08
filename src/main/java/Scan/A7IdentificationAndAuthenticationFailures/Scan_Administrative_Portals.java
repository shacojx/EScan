/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A7IdentificationAndAuthenticationFailures;

import Entity.UrlOb;
import PayloadSignature.A7IdentificationandAuthenticationFailures.administrative_Portals;

/**
 *
 * @author shacojx
 */
public class Scan_Administrative_Portals {

    public String paramx;
    public String payloadx;
    public String signaturex;

    public boolean Scan(UrlOb ob) {
        boolean check = false;
        try {
            if (ob.getParam() != null) {
                String param = ob.getParam();
                administrative_Portals adPayload = new administrative_Portals();
                String[] payload = adPayload.getAdministrative_Portals();
                for (String x : payload) {
                    if (param.contains(x)) {
                        payloadx = x;
                        paramx = param;
                        signaturex = x;
                        check = true;
                        break;
                    }
                }
            }
            return check;
        } catch (Exception e) {
            System.out.println("loi admin");
        }
        return check;

    }
}
