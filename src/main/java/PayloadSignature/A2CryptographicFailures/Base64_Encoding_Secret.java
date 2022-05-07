/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A2CryptographicFailures;

/**
 *
 * @author shacojx
 */
public class Base64_Encoding_Secret {

    private String SIGBase64;

    public Base64_Encoding_Secret() {
        SIGBase64 = "^(?:[A-Za-z0-9+\\/]{4})*(?:[A-Za-z0-9+\\/]{2}==|[A-Za-z0-9+\\/]{3}=)?$";

    }

    public String getSIGBase64() {
        return SIGBase64;
    }
}
