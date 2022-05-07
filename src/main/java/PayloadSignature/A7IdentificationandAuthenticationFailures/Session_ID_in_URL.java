/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A7IdentificationandAuthenticationFailures;

/**
 *
 * @author shacojx
 */
public class Session_ID_in_URL {

    private String[] SessionPayload;

    public Session_ID_in_URL() {
        SessionPayload = new String[]{
            "sessionID",
            "Sid",
            "token",
            "cookie",
            "pid"
        };
    }

    public String[] getSessionPayload() {
        return SessionPayload;
    }
}
