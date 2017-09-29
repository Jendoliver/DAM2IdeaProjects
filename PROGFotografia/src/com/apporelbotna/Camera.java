package com.apporelbotna;

public class Camera
{
    private Brand brand;
    private String model;
    private boolean bFlash;
    private CameraStatus status;
    private int daysRented;

    public Camera(Brand brand, String model, boolean bFlash)
    {
        this.brand = brand;
        this.model = model;
        this.bFlash = bFlash;
        this.status = CameraStatus.ON_SHOP;
        this.daysRented = 0;
    }

    public Camera(Brand brand, String model, boolean bFlash, CameraStatus status, int daysRented)
    {
        this.brand = brand;
        this.model = model;
        this.bFlash = bFlash;
        this.status = status;
        this.daysRented = daysRented;
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

    public CameraStatus getStatus() {
        return status;
    }

    public void setStatus(CameraStatus status) {
        this.status = status;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    @Override
    public String toString() {
        return "--- CAMERA DETAILS ---\n" +
                "Brand: " + this.brand.getName() + "\n" +
                "Model: " + this.model + "\n" +
                "Has flash: " + this.bFlash + "\n" +
                "Status: " + this.status.name() + "\n\n";
    }
}
