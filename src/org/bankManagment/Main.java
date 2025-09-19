package org.bankManagment;


import org.bankManagment.Domain.User;
import org.bankManagment.RepositoryMemory.inMemoryUserRepository;
import org.bankManagment.Service.AuthService;

import java.util.Scanner;

public class Main {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService(new inMemoryUserRepository());
        User authenticatedUser = null;

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
                    if (auth.register(fullName, email, password) && confirmPassword.equals(password)) {
                        System.out.println("You have successfully registered!");
                    } else {
                        System.out.println("Sorry email or password is incorrect!");
                    }
                    break;
                case 2:
                    System.out.println("email: ");
                    String loginEmail = sc.nextLine();
                    System.out.println("Password:");
                    String loginPassword = sc.nextLine();

                    if (auth.login(loginEmail, loginPassword)) {
                        authenticatedUser = auth.getLoggedUser();
                        System.out.println("welcome" + authenticatedUser.getFullName());
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

                            switch (choice) {
                                case 10:
                                    auth.logout();
                                    System.out.println("You have been logged out. ");
                                    authenticatedUser = null;
                                    break;
                                case 11:
                                    System.out.println("Goodbye!");
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("invalid choice, please try again");
                                    break;
                            }
                        } while (authenticatedUser != null);
                    } else {
                        System.out.println("Sorry email or password is incorrect!");
                        System.out.println("--------------------------------------------------");
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
        } while (true);
    }
}
