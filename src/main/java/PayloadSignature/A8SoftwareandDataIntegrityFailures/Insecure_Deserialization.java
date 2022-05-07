/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A8SoftwareandDataIntegrityFailures;

/**
 *
 * @author shacojx
 */
public class Insecure_Deserialization {

    private String[] SIG;

    public Insecure_Deserialization() {
        SIG = new String[]{
            "user",
            "admin",
            "root",};

    }

    public String[] getSIG() {
        return SIG;
    }
}
