package ex03;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private String transferCategory;
    private int transferAmount;

    public UUID getIdentifier() {
        return identifier;
    }

    public Transaction(User sender, User recipient, String transferCategory, int transferAmount, UUID identifier) {
        this.sender = sender;
        this.recipient = recipient;
        this.transferCategory = transferCategory;
        setTransferAmount(transferAmount, transferCategory);
        this.identifier = identifier;
        sender.setBalance(sender.getBalance() + transferAmount);
    }

    public void setTransferAmount(int transferAmount, String transferCategory) {
        if ((transferCategory.equals("INCOME") && transferAmount >= 0) ||
                (transferCategory.equals("OUTCOME") && transferAmount <= 0)) {
            this.transferAmount = transferAmount;
        } else {
            System.err.println("There is mistake at sum for this type of operation!");
        }
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public User getSender() {
        return sender;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "recipient=" + recipient +
                ", sender=" + sender +
                ", transferCategory='" + transferCategory + '\'' +
                ", transferAmount=" + transferAmount +
                ", identifier=" + identifier +
                '}';
    }
}