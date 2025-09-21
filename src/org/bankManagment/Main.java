package org.bankManagment;


import org.bankManagment.Domain.Account;
import org.bankManagment.Domain.Transaction;
import org.bankManagment.Domain.User;
import org.bankManagment.RepositoryMemory.inMemoryAccountRepository;
import org.bankManagment.RepositoryMemory.inMemoryTransactionRepository;
import org.bankManagment.RepositoryMemory.inMemoryUserRepository;
import org.bankManagment.Service.AccountService;
import org.bankManagment.Service.AuthService;
import org.bankManagment.Service.TransactionService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService(new inMemoryUserRepository());
        User authenticatedUser = null;
        AccountService accountService = new AccountService(new inMemoryAccountRepository());
        TransactionService transactionService = new TransactionService(new inMemoryTransactionRepository());

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
                                case 1:
                                    UUID userId = authenticatedUser.getId();
                                    if (accountService.createAccount(userId)) {
                                        System.out.println("Congratulations! You have successfully create your account.!");
                                    } else {
                                        System.out.println("something went wrong!");
                                    }
                                    break;
                                case 2:
                                    UUID ownerId = authenticatedUser.getId();
                                    List<Account> accounts = accountService.getAllAccounts(ownerId);
                                    System.out.println("--------- List of your all accounts : ---------");
                                    accounts.stream().forEach(System.out::println);
                                    break;
                                case 3:
                                    List<Account> dispositAccounts = accountService.getAllAccounts(authenticatedUser.getId());
                                    System.out.println("------- list of your accounts----------");
                                    dispositAccounts.forEach(System.out::println);

                                    System.out.println("Enter your account ID : ");
                                    String despositAccountId = sc.nextLine();

                                    System.out.println("Enter the amount you want to withdraw : ");
                                    BigDecimal dispositAmount = sc.nextBigDecimal();

                                    if (accountService.desposit(despositAccountId, dispositAmount)) {
                                        UUID owner = authenticatedUser.getId();
                                        transactionService.addDesposit(owner, despositAccountId, dispositAmount);
                                        System.out.println("You have successfully deposited your account.");
                                    }else{
                                        System.out.println("something went wrong!");
                                    }

                                    break;
                                case 4:
                                    List<Account> withdrawAccounts = accountService.getAllAccounts(authenticatedUser.getId());
                                    System.out.println("------- list of your accounts----------");
                                    withdrawAccounts.forEach(System.out::println);

                                    System.out.println("Enter your account ID : ");
                                    String accountId = sc.nextLine();

                                    System.out.println("Enter the amount you want to withdraw : ");
                                    BigDecimal amount = sc.nextBigDecimal();

                                    if (accountService.withdraw(accountId, amount)) {
                                        UUID owner = authenticatedUser.getId();
                                        transactionService.addWithdraw(owner, accountId, amount);
                                        System.out.println("The amount has been withdrawn");
                                    }else {
                                        System.out.println("Sorry something went wrong, please try again");
                                    }
                                    break;
                                case 6:
                                    System.out.println("----------------Your Transactions history-----------------");
                                    List<Transaction> transactions= transactionService.getAllTransactions(authenticatedUser.getId());
                                    transactions.forEach(System.out::println);
                                    break;
                                case 7:
                                    System.out.println("enter your new username:");
                                    String userName = sc.nextLine();
                                    System.out.println("enter your new email:");
                                    String newEmail = sc.nextLine();
                                    if (auth.updateProfil(userName, newEmail)) {
                                        System.out.println("You have successfully update your account!");
                                    } else {
                                        System.out.println("Sorry something went wrong, please try again!");
                                    }
                                case 8:
                                    System.out.println("Enter Your new password:");
                                    String newPassword = sc.nextLine();
                                    if(auth.changePassword(newPassword)) {
                                        System.out.println("You have successfully changed your password!");
                                    }else{
                                        System.out.println("Sorry something went wrong, please try again!");
                                    }
                                    break;
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
