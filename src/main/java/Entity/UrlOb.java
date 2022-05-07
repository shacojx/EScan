/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author shacojx
 */
public class UrlOb {
    private String url;
    private String method;
    private String param;
    private int depth;
    private boolean scan;
    private long idTarget;
    private String cookie;
    private String username;

    public UrlOb() {
    }

    public UrlOb(String url, String method, String param, int depth, boolean scan, long idTarget, String cookie, String username) {
        this.url = url;
        this.method = method;
        this.param = param;
        this.depth = depth;
        this.scan = scan;
        this.idTarget = idTarget;
        this.cookie = cookie;
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean isScan() {
        return scan;
    }

    public void setScan(boolean scan) {
        this.scan = scan;
    }

    public long getIdTarget() {
        return idTarget;
    }

    public void setIdTarget(long idTarget) {
        this.idTarget = idTarget;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UrlOb{" + "url=" + url + ", method=" + method + ", param=" + param + ", depth=" + depth + ", scan=" + scan + ", idTarget=" + idTarget + ", cookie=" + cookie + ", username=" + username + '}';
    }
    
    
}
