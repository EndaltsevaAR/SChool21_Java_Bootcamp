package ex05;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private List<Transaction> transactionLinkedList;

    public TransactionsLinkedList() {
        transactionLinkedList = new LinkedList<>();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactionLinkedList.add(transaction);
    }

    @Override
    public void removeTransactionByID(UUID id) {
        Transaction[] transactionListToDelete = getTransactionDyID(id);
        removeTransactions(transactionListToDelete);
    }

    private void removeTransactions(Transaction[] transactionListToDelete) {
        if (transactionListToDelete.length == 0 || transactionListToDelete[0] == null) {
            throw new TransactionNotFoundException("There is no transaction with that ID");
        } else {
            for (Transaction transaction : transactionListToDelete) {
                transactionLinkedList.remove(transaction);
                transaction.getSender()
                        .setBalance(transaction.getSender().getBalance() + transaction.getTransferAmount());
            }
        }
    }

    @Override
    public Transaction[] getTransactionDyID(UUID id) {
        TransactionsList transactionListDyID = new TransactionsLinkedList();
        for (Transaction transaction : transactionLinkedList) {
            if (transaction.getIdentifier().equals(id)) {
                transactionListDyID.addTransaction(transaction);
            }
        }
        return transactionListDyID.toArray();
    }

    @Override
    public void removeTransactionByIDAndUserID(int userID, UUID transactionID) {
        Transaction[] transactionListToDelete = getTransactionByIDAndUserID(userID, transactionID);
        removeTransactions(transactionListToDelete);
    }

    @Override
    public Transaction[] getTransactionByIDAndUserID(int userID, UUID transactionID) {
        TransactionsList transactionListDyID = new TransactionsLinkedList();
        for (Transaction transaction : transactionLinkedList) {
            if (transaction.getIdentifier().equals(transactionID)
                    && transaction.getSender().getIdentifier() == userID) {
                transactionListDyID.addTransaction(transaction);
            }
        }
        return transactionListDyID.toArray();
    }

    public int size() {
        return transactionLinkedList.size();
    }

    @Override
    public Transaction[] toArray() {
        return transactionLinkedList.toArray(new Transaction[0]);
    }

    @Override
    public String toString() {
        return "TransactionsLinkedList{" +
                "transactionLinkedList=" + transactionLinkedList +
                '}' + "\n";
    }
}
