/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A4InsecureDesign;

/**
 *
 * @author shacojx
 */
public class sensitive_file {

    private String[] senFile;

    public sensitive_file() {
        senFile = new String[]{
            "sql",
            "zip",
            "rar",
            "7z",
            "bak",
            "log",
            "mail",
            "txt",
            "dump",};
    }

    public String[] getSenFile() {
        return senFile;
    }
}
