/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A3Injection;

/**
 *
 * @author shacojx
 */
public class ServerSideIncludesInjection {

    private static String[] arrPaySSI;
    private static String[] arrSigSSI;

    public ServerSideIncludesInjection() {
        arrSigSSI = new String[]{
            "root:x:0:0",
            "root:*:0:0",
            "www-data:x:",
            "root",
            "nt-system"
        };
        arrPaySSI = new String[]{
            "<!--#exec cmd=\"whoami\" -->",
            "<!--#exec cmd=\"cat /etc/passwd\" -->",};
    }

    public String[] getArrSigSSI() {
        return arrSigSSI;
    }

    public String[] getArrPaySSI() {
        return arrPaySSI;
    }
}
