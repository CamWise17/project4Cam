import java.util.Scanner;
import java.util.List;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Bank bank = new Bank();

    public void runMenu() {
        int choice;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    openNewAccount();
                    break;
                case 2:
                    accessAccount();
                    break;
                case 3:
                    closeAllAccounts();
                    break;
                case 4:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid entry");
                    break;
            }
        } while (choice != 4);
    }

    private void displayMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Open a new account");
        System.out.println("2. Access an existing account");
        System.out.println("3. Close all accounts");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void openNewAccount() {
        System.out.println("Are you a new customer? (yes/no)");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("yes")) {
            Customer customer = createNewCustomer();
            if (customer != null) {
                enterDepositForNewAccount(customer);
            }
        } else if (answer.equalsIgnoreCase("no")) {
            System.out.println("Enter your PIN: ");
            int pin = scanner.nextInt();
            Customer customer = bank.getCustomerByPin(pin);
            if (customer == null) {
                System.out.println("PIN is not valid");
                return;
            }
            enterDepositForNewAccount(customer);
        } else {
            System.out.println("Invalid entry");
        }
    }

    private Customer createNewCustomer() {
        System.out.println("Enter first name: ");
        String firstName = scanner.next();
        System.out.println("Enter last name: ");
        String lastName = scanner.next();
        System.out.println("Enter 4-digit PIN: ");
        int pin = scanner.nextInt();
        Customer customer = new Customer(firstName, lastName, pin);
        bank.addCustomer(customer);
        return customer;
    }

    private void enterDepositForNewAccount(Customer customer) {
        System.out.println("Enter deposit amount for new account: ");
        double deposit = scanner.nextDouble();
        System.out.println("Is it a savings account? (yes/no)");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("Enter interest rate for the savings account: ");
            double interestRate = scanner.nextDouble();
            SavingsAccount account = new SavingsAccount(deposit, interestRate);
            customer.addAccount(account);
            System.out.println("New Savings Account Opened: " + account.toString());
        } else {
            Account account = new Account(deposit);
            customer.addAccount(account);
            System.out.println("New Account Opened: " + account.toString());
        }
    }

    private void accessAccount() {
        System.out.println("Enter your PIN: ");
        int pin = scanner.nextInt();
        Customer customer = bank.getCustomerByPin(pin);
        if (customer == null) {
            System.out.println("PIN is not valid");
            return;
        }

        System.out.println("Enter the account number you want to access: ");
        int accountNumber = scanner.nextInt();
        Account account = customer.getAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Account not found");
            return;
        }


    }

    private void closeAllAccounts() {
        System.out.println("Enter your PIN: ");
        int pin = scanner.nextInt();
        Customer customer = bank.getCustomerByPin(pin);
        if (customer == null) {
            System.out.println("PIN is not valid");
            return;
        }


        List<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {

            customer.removeAccount(account);
        }

        System.out.println("All accounts closed for the customer.");
    }
}