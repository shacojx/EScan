/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FunctionPlus;

import okhttp3.OkHttpClient;

/**
 *
 * @author shacojx
 */
public class HttpCommon {
    public OkHttpClient httpClient;

    public static HttpCommon _instance;

    public static HttpCommon getInstance(){
        if(_instance==null){
            _instance = new HttpCommon();
        }
        return _instance;
    }

    public HttpCommon() {
        httpClient = new OkHttpClient();
    }

    public  OkHttpClient getHttpClient() {
        return httpClient;
    }
}
