package com.apporelbotna;
import com.jendoliver.io.InputAsker;
import java.util.ArrayList;

public class Main
{
    private static ArrayList<Shop> shops = new ArrayList<>();
    private static Shop selectedShop;

    public static void main(String[] args)
    {
        shops.add(new Shop("DefaultShop", "undefined"));
        selectedShop = shops.get(0);
        int option;
        do {
            printMenu();
            option = InputAsker.askInt("Select an option: ");
            decode(option);
        } while (option != 15);
        System.out.println("Goodbye!");
    }

    // Main helper methods
    private static void printMenu()
    {
        System.out.println("\n--- MAIN MENU (Shop: " + selectedShop.getName() + ")---");
        System.out.println("1. Add client");
        System.out.println("2. Add camera type");
        System.out.println("3. Add camera item");
        System.out.println("4. Rent a camera to a client");
        System.out.println("5. Register a camera return from a client");
        System.out.println("6. Check out all rentals from a concrete client (sorted by date)");
        System.out.println("7. Check out all available items (sorted alphabetically by brand)");
        System.out.println("8. Check out all registered clients");
        System.out.println("9. Check out defaulter clients (who had a camera rented for more than seven days)");
        System.out.println("10. Check out selected shop details");
        System.out.println("11. Modify selected shop name and address");
        System.out.println("12. Check out all shops");
        System.out.println("13. Add shop");
        System.out.println("14. Change shop");
        System.out.println("15. Exit the program");
    }

    private static void decode(int option)
    {
        switch(option)
        {
            case 1:
                try {
                    selectedShop.addClient(askClientDetails());
                    System.out.println("Client added successfully!");
                } catch (AlreadyExistsException e) {
                    System.out.println(e.getMessage());
                } break;
            case 2:
                try {
                    selectedShop.addCamera(askCameraTypeDetails());
                    System.out.println("Camera type added successfully!");
                } catch (AlreadyExistsException e) {
                    System.out.println(e.getMessage());
                } break;
            case 3:
                try {
                    selectedShop.addCameraItem(askCameraItemDetails());
                    System.out.println("Camera item added successfully!");
                } catch (AlreadyExistsException | NoCamerasException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4: ERentCameraNotification.printRentCameraNotification(selectedShop.rentCameraItem(InputAsker.askNonEmptyString("Item reference: "), InputAsker.askNonEmptyString("Client NIF: "))); break;
            case 5: EReturnCameraNotification.printReturnCameraNotification(selectedShop.returnCamera(InputAsker.askNonEmptyString("Client NIF: "))); break;
            case 6: selectedShop.getRentalsByClient(InputAsker.askNonEmptyString("Client NIF: ")).forEach(System.out::println); break;
            case 7: selectedShop.getCameraItems().forEach(System.out::println); break;
            case 8: selectedShop.getClients().forEach(System.out::println); break;
            case 9: printDefaulterClients(selectedShop.getDefaulterClientsList()); break;
            case 10: System.out.println(selectedShop); break;
            case 11: modifySelectedShop(); break;
            case 12: shops.forEach(System.out::println); break;
            case 13: shops.add(new Shop(InputAsker.askNonEmptyString("New shop name: "), InputAsker.askNonEmptyString("New shop address: "))); break;
            case 14: changeSelectedShop();
        }
    }

    // Asks for the attribute details of a Client and returns the Object
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

    // Asks for the attribute details of a Camera and returns the Object
    private static Camera askCameraTypeDetails()
    {
        System.out.println("--- NEW CAMERA TYPE: Please provide details below ---");
        return new Camera(
                new Brand(
                        InputAsker.askNonEmptyString("Camera brand name: "),
                        InputAsker.askNonEmptyString("Camera reparation service address: ")
                ),
                InputAsker.askNonEmptyString("Camera model: "),
                InputAsker.askYNQuestion("Has it got flash?")
        );
    }

    // Shows to the user the lists of available Camera types and makes him select one, then makes him give a reference for the item and returns it
    private static CameraItem askCameraItemDetails() throws NoCamerasException
    {
        if(selectedShop.getCameras().size() == 0)
            throw new NoCamerasException();
        System.out.println("--- THESE ARE THE CAMERA TYPES REGISTERED: Please select one to add an item of it ---");
        int cameraIndex = 1;
        for(Camera camera : selectedShop.getCameras())
        {
            System.out.printf("Camera number %d%n", cameraIndex);
            System.out.println(camera);
            cameraIndex++;
        }
        Camera selectedCamera = selectedShop.getCameras().get(InputAsker.askIntBetween("Selected camera: ", 1, selectedShop.getCameras().size()) - 1);
        return new CameraItem(
                selectedCamera,
                InputAsker.askNonEmptyString("New Camera Item reference: ")
        );
    }

    // Prints the defaulter clients list
    private static void printDefaulterClients(ArrayList<Client> defaulterClients)
    {
        if(defaulterClients.size() == 0) {
            System.out.println("There's no defaulter clients. What a time to be alive!");
            return;
        }
        System.out.println("--- DEFAULTER CLIENTS LIST ---");
        defaulterClients.forEach(System.out::println);
    }

    // Lets the user modify the name and address of the currently selected shop
    private static void modifySelectedShop()
    {
        System.out.println(selectedShop);
        String newName = InputAsker.askString("Input a new name for the shop (don't type anything to leave it unchanged): ");
        String newAddress = InputAsker.askString("Input a new sddress for the shop (don't type anything to leave it unchanged): ");
        if( ! newName.equals("")) {
            selectedShop.setName(newName);
        }
        if( ! newAddress.equals("")) {
            selectedShop.setAddress(newAddress);
        }
    }

    private static void changeSelectedShop()
    {
        System.out.println("--- THESE ARE THE EXISTING SHOPS: Please select which one would you like to switch to ---");
        int shopIndex = 1;
        for(Shop shop : shops)
        {
            System.out.printf("Shop number %d%n", shopIndex);
            System.out.println(shop);
            shopIndex++;
        }
        selectedShop = shops.get(InputAsker.askIntBetween("Selected shop: ", 1, shops.size()) - 1);
    }
}