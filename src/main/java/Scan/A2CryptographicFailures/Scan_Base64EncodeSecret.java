/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan.A2CryptographicFailures;

import PayloadSignature.A2CryptographicFailures.Base64_Encoding_Secret;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author shacojx
 */
public class Scan_Base64EncodeSecret {

    public String paramx;
    public String payloadx;
    public String signaturex;

    public Boolean Check_Base64(String cookie) {
        boolean check = false;
        try {
            if (cookie != null) {
                paramx = cookie;
                Base64_Encoding_Secret base64 = new Base64_Encoding_Secret();
                String payload_base64 = base64.getSIGBase64();
                payloadx = "";
                signaturex = "";
                Pattern value = Pattern.compile(payload_base64);
                Matcher check_base64 = value.matcher(cookie);
                check = check_base64.find();
            } else {
                check = false;
            }
            return check;
        } catch (Exception e) {
            System.out.println("loi ne");
        }
        return check;

    }
}
