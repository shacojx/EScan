/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A3Injection;

/**
 *
 * @author shacojx
 */
public class PHPCodeInjection {

    private static String[] arrSigCI;
    private static String[] arrPayCI;

    public PHPCodeInjection() {
        arrSigCI = new String[]{
            "Fatal error</b>: preg_replace",
            "Warning: preg_replace():",
            "eval()'d code</b> on line <b>",
            "Cannot execute a blank command in",
            "sh: command substitution:",
            "PATH=",
            "PWD=",
            "Warning: usort()",
            "Warning: assert():",
            "Failure evaluating code:",
            "w4p1t1_eval",};

        arrPayCI = new String[]{
            ";env",
            "a;env",
            "a);env",
            "[VALUE];env",
            "[VALUE][LF]env",
            "/e\0",
            "a;exit(base64_decode('dzRwMXQxX2V2YWw='));//",};
    }

    public String[] getArrSigCI() {
        return arrSigCI;
    }

    public String[] getArrPayCI() {
        return arrPayCI;
    }
}
