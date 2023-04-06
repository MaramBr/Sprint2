/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author Maram
 */
public class Coaching {
     
    private int id;
    private String descCoach,Cours,DispoCoach,ImgCoach;

    public Coaching() {
    }

    public Coaching(int id, String descCoach, String Cours, String DispoCoach, String ImgCoach) {
        this.id = id;
        this.descCoach = descCoach;
        this.Cours = Cours;
        this.DispoCoach = DispoCoach;
        this.ImgCoach = ImgCoach;
    }

    public Coaching(String descCoach, String Cours, String DispoCoach, String ImgCoach) {
        this.descCoach = descCoach;
        this.Cours = Cours;
        this.DispoCoach = DispoCoach;
        this.ImgCoach = ImgCoach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescCoach() {
        return descCoach;
    }

    public void setDescCoach(String descCoach) {
        this.descCoach = descCoach;
    }

    public String getCours() {
        return Cours;
    }

    public void setCours(String Cours) {
        this.Cours = Cours;
    }

    public String getDispoCoach() {
        return DispoCoach;
    }

    public void setDispoCoach(String DispoCoach) {
        this.DispoCoach = DispoCoach;
    }

    public String getImgCoach() {
        return ImgCoach;
    }

    public void setImgCoach(String ImgCoach) {
        this.ImgCoach = ImgCoach;
    }

    @Override
    public String toString() {
        return "Coaching{" + "id=" + id + ", descCoach=" + descCoach + ", Cours=" + Cours + ", DispoCoach=" + DispoCoach + ", ImgCoach=" + ImgCoach + '}';
    }
    
    
    
    
}
