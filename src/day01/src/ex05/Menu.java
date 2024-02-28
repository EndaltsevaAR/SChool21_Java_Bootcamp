package ex05;

import java.util.Scanner;
import java.util.UUID;

import static java.lang.System.exit;

public class Menu {

    private TransactionsService service;

    public Menu() {
        service = new TransactionsService();
    }

    public void startApp() {
        Scanner scanner = new Scanner(System.in);
        boolean isClose;
        do {
            paintMenu();
            String userChoice = scanner.nextLine();
            isClose = doOperation(userChoice, scanner);

        } while (!isClose);
        scanner.close();
    }

    private boolean doOperation(String userChoice, Scanner scanner) {
        try {
            switch (userChoice) {
                case "1":
                    addUserOperation(scanner);
                    break;
                case "2":
                    viewUserBalancesOperation(scanner);
                    break;
                case "3":
                    performTransferOperation(scanner);
                    break;
                case "4":
                    viewTransactionForUserOperation(scanner);
                    break;
                case "5":
                    removeTransferByID(scanner);
                    break;
                case "6":
                    checkTransferValidity();
                    break;
                case "7":
                    System.out.println("By-by!");
                    return true;
                default:
                    throw new RuntimeException("There is no operation with such symbol");
            }
            System.out.println("---------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("There is some mistakes. Let's start again. Enter operation number, please!");
            userChoice = scanner.nextLine();
            if (userChoice.equals("7")) {
                exit(-1);
            }
            doOperation(userChoice, scanner);
        }
        return false;
    }

    private void paintMenu() {
        System.out.println("1. Add a user\n" +
                "2. View user balances\n" +
                "3. Perform a transfer\n" +
                "4. View all transactions for a specific user\n" +
                "5. DEV – remove a transfer by ID\n" +
                "6. DEV – check transfer validity\n" +
                "7. Finish execution");
    }

    private void addUserOperation(Scanner scanner) {
        System.out.println("Enter a user name and a balance by space");
        String userAddInfo = scanner.nextLine();
        String[] userAddInfoParts = userAddInfo.split(" ");
        service.addUser(userAddInfoParts[0], Integer.parseInt(userAddInfoParts[1]));
        System.out.println("User with id = "
                + service.getUsersArray().getUserByIndex(service.getUsersArray().getSizeUsers() - 1).getIdentifier()
                + " is added");
    }

    private void viewUserBalancesOperation(Scanner scanner) {
        System.out.println("Enter a user ID");
        int userID = Integer.parseInt(scanner.nextLine());
        int balance = service.getUserBalance(userID);
        System.out.println(service.getUsersArray().getUserByID(userID).getName() + " - " + balance);
    }

    private void performTransferOperation(Scanner scanner) {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String userAddInfo = scanner.nextLine();
        String[] userAddInfoParts = userAddInfo.split(" ");
        service.performTransfer(Integer.parseInt(userAddInfoParts[0]), Integer.parseInt(userAddInfoParts[1]),
                Integer.parseInt(userAddInfoParts[2]));
        System.out.println("The transfer is completed");
    }

    private void viewTransactionForUserOperation(Scanner scanner) {
        System.out.println("Enter a user ID");
        int userID = Integer.parseInt(scanner.nextLine());
        for (Transaction transaction : service.getUserTransactions(userID)) {
            int amount = transaction.getTransferAmount();
            if (transaction.getTransferCategory().equals("OUTCOME")) {
                amount = -amount;
            }
            System.out.println(
                    "To " + transaction.getRecipient().getName() + "(id = " + transaction.getRecipient().getIdentifier()
                            + ") " + (-amount) + " with id = " + transaction.getIdentifier().toString());
        }
    }

    private void removeTransferByID(Scanner scanner) {
        System.out.println("Enter a user ID and a transfer ID");
        String userAddInfo = scanner.nextLine();
        String[] userAddInfoParts = userAddInfo.split(" ");
        Transaction[] transactions = service.getUserTransactions(Integer.parseInt(userAddInfoParts[0]));
        Transaction findedTransaction = null;
        for (Transaction transaction : transactions) {
            if (transaction.getIdentifier().equals(UUID.fromString(userAddInfoParts[1]))) {
                findedTransaction = transaction;
            }
        }
        String builder = "Transfer TO " + findedTransaction.getRecipient().getName() + "(id = "
                + findedTransaction.getRecipient().getIdentifier() + ") " + (-findedTransaction.getTransferAmount())
                + " removed";

        service.removeTransaction(Integer.parseInt(userAddInfoParts[0]), UUID.fromString(userAddInfoParts[1]));
        System.out.println(builder);
    }

    private void checkTransferValidity() {
        Transaction[] uncorrectTransactions = service.getUncorrectTransactions();
        System.out.println("Check results:");
        for (Transaction transaction : uncorrectTransactions) {
            System.out.println(transaction.getSender().getName() + ("(id = ") + transaction.getSender().getIdentifier()
                    + ") has an unacknowledged transfer id = " + transaction.getIdentifier().toString() + " from "
                    + transaction.getRecipient().getName() + "(id = " + transaction.getRecipient().getIdentifier()
                    + ") for " + transaction.getTransferAmount());
        }
    }
}