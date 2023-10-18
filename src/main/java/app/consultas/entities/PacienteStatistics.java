package app.consultas.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

public class PacienteStatistics implements Serializable {
    @Expose
    private int total;
    @Expose
    private int hombres;
    @Expose
    private int mujeres;

    public PacienteStatistics(int total, int hombres, int mujeres) {
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