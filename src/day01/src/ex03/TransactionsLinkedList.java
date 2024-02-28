package ex03;

import java.util.ArrayList;
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
    public void removeTransactionByID(UUID ID) {
        List<Transaction> transactionListToDelete = new ArrayList<>();
        for (Transaction transaction : transactionLinkedList) {
            if (transaction.getIdentifier().equals(ID)) {
                transactionListToDelete.add(transaction);
            }
        }
        if (transactionListToDelete.isEmpty()) {
            throw new TransactionNotFoundException("There is no transaction with that ID");
        } else {
            for (Transaction transaction : transactionListToDelete) {
                transactionLinkedList.remove(transaction);
                transaction.getSender().setBalance(transaction.getSender().getBalance() + transaction.getTransferAmount());
            }
        }
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
