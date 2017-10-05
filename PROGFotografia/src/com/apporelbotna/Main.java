package com.apporelbotna;
import com.jendoliver.io.InputAsker;
import java.util.ArrayList;

public class Main
{
    private static ArrayList<Shop> shops = new ArrayList<>();
    private static int selectedShop = 0;

    public static void main(String[] args)
    {
        shops.add(new Shop("DefaultShop", "undefined"));
        int option;
        do {
            printMenu();
            option = InputAsker.askInt("Select an option: ");
            decode(option);
        } while (option != 13);
        System.out.println("Goodbye!");
    }

    // Main helper methods
    private static void printMenu()
    {
        System.out.println("--- MAIN MENU (Shop: " + shops.get(selectedShop).getName() + ")---");
        System.out.println("1. Add client");
        System.out.println("2. Add camera type");
        System.out.println("3. Add camera item");
        System.out.println("4. Rent a camera to a client");
        System.out.println("5. Register a camera return from a client");
        System.out.println("6. Check out all rentals from a concrete client (sorted by date)");
        System.out.println("7. Check out all available items (sorted alphabetically by brand)");
        System.out.println("8. Check out selected shop details");
        System.out.println("9. Modify selected shop name and address");
        System.out.println("10. Check out all shops");
        System.out.println("11. Add shop");
        System.out.println("12. Change shop");
        System.out.println("13. Exit the program");
    }

    private static void decode(int option)
    {
        switch(option)
        {
            case 1:
                try {
                    shops.get(selectedShop).addClient(askClientDetails());
                } catch (AlreadyExistsException e) {
                    System.out.println(e.getMessage());
                } break;
            case 2:
                try {
                    shops.get(selectedShop).addCamera(askCameraTypeDetails(true));
                } catch (AlreadyExistsException e) {
                    System.out.println(e.getMessage());
                } break;
            case 3:
                try {
                    shops.get(selectedShop).addCameraItem(askCameraItemDetails());
                } catch (AlreadyExistsException e) {
                    System.out.println(e.getMessage());
                } break; // TODO: shouldn't this show the existing Camera objects and make the user select from one of them and just add a reference?
            case 4: ERentCameraNotification.printRentCameraNotification(shops.get(selectedShop).rentCameraItem(InputAsker.askNonEmptyString("Item reference: "), InputAsker.askNonEmptyString("Client NIF: "))); break;
            case 5: EReturnCameraNotification.printReturnCameraNotification(shops.get(selectedShop).returnCamera(InputAsker.askNonEmptyString("Client NIF: "))); break;
            case 6: shops.get(selectedShop).getRentalsByClient(InputAsker.askNonEmptyString("Client NIF: ")).forEach(System.out::println); break;
            case 7: shops.get(selectedShop).getCameraItems().forEach(System.out::println); break;
            case 8: System.out.println(shops.get(selectedShop)); break;
            case 9: modifySelectedShop(); break;
            case 10: shops.forEach(System.out::println); break;
            case 11: shops.add(new Shop(InputAsker.askNonEmptyString("New shop name: "), InputAsker.askNonEmptyString("New shop address: "))); break;
            case 12: selectedShop = InputAsker.askInt("Input ArrayList position: "); break; // TODO: refactor this shit
        }
    }

    private static Client askClientDetails()
    {
        System.out.println("--- NEW CLIENT: Please provide details below ---");
        return new Client(
                InputAsker.askNonEmptyString("Client NIF: "),
                InputAsker.askNonEmptyString("Client first name: "),
                InputAsker.askNonEmptyString("Client first surname: "),
                InputAsker.askString("Client second surname: "),
                InputAsker.askNonEmptyString("Client address: "),
                InputAsker.askNonEmptyString("Client telephone: "),
                InputAsker.askNonEmptyString("Client email: ")
        );
    }

    private static Camera askCameraTypeDetails(boolean newType)
    {
        if(newType) {
            System.out.println("--- NEW CAMERA TYPE: Please provide details below ---");
        }
        return new Camera(
                new Brand(
                        InputAsker.askNonEmptyString("Camera brand name: "),
                        InputAsker.askNonEmptyString("Camera reparation service address: ")
                ),
                InputAsker.askNonEmptyString("Camera model: "),
                InputAsker.askYNQuestion("Has it got flash?")
        );
    }

    private static CameraItem askCameraItemDetails() // TODO: Refactor this so the user selects a camera from the existing ones
    {
        System.out.println("--- NEW CAMERA ITEM: Please provide details below ---");
        Camera cameraType = askCameraTypeDetails(false);
        return new CameraItem(
                cameraType.getBrand(),
                cameraType.getModel(),
                cameraType.hasFlash(),
                InputAsker.askNonEmptyString("Item reference: ")
        );
    }

    private static void modifySelectedShop()
    {
        System.out.println(shops.get(selectedShop));
        String newName = InputAsker.askString("Input a new name for the shop (don't type anything to leave it unchanged): ");
        String newAddress = InputAsker.askString("Input a new sddress for the shop (don't type anything to leave it unchanged): ");
        if( ! newName.equals("")) {
            shops.get(selectedShop).setName(newName);
        }
        if( ! newAddress.equals("")) {
            shops.get(selectedShop).setAddress(newAddress);
        }
    }
}