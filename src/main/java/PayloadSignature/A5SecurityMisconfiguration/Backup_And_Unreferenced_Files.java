/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A5SecurityMisconfiguration;

/**
 *
 * @author shacojx
 */
public class Backup_And_Unreferenced_Files {

    
    private String payloa = "";

    public Backup_And_Unreferenced_Files() {
    }

    public String getPayloa() {
        return payloa;
    }

    public void setPayloa(String payloa) {
        this.payloa = payloa;
    }

   

    public String getPayload(String domain) {
        payloa = "https://www.google.com.vn/search?q=site%3A%22" + domain + "%22+intext%3A%28bak%7Csql%7Czip%7Cdump%7Ctxt%7Crar%7C7z%7Cbackp%29";
        return payloa;
    }
}
