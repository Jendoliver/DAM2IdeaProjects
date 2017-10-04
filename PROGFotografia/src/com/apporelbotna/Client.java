package com.apporelbotna;

import com.sun.istack.internal.Nullable;

public class Client
{
    private String nif;
    private String name;
    private String surname1;
    private String surname2;
    private String telephone;
    private String email;
    private CameraItem rentedCamera;

    public Client(String nif, String name, String surname1, String surname2, String telephone, String email)
    {
        this.nif = nif;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.telephone = telephone;
        this.email = email;
        this.rentedCamera = null;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() { return surname2; }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Nullable
    public CameraItem getRentedCamera() {
        return rentedCamera;
    }

    public void setRentedCamera(CameraItem rentedCamera) {
        this.rentedCamera = rentedCamera;
    }

    public boolean equals(Client client) {
        return this.nif.equals(client.getNif());
    }

    @Override
    public String toString() {
        String s = "--- CLIENT DATA ---\n" +
                "NIF: " + this.nif + "\n" +
                "Name: " + this.name + " " + this.surname1 + " " + this.surname2 + "\n" +
                "Telephone: " + this.telephone + "\n" +
                "Email: " + this.email + "\n" +
                "Rented camera: ";

        if(rentedCamera != null) {
            s += this.rentedCamera.getReference() + "\n\n";
        } else {
            s += "none\n\n";
        }
        return s;
    }
}
