package ex05;

import java.util.UUID;

public interface TransactionsList {
    int size();

    void addTransaction(Transaction transaction);

    void removeTransactionByID(UUID id);

    Transaction[] getTransactionDyID(UUID id);

    void removeTransactionByIDAndUserID(int userID, UUID transactionID);

    Transaction[] getTransactionByIDAndUserID(int userID, UUID transactionID);

    Transaction[] toArray();
}
