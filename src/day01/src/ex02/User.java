package ex02;

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

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}

