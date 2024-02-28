package ex04;

import java.util.Objects;

public class User {
    private int identifier;
    private String name;
    private int balance;

    public User(String name, int balance) {
        this.name = name;
        if (balance < 0) {
            System.err.println("Balance is negative!");
        } else {
            this.balance = balance;
        }
        this.identifier = UserIdsGenerator.getInstance().generateId();
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return identifier == user.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}