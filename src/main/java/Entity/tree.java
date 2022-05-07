/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author shacojx
 */
public class tree {

    private String father;
    private String chill;

    public tree() {
    }

    public tree(String father, String chill) {
        this.father = father;
        this.chill = chill;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getChill() {
        return chill;
    }

    public void setChill(String chill) {
        this.chill = chill;
    }

    @Override
    public String toString() {
        return "tree{" + "father=" + father + ", chill=" + chill + '}';
    }
    
}
