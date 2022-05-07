/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shacojx
 */
public class robot_file_module {

    private String User_agent = "";
    private List<String> Allow = new ArrayList<String>();
    private List<String> Disallow = new ArrayList<String>();

    public robot_file_module() {
    }
    

    public String getUser_agent() {
        return User_agent;
    }

    public void setUser_agent(String User_agent) {
        this.User_agent = User_agent;
    }

    public List<String> getAllow() {
        return Allow;
    }

    public void setAllow(List<String> Allow) {
        this.Allow = Allow;
    }

    public List<String> getDisallow() {
        return Disallow;
    }

    public void setDisallow(List<String> Disallow) {
        this.Disallow = Disallow;
    }

    @Override
    public String toString() {
        return "robot_file_module{" + "User_agent=" + User_agent + ", Allow=" + Allow + ", Disallow=" + Disallow + '}';
    }

}
