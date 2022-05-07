/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A3Injection;

/**
 *
 * @author shacojx
 */
public class iFrameInjection {

    private static String[] arrPayii;

    public iFrameInjection() {
        arrPayii = new String[]{
            "%3Ciframe%3E%3Ch1%3ETest12345%3C%2Fh1%3E%3C%2Fiframe%3E",};
    }

    public String[] getArrPayXSS() {
        return arrPayii;
    }
}
