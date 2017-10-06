package com.apporelbotna;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Collections;

public class Shop
{
    private String name;
    private String address;
    private ArrayList<Client> clients;
    private ArrayList<Camera> cameras;
    private ArrayList<CameraItem> cameraItems;
    private ArrayList<Rental> rentals;

    public Shop(String name, String address)
    {
        this.name = name;
        this.address = address;
        this.clients = new ArrayList<>();
        this.cameras = new ArrayList<>();
        this.cameraItems = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Client> getClients() {
        Collections.sort(this.clients);
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Camera> getCameras() {
        Collections.sort(this.cameras);
        return cameras;
    }

    public void setCameras(ArrayList<Camera> cameras) {
        this.cameras = cameras;
    }

    public ArrayList<CameraItem> getCameraItems() {
        Collections.sort(this.cameraItems);
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
    public void addClient(Client client) throws AlreadyExistsException
    {
        if(findClientByNif(client.getNif()) != null)
            throw new AlreadyExistsException(client);
        clients.add(client);
    }

    public void addCamera(Camera camera) throws AlreadyExistsException
    {
        if(findCameraByBrandNameAndModel(camera.getBrand().getName(), camera.getModel()) != null)
            throw new AlreadyExistsException(camera);
        cameras.add(camera);
    }

    public void addCameraItem(CameraItem cameraItem)  throws AlreadyExistsException
    {
        if(findCameraItemByReference(cameraItem.getReference()) != null)
            throw new AlreadyExistsException(cameraItem);
        cameraItems.add(cameraItem);
    }

    public ERentCameraNotification rentCameraItem(String cameraItemReference, String clientNif)
    {
        CameraItem cameraItem = findCameraItemByReference(cameraItemReference);
        if(cameraItem == null)
            return ERentCameraNotification.CAMERA_DOESNT_EXIST;
        if(cameraItem.getStatus() == ECameraStatus.RENTED)
            return ERentCameraNotification.ALREADY_RENTED;

        Client client = findClientByNif(clientNif);
        if(client == null)
            return ERentCameraNotification.CLIENT_DOESNT_EXIST;

        if(client.getRentedCamera() != null)
            return ERentCameraNotification.CLIENT_HAS_CAMERA;

        rentals.add(new Rental(client, cameraItem));
        client.setRentedCamera(cameraItem);
        cameraItem.setStatus(ECameraStatus.RENTED);
        return ERentCameraNotification.SUCCESSFUL;
    }

    public EReturnCameraNotification returnCamera(String clientNif)
    {
        Client client = findClientByNif(clientNif);

        if(client == null)
            return EReturnCameraNotification.CLIENT_DOESNT_EXIST;
        if(client.getRentedCamera() == null)
            return EReturnCameraNotification.CLIENT_HASNT_GOT_CAMERA;

        client.getRentedCamera().setStatus(ECameraStatus.ON_SHOP);
        client.setRentedCamera(null);
        return EReturnCameraNotification.SUCCESSFUL;
    }

    public ArrayList<Rental> getRentalsByClient(String clientNif)
    {
        ArrayList<Rental> rentalsByClient = new ArrayList<>();
        Client client = findClientByNif(clientNif);
        for(Rental rental : rentals)
        {
            if(rental.getClient().equals(client)) {
                rentalsByClient.add(rental);
            }
        }
        Collections.sort(rentalsByClient);
        return rentalsByClient;
    }

    public ArrayList<Client> getDefaulterClientsList() // Gets the clients which should have already returned the camera
    {
        ArrayList<Client> defaulterClients = new ArrayList<>();
        for(Rental rental : rentals)
        {
            if(rental.getCameraItem().getStatus() == ECameraStatus.RENTED)
            {
                if(System.currentTimeMillis() - rental.getRentalDate().getTime() > 604800000) { // 7 days
                    defaulterClients.add(rental.getClient());
                }
            }
        }
        return defaulterClients;
    }

    @Nullable
    private Camera findCameraByBrandNameAndModel(String brandName, String model)
    {
        for(Camera camera : cameras)
        {
            if(camera.getBrand().getName().equals(brandName) && camera.getModel().equals(model)) {
                return camera;
            }
        }
        return null;
    }

    @Nullable
    private CameraItem findCameraItemByReference(String cameraItemReference)
    {
        for(CameraItem cameraItem : cameraItems)
        {
            if(cameraItem.getReference().equals(cameraItemReference)) {
                return cameraItem;
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

    @Override
    public String toString() {
        return "--- SHOP DATA ---\n" +
                "Name: " + this.name + "\n" +
                "Address: " + this.address + "\n" +
                "Number of clients: " + this.clients.size() + "\n" +
                "Number of camera types: " + this.cameras.size() + "\n" +
                "Number of items: " + this.cameraItems.size() + "\n" +
                "Total of rentals made: " + this.rentals.size() + "\n\n";
    }
}
