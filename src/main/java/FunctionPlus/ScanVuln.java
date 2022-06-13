/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FunctionPlus;

import Entity.UrlOb;
import InformationGathering.InfoGathering;
import Scan.A1BrokenAccessControl.Scan_Directory_Traversal_Files;
import Scan.A1BrokenAccessControl.Scan_Host_Header_Attack;
import Scan.A1BrokenAccessControl.Scan_Restrict_Folder_Access;
import Scan.A2CryptographicFailures.Scan_Base64EncodeSecret;
import Scan.A2CryptographicFailures.Scan_Clear_Text_HTTP;
import Scan.A2CryptographicFailures.Scan_Text_Files_Accounts;
import Scan.A3Injection.Scan_CMDinjection;
import Scan.A3Injection.Scan_HTMLinjection;
import Scan.A3Injection.Scan_IFrameinjection;
import Scan.A3Injection.Scan_PHPInjection;
import Scan.A3Injection.Scan_SQLinjection;
import Scan.A3Injection.Scan_SSIinjection;
import Scan.A3Injection.Scan_XSS;
import Scan.A3Injection.Scan_xmlinjection;
import Scan.A4InsecureDesign.Scan_sensitive_file;
import Scan.A5SecurityMisconfiguration.Scan_Backup_And_Unreferenced_File;
import Scan.A5SecurityMisconfiguration.Scan_Cross_Site_Tracing;
import Scan.A5SecurityMisconfiguration.Scan_Robots_File_Disclosure;
import Scan.A6VulnerableandOutdatedComponents.Scan_PHP_CGI_RCE;
import Scan.A6VulnerableandOutdatedComponents.Scan_PHP_Eval_Function;
import Scan.A7IdentificationAndAuthenticationFailures.Scan_Administrative_Portals;
import Scan.A7IdentificationAndAuthenticationFailures.Scan_Insecure_Login_Forms;
import Scan.A7IdentificationAndAuthenticationFailures.Scan_Session_ID_in_URL;
import Scan.A8SoftwareandDataIntegrityFailuress.Scan_Insecure_Deserialization;
import Spider.SpiderWeb;
import View.EScan;
import java.util.List;

/**
 *
 * @author shacojx
 */
public class ScanVuln {

    public void scan(String url, String username, String passwd, String linklogin, String cookie) {
        SpiderWeb spi = new SpiderWeb();

        //A1
        Scan_Directory_Traversal_Files scan_dir_lfi = new Scan_Directory_Traversal_Files();
        Scan_Host_Header_Attack scan_host = new Scan_Host_Header_Attack();
        Scan_Restrict_Folder_Access scan_folder_access = new Scan_Restrict_Folder_Access();

        //A2
        Scan_Base64EncodeSecret scan_base64 = new Scan_Base64EncodeSecret();
        Scan_Clear_Text_HTTP scan_http = new Scan_Clear_Text_HTTP();
        Scan_Text_Files_Accounts scan_txt_acc = new Scan_Text_Files_Accounts();

        //A3
        Scan_CMDinjection scan_CMD = new Scan_CMDinjection();
        Scan_HTMLinjection scan_html = new Scan_HTMLinjection();
        Scan_IFrameinjection scan_ifram = new Scan_IFrameinjection();
        Scan_PHPInjection scan_php = new Scan_PHPInjection();
        Scan_SQLinjection scan_Sql = new Scan_SQLinjection();
        Scan_SSIinjection scan_ssi = new Scan_SSIinjection();
        Scan_XSS scan_xss = new Scan_XSS();
        Scan_xmlinjection scan_xml = new Scan_xmlinjection();

        //A4
        Scan_sensitive_file scan_sen = new Scan_sensitive_file();
        
        //A5
        Scan_Backup_And_Unreferenced_File scan_bak = new Scan_Backup_And_Unreferenced_File();
        Scan_Cross_Site_Tracing scan_track = new Scan_Cross_Site_Tracing();
        
        
        //A6
        Scan_PHP_CGI_RCE scan_php_rce = new Scan_PHP_CGI_RCE();
        Scan_PHP_Eval_Function scan_php_eval = new Scan_PHP_Eval_Function();
        
        //A7
        Scan_Administrative_Portals scan_admin = new Scan_Administrative_Portals();
        Scan_Insecure_Login_Forms scan_inse_login = new Scan_Insecure_Login_Forms();
        Scan_Session_ID_in_URL scan_ss = new Scan_Session_ID_in_URL();
        
        //A8
        Scan_Insecure_Deserialization scan_in_des = new Scan_Insecure_Deserialization();
        
        

        InfoGathering infoga = new InfoGathering();

        infoga.Scan(url);
        List<UrlOb> list_url = spi.SpiderWeb(url, cookie, username, passwd, linklogin, "EScan");

        boolean check_txt_acc = scan_txt_acc.Scan(url);
        if (check_txt_acc == true) {
            String datalog = "[Vuln] Leak Text File Account";
            EScan.GhiLog(datalog);
            EScan.addRowData(url, "Leak Text File Account", "Critical", scan_txt_acc.payloadx, scan_txt_acc.payloadx,scan_txt_acc.signaturex);
        } else {
            String datalog = "[Scanning] " + url + " NOT Leak Text File Account";
            EScan.GhiLog(datalog);
        }

        boolean check_sen = scan_sen.Scan(url);
        if (check_sen == true) {
            String datalog = "[Vuln] Sensitive File";
            EScan.GhiLog(datalog);
            EScan.addRowData(url, "Sensitive File", "Medium", scan_sen.payloadx, scan_sen.payloadx, scan_sen.signaturex);
        } else {
            String datalog = "[Scanning] " + url + " NOT Sensitive File";
            EScan.GhiLog(datalog);
        }
        
        String domain = url.replaceAll("://", "");
        domain = domain.replaceAll("/", "");
        domain = domain.replaceAll("http", "");
        domain = domain.replaceAll("https", "");
        boolean check_bak = scan_bak.Scan(domain);
        if (check_bak == true) {
            String datalog = "[Vuln] Backup And Unreferenced File";
            EScan.GhiLog(datalog);
            EScan.addRowData(url, "Backup And Unreferenced File", "Critical", scan_bak.payloadx, scan_bak.payloadx, scan_bak.signaturex);
        } else {
            String datalog = "[Scanning] " + url + " NOT Backup And Unreferenced File";
            EScan.GhiLog(datalog);
        }
        
        
        for (UrlOb o : list_url) {
            if (EScan.isStop == true) {
                String datalog = "==> STOP SCAN <==";
                EScan.GhiLog(datalog);
                return;
            }
            System.out.println(o.toString());

            //A1 Scan
            boolean check_lfi = scan_dir_lfi.Scan(o, cookie);
            if (check_lfi == true) {
                String datalog = "[Vuln] LFI: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "Directory Traversal", "Critical", "https://portswigger.net/web-security/file-path-traversal", scan_dir_lfi.payloadx, scan_dir_lfi.signaturex);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "LFI", "Critical", "https://owasp.org/www-project-web-security-testing-guide/v42/4-Web_Application_Security_Testing/07-Input_Validation_Testing/11.1-Testing_for_Local_File_Inclusion",scan_dir_lfi.payloadx, scan_dir_lfi.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT LFI";
                EScan.GhiLog(datalog);
            }

            boolean check_host = scan_host.Scan(o, cookie);
            if (check_host == true) {
                String datalog = "[Vuln] Host Header Attack: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "Host Header Attack", "Medium", "https://portswigger.net/web-security/host-header", scan_host.payloadx, scan_host.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT Host Header Attack";
                EScan.GhiLog(datalog);
            }

            //A1 End
            //A2 Scan
            boolean check_base64 = scan_base64.Check_Base64(cookie);
            if (check_base64 == true) {
                String datalog = "[Vuln] Base64 Encode Secret: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "Base64 Encode Secret", "Critical", "https://www.tenable.com/blog/detecting-base64-encoded-authentication-requests", scan_base64.payloadx, scan_base64.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT Host Header Attack";
                EScan.GhiLog(datalog);
            }

            boolean check_http = scan_http.Scan(o);
            if (check_base64 == true) {
                String datalog = "[Vuln] Clear Text HTTP: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "Clear Text HTTP", "Medium", "https://portswigger.net/kb/issues/00300100_cleartext-submission-of-password", scan_http.payloadx, scan_http.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT Clear Text HTTP";
                EScan.GhiLog(datalog);
            }

            //A2 End
            //A3 Scan
            boolean check_cmd = scan_CMD.Scan(o);
            if (check_cmd == true) {
                String datalog = "[Vuln] OS CMD Injection: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "CMD Injection", "Critical", "https://owasp.org/www-community/attacks/Command_Injection", scan_CMD.payloadx, scan_CMD.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT CMD Injection";
                EScan.GhiLog(datalog);
            }

            boolean check_html = scan_html.Scan(o);
            if (check_html == true) {
                String datalog = "[Vuln] HTML Injection: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "HTML Injection", "Low", "https://owasp.org/www-project-web-security-testing-guide/latest/4-Web_Application_Security_Testing/11-Client-side_Testing/03-Testing_for_HTML_Injection", scan_html.payloadx, scan_html.signaturex);

            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT HTML Injection";
                EScan.GhiLog(datalog);
            }

            boolean check_ifame = scan_ifram.Scan(o);
            if (check_ifame == true) {
                String datalog = "[Vuln] IFrame Injection: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "IFrame Injection", "High", "https://www.invicti.com/blog/web-security/frame-injection-attacks/", scan_ifram.payloadx, scan_ifram.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT IFrame Injection";
                EScan.GhiLog(datalog);
            }

            boolean check_php = scan_php.Scan(o);
            if (check_php == true) {
                String datalog = "[Vuln] PHP Injection: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "PHP Injection", "Critical", "https://owasp.org/www-community/vulnerabilities/PHP_Object_Injection", scan_php.payloadx, scan_php.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT PHP Injection";
                EScan.GhiLog(datalog);
            }

            boolean check_sql = scan_Sql.Scan(o);
            if (check_sql == true) {
                String datalog = "[Vuln] SQL Injection: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "SQL Injection", "Critical", "https://owasp.org/www-community/attacks/SQL_Injection", scan_Sql.payloadx, scan_Sql.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT SQL Injection";
                EScan.GhiLog(datalog);
            }

            boolean check_ssi = scan_ssi.Scan(o);
            if (check_ssi == true) {
                String datalog = "[Vuln] SSI Injection: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "SSI Injection", "Critical", "https://owasp.org/www-community/attacks/Server-Side_Includes_(SSI)_Injection", scan_ssi.payloadx, scan_ssi.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT SSI Injection";
                EScan.GhiLog(datalog);
            }

            boolean check_xss = scan_xss.Scan(o);
            if (check_xss == true) {
                String datalog = "[Vuln] XSS: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "XSS", "High", "https://owasp.org/www-community/attacks/xss/", scan_xss.payloadx, scan_xss.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT XSS";
                EScan.GhiLog(datalog);
            }

            boolean check_xml = scan_xml.Scan(o);
            if (check_xml == true) {
                String datalog = "[Vuln] XML Injection: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "XML Injection", "Critical", "https://owasp.org/www-project-web-security-testing-guide/latest/4-Web_Application_Security_Testing/07-Input_Validation_Testing/07-Testing_for_XML_Injection", scan_xml.payloadx, scan_xml.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT XML Injection";
                EScan.GhiLog(datalog);
            }
            //A3 End
            
            //A5 Scan
            boolean check_track = scan_track.Scan(o, cookie);
            if (check_track == true) {
                String datalog = "[Vuln] Cross Site Tracing: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "Cross Site Tracing", "Medium", "https://owasp.org/www-community/attacks/Cross_Site_Tracing", scan_track.payloadx, scan_track.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT Cross Site Tracing";
                EScan.GhiLog(datalog);
            }
            //A5 End
            
            //A6 Scan
            boolean check_php_rce = scan_php_rce.Scan(o, cookie);
            if (check_track == true) {
                String datalog = "[Vuln] PHP CGI RCE: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "PHP CGI RCE", "Critical", "https://www.acunetix.com/vulnerabilities/web/php-cgi-remote-code-execution/", scan_php_rce.payloadx, scan_php_rce.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT PHP CGI RCE";
                EScan.GhiLog(datalog);
            }
            
            
            boolean check_php_eval = scan_php_eval.Scan(o, cookie);
            if (check_php_eval == true) {
                String datalog = "[Vuln] PHP Eval Function: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "PHP Eval Function", "Critical", "https://owasp.org/www-community/attacks/Direct_Dynamic_Code_Evaluation_Eval%20Injection", scan_php_eval.payloadx, scan_php_eval.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT PHP Eval Function";
                EScan.GhiLog(datalog);
            }
            //A6 End
            
            //A7 Scan
             boolean check_admin = scan_admin.Scan(o);
            if (check_admin == true) {
                String datalog = "[Vuln] Administrative Portals: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "Administrative Portals", "Low", "", scan_admin.payloadx, scan_admin.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT Administrative Portals";
                EScan.GhiLog(datalog);
            }
            
            
            boolean check_login_form = scan_inse_login.Scan(o, cookie);
            if (check_login_form == true) {
                String datalog = "[Vuln] Insecure Login Forms: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "Insecure Login Forms", "Low", "", scan_inse_login.payloadx, scan_inse_login.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT Insecure Login Forms";
                EScan.GhiLog(datalog);
            }
            
            
            boolean check_ss = scan_ss.Scan(o.getParam());
            if (check_ss == true) {
                String datalog = "[Vuln] SessionID In URL: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "SessionID In URL", "Medium", "", scan_ss.payloadx, scan_ss.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT SessionID In URL";
                EScan.GhiLog(datalog);
            }
            //A7 End
            
            //A8 Start
             boolean check_in_des = scan_in_des.Scan(o,cookie);
            if (check_in_des == true) {
                String datalog = "[Vuln] Insecure Deserialization: " + o.getUrl();
                EScan.GhiLog(datalog);
                EScan.addRowData(o.getUrl() + "?" + o.getParam(), "Insecure Deserialization", "Medium", "https://owasp.org/www-project-top-ten/2017/A8_2017-Insecure_Deserialization", scan_in_des.payloadx, scan_in_des.signaturex);
            } else {
                String datalog = "[Scanning] " + o.getUrl() + " NOT Insecure Deserialization";
                EScan.GhiLog(datalog);
            }
            //A8 End

        }
        System.out.println("Done Scan");
        EScan.isDone = true;

    }
}
