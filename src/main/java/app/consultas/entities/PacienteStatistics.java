package app.consultas.entities;

public class PacienteStatistics {
    private int total;
    private int hombres;
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