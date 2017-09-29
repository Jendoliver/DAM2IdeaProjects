package com.apporelbotna;

public class Brand
{
    private String name;
    private String reparationServiceAddress;

    public Brand(String name, String reparationServiceAddress)
    {
        this.name = name;
        this.reparationServiceAddress = reparationServiceAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReparationServiceAddress() {
        return reparationServiceAddress;
    }

    public void setReparationServiceAddress(String reparationServiceAddress) {
        this.reparationServiceAddress = reparationServiceAddress;
    }

    @Override
    public String toString() {
        return "--- BRAND DETAILS ---\n" +
                "Name: " + this.name + "\n" +
                "Reparation service address: " + this.reparationServiceAddress + "\n\n";
    }
}
