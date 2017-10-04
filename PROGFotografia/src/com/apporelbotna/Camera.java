package com.apporelbotna;

public class Camera implements Comparable<Camera>
{
    private Brand brand;
    private String model;
    private boolean bFlash;

    public Camera(Brand brand, String model, boolean bFlash)
    {
        this.brand = brand;
        this.model = model;
        this.bFlash = bFlash;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean hasFlash() {
        return bFlash;
    }

    public void setbFlash(boolean bFlash) {
        this.bFlash = bFlash;
    }

    @Override
    public int compareTo(Camera camera) {
        return this.brand.getName().compareTo(camera.getBrand().getName());
    }

    @Override
    public String toString() {
        return "--- CAMERA DETAILS ---\n" +
                "Brand: " + this.brand.getName() + "\n" +
                "Model: " + this.model + "\n" +
                "Has flash: " + this.bFlash + "\n\n";
    }
}
