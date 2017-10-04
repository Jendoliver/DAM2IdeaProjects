package com.apporelbotna;

import java.util.Date;

public class Rental implements Comparable<Rental>
{
    private Client client;
    private CameraItem cameraItem;
    private Date rentalDate;

    public Rental(Client client, CameraItem cameraItem)
    {
        this.client = client;
        this.cameraItem = cameraItem;
        this.rentalDate = new Date(System.currentTimeMillis());
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CameraItem getCameraItem() {
        return cameraItem;
    }

    public void setCameraItem(CameraItem cameraItem) {
        this.cameraItem = cameraItem;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Override
    public int compareTo(Rental rental) {
        return this.rentalDate.compareTo(rental.getRentalDate());
    }

    @Override
    public String toString() {
        return "--- RENTAL REPORT ---\n" +
                "Client renting: " + this.client + "\n" +
                "Camera item rented: " + this.cameraItem + "\n" +
                "Rental date: " + this.rentalDate + "\n\n";
    }
}
