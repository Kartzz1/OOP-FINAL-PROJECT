import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pet> pets = new ArrayList<>();

        pets.add(new Pet("Buddy", "Dog", "Male", "Aspin", true));
        pets.add(new Pet("Mittens", "Cat", "Female", "Siamese", true));
        pets.add(new Pet("Tweety", "Bird", "Female", "Parrot", false));

        boolean running = true;

        while (running) {
            System.out.println("\n===== Welcome to the Pet Adoption System =====");
            System.out.println("1. Staff - Add a new pet");
            System.out.println("2. Adopter - Adopt a pet");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String option = sc.nextLine();

            switch (option) {
                case "1":
                    addNewPet(sc, pets);
                    break;
                case "2":
                    adoptPet(sc, pets);
                    break;
                case "3":
                    System.out.println("Thank you for visiting! Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }

        sc.close();
    }

    public static void addNewPet(Scanner sc, ArrayList<Pet> pets) {
        System.out.print("\nEnter Pet Name: ");
        String petName = sc.nextLine();

        String petType;
        do {
            System.out.print("Enter Pet Type (Dog/Cat/Bird): ");
            petType = sc.nextLine();
            if (!petType.equalsIgnoreCase("Dog") && 
                !petType.equalsIgnoreCase("Cat") && 
                !petType.equalsIgnoreCase("Bird")) {
                System.out.println("Invalid type. Enter Dog, Cat, or Bird.");
            }
        } while (!petType.equalsIgnoreCase("Dog") && 
                 !petType.equalsIgnoreCase("Cat") && 
                 !petType.equalsIgnoreCase("Bird"));

        String petGender;
        do {
            System.out.print("Enter Pet Gender (Male/Female): ");
            petGender = sc.nextLine();
            if (!petGender.equalsIgnoreCase("Male") && !petGender.equalsIgnoreCase("Female")) {
                System.out.println("Invalid gender. Enter Male or Female.");
            }
        } while (!petGender.equalsIgnoreCase("Male") && !petGender.equalsIgnoreCase("Female"));

        System.out.print(
    "Enter Pet Breed:\n" +
    "(:Dog: Aspin/Shih Tzu, Others)\n" +
    "(Cat: Puspin/Siamese, Others)\n" +
    "(Bird: Dove/Parrot, Others): ");
    
        String petBreed = sc.nextLine();

        pets.add(new Pet(petName, petType, petGender, petBreed, true));
        System.out.println(" Pet added successfully!");
    }

    public static void adoptPet(Scanner sc, ArrayList<Pet> pets) {
        Adopter adopter = new Adopter();

        String name;
        do {
            System.out.print("\nEnter your full name: ");
            name = sc.nextLine();
            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid name. Letters only.");
            }
        } while (!name.matches("[a-zA-Z ]+"));
        System.out.print("Enter your address: ");
        String address = sc.nextLine();
        String gender;
        do {
            System.out.print("Enter your gender (Male/Female): ");
            gender = sc.nextLine();
            if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
                System.out.println("Invalid. Enter Male or Female.");
            }
        } while (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female"));

        String contact;
        do {
            System.out.print("Enter your contact number: ");
            contact = sc.nextLine();
            if (!contact.matches("[0-9]+")) {
                System.out.println("Invalid contact number. Digits only.");
            }
        } while (!contact.matches("[0-9]+"));

        adopter.setPersonInfo(name, address, gender, contact);
        Pet selectedPet = null;
        boolean choosing = true;

        while (choosing) {
            System.out.println("\n===== AVAILABLE PETS =====");
            for (int i = 0; i < pets.size(); i++) {
                System.out.println((i + 1) + ". " + pets.get(i).getPetName() + " - " +
                        pets.get(i).getPetType() + " - " +
                        pets.get(i).getPetBreed() + " - " +
                        pets.get(i).getPetGender() + " - " +
                        (pets.get(i).isAvailable() ? "Available" : "Adopted"));
            }
            System.out.println("===========================");

            int choice = 0;
            do {
                System.out.print("Enter the number of the pet you want to adopt: ");
                try {
                    choice = Integer.parseInt(sc.nextLine());
                    if (choice < 1 || choice > pets.size()) {
                        System.out.println("Invalid selection. Choose a number from the list.");
                    } else if (!pets.get(choice - 1).isAvailable()) {
                        System.out.println("Sorry, that pet is already adopted. Choose another pet.");
                        choice = 0;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Enter a number.");
                }
            } while (choice < 1 || choice > pets.size());

            selectedPet = pets.get(choice - 1);
            choosing = false;
        }

        System.out.println("\nThe pet is available!");
        selectedPet.displayPetInfo();

        System.out.print("\nAre you sure you want to adopt this pet? (Yes/No): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("Yes")) {
            selectedPet.markAsAdopted();
            printReceipt(adopter, selectedPet);
        } else {
            System.out.println("Thank you for your interest!");
        }
    }

    public static void printReceipt(Adopter adopter, Pet pet) {
        int fee = pet.getAdoptionFee();

        System.out.println("\n===== PET ADOPTION RECEIPT =====");
        adopter.displayAdopterInfo();
        System.out.println("\nPet Adopted:");
        pet.displayPetInfo();
        System.out.println("Total Adoption Fee: ₱" + fee);
        System.out.println("\nCongratulations! You are now the proud owner of " + pet.getPetName() + "!");
        System.out.println("=================================");

        String fileName = "AdoptionReceipt_" + pet.getPetName() + ".txt";
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write("===== PET ADOPTION RECEIPT =====\n");
            fw.write("Name: " + adopter.getName() + "\n");
            fw.write("Address: " + adopter.getAddress() + "\n");
            fw.write("Gender: " + adopter.getGender() + "\n");
            fw.write("Contact Number: " + adopter.getContactNumber() + "\n");
            fw.write("\nPet Adopted:\n");
            fw.write("Pet Name: " + pet.getPetName() + "\n");
            fw.write("Pet Type: " + pet.getPetType() + "\n");
            fw.write("Pet Breed: " + pet.getPetBreed() + "\n");
            fw.write("Pet Gender: " + pet.getPetGender() + "\n");
            fw.write("\nTotal Adoption Fee: ₱" + fee + "\n");
            fw.write("\nThank you for giving this pet a loving home!\n");
            fw.write("=================================\n");
            System.out.println("\nReceipt saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving receipt file.");
        }
    }
}

class Person {
    protected String name;
    protected String address;
    protected String gender;

    public void setPersonInfo(String n, String a, String g) {
        name = n;
        address = a;
        gender = g;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Gender: " + gender);
    }
}

class Adopter extends Person {
    private String contactNumber;

    public void setPersonInfo(String n, String a, String g, String contact) {
        name = n;
        address = a;
        gender = g;
        contactNumber = contact;
    }

    public String getContactNumber() { return contactNumber; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getGender() { return gender; }

    public void displayAdopterInfo() {
        System.out.println("\nAdopter Information:");
        displayInfo();
        System.out.println("Contact Number: " + contactNumber);
    }
}

class Pet {
    private String petName;
    private String petType;
    private String petBreed;
    private String petGender;
    private boolean isAvailable;

    public Pet(String name, String type, String gender, String breed, boolean available) {
        petName = name;
        petType = type;
        petGender = gender;
        petBreed = breed;
        isAvailable = available;
    }

    public void markAsAdopted() { isAvailable = false; }
    public boolean isAvailable() { return isAvailable; }

    public void displayPetInfo() {
        System.out.println("Pet Name: " + petName);
        System.out.println("Pet Type: " + petType);
        System.out.println("Pet Breed: " + petBreed);
        System.out.println("Pet Gender: " + petGender);
        System.out.println("Status: " + (isAvailable ? "Available" : "Adopted"));
    }

    public String getPetName() { return petName; }
    public String getPetType() { return petType; }
    public String getPetBreed() { return petBreed; }
    public String getPetGender() { return petGender; }

    public int getAdoptionFee() {
        switch(petType.toLowerCase()) {
            case "dog": return 1000;
            case "cat": return 800;
            case "bird": return 500;
            default: return 300;
        }
    }
}

