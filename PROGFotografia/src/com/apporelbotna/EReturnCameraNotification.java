package com.apporelbotna;

public enum EReturnCameraNotification
{
    SUCCESSFUL,
    CLIENT_DOESNT_EXIST,
    CLIENT_HASNT_GOT_CAMERA;

    public static void printReturnCameraNotification(EReturnCameraNotification notification)
    {
        switch(notification)
        {
            case SUCCESSFUL: System.out.println("Camera returned successfully!"); break;
            case CLIENT_DOESNT_EXIST: System.out.println("The specified client doesn't exist"); break;
            case CLIENT_HASNT_GOT_CAMERA: System.out.println("The specified client hasn't got any camera rented"); break;
        }
        System.out.println();
    }
}
