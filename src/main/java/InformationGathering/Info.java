/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InformationGathering;

/**
 *
 * @author shacojx
 */
public class Info {
    private String server;
    private String code;
    private String sttcode;

    public Info() {
    }

    public Info(String server, String code, String sttcode) {
        this.server = server;
        this.code = code;
        this.sttcode = sttcode;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSttcode() {
        return sttcode;
    }

    public void setSttcode(String sttcode) {
        this.sttcode = sttcode;
    }

    @Override
    public String toString() {
        return "Info{" + "server=" + server + ", code=" + code + ", sttcode=" + sttcode + '}';
    }
    
}
