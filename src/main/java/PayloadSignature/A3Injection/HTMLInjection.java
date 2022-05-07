/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A3Injection;

/**
 *
 * @author shacojx
 */
public class HTMLInjection {

    private static String[] arrPayhtmli;

    public HTMLInjection() {
        arrPayhtmli = new String[]{
            "%3Ch1%3ETest12345%3C%2Fh1%3E",};
    }

    public String[] getArrPayhtmli() {
        return arrPayhtmli;
    }
}
