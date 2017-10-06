package com.apporelbotna;

public enum ERentCameraNotification
{
    SUCCESSFUL,
    CLIENT_DOESNT_EXIST,
    CAMERA_DOESNT_EXIST,
    CLIENT_HAS_CAMERA,
    ALREADY_RENTED;

    public static void printRentCameraNotification(ERentCameraNotification notification)
    {
        switch(notification)
        {
            case SUCCESSFUL: System.out.println("Camera rented successfully!"); break;
            case CLIENT_DOESNT_EXIST: System.out.println("The specified client doesn't exist"); break;
            case CAMERA_DOESNT_EXIST: System.out.println("The specified camera doesn't exist"); break;
            case CLIENT_HAS_CAMERA: System.out.println("The specified client already has a camera"); break;
            case ALREADY_RENTED: System.out.println("The specified camera is already rented"); break;
        }
        System.out.println();
    }
}
