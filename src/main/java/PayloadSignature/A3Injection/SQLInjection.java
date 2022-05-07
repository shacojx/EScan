/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PayloadSignature.A3Injection;

/**
 *
 * @author shacojx
 */
public class SQLInjection {

    private static String[] arrSigSQLin;
    private static String[] arrPaySQLin;

    public SQLInjection() {
        arrSigSQLin = new String[]{
            "Syntax error",
            "Incorrect syntax near",
            "You have an error in your SQL syntax",
            "supplied argument is not a valid MySQL",
            "mysql_fetch_array() expects parameter 1 to be resource, boolean given in",
            "java.sql.SQLException: Syntax error or access violation",
            "java.sql.SQLException: Unexpected end of command",
            "PostgreSQL query failed: ERROR: parser:",
            "XPathException",
            "Warning: SimpleXMLElement::xpath():",
            "[Microsoft][ODBC SQL Server Driver]",
            "Microsoft OLE DB Provider for ODBC Drivers",
            "[Microsoft][ODBC Microsoft Access Driver]",
            "supplied argument is not a valid ldap",
            "DB2 SQL error:",
            "Interbase Injection",
            "Sybase message:",
            "Unclosed quotation mark after the character string",
            "Incorrect syntax near"};


        /*List Payload SQL Injection*/
        arrPaySQLin = new String[]{
            "'",
            "')",
            "';",
            "\"",
            "\")",
            "\";",
            "\"\"",
            ")",
            "`;",
            "\\",
            "%27",
            "%%2727",
            "%25%27",
            "%60",
            "%5C",
            "{$gt: ''}",
            "[$ne]=1",};
    }

    public String[] getArrSigSQLin() {
        return arrSigSQLin;
    }

    public String[] getArrPaySQLin() {
        return arrPaySQLin;
    }
}
