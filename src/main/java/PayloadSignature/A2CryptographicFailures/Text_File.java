/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A2CryptographicFailures;

/**
 *
 * @author shacojx
 */
public class Text_File {

    private String[] intext;

    public Text_File() {
        intext = new String[]{
            "account",
            "passwd",
            "pass",
            "user",
            "username",
            "uname",
            "pwd",
            "uid"

        };
    }

    public String[] getIntext() {
        return intext;
    }
}
