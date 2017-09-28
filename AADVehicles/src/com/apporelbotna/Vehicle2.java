package com.apporelbotna;

public class Vehicle2
{
    private String marca;
    private String model;
    private int any;
    private Matricula matricula;
    private float consum;

    public Vehicle2(String marca, String model, int any, int num_matr, String let_matr, float consum)
    {
        this.marca = marca;
        this.model = model;
        this.any = any;
        this.matricula = new Matricula(num_matr, let_matr);
        this.consum = consum;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public float getConsum() {
        return consum;
    }

    public void setConsum(float consum) {
        this.consum = consum;
    }

    public float calcAutonomia(float litrCarburant) {
        return litrCarburant*100 / consum;
    }

    @Override
    public String toString() {
        return "Marca: " + this.marca + "\nModel: " + this.model + "\nAny: " + this.any + "\nMatricula: " + this.matricula + "\nConsum: " + this.consum + " litres*100km";
    }
}
