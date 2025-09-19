package org.bankManagment;


import org.bankManagment.RepositoryMemory.inMemoryUserRepository;
import org.bankManagment.Service.AuthService;

import java.util.Scanner;

public class Main {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService(new inMemoryUserRepository());

        int choice;
        do {
            System.out.println("Welcome: ");
            System.out.println("1- Register");
            System.out.println("2- Login ");
            System.out.println("3- Exit ");
            System.out.println("enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter your full name: ");
                    System.out.println("Full name: ");
                    String fullName = sc.nextLine();
                    System.out.println("Email: ");
                    String email = sc.nextLine();
                    System.out.println("Password: ");
                    String password = sc.nextLine();
                    System.out.println("confirm password: ");
                    String confirmPassword = sc.nextLine();
                    if (auth.register(fullName, email, password)&& confirmPassword.equals(password)) {
                        System.out.println("You have successfully registered!");
                    }else {
                        System.out.println("Sorry email or password is incorrect!");
                    }
                    break;
                case 2:
                    System.out.println("email: ");
                    String loginEmail = sc.nextLine();
                    System.out.println("Password:");
                    String loginPassword = sc.nextLine();
                    if (loginEmail != null && loginPassword != null) {
                        do {
                            System.out.println("1- Create account ");
                            System.out.println("2- List my accounts");
                            System.out.println("3- Deposit");
                            System.out.println("4- Withdraw");
                            System.out.println("5- Transfer");
                            System.out.println("6- Transaction history");
                            System.out.println("7- Update Profile");
                            System.out.println("8- Change Password");
                            System.out.println("9- Close account");
                            System.out.println("10- Logout");
                            System.out.println("11- Exit");
                            System.out.println("Enter your choice: ");
                            choice = sc.nextInt();
                            sc.nextLine();
                        } while (choice != 11);
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice != 3);
    }
}
