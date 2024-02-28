package ex03;

/**
 * Unlike users, a list of transactions requires a special implementation approach. 
 * Since the number of transaction creation operations can be very large, we need a storage method to avoid a costly array size extension.
 *  In this task, we offer you to create TransactionsList interface describing the following behavior:

Add a transaction
Remove a transaction by ID (in this case, UUID string identifier is used)
Transform into array (ex. Transaction[] toArray())

A list of transactions shall be implemented as a linked list (LinkedList) in TransactionsLinkedList class. Therefore, each transaction shall contain a field with a link to the next transaction object.
If an attempt is made to remove a transaction with non-existent ID, TransactionNotFoundException runtime exception must be thrown.
An example of use of such classes shall be contained in Program file (creation, initialization, printing object content on a console).
Note:

We need to add transactions field of TransactionsList type to User class so that each user can store the list of their transactions.
A transaction must be added with a SINGLE operation (O(1))

LinkedList<T> nested Java class has the same structure, a bidirectional linked list.
 */

import java.util.Arrays;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User harry = new User("Harry", 10000);
        User hermione = new User("Hermione", 3200);
        System.out.println(harry);
        System.out.println(hermione + "\n");

        TransactionsList transactionsList = new TransactionsLinkedList();
        System.out.println(transactionsList);

        Transaction fromHarryToHermioneOut1 = new Transaction(harry, hermione, "OUTCOME", -100,
                UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        Transaction fromHarryToHermioneInc1 = new Transaction(hermione, harry, "INCOME", 100,
                UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        transactionsList.addTransaction(fromHarryToHermioneOut1);
        transactionsList.addTransaction(fromHarryToHermioneInc1);
        System.out.println("List is " + transactionsList);
        System.out.println(harry);
        System.out.println(hermione + "\n");

        transactionsList.removeTransactionByID(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        System.out.println("After removing: " + transactionsList);
        System.out.println(harry);
        System.out.println(hermione + "\n");

        Transaction fromHarryToHermioneOut2 = new Transaction(harry, hermione, "OUTCOME", -100,
                UUID.fromString("550e8400-e29b-41d4-a716-446655440003"));
        Transaction fromHarryToHermioneInc2 = new Transaction(hermione, harry, "INCOME", 100,
                UUID.fromString("550e8400-e29b-41d4-a716-446655440003"));
        transactionsList.addTransaction(fromHarryToHermioneOut2);
        transactionsList.addTransaction(fromHarryToHermioneInc2);

        Transaction[] transactionsArray = transactionsList.toArray();
        System.out.println("Array is " + Arrays.toString(transactionsArray));
        System.out.println(harry);
        System.out.println(hermione + "\n");
    }
}
