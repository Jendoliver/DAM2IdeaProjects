package com.apporelbotna;

public class CameraItem extends Camera
{
    private String reference;
    private ECameraStatus status;

    public CameraItem(Brand brand, String model, boolean bFlash, String reference)
    {
        super(brand, model, bFlash);
        this.reference = reference;
        this.status = ECameraStatus.ON_SHOP;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ECameraStatus getStatus() {
        return status;
    }

    public void setStatus(ECameraStatus status) {
        this.status = status;
    }

    public boolean equals(CameraItem cameraItem) {
        return this.reference.equals(cameraItem.getReference());
    }

    @Override
    public String toString() {
        return super.toString() +
                "--- CAMERA ITEM DETAILS ---\n" +
                "Reference: " + this.reference + "\n" +
                "Status: " + this.status.name() + "\n\n";
    }
}
