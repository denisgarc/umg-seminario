/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.consultas.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

/**
 *
 * @author Francisco Lopez
 */
public class PersonalStatistics implements Serializable{
    @Expose
    private int total;
    @Expose
    private int hombres;
    @Expose
    private int mujeres;
    
    public PersonalStatistics(int total, int hombres, int mujeres) {
        this.total = total;
        this.hombres = hombres;
        this.mujeres = mujeres;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHombres() {
        return hombres;
    }

    public void setHombres(int hombres) {
        this.hombres = hombres;
    }

    public int getMujeres() {
        return mujeres;
    }

    public void setMujeres(int mujeres) {
        this.mujeres = mujeres;
    }
    
}
