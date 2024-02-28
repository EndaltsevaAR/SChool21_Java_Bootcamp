package ex04;

/**
 * The business logic level of the application is located in service classes. Such classes contain basic algorithms of the system, automated processes, 
 * etc. These classes are usually designed based on the Facade pattern that can encapsulate behavior of several classes.
In this case, TransactionsService class must contain a field of UserList type for user interactions and provide the following functionality:

Adding a user

Retrieving a user's balance

Performing a transfer transaction (user IDs and transfer amount are specified). In this case, two transactions of DEBIT/CREDIT types are created and 
added to recipient and sender. IDs of both transactions must be equal

Retrieving transfers of a specific user (an ARRAY of transfers is returned). Removing a transaction by ID for a specific user 
(transaction ID and user ID are specified)

Check validity of transactions (returns an ARRAY of unpaired transactions).

In case of an attempt to make a transfer of the amount exceeding user's residual balance, IllegalTransactionException runtime exception must be thrown.
An example of use of such classes shall be contained in Program file (creation, initialization, printing object content on a console).
 */

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();
        service.addUser("Harry", 10000);
        service.addUser("Hermione", 3200);
        System.out.println(service);

        System.out.println("Check add users");
        System.out.println("User 1 has " + service.getUserBalance(1));
        System.out.println("User 2 has " + service.getUserBalance(2) + "\n");

        System.out.println("Check add transaction");
        service.performTransfer(1, 2, 100);
        System.out.println("User 1 has " + service.getUserBalance(1));
        System.out.println("User 2 has " + service.getUserBalance(2) + "\n");

        // service.performTransfer(1, 2, 10000); // check exception

        System.out.println("Check getUserTransactions");
        service.performTransfer(1, 2, 200);
        service.performTransfer(1, 2, 300);
        service.performTransfer(2, 1, 800);
        System.out.println("User 1 has " + service.getUserBalance(1));
        System.out.println("User 2 has " + service.getUserBalance(2));
        System.out.println(Arrays.toString(service.getUserTransactions(1)));
        System.out.println(Arrays.toString(service.getUserTransactions(2)) + "\n");

        System.out.println("Check removing");
        service.removeTransaction(1, service.getUserTransactions(1)[0].getIdentifier());
        System.out.println("User 1 has " + service.getUserBalance(1));
        System.out.println("User 2 has " + service.getUserBalance(2));
        System.out.println(Arrays.toString(service.getUserTransactions(1)));
        System.out.println(Arrays.toString(service.getUserTransactions(2)) + "\n");

        System.out.println("Check uncorrect transactions");

        for (Transaction transaction : service.getUncorrectTransactions()) {
            System.out.println(transaction);
        }
    }
}