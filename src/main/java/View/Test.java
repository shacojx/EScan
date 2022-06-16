/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author shacojx
 */
public class Test {

    public static void run(String[] args) throws IOException, InterruptedException {
        String cmd_curl = "curl --location --request GET https://id.funtap.vn/login";
        String response = "";

        Process proc = Runtime.getRuntime().exec(cmd_curl);

        // Read the output
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while ((line = reader.readLine()) != null) {
            String out = line + "\n";
            response = response + out;

        }
        System.out.println(response);
        proc.destroy();

    }

}
