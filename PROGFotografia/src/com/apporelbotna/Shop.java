package com.apporelbotna;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;

public class Shop
{
    private ArrayList<Client> clients;
    private ArrayList<Camera> cameras;
    private ArrayList<CameraItem> cameraItems;
    private ArrayList<Rental> rentals;

    public Shop()
    {
        this.clients = new ArrayList<>();
        this.cameras = new ArrayList<>();
        this.cameraItems = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    // Getters and setters
    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(ArrayList<Camera> cameras) {
        this.cameras = cameras;
    }

    public ArrayList<CameraItem> getCameraItems() {
        return cameraItems;
    }

    public void setCameraItems(ArrayList<CameraItem> cameraItems) {
        this.cameraItems = cameraItems;
    }

    public ArrayList<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(ArrayList<Rental> rentals) {
        this.rentals = rentals;
    }

    // Methods
    public void addClient(Client client) {
        clients.add(client);
    }

    public void addCamera(Camera camera) {
        cameras.add(camera);
    }

    public void addCameraItem(CameraItem cameraItem) {
        cameraItems.add(cameraItem);
    }

    public ERentCameraNotification rentCameraItem(String cameraItemReference, String clientNif)
    {
        CameraItem cameraItem = findCameraItemByReference(cameraItemReference);
        Client client = findClientByNif(clientNif);

        if(cameraItem == null)
            return ERentCameraNotification.CAMERA_DOESNT_EXIST;

        if(client == null)
            return ERentCameraNotification.CLIENT_DOESNT_EXIST;

        if(client.getRentedCamera() != null)
            return ERentCameraNotification.CLIENT_HAS_CAMERA;

        rentals.add(new Rental(client, cameraItem));
        return ERentCameraNotification.SUCCESSFUL;
    }

    public EReturnCameraNotification returnCamera(String clientNif)
    {
        Client client = findClientByNif(clientNif);

        if(client == null)
            return EReturnCameraNotification.CLIENT_DOESNT_EXIST;
        if(client.getRentedCamera() == null)
            return EReturnCameraNotification.NO_CAMERA;

        client.setRentedCamera(null);
        return EReturnCameraNotification.SUCCESSFUL;
    }

    public ArrayList<Rental> getRentalsByClient(String clientNif)
    {
        ArrayList<Rental> rentalsByClient = new ArrayList<>();
        Client client = findClientByNif(clientNif);

        // TODO
        return rentalsByClient;
    }

    @Nullable
    private CameraItem findCameraItemByReference(String cameraReference)
    {
        for(CameraItem camera : cameraItems)
        {
            if(camera.getReference().equals(cameraReference)) {
                return camera;
            }
        }
        return null;
    }

    @Nullable
    private Client findClientByNif(String clientNif)
    {
        for(Client client : clients)
        {
            if (client.getNif().equals(clientNif)) {
                return client;
            }
        }
        return null;
    }
}
