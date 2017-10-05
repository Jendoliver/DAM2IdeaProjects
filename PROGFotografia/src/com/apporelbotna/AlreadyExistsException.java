package com.apporelbotna;

public class AlreadyExistsException extends Exception
{
    public AlreadyExistsException(Client client) {
        super(String.format("The client with NIF %s already exists", client.getNif()));
    }

    public AlreadyExistsException(Camera camera) {
        super(String.format("The camera %s %s already exists", camera.getBrand().getName(), camera.getModel()));
    }

    public AlreadyExistsException(CameraItem cameraItem) {
        super(String.format("The camera item %s %s with reference %s already exists", cameraItem.getBrand().getName(), cameraItem.getModel(), cameraItem.getReference()));
    }
}
