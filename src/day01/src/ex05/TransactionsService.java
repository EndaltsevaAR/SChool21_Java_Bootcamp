package ex05;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TransactionsService {
    private UsersList usersArray;
    private TransactionsList transactionsList;

    public TransactionsService() {
        usersArray = new UsersArrayList();
        transactionsList = new TransactionsLinkedList();
    }

    public void addUser(String name, int balance) {
        User user = new User(name, balance);
        usersArray.addUser(user);
    }

    public int getUserBalance(int id) {
        return usersArray.getUserByID(id).getBalance();
    }

    public void performTransfer(int senderID, int recipientID, int transferAmount) {
        UUID id = UUID.randomUUID();
        if (usersArray.getUserByID(senderID).getBalance() < transferAmount) {
            throw new IllegalTransactionException("User has hot have enough money");
        }
        Transaction outTransaction = new Transaction(usersArray.getUserByID(senderID),
                usersArray.getUserByID(recipientID), "OUTCOME", -transferAmount, id);
        Transaction inTransaction = new Transaction(usersArray.getUserByID(recipientID),
                usersArray.getUserByID(senderID), "INCOME", transferAmount, id);
        transactionsList.addTransaction(outTransaction);
        transactionsList.addTransaction(inTransaction);
    }

    public Transaction[] getUserTransactions(int ID) {
        TransactionsList transactionsListForUser = new TransactionsLinkedList();
        User user = usersArray.getUserByID(ID);
        for (Transaction transaction : transactionsList.toArray()) {
            if (transaction.getSender().equals(user)) {
                transactionsListForUser.addTransaction(transaction);
            }
        }
        return transactionsListForUser.toArray();
    }

    public void removeTransaction(int userID, UUID transactionID) {
        transactionsList.removeTransactionByIDAndUserID(userID, transactionID);

    }

    public Transaction[] getUncorrectTransactions() {
        TransactionsList uncorrectList = new TransactionsLinkedList();
        Map<UUID, Integer> statuses = new HashMap<>();
        for (Transaction transaction : transactionsList.toArray()) {
            if (statuses.containsKey(transaction.getIdentifier())) {
                statuses.put(transaction.getIdentifier(), statuses.get(transaction.getIdentifier()) + 1);
            } else {
                statuses.put(transaction.getIdentifier(), 1);
            }
        }
        for (Map.Entry<UUID, Integer> pair : statuses.entrySet()) {
            if (pair.getValue() != 2) {
                for (Transaction transaction : transactionsList.getTransactionDyID(pair.getKey())) {
                    uncorrectList.addTransaction(transaction);
                }
            }
        }
        return uncorrectList.toArray();
    }

    @Override
    public String toString() {
        return "TransactionsService{" +
                "usersArray=" + usersArray +
                ", transactionsList=" + transactionsList +
                '}' + "\n";
    }

    public UsersList getUsersArray() {
        return usersArray;
    }
}